package frc.robot.generalconstants;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

public class FieldConstants {
    public static Pose2d HubCenter = new Pose2d(4.631, 4.069, new Rotation2d(4.631, 4.069));

    public static Pose2d ScorePositionRight = new Pose2d(2.48, 2, new Rotation2d(Units.degreesToRadians(43)));
    public static Pose2d ScorePositionLeft = new Pose2d(2.48, 6, new Rotation2d(Units.degreesToRadians(-43)));
    public static Pose2d ScorePositionCenter = new Pose2d(2.48, 4, new Rotation2d());

}