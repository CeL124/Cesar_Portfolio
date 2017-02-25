/**
 * Author: Cesar Lopez
 * Date: May 8, 2016
 *
 * purpose: the purpose if this program is to manage a student database.
 *
 */
package studentdatabase;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

/**
 *
 * @author CeL
 */
public class StudentDatabase extends JFrame implements ActionListener {

    //Global variables    
    private JLabel ID, Sname, major, selections;
    private JTextField xID, xSname, xmajor;
    private String[] option = new String[]{"Insert", "Delete", "Find", "Update"};
    private JComboBox<String> optionBox;
    private JButton process;
    private HashMap<Integer, Student> database;
    private JPanel mainPanel;

    /**
     * invoke the dataManager method in dispatch thread. Not violating swing
     * single threading rule.
     *
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentDatabase().dataManager());

    }

    /**
     * method where GUI is build.
     */
    private void dataManager() {

        database = new HashMap<>();
        ID = new JLabel("ID:");
        Sname = new JLabel("NAME:");
        major = new JLabel("MAJOR:");
        selections = new JLabel("Select Option");
        process = new JButton("Process Request");
        xID = new JTextField(12);
        xSname = new JTextField(12);
        xmajor = new JTextField(12);
        optionBox = new JComboBox<>(option);

        mainPanel = new JPanel(new GridLayout(5, 2, 8, 8));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        mainPanel.add(ID);
        mainPanel.add(xID);
        xID.setEditable(true);

        mainPanel.add(Sname);
        mainPanel.add(xSname);
        xSname.setEditable(true);

        mainPanel.add(major);
        mainPanel.add(xmajor);
        xmajor.setEditable(true);

        mainPanel.add(selections);
        mainPanel.add(optionBox);
        mainPanel.add(process);

        process.addActionListener((ActionListener) this);//adding listener interface for JComboBox.

        JFrame frame = new JFrame("Student Database");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent click) {
        try {
            int idStg = Integer.parseInt(xID.getText());
            String xNameStg = xSname.getText();
            String majorStg = xmajor.getText();

            if (click.getSource() == optionBox) {

                if (optionBox.getSelectedIndex() == 0) {

                    xID.setEditable(true);
                    xSname.setEditable(true);
                    xmajor.setEditable(true);
                } else {
                    xID.setEditable(true);
                    xSname.setEditable(false);
                    xmajor.setEditable(false);

                }
                //opration should be performed when button is performed.
            } else if (click.getSource() == process) {  

                if (idStg < 0) {
                    errorMsg("Must enter a positive numberical value for ID field ");
                    return;
                }
                //Insert option selected from combo box
                if (optionBox.getSelectedIndex() == 0) {  
                    
                    //check for duplicates
                    if (database.containsKey(idStg)) { 
                        errorMsg("This ID already exist");
                        return;
                    }
                    if (xNameStg.length() == 0) {
                        errorMsg("Value for NAME field must be entered");
                        return;
                    }
                    if (majorStg.length() == 0) {
                        errorMsg("Value for MAJOR field must be entered");
                        return;
                    }
                    database.put(idStg, new Student(xNameStg, majorStg));
                    //notify when instertion is successful
                    noteMsg("Successfully Added information");

                } else {
                    if (!database.containsKey(idStg)) {
                        //if a user attemps to find record not in database,message should also be displayed.
                        errorMsg("Student not found");
                        return;
                    } else {
                    }
                    //Delete option selected from combo box.
                    if (optionBox.getSelectedIndex() == 1) {

                        Student human = database.get(idStg);
                        database.remove(idStg);
                        noteMsg("Successfully removed from database\n" + human.toString());
                        
                         //Find option selected in combo box
                    } else if (optionBox.getSelectedIndex() == 2) {  
                        Student human = database.get(idStg);

                        // If find action is successful display student info from toString method.
                        noteMsg("Student Found\n" + human.toString());

                    } else if (optionBox.getSelectedIndex() == 3) {  //Find option selected in combo box

                        String[] gradeChoices = {"A", "B", "C", "D", "F"};

                        //When update in selected a JOptionPane window will gather information
                        String gradeSelection = (String) JOptionPane.showInputDialog(mainPanel, "Choose grade: ",
                                "Choose grade", JOptionPane.QUESTION_MESSAGE, null, gradeChoices, gradeChoices[0]);

                        gradeChoices = new String[]{"3", "6"};
                        int credits = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Choose credits: ",
                                "Choose credits", JOptionPane.QUESTION_MESSAGE, null, gradeChoices, gradeChoices[0]));

                        Student human = database.get(idStg);
                        human.courseCompleted(gradeSelection, credits);
                        // If find action is successful display student info from toString method.
                        noteMsg("Successfully Updated information\n" + human.toString());

                    }
                }

            }

            //Also any exceptions thrown by nonnumeric inputs should be properly handled.
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Cannot take non-numerical values");
        }
    }

    /**
     * JOptionPane dialog incase of error or anomaly.
     *
     * @param x
     */
    public void errorMsg(String x) {
        JOptionPane.showMessageDialog(mainPanel, x, "ERROR!!!", ERROR_MESSAGE);
    }

    /**
     * JOptionPane dialog to notify successful action.
     *
     * @param z
     */
    public void noteMsg(String z) {
        JOptionPane.showMessageDialog(mainPanel, z, "Notification", INFORMATION_MESSAGE);
    }

}
