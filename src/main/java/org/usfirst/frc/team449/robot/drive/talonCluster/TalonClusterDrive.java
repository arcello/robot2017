package org.usfirst.frc.team449.robot.drive.talonCluster;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import maps.org.usfirst.frc.team449.robot.components.ToleranceBufferAnglePIDMap;
import maps.org.usfirst.frc.team449.robot.components.UnitlessCANTalonSRXMap;
import org.usfirst.frc.team449.robot.components.NavxSubsystem;
import org.usfirst.frc.team449.robot.components.UnitlessCANTalonSRX;
import org.usfirst.frc.team449.robot.drive.DriveSubsystem;
import org.usfirst.frc.team449.robot.drive.talonCluster.commands.ExecuteProfile;
import org.usfirst.frc.team449.robot.drive.talonCluster.commands.OpTankDrive;
import org.usfirst.frc.team449.robot.drive.talonCluster.commands.PIDTest;
import org.usfirst.frc.team449.robot.drive.talonCluster.commands.ois.TankOI;
import org.usfirst.frc.team449.robot.mechanism.doubleflywheelshooter.commands.PIDTune;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A drive with a cluster of any number of CANTalonSRX controlled motors on each side.
 */
public class TalonClusterDrive extends DriveSubsystem implements NavxSubsystem {

	public UnitlessCANTalonSRX rightMaster;
	public UnitlessCANTalonSRX leftMaster;
	public AHRS navx;
	public ToleranceBufferAnglePIDMap.ToleranceBufferAnglePID turnPID;
	public ToleranceBufferAnglePIDMap.ToleranceBufferAnglePID straightPID;
	public TankOI oi;
	// TODO take this out after testing
	public CANTalon.MotionProfileStatus leftTPointStatus;
	public CANTalon.MotionProfileStatus rightTPointStatus;
	private long startTime;
	private String logFN = "driveLog.csv";
	double maxSpeed;

	public TalonClusterDrive(maps.org.usfirst.frc.team449.robot.drive.talonCluster.TalonClusterDriveMap
			                         .TalonClusterDrive map, TankOI oi) {
		super(map.getDrive());
		this.map = map;
		this.oi = oi;
		this.navx = new AHRS(SPI.Port.kMXP);
		this.turnPID = map.getTurnPID();
		this.straightPID = map.getStraightPID();

		rightMaster = new UnitlessCANTalonSRX(map.getRightMaster());
		leftMaster = new UnitlessCANTalonSRX(map.getLeftMaster());

		for (UnitlessCANTalonSRXMap.UnitlessCANTalonSRX talon : map.getRightSlaveList()) {
			UnitlessCANTalonSRX talonObject = new UnitlessCANTalonSRX(talon);
			talonObject.canTalon.changeControlMode(CANTalon.TalonControlMode.Follower);
			talonObject.canTalon.set(map.getRightMaster().getPort());
		}
		for (UnitlessCANTalonSRXMap.UnitlessCANTalonSRX talon : map.getLeftSlaveList()) {
			UnitlessCANTalonSRX talonObject = new UnitlessCANTalonSRX(talon);
			talonObject.canTalon.changeControlMode(CANTalon.TalonControlMode.Follower);
			talonObject.canTalon.set(map.getLeftMaster().getPort());
		}

		// TODO take this out
		leftTPointStatus = new CANTalon.MotionProfileStatus();
		rightTPointStatus = new CANTalon.MotionProfileStatus();
	}

	/**
	 * Sets the left and right wheel speeds as a voltage percentage, not nearly as precise as PID.
	 *
	 * @param left  The left throttle, a number between -1 and 1 inclusive.
	 * @param right The right throttle, a number between -1 and 1 inclusive.
	 */
	private void setVBusThrottle(double left, double right) {
		leftMaster.setPercentVbus(left);
		rightMaster.setPercentVbus(right);
	}

	private void setPIDThrottle(double left, double right) {
		leftMaster.setSpeed(.7 * (left * leftMaster.getMaxSpeed()));
		rightMaster.setSpeed(.7 * (right * rightMaster.getMaxSpeed()));
	}

	/**
	 * Allows the type of motor control used to be varied in testing.
	 *
	 * @param left  Left throttle value
	 * @param right Right throttle value
	 */
	public void setDefaultThrottle(double left, double right) {
		setVBusThrottle(left, right);
//		setVBusThrottle(1, 1);
	}

	public void logData() {
		try (FileWriter fw = new FileWriter(logFN, true)) {
			StringBuilder sb = new StringBuilder();
			sb.append((System.nanoTime() - startTime) / 100);
			sb.append(",");
			sb.append(leftMaster.canTalon.getEncPosition());
			sb.append(",");
			sb.append(rightMaster.canTalon.getEncPosition());
			sb.append(",");
			sb.append(leftMaster.canTalon.getEncVelocity());
			sb.append(",");
			sb.append(rightMaster.canTalon.getEncVelocity());
			sb.append(",");
			sb.append(leftTPointStatus.activePoint.position);
			sb.append(",");
			sb.append(rightTPointStatus.activePoint.position);
			sb.append("\n");

			fw.write(sb.toString());

			if (leftMaster.getSpeed() > maxSpeed)
				maxSpeed = leftMaster.getSpeed();
			if (rightMaster.getSpeed() > maxSpeed)
				maxSpeed = rightMaster.getSpeed();
			SmartDashboard.putNumber("Left", leftMaster.getSpeed());
			SmartDashboard.putNumber("Right", rightMaster.getSpeed());
			SmartDashboard.putNumber("Max Speed", maxSpeed);
			SmartDashboard.putNumber("Throttle", leftMaster.nativeToRPS(leftMaster.canTalon.getSetpoint()));
			SmartDashboard.putNumber("Heading", navx.pidGet());
			SmartDashboard.putNumber("Left Setpoint", leftMaster.nativeToRPS(leftMaster.canTalon.getSetpoint()));
			SmartDashboard.putNumber("Left Error", leftMaster.nativeToRPS(leftMaster.canTalon.getError()));
			SmartDashboard.putNumber("Right Setpoint", rightMaster.nativeToRPS(rightMaster.canTalon.getSetpoint()));
			SmartDashboard.putNumber("Right Error", rightMaster.nativeToRPS(rightMaster.canTalon.getError()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initDefaultCommand() {
		maxSpeed = -1;
		logFN = "/home/lvuser/driveLog-" + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) + ".csv";
		try (PrintWriter writer = new PrintWriter(logFN)) {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


		setDefaultCommand(new PIDTest(this));
//		setDefaultCommand(new OpTankDrive(this, oi));

		startTime = System.nanoTime();
	}

	public double getGyroOutput() {
		return navx.pidGet();
	}
}
