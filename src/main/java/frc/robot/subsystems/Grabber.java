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
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DiskGrabber;;

/**
 * Add your docs here.
 */
public class Grabber extends Subsystem {

  DoubleSolenoid.Value FORWARD = DoubleSolenoid.Value.kForward;
  DoubleSolenoid.Value REVERSE = DoubleSolenoid.Value.kReverse;
  DoubleSolenoid.Value OFF = DoubleSolenoid.Value.kReverse;
  DoubleSolenoid disk;
  // PWMTalonSRX backDriveLeft, backDriveRight;
  boolean diskExtended = false;

  public Grabber() {
    System.out.println("Grabber subsystem init");
    disk = new DoubleSolenoid(3, 4, 5);
  }

  public void toggle() {
    if (diskExtended) {
      disk.set(REVERSE);
    } else {
      disk.set(FORWARD);
    }
    diskExtended = !diskExtended;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DiskGrabber());
  }
}
