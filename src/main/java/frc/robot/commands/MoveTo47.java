/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.RobotMap;

public class MoveTo47 extends CommandBase {

  boolean upThere = false;

  public MoveTo47() {
    super("MoveTo47Inches");
    requires(arm);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("Move to 47 started");
    arm.setArmPos(-9.2);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println("m47exec");
  }

  public boolean isFinished() { // Do not worry about this method. It will be interrupted when the button is released (2)
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    arm.driveArm(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    System.out.println("Move to 47 interrupt");
  }
}
