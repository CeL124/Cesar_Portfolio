/**
 * Author: Cesar Lopez
 * 
 * File Name: Salesman.java
 * 
 * Purpose: subclass that contains annual sales.
 */
package employeesalaries;

/**
 *
 * @author CeL
 */
public class Salesman extends Employee{
    //Instance Data
    private int annualSales;
    
    /**
     * Constructor that initializes name, monthly salary and annual sales.
     * @param empName
     * @param monthlySalary
     * @param annualSales 
     */
    public Salesman(String empName, int monthlySalary, int annualSales) {
        super(empName, monthlySalary);
        this.annualSales = annualSales;
    }
    
    /**
     * 
     * @return 
     */
    public int getAnnualSales(){
        return annualSales;
    }
    
    /**
     * 
     * @param annualSales 
     */
    public void setAnnualSales(int annualSales){
        this.annualSales = annualSales;
    }
    /**
     * 
     * @return 
     */
    @Override
    public int annualSalary() {
        //salesman get %2 commission of annualSales. 
        int commission = (int)(0.02*annualSales);
        //max commission is 20000
        if(commission>20000)
            commission=20000;
        
        //base annual salary is the sum of the monthly salary and commission
        return (12*getMonthlySalary())+commission;
    }
    /**
     * 
     * @return 
     */
    @Override
    public String toString(){
        return  super.toString() + "  Annual Sale:" + getAnnualSales();
    }
    
    
    
}

