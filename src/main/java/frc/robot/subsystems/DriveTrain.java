/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.TankDrive;

import com.kauailabs.navx.frc.AHRS;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  AHRS navx;
  PWMTalonSRX leftMotor1, leftMotor2, rightMotor1, rightMotor2;
  Compressor compressor;

  public DriveTrain() {
    leftMotor1 = new PWMTalonSRX(RobotMap.leftMotor1);
    // leftMotor2 = new TalonSRX(RobotMap.leftMotor2);
    rightMotor1 = new PWMTalonSRX(RobotMap.rightMotor1);
    // rightMotor2 = new TalonSRX(RobotMap.rightMotor2);

    rightMotor1.setInverted(true);
    //rightMotor2.setInverted(true);

    //leftMotor1.setNeutralMode(NeutralMode.Brake);
    //leftMotor2.setNeutralMode(NeutralMode.Brake);
    //rightMotor1.setNeutralMode(NeutralMode.Brake);
    //rightMotor2.setNeutralMode(NeutralMode.Brake);

    // compressor = new Compressor(0);
    // compressor.setClosedLoopControl(true);

    navx = new AHRS(SPI.Port.kMXP);
    navx.reset();
  }

  public void tankDrive(double leftThrottle, double rightThrottle) {
    setMotorValues(leftThrottle, rightThrottle);
  }

  private void setMotorValues(double left, double right) {
    leftMotor1.set(left * RobotMap.speedLimiter);
    //leftMotor2.set(ControlMode.PercentOutput, left);
    rightMotor1.set(right * RobotMap.speedLimiter);
   // rightMotor2.set(ControlMode.PercentOutput, right);
  }

  @Override
  public void initDefaultCommand() {
    System.out.println("Init drivetrain");
    // Set the default command for a subsystem here.
    setDefaultCommand(new TankDrive());
  }
}
