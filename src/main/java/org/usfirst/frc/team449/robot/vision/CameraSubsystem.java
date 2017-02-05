package org.usfirst.frc.team449.robot.vision;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
//import edu.wpi.first.wpilibj.CameraServer;

import maps.org.usfirst.frc.team449.robot.vision.CameraMap;
import org.usfirst.frc.team449.robot.MappedSubsystem;

import java.util.Arrays;

/**
 * Created by bryanli on 11/10/16.
 */
public class CameraSubsystem extends MappedSubsystem {

    public int[] sessions;
    public int sessionPtr;
    public int imgQuality;
    public MjpegServer server;
    //public CameraServer server;
    public UsbCamera cam1;
    public UsbCamera cam2;
    public UsbCamera cameras;
    public int camNum;

    public static maps.org.usfirst.frc.team449.robot.vision.CameraMap.CamRobot staticMap;

    public CameraSubsystem(CameraMap.CamRobot map){
        super(map);
        staticMap = map;
        //this.imgQuality = map.getCamera().getImgQuality();
        //this.sessions = Arrays.stream(map.getSessionsList().toArray()).mapToInt(o -> (int)o).toArray();
        System.out.println("initStart");
        System.out.println("Set URL of MJPGServer to `http://roboRIO-449-frc.local:5800/stream.mjpg`");
        server = new MjpegServer("Cameras",/*"MJPGSERVER",*/5800);
        cam1 = new UsbCamera("cam1",0);
        cam1.setResolution(160,120);
        cam1.setFPS(30);
        cam2 = new UsbCamera("cam2",1);
        cam2.setResolution(160,120);
        cam2.setFPS(30);
        server.setSource(cam1);
        camNum = 1;
        System.out.println("initEnd");
    }

    @Override
    protected void initDefaultCommand () {
        // setDefaultCommand(new DefaultCameraGroup(this, oi));
    }

}
