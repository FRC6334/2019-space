/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Ultrasonic;
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
  PWMTalonSRX backDriveLeft, backDriveRight;
  CANSparkMax arm, front, back;
  AHRS navX;
  CANEncoder armEncoder, frontClimbEncoder, backClimbEncoder;
  CANPIDController armPID;
  Ultrasonic frontSensor, backSensor;

  public Climber() {
    System.out.println("Climber subsystem init");
    navX = new AHRS(SPI.Port.kMXP);
    frontSensor = new Ultrasonic(1, 0);
    backSensor = new Ultrasonic(3, 2);
    backDriveLeft = new PWMTalonSRX(8);
    backDriveRight = new PWMTalonSRX(9);
    front = new CANSparkMax(6, MotorType.kBrushless);
    back = new CANSparkMax(7, MotorType.kBrushless);

    arm = new CANSparkMax(5, MotorType.kBrushless);
    armEncoder = new CANEncoder(arm);
    frontClimbEncoder = new CANEncoder(front);
    backClimbEncoder = new CANEncoder(back);
    armPID = new CANPIDController(arm);

    backDriveRight.setInverted(true);
    back.setInverted(true);

    armPID.setP(0.25);
    armPID.setI(0);
    armPID.setD(0.20);
    armPID.setIZone(0);
    armPID.setFF(0);
    armPID.setOutputRange(-0.25, 0.6);
  }

  public void resetArm() {
    armEncoder.setPosition(0);
  }

  public void resetBothClimbEncoders() {
    frontClimbEncoder.setPosition(0);
    backClimbEncoder.setPosition(0);
  }

  public double getFrontSensorInches() {
    return frontSensor.getRangeInches();
  }

  public double getBackSensorInches() {
    return backSensor.getRangeInches();
  }

  public void driveFrontClimber(double val) {
    front.set(val);
  }

  public void driveBackClimber(double val) {
    back.set(val);
  }

  public void driveClimbBoth(double val) {
    front.set(val);
    back.set(val);
  }

  public double getRoll() {
    return navX.getRoll();
  }

  public void driveLeftClimbAxle(double val) {
    backDriveLeft.set(val);
  }

  public void driveRightClimbAxle(double val) {
    backDriveRight.set(val);
  }

  public void driveBothClimbAxleWheels(double val) {
    backDriveLeft.set(val);
    backDriveRight.set(val);
  }

  public double getFrontClimbEncoder() {
    return frontClimbEncoder.getPosition();
  }

  public double getBackClimbEncoder() {
    return backClimbEncoder.getPosition();
  }

  public double getFrontClimbVoltage() {
    return front.getBusVoltage();
  }

  public double getBackClimbVoltage() {
    return back.getBusVoltage();
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

  public void driveArm(double num) {
    arm.set(num * RobotMap.speedLimiter);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ClimberDrive());
  }
}
