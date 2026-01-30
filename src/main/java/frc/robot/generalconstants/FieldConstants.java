package frc.robot.generalconstants;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

public class FieldConstants {
    public static Pose2d HubCenter = new Pose2d(4.631, 4.069, new Rotation2d(4.631, 4.069));

    public static Pose2d ScorePositionRight = new Pose2d(2.703, 2.127, new Rotation2d(Units.degreesToRadians(44.01)));
    public static Pose2d ScorePositionLeft = new Pose2d(2.767, 5.558, new Rotation2d(Units.degreesToRadians(-38.12)));
    public static Pose2d ScorePositionCenter = new Pose2d(2.48, 4, new Rotation2d());

}