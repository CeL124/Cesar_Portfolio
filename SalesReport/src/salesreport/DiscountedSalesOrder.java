/*
 * Sub class Discount Sales Order
 */
package salesreport;

import java.text.DecimalFormat;

class DiscountedSalesOrder extends SalesOrder {

    private double discount;

    public DiscountedSalesOrder(String productName, int unitCount, double unitCost, double discount) {
        super(productName, unitCount, unitCost);
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public double totalCost() {
        double totalCost;
        double orderCost = getUnitCost() * getUnitCount();
        double discountConverter = getDiscount() / 100.0;

        totalCost = orderCost - (orderCost * discountConverter);

        return totalCost;
    }

    @Override
    public String toString() {
        double number = getUnitCost();
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String priceUnit = decimalFormat.format(number);

        return "DiscountedSalesOrder--- " + "Product Name: " + getProductName() + "   Unit Cost: $"
                + priceUnit + "   Number of Unit: " + getUnitCount() + " Discount: " + getDiscount() + "%";
    }

}
