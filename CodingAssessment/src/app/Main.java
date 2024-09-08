package app;

import entities.AirConditioner;
import entities.Appliance;
import entities.Fan;
import entities.Light;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

/*
 *  James Hare
 *  September 6th, 2024
 *  Medavie Blue Cross Coding Assessment
 *
 *  My take on the smart home appliance system is a simple console app designed for the user
 *  to navigate through their registered appliances and change their settings as they desire.
 */

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Light light = new Light("Light", "OFF");
    static Fan fan = new Fan("Fan", "OFF");
    static AirConditioner ac = new AirConditioner("Air Conditioner", "OFF", 20);
    static Appliance[] appliances = {light, fan, ac};

    /*
     * Since the app is merely simulating an actual update I've set a flag to check whether
     * an update has already occurred in this instance of the application so that the appliances
     * are not continuously turned off
     */

    static int updateFlag = 0;
    static final LocalDateTime updateDate = LocalDateTime.of(LocalDate.now().getYear(), 1, 1, 1, 0, 0);

    public static void main(String[] args) {

        System.out.println("Welcome to your Smart Home appliance control panel!");
        System.out.println();

        int selection = displayMenu();

        do {
            switch (selection) {
                case 1:
                    //Light
                    lightSettings();
                    selection = displayMenu();
                    break;
                case 2:
                    //Fan
                    fanSettings();
                    selection = displayMenu();
                    break;
                case 3:
                    //AC
                    airConditionerSettings();
                    selection = displayMenu();
                    break;
                default:
                    //Exit
                    break;
            }
        } while (selection != 4);

        System.out.println("Thank you, Goodbye");
    }

    public static int displayMenu() {

        updateSystem();
        int selection = 0;

        System.out.println("--You are viewing the Main Menu--");
        System.out.println();
        System.out.println("You have " + appliances.length + " appliances registered.");

        for (int i = 0; i < appliances.length; i++) {
            System.out.println((i + 1) + ". " + appliances[i].getName() + " (" + appliances[i].getPower() + ")");
        }

        System.out.println((appliances.length + 1) + ". Exit Application");
        System.out.println("Enter a number to select an appliance and change its settings:");

        selection = validateUserInput(1, (appliances.length + 1), "Please select an option from the menu");

        return selection;
    }

    public static void lightSettings() {

        int selection = 0;

        System.out.println("--You are viewing settings for the Light--");
        System.out.println("Power: '" + light.getPower() + "'");
        System.out.println();
        System.out.println("Options:");
        System.out.println("1. ON");
        System.out.println("2. OFF");
        System.out.println("3. Return to Menu");
        System.out.println("Please enter a number");

        selection = validateUserInput(1, 3, "Please select an option from the menu");

        if (selection == 1) {
            light.setPower("ON");
            System.out.println("The Light has been set to '" + light.getPower() + "'");
            System.out.println();
        } else if (selection == 2) {
            light.setPower("OFF");
            System.out.println("The Light has been set to '" + light.getPower() + "'");
            System.out.println();
        }

    }

    public static void fanSettings() {

        int selection;

        System.out.println("--You are viewing settings for the Fan--");
        System.out.println("Power: '" + fan.getPower() + "'");
        System.out.println("Speed: '" + fan.getSpeed() + "'");
        System.out.println();
        System.out.println("Options:");
        System.out.println("1. Speed: 1");
        System.out.println("2. Speed: 2");
        System.out.println("3. Speed: 0");
        System.out.println("4. Return to Menu");
        System.out.println("Please enter a number");

        selection = validateUserInput(1, 4, "Please select an option from the menu");

        switch (selection) {
            case 1:
                // Speed 1
                fan.setPower("ON");
                fan.setSpeed(1);
                break;
            case 2:
                // Speed 2
                fan.setPower("ON");
                fan.setSpeed(2);
                break;
            case 3:
                // OFF
                fan.setPower("OFF");
                fan.setSpeed(0);
                break;
            default:
                // Return
                break;
        }
    }

    public static void airConditionerSettings() {

        int selection;

        System.out.println("--You are viewing settings for the Air Conditioner--");
        System.out.println("Power: '" + ac.getPower() + "'");
        System.out.println("Temperature: '" + ac.getTemperature() + "°C'");
        System.out.println();
        System.out.println("Options:");
        System.out.println("1. ON");
        System.out.println("2. OFF");
        System.out.println("3. Set Temperature");
        System.out.println("4. Return to Menu");
        System.out.println("Please enter a number");

        selection = validateUserInput(1, 4, "Please select an option from the menu");

        switch (selection) {
            case 1:
                // ON
                ac.setPower("ON");
                break;
            case 2:
                // OFF
                ac.setPower("OFF");
                break;
            case 3:
                // Set Temperature
                setTemperature();
                break;
            default:
                // Return
                break;
        }
    }

    public static void setTemperature() {

        int temperature;

        System.out.println("--You are viewing Temperature Controls for the Air Conditioner--");
        System.out.println("Temperature: '" + ac.getTemperature() + "°C'");
        System.out.println();
        System.out.println("Please enter a new temperature between 18°C and 40°C");

        temperature = validateUserInput(18, 40, "Please enter a number within the temperature range");

        ac.setTemperature(temperature);
        airConditionerSettings();
    }

    public static void updateSystem() {

        if (updateFlag == 0 && LocalDateTime.now().isAfter(updateDate)) {
            light.setPower("OFF");
            fan.setPower("OFF");
            ac.setPower("OFF");
            updateFlag = 1;
        }

    }

    public static int validateNumericInput() {

        int input = 0;

        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number");
            scanner.next();
        }

        input = scanner.nextInt();

        return input;
    }

    public static int validateUserInput(int lowerLimit, int upperLimit, String errorMessage) {

        int selection = 0;

        do {

            selection = validateNumericInput();

            if (selection < lowerLimit || selection > upperLimit) {
                System.out.println(errorMessage);
            }

        } while (selection < lowerLimit || selection > upperLimit);

        return selection;
    }

}