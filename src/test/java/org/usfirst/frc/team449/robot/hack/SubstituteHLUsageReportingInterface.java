package org.usfirst.frc.team449.robot.hack;

import edu.wpi.first.wpilibj.HLUsageReporting;

/**
 * Created by Ryan on 2017-01-27.
 */
public class SubstituteHLUsageReportingInterface implements HLUsageReporting.Interface {
	@Override
	public void reportScheduler() {
		// Do nothing
	}

	@Override
	public void reportPIDController(int num) {
		// Do nothing
	}

	@Override
	public void reportSmartDashboard() {
		// Do nothing
	}
}
