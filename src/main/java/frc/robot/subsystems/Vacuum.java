/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.VacuumDrive;

/**
 * Add your docs here.
 */
public class Vacuum extends Subsystem {
  
  PWMVictorSPX vacuum;

  public Vacuum() {
    vacuum = new PWMVictorSPX(0);
  }

  public void setForward() {
    vacuum.set(1);
  }

  public void setBackwards() {
    vacuum.set(-1);
  }

  public void setValue(double val) {
    vacuum.set(val);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new VacuumDrive());
  }
}
