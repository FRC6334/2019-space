/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import frc.robot.commands.ArmDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.RobotMap;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class Arm extends Subsystem {
    CANSparkMax m_arm;
    CANEncoder armEncoder;
    CANPIDController armPID;
  
    public Arm() {
      System.out.println("Arm subsystem init");
      
      m_arm = new CANSparkMax(5, MotorType.kBrushless);
      armEncoder = new CANEncoder(m_arm);
      armPID = new CANPIDController(m_arm);
  
      armPID.setP(0.25);
      armPID.setI(0);
      armPID.setD(0.20);

      armPID.setIZone(0);
      armPID.setFF(0);
      armPID.setOutputRange(-0.25, 0.6);
    }
  
    public void resetArm() { armEncoder.setPosition(0); }
  
    public void setArmPos(double revs) { armPID.setReference(revs, ControlType.kPosition); }
  
    public double getPosition() { return armEncoder.getPosition(); }
  
    public double getVelocity() { return armEncoder.getVelocity(); }
  
    public void driveArm(double num) { m_arm.set(num * RobotMap.speedLimiter); }
  
    @Override
    public void initDefaultCommand() {
      setDefaultCommand(new ArmDrive());
    }
  }