/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.OI;

public class AutoLimelightAlign extends CommandBase {

  Joystick leftStick = OI.getLeftDriveStick();

  public AutoLimelightAlign() {
    super("AutoLimelightAlign");
    requires(driveTrain);
    requires(vision);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("using limelight align");
    vision.setLedMode(3);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double left_command = 0;
    double right_command = 0;
    double Kp = 0.01f;
    double min_command = 0.05f;

    double tx = vision.getXOffset();
    boolean targetValid = vision.hasTarget();
    System.out.println("Offset: " + tx);
    if (tx != 0) {

      double heading_error = -tx;
      double steering_adjust = 0.0f;
      if (tx > -1.75) {
        steering_adjust = Kp * heading_error - min_command;
      } else if (tx < -3.75) {
        steering_adjust = Kp * heading_error + min_command;
      }
      left_command += steering_adjust;
      right_command -= steering_adjust;
      driveTrain.tankDrive(left_command, right_command);
      double leftX = Math.abs(leftStick.getX()) < 0.05 ? 0 : leftStick.getX();
      double leftY = Math.abs(leftStick.getY()) < 0.05 ? 0 : leftStick.getY();
      if (leftX != 0 || leftY != 0) {
        driveTrain.arcadeDrive(leftX, leftY);
      }
    } else {
      double leftX = Math.abs(leftStick.getX()) < 0.05 ? 0 : leftStick.getX();
      double leftY = Math.abs(leftStick.getY()) < 0.05 ? 0 : leftStick.getY();
      driveTrain.arcadeDrive(leftX, leftY);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    driveTrain.tankDrive(0, 0);
    vision.disableCameraTracking();
    vision.setLedMode(1);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    vision.disableCameraTracking();
    driveTrain.tankDrive(0, 0);
    System.out.println("limelight port stopped");
    vision.setLedMode(1);
  }
}
