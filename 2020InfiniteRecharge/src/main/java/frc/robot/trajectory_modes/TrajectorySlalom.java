package frc.robot.trajectory_modes;

import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Chassis;

public class TrajectorySlalom {
    //reads slalom and gives autonomous
    private static final double MAX_VELOCITY = Units.inchesToMeters(150);
    private static final double MAX_ACCELERATION = Units.inchesToMeters(150);

    public static Command slalom(Chassis chassis) {
        TrajectoryConfig trajectoryConfig = TrajectoryModeFactory.getTrajectoryConfig(MAX_VELOCITY, MAX_ACCELERATION);
        //CentripetalAccelerationConstraint accelerationConstraint = new CentripetalAccelerationConstraint(MAX_CENT_ACCELERATION);

        return TrajectoryUtils.startTrajectory("pathweaver_athome/Slalom/PathWeaver/Paths/Slalom1", trajectoryConfig, chassis);
    }
}
