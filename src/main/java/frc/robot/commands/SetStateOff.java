package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.climber.Climber;
import frc.robot.subsystems.indexer.Indexer;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.shooter.Shooter;

public class SetStateOff extends Command{
    Intake intake;
    Indexer indexer;
    Shooter shooter;
    Climber climber;

    public SetStateOff(Intake intake, Indexer indexer, Shooter shooter, Climber climber){
        this.intake = intake;
        this.indexer = indexer;
        this.shooter = shooter;
        this.climber = climber;

        addRequirements(intake,
                        indexer,
                        shooter,
                        climber);   
    }

    @Override
    public void initialize() {
        shooter.setShooterOff();
        indexer.setIndexerOff();
        intake.setWheelsOff();
        climber.climberStop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
