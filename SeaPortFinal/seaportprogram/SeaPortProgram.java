/**
 * File: SeaPortProgram.java
 * Author:Cesar Lopez
 * Date:September 10, 2016
 * Purpose: To Build a GUI that will display data from text input file and sorting options.
 */
package seaportprogram;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.io.*;
import java.io.FileReader;
import java.util.*;
import javax.swing.*;

import javax.swing.tree.DefaultMutableTreeNode;

public class SeaPortProgram extends JFrame {

    public static final long serialVersionUID = 123L;
    public World world;
    JPanel mainBarPanel = new JPanel();
    JPanel barPanel = new JPanel();

    JTextArea textArea;
    public static JTextArea resourceArea;
    public static JTextArea messageArea;
    JTextField searchField, pathField;
    JComboBox<String> optionBox;
    Scanner readFile;
    JComboBox<Comparator<?>> sortOptions = new JComboBox<>();
    public static HashMap<String, Integer> skillCount = new HashMap<>();

    static DefaultMutableTreeNode root = new DefaultMutableTreeNode("World");
    public static JTree tree = new JTree(root);

    // GUI constructor
    SeaPortProgram() {

        setTitle("SeaPort Program");
        

        textArea = new JTextArea();
        resourceArea = new JTextArea();
        messageArea = new JTextArea();

        textArea.setFont(new java.awt.Font("Monospaced", 0, 12));
        messageArea.setFont(new java.awt.Font("Monospaced", 0, 12));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setSize(300, 100);
        JPanel textAreaPanel = new JPanel(new BorderLayout());
        textAreaPanel.add(scrollPane, BorderLayout.CENTER);

        JScrollPane messageScroll = new JScrollPane(messageArea);
        messageScroll.setSize(150, 100);
        JPanel messagePanel = new JPanel(new BorderLayout());

        messagePanel.add(messageScroll, BorderLayout.CENTER);

        JScrollPane treePane = new JScrollPane(tree);
        treePane.setPreferredSize(new Dimension(100, 30));

        //Attributes for Resource text area
        JScrollPane sourceScroll = new JScrollPane(resourceArea);
        sourceScroll.setSize(150, 150);
        JPanel resourcePanel = new JPanel(new BorderLayout());
        resourcePanel.add(sourceScroll, BorderLayout.CENTER);

        for (Ship.Sorts o : Ship.Sorts.values()) {
            sortOptions.addItem(o);
        }

        optionBox = new JComboBox<>();
        optionBox.addItem("Index");
        optionBox.addItem("Name");
        optionBox.addItem("Skill");

        searchField = new JTextField(10);
        pathField = new JTextField(15);

        //Progress bar panel stuff
        barPanel.setLayout(new GridLayout(0, 6));
        JScrollPane barScroll = new JScrollPane(barPanel);

        JButton openFile = new JButton("Open File");
        openFile.addActionListener(e -> readFile());

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> search((String) (optionBox.getSelectedItem()), searchField.getText()));

        sortOptions.addItemListener(this::printSort);

        //for TOOL PANEL
        JPanel panelTop = new JPanel();
        panelTop.add(new JLabel("file path:"));
        panelTop.add(pathField);
        panelTop.add(openFile);

        //for TOOL PANEL
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 0));
        topPanel.setBorder(BorderFactory.createTitledBorder("Open File"));
        topPanel.add(panelTop);

        //for TOOL PANEL
        JPanel panelMiddle = new JPanel();
        panelMiddle.add(optionBox);
        panelMiddle.add(searchField);
        panelMiddle.add(searchButton);

        //for TOOL PANEL
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(1, 0));
        middlePanel.setBorder(BorderFactory.createTitledBorder("Search Target"));
        middlePanel.add(panelMiddle);
        //middlePanel.add(subPanelMiddle);

        //for TOOL PANEL
        JPanel panelBottom = new JPanel();
        panelBottom.add(sortOptions);

        //for TOOL PANEL
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 0));
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Sorting Option"));
        bottomPanel.add(panelBottom);

        //Tabbed pane for TOP
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Text View", textAreaPanel);
        tabbedPane.add("Tree View", treePane);
        tabbedPane.add("Job Progress", barScroll);
        tabbedPane.add("Resouces", resourcePanel);


        JPanel finBottomPanel = new JPanel(new GridLayout(1, 2));
        finBottomPanel.setSize(580, 200);
        finBottomPanel.setBorder(BorderFactory.createTitledBorder(" LOG"));
        finBottomPanel.add(messagePanel, BorderLayout.CENTER);

        //Main TOOL PANEL
        JPanel subRightMainPanel = new JPanel();
        subRightMainPanel.setLayout(new GridLayout(1, 3));
        subRightMainPanel.add(topPanel);
        subRightMainPanel.add(middlePanel);
        subRightMainPanel.add(bottomPanel);

        JPanel rightMainPanel = new JPanel();
        rightMainPanel.add(subRightMainPanel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tabbedPane, finBottomPanel);
        splitPane.setDividerLocation(500);

        add(rightMainPanel, BorderLayout.PAGE_START);
        add(splitPane, BorderLayout.CENTER);


//        setSize(new Dimension(1300, 1000));// deals with sizing issue
//        setResizable(false);
//
//        setVisible(true);

        readFile();
        validate();

    }

    /**
     * Initiates File Chooser dialog and uses methods from World class to read the file.
     */
    public void readFile() {
        messageArea.setText("");
        resourceArea.setText("");
        JFileChooser fileChooser = new JFileChooser(".");
        int returnVal = fileChooser.showDialog(this, "open file");
        try {
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    readFile = new Scanner(new FileReader(fileChooser.getSelectedFile()));
                    File file;
                    file = fileChooser.getCurrentDirectory();
                    pathField.setText(file.toString());
                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "File not found!", "Notice", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "User Cancelled", "Cancel Notice", JOptionPane.WARNING_MESSAGE);
            }

            world = new World(readFile, barPanel);
            textArea.setText(world.toString());

            
        } catch (NullPointerException ex) {
            System.out.println("Error in readFile method Driver Class");//Testing purposes
        }
    }// end readFile

    /**
     * Searches for target
     *
     * @param option
     * @param target
     */
    public void search(String option, String target) {
        textArea.setText("");

        textArea.append("\n\n  ------------SEARCH RESULTS FOR " + option.toUpperCase() + ": " + target
                + "----------------- \n");
        textArea.append("" + world.readOptions(option, target) + "\t");
    } // end method search

    /**
     * Item listener to sortOptions combo box. It sorts all the ships in que.
     *
     * @param e
     */
    public void printSort(ItemEvent e) {
        try {
            if (e.getStateChange() != ItemEvent.SELECTED) {
                return;
            }
            Comparator<?> compS = sortOptions.getItemAt(sortOptions.getSelectedIndex());

            if (compS instanceof Ship.Sorts) {
                for (SeaPort p : world.ports) {
                    p.que.sort((Ship.Sorts) compS);// sort ships in que
                    // p.ships.sort((Ship.Sorts)compS); // ships in port and
                    // dock (not required)
                }
            }
            textArea.setText(world.toString());
        } catch (Exception error) {// in case user selects sorting option while
            // no file is displayed.
            JOptionPane.showMessageDialog(null, "Please Load File First", "Notice", JOptionPane.ERROR_MESSAGE);
        }
    } // end method printSort

    public static void main(String[] args) {
        SeaPortProgram newWorld = new SeaPortProgram();
        newWorld.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newWorld.setSize(new Dimension(1300, 1000));// deals with sizing issue
        newWorld.setResizable(false);

        newWorld.setVisible(true);

    }// end Main

}// END SeaPortProgram Class
