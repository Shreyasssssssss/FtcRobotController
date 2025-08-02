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
    public static int upPos = 1800;
    public static double kp = 0.005;
    States currentState = States.DOWN;
    DcMotor rightMoter;
    DcMotor leftMoter;
    public void initiate(HardwareMap hardwareMap){
        rightMoter = hardwareMap.dcMotor.get("RightSlide");
        leftMoter = hardwareMap.dcMotor.get("LeftSlide");
    }
    public void setState(States newState){
        currentState = newState;
    }
    public States getState(){
        return currentState;
    }
    public void update(){
        double maxPower = 1;
        switch (currentState){
            case DOWN:
                rightMoter.setTargetPosition(downPos);
                maxPower = 0.3;
                break;
            case UP:
                rightMoter.setTargetPosition(upPos);
        }
        int error = rightMoter.getTargetPosition() - rightMoter.getCurrentPosition();
        rightMoter.setPower(kp *error);
        if (Math.abs(rightMoter.getPower()) > maxPower){
            rightMoter.setPower(Math.signum(rightMoter.getPower()*maxPower));
        }

        leftMoter.setPower(-rightMoter.getPower());
    }
    public void status(Telemetry telemetry){
        telemetry.addData("SlidePos", rightMoter.getCurrentPosition());
    }
}
