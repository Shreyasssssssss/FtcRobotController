package TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.State;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import SubSystems.Claw;
import SubSystems.ClawGrab;
import SubSystems.ClimbMotors;
import SubSystems.DriveTrain;
import SubSystems.Extendo;
import SubSystems.Sholder;
import SubSystems.VerticalSlides;
import SubSystems.Wrist;

@TeleOp
public class To extends LinearOpMode {
    public enum RobotStates{
        RESTING,
        SCOUTING,
        INTAKING
    }
    @Override
    public void runOpMode() throws InterruptedException {
        ClawGrab clawGrab = new ClawGrab();
        clawGrab.initiate(hardwareMap);
        Sholder sholder = new Sholder();
        sholder.initiate(hardwareMap);
        ClimbMotors climbMotors = new ClimbMotors();
        climbMotors.initiate(hardwareMap);
        VerticalSlides verticalSlides = new VerticalSlides();
        verticalSlides.initiate(hardwareMap);
        Extendo extendo = new Extendo();
        extendo.initiate(hardwareMap);
        Wrist wrist = new Wrist();
        wrist.initiate(hardwareMap);
        DriveTrain driveTrain = new DriveTrain();
        driveTrain.initalize(hardwareMap);


        waitForStart();

        if (isStopRequested()) return;
        RobotStates state = RobotStates.RESTING;
        Gamepad previousGamePad1 = new Gamepad();
        ClawGrab claw = new ClawGrab();
        claw.initiate(hardwareMap);
        while (opModeIsActive()) {
            boolean rightBumper = gamepad1.right_bumper && previousGamePad1.right_bumper;
            boolean leftBumber = gamepad1.left_bumper && previousGamePad1.left_bumper;
            boolean leftTrigger = gamepad1.left_trigger > 0.1 && previousGamePad1.left_trigger <0.1;
            previousGamePad1.copy(gamepad1);

            if (rightBumper) {
               state = RobotStates.RESTING;
            }
            if (leftBumber) {
                switch (state){
                    case RESTING:
                    case INTAKING:
                        state = RobotStates.SCOUTING;
                        break;
                    case SCOUTING:
                        state = RobotStates.INTAKING;
                        break;
            }
            if (leftTrigger) {
                switch (state){
                    case INTAKING:
                    case SCOUTING:
                        clawGrab.flip();
                }
            }
            switch (state){
                case RESTING:
                    sholder.setState(Sholder.SState.RESTING);
                    clawGrab.setState(ClawGrab.clawState.CLOSE);
                    wrist.setState(Wrist.States.FORWARDS);
                    extendo.setState(Extendo.States.RETRACT);
                    verticalSlides.setState(VerticalSlides.States.DOWN);
                    break;
                case SCOUTING:
                    sholder.setState(Sholder.SState.SCOUTING);
                    wrist.setState(Wrist.States.FORWARDS);
                    extendo.setState(Extendo.States.EXTENDED);
                    verticalSlides.setState(VerticalSlides.States.DOWN);
                    break;
                case INTAKING:
                    sholder.setState(Sholder.SState.INTAKING);
                    wrist.setState(Wrist.States.FORWARDS);
                    extendo.setState(Extendo.States.EXTENDED);
                    verticalSlides.setState(VerticalSlides.States.DOWN);
            }
            driveTrain.update(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
            telemetry.addData("Robot State", state);
            verticalSlides.update();
            climbMotors.update();
            claw.update();
            previousGamePad1.copy(gamepad1);
            verticalSlides.status(telemetry);

            telemetry.update();
                }
            }
        }
    }

