package org.usfirst.frc.team449.robot.mechanism.doubleflywheelshooter.commands;

import org.usfirst.frc.team449.robot.MappedSubsystem;
import org.usfirst.frc.team449.robot.ReferencingCommandGroup;
import org.usfirst.frc.team449.robot.mechanism.doubleflywheelshooter.DoubleFlywheelShooter;

/**
 * Created by blairrobot on 1/10/17.
 */
public class ToggleFlywheel extends ReferencingCommandGroup {

	private DoubleFlywheelShooter shooterSubsystem;

	public ToggleFlywheel(MappedSubsystem subsystem) {
		super(subsystem);
		requires(subsystem);
		shooterSubsystem = (DoubleFlywheelShooter) subsystem;

		if (shooterSubsystem.spinning)
			addSequential(new DecelerateFlywheel(shooterSubsystem, 1));
		else
			addSequential(new AccelerateFlywheel(shooterSubsystem, 1));
	}
}
