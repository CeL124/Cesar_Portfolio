/**
 * File Name: InsufficientFunds.java
 * Author: Cesar Lopez
 * Date: April 10,2016
 *
 * Purpose: user defined checked exception
 * 
 */
package atmmachinegui;

/**
 *
 * @author CeL
 */
public class InsufficientFunds extends Exception {

    private String message;

    InsufficientFunds(String message) {

        super(message);
        this.message = message;
    }

    @Override
    public String toString() {

        return "InsufficientFundsException{ " + message + "}";
    }

}
