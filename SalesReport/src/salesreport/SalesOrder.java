/**
 * SalesOrder Super Class
 */
package salesreport;

import java.text.DecimalFormat;

public class SalesOrder {

    private String productName;
    private double unitCost;
    private int unitCount;

    /**
     *Constructor to initialize @param
     * @param productName
     * @param unitCost
     * @param unitCount
     */
    public SalesOrder(String productName, int unitCount, double unitCost) {
        this.productName = productName;
        this.unitCost = unitCost;
        this.unitCount = unitCount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public int getUnitCount() {
        return unitCount;
    }

    public void setUnitCount(int unitCount) {
        this.unitCount = unitCount;
    }

    public double totalCost() {
        return getUnitCost() * getUnitCount();
    }

    @Override
    public String toString() {
        //Make sure toString Prints out unit cost in decimal dollars
        double number = getUnitCost();
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String priceUnit = decimalFormat.format(number);

        return "Sales Order------------ " + "Product Name: " + getProductName() + "   Unit Cost: $"
                + priceUnit + "   Number of Unit: " + getUnitCount();
    }

}
