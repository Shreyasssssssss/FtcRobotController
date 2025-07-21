package SubSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveTime {
    DcMotor frontLeftMoter;
    DcMotor frontRightMoter;
    DcMotor backRightMoter;
    DcMotor backLeftMoter;

    public void initalize(HardwareMap hardWareMap){
        frontLeftMoter = hardWareMap.dcMotor.get("fl");
        frontLeftMoter = hardWareMap.dcMotor.get("fr");
        frontLeftMoter = hardWareMap.dcMotor.get("bl");
        frontLeftMoter = hardWareMap.dcMotor.get("br");
    }
    public void update(double x, double y, double z){
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
