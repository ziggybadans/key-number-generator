package com.ziggybadans.keynumbergenerator;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

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
    public String keyNumber;

    boolean debug = true;

    KeyNumberGenerator() {
    }

    public void setMarket(String input) {
        int tempMarket;
        if (input.equals("NULL")) {
            market = null;
            marketReady = false;
        }
        try {
            tempMarket = java.util.Arrays.asList(markets).indexOf(input);
            market = markets[tempMarket];
            marketReady = true;
            if (debug) { System.out.println("Debug: " + market + " - Successful!"); }
        }
        catch (Exception e) {
            if (debug) { System.out.println("Debug: " + input + " - Invalid."); }
        }
    }

    // Get current year for this later.
    public void setYear(String input) {
        CharConcatentation charConcat = new CharConcatentation();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        System.out.println("Current year: " + currentYear);
        try {
            if (input.length() == 2 && Integer.parseInt(input) >= Integer.parseInt(Integer.toString(currentYear).substring(2,4))) {
                this.year = Integer.parseInt(input);
                this.yearReady = true;
                if (debug) { System.out.println("Debug1: " + this.year + " - Successful!"); }
            } else if (input.length() == 4 && Integer.parseInt(input) >= currentYear) {
                char firstYearChar = input.charAt(2);
                char secondYearChar = input.charAt(3);

                this.year = Integer.parseInt(charConcat.concat(firstYearChar, secondYearChar));
                this.yearReady = true;
                if (debug) { System.out.println("Debug2: " + this.year + " - Successful!"); }
            } else {
                this.yearReady = false;
                if (debug) { System.out.println("Debug: " + input + " - Invalid."); }
            }
        } catch (NullPointerException e) {
            if (debug) { System.out.println("Input is null! Resetting..."); }
            year = -1;
            this.yearReady = false;
        }
    }

    public void setWriterI(String input) {
        try {
            writerInitial = input.toUpperCase(Locale.ROOT).charAt(0);
            if (debug) { System.out.println("Debug: " + writerInitial + " - Successful!"); }
        } catch (NullPointerException e) {
            if (debug) { System.out.println("Input is null! Resetting..."); }
            writerInitial = '\0';
        }

    }

    public void setDuration(int input) {
        int tempDuration;
        if (input == 0) {
            duration = 0;
            durationReady = false;
        } else {
            try {
                // Check this
                tempDuration = Arrays.asList(durations).indexOf(input);
                duration = durations[tempDuration];
                durationReady = true;
                if (debug) { System.out.println("Debug: " + duration + " - Successful!"); }
            }
            catch (Exception e) {
                if (debug) { System.out.println("Debug: " + input + " - Invalid."); }
                durationReady = false;
            }
        }

    }

    public void setType(String input) {
        int tempType;
        if (input.equals("NULL")) {
            type = null;
            typeReady = false;
        }
        try {
            tempType = java.util.Arrays.asList(types).indexOf(input);
            type = types[tempType];
            typeReady = true;
            if (debug) { System.out.println("Debug " + type + " - Successful!"); }
        }
        catch (Exception e) {
            if (debug) { System.out.println("Debug: " + input + " - Invalid."); }
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
            if (debug) { System.out.println("Debug: " + clientInitial + " - Successful!"); }
        } catch (NullPointerException e) {
            if (debug) { System.out.println("Input is null! Resetting..."); }
            clientInitial = null;
        }
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
        System.out.println("Key Number:");
        keyNumber = get(market) + get(year) + get(writerInitial) + '-' + get(clientInitial) + '-' + get(duration) + get(type) + '-' + number;
        System.out.println(keyNumber);
        number++;

        StringSelection selection = new StringSelection(keyNumber);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);

        return keyNumber;
    }

    public static void main(String[] args) throws InterruptedException {
        new GUI();
    }
}