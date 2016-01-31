package com.qualcomm.ftcrobotcontroller.opmodes1;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;



public class AutoOp extends LinearOpMode {

        DcMotor motorRight, motorLeft, backRight, backLeft, armExtend, armAdjust;
        Servo wingRight, wingLeft, bd, dumpRotate, dumpExtend;

    @Override
    public void runOpMode() throws InterruptedException {

        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        armExtend = hardwareMap.dcMotor.get("armExtend");
        armAdjust = hardwareMap.dcMotor.get("armAdjust");

        wingRight = hardwareMap.servo.get("wingRight");
        wingLeft = hardwareMap.servo.get("wingLeft");

        bd = hardwareMap.servo.get("bd");

        dumpRotate = hardwareMap.servo.get("dumpRotate");
        dumpExtend = hardwareMap.servo.get("dumpExtend");

        final double s = .2; // general motor speed

        waitForStart();

        long startTime = System.currentTimeMillis();

        motorRight.setPower(s);
        motorLeft.setPower(s);
        backLeft.setPower(s);
        backRight.setPower(s);

        while(System.currentTimeMillis() <= startTime+5350){} // 6.5 ft

        motorRight.setPower(0);
        motorLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);


    }
}