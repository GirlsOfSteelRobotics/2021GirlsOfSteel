/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public Joystick drivingPad;

	private JoystickButton backUp;
	private JoystickButton backDown;

	private JoystickButton frontUp;
	private JoystickButton frontDown;

	private JoystickButton allUp;
	private JoystickButton allDown;

	private JoystickButton collect;
	private JoystickButton release;

	private JoystickButton hatchCollect;
	private JoystickButton hatchRelease;

	private JoystickButton babyDriveForward; 
	private JoystickButton babyDriveBackward;

	public OI() {
		drivingPad = new Joystick(0);

		// Climber Buttons
		frontUp = new JoystickButton(drivingPad, 1);
		frontUp.whenPressed(new FrontUp());
		
		frontDown = new JoystickButton(drivingPad, 2);
		frontDown.whenPressed(new FrontDown());
		
		backUp = new JoystickButton(drivingPad, 3);
		backUp.whenPressed(new BackUp());
		
		backDown = new JoystickButton(drivingPad, 4);
		backDown.whenPressed(new BackDown());
		
		allUp = new JoystickButton(drivingPad, 5);
		allUp.whenPressed(new AllUp());
		
		allDown = new JoystickButton(drivingPad, 6);
		allDown.whenPressed(new AllDown());

		
		// Collector buttons
		collect = new JoystickButton(drivingPad, 7);
		collect.whileHeld(new Collect());

		release = new JoystickButton(drivingPad, 8);
		release.whileHeld(new Release());

		// Hatch buttons
		hatchCollect = new JoystickButton(drivingPad, 9);
		hatchCollect.whenPressed(new HatchCollect());

		hatchRelease = new JoystickButton(drivingPad, 10);
		hatchRelease.whenPressed(new HatchRelease());

		// BabyDrive buttons
		babyDriveForward = new JoystickButton(drivingPad, 11);
		babyDriveForward.whileHeld(new BabyDriveForward());

		babyDriveBackward = new JoystickButton(drivingPad, 12);
		babyDriveBackward.whileHeld(new BabyDriveBackwards());
  	}


	public double getLeftUpAndDown() {
		return -drivingPad.getY();
	}	

	public double getRightSideToSide() {
		return -drivingPad.getTwist();
	}
	
}




