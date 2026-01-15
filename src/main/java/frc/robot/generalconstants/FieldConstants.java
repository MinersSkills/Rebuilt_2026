package frc.robot.generalconstants;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

public class FieldConstants {
    public static Pose2d HubCenter = new Pose2d(4.631, 4.069, new Rotation2d(4.631, 4.069));

    public static Pose2d ScorePositionRight = new Pose2d(2.48, 2, new Rotation2d(Units.degreesToRadians(43)));
    public static Pose2d ScorePositionLeft = new Pose2d(2.48, 6, new Rotation2d(Units.degreesToRadians(-43)));
    public static Pose2d ScorePositionCenter = new Pose2d(2.48, 4, new Rotation2d());

    public static final class IntakePositions {

        public static final Pose2d[] POSES_RIGHT = {
            new Pose2d(
                3.00,
                0.524,
                new Rotation2d(Units.degreesToRadians(90))
            ),
            new Pose2d(
                5.76,
                0.524,
                new Rotation2d(Units.degreesToRadians(90))
            ),
            new Pose2d(
                6.53,
                2.66,
                new Rotation2d(Units.degreesToRadians(150))
            ),
            
        };

        public static final Pose2d[] POSES_LEFT = {
            new Pose2d(
                3.00,
                7.48,
                new Rotation2d(Units.degreesToRadians(270))
            ),
            new Pose2d(
                5.76,
                7.48,
                new Rotation2d(Units.degreesToRadians(270))
            ),
            new Pose2d(
                6.53,
                5.91,
                new Rotation2d(Units.degreesToRadians(225))
            ),
            
        };
    }

        public static final class ShooterPositions {

        public static final Pose2d[] POSES_RIGHT = {
            new Pose2d(
                5.9,
                0.524,
                new Rotation2d(Units.degreesToRadians(90))
            ),
            new Pose2d(
                3.512,
                0.524,
                new Rotation2d(Units.degreesToRadians(90))
            ),
            new Pose2d(
                2.48, 
                2,
                new Rotation2d(Units.degreesToRadians(43))
            ),
            
        };

                public static final Pose2d[] POSES_LEFT = {
            new Pose2d(
                5.76,
                7.48,
                new Rotation2d(Units.degreesToRadians(270))
            ),
            new Pose2d(
                3.00,
                7.48,
                new Rotation2d(Units.degreesToRadians(270))
            ),
            new Pose2d(
                2.48,
                6, 
                new Rotation2d(Units.degreesToRadians(-43))
            ),
        };
    }
}

