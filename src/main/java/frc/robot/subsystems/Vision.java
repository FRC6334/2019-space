/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.VisionControl;

/**
 * Add your docs here.
 */
public class Vision extends Subsystem {

  NetworkTable nTable;
  NetworkTableEntry tx, ty, ta, tv, ts;
  Servo cameraServo;
  boolean driverMode;

  public Vision() {
    cameraServo = new Servo(7);
    nTable = NetworkTableInstance.getDefault().getTable("limelight");
    tx = nTable.getEntry("tx");
    ty = nTable.getEntry("ty");
    ta = nTable.getEntry("ta");
    tv = nTable.getEntry("tv");
    ts = nTable.getEntry("ts");

    driverMode = false;
  }

  public void setLedMode(int num) {
    System.out.println(num + " provided " + nTable.getEntry("ledMode").getDouble(0));
    nTable.getEntry("ledMode").setDouble(num);
  }

  public void toggleCamMode() {
    if (driverMode) {
      driverMode = !driverMode;
      nTable.getEntry("camMode").setDouble(1);
    } else {
      driverMode = !driverMode;
      nTable.getEntry("camMode").setDouble(0);
    }
  }

  public void disableCameraTracking() {
    driverMode = true;
    nTable.getEntry("camMode").setDouble(1);
  }

  public void enableCameraTracking() {
    driverMode = false;
    nTable.getEntry("camMode").setDouble(0);
  }

  public void camHigh() {
    System.out.println("high");
    cameraServo.set(0.7);
  }

  public void camMid() {
    System.out.println("mid");
    cameraServo.set(0.5);
  }

  public void camLow() {
    System.out.println("low");
    cameraServo.set(0.10);
  }

  public double getXOffset() {
    tx = nTable.getEntry("tx");
    return tx.getDouble(0);
  }

  public boolean hasTarget() {
    tv = nTable.getEntry("tv");
    return tv.getBoolean(false);
  }
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new VisionControl());
  }
}
