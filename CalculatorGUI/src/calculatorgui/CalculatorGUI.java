/**
 * File:CalculatorGUI.java
 * Author: Cesar Lopez
 * Date: October 8, 2016
 * Purpose: Driver class. generates the GUI using input .txt file.
 */
package calculatorgui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Enumeration;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CalculatorGUI extends JFrame {

    private JPanel buttonPanel, textPanel, bottomPanel, editButtonPanel;
    private JFrame mainFrame;
    private char operatorSymbol;
    private JButton button, editButton;
    private JRadioButton radioButton;
    private JTextField textField;
    private ButtonGroup group = new ButtonGroup();

    /**
     * main. Create and instance of the CalculatorGUI.
     *
     * @param args
     */
    public static void main(String[] args) {
        CalculatorGUI calculator = new CalculatorGUI();

    }//End main

    /**
     * Constructor where GUI is build
     */
    public CalculatorGUI() {

        editButtonPanel = new JPanel(new GridLayout(1, 2, 3, 0));

        //because of ("."), file chooser dialog opens in current directory (current project folder)
        JFileChooser fileChooser = new JFileChooser(".");

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "text Documents (*.txt)", "txt");

        fileChooser.setFileFilter(filter);

        //disabled "all Files" option in the file chooser dialog.
        fileChooser.setAcceptAllFileFilterUsed(false);

        int result = fileChooser.showDialog(null, "read file");

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getName());
            Deserializer fileObj = new Deserializer();
            String fileData = "";
            try {
                fileData = fileObj.readAll(file);

            } catch (IOException e) {
                System.out.print(e.toString());
            }

            //spliting the file up by lines. 
            String element[] = fileData.split("\n");

            for (String element1 : element) {
                String regex = "\\s\\s+";
                try {
                    //replace and split lines with white spaces.
                    element = element1.replace('"', ' ').replace('(', ' ').replace(')', ' ')
                            .replace(',', ' ').replace(';', ' ').replace('\r', ' ').replace(':', ' ').split(regex);

                    if (element[0].contains("Window")) {
                        mainFrame = new JFrame(element[1]);
                        mainFrame.setSize(Integer.parseInt(element[2]), Integer.parseInt(element[3]));
                        mainFrame.setMaximumSize(new Dimension(330, 330));
                        mainFrame.setResizable(false);
                        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        mainFrame.setLocationRelativeTo(null);

                    } else if (element[0].trim().contains("Border")) {
                        mainFrame.setLayout(new BorderLayout());
                    }

                    if (element.length > 1) {
                        if (element[1].trim().contains("Textfield")) {
                            String[] textFieldProperty = element[1].split(" ");
                            textField = new JTextField();
                            textField.setSize(new Dimension(Integer.parseInt(textFieldProperty[1]), 12));
                            textPanel = new JPanel(new GridLayout(1, 1));
                            textPanel.add(textField);
                            mainFrame.add(textPanel, BorderLayout.NORTH);

                        } else if (element[1].trim().contains("Panel")) {
                            buttonPanel = new JPanel(new GridLayout(Integer.parseInt(element[2]),
                                    Integer.parseInt(element[3]), Integer.parseInt(element[4]),
                                    Integer.parseInt(element[5])));
                            buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 5));
                            buttonPanel.setBackground(Color.BLACK);
                            bottomPanel = new JPanel(new GridLayout(1, 1));
                            bottomPanel.add(buttonPanel);

                            mainFrame.add(bottomPanel, BorderLayout.CENTER);

                        } else if (element[1].trim().equals("Button")) {

                            button = new JButton(element[2]);
                            button.setFont(button.getFont().deriveFont(26.0f));

                            button.addActionListener(event -> buttonPressed(event));

                            paintButton(button, element[2]);

                            buttonPanel.add(button);
                            buttonPanel.validate();

                        } else if (element[1].trim().contains("Radio")) {
                            radioButton = new JRadioButton(element[2]);
                            radioButton.addActionListener(event -> buttonPressed(event));
                            group.add(radioButton);

                            radioButton.setFont(radioButton.getFont().deriveFont(28.0f));
                            radioButton.setForeground(Color.orange);
                            radioButton.setBackground(Color.black);
                            radioButton.repaint();

                            buttonPanel.add(radioButton);
                            buttonPanel.validate();

                        } else if (element[1].trim().contains("Edit")) {
                            editButton = new JButton(element[2]);
                            editButton.addActionListener(event -> buttonPressed(event));
                            editButton.setBackground(Color.CYAN);
                            editButton.setFont(radioButton.getFont().deriveFont(15.0f));

                            editButtonPanel.add(editButton);
                            editButtonPanel.validate();
                            editButtonPanel.setBorder(BorderFactory.createEmptyBorder(2, 5, 5, 2));
                            editButtonPanel.setBackground(Color.BLACK);
                            mainFrame.add(editButtonPanel, BorderLayout.SOUTH);
                        }

                        mainFrame.setVisible(true);

                    }

                } catch (NullPointerException ex) {
                    System.out.println(ex.toString());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "User Cancelled", "Cancel Notice",
                    JOptionPane.WARNING_MESSAGE);

        }// end of IF ELSE Statement where file is choosen. 
    }//end CalculatorGUI constructor.

    /**
     * Action performed when a button event is fired.
     *
     * @param event
     */
    public void buttonPressed(ActionEvent event) {
        Enumeration<AbstractButton> enumeration = group.getElements();
        String textData = textField.getText();
        //String backPosition = textData.substring(0, textData.length() - 1);

        Calculation doCalc = new Calculation(textData, operatorSymbol);
        char command = event.getActionCommand().charAt(0);

        switch (command) {

            case '+':
                operatorSymbol = '+';
                textField.setText(textData + "+");
                notEnable(enumeration);
                break;
            case '-':
                operatorSymbol = '-';
                textField.setText(textData + "-");
                notEnable(enumeration);
                break;
            case 'x':
                operatorSymbol = 'x';
                textField.setText(textData + "x");
                notEnable(enumeration);
                break;
            case '/':
                operatorSymbol = '/';
                textField.setText(textData + "/");
                notEnable(enumeration);
                break;
            case '.':
                textField.setText(textData + ".");
                break;
            case '0':
                textField.setText(textData + "0");
                break;
            case '1':
                textField.setText(textData + "1");
                break;
            case '2':
                textField.setText(textData + "2");
                break;
            case '3':
                textField.setText(textData + "3");
                break;
            case '4':
                textField.setText(textData + "4");
                break;
            case '5':
                textField.setText(textData + "5");
                break;
            case '6':
                textField.setText(textData + "6");
                break;
            case '7':
                textField.setText(textData + "7");
                break;
            case '8':
                textField.setText(textData + "8");
                break;
            case '9':
                textField.setText(textData + "9");
                break;
            case '=':
                textField.setText(doCalc.getResult());
                group.clearSelection();
                enable(enumeration);
                break;
            case 'C':
                textField.setText("");
                group.clearSelection();
                enable(enumeration);
                break;
            case 'B':
                if (textData.length() > 0) {
                    textField.setText(textData.substring(0, textData.length() - 1));
                }

                break;

        }//end of switch statement

    }//end of buttonPressed method

    /**
     * Sets all the radio buttons in the button group not selectable.
     *
     * @param enumeration
     */
    public void notEnable(Enumeration<AbstractButton> enumeration) {
        while (enumeration.hasMoreElements()) {

            enumeration.nextElement().setEnabled(false);
        }
    }//end notEnable method. 

    /**
     * Sets all the radio buttons in the button group selectable.
     *
     * @param enumeration
     */
    public void enable(Enumeration<AbstractButton> enumeration) {
        while (enumeration.hasMoreElements()) {

            enumeration.nextElement().setEnabled(true);
        }
    }//End enable method

    /**
     * Method for painting the operators button orange, numbers button yellow
     * and the equal symbol button green
     *
     * @param button
     * @param element
     */
    public void paintButton(JButton button, String element) {

        switch (element) {
            case "=":
                button.setBackground(Color.GREEN);
                break;
            default:
                button.setBackground(Color.YELLOW);
                break;
        }

    }//end paintButton method.

}//END of CalcualtorGUI class
