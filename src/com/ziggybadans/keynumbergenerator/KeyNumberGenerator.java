package com.ziggybadans.keynumbergenerator;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.Properties;

public class KeyNumberGenerator {

    public int year = -1;
    public boolean yearReady;

    public static String[] markets = {"AUG", "BAL", "BEG", "BEN", "BER", "BOW", "BUN", "BUR", "CAI", "DAR", "DEV", "GEE", "GLA", "GOL", "HOB", "IPS", "LAU", "LIN", "MAC",
            "MAR", "MIL", "MUR", "MUS", "NOW", "PIR", "QUE", "ROC", "TOW", "WOL", "TRSN", "AGEN"};
    public boolean marketReady = false;
    public String market;

    public char writerInitial;

    public static Integer[] durations = {10, 15, 30, 45, 60, 90};
    public boolean durationReady = false;
    public int duration = -1;

    public static String[] types = {"R", "L", "SL", "X", "SX"};
    public boolean typeReady = false;
    public String type;

    public String clientInitial;

    public static int number = 0;
    public static String pathname;
    public String keyNumber;

    KeyNumberGenerator() {
    }

    public void setMarket(String input) {
        int tempMarket;
        try {
            tempMarket = java.util.Arrays.asList(markets).indexOf(input);
            market = markets[tempMarket];
            marketReady = true;
            System.out.println("Debug: " + market + " - Successful!");
        }
        catch (Exception e) {
            System.out.println("Debug: " + input + " - Invalid.");
        }
    }

    // Get current year for this later.
    public void setYear(String input) {
        CharConcatentation charConcat = new CharConcatentation();
        try {
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
        } catch (NullPointerException e) {
            System.out.println("Input is null! Resetting...");
            year = -1;
        }

    }

    public void setWriterI(String input) {
        try {
            writerInitial = input.toUpperCase(Locale.ROOT).charAt(0);
            System.out.println("Debug: " + writerInitial + " - Successful!");
        } catch (NullPointerException e) {
            System.out.println("Input is null! Resetting...");
            writerInitial = '\0';
        }

    }

    public void setDuration(int input) {
        int tempDuration;
        try {
            // Check this
            tempDuration = Arrays.asList(durations).indexOf(input);
            //System.out.println("Debug: " + tempDuration);
            duration = durations[tempDuration];
            durationReady = true;
            System.out.println("Debug: " + duration + " - Successful!");
        }
        catch (Exception e) {
            System.out.println("Debug: " + input + " - Invalid.");
        }
    }

    public void setType(String input) {
        int tempType;
        try {
            tempType = java.util.Arrays.asList(types).indexOf(input);
            type = types[tempType];
            typeReady = true;
            System.out.println("Debug " + type + " - Successful!");
        }
        catch (Exception e) {
            System.out.println("Debug: " + input + " - Invalid.");
        }
    }

    public void setClientI(String input) {
        try {
            CharConcatentation charConcat = new CharConcatentation();
            if (input.length() == 2) {
                char firstClientInitial = input.toUpperCase(Locale.ROOT).charAt(0);
                char secondClientInitial = input.toUpperCase(Locale.ROOT).charAt(1);

                clientInitial = charConcat.concat(firstClientInitial, secondClientInitial);
            } else {
                int secondInitialIndex = input.toUpperCase(Locale.ROOT).indexOf(" ") + 1;
                char firstClientInitial = input.toUpperCase(Locale.ROOT).charAt(0);
                char secondClientInitial = input.toUpperCase(Locale.ROOT).charAt(secondInitialIndex);

                clientInitial = charConcat.concat(firstClientInitial, secondClientInitial);
            }
            System.out.println("Debug: " + clientInitial + " - Successful!");
        } catch (NullPointerException e) {
            System.out.println("Input is null! Resetting...");
            clientInitial = null;
        }

    }

    public static int getProperties() {
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
                    System.out.println("Debug: Read properties successfully!");
                } catch (IOException e) {
                    System.out.println("Could not access default path, because it either doesn't exist or is protected. Please set path someplace else.");
                    e.printStackTrace();
                }
            } else {
                try {
                    System.out.println("Debug: Properties file doesn't exist. Creating new file.");
                    p.setProperty("number", "1");
                    p.store(new FileWriter(pathname), "KeyNumberGenerator UNIQUE NUMBER // DO NOT DELETE");
                    System.out.println("Debug: Created properties successfully!");
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

    public String get(String input) {
        return Objects.requireNonNullElse(input, "NULL");
    }

    public String get(int input) {
        if (input != -1) {
            return String.valueOf(input);
        } else {
            return "NULL";
        }
    }

    public String get(char input) {
        if (input != '\0') {
            return String.valueOf(input);
        } else {
            return "NULL";
        }
    }

    public String generate() {
        if (getOS.isWindows()) {
            pathname = System.getenv("APPDATA") + "/number.properties";
        }
        if (getOS.isMac()) {
            pathname = System.getProperty("user.home") + "/number.properties";
        }

        System.out.println("Key Number:");
        //keyNumber = market + year + writerInitial + '-' + clientInitial + '-' + duration + type + '-' + number;
        keyNumber = get(market) + get(year) + get(writerInitial) + '-' + get(clientInitial) + '-' + get(duration) + get(type) + '-' + number;
        System.out.println(keyNumber);

        try {
            number++;
            Properties p = new Properties();
            p.setProperty("number", String.valueOf(number));
            p.store(new FileWriter(pathname), "KeyNumberGenerator UNIQUE NUMBER // DO NOT DELETE");
            System.out.println("Debug: Updated properties successfully!");
        } catch (IOException e) {
            System.out.println("Could not save important properties! Please try again, or change location of properties file to somewhere with access.");
            e.printStackTrace();

        }

        StringSelection selection = new StringSelection(keyNumber);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);

        return keyNumber;
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
    }
}