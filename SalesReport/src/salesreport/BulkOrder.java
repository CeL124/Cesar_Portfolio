/*
 * Sub class Bulk Order
 */
package salesreport;

import java.text.DecimalFormat;

class BulkOrder extends SalesOrder {

    private int minimumSize;

    public BulkOrder(String productName, int unitCount, double unitCost, int minimumSize) {
        super(productName, unitCount, unitCost);
        this.minimumSize = minimumSize;
    }

    public int getMinimumSize() {
        return minimumSize;
    }

    public void setMinimumSize(int minimumSize) {
        this.minimumSize = minimumSize;
    }

    @Override
    public double totalCost() {
        double totalCost = 0;
        double orderCost = getUnitCost() * getUnitCount();

        if (getUnitCount() > 2*getMinimumSize()) {
            totalCost =  (orderCost - (0.3 * orderCost));

        } else if (getUnitCount() > getMinimumSize() || getUnitCount() < 2 * getMinimumSize()) {
            totalCost =  (orderCost - (0.1 * orderCost));

        } else if(getUnitCount() <= getMinimumSize()){
            totalCost = orderCost;
        }

        return totalCost;
    }

    @Override
    public String toString() {
        double number = getUnitCost();
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String priceUnit = decimalFormat.format(number);

        return "Bulk Order------------ " + "Product Name: " + getProductName() + "   Unit Cost: $"
                + priceUnit + "   Number of Unit: " + getUnitCount() + "   Minimum Size: " + getMinimumSize();
    }

}
