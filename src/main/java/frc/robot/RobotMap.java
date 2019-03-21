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
  public static final int rightDriveStick = 0; // The really fancy, silver joystick with a ton of buttons and z-axis
                                               // capabilities
  public static final int leftDriveStick = 1;
  public static final int auxillaryStick = 2; // Programmer/aux driver stick

  // Drivetrain motors. Look at the bot when the battery is closest to you and you
  // shall see :)
  public static final int leftFrontMotor = 3;
  public static final int leftBackMotor = 4;
  public static final int rightFrontMotor = 1;
  public static final int rightBackMotor = 2;
  public static final float speedLimiter = 0.4f; // Out of 1.0, this value represents the max speed that the robot will
                                                  // go. 0.8, for example, means 80% max motor speed

  public static class rightStick {
    public static final int cycleVisionMode = 11;
    public static final int cycleCamMode = 10;
  }

  public static class encoderMath {
    public static final double hatchTwoLow = -7.65; // Second hatch on rocket position using the arm
    public static final double hatchTwoHigh = -8; // You know the rest
    public static final double hatchThreeLow = -14.80;
    public static final double hatchThreeHigh = -15.09;
  }

  public static class arm {
    public static final int armTalonId = 7;
  }

  public static class climber {
    public static final int rightFrontExtend = 0;
    public static final int rightFrontReverse = 1;
    public static final int rightBackExtend = 2;
    public static final int rightBackReverse = 3;
    public static final int leftFrontExtend = 4;
    public static final int leftFrontReverse = 5;
    public static final int leftBackExtend = 6;
    public static final int leftBackReverse = 7;
    public static final double speedLimiter = 0.50; // Multiply by 100 | maximum throttle
    // Buttons
    public static final int normalDriveButton = 3;
    public static final int turboDriveButton = 1;
  }

  public static class pcm {
    public static final int mainPcm = 1;
    public static final int auxPcm = 3; // Phoenix Tuner set the aux pcm id to 3
  }

  public static final int compressor = pcm.mainPcm; // Compressor takes the main pcm id as an arg
}