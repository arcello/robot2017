package org.usfirst.frc.team449.robot

import org.junit.Test

/**
 * Created by Ryan on 2017-01-27.
 */
class RobotTest extends GroovyTestCase {
	@Test
	void testRobotInit() {
		Robot r17 = new Robot();
		r17.robotInit();
	}

	@Test
	void testTeleopPeriodic() {
		// Do nothing
	}
}
