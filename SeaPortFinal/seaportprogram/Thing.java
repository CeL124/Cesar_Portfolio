/**
 * File: Thing.java
 * Author:Cesar Lopez
 * Date: September 11, 2016
 * Purpose:This class implements comparable. It is a super class for many classes in this project. 
 */
package seaportprogram;

public class Thing implements Comparable<Thing> {

	int index;
	String name;
        Thing parent;
	int parentIndex;

	/**
	 * Initialize variable given for Thing Class
	 */
	public Thing() {
		name = "";
		index = 0;
		parent = null;
  
	}// END Thing constructor

        public Thing(int index, String name, Thing p) {
            this.index=index;
            this.name=name;
            this.parent = p;
        }

    @Override
	public String toString() {
		
		String st = String.format("%9s  %7d ", name, index);
		return st;
	}// End toString Method

	@Override
	public int compareTo(Thing arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}// END Thing Class
