/**
 * File:Calculation.java
 * Author: Cesar Lopez
 * Date: October 8, 2016
 * Purpose: class that does calculation for each operator
 */
package calculatorgui;

import java.text.*;

public class Calculation {

    private final char operator;
    private final String input;

    /**
     * Calculation Constructor
     * @param input
     * @param operator 
     */
    public Calculation(String input, char operator) {
        this.operator = operator;
        this.input = input;

    }//end constructor

    /**
     * Get results from calculations
     * @return 
     */
    public String getResult() {
        String result = null;
        try {
            //gets characters from input string using subString. 
            double num1 = Double.parseDouble(input.substring(0, input.indexOf(operator)));
            double num2 = Double.parseDouble(input.substring(input.indexOf(operator) + 1, input.length()));
            //Number Foramatter to deal with decimals. 
            NumberFormat formatter = new DecimalFormat("0.##");

            switch (operator) {

                case '/':
                    if (num2 == 0) {
                        //Handle error condition raised.If divided by zero, calculator will say “Cannot divide by zero”.
                        result = "Cannot divide by zero";

                    } else {
                        result = formatter.format(num1 / num2);
                    }
                    break;

                case 'x':
                    result = formatter.format(num1 * num2);
                    break;

                case '-':
                    result = formatter.format(num1 - num2);
                    break;

                case '+':
                    result = formatter.format(num1 + num2);
                    break;

            }//end of switch statement
            return result;

        } catch (Exception e) {
            
            return "Error! Invalid input";
        }

    }
}
