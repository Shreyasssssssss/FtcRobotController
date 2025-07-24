package TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import SubSystems.ClawGrab;

@TeleOp
public class To extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        ClawGrab clawGrab = new ClawGrab();
        clawGrab.initiate(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;
        while (opModeIsActive()) {
            if (gamepad1.left_bumper) {
                clawGrab.setState(SubSystems.ClawGrab.clawState.CLOSE);
            }
            if (gamepad1.right_bumper) {
                clawGrab.setState(SubSystems.ClawGrab.clawState.OPEN);
            }
            clawGrab.update();
        }
    }
}
