/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Hab3AutoClimb;

import frc.robot.commands.AutoNavXBalanceClimb;

public class AutoApproachPlatform3Front extends AutoNavXBalanceClimb {
  public AutoApproachPlatform3Front() {
    // Use requires() here to declare subsystem dependencies
    requires(climber);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("Approaching the platform while auto balancing");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double distance = climber.getFrontSensorInches();
    System.out.println("F: " + distance);
    if (distance > 1.1) {
      climber.driveBothClimbAxleWheels(0.15);
    }
    this.balance(); // maintain balance while approaching
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return climber.getFrontSensorInches() < 1.1;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    climber.driveBothClimbAxleWheels(0);
    System.out.println("The front axle is in optimal position, ended.");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    climber.driveBothClimbAxleWheels(0);
    System.out.println("The front axle approach to the hab has been interrupted.");
  }
}
