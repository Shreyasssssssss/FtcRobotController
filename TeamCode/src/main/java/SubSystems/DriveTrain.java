package SubSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveTrain{
    DcMotor frontLeftMoter;
    DcMotor frontRightMoter;
    DcMotor backRightMoter;
    DcMotor backLeftMoter;

    public void initalize(HardwareMap hardWareMap){
        frontLeftMoter = hardWareMap.dcMotor.get("fl");
        frontRightMoter = hardWareMap.dcMotor.get("fr");
        backLeftMoter = hardWareMap.dcMotor.get("bl");
        backRightMoter = hardWareMap.dcMotor.get("br");

        frontRightMoter.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMoter.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void update(double x, double y, double z){
        x=-x;
        z=-z;
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(z), 1);
        double frontLeftPower = (y + x + z) / denominator;
        double backLeftPower = (y - x + z) / denominator;
        double frontRightPower = (y - x - z) / denominator;
        double backRightPower = (y + x - z) / denominator;
        frontLeftMoter.setPower(frontLeftPower);
        frontRightMoter.setPower(frontRightPower);
        backLeftMoter.setPower(backLeftPower);
        backRightMoter.setPower(backRightPower);
    }
}
