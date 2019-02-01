/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.OI;
import frc.robot.RobotMap;

public class ClimberDrive extends CommandBase {

  Joystick rightStick, auxJoystick;

  public ClimberDrive() {
    super("ClimberDrive");
    requires(climber);
    rightStick = OI.getRightDriveStick();
    auxJoystick = OI.getAuxStick();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (rightStick.getRawButtonPressed(6)) climber.toggleFront();
    if (rightStick.getRawButtonPressed(7)) climber.toggleBack();
    // if (rightStick.getRawButtonPressed(11)) climber.togglePiston(11);
    // if (rightStick.getRawButtonPressed(10)) climber.togglePiston(10);
    if (rightStick.getRawButtonPressed(3)) climber.toggleAll();
    
    if (Math.abs(auxJoystick.getY()) <= 0.05) climber.driveBack(0);
    else climber.driveBack(auxJoystick.getY() * RobotMap.climber.speedLimiter);
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
  }
}
