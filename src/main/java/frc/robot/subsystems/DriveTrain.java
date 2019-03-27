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
import frc.robot.commands.ArcadeDrive;
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
  CANSparkMax m_rightFront, m_rightBack, m_leftFront, m_leftBack;
  Compressor compressor;

  public DriveTrain() {
    m_leftFront = new CANSparkMax(RobotMap.leftFrontMotor, MotorType.kBrushless);
    m_leftBack = new CANSparkMax(RobotMap.leftBackMotor, MotorType.kBrushless);
    m_rightFront = new CANSparkMax(RobotMap.rightFrontMotor, MotorType.kBrushless);
    m_rightBack = new CANSparkMax(RobotMap.rightBackMotor, MotorType.kBrushless);

    m_rightBack.follow(m_rightFront);
    m_leftBack.follow(m_leftFront);


    m_leftFront.setInverted(true);
    m_leftBack.setInverted(true);

    compressor = new Compressor(RobotMap.compressor);
    compressor.setClosedLoopControl(true);

    navx = new AHRS(SPI.Port.kMXP);
    navx.reset();
  }

  public void arcadeDrive(double x, double y) {
    double right = y;
		double left = y;
		double turningThrottleScale;
		
		turningThrottleScale = Math.abs(y) * 2;

		if(Math.abs(right) <= 0.05)
			right = 0;
		if(Math.abs(left) <= 0.05)
			left = 0;
		
		if(y <= 0) {
			right += x * turningThrottleScale;  
			left -= x * turningThrottleScale;
		} else {
			right -= x * turningThrottleScale;  
			left += x * turningThrottleScale;
		}
		
		setMotorValues(left, right);
  }

  public void tankDrive(double leftThrottle, double rightThrottle) {
    setMotorValues(leftThrottle, rightThrottle);
  }

  private void setMotorValues(double left, double right) {
    m_leftFront.set(left);
    m_leftBack.set(left);
    m_rightFront.set(right);
    m_rightBack.set(right);
  }

  @Override
  public void initDefaultCommand() {
    System.out.println("Init drivetrain");
    // Set the default command for a subsystem here.
    setDefaultCommand(new ArcadeDrive());
  }
}
