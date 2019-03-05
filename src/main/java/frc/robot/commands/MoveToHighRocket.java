/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.RobotMap;

public class MoveToHighRocket extends CommandBase {

  boolean upThere = false;

  public MoveToHighRocket() {
    super("MoveToHighRocket");
    requires(climber);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("Move to high rocket started");
    climber.setArmPos(-15);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println("high rocket pid exec");
  }

  public boolean isFinished() { // Do not worry about this method. It will be interrupted when the button is released (2)
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
      climber.driveArm(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    System.out.println("Move to high rocket interrupted");
  }
}
