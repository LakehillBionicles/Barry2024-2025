package org.firstinspires.ftc.teamcode.V1;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
@Config
public class hardwareMap {
    public DcMotor fpd = null, bpd = null, fsd = null, bsd = null, portArm = null, starArm = null;
    public Servo shoulderPort = null, shoulderStar = null, extentyBoi = null;
    public CRServo mcLarenDaddy=null;
    HardwareMap hwMap = null;
    public static double shoulderPortDown = 0.455;
    public static double shoulderPortBar = 0.5;
    public static double shoulderPortUp = 0.62;
    public static double shoulderStarDown = 0.545;
    public static double shoulderStarBar = 0.5;
    public static double shoulderStarUp = 0.38;
    public static double extendyBoiExtended = 1;
    public static double extendyBoiRetracted = 0;
    public hardwareMap() {}
    public void runOpMode() {}

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;
        fpd = hwMap.get(DcMotor.class, "fpd" );
        bpd = hwMap.get(DcMotor.class, "bpd");
        fsd = hwMap.get(DcMotor.class, "fsd");
        bsd = hwMap.get(DcMotor.class, "bsd");
        portArm = hwMap.get(DcMotor.class, "pa");
        starArm = hwMap.get(DcMotor.class,"sa");
        shoulderPort = hwMap.get(Servo.class, "portElbow");
        shoulderStar = hwMap.get(Servo.class, "starElbow");
        extentyBoi = hwMap.get(Servo.class, "extendyBoi");
        mcLarenDaddy=hwMap.get(CRServo.class,"hand");
        fpd.setDirection(DcMotorSimple.Direction.REVERSE);
        bpd.setDirection(DcMotorSimple.Direction.REVERSE);
        fsd.setDirection(DcMotorSimple.Direction.FORWARD);
        bsd.setDirection(DcMotorSimple.Direction.FORWARD);

        portArm.setDirection(DcMotorSimple.Direction.REVERSE);
        starArm.setDirection(DcMotorSimple.Direction.FORWARD);
        fpd.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bpd.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fsd.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bsd.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        portArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        starArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        fpd.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bpd.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fsd.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bsd.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        portArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        starArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
