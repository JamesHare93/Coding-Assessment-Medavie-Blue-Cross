package entities;

public class AirConditioner extends Appliance{

    private int temperature;
    public AirConditioner(String name, String power, int temperature) {
        super(name, power);
        this.setTemperature(temperature);
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
