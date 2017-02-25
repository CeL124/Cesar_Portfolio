/**
 * HomeWork 1
 * Week one: May 16-22, 2016
 *
 * @Author Cesar Lopez
 *
 * Purpose:a program that involves reading input from a file and to compute the cost for a collection of sale
 * orders of different types.
 */
package salesreport;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class SalesReport {

    public static void main(String[] args) throws FileNotFoundException {
        SalesOrder salesReport[] = new SalesOrder[100];
        int salesCount = 0;

        String salesData = "SalesReportData.txt";

        try (Scanner output = new Scanner(new File(salesData))) {
            while (output.hasNextLine()) {
                String element[] = output.nextLine().split(",");
                SalesOrder x = getData(element);
                salesReport[salesCount++] = x;

            }
        }
        System.out.println("\t\t\t\t\t----------Sales Report---------\n");
        ShowData(salesReport, salesCount);

    }
    // read the data from the text file into an array.
    public static SalesOrder getData(String element[]) {
        if ("SalesOrder".equalsIgnoreCase(element[0])) {
            return new SalesOrder(element[1].trim(), Integer.parseInt(element[2].trim()),
                    Double.parseDouble(element[3].trim()));

        } else if ("DiscountSalesOrder".equalsIgnoreCase(element[0])) {

            return new DiscountedSalesOrder(element[1].trim(), Integer.parseInt(element[2].trim()),
                    Double.parseDouble(element[3].trim()), Double.parseDouble(element[4].trim()));

        } else if ("BulkOrder".equalsIgnoreCase(element[0])) {

            return new BulkOrder(element[1].trim(), Integer.parseInt(element[2].trim()),
                    Double.parseDouble(element[3].trim()), Integer.parseInt(element[4].trim()));
        }
        return null;
    }

    
     //Method reads every line from the text file
    public static void ShowData(SalesOrder data[], int count) {
        HashMap<String, Double> costOfProducts = new HashMap<String, Double>();
        for (int i = 0; i < count; i++) {
            System.out.print(data[i]);
            System.out.printf("  Total Cost: $%.2f\n\n", data[i].totalCost());

            if (costOfProducts.get(data[i].getProductName()) == null) {
                costOfProducts.put(data[i].getProductName(), data[i].totalCost());
            } else {
                costOfProducts.put(data[i].getProductName(), costOfProducts.get(data[i].getProductName()) + data[i].totalCost());
            }

        }
        
        System.out.println("\t\t\t------ Product Report in Ascending Order or Total Cost--------\n");
        // Sort total cost in ascending order.
        SortedSet<Double> values = new TreeSet<Double>(((HashMap<String, Double>) costOfProducts.clone()).values());
        Set<String> productName = costOfProducts.keySet();
        for (Double value : values) {
            for (String product : productName) {
                if (Objects.equals(value, costOfProducts.get(product))) {
                    System.out.printf(product + "\tTotal Cost: $%.2f\n", costOfProducts.get(product));
                    break;
                }
            }
        }

    }

}
