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
	private Throttle leftThrottle;
	/**
	 * Right (fwd/rev control) stick's throttle
	 */
	private Throttle rightThrottle;

	private OI2017ArcadeMap.OI2017Arcade map;

	public OI2017Arcade(maps.org.usfirst.frc.team449.robot.oi.OI2017ArcadeMap.OI2017Arcade map) {
		this.map = map;

		Joystick _leftStick = new Joystick(map.getLeftStick());
		Joystick _rightStick = new Joystick(map.getRightStick());
		this.leftThrottle = new PolyThrottle(_leftStick, 1, 1);
		this.rightThrottle = new PolyThrottle(_rightStick, 1, 1);
	}

	@Override
	public void mapButtons() {
		// Do nothing
	}

	/**
	 * @return rotational velocity component
	 */
	@Override
	public double getRot() {
		return leftThrottle.getValue();
	}

	/**
	 * @return forward velocity component
	 */
	@Override
	public double getFwd() {
		return rightThrottle.getValue();
	}
}
