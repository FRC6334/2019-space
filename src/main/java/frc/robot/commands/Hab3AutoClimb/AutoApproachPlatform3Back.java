/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Hab3AutoClimb;

import frc.robot.commands.CommandBase;

public class AutoApproachPlatform3Back extends CommandBase {
  public AutoApproachPlatform3Back() {
    super("approachplatformusingback");
    // Use requires() here to declare subsystem dependencies
    requires(climber);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("Pushing onto the platform using back sensor");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double distance = climber.getBackSensorInches();
    if (distance > 1.1) {
      climber.driveBothClimbAxleWheels(0.35);
    } else {
      climber.driveBothClimbAxleWheels(0);
    }
    if (climber.getBackClimbEncoder() > -65) {
      climber.driveBackClimber(-0.20);
    } else {
      climber.driveBackClimber(0);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return climber.getBackSensorInches() < 1.1;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    climber.driveBothClimbAxleWheels(0);
    climber.driveBackClimber(0);
    System.out.println("The back axle is 1.1\" away from the back platform. ending.");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    climber.driveBothClimbAxleWheels(0);
    climber.driveBackClimber(0);
    System.out.println("The automatic approach to the back platform was interrupted.");
  }
}
