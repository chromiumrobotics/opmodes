

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class pushbotOp extends OpMode {


    public pushbotOp() {

    }

    /* THESE MUST BE DECLARED OUTSIDE OF A METHOD! */
    /* If declared inside the 'init' method, they will
    * not work. */

    DcMotor motorRight;
    DcMotor motorLeft;
    DcMotor backRight;
    DcMotor backLeft;
    DcMotor armExtend;
    DcMotor armAdjust;
    Servo bdLeft;
    Servo bdRight;

    @Override
    public void init() {

        /*
        *                =======!!!! NOTICE !!!!=======
        * All of these parts MUST have a module connected for them.
        * They must also have a configuration file that is set up properly.
        *
        * MY ADVICE: Set up the configuration file based on what is HERE.
        * These are named very intentionally; if you change them, please
        * use good names! ;(
        *
        * */

        /* These are the motors that the robot tracks are driven by. */
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");

        backLeft.setDirection(DcMotor.Direction.REVERSE);

        armExtend = hardwareMap.dcMotor.get("armExtend");
        armAdjust = hardwareMap.dcMotor.get("armAdjust");

        bdLeft = hardwareMap.servo.get("bdLeft");/*bd is bulldozer*/
        bdRight = hardwareMap.servo.get("bdRight");

    }


    @Override
    public void loop() {

        /* Sets the power of each motor to the float value of the joysticks (y axis) */
        motorRight.setPower(-1 * gamepad1.right_stick_y);
        motorLeft.setPower(-1 * gamepad1.left_stick_y);
        backLeft.setPower(-1 * gamepad1.left_stick_y);
        backRight.setPower(-1 * gamepad1.right_stick_y);

        if (Math.abs(gamepad2.left_stick_y) > 0.2) {
            armAdjust.setPower(-1 * gamepad2.left_stick_y * .25);
        } else if (Math.abs(gamepad2.right_stick_y) > 0.2) {
            armAdjust.setPower(-1 * gamepad2.right_stick_y * .25);
        } else {
            armAdjust.setPower(0);
        }

        /*Controls the arm retraction (against the bungee)*/
        if (gamepad2.right_bumper) {
            armExtend.setPower(1.0);
        } else if (gamepad2.right_trigger > 0.5) {
            armExtend.setPower(-1 * gamepad2.right_trigger);
        } else {
            armExtend.setPower(0);
        }

        /* Controlls the Bulldozer */
        if (gamepad1.left_trigger > 0.5 || gamepad1.right_trigger > 0.5) {
            bdLeft.setPosition(.4);
            bdRight.setPosition(.4);
        } else if (gamepad1.left_bumper || gamepad1.right_bumper) {
            bdLeft.setPosition(1.0);
            bdRight.setPosition(1.0);

        }
    }
}
