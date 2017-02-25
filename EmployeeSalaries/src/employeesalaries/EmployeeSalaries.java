/**
 * Author: Cesar Lopez
 * 
 * Date: March 27, 2016
 *
 * File Name: EmployeeSalaries.java
 *
 * Purpose: It contains the main method.It should read in employee information
 * from a text file and display it when user runs the program.
 */
package employeesalaries;

import java.io.*;
import java.util.*;

/**
 *
 * @author CeL
 */
public class EmployeeSalaries {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        Employee data2014[] = new Employee[10];//assume file will have no more than 10.
        int count14 = 0;
        Employee data2015[] = new Employee[10];
        int count15 = 0;

        String EmpData = "EmployeeData.txt";
        try (Scanner output = new Scanner(new File(EmpData))) {
            while (output.hasNextLine()) {
                String element[] = output.nextLine().split(", ");
                Employee x = getData(element);
                int year = Integer.parseInt(element[0]);

                if (year == 2015) {
                    data2015[count15++] = x;

                } else {
                    data2014[count14++] = x;
                }
            }
        }

        /**
         * This will show all the data on the console for each of year 2014 and
         * 2015 and display the average of all salaries.
         */
        System.out.println("\t\t\t2014 DATA");
        ShowData(data2014, count14);

        System.out.println("\n\t\t\t2015 DATA");
        ShowData(data2015, count15);

    }
    
    /**
     * method to get data from specific employee type from text file.
     * @param element
     * @return 
     */
    public static Employee getData(String element[]) {
        if ("Employee".equalsIgnoreCase(element[1])) {
            return new Employee(element[2], Integer.parseInt(element[3]));
        } else if ("Salesman".equalsIgnoreCase(element[1])) {
            return new Salesman(element[2], Integer.parseInt(element[3]),
                    Integer.parseInt(element[4]));
        } else if ("Executive".equalsIgnoreCase(element[1])) {
            return new Executive(element[2], Integer.parseInt(element[3]),
                    Integer.parseInt(element[4]));
        }
        return null;
    }

    /**
     * Method used to calculate the average of all salaries and display the data
     * in the array.
     *
     * @param data
     * @param count
     */
    public static void ShowData(Employee data[], int count) {
        double totalSalary = 0;
        for (int i = 0; i < count; i++) {
            System.out.print(data[i]);
            System.out.println("  Annual Salary: " + data[i].annualSalary());

            totalSalary = data[i].annualSalary() + totalSalary;
        }
        System.out.printf("Average salary of all employees for this year is"
                + " $%.2f\n", (totalSalary / count));
    }

}
