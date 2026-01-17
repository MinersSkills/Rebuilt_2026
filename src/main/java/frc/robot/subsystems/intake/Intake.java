package frc.robot.subsystems.intake;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.generalconstants.IntakeConstants;

public class Intake extends SubsystemBase {
    private SparkMaxConfig motorIntakeConfig;
    private SparkMaxConfig motorIntakeWheelsConfig;

    public static SparkMax intake = new SparkMax(2, MotorType.kBrushless);
    public static SparkMax intakeWheels = new SparkMax(3, MotorType.kBrushless);

    public RelativeEncoder intakeEncoder;

    private final ProfiledPIDController intakePID;

    public Intake(){
        // Intake articulation 
        motorIntakeConfig = new SparkMaxConfig();
        motorIntakeConfig.idleMode(IdleMode.kBrake);
        motorIntakeConfig.inverted(false);
        motorIntakeConfig.encoder.positionConversionFactor(1);

        intake.configure(motorIntakeConfig, null, PersistMode.kNoPersistParameters);

        // Intake Wheels
        motorIntakeWheelsConfig = new SparkMaxConfig();
        motorIntakeWheelsConfig.idleMode(IdleMode.kBrake);
        motorIntakeWheelsConfig.inverted(false);

        intakeWheels.configure(motorIntakeConfig, null, PersistMode.kNoPersistParameters);

        // Encoder
        intakeEncoder = intake.getEncoder();

        // PID Initializing
        intakePID = new ProfiledPIDController(
            IntakeConstants.Pid.KP, 
            IntakeConstants.Pid.KI, 
            IntakeConstants.Pid.KD, 
            new Constraints(
                IntakeConstants.Pid.MAX_VELOCITY, 
                IntakeConstants.Pid.MAX_ACELERATION)
        );
    }


}
