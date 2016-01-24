

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class TeleOp extends OpMode {


    public TeleOp() {

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
    Servo wingRight;
    Servo wingLeft;
    Servo bdLeft;
    Servo bdRight;
    Servo dumpRotate;
    Servo dumpExtend;
    DcMotor solenoid;
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

        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");

        backLeft.setDirection(DcMotor.Direction.REVERSE);

        /* This is done so that all controls can be set up without
            always having to reverse one side. */
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        /* Some of these should  be reversed as needed (testing) */
        armExtend = hardwareMap.dcMotor.get("armExtend");
        armAdjust = hardwareMap.dcMotor.get("armAdjust"); //This may need to be changed to a servo later on

        /* These are for knocking out the zipline triggers. Later on, we may switch
        * to a single servo port with a Y adapter. Make sure all connections match colors XD */
        wingRight = hardwareMap.servo.get("wingRight");
        wingLeft = hardwareMap.servo.get("wingLeft");

        /* Stuffs for bulldozer. This may be switched around eventually,
        * to a Y adapter but when the decision comes around, make sure
        * to mention that on a Y adapter, the servos can't move
        * independently of one another. */
        bdLeft = hardwareMap.servo.get("bdLeft");/*bd is bulldozer*/
        bdRight = hardwareMap.servo.get("bdRight");

        /* Rotates the dumper to release the men into shelter. Simple. */
        dumpRotate = hardwareMap.servo.get("dumpRotate");

        /* This might need to be changed to a crServo or motor later */
        dumpExtend = hardwareMap.servo.get("dumpExtend");
        /*This is the Solenoid to stop the robot*/
        solenoid = hardwareMap.dcMotor.get("solenoid");
    }


    @Override
    public void loop() {

        /* Sets the power of each motor to the float value of the joysticks (y axis) */
        motorRight.setPower(-1 * gamepad1.right_stick_y);
        motorLeft.setPower(-1 * gamepad1.left_stick_y);

        backLeft.setPower(-1 * gamepad1.left_stick_y);
        backRight.setPower(-1 * gamepad1.right_stick_y);

        /* Brings out the wings on either side of the robot. */
        if (gamepad1.x) {
            wingLeft.setPosition(.5);
        } else {
            wingLeft.setPosition(0.0);
        }

        if (gamepad1.b) {
            wingRight.setPosition(0.6);
        } else {
            wingRight.setPosition(1.0);
        }

        /* Controlls the Bulldozer */
        if (gamepad1.left_trigger > 0.5 || gamepad1.right_trigger > 0.5) {
            bdLeft.setPosition(.4);
            bdRight.setPosition(.4);
        } else if (gamepad1.left_bumper || gamepad1.right_bumper) {
            bdLeft.setPosition(1.0);
            bdRight.setPosition(1.0);

        }


        /* ============= */
        /*   Gamepad 2   */


        /* Controls the arm adjustment with .2 buffer. */
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

        /*Negative is counterClockwise
        * Positive is Clockwise*/

        if (gamepad2.left_trigger > 0.5) {
            dumpExtend.setPosition(0.6);
        } else if (gamepad2.left_bumper) {
            dumpExtend.setPosition(0.3);
        } else {
            dumpExtend.setPosition(0.5);
        }

        if (gamepad2.a) {
            dumpRotate.setPosition(0.0);
        } else {
            dumpRotate.setPosition(1.0);


        }

        if (gamepad2.y)  {
            solenoid.setPower(1);

        }else {
            solenoid.setPower(0);
            }
        }
    }
