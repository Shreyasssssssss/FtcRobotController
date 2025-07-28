package TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import SubSystems.Claw;
import SubSystems.ClawGrab;
import SubSystems.Sholder;

@TeleOp
public class To extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        ClawGrab clawGrab = new ClawGrab();
        clawGrab.initiate(hardwareMap);
        Sholder sholder = new Sholder();
        sholder.initiate(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;
        Gamepad previousGamePad1 = new Gamepad();
        ClawGrab claw = new ClawGrab();
        claw.initiate(hardwareMap);
        while (opModeIsActive()) {
            boolean cross = gamepad1.cross && !previousGamePad1.cross;
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
                    while (opModeIsActive()) {
                        if (gamepad1.left_bumper) {
                            clawGrab.setState(SubSystems.ClawGrab.clawState.CLOSE);
                        }
                        if (gamepad1.right_bumper) {
                            clawGrab.setState(SubSystems.ClawGrab.clawState.OPEN);
                        }

                    }
                    claw.update();
                    previousGamePad1.copy(gamepad1);
                }

            }
        }
    }
}
