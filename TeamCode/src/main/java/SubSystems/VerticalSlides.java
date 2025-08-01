package SubSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class VerticalSlides {
    public enum States{
        UP,
        DOWN
    }
    public static int downPos= 0;
    public static int upPos = 1000;
    public static double kp = 0.01;
    States currentState = States.DOWN;
    DcMotor slideMoter;
    public void initiate(HardwareMap hardwareMap){
        slideMoter = hardwareMap.dcMotor.get("fr");
    }
    public void setState(States newState){
        currentState = newState;
    }
    public States getState(){
        return currentState;
    }
    public void update(){
        switch (currentState){
            case DOWN:
                slideMoter.setTargetPosition(downPos);
                break;
            case UP:
                slideMoter.setTargetPosition(upPos);
        }
        int error = slideMoter.getTargetPosition() - slideMoter.getCurrentPosition();
        slideMoter.setPower(kp *error);
    }
    public void status(Telemetry telemetry){
        telemetry.addData("SlidePos", slideMoter.getCurrentPosition());
    }
}
