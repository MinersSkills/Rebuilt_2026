package frc.robot.limelight;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LimelightPoseEstimator {

    private final String limelightName;

    private Pose2d poseMegaTag2 = new Pose2d();
    private double timestampSeconds = 0.0;
    private boolean doRejectUpdate = false;

    private Field2d field2d = new Field2d();

    public LimelightPoseEstimator(String limelightName){
        this.limelightName = limelightName;
    }

    public void updateEstimatePose(double yawDegrees, double yawRate){
        LimeLightHelpers.SetRobotOrientation(limelightName, yawDegrees, 0, 0, 0, 0, 0);
        LimeLightHelpers.PoseEstimate mt2 = LimeLightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2(limelightName);

        doRejectUpdate = false;
         
        // if our angular velocity is greater than 360 degrees per second, ignore vision updates
        if(mt2 == null || Math.abs(yawRate) > 360 || mt2.tagCount == 0)
        {
          doRejectUpdate = true;
          return;
        }
        if(mt2.tagCount == 0)
        {
          doRejectUpdate = true;
        }
        if(!doRejectUpdate)
        {
            poseMegaTag2 = mt2.pose;
            timestampSeconds = mt2.timestampSeconds;

            field2d.setRobotPose(mt2.pose);

            SmartDashboard.putData("Limelight pose", field2d);
        }
    }

    public Pose2d getEstimatedPose(){
        return poseMegaTag2;
    }

    public double getTimestampSecondsEstimatedPose(){
        return timestampSeconds;
    }

    public boolean isTheLastEstimatedPoseValid(){
        return !doRejectUpdate;
    }
}
