package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.indexer.Indexer;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.shooter.Shooter;

public class SetStateOff extends Command{
    Intake intake;
    Indexer indexer;
    Shooter shooter;

    public SetStateOff(Intake intake, Indexer indexer, Shooter shooter){
        this.intake = intake;
        this.indexer = indexer;
        this.shooter = shooter;

        addRequirements(intake,
                        indexer,
                        shooter);   
    }

    @Override
    public void initialize() {
        shooter.setShooterOff();
        indexer.setIndexerOff();
        intake.setWheelsOff();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
