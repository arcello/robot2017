package org.usfirst.frc.team449.robot;

import edu.wpi.first.wpilibj.HLUsageReporting;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.util.BaseSystemNotInitializedException;
import maps.org.usfirst.frc.team449.robot.Robot2017Map;
import org.junit.Test;
import org.usfirst.frc.team449.robot.drive.talonCluster.TalonClusterDrive;
import org.usfirst.frc.team449.robot.hack.SubstituteHLUsageReportingInterface;
import org.usfirst.frc.team449.robot.mechanism.climber.ClimberSubsystem;
import org.usfirst.frc.team449.robot.mechanism.singleflywheelshooter.SingleFlywheelShooter;
import org.usfirst.frc.team449.robot.oi.OI2017;

import java.io.IOException;

/**
 * Created by Ryan on 2017-01-27.
 */
public class RobotTest {
	@Test
	public void robotInit() throws Exception {
		System.out.println("Started robotInit");

		HLUsageReporting.SetImplementation(new SubstituteHLUsageReportingInterface());

//		Scheduler.getInstance().enable();

		try {
			Robot2017Map.Robot2017 _cfg = (Robot2017Map.Robot2017) MappedSubsystem.readConfig
					("./src/main/resources/map.cfg", Robot2017Map.Robot2017.newBuilder());

//			new OI2017(_cfg.getOi());

			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("JAVA LIBRARY PATH:");
			System.out.println(System.getProperties().getProperty("java.library.path"));
			System.out.println();
			System.out.println();
			System.out.println();

			System.out.println("STARTING TALON CLUSTER DRIVE INIT");

			new TalonClusterDrive(_cfg.getDrive(), null);

			/*
			try {
				new OI2017(_cfg.getOi());
			} catch (BaseSystemNotInitializedException e) {
			}

			try {
				new TalonClusterDrive(_cfg.getDrive(), null);
			} catch (BaseSystemNotInitializedException e) {
			}

			try {
				new ClimberSubsystem(_cfg.getClimber(), null);
			} catch (BaseSystemNotInitializedException e) {
			}

			//			new DoubleFlywheelShooter(_cfg.getDoubleFlywheelShooter());

			try {
				new SingleFlywheelShooter(_cfg.getShooter());
			} catch (BaseSystemNotInitializedException e) {
			}

			System.out.println("finished");
			*/

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
