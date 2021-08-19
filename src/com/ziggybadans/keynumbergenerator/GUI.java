package com.ziggybadans.keynumbergenerator;

import net.java.balloontip.BalloonTip;
import net.java.balloontip.utils.FadingUtils;
import net.java.balloontip.utils.TimingUtils;

import javax.swing.*;
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

public class GUI implements ActionListener {

    KeyNumberGenerator main = new KeyNumberGenerator();

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

    public GUI() {
        frame = new JFrame("Key Number Generator");

        frame.setSize(850, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLayeredPane panel = new JLayeredPane();
        frame.add(panel);

        placeComponents(panel);

        updateFields();

        frame.setVisible(true);
    }

    class SetPropertiesButton extends JButton {
        public SetPropertiesButton(String key, JTextField input) {
            super("Set prop.");
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    main.setProperties(key, input.getText());
                }
            });
        }

        public SetPropertiesButton(String key, JComboBox<?> input) {
            super("Set prop.");
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    main.setProperties(key, Objects.requireNonNull(input.getSelectedItem()).toString());
                }
            });
        }
    }

    private void updateFields() {
        marketMenu.setSelectedItem(main.getProperty("market"));
        yearInput.setText(main.getProperty("year"));
        if (yearInput.getText().equals("")) {
            yearInput.setText("Type year");
        }
        writerIInput.setText(main.getProperty("writer_initial"));
        if (writerIInput.getText().equals("")) {
            writerIInput.setText("Type name");
        }
        try {
            durationMenu.setSelectedItem(Integer.parseInt(main.getProperty("duration")));
        } catch (NumberFormatException e) {
            durationMenu.setSelectedIndex(0);
        }
        typeMenu.setSelectedItem(main.getProperty("type"));
        clientIInput.setText(main.getProperty("client_initial"));
        if (clientIInput.getText().equals("")) {
            clientIInput.setText("Type name");
        }
        numberOutput.setText(main.getProperty("number"));
    }

    public static void throwPropertiesError(String type) {
        switch (type) {
            case "access" ->
                    // Add path placement option here
                    JOptionPane.showMessageDialog(new JFrame(), "Could not access default path, because it either doesn't exist or is protected. Please set path someplace else.",
                            "Error", JOptionPane.ERROR_MESSAGE);
            case "save" -> JOptionPane.showMessageDialog(new JFrame(), "Could not save important properties! Please try again, or change location of properties file to somewhere with access.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            case "read" -> JOptionPane.showMessageDialog(new JFrame(), "Could not read important properties! Please try again, or change location of properties file to somewhere with access.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            // Change this to a balloon dialog eventually
            case "type..." -> JOptionPane.showMessageDialog(new JFrame(), "This is already the default value!");
        }
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
        place(backgroundButton, panel, 0, 0, 0, 1200, 600);

        JLabel marketLabel = new JLabel("Market");
        marketMenu = new JComboBox<>(KeyNumberGenerator.markets);
        marketMenu.setSelectedIndex(0);
        marketMenu.addActionListener(this);
        marketSP = new SetPropertiesButton("market", marketMenu);
        place(marketLabel, marketMenu, marketSP, panel, 50, 25, 80, 12);

        JLabel yearLabel = new JLabel("Year");
        yearInput = new JTextField("Type year");
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
                    System.out.println("Year: " + null);
                    main.setYear(null);
                } else if (yearInput.getText().equals("")) {
                    yearInput.setText("Type year");
                    System.out.println("Year: " + null);
                    main.setYear(null);
                } else {
                    System.out.println("Year: " + yearInput.getText());
                    main.setYear(yearInput.getText());
                    // Add balloon tooltip for invalid input
                }
            }
        });
        yearDocument = (PlainDocument) yearInput.getDocument();
        yearDocument.setDocumentFilter(new NumberFilter(4, false));
        yearSP = new SetPropertiesButton("year", yearInput);
        place(yearLabel, yearInput, yearSP, panel, 150, 25, 80, 12);

        JLabel wILabel = new JLabel("Writer");
        writerIInput = new JTextField("Type name");
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
                    System.out.println("Writer input: " + null);
                    main.setWriterI(null);
                } else if (writerIInput.getText().equals("")) {
                    writerIInput.setText("Type name");
                    System.out.println("Writer input: " + null);
                    main.setWriterI(null);
                } else {
                    System.out.println("Writer Input: " + writerIInput.getText());
                    main.setWriterI(writerIInput.getText());
                }
            }
        });
        writerISP = new SetPropertiesButton("writer_initial", writerIInput);
        place(wILabel, writerIInput, writerISP, panel, 250, 25, 80, 12);

        JLabel durationLabel = new JLabel("Duration");
        durationMenu = new JComboBox<>(KeyNumberGenerator.durations);
        durationMenu.setSelectedIndex(0);
        durationMenu.addActionListener(this);
        durationSP = new SetPropertiesButton("duration", durationMenu);
        place(durationLabel, durationMenu, durationSP, panel, 350, 25, 80, 12);

        JLabel typeLabel = new JLabel("Type");
        typeMenu = new JComboBox<>(KeyNumberGenerator.types);
        typeMenu.setSelectedIndex(0);
        typeMenu.addActionListener(this);
        typeSP = new SetPropertiesButton("type", typeMenu);
        place(typeLabel, typeMenu, typeSP, panel, 450, 25, 80, 12);

        JLabel cILabel = new JLabel("Client");
        clientIInput = new JTextField("Type client name");
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
                    System.out.println("Client: " + null);
                    main.setClientI(null);
                } else if (clientIInput.getText().equals("")) {
                    clientIInput.setText("Type client name");
                    System.out.println("Client: " + null);
                    main.setClientI(null);
                } else {
                    System.out.println("Client: " + clientIInput.getText());
                    main.setClientI(clientIInput.getText());
                }
            }
        });
        clientISP = new SetPropertiesButton("client_initial", clientIInput);
        place(cILabel, clientIInput, clientISP, panel, 550, 25, 110, 12);

        JLabel numberLabel = new JLabel("Sequential Number");
        numberOutput = new JTextField();
        numberOutput.setEditable(false);
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
        preferences = new JMenu("Preferences");
        editProperties = new JMenuItem("Edit Properties...");
        editProperties.addActionListener(this);
        menuBar.add(preferences);
        preferences.add(editProperties);
        frame.setJMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // All of this probably won't run when the program first launches, making defaults broken
        if (event.getSource() == marketMenu) {
            System.out.println("Market: " + marketMenu.getSelectedItem());
            main.setMarket((String) marketMenu.getSelectedItem());
        } else if (event.getSource() == durationMenu) {
            try {
                System.out.println("Duration: " + durationMenu.getSelectedItem());
                main.setDuration((int) durationMenu.getSelectedItem());
            } catch (NullPointerException e) {
                durationMenu.setSelectedItem(KeyNumberGenerator.durations[0]);
                System.out.println("Duration: " + durationMenu.getSelectedItem());
                main.setDuration((int) durationMenu.getSelectedItem());
            }
        } else if (event.getSource() == typeMenu) {
            System.out.println("Type: " + typeMenu.getSelectedItem());
            main.setType((String) typeMenu.getSelectedItem());
        } else if (event.getSource() == generateButton) {
            main.setMarket((String) marketMenu.getSelectedItem());
            main.setDuration((int) durationMenu.getSelectedItem());
            main.setType((String) typeMenu.getSelectedItem());
            System.out.println("Debug: Attempting to generate...");
            generateOutput = main.generate();
            generateResult.setText("");
            generateResult.setText(generateOutput);
            numberOutput.setText(main.getProperty("number"));
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
        } else if (event.getSource() == editProperties) {
            System.out.println("test");
            File properties = new File(KeyNumberGenerator.pathname);
            try {
                if (!Desktop.isDesktopSupported()) {
                    System.out.println("Desktop not supported.");
                    JOptionPane.showMessageDialog(new JFrame(), "Desktop not supported.",
                            "Couldn't open properties", JOptionPane.ERROR_MESSAGE);
                } else {
                    Desktop desktop = Desktop.getDesktop();
                    if (properties.exists()) {
                        desktop.open(properties);
                    } else {
                        main.getProperty("number");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(new JFrame(), "An error occurred while trying to access the properties file.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    static class CharacterFilter extends DocumentFilter {
        boolean upper = false;
        // Removing this limit initializer, if there's a bug change it back
        int limit;

        public CharacterFilter(int limit) {
            System.out.println("Limit: " + limit);
            this.limit = limit;
        }

        public CharacterFilter(int limit, boolean upper) {
            System.out.println("Limit: " + limit + "(Upper: " + upper + ')');
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
            System.out.println("Replace called!");
        }

        private String revise(FilterBypass fb, String text) {
            System.out.println("Revise called!");
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
        // Removing this limit initializer, if there's a bug change it back
        int limit;
        boolean number;

        public NumberFilter(int limit, boolean number) {
            System.out.println("Limit: " + limit + "(Number: " + number + ')');
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
                System.out.println("Replace called!");
            } else {
                super.replace(fb, offset, length, text, attr);
            }
        }

        private String revise(FilterBypass fb, String text) {

            System.out.println("Revise called!");

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