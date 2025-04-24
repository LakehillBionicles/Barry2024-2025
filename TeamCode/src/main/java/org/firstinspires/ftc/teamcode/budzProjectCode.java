package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.TouchSensor;
@TeleOp
public class budzProjectCode extends LinearOpMode {
    public TouchSensor touchie = null;
        budzProjectCodeHardwareMap robot = new budzProjectCodeHardwareMap();
public void runOpMode() {
        robot.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.b) {
                robot.portFingie.setPosition(0);
            }
            if (touchie.isPressed()) {
                robot.portFingie.setPosition(1);
            }
        }
}
}



