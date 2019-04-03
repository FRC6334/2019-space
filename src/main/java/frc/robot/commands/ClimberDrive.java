/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.RobotMap;

public class ClimberDrive extends CommandBase {

  Joystick rightStick;

  public ClimberDrive() {
    super("ClimberDrive");
    requires(climber);
    rightStick = OI.getRightDriveStick();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("climberdrive init");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (rightStick.getRawButton(4)) {
      climber.driveBothClimbAxleWheels(0.50);
    } else {
      climber.driveBothClimbAxleWheels(0);
    }

    if (rightStick.getRawButtonPressed(7)) {
      System.out.println("reset both climb encoders");
      climber.resetBothClimbEncoders();
    }

    if (rightStick.getRawButtonPressed(6)) {
      System.out.println("F: " + climber.getFrontSensorInches() + "\nB: " + climber.getBackSensorInches());
    }

    if (Math.abs(rightStick.getY()) <= 0.05) {
      climber.driveClimbBoth(0);
    } else {
      climber.driveClimbBoth(rightStick.getY());
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    System.out.println("climberdrive interrupt");
  }
}
