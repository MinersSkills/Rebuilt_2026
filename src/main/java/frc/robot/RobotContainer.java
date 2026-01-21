// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.path.PathConstraints;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.shooter.SetShooterScore;
import frc.robot.commands.swervedrive.drivebase.DriveToPose;
import frc.robot.joystick.KeyboardController;
import frc.robot.poseflipper.PoseFlipper;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import java.io.File;
import swervelib.SwerveInputStream;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very
 * little robot logic should actually be handled in the {@link Robot} periodic
 * methods (other than the scheduler calls).
 * Instead, the structure of the robot (including subsystems, commands, and
 * trigger mappings) should be declared here.
 */
public class RobotContainer {
        SendableChooser<Command> autoChooser;

        Shooter shooter = new Shooter();

        // Replace with CommandPS4Controller or CommandJoystick if needed
        final CommandXboxController driverXbox = new CommandXboxController(0);
        final KeyboardController keyboardController = new KeyboardController();
        // The robot's subsystems and commands are defined here...
        private final SwerveSubsystem drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(),
                        "swerve/neo"));

        /**
         * Converts driver input into a field-relative ChassisSpeeds that is controlled
         * by angular velocity.
         */
        SwerveInputStream driveAngularVelocity = SwerveInputStream.of(drivebase.getSwerveDrive(),
                        () -> -driverXbox.getLeftY(),
                        () -> -driverXbox.getLeftX())
                        .withControllerRotationAxis(driverXbox::getRightX)
                        .deadband(OperatorConstants.DEADBAND)
                        .scaleTranslation(.9)
                        .scaleRotation(.4)
                        .allianceRelativeControl(false);

        /**
         * Clone's the angular velocity input stream and converts it to a fieldRelative
         * input stream.
         */
        public SwerveInputStream driveDirectAngle = driveAngularVelocity.copy()
                        .withControllerHeadingAxis(() -> driverXbox.getRightX() * -1,
                                        () -> driverXbox.getRightY() * -1)
                        .headingWhile(true);

        /**
         * Clone's the angular velocity input stream and converts it to a robotRelative
         * input stream.
         */
        SwerveInputStream driveRobotOriented = driveAngularVelocity.copy().robotRelative(true);

        SwerveInputStream driveAngularVelocityKeyboard = SwerveInputStream.of(drivebase.getSwerveDrive(),
                        () -> -driverXbox.getLeftY(),
                        () -> -driverXbox.getLeftX())
                        .withControllerRotationAxis(() -> driverXbox.getRawAxis(
                                        2))
                        .deadband(OperatorConstants.DEADBAND)
                        .scaleTranslation(0.8)
                        .allianceRelativeControl(true);
        // Derive the heading axis with math!
        SwerveInputStream driveDirectAngleKeyboard = driveAngularVelocityKeyboard.copy()
                        .withControllerHeadingAxis(() -> Math.sin(
                                        driverXbox.getRawAxis(
                                                        2) *
                                                        Math.PI)
                                        *
                                        (Math.PI *
                                                        2),
                                        () -> Math.cos(
                                                        driverXbox.getRawAxis(
                                                                        2) *
                                                                        Math.PI)
                                                        *
                                                        (Math.PI *
                                                                        2))
                        .headingWhile(true)
                        .translationHeadingOffset(true)
                        .translationHeadingOffset(Rotation2d.fromDegrees(
                                        0));

        /**
         * The container for the robot. Contains subsystems, OI devices, and commands.
         */
        public RobotContainer() {
                // Configure the trigger bindings
                configureBindings();

                autoChooser = AutoBuilder.buildAutoChooser();
                SmartDashboard.putData("Auto Chooser", autoChooser);

                DriverStation.silenceJoystickConnectionWarning(true);
                NamedCommands.registerCommand("test", Commands.print("I EXIST"));
        }

    /**
     * Use this method to define your trigger->command mappings. Triggers can be
     * created via the
     * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
     * an arbitrary predicate, or via the
     * named factories in
     * {@link edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses
     * for
     * {@link CommandXboxController
     * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller PS4}
     * controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick
     * Flight joysticks}.
     */
    private void configureBindings() {
        driveDirectAngle.aim(PoseFlipper.hubCenter());

        Command driveFieldOrientedDirectAngle = drivebase.driveFieldOriented(driveDirectAngle);
        drivebase.setDefaultCommand(driveFieldOrientedDirectAngle);

        // COMANDOS PILOTOS//

        driverXbox.back().onTrue(Commands.runOnce(() -> CommandScheduler.getInstance().cancelAll())); /*Cancelar comandos */

        driverXbox.y().toggleOnTrue(
                Commands.runEnd(() -> driveDirectAngle.aimWhile(true), () -> driveDirectAngle.aimWhile(false))); /* Travar a mira no HUB */

        driverXbox.x().onTrue(
          AutoBuilder.pathfindToPose(new Pose2d(6.7, 5.518, new Rotation2d(Units.degreesToRadians(-142)
                )), new PathConstraints(
                3,
                3,                             /* Mandar passar pela rampa pelo lado esquerdo */
                Units.degreesToRadians(360),
                Units.degreesToRadians(540)))      
        );
        

        driverXbox.a().onTrue(
        new RunCommand(
        () -> drivebase.alignSwerveWithAngle(
            () -> -driverXbox.getLeftY(),
            () -> -driverXbox.getLeftX(),
            -90   // ângulo do lado azul
            ),
            drivebase
        ).until(() -> drivebase.isAtHeading(-90))
        
        );

        // COMANDOS COPILOTO //

        keyboardController.getRightTrigger()
                .onTrue(new DriveToPose(drivebase, PoseFlipper.scorePositionRight(), driveDirectAngle)); // Posição para disparo do lado direito


        keyboardController.getUpTrigger()
                .onTrue(new DriveToPose(drivebase, PoseFlipper.scorePositionCenter(), driveDirectAngle)); // Posição para disparo no centro

        keyboardController.getLeftTrigger()
                .onTrue(new DriveToPose(drivebase, PoseFlipper.scorePositionLeft(), driveDirectAngle)); //Posição para disparo do lado esquerdo

        keyboardController.getATrigger()
                .onTrue(new SetShooterScore(shooter)); // Disparar manualmente o shooter

    }

        /* Comando para alinhar swerve com um angulo */

        public Command alignSwerveWithAngle(double angleDegrees) {
                SwerveInputStream alignWithAngles = driveDirectAngle.copy().withControllerHeadingAxis(
                                () -> {
                                        return Rotation2d.fromDegrees(angleDegrees).getCos();
                                },
                                () -> {
                                        return Rotation2d.fromDegrees(angleDegrees).getSin();
                                });
                return drivebase.driveFieldOriented(alignWithAngles);
        }

        /**
         * Use this to pass the autonomous command to the main {@link Robot} class.
         *
         * @return the command to run in autonomous
         */
        public Command getAutonomousCommand() {
                // An example command will be run in autonomous
                return autoChooser.getSelected();
        }

        public void setMotorBrake(boolean brake) {
                drivebase.setMotorBrake(brake);
        }

}
