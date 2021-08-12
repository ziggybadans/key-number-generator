package com.ziggybadans.keynumbergenerator;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;
import java.util.stream.Collectors;

public class KeyNumberGenerator {

    public int year;
    public boolean yearReady;

    public static String[] markets = {"AUG", "BAL", "BEG", "BEN", "BER", "BOW", "BUN", "BUR", "CAI", "DAR", "DEV", "GEE", "GLA", "GOL", "HOB", "IPS", "LAU", "LIN", "MAC",
            "MAR", "MIL", "MUR", "MUS", "NOW", "PIR", "QUE", "ROC", "TOW", "WOL", "TRSN", "AGEN"};
    public boolean marketReady = false;
    public String market;

    public char writerInitial;

    public static Integer[] durations = {10, 15, 30, 45, 60, 90};
    public boolean durationReady = false;
    public int duration;

    public static String[] types = {"R", "L", "SL", "X", "SX"};
    public boolean typeReady = false;
    public String type;

    public String clientInitial;

    public static int number = 0;
    public String pathname;
    public String keyNumber;

    KeyNumberGenerator() {
    }

    // Get current year for this later.
    public void setYear(String input) {
        CharConcatentation charConcat = new CharConcatentation();
        if (input.length() == 2 && Integer.parseInt(input) >= 21) {
            this.year = Integer.parseInt(input);
            this.yearReady = true;
            System.out.println("Debug: " + this.year + " - Successful!");
        } else if (input.length() == 4 && Integer.parseInt(input) >= 2021) {
            char firstYearChar = input.charAt(2);
            char secondYearChar = input.charAt(3);

            this.year = Integer.parseInt(charConcat.concat(firstYearChar, secondYearChar));
            this.yearReady = true;
            System.out.println("Debug: " + this.year + " - Successful!");
        } else {
            this.yearReady = false;
            System.out.println("Debug: " + input + " - Invalid.");
        }
    }

    public void setMarket(String input) {
        int tempMarket;
        try {
            tempMarket = java.util.Arrays.asList(markets).indexOf(input);
            market = markets[tempMarket];
            marketReady = true;
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
            // Check this
            tempDuration = Arrays.asList(durations).indexOf(input);
            //System.out.println("Debug: " + tempDuration);
            duration = durations[tempDuration];
            durationReady = true;
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
            typeReady = true;
        }
        catch (Exception e) {
            System.out.println(input + " is not a valid type! Please enter again.");
        }
    }

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

    /*
    public void properties() throws IOException {
        if (getOS.isWindows()) {
            pathname = System.getenv("APPDATA")  + "/number.properties";
        }
        if (getOS.isMac()) {
            pathname = System.getProperty("user.home") + "/number.properties";
        }

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
     */

    public int getProperties() {
        if (getOS.isWindows()) {
            pathname = System.getenv("APPDATA")  + "/number.properties";
        }
        if (getOS.isMac()) {
            pathname = System.getProperty("user.home") + "/number.properties";
        }

        File getFile = new File(pathname);
        boolean exists = getFile.exists();

        Properties p = new Properties();

            if (exists) {
                try {
                    FileReader reader = new FileReader(pathname);
                    p.load(reader);
                    number = Integer.parseInt(p.getProperty("number"));
                } catch (IOException e) {
                    System.out.println("Could not access default path, because it either doesn't exist or is protected. Please set path someplace else.");
                    e.printStackTrace();
                }
            } else {
                try {
                    p.setProperty("number", "1");
                    p.store(new FileWriter(pathname), "KeyNumberGenerator UNIQUE NUMBER // DO NOT DELETE");
                } catch (IOException e) {
                    System.out.println("Could not save important properties! Please try again, or change location of properties file to somewhere with access.");
                    e.printStackTrace();
                }
                try {
                    FileReader reader = new FileReader(pathname);
                    p.load(reader);
                    number = Integer.parseInt(p.getProperty("number"));
                } catch (IOException e) {
                    System.out.println("Could not read important properties! Please try again, or change location of properties file to somewhere with access.");
                    e.printStackTrace();
                }
            }
        return number;
    }

    public void generate() {
        if (getOS.isWindows()) {
            pathname = System.getenv("APPDATA") + "/number.properties";
        }
        if (getOS.isMac()) {
            pathname = System.getProperty("user.home") + "/number.properties";
        }

        System.out.println("Key Number:");
        keyNumber = market + year + writerInitial + '-' + clientInitial + '-' + duration + type + '-' + number;
        System.out.println(keyNumber);

        try {
            number++;
            Properties p = new Properties();
            p.setProperty("number", String.valueOf(number));
            p.store(new FileWriter(pathname), "KeyNumberGenerator UNIQUE NUMBER // DO NOT DELETE");
        } catch (IOException e) {
            System.out.println("Could not save important properties! Please try again, or change location of properties file to somewhere with access.");
            e.printStackTrace();

        }

        StringSelection selection = new StringSelection(keyNumber);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }

    public static void main(String[] args) throws IOException {
        KeyNumberGenerator keyNumberGenerator = new KeyNumberGenerator();

        GUI gui = new GUI();
        /*
        Scanner input = new Scanner(System.in);

        // Rearrange this later (year after market).
        System.out.println("What year?:");
        while (!keyNumberGenerator.yearReady) {
            try {
                keyNumberGenerator.year = Integer.parseInt(input.nextLine());
                keyNumberGenerator.yearReady = true;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.out.println("That is not a valid number! Please enter again:");
            }
        }

        keyNumberGenerator.properties();

        System.out.println("What market?" + Arrays.toString(keyNumberGenerator.markets) + ':');
        while (!keyNumberGenerator.marketReady) {
            String marketInput = input.nextLine();
            keyNumberGenerator.setMarket(marketInput);
        }

        // Add cI functionality to wI
        System.out.println("Type writer initials or name:");
        String writerInitialsInput = input.nextLine();
        keyNumberGenerator.getwI(writerInitialsInput);

        System.out.println("Type client initials or name:");
        String clientInitialsInput = input.nextLine();
        keyNumberGenerator.getcI(clientInitialsInput);

        System.out.println("What duration?:" + Arrays.toString(keyNumberGenerator.durations) + ':');
        while (!keyNumberGenerator.durationReady) {
            int durationInput = Integer.parseInt(input.nextLine());
            keyNumberGenerator.setDuration(durationInput);
        }

        System.out.println("What type?" + Arrays.toString(keyNumberGenerator.types) + ':');
        while (!keyNumberGenerator.typeReady) {
            String typeInput = input.nextLine();
            keyNumberGenerator.setType(typeInput);
        }

        System.out.println("Here is your key number! (Copied to clipboard)");
        keyNumberGenerator.print();
         */
    }
}