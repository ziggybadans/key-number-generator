package com.ziggybadans.keynumbergenerator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class GUI implements ActionListener {

    JFrame frame;

    JComboBox marketMenu;
    JTextField yearInput;
    JTextField wIInput;
    JComboBox durationMenu;
    JComboBox typeMenu;
    JTextField cIInput;
    JTextField numberOutput;

    KeyNumberGenerator main = new KeyNumberGenerator();

    public GUI() {
        frame = new JFrame("Key Number Generator");

        frame.setSize(1200,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);

        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel marketLabel = new JLabel("Market");
        marketLabel.setBounds(50, 25, 80, 12);
        panel.add(marketLabel);
        marketMenu = new JComboBox(KeyNumberGenerator.markets);
        marketMenu.setBounds(50,50,90,20);
        marketMenu.addActionListener(this);
        panel.add(marketMenu);

        JLabel yearLabel = new JLabel("Year");
        yearLabel.setBounds(150, 25, 80, 12);
        panel.add(yearLabel);
        yearInput = new JTextField("Type year");
        yearInput.setBounds(150, 50, 90, 20);
        yearInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (yearInput.getText().equals("Type year")) {
                    yearInput.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (yearInput.getText().equals("Type year")) {
                } else {
                    System.out.println("Year: " + yearInput.getText());
                    main.setYear(yearInput.getText());
                    // Add balloon tooltip for invalid input
                }
            }
        });
        panel.add(yearInput);

        JLabel wILabel = new JLabel("Writer");
        wILabel.setBounds(250, 25, 80, 12);
        panel.add(wILabel);
        wIInput = new JTextField("Type name");
        wIInput.setBounds(250, 50, 90, 20);
        wIInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (wIInput.getText().equals("Type name")) {
                    wIInput.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (wIInput.getText().equals("Type name")) {
                } else {
                    System.out.println("Writer Input: " + wIInput.getText());
                }
            }
        });
        panel.add(wIInput);

        JLabel durationLabel = new JLabel("Duration");
        durationLabel.setBounds(350, 25, 80, 12);
        panel.add(durationLabel);
        durationMenu = new JComboBox(KeyNumberGenerator.durations);
        durationMenu.setBounds(350, 50, 90, 20);
        durationMenu.addActionListener(this);
        panel.add(durationMenu);

        JLabel typeLabel = new JLabel("Type");
        typeLabel.setBounds(450, 25, 80, 12);
        panel.add(typeLabel);
        typeMenu = new JComboBox(KeyNumberGenerator.types);
        typeMenu.setBounds(450, 50, 90, 20);
        typeMenu.addActionListener(this);
        panel.add(typeMenu);

        JLabel cILabel = new JLabel("Client");
        cILabel.setBounds(550, 25, 80, 12);
        panel.add(cILabel);
        cIInput = new JTextField("Type client name");
        cIInput.setBounds(550, 50, 110, 20);
        cIInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (cIInput.getText().equals("Type client name")) {
                    cIInput.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (cIInput.getText().equals("Type client name")) {
                } else {
                    System.out.println("Client: " + cIInput.getText());
                }
            }
        });
        panel.add(cIInput);

        // Make getProperties functionality
        JLabel numberLabel = new JLabel("Sequential Number");
        numberLabel.setBounds(670, 25, 110, 12);
        panel.add(numberLabel);
        numberOutput = new JTextField(String.valueOf(main.getProperties()));
        numberOutput.setBounds(670, 50, 50, 20);
        numberOutput.setEditable(false);
        panel.add(numberOutput);
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
            }
        } else if (event.getSource() == typeMenu) {
            System.out.println("Type: " + typeMenu.getSelectedItem());
            main.setType((String) typeMenu.getSelectedItem());
        }
    }
}
