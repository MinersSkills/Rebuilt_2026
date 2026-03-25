# Peter Lund — Rebuilt 2026

This repository contains the robot code for the "Peter Lund". It uses WPILib, a command-based structure, and a swerve drive. The codebase is laid out under `src/main/java/frc/robot`.

Highlights
- Controls  
  The primary control mappings and subsystem wiring live in [`frc.robot.RobotContainer`](src/main/java/frc/robot/RobotContainer.java) ([src/main/java/frc/robot/RobotContainer.java](src/main/java/frc/robot/RobotContainer.java)). Driver and operator inputs are merged and routed to commands and subsystems from there.

- Swerve & Drive  
  The swerve drive implementation and helpers are in the swervedrive subsystem: [`frc.robot.subsystems.swervedrive.SwerveSubsystem`](src/main/java/frc/robot/subsystems/swervedrive/SwerveSubsystem.java) ([src/main/java/frc/robot/subsystems/swervedrive/SwerveSubsystem.java](src/main/java/frc/robot/subsystems/swervedrive/SwerveSubsystem.java)). Autonomous and balancing utilities include [`frc.robot.commands.swervedrive.auto.AutoBalanceCommand`](src/main/java/frc/robot/commands/swervedrive/auto/AutoBalanceCommand.java) ([src/main/java/frc/robot/commands/swervedrive/auto/AutoBalanceCommand.java](src/main/java/frc/robot/commands/swervedrive/auto/AutoBalanceCommand.java)), which uses a PID controller to hold robot pitch.

- Shooter & Scoring  
  Shooter subsystem and commands are under `frc.robot.subsystems.shooter` and `frc.robot.commands.shooter`. Example command: [`frc.robot.commands.shooter.SetShooterOff`](src/main/java/frc/robot/commands/shooter/SetShooterOff.java) ([src/main/java/frc/robot/commands/shooter/SetShooterOff.java](src/main/java/frc/robot/commands/shooter/SetShooterOff.java)), which calls into the RobotContainer
Project structure (key packages & files)
- Commands
  - [src/main/java/frc/robot/commands](src/main/java/frc/robot/commands) — top-level commands package
  - [`frc.robot.commands.swervedrive.auto.AutoBalanceCommand`](src/main/java/frc/robot/commands/swervedrive/auto/AutoBalanceCommand.java) — autonomous balancing routine
  - [`frc.robot.commands.shooter.SetShooterOff`](src/main/java/frc/robot/commands/shooter/SetShooterOff.java) — example shooter command
- Subsystems
  - [src/main/java/frc/robot/subsystems](src/main/java/frc/robot/subsystems) — subsystems root
  - [`frc.robot.subsystems.swervedrive.SwerveSubsystem`](src/main/java/frc/robot/subsystems/swervedrive/SwerveSubsystem.java) — swerve drive subsystem
  - [`frc.robot.subsystems.shooter.Shooter`](src/main/java/frc/robot/subsystems/shooter/Shooter.java) — shooter subsystem
- Robot entrypoints
  - [`frc.robot.RobotContainer`](src/main/java/frc/robot/RobotContainer.java) — button bindings and command wiring
- Utilities & other helpers
  - [src/main/java/frc/robot/poseflipper](src/main/java/frc/robot/poseflipper) — pose utilities (e.g. `frc.robot.poseflipper.PoseFlipper`)
