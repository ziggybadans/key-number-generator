package com.ziggybadans.keynumbergenerator;

import java.io.FileWriter;
import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;

public class KeyNumberGenerator {

    public String[] markets = {"BAL", "AUG"};
    public String market;
    public int year;
    public char writerInitial;
    public int[] durations = {10, 15, 30, 45, 60, 90};
    public int duration;
    public String[] types = {"R", "L", "SL", "X", "SX"};
    public String type;
    public char clientInitial;

    public static boolean firstTime;

    KeyNumberGenerator(int year) {
        this.year = year;
    }

    public void setMarket(String input) {
        int tempMarket;
        try {
            tempMarket = java.util.Arrays.asList(markets).indexOf(input);
            market = markets[tempMarket];
        }
        catch (Exception e) {
            System.out.println(input + " is not a valid market! Please enter again.");
        }
    }

    public void getwI(String input) {
        writerInitial = input.charAt(0);
    }

    public void setDuration(int input) {
        int tempDuration;
        try {
            tempDuration = Arrays.stream(durations).boxed().collect(Collectors.toList()).indexOf(input);
            //System.out.println("Debug: " + tempDuration);
            duration = durations[tempDuration];
        }
        catch (Exception e) {
            System.out.println(input + " is not a valid duration! Please enter again.");
        }
    }

    public void setType(String input) {
        int tempType;
        try {
            tempType = java.util.Arrays.asList(types).indexOf(input);
            type = types[tempType];
        }
        catch (Exception e) {
            System.out.println(input + " is not a valid type! Please enter again.");
        }
    }

    // Have this get the char after space later
    public void getcI(String input) {
        clientInitial = input.charAt(0);
    }

    public void print() {
        System.out.println("Key Number:");
        System.out.println(market + year + writerInitial + '-' + duration + type + '-' + clientInitial + '-');
    }

    public static void main(String[] args) {
        // Add properties file for counting the number

        KeyNumberGenerator keyNumberGenerator = new KeyNumberGenerator(Integer.parseInt(args[1]));
        keyNumberGenerator.setMarket(args[0]);
        keyNumberGenerator.getwI(args[2]);
        keyNumberGenerator.setDuration(Integer.parseInt(args[3]));
        keyNumberGenerator.setType(args[4]);
        keyNumberGenerator.getcI(args[5]);
        keyNumberGenerator.print();
    }
}
