package frc.robot.subsystems.climber;

import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.generalconstants.ClimberConstants;

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

        // PID
        motorClimberConfig.closedLoop.pid(ClimberConstants.Pid.kP, 
                                          ClimberConstants.Pid.kI,  
                                          ClimberConstants.Pid.kD);
        climber.configure(motorClimberConfig, null, PersistMode.kNoPersistParameters);

        climber.configure(motorClimberConfig, null, PersistMode.kNoPersistParameters);

        // Encoder
        encoder = climber.getEncoder();
    }

    public void setSetpoint(double setpoint){
        climber.getClosedLoopController().setSetpoint(
                                                            setpoint,
                                                            ControlType.kPosition
                                                      );
    }

    public void SetClimber() { 
        if (driver.getRightBumper()){
            setClimberDown();
        } else if (driver.getRightTriggerAxis() > 0.5){
            setClimberUp();
        } else {
            climber.set(0);
        }
    };

    public void setClimberUp(){
        setSetpoint(ClimberConstants.Setpoints.SETPOINT_UP);
    }

    public void setClimberDown(){
        setSetpoint(ClimberConstants.Setpoints.SETPOINT_DOWN);
    }

    public void setClimberOff(){
        climber.set(0);
    }

    public void dashboard(){
        SmartDashboard.putNumber("Encoder Climber", encoder.getPosition());
        SmartDashboard.putNumber("Climber setpoint", ClimberConstants.Setpoints.SETPOINT_UP);
    }
}
