package frc.robot.subsystems.climber;

import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase{
    private SparkMaxConfig motorClimberConfig;

    public static SparkMax climber = new SparkMax(1, MotorType.kBrushless);

    public RelativeEncoder encoder;

    public Climber(){
        // Climber motor
        motorClimberConfig = new SparkMaxConfig();

        motorClimberConfig.idleMode(IdleMode.kBrake);
        motorClimberConfig.inverted(false);
        motorClimberConfig.encoder.positionConversionFactor(1);

        climber.configure(motorClimberConfig, null, PersistMode.kNoPersistParameters);

        // Encoder
        encoder = climber.getEncoder();
    }
}
