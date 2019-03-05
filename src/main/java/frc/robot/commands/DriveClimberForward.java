/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

public class DriveClimberForward extends CommandBase {
  public DriveClimberForward() {
    super("driveclimberforward");
    // Use requires() here to declare subsystem dependencies
    requires(climber);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("Driving climber forward");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    climber.driveBack(0.5);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("done driving the climber forwards");
    climber.driveBack(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    System.out.println("climber forward drive interrupted");
    climber.driveBack(0);
  }
}
