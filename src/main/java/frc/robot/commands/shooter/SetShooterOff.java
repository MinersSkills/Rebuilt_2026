package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.shooter.Shooter;

public class SetShooterOff extends Command {
    
    private final Shooter shooter;

    public SetShooterOff(Shooter shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        shooter.setShooterOff();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
