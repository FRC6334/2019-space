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
  DoubleSolenoid disk, retractIntake;
  // PWMTalonSRX backDriveLeft, backDriveRight;
  boolean diskExtended = false;
  boolean intakeExtended = false;

  public Grabber() {
    System.out.println("Grabber subsystem init");
    disk = new DoubleSolenoid(RobotMap.pcm.mainPcm, 4, 5); // The disk grabber solenoid is plugged into the main pcm (ID=1), 0 being the open forward channel and 1 being the reverse
    retractIntake = new DoubleSolenoid(RobotMap.pcm.mainPcm, 6, 7);
    retractIntake.set(REVERSE);
    disk.set(REVERSE);
  }

  public void toggle() {
    if (diskExtended) {
      System.out.println("Grabber is closing.");
      disk.set(REVERSE);
    } else {
      System.out.println("Grabber is opening.");
      disk.set(FORWARD);
    }
    diskExtended = !diskExtended;
  }

  public void toggleIntake() {
    if (intakeExtended) {
      System.out.println("retracting the intake.");
      retractIntake.set(REVERSE);
    } else {
      System.out.println("intake is extending now.");
      retractIntake.set(FORWARD);
    }
    intakeExtended = !intakeExtended;
  }

  public void extendIntake() { // I shouldn't be logging stuff in a subsystem over a command, but at this point, whatever.
    System.out.println("manual override, extending intake.");
    retractIntake.set(FORWARD);
    intakeExtended = true;
  }

  public void retractIntake() {
    System.out.println("manual override, retracting intake");
    retractIntake.set(REVERSE);
    intakeExtended = false;
  }

  public void setForward() {
    System.out.println("Grabber has been set forward");
    disk.set(FORWARD);
    diskExtended = true;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DiskGrabber());
  }
}
