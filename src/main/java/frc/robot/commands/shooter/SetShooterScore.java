package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.shooter.Shooter;

public class SetShooterScore extends Command {
    private Shooter shooter;
    private double speed;

    public SetShooterScore(Shooter shooter, double speed){
        this.shooter = shooter;
        this.speed = speed;
        addRequirements(shooter);
    }

    @Override
    public void initialize() { }

    @Override
    public void execute(){
        shooter.setSpeed(speed);
    }

    @Override
    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(boolean interrupted){
        shooter.setSpeed(speed);
    }
}