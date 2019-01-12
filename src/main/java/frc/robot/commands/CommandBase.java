/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveTrain;
import frc.robot.OI;


public class CommandBase extends Command {

  public static OI oi;
  public static DriveTrain driveTrain;

  // Init all subsystems
  public static void init() {
    System.out.println("CommandBase init");
    // Instantiate all subsystems
    driveTrain = new DriveTrain();

    OI.init();
  }

  public CommandBase(String name) {
    super(name);
  }

  public boolean isFinished() {
    return false;
  }
}
