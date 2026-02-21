package frc.robot.subsystems.shooter;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase{
    private SparkMaxConfig motorShooterConfig;
    private SparkMaxConfig motorShooterConfigInvertaded;


    public static SparkMax shooter1 = new SparkMax(4, MotorType.kBrushless);
    public static SparkMax shooter2 = new SparkMax(15, MotorType.kBrushless);
    public static SparkMax shooterStars = new SparkMax(6, MotorType.kBrushless);

    public Shooter(){
        motorShooterConfig = new SparkMaxConfig();

        motorShooterConfig.idleMode(IdleMode.kCoast);
        motorShooterConfig.inverted(false);

        motorShooterConfigInvertaded = new SparkMaxConfig();

        motorShooterConfigInvertaded.idleMode(IdleMode.kCoast);
        motorShooterConfigInvertaded.inverted(true);

        shooter1.configure(motorShooterConfig, null, PersistMode.kNoPersistParameters);
        shooter2.configure(motorShooterConfig, null, PersistMode.kNoPersistParameters);
        shooterStars.configure(motorShooterConfig, null, PersistMode.kNoPersistParameters);
    }

    public void setShooterOff(){
        shooter1.set(0);
        shooter2.set(0);
        shooterStars.set(0);
    }

    public void setSpeedShootFront(){
        shooter1.set(0.8);
        shooter2.set(0.8);
        
    }

    public void setSpeedShootStars(){
        shooterStars.set(1);
    }
}