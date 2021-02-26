package frc.robot.subsystems;


import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimelightSubsystem extends SubsystemBase {
    private final NetworkTableEntry m_tx;
    private final NetworkTableEntry m_ty;
    private final NetworkTableEntry m_ta;
    private final NetworkTableEntry m_tv;
    public LimelightSubsystem() {
        m_tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx");
        m_ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta");
        m_ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty");
        m_tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv");
    }
public double limelightAngle () {
      return 0;

}



}



