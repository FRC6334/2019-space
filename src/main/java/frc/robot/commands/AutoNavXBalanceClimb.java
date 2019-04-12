/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.OI;

public class AutoNavXBalanceClimb extends CommandBase {

  Joystick rightStick;

  public AutoNavXBalanceClimb() {
    super("navxautobalance");
    // Use requires() here to declare subsystem dependencies
    requires(climber);
    rightStick = OI.getRightDriveStick();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("auto balance navx started");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    balance();
  }

  public void balance() {
    double roll = climber.getRoll();
    if (Math.abs(roll) < 2)
      roll = 0;
    if (roll == 0) {
      if (climber.getFrontClimbEncoder() > -65) {
        climber.driveFrontClimber(-0.50);
      } else climber.driveFrontClimber(0);
      if (climber.getBackClimbEncoder() > -65) {
        climber.driveBackClimber(-0.50);
      } else climber.driveBackClimber(0);
    }
    ;
    if (roll > 0 && Math.ceil(climber.getFrontClimbEncoder()) >= -65) {
      System.out.println("I'm leaning forwards... adjusting");
      if (climber.getFrontClimbEncoder() > -65) {
        climber.driveFrontClimber(-0.20);
        climber.driveBackClimber(0);
      } else {
        climber.driveFrontClimber(0);
        climber.driveBackClimber(0);
      }
    } else if (roll < 0) {
      System.out.println("I'm leaning backwards... adjusting");
      if (climber.getBackClimbEncoder() > -65) {
        climber.driveBackClimber(-0.20);
        climber.driveFrontClimber(0);
      } else {
        climber.driveBackClimber(0);
        climber.driveFrontClimber(0);
      }
    }
    if (rightStick.getRawButton(4)) {
      climber.driveBothClimbAxleWheels(0.50);
    }
    if (rightStick.getRawButton(5)) {
      climber.driveBothClimbAxleWheels(-0.50);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return Math.ceil(climber.getFrontClimbEncoder()) <= -65 && Math.ceil(climber.getBackClimbEncoder()) <= -65;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("auto balance is done.");
    climber.driveClimbBoth(0);
    climber.driveBothClimbAxleWheels(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    System.out.println("auto balance interrupt");
    climber.driveClimbBoth(0);
    climber.driveBackClimber(0);
    climber.driveBothClimbAxleWheels(0);
  }
}
