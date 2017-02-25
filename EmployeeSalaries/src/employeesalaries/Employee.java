/**
 * Author: Cesar Lopez
 *
 * File Name: Employee.java
 *
 * Purpose: Superclass that contains employee name and monthly salary.
 */
package employeesalaries;

/**
 *
 * @author CeL
 */
public class Employee {
    //instance data
    private String empName;
    private int monthlySalary;
    
    /**
     * Constructor to initialize name and monthly salary.
     * @param empName
     * @param monthlySalary 
     */
    public Employee(String empName, int monthlySalary){
        this.empName = empName;
        this.monthlySalary = monthlySalary;
    }
    
    /**
     * getter method returns employee name.
     * @return 
     */
    public String getEmpName(){
        return empName;
    }
    
    /**
     * 
     * @param empName 
     */
    public void  setEmpName(String empName){
        this.empName = empName;
    }
    
    /**
     * 
     * @return 
     */
    public int getMonthlySalary(){
        return monthlySalary;
    }
    
   /**
    * 
    * @param monthlySalary 
    */
    public void setMonthlySalary(int monthlySalary){
        this.monthlySalary = monthlySalary;
    }
    
    /**
     * calculates and return annual salary.
     * @return 
     */
    public int annualSalary(){
        return 12*getMonthlySalary();
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString(){
        return "Employee Name: "+getEmpName()+"\tMonthly Salary: "+
                getMonthlySalary();
    }
    
    
}
