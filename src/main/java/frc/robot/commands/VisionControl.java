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
import frc.robot.commands.CommandBase;

public class VisionControl extends CommandBase {

  Joystick rightStick = OI.getRightDriveStick();
  int visionMode = 0;

  public VisionControl() {
    super("VisonControl");
    // Use requires() here to declare subsystem dependencies
    requires(vision);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("Vision init");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (rightStick.getRawButtonPressed(RobotMap.rightStick.cycleVisionMode)) {
      if (visionMode == 3) {
        visionMode = 0;
        vision.setLedMode(visionMode);
      } else {
        visionMode++;
        vision.setLedMode(visionMode);
      }
      System.out.println("Vision mode: " + visionMode);
    }
    if (rightStick.getRawButtonPressed(RobotMap.rightStick.cycleCamMode)) {
      vision.toggleCamMode();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
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
