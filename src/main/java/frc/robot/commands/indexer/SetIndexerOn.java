package frc.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.indexer.Indexer;

public class SetIndexerOn extends Command{
    
    private Indexer indexer;

    public SetIndexerOn(Indexer indexer){
        this.indexer = indexer;
        addRequirements(indexer);
    }

    @Override
    public void initialize() { }

    @Override
    public void execute(){
        indexer.setIndexerOn();
    }

    @Override
    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(boolean interrupted){
        indexer.setIndexerOff();
    }
}
