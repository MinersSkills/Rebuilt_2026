package frc.robot.poseflipper;

import java.util.Optional;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import frc.robot.generalconstants.FieldConstants;

public class PoseFlipper {

    private static final double FIELD_LENGTH_METERS = 16.54;

    /*
     * INDEPENDET SCORE POSITIONS
    */

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

    public static Pose2d blueToRed(Pose2d bluePose) {
        double xRed = FIELD_LENGTH_METERS - bluePose.getX();
        double yRed = bluePose.getY();

        double thetaRed = Math.PI - bluePose.getRotation().getRadians();
        thetaRed = MathUtil.angleModulus(thetaRed);

        return new Pose2d(xRed, yRed, new Rotation2d(thetaRed));
    }
}