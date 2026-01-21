package frc.robot.limelight;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.DriverStation;
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

        int[] idsRedAlliance = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        int[] idsBlueAlliance = {17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32};

        var alliance = DriverStation.getAlliance();

        if (alliance.isPresent() && alliance.get() == DriverStation.Alliance.Red) {
            LimeLightHelpers.SetFiducialIDFiltersOverride("limelight-two", idsRedAlliance);
        }else{
            LimeLightHelpers.SetFiducialIDFiltersOverride("limelight-two", idsBlueAlliance);
        }
         
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
