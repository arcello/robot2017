package org.usfirst.frc.team449.robot.mechanism.singleflywheelshooter.commands;

import org.usfirst.frc.team449.robot.MappedSubsystem;
import org.usfirst.frc.team449.robot.ReferencingCommand;
import org.usfirst.frc.team449.robot.mechanism.singleflywheelshooter.SingleFlywheelShooter;

/**
 * Created by Justin on 1/31/2017.
 */
public class SpinScrewCCW extends ReferencingCommand {

	SingleFlywheelShooter flywheelShooter;

	/**
	 * <p>
	 * Instantiates a new <code>ReferencingCommand</code> with a given <code>Robot.java</code> class. This is used by
	 * build season code commands calling these library commands with a separate <code>Robot.java</code> not in the
	 * 449 central repo.
	 * </p>
	 *
	 * @param subsystem the subsystem that the <code>ReferencingCommand</code> belongs to
	 */
	public SpinScrewCCW(MappedSubsystem subsystem) {
		super(subsystem);
		flywheelShooter = (SingleFlywheelShooter) subsystem;
		requires(subsystem);
	}

	@Override
	protected void initialize() {
		System.out.println("SpinScrewCCW init");
	}

	@Override
	protected void execute() {
		flywheelShooter.setScrewVbusSpeed(1);
		System.out.println("SpinScrewCCW executed");
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		flywheelShooter.setScrewVbusSpeed(0);
		System.out.println("SpinScrewCCW end");
	}

	@Override
	protected void interrupted() {
		flywheelShooter.setScrewVbusSpeed(0);
		System.out.println("SpinScrewCCW interrupted, stopping screw.");
	}
}
