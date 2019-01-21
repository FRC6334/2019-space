/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.HashMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ClimberDrive;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {

  DoubleSolenoid rightFront, rightBack, leftFront, leftBack;
  boolean rightFrontExtended, rightBackExtended, leftFrontExtended, leftBackExtended, gg = false;

  public Climber() {
    System.out.println("Climber subsystem init");
    rightFront = new DoubleSolenoid(RobotMap.pcm.mainPcm, RobotMap.climber.rightFrontExtend,
        RobotMap.climber.rightFrontReverse);
    rightBack = new DoubleSolenoid(RobotMap.pcm.mainPcm, RobotMap.climber.rightBackExtend,
        RobotMap.climber.rightBackReverse);
    leftFront = new DoubleSolenoid(RobotMap.pcm.mainPcm, RobotMap.climber.leftFrontExtend,
        RobotMap.climber.leftFrontReverse);
    leftBack = new DoubleSolenoid(RobotMap.pcm.mainPcm, RobotMap.climber.leftBackExtend,
        RobotMap.climber.leftBackReverse);
        
    rightFront.set(DoubleSolenoid.Value.kOff);
    rightBack.set(DoubleSolenoid.Value.kOff);
    leftFront.set(DoubleSolenoid.Value.kOff);
    leftBack.set(DoubleSolenoid.Value.kOff);
  }

  public void togglePiston(int button) {
    switch (button) {
    case 6:
      if (leftFrontExtended) {
        leftFront.set(DoubleSolenoid.Value.kReverse);
      } else {
        leftFront.set(DoubleSolenoid.Value.kForward);
      }
      leftFrontExtended = !leftFrontExtended;
      break;
    case 7:
      if (leftBackExtended) {
        leftBack.set(DoubleSolenoid.Value.kReverse);
      } else {
        leftBack.set(DoubleSolenoid.Value.kForward);
      }
      leftBackExtended = !leftBackExtended;
      break;
    case 10:
      if (rightBackExtended) {
        rightBack.set(DoubleSolenoid.Value.kReverse);
      } else {
        rightBack.set(DoubleSolenoid.Value.kForward);
      }
      rightBackExtended = !rightBackExtended;
      break;
    case 11:
      if (rightFrontExtended) {
        rightFront.set(DoubleSolenoid.Value.kReverse);
      } else {
        rightFront.set(DoubleSolenoid.Value.kForward);
      }
      rightFrontExtended = !rightFrontExtended;
      break;
    default:
      System.out.println("Piston toggler was fed an unhandled button! " + button);
      break;
    }
  }

  public void toggleAll() {
    if (gg) {
      rightBack.set(DoubleSolenoid.Value.kReverse);
      leftBack.set(DoubleSolenoid.Value.kReverse);
      leftFront.set(DoubleSolenoid.Value.kReverse);
      rightFront.set(DoubleSolenoid.Value.kReverse);
    } else {
      rightBack.set(DoubleSolenoid.Value.kForward);
      leftBack.set(DoubleSolenoid.Value.kForward);
      leftFront.set(DoubleSolenoid.Value.kForward);
      rightFront.set(DoubleSolenoid.Value.kForward);
    }
    gg = !gg;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ClimberDrive());
  }
}
