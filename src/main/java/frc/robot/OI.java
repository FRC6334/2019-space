/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  static Joystick rightDriveStick;
  static Joystick leftDriveStick;
  static Joystick auxStick;

  public static void init() {
    rightDriveStick = new Joystick(RobotMap.rightDriveStick);
    leftDriveStick = new Joystick(RobotMap.leftDriveStick);
    auxStick = new Joystick(RobotMap.auxillaryStick);

    Button b_moveArmToRocketHatch2 = new JoystickButton(leftDriveStick, 9);
    Button b_moveArmToRocketHatch3 = new JoystickButton(leftDriveStick, 8);
    Button b_navXClimb             = new JoystickButton(rightDriveStick, 3);
    Button b_driveFrontClimber     = new JoystickButton(rightDriveStick, 8);
    Button b_driveBackClimber      = new JoystickButton(rightDriveStick, 9);
    Button b_autoLimelight         = new JoystickButton(leftDriveStick, 4);

    b_navXClimb.whileHeld(new AutoNavXBalanceClimb());
    b_moveArmToRocketHatch3.whileHeld(new MoveToRocketHatch3());
    b_moveArmToRocketHatch2.whileHeld(new MoveToRocketHatch2());
    b_driveFrontClimber.toggleWhenPressed(new DriveFrontClimberX());
    b_driveBackClimber.toggleWhenPressed(new DriveBackClimberX());
    b_autoLimelight.whileHeld(new AutoDriveToTarget());
  }

  public static Joystick getRightDriveStick() { 
    return rightDriveStick; 
  }

  public static Joystick getLeftDriveStick() {
     return leftDriveStick; 
  }

  public static Joystick getAuxStick() {
     return auxStick; 
  }
}
