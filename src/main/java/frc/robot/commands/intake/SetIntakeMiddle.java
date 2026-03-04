package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.generalconstants.IntakeConstants;
import frc.robot.subsystems.intake.Intake;

public class SetIntakeMiddle extends Command {
    Intake intake;

    public SetIntakeMiddle(Intake intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        intake.setIntakeMiddle();
    }

    @Override
    public boolean isFinished() {
        if (intake.intakeEncoder.getPosition() <= IntakeConstants.Setpoints.POSITION_MIDDLE + 0.5) {
            return true;
        } else {
            return false;
        }
    }
}
