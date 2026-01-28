package frc.robot.subsystems.shooter;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase{
    private SparkMaxConfig motorShooterConfig;
    private SparkMaxConfig motorShooterConfigInvertaded;;

    private XboxController driverXbox = new XboxController(0);

    public static SparkMax shooter1 = new SparkMax(32, MotorType.kBrushless);
    public static SparkMax shooter2 = new SparkMax(50, MotorType.kBrushless);
    public static SparkMax shooter3 = new SparkMax(15, MotorType.kBrushless);

    public Shooter(){
        motorShooterConfig = new SparkMaxConfig();

        motorShooterConfig.idleMode(IdleMode.kCoast);
        motorShooterConfig.inverted(false);

        motorShooterConfigInvertaded = new SparkMaxConfig();

        motorShooterConfigInvertaded.idleMode(IdleMode.kCoast);
        motorShooterConfigInvertaded.inverted(true);

        shooter1.configure(motorShooterConfigInvertaded, null, PersistMode.kNoPersistParameters);
        shooter2.configure(motorShooterConfig, null, PersistMode.kNoPersistParameters);
        shooter3.configure(motorShooterConfig, null, PersistMode.kNoPersistParameters);
    }

    public void shootState(){
        double pov = driverXbox.getPOV();

        if (pov == 0){
            shooter2.set(0.9);
            shooter3.set(0.9);
        } else if (pov == 180){
            shooter1.set(0);
            shooter2.set(0);
            shooter3.set(0);
        } else if (pov == 90){
            shooter1.set(1);
        }

    }

    public void setSpeedShoot(double speed){
        shooter1.set(speed);
        shooter2.set(speed);
        shooter3.set(speed);
    }

    public void setShooterOff(){
        shooter1.set(0);
        shooter2.set(0);
        shooter3.set(0);
    }
}