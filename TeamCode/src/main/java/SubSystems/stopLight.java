package SubSystems;

public class stopLight {
    String color = "RED";

    public String setColor(String setColor) {
        return color;
    }
    public void changeColor(){
        if (color.equals("RED")){
            color = "GREEN";
        }else if (color.equals("YELLOW")){
            color = "RED";
        }else if (color.equals("GREEN")){
            color = "YELLOW";
        }
    }
}
