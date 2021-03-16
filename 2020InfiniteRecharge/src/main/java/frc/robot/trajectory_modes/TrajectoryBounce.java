package frc.robot.trajectory_modes;

import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.constraint.CentripetalAccelerationConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Chassis;

public class TrajectoryBounce {
    public static Command bounce(Chassis chassis) {
        TrajectoryConfig trajectoryConfig = TrajectoryModeFactory.getTrajectoryConfig();
        CentripetalAccelerationConstraint accelerationConstraint = new CentripetalAccelerationConstraint(0.1);
        trajectoryConfig.addConstraint(accelerationConstraint);

        Command first = TrajectoryUtils.startTrajectory("pathweaver_athome/BouncePath/PathWeaver/Paths/Bounce1", trajectoryConfig, chassis);
        Command second = TrajectoryUtils.createTrajectory("pathweaver_athome/BouncePath/PathWeaver/Paths/Bounce2", trajectoryConfig, chassis);
        Command third = TrajectoryUtils.createTrajectory("pathweaver_athome/BouncePath/PathWeaver/Paths/Bounce3", trajectoryConfig, chassis);
        Command fourth = TrajectoryUtils.createTrajectory("pathweaver_athome/BouncePath/PathWeaver/Paths/Bounce4", trajectoryConfig, chassis);
        SequentialCommandGroup combinedPath = new SequentialCommandGroup();
        combinedPath.addCommands(first, second, third, fourth);
        return combinedPath;
    }
}
