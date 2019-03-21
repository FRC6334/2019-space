/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Hab3AutoClimb;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.OI;
import frc.robot.commands.CommandBase;

public class AutoRetractBackAxle extends CommandBase {

  Joystick arcadeStick = OI.getLeftDriveStick(); // arcade stick

  public AutoRetractBackAxle() {
    super("autoretractbackaxle");
    // Use requires() here to declare subsystem dependencies
    requires(climber);
    requires(driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("going to retract the back climber.");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Math.ceil(climber.getBackClimbEncoder()) < 0) {
      climber.driveBackClimber(0.15);
    } else {
      climber.driveBackClimber(0);
    } 
    if (Math.abs(arcadeStick.getY()) < 0.05) {
      driveTrain.arcadeDrive(0, 0);
    } else {
      driveTrain.arcadeDrive(arcadeStick.getX(), arcadeStick.getY());
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return Math.ceil(climber.getBackClimbEncoder()) >= -2;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    climber.driveBothClimbAxleWheels(0);
    climber.driveClimbBoth(0);
    System.out.println("done retracting the back axle.");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    climber.driveBothClimbAxleWheels(0);
    climber.driveClimbBoth(0);
    System.out.println("back axle retraction command has been interrupted.");
  }
}
