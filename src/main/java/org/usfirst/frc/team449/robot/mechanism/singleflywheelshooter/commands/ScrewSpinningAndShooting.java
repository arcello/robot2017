package org.usfirst.frc.team449.robot.mechanism.singleflywheelshooter.commands;

import org.usfirst.frc.team449.robot.MappedSubsystem;
import org.usfirst.frc.team449.robot.ReferencingCommandGroup;
import org.usfirst.frc.team449.robot.ReferencingCommand;
import org.usfirst.frc.team449.robot.mechanism.singleflywheelshooter.SingleFlywheelShooter;

/**
 * Created by Justin on 1/31/2017.
 */
public class ScrewSpinningAndShooting extends ReferencingCommandGroup {

	SingleFlywheelShooter flywheelShooter;

	/**
	 * Instantiate the ReferencingCommandGroup
	 *
	 * @param mappedSubsystem the {@link MappedSubsystem}
	 *                        to feed to this
	 *                        {@code
	 *                        ReferencingCommandGroup}'s
	 *                        {@link ReferencingCommand}s
	 */
	public ScrewSpinningAndShooting(MappedSubsystem mappedSubsystem) {
		super(mappedSubsystem);
		flywheelShooter = (SingleFlywheelShooter) mappedSubsystem;
		requires(mappedSubsystem);
		addParallel(new SpinScrewCCW(flywheelShooter));
		addParallel(new AccelerateFlywheel(flywheelShooter, 1));
	}
}
