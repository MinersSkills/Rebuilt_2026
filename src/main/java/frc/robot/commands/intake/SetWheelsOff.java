package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.intake.Intake;

public class SetWheelsOff extends Command {
        private Intake intake;

    public SetWheelsOff(Intake intake){
        this.intake = intake;

        addRequirements(intake);
    }

    @Override
    public void initialize() { }

    @Override
    public void execute(){
        intake.setWheelsOff();;
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}