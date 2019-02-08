/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.TankDrive;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.kauailabs.navx.frc.AHRS;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  AHRS navx;
  CANSparkMax rightFront, rightBack, leftFront, leftBack;
  Compressor compressor;

  public DriveTrain() {
    leftFront = new CANSparkMax(RobotMap.leftFrontMotor, MotorType.kBrushless);
    leftBack = new CANSparkMax(RobotMap.leftBackMotor, MotorType.kBrushless);
    rightFront = new CANSparkMax(RobotMap.rightFrontMotor, MotorType.kBrushless);
    rightBack = new CANSparkMax(RobotMap.rightBackMotor, MotorType.kBrushless);

    leftFront.setInverted(true);
    leftBack.setInverted(true);

    //leftMotor1.setNeutralMode(NeutralMode.Brake);
    //leftMotor2.setNeutralMode(NeutralMode.Brake);
    //rightMotor1.setNeutralMode(NeutralMode.Brake);
    //rightMotor2.setNeutralMode(NeutralMode.Brake);

    compressor = new Compressor(RobotMap.compressor);
    compressor.setClosedLoopControl(true);

    navx = new AHRS(SPI.Port.kMXP);
    navx.reset();
  }

  public void tankDrive(double leftThrottle, double rightThrottle) {
    setMotorValues(leftThrottle, rightThrottle);
  }

  private void setMotorValues(double left, double right) {
    leftFront.set(left * RobotMap.speedLimiter);
    leftBack.set(left * RobotMap.speedLimiter);
    rightFront.set(right * RobotMap.speedLimiter);
    rightBack.set(right * RobotMap.speedLimiter);
  }

  @Override
  public void initDefaultCommand() {
    System.out.println("Init drivetrain");
    // Set the default command for a subsystem here.
    setDefaultCommand(new TankDrive());
  }
}
