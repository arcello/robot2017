package org.usfirst.frc.team449.robot.drive.talonCluster.commands;

import org.usfirst.frc.team449.robot.MappedSubsystem;
import org.usfirst.frc.team449.robot.ReferencingCommand;
import org.usfirst.frc.team449.robot.drive.talonCluster.TalonClusterDrive;

/**
 * Created by BlairRobot on 2017-02-09.
 */
public class SwitchToHighGear extends ReferencingCommand{
	public SwitchToHighGear(MappedSubsystem subsystem) {
		super(subsystem);
	}

	@Override
	protected void initialize(){
		//Do nothing!
	}

	@Override
	protected void execute() {
		((TalonClusterDrive) subsystem).setSmallGear(true);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		//Do nothing!
	}

	@Override
	protected void interrupted() {
		System.out.println("SwitchToHighGear Interrupted!");
	}
}