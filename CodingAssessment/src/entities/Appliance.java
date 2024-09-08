package entities;

public abstract class Appliance {

    private final String name;
    private String power;

    public Appliance(String name, String power) {
        this.name = name;
        this.power = power;
    }

    public String getName() { return name; }

    public String getPower() { return power; }

    public void setPower(String power) { this.power = power; }
}
