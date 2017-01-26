package org.usfirst.frc.team449.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import maps.org.usfirst.frc.team449.robot.Robot2017Map;
import org.usfirst.frc.team449.robot.drive.talonCluster.TalonClusterDrive;
import org.usfirst.frc.team449.robot.mechanism.climber.ClimberSubsystem;
import org.usfirst.frc.team449.robot.mechanism.doubleflywheelshooter.DoubleFlywheelShooter;
import org.usfirst.frc.team449.robot.oi.BaseOI;
import org.usfirst.frc.team449.robot.oi.OI2017;
import org.usfirst.frc.team449.robot.oi.OI2017Arcade;
import org.usfirst.frc.team449.robot.oi.OI2017Tank;

import java.io.IOException;

/**
 * Created by BlairRobot on 2017-01-08.
 */
public class Robot extends IterativeRobot {

	public static DoubleFlywheelShooter shooterSubsystem;

	public static ClimberSubsystem climberSubsystem;

	public static TalonClusterDrive driveSubsystem;

//	public static OI2017Arcade oiSubsystem;

	public static BaseOI oi;

	private static maps.org.usfirst.frc.team449.robot.Robot2017Map.Robot2017 cfg;

	public void robotInit() {
		System.out.println("Started robotInit");
		try {
			cfg = (Robot2017Map.Robot2017) MappedSubsystem.readConfig("/home/lvuser/map.cfg", Robot2017Map.Robot2017.newBuilder());
		} catch (IOException e) {
			e.printStackTrace();
		}

		oi = new OI2017Tank(cfg.getTankOi());
		System.out.println("Constructed OI");
//		climberSubsystem = new ClimberSubsystem(cfg.getClimber(), oi);
		driveSubsystem = new TalonClusterDrive(cfg.getDrive(), (OI2017Tank) oi);
//		shooterSubsystem = new DoubleFlywheelShooter(cfg.getShooter());
		System.out.println("Constructed DoubleFlywheelShooter");

		oi.mapButtons();
		System.out.println("Mapped buttons");
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
}