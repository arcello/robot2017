package org.usfirst.frc.team449.robot.oi;

import edu.wpi.first.wpilibj.Joystick;
import maps.org.usfirst.frc.team449.robot.oi.OI2017ArcadeMap;
import org.usfirst.frc.team449.robot.drive.talonCluster.commands.ois.ArcadeOI;
import org.usfirst.frc.team449.robot.oi.components.PolyThrottle;
import org.usfirst.frc.team449.robot.oi.components.Throttle;

/**
 * Created by blairrobot on 1/9/17.
 */
public class OI2017Arcade extends BaseOI implements ArcadeOI {
	/**
	 * Left (rotation control) stick's throttle
	 */
	private Throttle rotThrottle;
	/**
	 * Right (fwd/rev control) stick's throttle
	 */
	private Throttle velThrottle;

	private OI2017ArcadeMap.OI2017Arcade map;

	public OI2017Arcade(maps.org.usfirst.frc.team449.robot.oi.OI2017ArcadeMap.OI2017Arcade map) {
		this.map = map;

		Joystick _leftStick = new Joystick(map.getLeftStick());
		Joystick _rightStick = new Joystick(map.getRightStick());
		this.rotThrottle = new PolyThrottle(_leftStick, 1, 1);
		this.velThrottle = new PolyThrottle(_rightStick, 1, 1);
	}

	@Override
	public void mapButtons() {
		// Do nothing
	}

	/**
	 * @return rotational velocity component
	 */
	public double getRot() {
		return rotThrottle.getValue();
	}

	/**
	 * @return forward velocity component
	 */
	public double getFwd() {
		return velThrottle.getValue();
	}
}
