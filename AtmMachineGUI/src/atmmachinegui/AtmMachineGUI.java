/**
 * File Name: AtmMachineGUI.java
 * Author: Cesar Lopez
 * Date: April 10,2016
 *
 * Purpose: Program that uses a GUI interface to implement an ATM Machine.
 *
 */
package atmmachinegui;

/**
 *
 * @author CeL
 */
import javax.swing.*;
import java.awt.*;
import static java.awt.Color.BLUE;
import java.awt.event.*;

public class AtmMachineGUI extends JFrame {

    JTextField xfield;
    JButton bDeposit;
    JButton bWithdrawl;
    JButton bTransfer;
    JButton bBalance;
    JRadioButton bChecking;
    JRadioButton bSaving;

    static Account checkingAccount;
    static Account savingAccount;

    public AtmMachineGUI() {
        super("ATM Machine");

        setLayout(new BorderLayout());
        xfield = new JTextField(30);
        bChecking = new JRadioButton("Checking");
        bSaving = new JRadioButton("Savings");
        JPanel p = new JPanel(new GridLayout(0, 1));
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel(new GridLayout(3, 2));
        bWithdrawl = new JButton("Withdraw");
        bDeposit = new JButton("Deposit");
        bBalance = new JButton("Balance");
        bTransfer = new JButton("Transfer");

        p2.add(bWithdrawl);
        p2.add(bDeposit);
        p2.add(bTransfer);
        p2.add(bBalance);
        p2.add(bChecking);
        p2.add(bSaving);
        p2.setBackground(BLUE);

        p.add(p2);
        p.add(p1);

        p1.add(xfield);
        add(p, BorderLayout.CENTER);

        ButtonGroup group = new ButtonGroup();
        group.add(bChecking);
        group.add(bSaving);

        theHandler handler = new theHandler(); //creating event object. 

        bChecking.setSelected(true);
        bDeposit.addActionListener(handler);
        bWithdrawl.addActionListener(handler);
        bTransfer.addActionListener(handler);
        bBalance.addActionListener(handler);

    }

    /**
     * event Handler
     */
    private class theHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {

            double amount = 0;
            Account accountChosen;
            Account accountNotChosen;

            if (bChecking.isSelected()) {
                accountChosen = checkingAccount;
                accountNotChosen = savingAccount;

            } else {
                accountChosen = savingAccount;
                accountNotChosen = checkingAccount;
            }

            if (event.getSource() != bBalance) {
                try {
                    amount = Double.parseDouble(xfield.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error! Please enter"
                            + " a numeric value in the field");
                    return;

                }

            }

            if (event.getSource() == bBalance) {
                JOptionPane.showMessageDialog(null, accountChosen.balance());

            } else if (event.getSource() == bWithdrawl) {

                //if not divisible by 20, then show message
                if (amount % 20 != 0) {
                    JOptionPane.showMessageDialog(null, "The amount should be "
                            + "increments of $20");
                    return;
                }
                try {
                    accountChosen.withdrawl(amount);
                } catch (InsufficientFunds ex) {
                    JOptionPane.showMessageDialog(null, ex);
                    return;
                }
                if (Account.withdrawlCount > 4) {
                    JOptionPane.showMessageDialog(null, "Total amount withdrew"
                            + " with $1.50 service charge: " + (amount + 1.50));

                } else {
                    JOptionPane.showMessageDialog(null, "Amount Withdrew: $"
                            + amount);
                }
            } else if (event.getSource() == bDeposit) {
                accountChosen.deposit(amount);
                JOptionPane.showMessageDialog(null, "Deposited Amount: $"
                        + amount);

            } else if (event.getSource() == bTransfer) {

                try {
                    accountNotChosen.transfer(amount);
                    accountChosen.deposit(amount);

                } catch (InsufficientFunds ex) {

                    JOptionPane.showMessageDialog(null, ex);
                    return;
                }

                JOptionPane.showMessageDialog(null, "Amount Transfered: $"
                        + amount);

            }

        }

    }

    /**
     * main
     *
     * @param args
     */
    public static void main(String[] args) {

        //instance of Account class
        checkingAccount = new Account("Checking", 0);
        savingAccount = new Account("Savings", 0);

        //creating frame
        AtmMachineGUI atm = new AtmMachineGUI();

        atm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        atm.pack();// automatically sizes the fame according component size.
        atm.setVisible(true);

    }

}
