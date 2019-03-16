/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Hab3AutoClimb;

import frc.robot.commands.CommandBase;

public class AutoRetractFrontAxle extends CommandBase {
  public AutoRetractFrontAxle() {
    super("retractthebackaxle");
    // Use requires() here to declare subsystem dependencies
    requires(climber);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("I am retracting the front axle");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    climber.driveFrontClimber(0.15); // Retract front axle
    if (climber.getBackClimbEncoder() > -65) {
      climber.driveBackClimber(-0.20); // Correct itself
    } else {
      climber.driveBackClimber(0); // don't keep on correcting...
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return Math.ceil(climber.getFrontClimbEncoder()) == 0;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    climber.driveClimbBoth(0);
    System.out.println("finished retracting the front axle");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    climber.driveClimbBoth(0);
    System.out.println("retracting the front axle was interrupted.");
  }
}
