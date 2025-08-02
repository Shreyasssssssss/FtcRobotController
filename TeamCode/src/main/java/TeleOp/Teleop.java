package TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import SubSystems.ClawGrab;
import SubSystems.ClimbMotors;
import SubSystems.Extendo;
import SubSystems.Sholder;
import SubSystems.VerticalSlides;
import SubSystems.Wrist;

@TeleOp
public class Teleop extends LinearOpMode {
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

        waitForStart();

        if (isStopRequested()) return;
        RobotStates state = RobotStates.RESTING;
        Gamepad previousGamePad1 = new Gamepad();
        ClawGrab claw = new ClawGrab();
        claw.initiate(hardwareMap);
        while (opModeIsActive()) {
            boolean triangle = gamepad1.triangle && !previousGamePad1.triangle;
            boolean cross = gamepad1.cross && !previousGamePad1.cross;
            boolean square = gamepad1.square && !previousGamePad1.square;
            boolean rightTrigger = gamepad1.right_trigger > 0.1 && previousGamePad1.right_trigger < 0.1;
            boolean leftTrigger = gamepad1.left_trigger > 0.1 && previousGamePad1.left_trigger < 0.1;
            if (cross) {
                switch (claw.getState()) {
                    case CLOSE:
                        claw.setState(ClawGrab.clawState.OPEN);
                        break;
                    case OPEN:
                        claw.setState(ClawGrab.clawState.CLOSE);
                        break;
                }
            }
            if (rightTrigger) {
                switch (sholder.getState()) {
                    case INTAKING:
                        sholder.setState(Sholder.SState.SCOUTING);
                        break;
                    case SCOUTING:
                        sholder.setState(Sholder.SState.RESTING);
                        break;
                    case RESTING:
                        sholder.setState(Sholder.SState.BUCKET_SCORE);
                        break;
                    case BUCKET_SCORE:
                        sholder.setState(Sholder.SState.HUMAN_INTAKING);
                        break;
                    case HUMAN_INTAKING:
                        sholder.setState(Sholder.SState.INTAKING);
                        break;
                }
                if (leftTrigger) {
                    switch (sholder.getState()) {
                        case INTAKING:
                            sholder.setState(Sholder.SState.HUMAN_INTAKING);
                            break;
                        case SCOUTING:
                            sholder.setState(Sholder.SState.INTAKING);
                            break;
                        case RESTING:
                            sholder.setState(Sholder.SState.SCOUTING);
                            break;
                        case BUCKET_SCORE:
                            sholder.setState(Sholder.SState.RESTING);
                            break;
                        case HUMAN_INTAKING:
                            sholder.setState(Sholder.SState.BUCKET_SCORE);
                            break;
                    }
                    if (square){
                        switch (climbMotors.getState()){
                            case STATIONARY:
                                climbMotors.setState(ClimbMotors.States.CLIMBING);
                                break;
                            case CLIMBING:
                                climbMotors.setState(ClimbMotors.States.STATIONARY);
                                break;
                        }
                    }
                    if (triangle){
                        switch (verticalSlides.getState()){
                            case DOWN:
                                verticalSlides.setState(VerticalSlides.States.UP);
                                break;
                            case UP:
                                verticalSlides.setState(VerticalSlides.States.DOWN);
                                break;
                        }
                    }
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
}
