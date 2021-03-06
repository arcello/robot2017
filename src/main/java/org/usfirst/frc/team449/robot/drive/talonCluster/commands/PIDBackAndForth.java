package org.usfirst.frc.team449.robot.drive.talonCluster.commands;

import org.usfirst.frc.team449.robot.MappedSubsystem;
import org.usfirst.frc.team449.robot.ReferencingCommand;
import org.usfirst.frc.team449.robot.ReferencingCommandGroup;
import org.usfirst.frc.team449.robot.drive.talonCluster.TalonClusterDrive;

/**
 * Created by BlairRobot on 2017-01-13.
 */
public class PIDBackAndForth extends ReferencingCommandGroup {
	/**
	 * Instantiate the ReferencingCommandGroup
	 *
	 * @param mappedSubsystem the {@link MappedSubsystem}
	 *                        to feed to this
	 *                        {@code
	 *                        ReferencingCommandGroup}'s
	 *                        {@link ReferencingCommand}s
	 */
	public PIDBackAndForth(MappedSubsystem mappedSubsystem) {
		super(mappedSubsystem);
		requires(mappedSubsystem);

		TalonClusterDrive driveSubsystem = (TalonClusterDrive) mappedSubsystem;
		double time = 1.5;

		/*
		for (int i = 0; i < 5; i++){
			addSequential(new DriveAtSpeed(driveSubsystem, 1), time);
			addSequential(new DriveAtSpeed(driveSubsystem, 0), time);
			addSequential(new DriveAtSpeed(driveSubsystem, -1), time);
			addSequential(new DriveAtSpeed(driveSubsystem, 0), time);
		}
		*/
		addSequential(new DriveAtSpeed(driveSubsystem, 1), time);
		addSequential(new DriveAtSpeed(driveSubsystem, 0), 100);
	}
}
