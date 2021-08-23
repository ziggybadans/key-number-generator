package com.ziggybadans.keynumbergenerator;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

/**
 * The KeyNumberGenerator class is the logic behind the program.
 * It stores hard-coded lists for the markets, durations, and types.
 * It handles the getting and setting of each variable.
 * It generates the key number and creates the GUI.
 */
public class KeyNumberGenerator {
    /**
     * Each mutable value has a variable to store the user's input and a boolean to check if the variable is valid and ready to be a part of the key number. If not, it is false.
     */

    public int year = -1;
    public boolean yearReady;

    /**
     * The inputs that are interacted through JComboBoxes use arrays to hard-code their values.
     */
    public static String[] markets = {"AUG", "BAL", "BEG", "BEN", "BER", "BOW", "BUN", "BUR", "CAI", "DAR", "DEV", "GEE", "GLA", "GOL", "HOB", "IPS", "LAU", "LIN", "MAC",
            "MAR", "MIL", "MUR", "MUS", "NOW", "PIR", "QUE", "ROC", "TOW", "WOL", "TRSN", "AGEN"};
    public boolean marketReady = false;
    public String market;

    public char writerInitial;
    public String clientInitial;

    public static Integer[] durations = {10, 15, 30, 45, 60, 90};
    public boolean durationReady = false;
    public int duration = -1;

    public static String[] types = {"R", "L", "SL", "X", "SX"};
    public boolean typeReady = false;
    public String type;

    /**
     * This number integer goes up by one every time a key number is generated, and is stored in properties by default.
     */
    public static int number = 0;
    String keyNumber;
    boolean debug = true;

    KeyNumberGenerator() {
    }

    /**
     * This method sets the market to the user's input from the {@code GUI}.
     * @param input the string to check against the {@code markets} array. If there is a match, it sets that value.
     */
    public void setMarket(String input) {
        if (input.equals("NULL")) {
            market = null;
            marketReady = false;
        } else {
            int tempMarket;
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
    }

    /**
     * This method sets {@code year} to the user's input from the {@code GUI}. It updates the minimum year by {@code Calender}.
     * A user can enter either the full year (eg. 2021) or a shortened version (eg. 21) and get the value suitable for the key number (eg. 21).
     * @param input the string to check against the current year. If there is a match, it ensures it is in the correct format, and sets that value. If there is a null value for {@code input}, it sets the year to {@code -1} and {@code yearReady} to {@code false}.
     * @see java.util.Calendar
     */
    public void setYear(String input) {
        CharConcatenation charConcat = new CharConcatenation();
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

    /**
     * This method sets {@code writerInitial} to the user's input from the {@code GUI}.
     * A user can either enter their full name (eg. Ziggy) or just the initial (eg. Z) and get the value suitable for the key number (eg. Z).
     * @param input the string to convert to an initial. If the value is null, it sets {@code writerInitial} to an escaped null value.
     */
    public void setWriterI(String input) {
        try {
            writerInitial = input.toUpperCase(Locale.ROOT).charAt(0);
            if (debug) { System.out.println("Debug: " + writerInitial + " - Successful!"); }
        } catch (NullPointerException e) {
            if (debug) { System.out.println("Input is null! Resetting..."); }
            writerInitial = '\0';
        }
    }

    /**
     * This method sets {@code duration} to the user's input from the {@code GUI}.
     * @param input the integer to check against the {@code durations} array. If there is a match, it sets that value.
     */
    public void setDuration(int input) {
        int tempDuration;
        if (input == 0) {
            duration = 0;
            durationReady = false;
        } else {
            try {
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

    /**
     * This method sets {@code type} to the user's input from the {@code GUI}.
     * @param input the string to check against the {@code types} array. If there is a match, it sets that value.
     */
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

    /**
     * This method sets {@code clientInitial} to the user's input from the {@code GUI}.
     * A user can either enter the full client name (eg. Ziggy Badans) or just the initials (eg. ZB) and get the value suitable for the key number (eg. ZB).
     * @param input the string to convert to two initials. If the value is null, it sets {@code clientInitial} to null.
     */
    public void setClientI(String input) {
        try {
            CharConcatenation charConcat = new CharConcatenation();
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

    /**
     * These methods check if the value of the associated variables are null.
     * @param input String, integer, or char to check for a null value.
     * @return a String that has the text "NULL" for the key number.
     */
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

    /**
     * This method generates the key number. An example key number looks like this: AUG21G-ZB-10R-1. After generating, it also copies the {@code keyNumber} automatically to the clipboard.
     * @return a String that is the key number.
     */
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