package frc.robot.subsystems.indexer;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Indexer extends SubsystemBase{

    private VictorSPX indexer;

    private XboxController driver = new XboxController(1);

    public Indexer(){
        indexer = new VictorSPX(20);
        indexer.setInverted(false);
    }

    public void setIndexerTest(){
        if (driver.getAButtonPressed()){
            setIndexerOn();
        } else if(driver.getXButtonPressed()){
            setIndexerOff();
        }
    }

    public void setIndexerOn(){
        indexer.set(ControlMode.PercentOutput, 1);
    }

    public void setIndexerOff(){
        indexer.set(ControlMode.PercentOutput, 0);
    }
}