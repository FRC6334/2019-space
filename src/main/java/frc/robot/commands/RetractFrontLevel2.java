/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.OI;

public class RetractFrontLevel2 extends CommandBase {

  Joystick rightStick;

  public RetractFrontLevel2() {
    super("retractfrontfor2");
    // Use requires() here to declare subsystem dependencies
    requires(climber);
    rightStick = OI.getRightDriveStick();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("climb level 2 retract front");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (rightStick.getRawButton(2)) {
      if (climber.getFrontClimbEncoder() < 0.5) {
        climber.driveFrontClimber(0.2);
      }
    } else {
      climber.driveFrontClimber(0);
    }
    if (climber.getBackClimbEncoder() >= -27) { // -27 for level 2, -65 for 3
      climber.driveBackClimber(-0.3);
    } else {
      climber.driveBackClimber(0);
    }
    if (rightStick.getRawButton(4)) {
      climber.driveBothClimbAxleWheels(0.50);
    } else {
      climber.driveBothClimbAxleWheels(0);
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
    System.out.println("is finished front level 2");
    climber.driveFrontClimber(0);
    climber.driveBackClimber(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    System.out.println("front climber drive interrupt level 2");
    climber.driveFrontClimber(0);
    climber.driveBackClimber(0);
  }
}
