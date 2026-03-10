package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.climber.Climber;

public class SetClimberDownAuto extends Command{
    
    Climber climber;

    public SetClimberDownAuto(Climber climber){
        this.climber = climber;
        addRequirements(climber);
    }

    @Override
    public void initialize() {
        climber.climberDown();
    }

    @Override
    public boolean isFinished() {
        if (climber.encoder.getPosition() <= 85 ) {
            return true;
        }else{
            return false;
        }

    }

    @Override
    public void end(boolean interrupted) {
        climber.climberStop();
    }
}
