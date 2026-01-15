package frc.robot.joystick;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class KeyboardController {
    private final NetworkTable table;

    public KeyboardController(){
        //Conect to the network table
        table = NetworkTableInstance.getDefault().getTable("OperatorController");
    }

    private Trigger createTrigger(String key){
        // function to read the key from table
        return new Trigger(() -> table.getEntry(key).getBoolean(false));
    }

    // this is how to create a trigger to be read in a robot container
    // Letrase
    public Trigger getA() { return createTrigger("a"); }
    public Trigger getB() { return createTrigger("b"); }
    public Trigger getC() { return createTrigger("c"); }
    public Trigger getD() { return createTrigger("d"); }
    public Trigger getE() { return createTrigger("e"); }
    public Trigger getF() { return createTrigger("f"); }
    public Trigger getG() { return createTrigger("g"); }
    public Trigger getH() { return createTrigger("h"); }
    public Trigger getI() { return createTrigger("i"); }
    public Trigger getJ() { return createTrigger("j"); }
    public Trigger getK() { return createTrigger("k"); }
    public Trigger getL() { return createTrigger("l"); }
    public Trigger getM() { return createTrigger("m"); }
    public Trigger getN() { return createTrigger("n"); }
    public Trigger getO() { return createTrigger("o"); }
    public Trigger getP() { return createTrigger("p"); }
    public Trigger getQ() { return createTrigger("q"); }
    public Trigger getR() { return createTrigger("r"); }
    public Trigger getS() { return createTrigger("s"); }
    public Trigger getT() { return createTrigger("t"); }
    public Trigger getU() { return createTrigger("u"); }
    public Trigger getV() { return createTrigger("v"); }
    public Trigger getW() { return createTrigger("w"); }
    public Trigger getX() { return createTrigger("x"); }
    public Trigger getY() { return createTrigger("y"); }
    public Trigger getZ() { return createTrigger("z"); }

    // Numeros
    public Trigger get0() { return createTrigger("0"); }
    public Trigger get1() { return createTrigger("1"); }
    public Trigger get2() { return createTrigger("2"); }
    public Trigger get3() { return createTrigger("3"); }
    public Trigger get4() { return createTrigger("4"); }
    public Trigger get5() { return createTrigger("5"); }
    public Trigger get6() { return createTrigger("6"); }
    public Trigger get7() { return createTrigger("7"); }
    public Trigger get8() { return createTrigger("8"); }
    public Trigger get9() { return createTrigger("9"); }

    // Especias
    public Trigger getSpace() { return createTrigger("space"); }
    public Trigger getEnter() { return createTrigger("enter"); }
    public Trigger getShift() { return createTrigger("shift"); }
    public Trigger getCtrl() { return createTrigger("ctrl"); }
    public Trigger getAlt() { return createTrigger("alt"); }
    public Trigger getEsc() { return createTrigger("esc"); }

    // Setas
    public Trigger getUp() { return createTrigger("up"); }
    public Trigger getDown() { return createTrigger("down"); }
    public Trigger getLeft() { return createTrigger("left"); }
    public Trigger getRight() { return createTrigger("right"); }

    // Especiais
    public Trigger getF1() { return createTrigger("f1"); }
    public Trigger getF2() { return createTrigger("f2"); }
    public Trigger getF3() { return createTrigger("f3"); }
    public Trigger getF4() { return createTrigger("f4"); }
    public Trigger getF5() { return createTrigger("f5"); }
    public Trigger getF6() { return createTrigger("f6"); }
    public Trigger getF7() { return createTrigger("f7"); }
    public Trigger getF8() { return createTrigger("f8"); }
    public Trigger getF9() { return createTrigger("f9"); }
    public Trigger getF10() { return createTrigger("f10"); }
    public Trigger getF11() { return createTrigger("f11"); }
    public Trigger getF12() { return createTrigger("f12"); }
}