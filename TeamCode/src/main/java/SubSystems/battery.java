package SubSystems;

public class battery {
    int power = 100;
    boolean on = false;
    public void turnOn(boolean newOn){
        on = newOn;
    }
    public void flip(){
        on = !on;
    }
    public boolean getOn(){
         return on;
    }
    public void setPower(int newPower){
        power = newPower;
    }
    public int getPower() {
        return power;
    }
    public void usePower(int powerUsed){
        power -= powerUsed;
    }
     public void chargePower(int powerCharged){
        power += powerCharged;
    }
    public void update(){
        if (on){
            usePower(1);
        }else{
            chargePower(1);
        }
    }
}
