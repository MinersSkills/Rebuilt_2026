package frc.robot.subsystems.climber;

import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase{
    private SparkMaxConfig motorClimberConfig;

    XboxController driver = new XboxController(0);

    public static SparkMax climber = new SparkMax(7, MotorType.kBrushless);

    public RelativeEncoder encoder;

    public Climber(){
        // Climber motor
        motorClimberConfig = new SparkMaxConfig();
        motorClimberConfig.idleMode(IdleMode.kBrake);
        motorClimberConfig.inverted(true);
        motorClimberConfig.encoder.positionConversionFactor(1);

        climber.configure(motorClimberConfig, null, PersistMode.kNoPersistParameters);

        // Encoder
        encoder = climber.getEncoder();
    }


    public void SetClimber() { 
        if (driver.getRightBumper()){
            climber.set(-1);
            SmartDashboard.putNumber("Posição climber Down", encoder.getPosition());

        } else if (driver.getRightTriggerAxis() > 0.5){
            climber.set(1);
        SmartDashboard.putNumber("Posição climber", encoder.getPosition());
        } else {
            climber.set(0);
        }
    };

    public void climberUp(){
        climber.set(1);
    }

    public void climberDown(){
        climber.set(-1);
    }

    public void climberStop(){
        climber.stopMotor();
    }
}