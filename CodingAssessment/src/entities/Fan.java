package entities;

public class Fan extends Appliance{

    private int speed;

    public Fan(String name, String power) {
        super(name, power);
    }

    public int getSpeed() { return speed; }

    public void setSpeed(int speed) { this.speed = speed; }
}
