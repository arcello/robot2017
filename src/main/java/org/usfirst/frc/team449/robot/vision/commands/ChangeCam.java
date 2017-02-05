package org.usfirst.frc.team449.robot.vision.commands;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team449.robot.vision.CameraSubsystem;
import org.usfirst.frc.team449.robot.ReferencingCommand;
import org.usfirst.frc.team449.robot.Robot;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.MjpegServer;

/**
 * Created by bryanli on 12/1/16.
 */
public class ChangeCam extends ReferencingCommand {
    private boolean finished;

    private CameraSubsystem cameraSubsystem;

    public ChangeCam(CameraSubsystem cameraSubsystem, double timeout) {
        super(cameraSubsystem, timeout);
        requires(cameraSubsystem);
        this.cameraSubsystem = cameraSubsystem;
        finished = true;

    }

    @Override
    protected void initialize() {

    	/*cameraSubsystem.server = CameraServer.getInstance();
    	cameraSubsystem.cameras = CameraServer.getInstance().startAutomaticCapture(0);
    	cameraSubsystem.cameras.setResolution(256,144);
    	cameraSubsystem.cameras.setFPS(30);*/
    }

    @Override
    protected void execute() {
        System.out.println("execStart");
		/*SmartDashboard.putNumber("GetHandle",cameraSubsystem.cameras.getHandle());
    	if (cameraSubsystem.cameras.getHandle() == 0){
    	    cameraSubsystem.cameras = CameraServer.getInstance().startAutomaticCapture(1);
    	}
    	else {
    		cameraSubsystem.cameras = CameraServer.getInstance().startAutomaticCapture(0);
	    }*/
        if (cameraSubsystem.camNum == 1){
            cameraSubsystem.camNum = 2;
            cameraSubsystem.server.setSource(cameraSubsystem.cam2);
        }
        else {
            cameraSubsystem.camNum = 1;
            cameraSubsystem.server.setSource(cameraSubsystem.cam1);
        }
        System.out.println("execEnd");
        //cameraSubsystem.server.startAutomaticCapture(cameraSubsystem.cameraNames[cameraSubsystem.sessionPtr]);
    }

    @Override
    protected boolean isFinished(){ return finished; }

    @Override
    protected void end() {}

    @Override
    protected void interrupted(){}
}
