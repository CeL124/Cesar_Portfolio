/**
 * Author: Cesar Lopez
 * 
 * File Name: Executive
 * 
 * Purpose: Subclass that contains stock prices.
 */
package employeesalaries;

/**
 *
 * @author CeL
 */
public class Executive extends Employee {
    //Instance Data;
    private int stockPrice;
    
    /**
     * Constructor that initializes name, monthly salary and stock price.
     * 
     * @param empName
     * @param monthlySalary
     * @param stockPrice 
     */
    public Executive(String empName, int monthlySalary, int stockPrice) {
        super(empName, monthlySalary);
        this.stockPrice = stockPrice;
    }
    
    /**
     * 
     * @return 
     */
    public int getStockPrice(){
        return stockPrice;
    }
    
    /**
     * 
     * @param stockPrice 
     */
    public void setStockPrice(int stockPrice){
        this.stockPrice = stockPrice;
    }
    
    /**
     * Annual salary that includes a $30,000 bonus is the stock price is greater
     * than $50.
     * @return 
     */
    @Override
    public int annualSalary(){
        int bonus = 0;
        if(stockPrice>50)
            bonus = 30000;
        //annual salary is based on the monthly salary plus bonus
        return (12*getMonthlySalary())+bonus;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString(){
       return super.toString() + "  Stock Price: " + getStockPrice();
               
    }
    
    
}
