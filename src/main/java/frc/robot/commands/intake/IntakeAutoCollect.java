package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.generalconstants.IntakeConstants;
import frc.robot.subsystems.intake.Intake;

public class IntakeAutoCollect extends Command {
    private Intake intake;

    public IntakeAutoCollect(Intake intake){
        this.intake = intake;

        addRequirements(intake);
    }

    @Override
    public void initialize() { }

    @Override
    public void execute(){
        intake.setIntakeDown();
        intake.setWheelsIntake();
    }

    @Override
    public boolean isFinished(){
        if (intake.intakeEncoder.getPosition() >= IntakeConstants.Setpoints.POSITION_DOWN - 0.2){
            return true;
        } else {
            return false;
    }
}

@Override
public void end(boolean interrupted) { 
    intake.setPivotOff();
}
}