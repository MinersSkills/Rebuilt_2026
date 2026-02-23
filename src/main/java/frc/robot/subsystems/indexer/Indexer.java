package frc.robot.subsystems.indexer;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Indexer extends SubsystemBase{

    private VictorSPX indexer;

    public Indexer(){
        indexer = new VictorSPX(6);

        indexer.setInverted(false);
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