package SubSystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Wrist {
    public enum States{
        UPWARDS,
        FORWARDS,
        DOWNWORD
    }
    States currentState = States.FORWARDS;
    Servo wristServo;

    public static double upwardPos = 0.3;
    public static double forwardPos = 0.56;
    public static double downwardPos = 0.84;

    public void initiate(HardwareMap hardwareMap){
        wristServo = hardwareMap.servo.get("wrist");
    }
    public void setState(States newState){
        currentState = newState;
    }
    public States getState(){
        return currentState;
    }
    public void update(){
        switch (currentState){
            case UPWARDS:
                wristServo.setPosition(upwardPos);
                break;
            case DOWNWORD:
                wristServo.setPosition(downwardPos);
                break;
            case FORWARDS:
                wristServo.setPosition(forwardPos);
                break;
        }
    }
}

