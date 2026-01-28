package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class SetShooterScore extends Command {
    private Shooter shooter;
    private double poseX;
    private SwerveSubsystem swerve;

    public SetShooterScore(Shooter shooter, double speed, SwerveSubsystem swerve){
        this.shooter = shooter;
        this.swerve  = swerve;

        addRequirements(shooter,
        swerve);
    }

    @Override
    public void initialize() { }

    @Override
    public void execute(){
        poseX = swerve.getPose().getX();

        if (poseX <= 3 && poseX >= 3.5){
            shooter.setSpeedShoot(0.7);
        } else if (poseX <= 4 && poseX >= 4.5){
            shooter.setSpeedShoot(1);
        }
    }

    @Override
    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(boolean interrupted){
        shooter.setShooterOff();
    }
}