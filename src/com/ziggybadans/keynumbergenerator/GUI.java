package com.ziggybadans.keynumbergenerator;

import net.java.balloontip.BalloonTip;
import net.java.balloontip.utils.FadingUtils;
import net.java.balloontip.utils.TimingUtils;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;

public class GUI implements ActionListener {

    JFrame frame;
    JButton backgroundButton;

    JComboBox marketMenu;
    JTextField yearInput;
    JTextField wIInput;
    JComboBox durationMenu;
    JComboBox typeMenu;
    JTextField cIInput;
    JTextField numberOutput;

    JButton generateButton;
    String generateOutput;
    PlainDocument document;
    static JTextField generateResult;

    KeyNumberGenerator main = new KeyNumberGenerator();

    public GUI() {
        frame = new JFrame("Key Number Generator");

        frame.setSize(1200,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLayeredPane panel = new JLayeredPane();
        frame.add(panel);

        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JLayeredPane panel) {
        panel.setLayout(null);

        JButton backgroundButton = new JButton();
        backgroundButton.setBounds(0,0,1200,600);
        backgroundButton.setOpaque(false);
        backgroundButton.setContentAreaFilled(false);
        backgroundButton.setBorderPainted(false);
        panel.add(backgroundButton);
        panel.setLayer(backgroundButton, 0, 0);

        JLabel marketLabel = new JLabel("Market");
        marketLabel.setBounds(50, 25, 80, 12);
        panel.add(marketLabel);
        panel.setLayer(marketLabel, 1, 0);
        marketMenu = new JComboBox(KeyNumberGenerator.markets);
        marketMenu.setBounds(50,50,90,20);
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
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (yearInput.getText().equals("Type year")) {
                    System.out.println("Year: " + null);
                    main.setYear(null);
                } else if(yearInput.getText().equals("")) {
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
        panel.add(yearInput);
        panel.setLayer(yearInput, 1, 0);

        JLabel wILabel = new JLabel("Writer");
        wILabel.setBounds(250, 25, 80, 12);
        panel.add(wILabel);
        panel.setLayer(wILabel, 1, 0);
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
                    System.out.println("Writer input: " + null);
                    main.setwI(null);
                } else if (wIInput.getText().equals("")) {
                    wIInput.setText("Type year");
                    System.out.println("Writer input: " + null);
                    main.setwI(null);
                }
                else {
                    System.out.println("Writer Input: " + wIInput.getText());
                    main.setwI(wIInput.getText());
                }
            }
        });
        panel.add(wIInput);
        panel.setLayer(wIInput, 1, 0);

        JLabel durationLabel = new JLabel("Duration");
        durationLabel.setBounds(350, 25, 80, 12);
        panel.add(durationLabel);
        panel.setLayer(durationLabel, 1, 0);
        durationMenu = new JComboBox(KeyNumberGenerator.durations);
        durationMenu.setBounds(350, 50, 90, 20);
        durationMenu.addActionListener(this);
        panel.add(durationMenu);
        panel.setLayer(durationMenu, 1, 0);

        JLabel typeLabel = new JLabel("Type");
        typeLabel.setBounds(450, 25, 80, 12);
        panel.add(typeLabel);
        panel.setLayer(typeLabel, 1, 0);
        typeMenu = new JComboBox(KeyNumberGenerator.types);
        typeMenu.setBounds(450, 50, 90, 20);
        typeMenu.addActionListener(this);
        panel.add(typeMenu);
        panel.setLayer(typeMenu, 1, 0);

        JLabel cILabel = new JLabel("Client");
        cILabel.setBounds(550, 25, 80, 12);
        panel.add(cILabel);
        panel.setLayer(cILabel, 1, 0);
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
                    System.out.println("Client: " + null);
                    main.setcI(null);
                } else if (cIInput.getText().equals("")) {
                    cIInput.setText("");
                    System.out.println("Client: " + null);
                    main.setcI(null);
                }
                else {
                    System.out.println("Client: " + cIInput.getText());
                    main.setcI(cIInput.getText());
                }
            }
        });
        panel.add(cIInput);
        panel.setLayer(cIInput, 1, 0);

        JLabel numberLabel = new JLabel("Sequential Number");
        numberLabel.setBounds(670, 25, 110, 12);
        panel.add(numberLabel);
        panel.setLayer(numberLabel, 1, 0);
        numberOutput = new JTextField(String.valueOf(main.getProperties()));
        numberOutput.setBounds(670, 50, 50, 20);
        numberOutput.setEditable(false);
        panel.add(numberOutput);
        panel.setLayer(numberOutput, 1, 0);

        generateButton = new JButton("Generate");
        generateButton.setBounds(450, 100, 100, 20);
        generateButton.addActionListener(this);
        panel.add(generateButton);
        panel.setLayer(generateButton, 1, 0);
        generateResult = new JTextField();
        generateResult.setBounds(450, 150, 200, 20);
        document = (PlainDocument) generateResult.getDocument();
        document.setDocumentFilter(new CharacterFilter(30, true));
        generateResult.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("Key pressed!");
            }
        });
        panel.add(generateResult);
        panel.setLayer(generateResult, 1, 0);
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
        } else if (event.getSource() == generateButton) {
            System.out.println("Debug: Attempting to generate...");
            generateOutput = main.generate();
            generateResult.setText("");
            generateResult.setText(generateOutput);
            numberOutput.setText(String.valueOf(main.getProperties()));
        }
    }

    static class CharacterFilter extends DocumentFilter {
        boolean upper = false;
        int limit = 0;

        public CharacterFilter(int limit) {
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
                    TimingUtils.showTimedBalloon(acceptError, 2000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            FadingUtils.fadeOutBalloon(acceptError, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    acceptError.closeBalloon();
                                }
                            }, 500, 15);
                        }
                    });

                }
            }
            return builder.toString();
        }

        public boolean accept(final char c) {
            return Character.isAlphabetic(c) || Character.isDigit(c) || c == '-' || c == '\0';
        }
    }
}

/*
class JTextFieldLimit extends PlainDocument {
    private int limit;

    public JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    public JTextFieldLimit(int limit, boolean upper) {
        super();
        this.limit = limit;
    }

    private class LimitDocument extends PlainDocument {
        @Override
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null) return;

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }
}
 */

/*
class CustomLengthTextField extends JTextField {
    protected boolean upper = false;
    protected int maxLength = 0;

    public CustomLengthTextField() {
        this(-1);
        System.out.println("Basic constructor.");
    }

    public CustomLengthTextField(int length, boolean upper) {
        this(length, upper, null);
        System.out.println("length, upper constructor.");
    }

    public CustomLengthTextField(int length, InputVerifier inputVerifier) {
        this(length, false, inputVerifier);
        System.out.println("length, inpv constructor.");
    }

    public CustomLengthTextField(int length, boolean upper, InputVerifier inputVerifier) {
        super();
        this.maxLength = length;
        this.upper = upper;
        if (length > 0) {
            AbstractDocument doc = (AbstractDocument) getDocument();
            doc.setDocumentFilter(new SizeFilter());
            System.out.println("New SizeFilter created.");
        }
        setInputVerifier(inputVerifier);
        System.out.println("Created text field.");
    }

    public CustomLengthTextField(int length) {
        this(length, false);
        System.out.println("length constructor.");
    }

    public void setMaxLength(int length) {
        this.maxLength = length;
        System.out.println("setMaxLength called.");
    }

    class SizeFilter extends DocumentFilter {
        public void insertString(FilterBypass fb, int offs, String str, AttributeSet a) throws BadLocationException {
            System.out.println("insertString called.");
            if ((fb.getDocument().getLength() + str.length()) <= maxLength) {
                System.out.println("insertString succeeded.");
                super.insertString(fb, offs, str, a);
            }
        }

        public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a) throws BadLocationException {
            if (upper) {
                System.out.println("replace called.");
                str = str.toUpperCase();
            }

            int charLength = fb.getDocument().getLength() + str.length() - length;
            if (charLength <= maxLength) {
                super.replace(fb, offs, length, str, a);
                System.out.println("Replace succeeded.");
                if (charLength == maxLength) {
                    focusNextComponent();
                    System.out.println("Replace cancelled.");
                }
            } else {
                focusNextComponent();
                System.out.println("Replace cancelled.");
            }
        }

        private void focusNextComponent() {
            System.out.println("focusNextComponent called.");
            if (CustomLengthTextField.this == KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner()) {
                KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
            }
        }
    }
}
 */

