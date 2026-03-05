package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.climber.Climber;

public class StopClimber extends Command{
    
    Climber climber;

    public StopClimber(Climber climber){
        this.climber = climber;
        addRequirements(climber);
    }

    @Override
    public void initialize() {
        climber.climberStop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
