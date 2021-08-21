package com.ziggybadans.keynumbergenerator;

import net.java.balloontip.BalloonTip;
import net.java.balloontip.utils.FadingUtils;
import net.java.balloontip.utils.TimingUtils;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class GUI implements ActionListener {

    KeyNumberGenerator main = new KeyNumberGenerator();
    ProgramProperties properties = new ProgramProperties();

    JFrame frame;
    JButton backgroundButton;

    JComboBox<String> marketMenu;
    SetPropertiesButton marketSP;

    static JTextField yearInput;
    SetPropertiesButton yearSP;

    JTextField writerIInput;
    SetPropertiesButton writerISP;

    JComboBox<Integer> durationMenu;
    SetPropertiesButton durationSP;

    JComboBox<String> typeMenu;
    SetPropertiesButton typeSP;

    JTextField clientIInput;
    SetPropertiesButton clientISP;

    JTextField numberOutput;

    JButton generateButton;
    String generateOutput;
    static JTextField generateResult;

    static boolean yearFieldActive;

    PlainDocument generateDocument;
    PlainDocument yearDocument;
    PlainDocument writerIDocument;
    PlainDocument clientIDocument;

    JMenuBar menuBar;
    JMenu preferences;
    JMenuItem editProperties;
    JMenuItem locationProperties;
    final JFileChooser fileChooser = new JFileChooser();
    JMenuItem reload;

    boolean debug = false;
    boolean debugProperties = false;
    boolean debugFields = false;

    public GUI() throws InterruptedException {
        frame = new JFrame("Key Number Generator");

        frame.setSize(850, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLayeredPane panel = new JLayeredPane();
        frame.add(panel);

        properties.loadProperties();
        TimeUnit.SECONDS.sleep(1);

        placeComponents(panel);

        frame.setVisible(true);
    }

    class SetPropertiesButton extends JButton {
        public SetPropertiesButton(String key, JTextField input) {
            super("Set prop.");
            this.addActionListener(e -> {
                if (debugProperties) { System.out.println("Input: " + input.getText()); }
                properties.setProperties(key, input.getText());
            });
            this.setToolTipText("Sets the current value as the default");
        }

        public SetPropertiesButton(String key, JComboBox<?> input) {
            super("Set prop.");
            this.addActionListener(e -> {
                if (debugProperties) { System.out.println("Input: " + input.getSelectedItem()); }
                try {
                    properties.setProperties(key, Objects.requireNonNull(input.getSelectedItem()).toString());
                } catch (NullPointerException error) {
                    System.out.println("Failed!");
                }

            });
            this.setToolTipText("Sets the current value as the default");
        }
    }

    static class NullButton extends JButton {
        public NullButton(JComboBox<String> linked) {
            super("Make null");
            this.addActionListener(e -> {
                linked.addItem("NULL");
                linked.setSelectedItem("NULL");
            });
            this.setToolTipText("Sets the text field to null");
        }

        public NullButton(JComboBox<Integer> linked, boolean integer) {
            super("Make null");
            this.addActionListener(e -> {
                linked.addItem(0);
                linked.setSelectedItem(0);
            });
            this.setToolTipText("Sets the text field to null");
        }
    }

    public static void throwPropertiesError(String type) {
        switch (type) {
            case "access" ->
                    // Add path placement option here
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Could not access default path, because it either doesn't exist or is protected. Please set path someplace else.",
                            "Error", JOptionPane.ERROR_MESSAGE);
            case "save" -> JOptionPane.showMessageDialog(new JFrame(),
                    "Could not save important properties! Please try again, or change location of properties file to somewhere with access.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            case "read" -> JOptionPane.showMessageDialog(new JFrame(),
                    "Could not read important properties! Please try again, or change location of properties file to somewhere with access.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            // Change this to a balloon dialog eventually
            //case "type..." -> JOptionPane.showMessageDialog(new JFrame(), "This is already the default value!");
            case "setPath" -> JOptionPane.showMessageDialog(new JFrame(),
                    "Could not set path! Access may be denied. Please try again with a different location.",
                    "Error setting path", JOptionPane.ERROR_MESSAGE);
            case "readPath" -> JOptionPane.showMessageDialog(new JFrame(),
                    "Could not read the path file! Please try again, or delete the path file at '" +
                            ProgramProperties.default_pathname + "/path.properties" + "' and set the directory for properties again.",
                    "Error reading path file", JOptionPane.ERROR_MESSAGE);
            case "oldProperties" -> JOptionPane.showMessageDialog(new JFrame(),
                    "Old properties file failed to be deleted; please delete manually.");
            default -> JOptionPane.showMessageDialog(new JFrame(), "Something went wrong!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void place(JLabel label, JComboBox component, SetPropertiesButton linked, boolean nullButtonBoolean, boolean integer, JLayeredPane panel, int... bounds) {
        label.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
        component.setBounds(label.getX(), label.getY() + 25, label.getWidth(), label.getHeight() + 8);
        linked.setBounds(component.getX(), component.getY() + 135, component.getWidth(), component.getHeight() + 8);
        if (nullButtonBoolean) {
            NullButton nullButton;
            if (integer) {
                nullButton = new NullButton(component, true);
            } else {
                nullButton = new NullButton(component);
            }
            nullButton.setBounds(linked.getX(), linked.getY() + 100, linked.getWidth(), linked.getHeight());
            panel.add(nullButton);
            panel.setLayer(nullButton, 1, 0);
        }
        panel.add(label);
        panel.add(component);
        panel.add(linked);
        panel.setLayer(label, 1, 0);
        panel.setLayer(component, 1, 0);
        panel.setLayer(linked, 1, 0);
    }

    private void place(JLabel label, JComponent component, SetPropertiesButton linked, JLayeredPane panel, int... bounds) {
        label.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
        component.setBounds(label.getX(), label.getY() + 25, label.getWidth(), label.getHeight() + 8);
        linked.setBounds(component.getX(), component.getY() + 135, component.getWidth(), component.getHeight() + 8);
        panel.add(label);
        panel.add(component);
        panel.add(linked);
        panel.setLayer(label, 1, 0);
        panel.setLayer(component, 1, 0);
        panel.setLayer(linked, 1, 0);
    }

    private void place(JLabel label, JComponent component, JLayeredPane panel, int... bounds) {
        label.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
        component.setBounds(label.getX(), label.getY() + 25, label.getWidth(), label.getHeight() + 8);
        panel.add(label);
        panel.add(component);
        panel.setLayer(label, 1, 0);
        panel.setLayer(component, 1, 0);
    }

    private void place(JComponent component, JLayeredPane panel, int layer, int... bounds) {
        component.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
        panel.add(component);
        panel.setLayer(component, layer, 0);
    }

    private void placeComponents(JLayeredPane panel) {
        panel.setLayout(null);

        backgroundButton = new JButton();
        backgroundButton.setOpaque(false);
        backgroundButton.setContentAreaFilled(false);
        backgroundButton.setBorderPainted(false);
        backgroundButton.addActionListener(this);
        place(backgroundButton, panel, 0, 0, 0, 1200, 600);

        JLabel marketLabel = new JLabel("Market");
        marketMenu = new JComboBox<>(KeyNumberGenerator.markets);
        marketMenu.setSelectedItem(properties.getProperty("market"));
        marketMenu.addActionListener(this);
        marketMenu.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Objects.equals(marketMenu.getSelectedItem(), "NULL")) {
                    marketMenu.setSelectedIndex(0);
                    marketMenu.removeItem("NULL");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {}
        });
        marketSP = new SetPropertiesButton("market", marketMenu);
        place(marketLabel, marketMenu, marketSP, true, false, panel, 50, 25, 80, 12);

        JLabel yearLabel = new JLabel("Year");
        yearInput = new JTextField(properties.getProperty("year"));
        yearInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (yearInput.getText().equals("Type year")) {
                    yearInput.setText("");
                    yearFieldActive = true;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (yearInput.getText().equals("Type year")) {
                    if (debug) { System.out.println("Year: " + null); }
                } else if (yearInput.getText().equals("")) {
                    yearInput.setText("Type year");
                    if (debug) { System.out.println("Year: " + null); }
                }
            }
        });
        yearDocument = (PlainDocument) yearInput.getDocument();
        yearDocument.setDocumentFilter(new NumberFilter(4, false));
        yearSP = new SetPropertiesButton("year", yearInput);
        place(yearLabel, yearInput, yearSP, panel, 150, 25, 80, 12);

        JLabel wILabel = new JLabel("Writer");
        writerIInput = new JTextField(properties.getProperty("writer_initial"));
        writerIInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (writerIInput.getText().equals("Type name")) {
                    writerIInput.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (writerIInput.getText().equals("Type name")) {
                    if (debug) { System.out.println("Writer input: " + null); }
                } else if (writerIInput.getText().equals("")) {
                    writerIInput.setText("Type name");
                    if (debug) { System.out.println("Writer input: " + null); }
                }
            }
        });
        writerISP = new SetPropertiesButton("writer_initial", writerIInput);
        place(wILabel, writerIInput, writerISP, panel, 250, 25, 80, 12);

        JLabel durationLabel = new JLabel("Duration");
        durationMenu = new JComboBox<>(KeyNumberGenerator.durations);
        durationMenu.setSelectedItem(properties.getProperty("duration"));
        durationMenu.addActionListener(this);
        durationMenu.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Objects.equals(durationMenu.getSelectedItem(), 0)) {
                    durationMenu.setSelectedIndex(0);
                    durationMenu.removeItem(0);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {}
        });
        durationSP = new SetPropertiesButton("duration", durationMenu);
        place(durationLabel, durationMenu, durationSP, true, true, panel, 350, 25, 80, 12);

        JLabel typeLabel = new JLabel("Type");
        typeMenu = new JComboBox<>(KeyNumberGenerator.types);
        typeMenu.setSelectedItem(properties.getProperty("type"));
        typeMenu.addActionListener(this);
        typeMenu.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Objects.equals(typeMenu.getSelectedItem(), "NULL")) {
                    typeMenu.setSelectedIndex(0);
                    typeMenu.removeItem("NULL");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {}
        });
        typeSP = new SetPropertiesButton("type", typeMenu);
        place(typeLabel, typeMenu, typeSP, true, false, panel, 450, 25, 80, 12);

        JLabel cILabel = new JLabel("Client");
        clientIInput = new JTextField(properties.getProperty("client_initial"));
        clientIInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (clientIInput.getText().equals("Type client name")) {
                    clientIInput.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (clientIInput.getText().equals("Type client name")) {
                    if (debug) { System.out.println("Client: " + null); }
                } else if (clientIInput.getText().equals("")) {
                    clientIInput.setText("Type client name");
                    if (debug) { System.out.println("Client: " + null); }
                }
            }
        });
        clientISP = new SetPropertiesButton("client_initial", clientIInput);
        place(cILabel, clientIInput, clientISP, panel, 550, 25, 110, 12);

        JLabel numberLabel = new JLabel("Sequential Number");
        numberOutput = new JTextField(String.valueOf(KeyNumberGenerator.number));
        numberOutput.setEditable(false);
        numberOutput.setToolTipText("This value increases by 1 every time a key is generated");
        place(numberLabel, panel, 1, 670, 25, 110, 12);
        place(numberOutput, panel, 1, numberLabel.getX(), numberLabel.getY() + 25, 50, numberLabel.getHeight() + 8);

        generateButton = new JButton("Generate");
        generateButton.addActionListener(this);
        place(generateButton, panel, 1, 375, 100, 100, 20);
        generateResult = new JTextField();
        generateDocument = (PlainDocument) generateResult.getDocument();
        generateDocument.setDocumentFilter(new CharacterFilter(30, true));
        place(generateResult, panel, 1, 302, 150, 250, 20);

        menuBar = new JMenuBar();
        preferences = new JMenu("Advanced");
        reload = new JMenuItem("Reload");
        reload.addActionListener(this);
        reload.setToolTipText("Replaces all text fields currently edited with default values");
        editProperties = new JMenuItem("Edit Properties...");
        editProperties.addActionListener(this);
        editProperties.setToolTipText("Opens the default properties file in the default text editor");
        locationProperties = new JMenuItem("Change properties location...");
        locationProperties.setToolTipText("Sets a custom directory for the properties file");
        locationProperties.addActionListener(this);
        menuBar.add(preferences);
        menuBar.add(reload);
        preferences.add(editProperties);
        preferences.add(locationProperties);
        frame.setJMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == marketMenu) {
            if (debug) { System.out.println("Market: " + marketMenu.getSelectedItem()); }
            main.setMarket((String) marketMenu.getSelectedItem());
        }
        else if (event.getSource() == durationMenu) {
            try {
                if (debug) { System.out.println("Duration: " + durationMenu.getSelectedItem()); }
                main.setDuration((int) durationMenu.getSelectedItem());
            } catch (NullPointerException e) {
                durationMenu.setSelectedItem(KeyNumberGenerator.durations[0]);
                if (debug) { System.out.println("Duration: " + durationMenu.getSelectedItem()); }
                main.setDuration((int) durationMenu.getSelectedItem());
            }
        }
        else if (event.getSource() == typeMenu) {
            if (debug) { System.out.println("Type: " + typeMenu.getSelectedItem()); }
            main.setType((String) typeMenu.getSelectedItem());
        }
        else if (event.getSource() == generateButton) {
            if (clientIInput.getText().equals("Type client name")) {
                main.setClientI(null);
            } else if (clientIInput.getText().equals("")) {
                main.setClientI(null);
                clientIInput.setText("Type client name");
            } else {
                main.setClientI(clientIInput.getText());
            }
            if (writerIInput.getText().equals("Type name")) {
                main.setWriterI(null);
            } else if (writerIInput.getText().equals("")) {
                main.setWriterI(null);
                writerIInput.setText("Type name");
            } else {
                main.setWriterI(writerIInput.getText());
            }
            if (yearInput.getText().equals("Type year")) {
                main.setYear(null);
            } else if (yearInput.getText().equals("")) {
                main.setYear(null);
                yearInput.setText("Type year");
            } else {
                main.setYear(yearInput.getText());
            }

            main.setMarket((String) marketMenu.getSelectedItem());
            main.setDuration((int) durationMenu.getSelectedItem());
            main.setType((String) typeMenu.getSelectedItem());

            if (debug) { System.out.println("Debug: Attempting to generate..."); }
            generateOutput = main.generate();
            properties.setProperties("number", String.valueOf(KeyNumberGenerator.number));
            generateResult.setText("");

            generateResult.setText(generateOutput);
            numberOutput.setText(properties.getProperty("number"));

            if (!main.yearReady) {
                BalloonTip nullError = new BalloonTip(GUI.yearInput, "This input was left blank.");
                TimingUtils.showTimedBalloon(nullError, 2000, e -> FadingUtils.fadeOutBalloon(nullError,
                        e1 -> nullError.closeBalloon(), 500, 15));
            }
            if (main.writerInitial == '\0') {
                BalloonTip nullError = new BalloonTip(writerIInput, "This input was left blank.");
                TimingUtils.showTimedBalloon(nullError, 2000, e -> FadingUtils.fadeOutBalloon(nullError,
                        e1 -> nullError.closeBalloon(), 500, 15));
            }
            if (main.clientInitial == null) {
                BalloonTip nullError = new BalloonTip(clientIInput, "This input was left blank.");
                TimingUtils.showTimedBalloon(nullError, 2000, e -> FadingUtils.fadeOutBalloon(nullError,
                        e1 -> nullError.closeBalloon(), 500, 15));
            }
            if (!main.durationReady) {
                BalloonTip nullError = new BalloonTip(durationMenu, "This input was left blank.");
                TimingUtils.showTimedBalloon(nullError, 2000, e -> FadingUtils.fadeOutBalloon(nullError,
                        e1 -> nullError.closeBalloon(), 500, 15));
            }
            if (!main.marketReady) {
                BalloonTip nullError = new BalloonTip(marketMenu, "This input was left blank.");
                TimingUtils.showTimedBalloon(nullError, 2000, e -> FadingUtils.fadeOutBalloon(nullError,
                        e1 -> nullError.closeBalloon(), 500, 15));
            }
            if (!main.typeReady) {
                BalloonTip nullError = new BalloonTip(typeMenu, "This input was left blank.");
                TimingUtils.showTimedBalloon(nullError, 2000, e -> FadingUtils.fadeOutBalloon(nullError,
                        e1 -> nullError.closeBalloon(), 500, 15));
            }
        }
        else if (event.getSource() == editProperties) {
            System.out.println("test");
            File properties = new File(ProgramProperties.pathname);
            try {
                if (!Desktop.isDesktopSupported()) {
                    System.out.println("Desktop not supported.");
                    JOptionPane.showMessageDialog(new JFrame(), "Desktop not supported.",
                            "Couldn't open properties", JOptionPane.ERROR_MESSAGE);
                } else {
                    Desktop desktop = Desktop.getDesktop();
                    if (properties.exists()) {
                        desktop.open(properties);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(new JFrame(), "An error occurred while trying to access the properties file.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (event.getSource() == locationProperties) {
            // Make this a proper file selector later
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = fileChooser.showOpenDialog(frame);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String result = fileChooser.getSelectedFile().toString();
                properties.setPathname(result);
            }
        }
        else if (event.getSource() == reload) {
            JComboBox<?>[] comboBoxes = {marketMenu, durationMenu, typeMenu};
            String[] comboNames = {"market", "duration", "type"};
            JTextField[] textFields = {yearInput, writerIInput, clientIInput};
            String[] textFieldNames = {"year", "writer_initial", "client_initial"};

            properties.loadProperties();
            for (int i = 0; i < comboNames.length; i++) {
                String fetch = properties.getProperty(comboNames[i]);
                if (!fetch.equals(comboBoxes[i].getSelectedItem())) {
                    comboBoxes[i].setSelectedItem(fetch);
                }
            }
            for (int i = 0; i < textFieldNames.length; i++) {
                textFields[i].setText("");
                String fetch = properties.getProperty(textFieldNames[i]);
                if (!fetch.equals(textFields[i].getText())) {
                    textFields[i].setText(fetch);
                }
            }
            numberOutput.setText(properties.getProperty("number"));
        }
    }

    static class CharacterFilter extends DocumentFilter {
        boolean debugFilter = false;

        boolean upper = false;
        // Removing this limit initializer, if there's a bug change it back
        int limit;

        public CharacterFilter(int limit) {
            if (debugFilter) { System.out.println("Limit: " + limit); }
            this.limit = limit;
        }

        public CharacterFilter(int limit, boolean upper) {
            if (debugFilter) { System.out.println("Limit: " + limit + "(Upper: " + upper + ')'); }
            this.limit = limit;
            this.upper = upper;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
            super.insertString(fb, offset, text, attr);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attr) throws BadLocationException {
            super.replace(fb, offset, length, revise(fb, text), attr);
            if (debugFilter) { System.out.println("Replace called!"); }
        }

        private String revise(FilterBypass fb, String text) {
            if (debugFilter) { System.out.println("Revise called!"); }
            if (upper) {
                text = text.toUpperCase();
            }

            StringBuilder builder = new StringBuilder(text);
            int index = 0;
            while (index < builder.length()) {
                if (accept(builder.charAt(index)) && fb.getDocument().getLength() + text.length() <= limit) {
                    index++;
                } else {
                    builder.deleteCharAt(index);
                    BalloonTip acceptError = new BalloonTip(GUI.generateResult,
                            "Maximum length is 30 characters. Only numbers, letters, and dashes allowed.");
                    TimingUtils.showTimedBalloon(acceptError, 2000, e -> FadingUtils.fadeOutBalloon(acceptError,
                            e1 -> acceptError.closeBalloon(), 500, 15));
                }
            }
            return builder.toString();
        }

        public boolean accept(final char c) {
            return Character.isAlphabetic(c) || Character.isDigit(c) || c == '-' || c == '\0';
        }
    }

    static class NumberFilter extends DocumentFilter {
        boolean debugFilter = false;

        // Removing this limit initializer, if there's a bug change it back
        int limit;
        boolean number;


        public NumberFilter(int limit, boolean number) {
            if (debugFilter) { System.out.println("Limit: " + limit + "(Number: " + number + ')'); }
            this.limit = limit;
            this.number = number;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
            super.insertString(fb, offset, text, attr);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attr) throws BadLocationException {
            if (!text.equals("Type year")) {
                super.replace(fb, offset, length, revise(fb, text), attr);
                if (debugFilter) { System.out.println("Replace called!"); }
            } else {
                super.replace(fb, offset, length, text, attr);
            }
        }

        private String revise(FilterBypass fb, String text) {
            if (debugFilter) { System.out.println("Revise called!"); }

            StringBuilder builder = new StringBuilder(text);
            int index = 0;
            while (index < builder.length()) {
                if (accept(builder.charAt(index)) && fb.getDocument().getLength() + text.length() <= limit) {
                    index++;
                } else {
                        builder.deleteCharAt(index);
                        BalloonTip acceptError = new BalloonTip(GUI.yearInput,
                                "Maximum length is 4 characters. Only numbers allowed.");
                        TimingUtils.showTimedBalloon(acceptError, 2000, e -> FadingUtils.fadeOutBalloon(acceptError,
                                e1 -> acceptError.closeBalloon(), 500, 15));
                }
            }
            return builder.toString();
        }

        public boolean accept(final char c) {
            return Character.isDigit(c);
        }
    }
}