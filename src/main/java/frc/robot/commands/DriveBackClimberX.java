/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.OI;
import frc.robot.RobotMap.rightStick;

public class DriveBackClimberX extends CommandBase {

  Joystick rightStick, arcadeStick;

  public DriveBackClimberX() {
    super("drivebackclimberx");
    // Use requires() here to declare subsystem dependencies
    requires(climber);
    requires(driveTrain);
    rightStick = OI.getRightDriveStick();
    arcadeStick = OI.getLeftDriveStick();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("back climber drive init");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (rightStick.getRawButton(2)) {
      if (climber.getBackClimbEncoder() < 0.5) {
        climber.driveBackClimber(0.2);
      }
    } else {
      climber.driveBackClimber(0);
    }
    if (rightStick.getRawButton(4)) {
      climber.driveBothClimbAxleWheels(0.50);
    } else {
      climber.driveBothClimbAxleWheels(0);
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
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("front climber done");
    climber.driveBackClimber(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    climber.driveBackClimber(0);
    System.out.println("back climber drive interrupt");
  }
}
