package com.ziggybadans.keynumbergenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class ProgramProperties {
    Properties p;
    final String[] props = {"market", "year", "writer_initial", "duration", "type", "client_initial", "number"};
    private final Map<String, String> defaultProps = Map.ofEntries(Map.entry("market", "AUG"), Map.entry("year", "Type year"),
            Map.entry("writer_initial", "Type name"), Map.entry("duration", "10"), Map.entry("type", "R"),
            Map.entry("client_initial", "Type client name"), Map.entry("number", "1"));

    public static String pathname;
    public static String default_pathname;

    public static String[] errors = {"access", "save", "read", "setPath", "readPath", "oldProperties"};

    File getProperties;

    boolean debug = false;

    ProgramProperties() {
        try {
            if (getOS.isWindows()) {
                default_pathname = System.getenv("APPDATA") + System.getProperty("file.separator") + "keynumbergenerator";
            } else if (getOS.isMac()) {
                default_pathname = System.getProperty("user.home") + System.getProperty("file.separator") + "keynumbergenerator";
            }
        } catch (Exception e) {
            System.out.println("Something went wrong!");
        }

        new File(default_pathname).mkdir();
        boolean exists = new File(default_pathname + "/path.properties").exists();
            if (exists) {
                try {
                    File file = new File(default_pathname + "/path.properties");
                    Scanner scanner = new Scanner(file);
                    int i = 0;
                    while (i <= 1) {
                        pathname = scanner.nextLine();
                        i++;
                    }
                    scanner.close();
                } catch (IOException e) {
                    System.out.println("Something went wrong reading the file at " + default_pathname + "/path.properties");
                    pathname = default_pathname + "/keynumbergenerator.properties";
                    GUI.throwPropertiesError(errors[4]);
                }
            } else {
                pathname = default_pathname + "/keynumbergenerator.properties";
                System.out.println("No custom directory selected! Using default directory.");
            }
        System.out.println("Pathname: " + pathname);
        p = new Properties();
    }

    void loadProperties() {
        if (debug) {
            System.out.println("");
            System.out.println("This is the loadProperties function!");
        }

        getProperties = new File(pathname);
        boolean exists = getProperties.exists();

        if (exists) {
            try {
                FileInputStream reader = new FileInputStream(pathname);
                p.load(reader);
                if (debug) { System.out.println("Properties: " + p); }
                reader.close();
            } catch (IOException e) {
                System.out.println("Could not access default path, because it either doesn't exist or is protected. Please set path someplace else.");
                GUI.throwPropertiesError(errors[0]);
            } // Maybe add NumberFormatException here later
        } else {
            System.out.println("Error: The properties file is missing! Creating new properties file...");
            for (String prop : props) {
                p.setProperty(prop, defaultProps.get(prop));
            } if (debug) { System.out.println("Properties: " + p); }
            try {
                FileWriter writer = new FileWriter(pathname);
                p.store(writer, "KeyNumberGenerator UNIQUE NUMBER // DO NOT DELETE");
                writer.close();
            } catch (IOException e) {
                System.out.println("Could not save important properties! Please try again, or change location of properties file to somewhere with access.");
                GUI.throwPropertiesError(errors[1]);
            }
        }
        KeyNumberGenerator.number = Integer.parseInt(p.getProperty("number"));
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
            p.setProperty(key, defaultProps.get(key));
        } else {
            if (debug) { System.out.println("Before update: " + p); }
            p.setProperty(key, input);
            if (debug) { System.out.println("After update: " + p); }
        }
        try {
            FileWriter writer = new FileWriter(pathname);
            System.out.println(pathname);
            p.store(writer, "KeyNumberGenerator UNIQUE NUMBER // DO NOT DELETE");
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not save important properties! Please try again, or change location of properties file to somewhere with access.");
            GUI.throwPropertiesError(errors[1]);
        }
    }

    void setPathname(String path) {
        System.out.println("");
        System.out.println("This is the setPathname function!");
        try {
            System.out.println("Old path: " + pathname);
            System.out.println("Selected path: " + path);
            FileWriter pathWriter = new FileWriter(default_pathname + "/path.properties");
            pathWriter.write("BEGIN");
            pathWriter.write(System.lineSeparator());
            pathWriter.write(path + "/keynumbergenerator.properties");
            pathWriter.write(System.lineSeparator());
            pathWriter.write("END");
            pathWriter.close();
            File file = new File(pathname);
            if (file.delete()) {
                System.out.println("Old properties file has been deleted.");
            } else {
                System.out.println("Old properties file failed to be deleted.");
                GUI.throwPropertiesError(errors[5]);
            }
            pathname = path + "/keynumbergenerator.properties";
        } catch (IOException e) {
            System.out.println("Could not set path! Access may be denied. Please try again with a different location.");
            GUI.throwPropertiesError(errors[3]);
        }
        try {
            System.out.println("New path: " + pathname);
            FileWriter writer = new FileWriter(pathname);
            p.store(writer, "KeyNumberGenerator UNIQUE NUMBER // DO NOT DELETE");
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not save important properties! Please try again, or change location of properties file to somewhere with access.");
            GUI.throwPropertiesError(errors[1]);
        }
    }
}
