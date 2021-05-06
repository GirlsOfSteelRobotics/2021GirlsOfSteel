// RobotBuilder Version: 3.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package frc.robot.subsystems;


import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SimableCANSparkMax;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.sim.CameraSimulator;
import org.snobotv2.coordinate_gui.RobotPositionPublisher;
import org.snobotv2.module_wrappers.rev.RevEncoderSimWrapper;
import org.snobotv2.module_wrappers.rev.RevMotorControllerSimWrapper;
import org.snobotv2.module_wrappers.wpi.ADXRS450GyroWrapper;
import org.snobotv2.sim_wrappers.DifferentialDrivetrainSimWrapper;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Chassis extends SubsystemBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SimableCANSparkMax m_rightFront;
    private final SimableCANSparkMax m_rightBack;
    private final SimableCANSparkMax m_leftFront;
    private final SimableCANSparkMax m_leftBack;
    private final MecanumDrive m_mecanumDrive;

    private final CANEncoder m_rightEncoder;
    private final CANEncoder m_leftEncoder;

    private final ADXRS450_Gyro m_gyroscope;
    private DifferentialDrivetrainSimWrapper m_simulator;
    DifferentialDriveOdometry m_odometry = new DifferentialDriveOdometry(new Rotation2d());
    private final Field2d m_field;
    private CameraSimulator m_cameraSimulator;
    private final RobotPositionPublisher m_coordinateGuiPublisher;

    private final PIDController m_pidController;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    /**
     *
     */
    public Chassis() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        m_rightFront = new SimableCANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_rightFront.setInverted(false);

        m_rightBack = new SimableCANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_rightBack.setInverted(false);

        m_leftFront = new SimableCANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_leftFront.setInverted(false);

        m_leftBack = new SimableCANSparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushless);
        m_leftBack.setInverted(false);

        m_mecanumDrive = new MecanumDrive(m_leftFront, m_leftBack,
                m_rightFront, m_rightBack);
        addChild("Mecanum Drive", m_mecanumDrive);
        m_mecanumDrive.setSafetyEnabled(true);
        m_mecanumDrive.setExpiration(0.1);
        m_mecanumDrive.setMaxOutput(1.0);

        m_rightEncoder = m_rightFront.getEncoder();
        m_leftEncoder = m_leftFront.getEncoder();

        m_gyroscope = new ADXRS450_Gyro();

        if (RobotBase.isSimulation()) {
            DifferentialDrivetrainSim drivetrainSim = DifferentialDrivetrainSim.createKitbotSim(DifferentialDrivetrainSim.KitbotMotor.kDualCIMPerSide, DifferentialDrivetrainSim.KitbotGearing.k5p95, DifferentialDrivetrainSim.KitbotWheelSize.SixInch, null);

            m_simulator = new DifferentialDrivetrainSimWrapper(
                    drivetrainSim,
                    new RevMotorControllerSimWrapper(m_leftFront),
                    new RevMotorControllerSimWrapper(m_rightFront),
                    RevEncoderSimWrapper.create(m_leftFront),
                    RevEncoderSimWrapper.create(m_rightFront),
                    new ADXRS450GyroWrapper(m_gyroscope));
            m_cameraSimulator = new CameraSimulator();
        }

        m_field = new Field2d();
        SmartDashboard.putData(m_field);

        m_coordinateGuiPublisher = new RobotPositionPublisher();

        m_pidController = new PIDController(0, 0, 0);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }


    @Override
    public void periodic() {
        var gyroAngle = Rotation2d.fromDegrees(-m_gyroscope.getAngle());

        // Update the pose
        m_odometry.update(gyroAngle, m_leftEncoder.getPosition(), m_rightEncoder.getPosition());
        m_field.setRobotPose(m_odometry.getPoseMeters());

        m_coordinateGuiPublisher.publish(m_odometry.getPoseMeters());
    }


    @Override
    public void simulationPeriodic() {
        m_simulator.update();
        m_cameraSimulator.update(m_odometry.getPoseMeters());
    }

    public void driveByJoystick(double joystickSpeed, double joystickSteer) {
        m_mecanumDrive.driveCartesian(0, joystickSpeed, joystickSteer);
    }

}

