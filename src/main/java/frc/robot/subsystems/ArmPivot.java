/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.MovePivotDown;

/**
 * Add your docs here.
 */
public class ArmPivot extends Subsystem {

  PWMTalonSRX armPivot;

  public ArmPivot() {
    armPivot = new PWMTalonSRX(RobotMap.arm.armTalonId);
  }
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public void driveArm(int num) {
    armPivot.set(num);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new MovePivotDown());
  }
}
