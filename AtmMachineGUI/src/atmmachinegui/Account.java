/**
 * File Name: Account.java
 * Author: Cesar Lopez
 * Date: April 10,2016
 *
 * Purpose: this class has constructor plus a method that corresponds to each of the four 
 * buttons in the GUI and It must also incorporate logic to deduct a service 
 * charge of $1.50 when more than four total withdrawals are made from 
 * either account.
 * 
 */
package atmmachinegui;

/**
 *
 * @author CeL
 */
public class Account {

    private String category;
    private double balance;
    public static int withdrawlCount = 0;

    public Account(String category, double balance) {
        this.category = category;
        this.balance = balance;
    }

    public void withdrawl(double money) throws InsufficientFunds {

        String message = "You cannot withdraw " + money + " because your"
                + " balance is $" + this.balance;

        if (withdrawlCount >= 4) {
            message = "Cannot Withdrawl " + money + " with the additional $1.50 "
                    + "service charge. Your current balance is $" + this.balance;

            money = money + 1.5;

        }
        if (money > this.balance) {
            throw new InsufficientFunds(message);
        }
        this.balance = this.balance - money;
        withdrawlCount++;

    }

    public void deposit(double money) {
        this.balance = this.balance + money;
    }

    public void transfer(double money) throws InsufficientFunds {

        if (money > this.balance) {
            
            throw new InsufficientFunds("Cannot transfer " + money + ". Current "
                    + "Account balance is $" + this.balance);
        }
        this.balance = this.balance - money;
    }
    
    public String balance(){
        return category+"Account Balance: $"+balance;
    }

}
