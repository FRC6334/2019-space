/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.RobotMap;

public class ArmDrive extends CommandBase {
    Joystick auxJoystick;

    public ArmDrive() {
        super("ArmDrive");
        requires(arm);
        requires(grabber);

        auxJoystick = OI.getAuxStick();
    }

    @Override protected void initialize() { System.out.println("climberdrive init"); }

    @Override
    protected void execute() {
        if (auxJoystick.getRawButtonPressed(1)) {
            grabber.toggle();
            System.out.println("Toggle grabber");
        }

        if (auxJoystick.getRawButtonPressed(10)) arm.resetArm();

        if ( Math.abs( auxJoystick.getY() ) <= 0.05 ) {
            arm.driveArm(0);
        } else {
            arm.driveArm(auxJoystick.getY());
        }
    }

    @Override 
    public boolean isFinished() { return false; }

    @Override 
    protected void end(){}

    @Override 
    protected void interrupted() { System.out.println("ArmDrive interrupted"); }
}