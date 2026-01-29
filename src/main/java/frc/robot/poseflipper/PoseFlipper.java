package frc.robot.poseflipper;

import java.util.Optional;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import frc.robot.generalconstants.FieldConstants;

public class PoseFlipper {

    private static final double FIELD_LENGTH_METERS = 17.54; // 16.54 talvez

    /*
     * INDEPENDET SCORE POSITIONS
    */

    public static Pose2d hubCenterPosition() {
        Pose2d bluePose = FieldConstants.HubCenter;

        Optional<Alliance> alliance = DriverStation.getAlliance();

        if (alliance.isPresent() && alliance.get() == Alliance.Red) {
            return blueToRed(bluePose);
        }

        return bluePose;
    }

    public static Pose2d scorePositionCenter() {
        Pose2d bluePose = FieldConstants.ScorePositionCenter;

        Optional<Alliance> alliance = DriverStation.getAlliance();

        if (alliance.isPresent() && alliance.get() == Alliance.Red) {
            return blueToRed(bluePose);
        }

        return bluePose;
    }

        public static Pose2d scorePositionRight() {
        Pose2d bluePose = FieldConstants.ScorePositionRight;

        Optional<Alliance> alliance = DriverStation.getAlliance();

        if (alliance.isPresent() && alliance.get() == Alliance.Red) {
            return blueToRed(bluePose);
        }

        return bluePose;
    }

        public static Pose2d scorePositionLeft() {
        Pose2d bluePose = FieldConstants.ScorePositionLeft;

        Optional<Alliance> alliance = DriverStation.getAlliance();

        if (alliance.isPresent() && alliance.get() == Alliance.Red) {
            return blueToRed(bluePose);
        }

        return bluePose;
    }

        /*
         * INTAKE POSITIONS
        */

    public static Pose2d intakePositions_POSES_RIGHT_0() {
        Pose2d bluePose = FieldConstants.IntakePositions.POSES_RIGHT[0];

        Optional<Alliance> alliance = DriverStation.getAlliance();

        if (alliance.isPresent() && alliance.get() == Alliance.Red) {
            return blueToRed(bluePose);
        }

        return bluePose;
    }

    public static Pose2d intakePositions_POSES_RIGHT_1() {
        Pose2d bluePose = FieldConstants.IntakePositions.POSES_RIGHT[1];

        Optional<Alliance> alliance = DriverStation.getAlliance();

        if (alliance.isPresent() && alliance.get() == Alliance.Red) {
            return blueToRed(bluePose);
        }

        return bluePose;
    }
    
    public static Pose2d intakePositions_POSES_RIGHT_2() {
        Pose2d bluePose = FieldConstants.IntakePositions.POSES_RIGHT[2];

        Optional<Alliance> alliance = DriverStation.getAlliance();

        if (alliance.isPresent() && alliance.get() == Alliance.Red) {
            return blueToRed(bluePose);
        }

        return bluePose;
    }

    public static Pose2d intakePositions_POSES_LEFT_0() {
        Pose2d bluePose = FieldConstants.IntakePositions.POSES_LEFT[0];

        Optional<Alliance> alliance = DriverStation.getAlliance();

        if (alliance.isPresent() && alliance.get() == Alliance.Red) {
            return blueToRed(bluePose);
        }

        return bluePose;
    }

    public static Pose2d intakePositions_POSES_LEFT_1() {
        Pose2d bluePose = FieldConstants.IntakePositions.POSES_LEFT[1];

        Optional<Alliance> alliance = DriverStation.getAlliance();

        if (alliance.isPresent() && alliance.get() == Alliance.Red) {
            return blueToRed(bluePose);
        }

        return bluePose;
    }
    
    public static Pose2d intakePositions_POSES_LEFT_2() {
        Pose2d bluePose = FieldConstants.IntakePositions.POSES_LEFT[2];

        Optional<Alliance> alliance = DriverStation.getAlliance();

        if (alliance.isPresent() && alliance.get() == Alliance.Red) {
            return blueToRed(bluePose);
        }

        return bluePose;
    }

    /*
     * SHOOTER POSITIONS
    */

    public static Pose2d shooterPositions_POSES_RIGHT_0() {
        Pose2d bluePose = FieldConstants.ShooterPositions.POSES_RIGHT[0];

        Optional<Alliance> alliance = DriverStation.getAlliance();

        if (alliance.isPresent() && alliance.get() == Alliance.Red) {
            return blueToRed(bluePose);
        }

        return bluePose;
    }

    public static Pose2d shooterPositions_POSES_RIGHT_1() {
        Pose2d bluePose = FieldConstants.ShooterPositions.POSES_RIGHT[1];

        Optional<Alliance> alliance = DriverStation.getAlliance();

        if (alliance.isPresent() && alliance.get() == Alliance.Red) {
            return blueToRed(bluePose);
        }

        return bluePose;
    }

    public static Pose2d shooterPositions_POSES_RIGHT_2() {
        Pose2d bluePose = FieldConstants.ShooterPositions.POSES_RIGHT[2];

        Optional<Alliance> alliance = DriverStation.getAlliance();

        if (alliance.isPresent() && alliance.get() == Alliance.Red) {
            return blueToRed(bluePose);
        }

        return bluePose;
    }

    public static Pose2d shooterPositions_POSES_LEFT_0() {
        Pose2d bluePose = FieldConstants.ShooterPositions.POSES_LEFT[0];

        Optional<Alliance> alliance = DriverStation.getAlliance();

        if (alliance.isPresent() && alliance.get() == Alliance.Red) {
            return blueToRed(bluePose);
        }

        return bluePose;
    }

    public static Pose2d shooterPositions_POSES_LEFT_1() {
        Pose2d bluePose = FieldConstants.ShooterPositions.POSES_LEFT[1];

        Optional<Alliance> alliance = DriverStation.getAlliance();

        if (alliance.isPresent() && alliance.get() == Alliance.Red) {
            return blueToRed(bluePose);
        }

        return bluePose;
    }

    public static Pose2d shooterPositions_POSES_LEFT_2() {
        Pose2d bluePose = FieldConstants.ShooterPositions.POSES_LEFT[2];

        Optional<Alliance> alliance = DriverStation.getAlliance();

        if (alliance.isPresent() && alliance.get() == Alliance.Red) {
            return blueToRed(bluePose);
        }

        return bluePose;
    }

    public static Pose2d blueToRed(Pose2d bluePose) {
        double xRed = FIELD_LENGTH_METERS - bluePose.getX();
        double yRed = bluePose.getY();

        double thetaRed = Math.PI - bluePose.getRotation().getRadians();
        thetaRed = MathUtil.angleModulus(thetaRed);

        return new Pose2d(xRed, yRed, new Rotation2d(thetaRed));
    }
}