package org.firstinspires.ftc.teamcode.V1.TeleOp;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.V1.hardwareMap;

import static org.firstinspires.ftc.teamcode.V1.hardwareMap.extendyBoiExtended;
import static org.firstinspires.ftc.teamcode.V1.hardwareMap.extendyBoiRetracted;
import static org.firstinspires.ftc.teamcode.V1.hardwareMap.shoulderPortBar;
import static org.firstinspires.ftc.teamcode.V1.hardwareMap.shoulderPortDown;
import static org.firstinspires.ftc.teamcode.V1.hardwareMap.shoulderPortUp;
import static org.firstinspires.ftc.teamcode.V1.hardwareMap.shoulderStarBar;
import static org.firstinspires.ftc.teamcode.V1.hardwareMap.shoulderStarDown;
import static org.firstinspires.ftc.teamcode.V1.hardwareMap.shoulderStarUp;

@Config
@TeleOp
public class masterTeleOp extends LinearOpMode {
    public hardwareMap robot = new hardwareMap();
    Gamepad currentGamepad1 = new Gamepad();
    Gamepad currentGamepad2 = new Gamepad();
    Gamepad previousGamepad1 = new Gamepad();
    Gamepad previousGamepad2 = new Gamepad();
    public static double shoulderPortPos = 0.5;
    public static double shoulderStarPos = 0.5;
    public static double extendyBoiPos = 0.1;
    public double leftHigh = -10000;
    public double drivePower = 1;
    @Override
    public void runOpMode(){
        robot.init(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {
            previousGamepad1.copy(currentGamepad1);
            previousGamepad2.copy(currentGamepad2);
            currentGamepad1.copy(gamepad1);
            currentGamepad2.copy(gamepad2);
            if(!previousGamepad1.a && currentGamepad1.a){
                if(robot.mcLarenDaddy.getPower()>0){
                    robot.mcLarenDaddy.setPower(0);
                }else{
                    robot.mcLarenDaddy.setPower(1);
                }
            }else if(!previousGamepad1.b && currentGamepad1.b){
                if(robot.mcLarenDaddy.getPower()<0){
                    robot.mcLarenDaddy.setPower(0);
                }else{
                    robot.mcLarenDaddy.setPower(-1);
                }
            }
            robot.fpd.setPower((-gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x) * drivePower);
            robot.bpd.setPower((-gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x) * drivePower);
            robot.fsd.setPower((-gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) * drivePower);
            robot.bsd.setPower((-gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x) * drivePower);
            robot.portArm.setPower(gamepad1.right_trigger-gamepad1.left_trigger);
            robot.starArm.setPower(gamepad1.right_trigger-gamepad1.left_trigger);
            /*
            if(robot.portArm.getCurrentPosition()<leftHigh){
                robot.portArm.setPower(-0.1);
                robot.starArm.setPower(-0.1);
            }else{

             */
            robot.portArm.setPower(gamepad1.right_trigger-gamepad1.left_trigger+0.15);
            robot.starArm.setPower(gamepad1.right_trigger-gamepad1.left_trigger+0.15);
            //}
            if (gamepad1.dpad_up){
                robot.shoulderPort.setPosition(shoulderPortUp);
                robot.shoulderStar.setPosition(shoulderStarUp);
            } else if (gamepad1.dpad_down) {
                robot.shoulderPort.setPosition(shoulderPortDown);
                robot.shoulderStar.setPosition(shoulderStarDown);
            } else if(gamepad1.dpad_right){
                robot.shoulderPort.setPosition(shoulderPortBar);
                robot.shoulderStar.setPosition(shoulderStarBar);
            }
            if (gamepad1.right_bumper){
               robot.extentyBoi.setPosition(extendyBoiExtended);
            } else if (gamepad1.left_bumper) {
                robot.extentyBoi.setPosition(extendyBoiRetracted);
            }
            telemetry.addData("portArm", robot.portArm.getCurrentPosition());
            telemetry.update();
        }
}
}
