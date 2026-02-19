package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.shooter.Shooter;

public class SetShooterStarsOn extends Command{
    private Shooter shooter;

    public SetShooterStarsOn(Shooter shooter){
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void initialize() { }

    @Override
    public void execute(){
        shooter.setSpeedShootStars();
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