package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.indexer.Indexer;
import frc.robot.subsystems.intake.Intake;

public class SetIntakeMode extends Command {

    private Intake intake;
    private Indexer indexer;

    public SetIntakeMode(Intake intake, Indexer indexer){
        this.intake = intake;
        this.indexer = indexer;
        addRequirements(intake,
                        indexer);
    }

    @Override
    public void initialize() {
        intake.setIntakeDown();
        intake.setWheelsIntake();
        // indexer.setIndexerOn();
     }

    @Override
    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(boolean interrupted){
        intake.setWheelsOff();
        indexer.setIndexerOff();
        intake.setPivotOff();
    }
}