package frc.robot.generalconstants;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

public class FieldConstants {
    public static Pose2d HubCenter = new Pose2d(4.631, 4.069, new Rotation2d(4.631, 4.069));

    /*
     * Score positions
     */
    public static Pose2d ScorePositionRight = new Pose2d(2.8, 2.5, new Rotation2d(Units.degreesToRadians(43)));
    public static Pose2d ScorePositionLeft = new Pose2d(2.73, 5, new Rotation2d(Units.degreesToRadians(-26.78)));
    public static Pose2d ScorePositionCenter = new Pose2d(2.48, 4, new Rotation2d());

    /*
     * Pass positions
     */
    public static Pose2d LeftPassPosition = new Pose2d(6, 5.4, new Rotation2d(Units.degreesToRadians(180)));
    public static Pose2d RightPassPosition = new Pose2d(6, 2.8, new Rotation2d(Units.degreesToRadians(180)));

    public static Pose2d LeftPassPositionRed = new Pose2d(11, 5.4, new Rotation2d(Units.degreesToRadians(0)));
    public static Pose2d RightPassPositionRed = new Pose2d(11, 2.8, new Rotation2d(Units.degreesToRadians(0)));
}