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
import frc.robot.commands.Hab3AutoClimb.AutoToHab3;

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

    Button moveArmToPos = new JoystickButton(leftDriveStick, 9);
    Button moveArmToHighestRocket = new JoystickButton(leftDriveStick, 8);
    Button navXClimb             = new JoystickButton(rightDriveStick, 3);
    Button driveFrontClimber     = new JoystickButton(rightDriveStick, 8);
    Button driveBackClimber      = new JoystickButton(rightDriveStick, 9);
    Button autoLimelight         = new JoystickButton(leftDriveStick, 4);
    //Button autoNavxClimb         = new JoystickButton(rightDriveStick, 2);

    navXClimb.whileHeld(new AutoNavXBalanceClimb());
    moveArmToHighestRocket.whileHeld(new MoveToHighRocket());
    moveArmToPos.whileHeld(new MoveTo47());
    driveFrontClimber.toggleWhenPressed(new DriveFrontClimberX());
    driveBackClimber.toggleWhenPressed(new DriveBackClimberX());
    autoLimelight.whileHeld(new AutoDriveToTarget());
    //autoNavxClimb.toggleWhenPressed(new AutoToHab3());
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
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
