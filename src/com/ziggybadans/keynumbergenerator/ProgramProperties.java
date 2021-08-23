package com.ziggybadans.keynumbergenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

/**
 * The program properties class is all the methods for storing, loading, getting, and writing to the properties files for this program to store user's preferences long-term.
 * The path for the properties file is settable. In case the properties file cannot be read, the program uses a hardcoded {@code Map} with the default properties.
 * @see java.util.Properties
 * @see java.util.Map
 */
public class ProgramProperties {
    /**
     * This allows the properties to be created once per run and accessed everywhere.
     */
    Properties p;
    /**
     * The strings of the keys of all properties.
     */
    final String[] props = {"market", "year", "writer_initial", "duration", "type", "client_initial", "number"};
    /**
     * A {@code Map} of the default properties.
     */
    private final Map<String, String> defaultProps = Map.ofEntries(Map.entry("market", "AUG"), Map.entry("year", "Type year"),
            Map.entry("writer_initial", "Type name"), Map.entry("duration", "10"), Map.entry("type", "R"),
            Map.entry("client_initial", "Type client name"), Map.entry("number", "1"));

    /**
     * This is the path for the main properties file.
     */
    public static String pathname;
    /**
     * If the user sets a custom directory for the properties file, then this pathname is used for getting that custom directory from a file at this default path.
     */
    public static String default_pathname;

    /**
     * These are all the errors for properties, for easy creation of error messages in the {@code GUI}.
     * @see GUI
     */
    public static String[] errors = {"access", "save", "read", "setPath", "readPath", "oldProperties"};

    File getProperties;

    boolean debug = false;

    /**
     * The constructor for the properties logic.
     * This method checks firstly if the OS is Windows or Mac, and gets the {@code default_pathname} from that info, adding the directory "keynumbergenerator" on the end.
     * It then makes a directory at the default path if there isn't one already, and checks if there is a {@code path.properties} file. If there is, it reads the file and sets {@code pathname} to the path in the file. If there isn't, it sets {@code pathname} to {@code default_pathname}.
     * Afterwards, it creates a new {@code Properties} instance.
     */
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

    /**
     * This method loads the properties for the program. It is only invoked when a program is run, and when the user clicks the {@code reload} button in the {@code GUI}.
     * It firstly checks if the properties file exists. If it does, it loads the file into the {@code Properties} instance. If it doesn't, it loads all the values from {@code defaultProps} and writes those to a new properties file.
     * It finally sets {@code KeyNumberGenerator.number} to the number in properties, the default being 1.
     * @see KeyNumberGenerator
     */
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

    /**
     * This method gets a value for a certain key in the properties. It then gets that value, or if not applicable, gets the default value from {@code defaultProps}.
     * @param key the key to get the value associated with in the {@code Properties} instance.
     * @return the string that is the value associated with the key.
     */
    String getProperty(String key) {
        if (debug) {
            System.out.println("");
            System.out.println("This is the getProperty function!");
            System.out.println("Input: " + key);
        }
        return p.getOrDefault(key, defaultProps.get(key)).toString();
    }

    /**
     * This method sets a certain value of a certain key in the properties. It firstly checks if the input contains any string indicating that there i s no user input, and if so, just sets the property to default.
     * If there is a user input, it sets the property, then tries to write it to the property file.
     * @param key the key to set the value for.
     * @param input the value to set for that key.
     */
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

    /**
     * This method sets a custom path for the properties file. It firstly creates the necessary path file at the default directory, inside which it writes the custom path selected. It then tries to delete the old properties file.
     * After this, it creates a new file at the new custom directory, and stores the properties in it. Because the {@code Properties} instance hasn't been altered, all the properties that where previously there are carried along to the new file.
     * @param path the path to set the directory to, taken from the filePicker in {@code GUI}.
     */
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
