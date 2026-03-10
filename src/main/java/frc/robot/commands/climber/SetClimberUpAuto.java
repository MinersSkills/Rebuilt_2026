package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.climber.Climber;

public class SetClimberUpAuto extends Command{
    
    Climber climber;

    public SetClimberUpAuto(Climber climber){
        this.climber = climber;
        addRequirements(climber);
    }

    @Override
    public void initialize() {
        climber.climberUp();
    }

    @Override
    public boolean isFinished() {
        if (climber.encoder.getPosition() >= 325 ) {
            return true;
        }else{
            return false;
        }

        
    }
}
