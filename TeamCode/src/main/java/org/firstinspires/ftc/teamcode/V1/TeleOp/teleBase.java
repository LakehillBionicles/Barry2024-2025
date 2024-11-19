package org.firstinspires.ftc.teamcode.V1.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

public class teleBase extends LinearOpMode {
    protected final Object lock = new Object();
    @Override
    public void runOpMode() throws InterruptedException {

    }
    /**
     *This method is for automating TeleOp
     *It returns a boolean which you can you instead of a while loop
     *Time 1 is the time after the button is pressed for the method to activate.
     *If it happens imediately after button then put in 0
     *Time 2 is the time after the button is pressed for the method to turn off.
     *The comparison is equal to a variable which you set to runtime
     *if(!previousGamepad1.dpad_left && currentGamepad1.dpad_left){
     *                 grabTime = getRuntime();}
     *else if(previousGamepad1.dpad_left && !currentGamepad1.dpad_left){
     *                 grabTime = -2000;}
     *This is a good method for setting a variable to runtime.
     *For runtime put in getRuntime(); or a varible which equals getRuntime();
     *I generally use the variable method as it's more efficient if your calling getRuntime() alot
     */
    public Boolean timerCheck (double comparison,double time1, double time2,double runtime) {
        synchronized (lock) {
            if (time2 < time1) throw new IllegalArgumentException("time 2 is greater than time 1");
            return comparison <= (runtime - time1) && comparison > (runtime - time2);
        }
    }

    /**
     * WARNING WARNING!!!feedforward is not the power to keep ArmUp!!!WARNING WARNING
     * @param arm1
     * @param arm2
     * @param feedForward1 This is an overshoot for your arm to stay up, basically the weight of the arm will pull it down so we need to set a higher target
     * @param feedForward2
     * @param arm1Tar
     * @param arm2Tar
     */
    public void armToPosition(DcMotorEx arm1, DcMotorEx arm2, Integer feedForward1, Integer feedForward2, Integer arm1Tar, Integer arm2Tar, double power1, double power2){
        arm1Tar= arm1Tar + feedForward1;
        arm2Tar= arm2Tar + feedForward2;
        arm1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm1.setTargetPosition(arm1Tar);
        arm2.setTargetPosition(arm2Tar);
        arm1.setPower(power1);
        arm2.setPower(power2);
    }

    public Boolean toggle(double currentPosition, double targetPosition, Boolean previousGamepad1, Boolean curentGamepad1){
        if(!previousGamepad1&&curentGamepad1){
            return true;
        }else{
            return false;
        }
    }
    public Boolean singlePress(Boolean previousGamepad1, Boolean curentGamepad1){
        if(!previousGamepad1&&curentGamepad1){
            return true;
        }else{
            return false;
        }
    }

    /**
     * just put in the current gamepad
     * Ex: currentGamepad1.right_bumper
     * @param curentGamepad1 current gamepad
     * @return
     */
    public Boolean hold(Boolean curentGamepad1){
        return curentGamepad1;
    }
    /**
     *
     * @param previousGamepad1 This is equal to your 1st previous gamepad. Insanity I know.
     * @param previousGamepad2 This is equal to your 2nd previous gamepad. This is code getting even crazier
     * @param currentGamepad1  This is equal to your 1st gampad controls active at the current moment
     * @param currentGamepad2  This is equal to your 2nd gampad controls active at the current moment
     * @param gamepad1 This is the variable which interacts with the driver hub no touchy
     * @param gamepad2 This is the variable which interacts with the driver hub no touchy
     * This method is needed to be able to call other methods regarding controllers
     * Only call this once at the begining of your whille
     * Just steal the parameters from my previous code its easier that way
     */
    public void copyGamepad(Gamepad previousGamepad1 ,Gamepad previousGamepad2, Gamepad currentGamepad1, Gamepad currentGamepad2, Gamepad gamepad1, Gamepad gamepad2){
        previousGamepad1.copy(currentGamepad1);//makes previousGamepad equal to the gamepad from the last opmode
        previousGamepad2.copy(currentGamepad2);
        currentGamepad1.copy(gamepad1);//makes currentGamepad equal to the actual current gamepad controls
        currentGamepad2.copy(gamepad2);
    }
}