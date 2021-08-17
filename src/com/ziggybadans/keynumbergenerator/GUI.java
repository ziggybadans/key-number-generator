package com.ziggybadans.keynumbergenerator;

import net.java.balloontip.BalloonTip;
import net.java.balloontip.utils.FadingUtils;
import net.java.balloontip.utils.TimingUtils;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class GUI<KeyNumberGenerator> implements ActionListener {

    JFrame frame;
    JButton backgroundButton;

    JComboBox<String> marketMenu = new JComboBox<>(KeyNumberGenerator.markets);
    public static JTextField yearInput;
    JTextField writerIInput;
    JComboBox<Integer> durationMenu;
    JComboBox<String> typeMenu;
    JTextField clientIInput;
    JTextField numberOutput;

    JButton generateButton;
    String generateOutput;
    public static JTextField generateResult;

    public static boolean yearFieldActive;

    PlainDocument generateDocument;
    PlainDocument yearDocument;
    PlainDocument writerIDocument;
    PlainDocument clientIDocument;

    JMenuBar menuBar;
    JMenu preferences;
    JMenuItem editProperties;

    KeyNumberGenerator main = new KeyNumberGenerator();

    public GUI() {
        frame = new JFrame("Key Number Generator");

        frame.setSize(850, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLayeredPane panel = new JLayeredPane();
        frame.add(panel);

        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JLayeredPane panel) {
        panel.setLayout(null);

        backgroundButton = new JButton();
        backgroundButton.setBounds(0, 0, 1200, 600);
        backgroundButton.setOpaque(false);
        backgroundButton.setContentAreaFilled(false);
        backgroundButton.setBorderPainted(false);
        backgroundButton.addActionListener(this);
        panel.add(backgroundButton);
        panel.setLayer(backgroundButton, 0, 0);

        JLabel marketLabel = new JLabel("Market");
        marketLabel.setBounds(50, 25, 80, 12);
        panel.add(marketLabel);
        panel.setLayer(marketLabel, 1, 0);
        marketMenu.setSelectedIndex(0);
        marketMenu.setBounds(50, 50, 90, 20);
        marketMenu.addActionListener(this);
        panel.add(marketMenu);
        panel.setLayer(marketMenu, 1, 0);

        JLabel yearLabel = new JLabel("Year");
        yearLabel.setBounds(150, 25, 80, 12);
        panel.add(yearLabel);
        panel.setLayer(yearLabel, 1, 0);
        yearInput = new JTextField("Type year");
        yearInput.setBounds(150, 50, 90, 20);
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
        panel.add(yearInput);
        panel.setLayer(yearInput, 1, 0);

        System.out.println(yearInput.getBounds());

        JLabel wILabel = new JLabel("Writer");
        wILabel.setBounds(250, 25, 80, 12);
        panel.add(wILabel);
        panel.setLayer(wILabel, 1, 0);
        writerIInput = new JTextField("Type name");
        writerIInput.setBounds(250, 50, 90, 20);
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
        panel.add(writerIInput);
        panel.setLayer(writerIInput, 1, 0);

        JLabel durationLabel = new JLabel("Duration");
        durationLabel.setBounds(350, 25, 80, 12);
        panel.add(durationLabel);
        panel.setLayer(durationLabel, 1, 0);
        durationMenu = new JComboBox<>(KeyNumberGenerator.durations);
        durationMenu.setSelectedIndex(0);
        durationMenu.setBounds(350, 50, 90, 20);
        durationMenu.addActionListener(this);
        panel.add(durationMenu);
        panel.setLayer(durationMenu, 1, 0);

        JLabel typeLabel = new JLabel("Type");
        typeLabel.setBounds(450, 25, 80, 12);
        panel.add(typeLabel);
        panel.setLayer(typeLabel, 1, 0);
        typeMenu = new JComboBox<>(KeyNumberGenerator.types);
        typeMenu.setSelectedIndex(0);
        typeMenu.setBounds(450, 50, 90, 20);
        typeMenu.addActionListener(this);
        panel.add(typeMenu);
        panel.setLayer(typeMenu, 1, 0);

        JLabel cILabel = new JLabel("Client");
        cILabel.setBounds(550, 25, 80, 12);
        panel.add(cILabel);
        panel.setLayer(cILabel, 1, 0);
        clientIInput = new JTextField("Type client name");
        clientIInput.setBounds(550, 50, 110, 20);
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
        panel.add(clientIInput);
        panel.setLayer(clientIInput, 1, 0);

        JLabel numberLabel = new JLabel("Sequential Number");
        numberLabel.setBounds(670, 25, 110, 12);
        panel.add(numberLabel);
        panel.setLayer(numberLabel, 1, 0);
        numberOutput = new JTextField(String.valueOf(KeyNumberGenerator.getProperties()));
        numberOutput.setBounds(670, 50, 50, 20);
        numberOutput.setEditable(false);
        panel.add(numberOutput);
        panel.setLayer(numberOutput, 1, 0);

        generateButton = new JButton("Generate");
        generateButton.setBounds(375, 100, 100, 20);
        generateButton.addActionListener(this);
        panel.add(generateButton);
        panel.setLayer(generateButton, 1, 0);
        generateResult = new JTextField();
        generateResult.setBounds(302, 150, 250, 20);
        generateDocument = (PlainDocument) generateResult.getDocument();
        generateDocument.setDocumentFilter(new CharacterFilter(30, true));
        panel.add(generateResult);
        panel.setLayer(generateResult, 1, 0);

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
        numberOutput.setText(String.valueOf(KeyNumberGenerator.getProperties()));
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
            numberOutput.setText(String.valueOf(KeyNumberGenerator.getProperties()));
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
                        KeyNumberGenerator.getProperties();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(new JFrame(), "An error occurred while trying to access the properties file.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static class CharacterFilter extends DocumentFilter {
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

    public static class NumberFilter extends DocumentFilter {
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

