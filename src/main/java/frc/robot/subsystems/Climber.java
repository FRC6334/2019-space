/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ClimberDrive;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {

  DoubleSolenoid pistonTest;
  boolean extended = false;

  public Climber() {
    System.out.println("Climber subsystem init");
    pistonTest = new DoubleSolenoid(RobotMap.pcm.auxPcm, RobotMap.climber.frontRightCylinderOpen, RobotMap.climber.frontRightCylinderClose);
  }
  
  public void togglePiston() {
    if (extended) {
      pistonTest.set(DoubleSolenoid.Value.kForward);
    } else {
      pistonTest.set(DoubleSolenoid.Value.kReverse);
    }
    extended = !extended;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ClimberDrive());
  }
}
