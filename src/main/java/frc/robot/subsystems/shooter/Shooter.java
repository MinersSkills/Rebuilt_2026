package frc.robot.subsystems.shooter;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.generalconstants.ShooterConstants;

public class Shooter extends SubsystemBase{
    private SparkMaxConfig motorShooterConfig;
    private SparkMaxConfig motorShooterConfig2;

    public static SparkMax shooter = new SparkMax(22, MotorType.kBrushless);
    public static SparkMax shooter2 = new SparkMax(50, MotorType.kBrushless);

    public Shooter(){
        motorShooterConfig = new SparkMaxConfig();

        motorShooterConfig.idleMode(IdleMode.kCoast);
        motorShooterConfig.inverted(false);
        shooter.configure(motorShooterConfig, null, PersistMode.kNoPersistParameters);

        motorShooterConfig2 = new SparkMaxConfig();

        motorShooterConfig2.idleMode(IdleMode.kCoast);
        motorShooterConfig2.inverted(true);
        shooter2.configure(motorShooterConfig2, null, PersistMode.kNoPersistParameters);
    }

    public void setSpeedScore(){
        shooter.set(ShooterConstants.Speeds.SHOOT_SPEED);
    }

    public void setShooterOff(){
        shooter.stopMotor();
    }
}