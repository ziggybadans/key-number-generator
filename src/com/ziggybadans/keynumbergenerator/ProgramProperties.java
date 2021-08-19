package com.ziggybadans.keynumbergenerator;

import java.io.*;
import java.util.Map;
import java.util.Properties;

public class ProgramProperties {
    Properties p;
    final String[] props = {"market", "year", "writer_initial", "duration", "type", "client_initial", "number"};
    private Map<String, String> defaultProps = Map.ofEntries(Map.entry("market", "AUG"), Map.entry("year", "Type year"),
            Map.entry("writer_initial", "Type name"), Map.entry("duration", "10"), Map.entry("type", "R"),
            Map.entry("client_initial", "Type name"), Map.entry("number", "1"));

    File getFile;
    boolean exists;

    boolean debug = true;

    ProgramProperties() {
        try {
            if (getOS.isWindows()) {
                KeyNumberGenerator.pathname = System.getenv("APPDATA") + "/number.properties";
            } else if (getOS.isMac()) {
                KeyNumberGenerator.pathname = System.getProperty("user.home") + "/number.properties";
            }
        } catch (Exception e) {
            System.out.println("The directory for properties has restricted access or is missing. " +
                    "Please try again, or change location of properties file to somewhere with access.");
        }

        System.out.println("Pathname: " + KeyNumberGenerator.pathname);

        getFile = new File(KeyNumberGenerator.pathname);
        p = new Properties();
    }

    void loadProperties() {
        if (debug) {
            System.out.println("");
            System.out.println("This is the loadProperties function!");
        }
        exists = getFile.exists();

        if (exists) {
            try {
                p.load(new FileInputStream(KeyNumberGenerator.pathname));
                if (debug) { System.out.println("Properties: " + p); }
            } catch (IOException e) {
                System.out.println("Could not access default path, because it either doesn't exist or is protected. Please set path someplace else.");
            } // Maybe add NumberFormatException here later
        } else {
            System.out.println("Error: The properties file is missing! Creating new properties file...");
            for (String prop : props) {
                p.setProperty(prop, defaultProps.get(prop));
            } if (debug) { System.out.println("Properties: " + p); }
            try {
                p.store(new FileWriter(KeyNumberGenerator.pathname), "KeyNumberGenerator UNIQUE NUMBER // DO NOT DELETE");
            } catch (IOException e) {
                System.out.println("Could not save important properties! Please try again, or change location of properties file to somewhere with access.");
            }
        }
    }

    String getProperty(String key) {
        if (debug) {
            System.out.println("");
            System.out.println("This is the getProperty function!");
            System.out.println("Input: " + key);
        }
        return p.getOrDefault(key, defaultProps.get(key)).toString();
    }

    void setProperties(String key, String input) {
        if (debug) {
            System.out.println("");
            System.out.println("This is the setProperties function!");
            System.out.println("Key: " + key + " -- " + "Input: " + input);
        }

        if (input.contains("Type ")) {
            System.out.println("Error: This value cannot be set as default!");
        } else {
            if (debug) { System.out.println("Before update: " + p); }
            p.setProperty(key, input);
            if (debug) { System.out.println("After update: " + p); }
        }
        try {
            p.store(new FileWriter(KeyNumberGenerator.pathname), "KeyNumberGenerator UNIQUE NUMBER // DO NOT DELETE");
        } catch (IOException e) {
            System.out.println("Could not save important properties! Please try again, or change location of properties file to somewhere with access.");
        }
    }
}
