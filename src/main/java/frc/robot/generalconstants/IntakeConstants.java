package frc.robot.generalconstants;

public final class IntakeConstants {
    public final class Pid {
        public static final double KP = 0.08;
        public static final double KI = 0.0;
        public static final double KD = 0.01;

        public static final int MAX_VELOCITY = 3;
        public static final int MAX_ACELERATION = 4;
    }

    public final class Setpoints{
        public static final double POSITION_UP = 0.5;
        public static final double POSITION_DOWN = 8.8;
        public static final double POSITION_MIDLE = 4;
    }

    public final class Speeds{
        public static final double SPEED_INTAKE = -0.4;
        public static final double SPEED_OUTAKE = 0.425;
    }
}   