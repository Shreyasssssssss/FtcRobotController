package SubSystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawGrab {
    public enum clawState{
        OPEN,
        CLOSE;
    }
    public clawState currentState = clawState.CLOSE;
    public void setState(clawState clawState){
        currentState = clawState;
    }


    Servo clawServo;

    public void initiate(HardwareMap hardwareMap){
        clawServo = hardwareMap.servo.get("ClawServo");
    }

    public static double clawOpen = 0.4;

    public static double clawClose = 0.5;

    public clawState getState(){
        return currentState;
    }

    public void update(){
        switch (currentState){
            case OPEN:
                clawServo.setPosition(clawOpen);
                break;
            case CLOSE:
                clawServo.setPosition(clawClose);
                break;
        }
    }
}
