package frc.robot.subsystems.indexer;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Indexer extends SubsystemBase{

    private VictorSPX indexer;

    private XboxController driver = new XboxController(0);

    public Indexer(){
        indexer = new VictorSPX(6);

        indexer.setInverted(false);
    }

    public void setIndexerTest(){
        if (driver.getYButton()){
            indexer.set(ControlMode.PercentOutput, 0.5);
        } else{
            indexer.set(ControlMode.PercentOutput, 0);
        }
    }

    public void setIndexerOn(){
        indexer.set(ControlMode.PercentOutput, 1);
    }

    public void setIndexerCounterClock(){
        indexer.set(ControlMode.PercentOutput, -1);
    }

    public void setIndexerOff(){
        indexer.set(ControlMode.PercentOutput, 0);
    }
}