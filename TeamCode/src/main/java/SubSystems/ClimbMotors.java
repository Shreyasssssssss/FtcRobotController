package SubSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcontroller.external.samples.UtilityOctoQuadConfigMenu;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ClimbMotors {
    public enum States{
        CLIMBING,
        STATIONARY
    }
    States currentState = States.STATIONARY;

    DcMotor climbMotor;

    public void initiate(HardwareMap hardwareMap){
        climbMotor = hardwareMap.dcMotor.get("fl");
    }
    public void setState(States newState){
        currentState = newState;
    }
    public States getState(){
        return currentState;
    }
    public void update(){
        switch (currentState){
            case STATIONARY:
                climbMotor.setPower(0);
                break;
            case CLIMBING:
                climbMotor.setPower(1);
                break;
        }
    }

}
