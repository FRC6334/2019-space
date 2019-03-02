/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
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
  DoubleSolenoid front, back;
  PWMTalonSRX backDriveLeft, backDriveRight;
  CANSparkMax arm;
  CANEncoder armEncoder;
  CANPIDController armPID;
  boolean rightFrontExtended, rightBackExtended, leftFrontExtended, leftBackExtended, gg = false;

  public Climber() {
    System.out.println("Climber subsystem init");
    front = new DoubleSolenoid(RobotMap.pcm.mainPcm, RobotMap.climber.rightFrontExtend, RobotMap.climber.rightFrontReverse);
    back = new DoubleSolenoid(RobotMap.pcm.mainPcm, RobotMap.climber.rightBackExtend, RobotMap.climber.rightBackReverse);

        backDriveLeft = new PWMTalonSRX(8);
        backDriveRight = new PWMTalonSRX(9);

        arm = new CANSparkMax(5, MotorType.kBrushless);
        armEncoder = new CANEncoder(arm);
        armPID = new CANPIDController(arm);
        armEncoder.getVelocity();
        armEncoder.getPosition();

        backDriveRight.setInverted(true);

    front.set(REVERSE);
    back.set(REVERSE);

    armPID.setP(0.25);
    armPID.setI(0);
    armPID.setD(0.20);
    armPID.setIZone(0);
    armPID.setFF(0);
    armPID.setOutputRange(-0.15, 0.6);
  }

  public void setArmPos(double revs) {
    armPID.setReference(revs, ControlType.kPosition);
  }

  public double getPosition() {
    return armEncoder.getPosition();
  }

  public double getVelocity() {
    return armEncoder.getVelocity();
  }

   public void driveBack(double amnt) {
     backDriveLeft.set(amnt);
     backDriveRight.set(amnt);
  }

  // public void driveIndividual(double left, double right) {
  //   backDriveLeft.set(left);
  //   backDriveRight.set(right);
  // }

  public void toggleAll() {
    if (gg) {
      front.set(REVERSE);
      back.set(REVERSE);
    } else {
      front.set(FORWARD);
      back.set(FORWARD);
    }
    gg = !gg;
  }
  
  public void driveArm(double num) {
    arm.set(num * RobotMap.speedLimiter);
  }

  public void toggleBack() {
    if (rightBackExtended && leftBackExtended) {
      back.set(REVERSE);
    } else {
      back.set(FORWARD);
    }
    rightBackExtended = !rightBackExtended;
    leftBackExtended = !leftBackExtended;
  }

  public void toggleFront() {
    if (rightFrontExtended && leftFrontExtended) {
      front.set(REVERSE);
    } else {
      front.set(FORWARD);
    }
    rightFrontExtended = !rightFrontExtended;
    leftFrontExtended = !leftFrontExtended;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ClimberDrive());
  }
}
