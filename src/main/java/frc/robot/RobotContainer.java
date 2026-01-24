// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.intake.SetIntakeDown;
import frc.robot.commands.intake.SetIntakeUp;
import frc.robot.commands.intake.SetWheelsOn;
import frc.robot.commands.swervedrive.drivebase.DriveToPose;
import frc.robot.generalconstants.FieldConstants;
import frc.robot.joystick.KeyboardController;
import frc.robot.subsystems.intake.Intake;
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

        Shooter shooter;

        Intake intake = new Intake();

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
                        .allianceRelativeControl(true);

        /**
         * Clone's the angular velocity input stream and converts it to a fieldRelative
         * input stream.
         */
        public SwerveInputStream driveDirectAngle = driveAngularVelocity.copy()
                        .withControllerHeadingAxis(
                                        () -> {
                                                double x = -driverXbox.getRightX();
                                                var alliance = DriverStation.getAlliance();

                                                if (alliance.isPresent()
                                                                && alliance.get() == DriverStation.Alliance.Red) {
                                                        x *= -1;
                                                }
                                                return x;
                                        },
                                        () -> {
                                                double y = -driverXbox.getRightY();
                                                var alliance = DriverStation.getAlliance();

                                                if (alliance.isPresent()
                                                                && alliance.get() == DriverStation.Alliance.Red) {
                                                        y *= -1;
                                                }
                                                return y;
                                        })
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
                NamedCommands.registerCommand("IntakeUp", new SetIntakeUp(intake));
                NamedCommands.registerCommand("IntakeDown", new SetIntakeDown(intake));
                NamedCommands.registerCommand("IntakeOn", new SetWheelsOn(intake));
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
                driveDirectAngle.aim(FieldConstants.HubCenter);

                Command driveFieldOrientedDirectAngle = drivebase.driveFieldOriented(driveDirectAngle);
                drivebase.setDefaultCommand(driveFieldOrientedDirectAngle);

                // COMANDOS PILOTOS//

                driverXbox.back().onTrue(Commands.runOnce(() -> CommandScheduler.getInstance().cancelAll()));

                driverXbox.y().toggleOnTrue(

                        Commands.runEnd(() -> driveDirectAngle.aimWhile(true),
                                        () -> driveDirectAngle.aimWhile(false))
                );

                driverXbox.b().onTrue(
                                Commands.sequence(
                                                new SetIntakeDown(intake),
                                                new SetWheelsOn(intake).withTimeout(5),
                                        new SetIntakeUp(intake)
                                )
                );


                // COMANDOS COPILOTO //

                keyboardController.getRightTrigger()
                                .onTrue(new DriveToPose(drivebase, FieldConstants.ScorePositionRight,
                                                driveDirectAngle));

                keyboardController.getUpTrigger()
                                .onTrue(new DriveToPose(drivebase, FieldConstants.ScorePositionCenter,
                                                driveDirectAngle));

                keyboardController.getLeftTrigger()
                                .onTrue(new DriveToPose(drivebase, FieldConstants.ScorePositionLeft, driveDirectAngle));

                /*
                 * keyboardController.getATrigger()
                 * .onTrue(new SetShooterScore(shooter, 1));
                 * keyboardController.getBTrigger()
                 * .onTrue(new SetWheelsOutake(intake));
                 */

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
