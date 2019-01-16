/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  // Joystick port positions. Currently hardcoded to tank drive.
  public static final int rightDriveStick = 0; // The really fancy, silver joystick with a ton of buttons and z-axis capabilities
  public static final int leftDriveStick = 1;
  public static final int auxillaryStick = 2; // Programmer/aux driver stick

  // Drivetrain motors. Look at the bot when the battery is closest to you and you shall see :)
  public static final int leftMotor1 = 3;
  public static final int leftMotor2 = 4;
  public static final int rightMotor1 = 1; // temp, set to 3
  public static final int rightMotor2 = 2;
  public static final float speedLimiter = 0.33f; // Out of 1.0, this value represents the max speed that the robot will go. 0.8, for example, means 80% max motor speed

  public static class rightStick {
    public static final int cycleVisionMode = 6;
    public static final int cycleCamMode = 7;
  }

  public static class climber {
    public static final int frontCylinders1 = 0; // These represent the ports that you put plug the doublesolenoid into.
    public static final int frontCylinders2 = 1;
    public static final int backCylinders1 = 3;
    public static final int backCylinders2 = 4;
  }
}