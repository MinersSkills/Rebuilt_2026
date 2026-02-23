package frc.robot.subsystems.intake;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.generalconstants.IntakeConstants;

public class Intake extends SubsystemBase {
    private SparkMaxConfig motorIntakeConfig;
    private SparkMaxConfig motorIntakeWheelsConfig;

    public static SparkMax intake = new SparkMax(32, MotorType.kBrushless);
    public static SparkMax intakeWheels = new SparkMax(22, MotorType.kBrushless);

    public RelativeEncoder intakeEncoder;

    XboxController controller = new XboxController(0);

    public Intake(){
        // Intake articulation 
        motorIntakeConfig = new SparkMaxConfig();
        motorIntakeConfig.idleMode(IdleMode.kBrake);
        motorIntakeConfig.inverted(true);
        motorIntakeConfig.encoder.positionConversionFactor(1);

        // Pid
        motorIntakeConfig.closedLoop.pid(IntakeConstants.Pid.KP, 
                                         IntakeConstants.Pid.KI, 
                                         IntakeConstants.Pid.KD); 

        intake.configure(motorIntakeConfig, null, PersistMode.kNoPersistParameters);

        // Intake Wheels
        motorIntakeWheelsConfig = new SparkMaxConfig();
        motorIntakeWheelsConfig.idleMode(IdleMode.kBrake);
        motorIntakeWheelsConfig.inverted(true);

        intakeWheels.configure(motorIntakeConfig, null, PersistMode.kNoPersistParameters);
        
        // Encoder
        intakeEncoder = intake.getEncoder();
    }

    public void setSetpoint(double setpoint){
        intake.getClosedLoopController()
              .setSetpoint(setpoint,
                     ControlType.kPosition);
    }

    public void setIntakeDown(){
        setSetpoint(IntakeConstants.Setpoints.POSITION_DOWN);
    }

    public void setIntakeUp(){
        setSetpoint(IntakeConstants.Setpoints.POSITION_UP);
    }

    public void setWheelsIntake(){
        intakeWheels.set(IntakeConstants.Speeds.SPEED_INTAKE);
    }

    public void setWheelsOn(){
        intakeWheels.set(IntakeConstants.Speeds.SPEED_OUTAKE);
    }

    public void setWheelsOff(){
        intakeWheels.stopMotor();
    }

    public void setPivotOff(){
        intake.set(0);
    }

    public void dashboard(){
        SmartDashboard.putNumber("Intake position", intakeEncoder.getPosition());
        SmartDashboard.putNumber("Setpoint", IntakeConstants.Setpoints.POSITION_DOWN);
    }
}