/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ClimberDrive;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {

  DoubleSolenoid.Value FORWARD = DoubleSolenoid.Value.kForward;
  DoubleSolenoid.Value REVERSE = DoubleSolenoid.Value.kReverse;
  DoubleSolenoid.Value OFF = DoubleSolenoid.Value.kReverse;
  DoubleSolenoid rightFront, rightBack, leftFront, leftBack;
  PWMTalonSRX backDriveLeft, backDriveRight;
  CANSparkMax arm;
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

        backDriveLeft = new PWMTalonSRX(8);
        backDriveRight = new PWMTalonSRX(9);

        arm = new CANSparkMax(5, MotorType.kBrushless);

        backDriveRight.setInverted(true);

    rightFront.set(REVERSE);
    rightBack.set(REVERSE);
    leftFront.set(REVERSE);
    leftBack.set(REVERSE);
  }

  public void togglePiston(int button) {
    switch (button) {
    case 6:
      if (leftFrontExtended) {
        leftFront.set(REVERSE);
      } else {
        leftFront.set(FORWARD);
      }
      leftFrontExtended = !leftFrontExtended;
      break;
    case 7:
      if (leftBackExtended) {
        leftBack.set(REVERSE);
      } else {
        leftBack.set(FORWARD);
      }
      leftBackExtended = !leftBackExtended;
      break;
    case 10:
      if (rightBackExtended) {
        rightBack.set(REVERSE);
      } else {
        rightBack.set(FORWARD);
      }
      rightBackExtended = !rightBackExtended;
      break;
    case 11:
      if (rightFrontExtended) {
        rightFront.set(REVERSE);
      } else {
        rightFront.set(FORWARD);
      }
      rightFrontExtended = !rightFrontExtended;
      break;
    default:
      System.out.println("Piston toggler was fed an unhandled button! " + button);
      break;
    }
  }

  boolean extended = true;
  public void testR() {
    if (extended) {
      rightBack.set(REVERSE);
    } else {
      rightBack.set(FORWARD);
    }
    System.out.println(extended);
    extended = !extended;
  }

   public void driveBack(double amnt) {
     backDriveLeft.set(amnt);
     backDriveRight.set(-amnt);
  }

  // public void driveIndividual(double left, double right) {
  //   backDriveLeft.set(left);
  //   backDriveRight.set(right);
  // }

  public void toggleAll() {
    if (gg) {
      rightBack.set(REVERSE);
      leftBack.set(REVERSE);
      leftFront.set(REVERSE);
      rightFront.set(REVERSE);
    } else {
      rightBack.set(FORWARD);
      leftBack.set(FORWARD);
      leftFront.set(FORWARD);
      rightFront.set(FORWARD);
    }
    gg = !gg;
  }
  
  public void driveArm(double num) {
    arm.set(num);
  }

  public void toggleBack() {
    if (rightBackExtended && leftBackExtended) {
      rightBack.set(REVERSE);
      leftBack.set(REVERSE);
    } else {
      rightBack.set(FORWARD);
      leftBack.set(FORWARD);
    }
    rightBackExtended = !rightBackExtended;
    leftBackExtended = !leftBackExtended;
  }

  public void toggleFront() {
    if (rightFrontExtended && leftFrontExtended) {
      rightFront.set(REVERSE);
      leftFront.set(REVERSE);
    } else {
      rightFront.set(FORWARD);
      leftFront.set(FORWARD);
    }
    rightFrontExtended = !rightFrontExtended;
    leftFrontExtended = !leftFrontExtended;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ClimberDrive());
  }
}
