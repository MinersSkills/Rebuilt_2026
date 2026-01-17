package frc.robot.subsystems.shooter;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.generalconstants.ShooterConstants;

public class Shooter extends SubsystemBase{
    private SparkMaxConfig motorShooterConfig;

    private XboxController driverXbox = new XboxController(0);

    public static SparkMax shooter = new SparkMax(1, MotorType.kBrushless);

    public Shooter(){
        motorShooterConfig = new SparkMaxConfig();

        motorShooterConfig.idleMode(IdleMode.kBrake);
        motorShooterConfig.inverted(false);
    }

    public void shootState(){
        if (driverXbox.getYButton() ==  true){
            shooter.set(1);
        } else {
            shooter.set(0);
        }
    }

    public void setSpeedScore(){
        shooter.set(ShooterConstants.Speeds.SHOOT_SPEED);
    }

    public void setShooterOff(){
        shooter.stopMotor();
    }
}