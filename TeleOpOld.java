package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class TeleOpOld extends OpMode {

    public TeleOpOld() {}

    DcMotor motorRight, motorLeft, backRight, backLeft, armExtend, armAdjust;
    Servo wingRight, wingLeft, bdLeft, bdRight, dumpRotate, dumpExtend;

    @Override
    public void init() {

        motorRight = hardwareMap.dcMotor.get("motorRight"); // Front Tracks
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        backRight = hardwareMap.dcMotor.get("backRight"); // Back Wheels
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        armExtend = hardwareMap.dcMotor.get("armExtend"); // Lifts robot
        armAdjust = hardwareMap.dcMotor.get("armAdjust"); // Arm angle

        wingRight = hardwareMap.servo.get("wingRight"); // *for zip-line climbers*
        wingLeft = hardwareMap.servo.get("wingLeft");

        bdLeft = hardwareMap.servo.get("bdLeft"); // pushing blocks
        bdRight = hardwareMap.servo.get("bdRight");

        dumpRotate = hardwareMap.servo.get("dumpRotate"); // Dumps climbers
        dumpExtend = hardwareMap.servo.get("dumpExtend"); // Swings out over beacon
    }


    @Override
    public void loop() {

        /* Gamepad 1 */

        motorRight.setPower(-1 * gamepad1.right_stick_y); // Tracks
        motorLeft.setPower(-1 * gamepad1.left_stick_y);

        backLeft.setPower(-1 * gamepad1.left_stick_y); // Back Wheels
        backRight.setPower(-1 * gamepad1.right_stick_y);


        if (gamepad1.x) // Left Wing
            wingLeft.setPosition(.5);
        else
            wingLeft.setPosition(0.0);


        if (gamepad1.b) // Right Wing
            wingRight.setPosition(0.6);
        else
            wingRight.setPosition(1.0);



        if (gamepad1.left_trigger > 0.5 || gamepad1.right_trigger > 0.5) { // bulldozer
            bdLeft.setPosition(.4);
            bdRight.setPosition(.4);
        }
        else
            if (gamepad1.left_bumper || gamepad1.right_bumper) {
                bdLeft.setPosition(1.0);
                bdRight.setPosition(1.0);
        }


        /* Gamepad 2 */


        /* Controls the arm adjustment with .2 buffer. */
        if (Math.abs(gamepad2.left_stick_y) > 0.2)
            armAdjust.setPower(-1 * gamepad2.left_stick_y * .25);
        else
            if (Math.abs(gamepad2.right_stick_y) > 0.2)
                armAdjust.setPower(-1 * gamepad2.right_stick_y * .25);
        else
            armAdjust.setPower(0);


        /*Controls the arm retraction (against the bungee)*/
        if (gamepad2.right_bumper)
            armExtend.setPower(1.0);
        else
            if (gamepad2.right_trigger > 0.5)
                armExtend.setPower(-1 * gamepad2.right_trigger);
        else
            armExtend.setPower(0);



        if (gamepad2.left_trigger > 0.5)
            dumpExtend.setPosition(0.6);
        else
            if (gamepad2.left_bumper)
                dumpExtend.setPosition(0.3);
        else
            dumpExtend.setPosition(0.5);


        if (gamepad2.a)
            dumpRotate.setPosition(0.0);
        else
            dumpRotate.setPosition(1.0);

    }
}
