/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

public class MovePivotUp extends CommandBase {
  public MovePivotUp() {
    super("MovePivotUp");
    // Use requires() here to declare subsystem dependencies
    requires(pivot);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("Drive arm up init");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println("Pivot arm up...");
    pivot.driveArm(1);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("stop driving arm up");
    pivot.driveArm(0); // peaceful stop
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    pivot.driveArm(0);
  }
}
