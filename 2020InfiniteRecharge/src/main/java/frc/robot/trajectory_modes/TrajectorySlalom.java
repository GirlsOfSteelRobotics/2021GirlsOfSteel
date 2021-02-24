package frc.robot.trajectory_modes;

import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.autonomous.FollowTrajectory;
import frc.robot.subsystems.Chassis;

public class TrajectorySlalom {
    //reads slalom and gives autonomous
    public static Command slalom(Chassis chassis) {
        Trajectory slalomTrajectory = TrajectoryUtils.loadingTrajectory("pathweaver_athome/Slalom/PathWeaver/Paths/Slalom1", TrajectoryModeFactory.getTrajectoryConfig());
        return new FollowTrajectory(slalomTrajectory, chassis);
    }
}
