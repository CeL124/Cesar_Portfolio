/**
 * File:PassengerShip.java
 * Author:Cesar Lopez
 * Date:September 11, 2016
 * Purpose:It contains attributes variables of a passenger ship that is docked.

 */
package seaportprogram;


public class PassengerShip extends Ship{
    int numberOfOccupiedRooms;
    int numberOfPassengers;
    int numberOfRooms;

    /**
     * Constructor to initialize variables. 
     * @param index
     * @param name 
     * @param parent 
     * @param draft 
     * @param length 
     * @param weight 
     * @param width 
     * @param numberOfOccupiedRooms 
     * @param numberOfPassengers 
     * @param numberOfRooms 
     * @param isDocked 
     */
    public PassengerShip(int index, String name, Thing parent, double draft, double length,
            double weight, double width, int numberOfOccupiedRooms, int numberOfPassengers, int numberOfRooms, boolean isDocked) {
        
        super(index,name,parent,draft,length,weight,width,isDocked);
            this.numberOfOccupiedRooms = numberOfOccupiedRooms;
            this.numberOfPassengers = numberOfPassengers;
            this.numberOfRooms = numberOfRooms;
            
    }//End Constructor
    
    
    @Override
     public String toString(){
        String st = "Passenger ship: " + super.toString();
        if (jobs.isEmpty()){
            return st;}
        for (Job mj: jobs){
            st += "\n    - " + mj;
        }
        return st;
    }//end toString method
    
}//END PassengerShip Class
