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
  CANSparkMax m_front, m_back;
  AHRS navX;
  CANEncoder frontClimbEncoder, backClimbEncoder;
  Ultrasonic frontSensor, backSensor;

  public Climber() {
    System.out.println("Climber subsystem init");
    navX = new AHRS(SPI.Port.kMXP);
    frontSensor = new Ultrasonic(1, 0);
    backSensor = new Ultrasonic(3, 2);
    backDriveLeft = new PWMTalonSRX(8);
    backDriveRight = new PWMTalonSRX(9);
    m_front = new CANSparkMax(6, MotorType.kBrushless);
    m_back = new CANSparkMax(7, MotorType.kBrushless);

    frontClimbEncoder = new CANEncoder(m_front);
    backClimbEncoder = new CANEncoder(m_back);

    backDriveRight.setInverted(true);
    m_back.setInverted(true);
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
    m_front.set(val);
  }

  public void driveBackClimber(double val) {
    m_back.set(val);
  }

  public void driveClimbBoth(double val) {
    m_front.set(val);
    m_back.set(val);
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
    return m_front.getBusVoltage();
  }

  public double getBackClimbVoltage() {
    return m_back.getBusVoltage();
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ClimberDrive());
  }
}
