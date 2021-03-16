package frc.robot.trajectory_modes;

import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.constraint.CentripetalAccelerationConstraint;
import edu.wpi.first.wpilibj.trajectory.constraint.EllipticalRegionConstraint;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Chassis;

public class TrajectoryBarrel {
    private static final double MAX_VELOCITY = Units.inchesToMeters(120);
    private static final double MAX_ACCELERATION = Units.inchesToMeters(120);
    private static final double X_WIDTH = 1.5;
    private static final double Y_WIDTH = 1.5;
    private static final double MAX_CENT_ACCELERATION = 0.1;


    public static Command barrel(Chassis chassis) {
        TrajectoryConfig trajectoryConfig = TrajectoryModeFactory.getTrajectoryConfig(MAX_VELOCITY, MAX_ACCELERATION);
        CentripetalAccelerationConstraint accelerationConstraint = new CentripetalAccelerationConstraint(MAX_CENT_ACCELERATION);
        EllipticalRegionConstraint ellipticalConstraint = new EllipticalRegionConstraint(
                new Translation2d(Units.feetToMeters(12.5), Units.feetToMeters(5.0)),
                X_WIDTH,
                Y_WIDTH,
                Rotation2d.fromDegrees(0.0),
                accelerationConstraint);
        trajectoryConfig.addConstraint(ellipticalConstraint);

        return TrajectoryUtils.startTrajectory("pathweaver_athome/Barrel/PathWeaver/Paths/barrel1", trajectoryConfig, chassis);
    }
}
