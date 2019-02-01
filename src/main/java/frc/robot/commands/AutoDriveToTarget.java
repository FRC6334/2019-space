/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;

public class AutoDriveToTarget extends CommandBase {

  NetworkTable limelight;
  double xOffset;

  public AutoDriveToTarget() {
    super("AutoDrive");
    System.out.println("wew");
    // Use requires() here to declare subsystem dependencies
    requires(driveTrain);
    requires(vision);
    requires(climber);
    System.out.println("lad");

    limelight = NetworkTableInstance.getDefault().getTable("limelight");
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("Init");
    xOffset = limelight.getEntry("tx").getDouble(0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    xOffset = limelight.getEntry("tx").getDouble(0);
    double target = limelight.getEntry("tv").getDouble(0);

    if (target == 0) {
      System.out.println("No target. Spinning aimlessly until I find something.");
      climber.driveIndividual(0.3, -0.3);
    } else if (target == 1) {
      double throttle = 0.07 * limelight.getEntry("tx").getDouble(0);
      if (Math.abs(throttle) > 0.5) {
        int sign = 1;
        if (throttle < 0) {
          sign = -1;
        }
        throttle = sign * 0.5;
      }
      System.out.println(throttle);
      climber.driveIndividual(-throttle, throttle);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  public boolean isFinished() {
    return Math.abs(xOffset) <= 2 && limelight.getEntry("tv").getDouble(0) == 1;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("end");
    climber.driveIndividual(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    System.out.println("interrupt auto");
  }
}
