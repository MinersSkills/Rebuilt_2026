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
        timer.reset();
        timer.start();

        indexer.setIndexerCounterClock();
        intake.setIntakeDown();
        shooter.setSpeedShootFront();
    }

    @Override
    public void execute() {
        if (timer.hasElapsed(1.5)){
            shooter.setSpeedShootStars();
            indexer.setIndexerOn();
        }
    }

    @Override
    public void end(boolean interrupted) {
        intake.setIntakeMiddle();
        intake.setWheelsIntake();
    }

    @Override
    public boolean isFinished() {
        if (timer.hasElapsed(3)){
            return true;
        } else {
            return false;
        }
    }
}