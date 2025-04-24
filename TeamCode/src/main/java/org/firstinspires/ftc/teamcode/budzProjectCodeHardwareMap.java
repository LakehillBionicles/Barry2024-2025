package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
public class budzProjectCodeHardwareMap extends LinearOpMode {
public Servo portFingie = null;
public TouchSensor touchie = null;
HardwareMap hwMap = null;
        public budzProjectCodeHardwareMap(){}
    @Override
    public void runOpMode() {}
    public void init(HardwareMap ahwMap) {
            hwMap = ahwMap;
                    portFingie = hwMap.get(Servo.class, "portFingie");
                    touchie = hwMap.get(TouchSensor.class, "touchie");
        }
    }

