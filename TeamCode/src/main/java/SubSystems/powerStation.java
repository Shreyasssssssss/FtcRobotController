package SubSystems;

public class powerStation {
    boolean on = true;
    stopLight StopLight = new stopLight();
    battery battery = new battery();
    public void update(){

        if (!on){
           StopLight.setColor("RED");
        }else{
            StopLight.changeColor();
        }
        battery.update();
    }
}
