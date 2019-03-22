/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;

public class ArcadeDrive extends CommandBase {

  Joystick rightStick;
  Joystick leftStick;
  boolean extended = false;
  boolean tank = false;

  public ArcadeDrive() {
    super("ArcadeDrive");
    System.out.println("arcade command init");
    requires(driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("arcadedrive command init 2");
    rightStick = OI.getRightDriveStick();
    leftStick = OI.getLeftDriveStick();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (leftStick.getRawButtonPressed(1)) {
      grabber.toggle();
    }
    double leftX = Math.abs(leftStick.getX()) < 0.05 ? 0 : leftStick.getX();
    double leftY = Math.abs(leftStick.getY()) < 0.05 ? 0 : leftStick.getY(); // Handle deadband
    double rightX = Math.abs(rightStick.getX()) < 0.05 ? 0 : rightStick.getX();
    double rightY = Math.abs(rightStick.getY()) < 0.05 ? 0 : rightStick.getY();
    // for other programmers: DO NOT call .tankDrive or .arcadeDrive (TBD) on a condition! You always must send something or else the Talon will resume it's last instruction. Send 0 to stop them
    if (tank) driveTrain.tankDrive(leftY, rightY);
    else driveTrain.arcadeDrive(leftX, leftY);

  }

  // Make this return true when this Command no longer needs to run execute()
  public boolean isFinished() {
    return false; // Never end tankdrive. I can be proper and use JoystickButtons in the OI, but this is easier.
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("End");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    System.out.println("DT Interrupt");
  }
}
