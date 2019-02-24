/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

public class RunVacuumBackwards extends CommandBase {
  public RunVacuumBackwards() {
    super("drivevacuumbackwards");
    // Use requires() here to declare subsystem dependencies
    requires(vacuum);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("Starting to backdrive vacuum motor...");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println("Backdriving vacuum");
    vacuum.setBackwards();
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

  @Override
  protected void interrupted() { // Called when the button specified in the OI is released
    System.out.println("Done backdriving the vacuum motor...");
    vacuum.setValue(0);
  }
}
