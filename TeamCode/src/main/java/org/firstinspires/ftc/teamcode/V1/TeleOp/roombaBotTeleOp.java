package org.firstinspires.ftc.teamcode.V1.TeleOp;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.V1.hardwareMap;

public class roombaBotTeleOp extends teleBase{
    public org.firstinspires.ftc.teamcode.V1.hardwareMap robot = new hardwareMap();
    Gamepad currentGamepad1 = new Gamepad();
    Gamepad currentGamepad2 = new Gamepad();
    Gamepad previousGamepad1 = new Gamepad();
    Gamepad previousGamepad2 = new Gamepad();
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {

        }
    }
}
