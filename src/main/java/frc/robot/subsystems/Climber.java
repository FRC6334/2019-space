/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.ClimberDrive;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {

  DoubleSolenoid frontCylinders, backCylinders;
  Joystick auxStick;

  public Climber() {
    System.out.println("Climber subsystem init");
    frontCylinders = new DoubleSolenoid(RobotMap.climber.frontCylinders1, RobotMap.climber.frontCylinders2);
    auxStick = OI.getAuxStick();
  }
  
  public void toggleAllCylinders() {

  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ClimberDrive());
  }
}
