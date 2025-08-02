package SubSystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Extendo {
    public enum States {
        EXTENDED,
        RETRACT
    }
    States currentState = States.RETRACT;
    Servo extendoServo;

    public static double extendedPos = 0;
    public static double retractPos = 0.23;
    public void initiate(HardwareMap hardwareMap){
        extendoServo = hardwareMap.servo.get("extendo");
    }
    public void setState(States newState){
        currentState = newState;
    }
    public States getState(){
        return currentState;
    }
    public void update(){
        switch (currentState){
            case RETRACT:
                extendoServo.setPosition(retractPos);
                break;
            case EXTENDED:
                extendoServo.setPosition(extendedPos);
                break;
        }
    }
}
