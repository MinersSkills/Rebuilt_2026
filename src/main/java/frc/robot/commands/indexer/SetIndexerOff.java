package frc.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.indexer.Indexer;

public class SetIndexerOff extends Command{
        
    private Indexer indexer;

    public SetIndexerOff(Indexer indexer){
        this.indexer = indexer;
        addRequirements(indexer);
    }

    @Override
    public void initialize() { }

    @Override
    public void execute(){
        indexer.setIndexerOff();
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

