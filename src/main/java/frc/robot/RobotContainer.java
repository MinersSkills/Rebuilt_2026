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
import frc.robot.commands.ScoreCommand;
import frc.robot.commands.indexer.SetIndexerCounterClock;
import frc.robot.commands.indexer.SetIndexerOn;
import frc.robot.commands.intake.SetIntakeDown;
import frc.robot.commands.intake.SetIntakeMode;
import frc.robot.commands.intake.SetIntakeUp;
import frc.robot.commands.intake.SetWheelsOff;
import frc.robot.commands.intake.SetWheelsOn;
import frc.robot.commands.intake.SetWheelsOutake;
import frc.robot.commands.shooter.SetShooterStarsOn;
import frc.robot.commands.shooter.ShooterDelayTimer;
import frc.robot.commands.shooter.SetShooterFrontOn;
import frc.robot.commands.shooter.SetShooterOff;
import frc.robot.commands.swervedrive.drivebase.DriveToPose;
import frc.robot.joystick.KeyboardController;
import frc.robot.poseflipper.PoseFlipper;
import frc.robot.subsystems.climber.Climber;
import frc.robot.subsystems.indexer.Indexer;
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

        Shooter shooter = new Shooter();

        Intake intake = new Intake();

        Indexer indexer = new Indexer();

        Climber climber = new Climber();

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

        SwerveInputStream slowDrive = driveDirectAngle.copy()
                                        .scaleTranslation(0.3);

        /**
         * Clone's the angular velocity inp
         * ut stream and converts it to a robotRelative
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
                NamedCommands.registerCommand("IndexerOn", new SetIndexerOn(indexer));
                // NamedCommands.registerCommand("ShooterOn", new SetShooterScore(shooter, drivebase));
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
                driveDirectAngle.aim(PoseFlipper.hubCenterPosition());

                Command driveFieldOrientedDirectAngle = drivebase.driveFieldOriented(driveDirectAngle);

                Command slowDriveCommand = drivebase.driveFieldOriented(slowDrive);

                drivebase.setDefaultCommand(driveFieldOrientedDirectAngle);

                // COMANDOS PILOTOS//

                driverXbox.back().onTrue(Commands.runOnce(() -> CommandScheduler.getInstance().cancelAll()));

                // driverXbox.y().toggleOnTrue(

                //         Commands.runEnd(() -> driveDirectAngle.aimWhile(true),
                //                         () -> driveDirectAngle.aimWhile(false))
                // ); // lock aim in the hub

                driverXbox.b().onTrue(
                        Commands.sequence(
                                new DriveToPose(drivebase, PoseFlipper.scorePositionRight(), driveDirectAngle),
                                new ScoreCommand(shooter, indexer, intake)
                        )
                ); // auto score by the right

                driverXbox.a().onTrue(
                        Commands.sequence(
                                new DriveToPose(drivebase, PoseFlipper.scorePositionCenter(), driveDirectAngle),
                                new ScoreCommand(shooter, indexer, intake)
                        )
                ); // auto score by the center

                driverXbox.x().onTrue(
                        Commands.sequence(
                                new DriveToPose(drivebase, PoseFlipper.scorePositionLeft(), driveDirectAngle),
                                new ScoreCommand(shooter, indexer, intake)      
                        )
                ); // auto score by the left

                driverXbox.leftBumper().whileTrue(slowDriveCommand); // slow down the translation move

                driverXbox.leftTrigger().toggleOnTrue(
                        new SetIntakeMode(intake, indexer)
                ); // prepare the intake to collect

                // COMANDOS COPILOTO //

                keyboardController.getQTrigger().onTrue(
                        new SetIntakeDown(intake)
                ); // take the intake down

                keyboardController.getWTrigger().onTrue(
                        new SetIntakeUp(intake)
                ); // take the intake up

                keyboardController.getETrigger().onTrue(
                        new SetWheelsOn(intake)
                ); // set the wheels on to intake

                keyboardController.getRTrigger().onTrue(
                        new SetWheelsOutake(intake)
                ); // set the wheels counter clock wise to outake

                keyboardController.getTTrigger().onTrue(
                        new SetWheelsOff(intake)
                ); // set the wheels off

                keyboardController.getZTrigger().onTrue(
                        new ShooterDelayTimer(shooter)
                ); // set shooter on like on scoring

                keyboardController.getXTrigger().onTrue(
                        new SetShooterFrontOn(shooter)
                ); // set only the front axis on

                keyboardController.getCTrigger().onTrue(
                        new SetShooterStarsOn(shooter)
                ); // set only the starks axis on
                
                keyboardController.getVTrigger().onTrue(
                        new SetShooterOff(shooter)
                ); // turn off the shooter

                keyboardController.getNTrigger().onTrue(
                        new SetIndexerOn(indexer)
                ); // set the indexer on

                keyboardController.getMTrigger().onTrue(
                        new SetIndexerCounterClock(indexer)
                ); // set the indexer on in counter clock wise

                keyboardController.getCommaTrigger().onTrue(
                        new SetIndexerOn(indexer)
                ); // turn off the indexer
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
