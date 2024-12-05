package org.firstinspires.ftc.teamcode.V1.Auto;
import static org.firstinspires.ftc.teamcode.V1.hardwareMap.bucketBar;
import static org.firstinspires.ftc.teamcode.V1.hardwareMap.extendyBoiExtended;
import static org.firstinspires.ftc.teamcode.V1.hardwareMap.extendyBoiRetracted;
import static org.firstinspires.ftc.teamcode.V1.hardwareMap.intakePower;
import static org.firstinspires.ftc.teamcode.V1.hardwareMap.outakePower;
import static org.firstinspires.ftc.teamcode.V1.hardwareMap.shoulderPortBar;
import static org.firstinspires.ftc.teamcode.V1.hardwareMap.shoulderPortDown;
import static org.firstinspires.ftc.teamcode.V1.hardwareMap.shoulderPortUp;
import static org.firstinspires.ftc.teamcode.V1.hardwareMap.shoulderStarBar;
import static org.firstinspires.ftc.teamcode.V1.hardwareMap.shoulderStarDown;
import static org.firstinspires.ftc.teamcode.V1.hardwareMap.shoulderStarUp;
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
        robot.extendyBoi.setTargetPosition(80);
        robot.extendyBoi.setPower(extendyBoiPower);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        TrajectorySequence goingUpToBar = drive.trajectorySequenceBuilder(startPose)
                .lineToLinearHeading(startPose.plus(new Pose2d(25, 25.3, Math.toRadians(0))))
                .build();
        TrajectorySequence goingUpToBarTwo = drive.trajectorySequenceBuilder(goingUpToBar.end())
                .back(5)
                .build();

        TrajectorySequence goingUpToSpecimens = drive.trajectorySequenceBuilder(goingUpToBarTwo.end())
                .forward(10)//pulling away from bar//
                .turn(Math.toRadians(-90))//turning to specimens//
                .build();

        TrajectorySequence approachingSpecimens = drive.trajectorySequenceBuilder(goingUpToSpecimens.end())
                .lineToLinearHeading(startPose.plus(new Pose2d(-5, 20, Math.toRadians(-90))))//driving to specimens//
                .build();
        TrajectorySequence intakingFirstSample = drive.trajectorySequenceBuilder(approachingSpecimens.end())
                .lineToLinearHeading(startPose.plus(new Pose2d(-7, 37, Math.toRadians(-90))))
                .build();

        TrajectorySequence depositingSpecimens = drive.trajectorySequenceBuilder(intakingFirstSample.end())
                .waitSeconds(2)
                .lineToLinearHeading(startPose.plus(new Pose2d(-13, -2, Math.toRadians(-90))))//turning to deposit first specimen//
                .build();
        TrajectorySequence depositingSecondSpecimen = drive.trajectorySequenceBuilder(depositingSpecimens.end())//to note here is that the position to actually pick up the specimens is NOT fulfilled
                .lineToLinearHeading(startPose.plus(new Pose2d(-10, 33, Math.toRadians(-90))))//picking up second specimen//
                .build();
        TrajectorySequence pullingBack = drive.trajectorySequenceBuilder(depositingSecondSpecimen.end())
                .back(3)
                .build();
        TrajectorySequence depositingSecondSpecimenFulfilled = drive.trajectorySequenceBuilder(pullingBack.end())
                .lineToLinearHeading(startPose.plus(new Pose2d(-10, 37, Math.toRadians(-90))))
                .lineToLinearHeading(startPose.plus(new Pose2d(0, 35)))
                .build();

        TrajectorySequence lowLevelAscent = drive.trajectorySequenceBuilder(depositingSecondSpecimenFulfilled.end())
                .lineToLinearHeading(startPose.plus(new Pose2d(-10, 55, Math.toRadians(-90))))//low level ascent//
                .back(18)//low level ascent completed//
                .build();


        waitForStart();//the beginning of the actual code itself
        if (isStopRequested()) return;
        drive.setPoseEstimate(startPose);
        drive.followTrajectorySequence(goingUpToBar);//Adrian would have been here, but he saw a cool car, vroom vroom
        resetRuntime();
        while (getRuntime() < 3) {
            robot.portArm.setPower((double) (twoArmBar - robot.portArm.getCurrentPosition()) / ((twoArmBar * 0.5) / 4));
            robot.starArm.setPower((double) (twoArmBar - robot.portArm.getCurrentPosition()) / ((twoArmBar * 0.5) / 4));

        }

        //scooch back
        drive.followTrajectorySequence(goingUpToBarTwo); //Eli Was Here

        resetRuntime();
        while (getRuntime() < 2) {
            robot.portArm.setPower(-0.5);
            robot.starArm.setPower(-0.5);
        }
        drive.followTrajectorySequence(goingUpToSpecimens);
        drive.followTrajectorySequence(approachingSpecimens);
        resetRuntime();
        robot.extendyBoi.setTargetPosition(extendyBoiExtended / 2);
        robot.extendyBoi.setPower(extendyBoiPower/2);
        robot.mcLarenDaddy.setPower(intakePower);
        sleep(1000);


        drive.followTrajectorySequence(intakingFirstSample);


            robot.extendyBoi.setTargetPosition(extendyBoiRetracted+30);//30 encoder ticks

        sleep(3000);
        robot.shoulderPort.setPosition(shoulderPortBar);
        robot.shoulderStar.setPosition(shoulderStarBar);
        drive.followTrajectorySequence(depositingSpecimens);
        resetRuntime();
        while (getRuntime()<4){
            robot.portArm.setPower((double) (bucketBar - robot.portArm.getCurrentPosition()) / ((bucketBar * 0.5) / 2));
            robot.starArm.setPower((double) (bucketBar - robot.portArm.getCurrentPosition()) / ((bucketBar * 0.5) / 2));

        }
        robot.extendyBoi.setTargetPosition(extendyBoiExtended);
        sleep(1500);
        robot.mcLarenDaddy.setPower(outakePower);
        drive.followTrajectorySequence(pullingBack);
        resetRuntime();
        sleep(2000);
        robot.extendyBoi.setTargetPosition(extendyBoiRetracted);//QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ
        robot.portArm.setPower(-1);
        robot.starArm.setPower(-1);
        sleep(500);


        drive.followTrajectorySequence(depositingSecondSpecimen);
        resetRuntime();
        while (getRuntime() < 2)
            robot.extendyBoi.setTargetPosition(extendyBoiExtended / 3);
        robot.extendyBoi.setPower(extendyBoiPower);
        robot.mcLarenDaddy.setPower(intakePower);

        drive.followTrajectorySequence(depositingSecondSpecimenFulfilled);
        while (getRuntime() < 3)
            robot.extendyBoi.setTargetPosition(extendyBoiRetracted);
        while (getRuntime() > 3)
            robot.portArm.setPower((double) (twoArmBar - robot.portArm.getCurrentPosition()) / ((twoArmBar * 0.5) / 2));
        robot.starArm.setPower((double) (twoArmBar - robot.portArm.getCurrentPosition()) / ((twoArmBar * 0.5) / 2));
        robot.mcLarenDaddy.setPower(outakePower);
        while (getRuntime() > 7) {
            robot.portArm.setPower(-0.5);
            robot.starArm.setPower(-0.5);
            drive.followTrajectorySequence(lowLevelAscent);
            sleep(1000);


        }
    }
}


