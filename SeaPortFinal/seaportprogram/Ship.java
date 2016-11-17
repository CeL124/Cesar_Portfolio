/**
 * File:Ship.java
 * Author:Cesar Lopez
 * Date:September 11, 2016
 * Purpose: Super class for cargoShip and passengerShip
 */
package seaportprogram;

import java.util.*;

/**
 * Contains attributes of the ships, including jobs (skills). 
 */
public class Ship extends Thing  {

    ArrayList<Job> jobs;
    double draft, length, weight, width;
    PortTime arrival, dockTime;
    public SeaPort port = null;
    public boolean isDocked;

    /**
     * Constructor to initialize scanner and the variables in Class 
     * @param index
     * @param name
     * @param parent
     * @param draft
     * @param length
     * @param weight
     * @param width
     * @param isDocked
     */
    public Ship(int index, String name, Thing parent, double draft, double length, double weight, double width, boolean isDocked) {
        super(index,name,parent);
        this.draft = draft;
        this.length = length;
        this.weight = weight;
        this.width = width;
        this.isDocked = isDocked;
        jobs = new ArrayList<>();
    }//End Ship Constructor

    
    /**
     * emun for Sorting
     */
    enum Sorts implements Comparator <Ship> { 
    	NAME     ("Name"             , (a,b) -> a.name.compareTo(b.name)),
        DRAFT    ("Draft"             , (a,b) -> Double.compare (a.draft, b.draft)), 
        LENGTH   ("Length"            , (a,b) -> Double.compare (a.length, b.length)),
        CAPACITY ("Width"			  , (a,b) -> Double.compare (a.width, b.width)),
        WEIGHT   ("Weight"            , (a,b) -> Double.compare (a.weight, b.weight)); 
         
        String display;
        java.util.function.BiFunction <Ship, Ship, Integer> func;
         
        Sorts (String s) {display = "Sort by Attribute: " + s; }  // String constructor
        Sorts (String s, java.util.function.BiFunction <Ship, Ship, Integer> bf) {
           this (s);
           func = bf;
        } // end 2 parameter constructor
            @Override
        public String toString ()  {return display;} // to get the display in the JComboBox right
            @Override
        public int compare (Ship c1, Ship c2) {return func.apply (c1, c2);}
	
     }; // end enum
 
    /**
     * calls on the releaseDock method. 
     * @param ship
     * @param d 
     */
    public void onComplete(Ship ship, Dock d){

	   if(d != null && d.ship != null){
		   d.ship = null;
                   SeaPortProgram.messageArea.append(ship.index + " undocked from Dock: " + d.index+"\n");
           }

   }
    @Override
    public String toString() {
    	  String st = String.format("\tShip:%5s \tWeight:%.2f   Draft:%.2f   Length:%.2f   Width:%.2f " , super.toString(), weight, draft, length, width);
        return st;
    }//End toString Method

}//END Ship Class