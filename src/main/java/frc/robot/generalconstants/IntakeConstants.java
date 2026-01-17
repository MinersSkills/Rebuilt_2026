package frc.robot.generalconstants;

public final class IntakeConstants {
    public final class Pid {
        public static final double KP = 5.0;
        public static final double KI = 0.0;
        public static final double KD = 0.0;

        public static final int MAX_VELOCITY = 3;
        public static final int MAX_ACELERATION = 4;
    }

    public final class Setpoints{
        /*
         * Only provisory values 
        */
        public static final double POSITION_UP = 0.1;
        public static final double POSITION_DOWN = 50;
    }

    public final class Speeds{
        /*
         * Only provisory values 
        */
        public static final double SPEED_INTAKE = 1;
        public static final double SPEED_OUTAKE = -0.4;
    }
}