package frc.robot.poseflipper;

import java.util.Optional;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;

public class PoseFlipper {

    private static final double FIELD_LENGTH_METERS = 17.54; // 16.54 talvez

    public static Pose2d centerScorePosition() {
        Pose2d bluePose = new Pose2d(1.9, 4, new Rotation2d());

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