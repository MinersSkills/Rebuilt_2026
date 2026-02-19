package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.indexer.Indexer;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.shooter.Shooter;

public class ScoreCommand extends Command{
    Shooter shooter;
    Indexer indexer;
    Intake intake;

    private final Timer timer = new Timer();

    public ScoreCommand(Shooter shooter, Indexer indexer, Intake intake){
        this.shooter = shooter;
        this.indexer = indexer;
        this.intake = intake;

        addRequirements(
            shooter,
            indexer,
            intake
        );
    }

    @Override
    public void initialize() {
        intake.setWheelsOff();
        intake.setIntakeDown();
        indexer.setIndexerOn();
        shooter.setSpeedShootFront();

        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        if (timer.hasElapsed(1.5)){
            shooter.setSpeedShootStars();
        }
    }

    @Override
    public void end(boolean interrupted) {
        shooter.setShooterOff();
        intake.setIntakeUp();
        indexer.setIndexerOff();
    }

    @Override
    public boolean isFinished() {
        if (timer.hasElapsed(20)){
        return true;
        } else {
            return false;
        }
    }
}