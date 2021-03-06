package org.usfirst.frc.team449.robot.drive.talonCluster.commands.ois;

/**
 * Created by Ryan on 2017-01-29.
 */
public interface ArcadeOI {
	/**
	 * @return rotational velocity component
	 */
	double getRot();

	/**
	 * @return forward velocity component
	 */
	double getFwd();
}
