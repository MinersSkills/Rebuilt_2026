package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.climber.Climber;

public class SetClimberDown extends Command{
    
    Climber climber;

    public SetClimberDown(Climber climber){
        this.climber = climber;
        addRequirements(climber);
    }

    @Override
    public void initialize() {
        climber.climberDown();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
