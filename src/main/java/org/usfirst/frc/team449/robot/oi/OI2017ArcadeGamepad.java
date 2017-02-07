package org.usfirst.frc.team449.robot.oi;

import edu.wpi.first.wpilibj.Joystick;
import maps.org.usfirst.frc.team449.robot.oi.OI2017ArcadeGamepadMap;
import maps.org.usfirst.frc.team449.robot.oi.OI2017ArcadeMap;
import maps.org.usfirst.frc.team449.robot.oi.OI2017Map;
import org.usfirst.frc.team449.robot.drive.talonCluster.commands.ois.ArcadeOI;
import org.usfirst.frc.team449.robot.oi.components.PolyThrottle;
import org.usfirst.frc.team449.robot.oi.components.Throttle;

/**
 * An OI for using an Xbox-style controller for an arcade drive, where one stick controls forward velocity and the other
 * controls turning velocity.
 */
public class OI2017ArcadeGamepad implements ArcadeOI {

	//How much the D-pad moves the robot rotationally on a 0 to 1 scale, equivalent to pushing the turning stick that
	// much of the way.
	private static final double SHIFT = 0.1;
	private static final double joystickDeadband = 0;
	//The throttle wrapper for the stick controlling turning velocity.
	private Throttle turnThrottle;
	//The throttle wrapper for the stick controlling linear velocity.
	private Throttle velThrottle;

	private Joystick gamepad;

	private OI2017ArcadeGamepadMap.OI2017ArcadeGamepad map;

	public OI2017ArcadeGamepad(maps.org.usfirst.frc.team449.robot.oi.OI2017ArcadeGamepadMap.OI2017ArcadeGamepad map) {
		this.map = map;

		gamepad = new Joystick(map.getGamepad());

		this.turnThrottle = new PolyThrottle(gamepad, map.getRotStick(), 1);
		this.velThrottle = new PolyThrottle(gamepad, map.getFwdStick(), 1);
	}

	/**
	 * The output of the throttle controlling linear velocity, smoothed and adjusted according to what type of
	 * joystick it is.
	 *
	 * @return The processed stick output, sign-adjusted so 1 is forward and -1 is backwards.
	 */
	public double getFwd() {
		return -velThrottle.getValue();
	}

	/**
	 * Get the output of the D-pad or turning joystick, whichever is in use. If both are in use, the D-pad takes
	 * preference.
	 *
	 * @return The processed stick or D-pad output, sign-adjusted so 1 is right and -1 is left.
	 */
	public double getRot() {
		if ((gamepad.getPOV() == -1 || gamepad.getPOV() % 180 == 0) && Math.abs(turnThrottle.getValue()) >
				joystickDeadband) {
			return turnThrottle.getValue();
		} else if (!(gamepad.getPOV() == -1 || gamepad.getPOV() % 180 == 0)) {
			return gamepad.getPOV() < 180 ? SHIFT : -SHIFT;
		} else {
			return 0;
		}
	}

	/**
	 * Simple helper function for clipping output to the -1 to 1 scale.
	 *
	 * @param in The number to be processed.
	 * @return That number, clipped to 1 if it's greater than 1 or clipped to -1 if it's less than -1.
	 */
	private static double clipToOne(double in) {
		if (in > 1)
			return 1;
		else if (in < -1)
			return -1;
		else
			return in;
	}

	public void mapButtons() {
	}
}
