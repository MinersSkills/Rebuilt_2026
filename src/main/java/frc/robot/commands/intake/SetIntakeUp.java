package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.intake.Intake;

public class SetIntakeUp extends Command {
    private Intake intake;

    public SetIntakeUp(Intake intake){
        this.intake = intake;

        addRequirements(intake);
    }

    @Override
    public void initialize() { }

    @Override
    public void execute(){
        intake.setIntakeUp();
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}