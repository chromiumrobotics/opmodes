package com.qualcomm.ftcrobotcontroller.opmodes;


import com.qualcomm.robotcore.*;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Auto Mode
 * <p>
 * Enables the bot to move by its self
 */

public class AutoOp extends LinearOpMode {

        DcMotor motorRight;
        DcMotor motorLeft;
        DcMotor backRight;
        DcMotor backLeft;
        DcMotor armExtend;
        DcMotor armAdjust;
        Servo wingRight;
        Servo wingLeft;
        Servo bdLeft;
        Servo bdRight;
        Servo dumpRotate;
        Servo dumpExtend;

    @Override
    public void runOpMode() throws InterruptedException {


        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        armExtend = hardwareMap.dcMotor.get("armExtend");
        armAdjust = hardwareMap.dcMotor.get("armAdjust");
        wingRight = hardwareMap.servo.get("wingRight");
        wingLeft = hardwareMap.servo.get("wingLeft");
        bdLeft = hardwareMap.servo.get("bdLeft");/*bd is */
        bdRight = hardwareMap.servo.get("bdRight");
        dumpRotate = hardwareMap.servo.get("dumpRotate");
        dumpExtend = hardwareMap.servo.get("dumpExtend");

        double s = .2;

        waitForStart();

        long startTime = System.currentTimeMillis();

        motorRight.setPower(s);
        motorLeft.setPower(s);
        backLeft.setPower(s);
        backRight.setPower(s);


        while(System.currentTimeMillis() <= startTime+5350){

        }

        motorRight.setPower(0);
        motorLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);


    }
}