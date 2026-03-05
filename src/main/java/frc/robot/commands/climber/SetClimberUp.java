package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.climber.Climber;

public class SetClimberUp extends Command{
    
    Climber climber;

    public SetClimberUp(Climber climber){
        this.climber = climber;
        addRequirements(climber);
    }

    @Override
    public void initialize() {
        climber.climberUp();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
