package frc.robot.subsystems.indexer;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Indexer extends SubsystemBase{

    private SparkMax indexer = new SparkMax(22, MotorType.kBrushless);

    private SparkMaxConfig indexerConfig;

    public Indexer(){
        indexerConfig = new SparkMaxConfig();
        indexerConfig.inverted(false);

        indexer.configure(indexerConfig, null, PersistMode.kNoPersistParameters);
    }

    public void setIndexerOn(){
        indexer.set(0.67);
    }

    public void setIndexerCounterClock(){
        indexer.set(-0.67);
    }   

    public void setIndexerOff(){
        indexer.stopMotor();
    }
}