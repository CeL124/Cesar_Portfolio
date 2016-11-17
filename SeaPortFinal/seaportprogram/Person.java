/**
 * File:Person.java
 * Author:Cesar Lopez
 * Date:September 11, 2016
 * Purpose: contains personal in ship or port
 */
package seaportprogram;


public class Person extends Thing {

    public String skill; 

    /**
     * Constructor to initialize the variables in Class
     * @param index 
     * @param name 
     * @param parent 
     * @param skill 
     */
    public Person(int index, String name, Thing parent, String skill) {
        super(index,name,parent);
            this.skill=skill;
    }//End Person Constructor

    @Override
    public String toString() {
        String st = "Person: " + super.toString() + " " + skill;
        return st;
    }//End toString

}//END Thing
