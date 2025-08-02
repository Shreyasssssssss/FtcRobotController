package SubSystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Sholder {
    public enum SState{
        INTAKING,
        SCOUTING,
        RESTING,
        BUCKET_SCORE,
        HUMAN_INTAKING;
    }
    SState currentState = SState.RESTING;
    Servo rightServo;
    Servo leftServo;
    public static double intakingPos = 0.7;
    public static double scoutingPos = 0.6;
    public static double restingPos = 0.4;
    public static double bucketPos = 0.3;
    public static double humanIntakePos = 0.58;

    public void initiate(HardwareMap hardwareMap){
        rightServo = hardwareMap.servo.get("RightShoulder");
        leftServo = hardwareMap.servo.get("LeftShoulder");
    }
    public void setState(SState newState){
        currentState = newState;
    }
    public SState getState(){
        return currentState;
    }
    public void upadate(){
        switch (currentState){
            case INTAKING:
                rightServo.setPosition(intakingPos);
                break;
            case SCOUTING:
                rightServo.setPosition(scoutingPos);
                break;
            case RESTING:
                rightServo.setPosition(restingPos);
                break;
            case BUCKET_SCORE:
                rightServo.setPosition(bucketPos);
                break;
            case HUMAN_INTAKING:
                rightServo.setPosition(humanIntakePos);
                break;
        }
        leftServo.setPosition(-rightServo.getPosition());
    }

}
