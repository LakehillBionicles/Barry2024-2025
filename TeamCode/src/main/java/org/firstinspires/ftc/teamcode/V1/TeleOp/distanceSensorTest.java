package org.firstinspires.ftc.teamcode.V1.TeleOp;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorMROpticalDistance;
import org.firstinspires.ftc.robotcontroller.external.samples.SensorMRRangeSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
@TeleOp
public class distanceSensorTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        AnalogInput distanceSensor = hardwareMap.get(AnalogInput.class, "distanceSensor");
        waitForStart();
        while (opModeIsActive()){
         telemetry.addData("voltage", distanceSensor.getVoltage());
         telemetry.addData("Inch", (distanceSensor.getVoltage()*32.5)-2.6-2.21);
         telemetry.update();
        }
    }
}
