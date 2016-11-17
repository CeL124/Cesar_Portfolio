/**
 * File:SeaPort.java
 * Author:Cesar Lopez
 * Date:September 10, 2016
 * Purpose: Contains Docks, ships, que, and Person in seaPort 
 */
package seaportprogram;

import java.util.*;

public class SeaPort extends Thing {

    ArrayList<Dock> docks;
   public ArrayList<Ship> ships;
    ArrayList<Ship> que;
    ArrayList<Person> persons;

    /**
     * Constructor to initialize the variables in Class 
     * @param index
     * @param name
     */
    public SeaPort(int index, String name) {
        this.index=index;
        this.name = name;
        this.parent=null;
        docks = new ArrayList<>();
        ships = new ArrayList<>();
        que = new ArrayList<>();
        persons = new ArrayList<>();
    }//End SeaPort constructor

    

       
       
    /**
     * toString() method for SeaPort Class.
     * This toString make everything in the ArrayList in this class into string. 
     * 
     * Used some code found in example codes given to us. 
     * @return
     */
    @Override
    public String toString() {
        String st = "\n\nSeaPort: " + super.toString() + '\n';
        for (Dock md : docks) {
            st += "\n" + md.toString(this.ships);
        }
        st += "\n\n --- List of all ships in que:";
        for (Ship ms : que) {
            st += "\n  > " + ms;
        }
        st += "\n\n --- List of all ships:";
        for (Ship ms : ships) {
            st += "\n  > " + ms;
        }
        st += "\n\n --- List of all person:";
        for (Person mp : persons) {
            st += "\n  > " + mp;
        }
        return st;
    }//End toString
    
    

}//END SeaPort Class
