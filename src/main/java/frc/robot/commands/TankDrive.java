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

public class TankDrive extends CommandBase {

  Joystick rightStick;
  Joystick leftStick;
  boolean extended = false;
  PowerDistributionPanel pdp;

  public TankDrive() {
    super("TankDrive");
    System.out.println("TankDrive command init");
    requires(driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("TankDrive command init 2");
    rightStick = OI.getRightDriveStick();
    leftStick = OI.getLeftDriveStick();
    pdp = new PowerDistributionPanel();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double leftThrottle = Math.abs(leftStick.getY()) < 0.05 ? 0 : leftStick.getY(); // Handle deadband
    double rightThrottle = Math.abs(rightStick.getY()) < 0.05 ? 0 : rightStick.getY();
    // for other programmers: DO NOT call .tankDrive or .arcadeDrive (TBD) on a condition! You always must send something or else the Talon will resume it's last instruction. Send 0 to stop them
    if (rightStick.getRawButtonPressed(4)) {
      driveTrain.tankDrive(0.7, 0.7);
    }
    driveTrain.tankDrive(leftThrottle, rightThrottle);

    SmartDashboard.putNumber("Battery IN voltage", pdp.getVoltage());
    SmartDashboard.putNumber("Power drawn all channels", pdp.getTotalPower());
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
