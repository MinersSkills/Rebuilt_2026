package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.shooter.Shooter;

public class ShooterDelayTimer extends Command {

    private final Shooter shooter;
    private final Timer timer = new Timer();

    public ShooterDelayTimer(Shooter shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.setSpeedShootFront(1);
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        if (timer.hasElapsed(1.5)) {
            shooter.setSpeedShootEstrela(1);
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
