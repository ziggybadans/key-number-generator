package com.ziggybadans.keynumbergenerator;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;

public class KeyNumberGenerator {

    public String[] markets = {"AUG", "BAL", "BEG", "BEN", "BER", "BOW", "BUN", "BUR", "CAI", "DAR", "DEV", "GEE", "GLA", "GOL", "HOB", "IPS", "LAU", "LIN", "MAC",
            "MAR", "MIL", "MUR", "MUS", "NOW", "PIR", "QUE", "ROC", "TOW", "WOL", "TRSN", "AGEN"};
    public String market;
    public int year;
    public char writerInitial;
    public int[] durations = {10, 15, 30, 45, 60, 90};
    public int duration;
    public String[] types = {"R", "L", "SL", "X", "SX"};
    public String type;
    public String clientInitial;
    public int number = 0;

    public String keyNumber;

    String pathname = System.getenv("APPDATA") + "/number.properties";

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
        CharConcatentation charConcat = new CharConcatentation();
        if (input.length() == 2) {
            char firstClientInitial = input.charAt(0);
            char secondClientInitial = input.charAt(1);

            clientInitial = charConcat.concat(firstClientInitial, secondClientInitial);
        } else {
            int secondInitialIndex = input.indexOf(" ") + 1;
            char firstClientInitial = input.charAt(0);
            char secondClientInitial = input.charAt(secondInitialIndex);

            clientInitial = charConcat.concat(firstClientInitial, secondClientInitial);
        }

    }

    public void properties() throws IOException {
        File getFile = new File(pathname);
        boolean exists = getFile.exists();

        Properties p = new Properties();

        if (exists) {
            FileReader reader = new FileReader(pathname);
            p.load(reader);
            number = Integer.parseInt(p.getProperty("number"));
        } else {
            try {
                p.setProperty("number", "1");
                p.store(new FileWriter(pathname), "KeyNumberGenerator UNIQUE NUMBER // DO NOT DELETE");

                FileReader reader = new FileReader(pathname);
                p.load(reader);
                number = Integer.parseInt(p.getProperty("number"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void print() {
        int tempNumber = number;
        try {
            number++;
            Properties p = new Properties();
            p.setProperty("number", String.valueOf(number));
            p.store(new FileWriter(pathname), "KeyNumberGenerator UNIQUE NUMBER // DO NOT DELETE");
        } catch (IOException e) {
            System.out.println("Could not save important properties! Please try again, or change location of properties file to somewhere with access.");
            e.printStackTrace();

        }
        System.out.println("Key Number:");
        keyNumber = market + year + writerInitial + '-' + clientInitial + '-' + duration + type + '-' + tempNumber;
        System.out.println(keyNumber);

        StringSelection selection = new StringSelection(keyNumber);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }

    public static void main(String[] args) throws IOException {
        KeyNumberGenerator keyNumberGenerator = new KeyNumberGenerator(Integer.parseInt(args[1]));

        keyNumberGenerator.properties();

        System.out.println("What market?");
        keyNumberGenerator.setMarket(args[0]);
        System.out.println("Type writer initials or name.");
        keyNumberGenerator.getwI(args[2]);
        System.out.println("Type client initials or name.");
        keyNumberGenerator.getcI(args[5]);
        System.out.println("What duration?");
        keyNumberGenerator.setDuration(Integer.parseInt(args[3]));
        System.out.println("What type?");
        keyNumberGenerator.setType(args[4]);

        System.out.println("Here is your key number! (Copied to clipboard)");
        keyNumberGenerator.print();
    }
}
