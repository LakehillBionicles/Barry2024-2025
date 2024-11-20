package org.firstinspires.ftc.teamcode.V1.Auto;
import static org.firstinspires.ftc.teamcode.V1.hardwareMap.extendyBoiExtended;
import static org.firstinspires.ftc.teamcode.V1.hardwareMap.extendyBoiRetracted;
import static org.firstinspires.ftc.teamcode.V1.hardwareMap.intakePower;
import static org.firstinspires.ftc.teamcode.V1.hardwareMap.outakePower;
import static org.firstinspires.ftc.teamcode.V1.hardwareMap.shoulderPortBar;
import static org.firstinspires.ftc.teamcode.V1.hardwareMap.shoulderPortDown;
import static org.firstinspires.ftc.teamcode.V1.hardwareMap.shoulderStarBar;
import static org.firstinspires.ftc.teamcode.V1.hardwareMap.shoulderStarDown;
import static org.firstinspires.ftc.teamcode.V1.hardwareMap.twoArmBar;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.V1.TeleOp.teleBase;
import org.firstinspires.ftc.teamcode.V1.hardwareMap;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.RoadRunner.trajectorysequence.trajectorysequence.TrajectorySequence;
@Autonomous
public class austinRutherfordIsNerd extends teleBase {
    public org.firstinspires.ftc.teamcode.V1.hardwareMap robot = new hardwareMap();
    public double extendyBoiPower = -1;
    Pose2d startPose = new Pose2d(0, 0, Math.toRadians(-90));

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        robot.shoulderStar.setPosition(shoulderStarDown);
        robot.shoulderPort.setPosition(shoulderPortDown);
        robot.extendyBoi.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.extendyBoi.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.portArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.portArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.portArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.extendyBoi.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.extendyBoi.setTargetPosition(0);
        robot.extendyBoi.setPower(extendyBoiPower);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        TrajectorySequence goingUpToBar=drive.trajectorySequenceBuilder(startPose)
                .lineToLinearHeading(startPose.plus(new Pose2d(25, 28, Math.toRadians(0))))
                .build();
        TrajectorySequence goingUpToSpecimens= drive.trajectorySequenceBuilder(goingUpToBar.end())
                .forward(10)//pulling away from bar//
                .turn(Math.toRadians(-90))//turning to specimens//
                .build();
        TrajectorySequence thing= drive.trajectorySequenceBuilder(goingUpToSpecimens.end())
                .lineToLinearHeading(startPose.plus(new Pose2d(-20,18, Math.toRadians(-90))))//driving to specimens//
                .build();
        TrajectorySequence depositingSpecimens= drive.trajectorySequenceBuilder(thing.end())
                .lineToLinearHeading(startPose.plus(new Pose2d(-44,0,Math.toRadians(120))))//picking up first specimen//
                .lineToLinearHeading(startPose.plus(new Pose2d(-58,-54, Math.toRadians(-125))))//depositing first specimen//
                .build();
        TrajectorySequence depositingSecondSpecimen= drive.trajectorySequenceBuilder(depositingSpecimens.end())
                .lineToLinearHeading(startPose.plus(new Pose2d(-50,-34,Math.toRadians(120))))//picking up second specimen//
                .lineToLinearHeading(startPose.plus(new Pose2d(-58, -54,Math.toRadians(-125))))//scoring second specimen//
                .build();
        TrajectorySequence lowLevelAscent= drive.trajectorySequenceBuilder(depositingSecondSpecimen.end())
                .lineToLinearHeading(startPose.plus(new Pose2d(-34,0,Math.toRadians(180))))//low level ascent//
                .back(10)//low level ascent completed//
                .build();



        waitForStart();
        if (isStopRequested()) return;
        drive.setPoseEstimate(startPose);
        drive.followTrajectorySequence(goingUpToBar);
        drive.followTrajectorySequence(goingUpToSpecimens);
        telemetry.addData("errr",drive.getLastError());
        telemetry.update();
        drive.followTrajectorySequence(thing);
        drive.followTrajectorySequence(depositingSpecimens);
        drive.followTrajectorySequence(depositingSecondSpecimen);
        sleep(1000);


    }
};


