/**
 * File:World.java
 * Author:Cesar Lopez
 * Date:September 11, 2016
 * Purpose: Most operations are done here. This is the world.
 */
package seaportprogram;

import java.awt.*;

import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;

import seaportprogram.Job.Status;

public class World extends Thing {

    ArrayList<SeaPort> ports = new ArrayList<>();
    PortTime time = new PortTime();
    JPanel subProgressPanel = new JPanel();
    public HashMap<String, Integer> skillCount = new HashMap<>();
    public HashMap<String, Integer> personalSkillCount = new HashMap<>();

    /**
     * scanner constructor of the World Class
     *
     * @param scan
     * @param subProgressPanel
     */
    public World(Scanner scan, JPanel subProgressPanel) {
        super();
        HashMap<Integer, SeaPort> mSeaport = new HashMap<>();
        HashMap<Integer, Dock> mDock = new HashMap<>();
        HashMap<Integer, Ship> mShip = new HashMap<>();
        this.subProgressPanel = subProgressPanel;

        while (scan.hasNextLine()) {
            read(scan.nextLine(), mSeaport, mDock, mShip);

        }//End While Loop
        generateTree();

    }// END World Constructor

    /**
     * Generate JTree nodes.
     */
public void generateTree() {

        for (SeaPort port : ports) {
            DefaultMutableTreeNode portNode = new DefaultMutableTreeNode(port.name);
            SeaPortProgram.root.add(portNode);
            for (Dock dock : port.docks) {
                DefaultMutableTreeNode dockNode = new DefaultMutableTreeNode(dock.name);
                if (dock.ship != null) {
                    DefaultMutableTreeNode shipNode = new DefaultMutableTreeNode(dock.ship.name);
                    dockNode.add(shipNode);
                }
                portNode.add(dockNode);
            }
            DefaultMutableTreeNode queShipsNode = new DefaultMutableTreeNode("All Ships in que");
            for (Ship ship : port.que) {
                DefaultMutableTreeNode shipNode = new DefaultMutableTreeNode(ship.name);
                DefaultMutableTreeNode weight = new DefaultMutableTreeNode("Weight : " + ship.weight);
                DefaultMutableTreeNode width = new DefaultMutableTreeNode("Width : " + ship.width);
                DefaultMutableTreeNode draft = new DefaultMutableTreeNode("Draft : " + ship.draft);
                DefaultMutableTreeNode length = new DefaultMutableTreeNode("Length : " + ship.length);
                shipNode.add(weight);
                shipNode.add(width);
                shipNode.add(draft);
                shipNode.add(length);
                if (ship instanceof PassengerShip) {
                    PassengerShip s = (PassengerShip) ship;
                    shipNode.add(new DefaultMutableTreeNode("No.Of Occupied Rooms : " + s.numberOfOccupiedRooms));
                    shipNode.add(new DefaultMutableTreeNode("No.Of Passengers     : " + s.numberOfPassengers));
                    shipNode.add(new DefaultMutableTreeNode("No.Of Rooms          : " + s.numberOfRooms));
                }
                if (ship instanceof CargoShip) {
                    CargoShip s = (CargoShip) ship;
                    shipNode.add(new DefaultMutableTreeNode("Cargo Value      : " + s.cargoValue));
                    shipNode.add(new DefaultMutableTreeNode("Cargo Volume     : " + s.cargoVolume));
                    shipNode.add(new DefaultMutableTreeNode("Cargo Weight     : " + s.cargoWeight));
                }
                queShipsNode.add(shipNode);
            }
            portNode.add(queShipsNode);
            DefaultMutableTreeNode allShipsNode = new DefaultMutableTreeNode("All Ships");
            for (Ship ship : port.ships) {

                DefaultMutableTreeNode shipNode = new DefaultMutableTreeNode(ship.name);
                DefaultMutableTreeNode weight = new DefaultMutableTreeNode("Weight : " + ship.weight);
                DefaultMutableTreeNode width = new DefaultMutableTreeNode("Width : " + ship.width);
                DefaultMutableTreeNode draft = new DefaultMutableTreeNode("Draft : " + ship.draft);
                DefaultMutableTreeNode length = new DefaultMutableTreeNode("Length : " + ship.length);
                shipNode.add(weight);
                shipNode.add(width);
                shipNode.add(draft);
                shipNode.add(length);
                if (ship instanceof PassengerShip) {
                    PassengerShip s = (PassengerShip) ship;
                    shipNode.add(new DefaultMutableTreeNode("No.Of Occupied Rooms : " + s.numberOfOccupiedRooms));
                    shipNode.add(new DefaultMutableTreeNode("No.Of Passengers     : " + s.numberOfPassengers));
                    shipNode.add(new DefaultMutableTreeNode("No.Of Rooms          : " + s.numberOfRooms));
                }
                if (ship instanceof CargoShip) {
                    CargoShip s = (CargoShip) ship;
                    shipNode.add(new DefaultMutableTreeNode("Cargo Value      : " + s.cargoValue));
                    shipNode.add(new DefaultMutableTreeNode("Cargo Volume     : " + s.cargoVolume));
                    shipNode.add(new DefaultMutableTreeNode("Cargo Weight     : " + s.cargoWeight));
                }
                allShipsNode.add(shipNode);
                portNode.add(allShipsNode);

            }
            portNode.add(queShipsNode);
            DefaultMutableTreeNode personsNode = new DefaultMutableTreeNode("Persons");
            for (Person p : port.persons) {
                personsNode.add(new DefaultMutableTreeNode(p.name + " Skill: " + p.skill));
            }
            portNode.add(personsNode);
        }
    }

    /**
     * this method reads a text file line by line stores the parent index in to HashMaps to link objects to their parent.
     *
     * @param st
     * @param mSeaport
     * @param mDock
     * @param mShip
     */
    public void read(String st, HashMap<Integer, SeaPort> mSeaport, HashMap<Integer, Dock> mDock,
            HashMap<Integer, Ship> mShip) {

        Scanner scan = new Scanner(st);

        if (!scan.hasNext()) {
            scan.close();
            return;
        }

        switch (scan.next()) {
            case "port":
                Thing t = readPort(scan);
                SeaPort portObj = new SeaPort(t.index, t.name);
                ports.add(portObj);
                mSeaport.put(portObj.index, portObj);
                break;

            case "dock":
                Dock dockObj = readDock(scan, mSeaport);
                mDock.put(dockObj.index, dockObj);
                mSeaport.get(dockObj.parent.index).docks.add(dockObj);
                break;

            case "pship":
                PassengerShip pShipObj = readPassengerShip(scan, mDock, mSeaport);
                //  assignShip(pShipObj);
                //           System.out.println("PS : " + pShipObj.isDocked);
                mShip.put(pShipObj.index, pShipObj);
                break;

            case "cship":
                CargoShip cShipObj = readCargoShip(scan, mDock, mSeaport);
                // assignShip(cShipObj);
                mShip.put(cShipObj.index, cShipObj);
                break;

            case "person":
                Person tempPerson = readPerson(scan, mSeaport);
                mSeaport.get(tempPerson.parent.index).persons.add(tempPerson);
                break;

            case "job":
                Job jobObj = readJob(scan, mShip);
                if (jobObj == null) {
                    break;
                }
                int pIndex = jobObj.parent.index;
                jobObj.ship = mShip.get(pIndex);
           //     for (int d : mDock.keySet()) {
                    if (jobObj.ship.parent != null && mDock.get(jobObj.ship.parent.index) != null) {
                        jobObj.status = Status.PENDING;
                        jobObj.dock = mDock.get(jobObj.ship.parent.index);
                    }
          //      }
                createProgressBar(jobObj, jobObj.status);
                break;
            default:
                break;
        }// End switch Statement

    }// END read method

   

    /**
     * Creates the progress Panel with all the requirements
     * @param job
     * @param status 
     */
    private void createProgressBar(Job job, Status status) {

        JProgressBar progressBar = new JProgressBar();
        progressBar.setPreferredSize(new Dimension(200, 14));
        progressBar.setMaximumSize(new Dimension(200, 14));
        job.setProgressBar(progressBar);

        JButton pauseButton = new JButton("Pause");
        JButton cancelButton = new JButton("Cancel");
        pauseButton.setPreferredSize(new Dimension(50, 20));
        cancelButton.setPreferredSize(new Dimension(50, 20));
        job.setPauseButton(pauseButton);
        job.setCancelButton(cancelButton);


        JLabel shipLabel = new JLabel("\t Ship: " + job.ship.name);
        JLabel jobLabel = new JLabel(" " + job.name);

        subProgressPanel.setLayout(new GridLayout(0, 6, 20, 10));
        
        subProgressPanel.add(shipLabel);
        subProgressPanel.add(jobLabel);
        subProgressPanel.add(progressBar);
        subProgressPanel.add(pauseButton);
        subProgressPanel.add(cancelButton);
        subProgressPanel.add(new JLabel(" "));

        subProgressPanel.repaint();

        job.status = status;
        job.skillCount = skillCount;
        job.personalSkillCount = personalSkillCount;

        Thread jobThread = new Thread(job);
        jobThread.start();

        subProgressPanel.invalidate();

    }

    @Override
    public String toString() {
        String st = "\n\n\t\t\t  -------------------------THE WORLD-------------------------";

        if (ports.isEmpty()) {
            return st;
        }
        for (SeaPort msp : ports) {
            st += msp;
        }
        return st;
    }// End toString method

    /**
     * this method searched a file in regards to what option was picked
     *
     * @param type
     * @param target
     * @return
     */
    public String readOptions(String type, String target) {
        String st = "";
        switch (type) {
            case "Name":
                st = searchName(target);
                break;
            case "Index":
                // check if int
                try {
                    st = searchIndex(Integer.parseInt(target));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "!!invalid format for Index!!", "Notice",
                            JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Skill":
                st = searchSkill(target);
                break;
            default:
                break;
        }
        return st;
    } // end class search

    /**
     * uses name option to search for target search
     *
     * @param target
     * @return
     */
    public String searchName(String target) {
        for (SeaPort msp : ports) {
            if (msp.name.equalsIgnoreCase(target)) {

                return msp.toString();
            }
            for (Dock md : msp.docks) {
                if (md.name.equalsIgnoreCase(target)) {
                    return md.toString();
                }
            }
            for (Ship ms : msp.ships) {
                if (ms.name.equalsIgnoreCase(target)) {
                    return ms.toString();
                }
            }
            for (Person mp : msp.persons) {
                if (mp.name.equalsIgnoreCase(target)) {
                    return mp.toString();
                }
            }
        }
        return "Name Not Found";
    }// End searchName method

    /**
     * uses Index option to search for target search
     *
     * @param target
     * @return
     */
    public String searchIndex(int target) {
        for (SeaPort msp : ports) {
            if (msp.index == target) {
                return msp.toString();
            }
            for (Dock md : msp.docks) {
                if (md.index == target) {
                    return md.toString();
                }
            }
            for (Ship ms : msp.ships) {
                if (ms.index == target) {
                    return ms.toString();
                }
            }
            for (Person mp : msp.persons) {
                if (mp.index == target) {
                    return mp.toString();
                }
            }
        }
        return "Index Not Found";
    }// ENd searchIndex method

    /**
     * Searches for supplied target by skill name to be searched for
     *
     * @param target
     *
     * @return info if target if found, message saying not found if not
     */
    public String searchSkill(String target) {
        String st = "";
        for (SeaPort msp : ports) {
            for (Person mp : msp.persons) {
                if (mp.skill.equalsIgnoreCase(target)) {
                    st += mp.toString() + '\n';
                }
            }
        }
        if ("".equals(st)) {
            return "Target Not Found";
        }
        return st;

    }// End SearchSkill method.

    /**
     * Scanner read scanner constructor for port
     * @param scan
     * @return 
     */
    private Thing readPort(Scanner scan) {
        int index = 0;
        String name = "";
        Thing parent = null;
        if (scan.hasNext()) {
            name = scan.next();
        }
        if (scan.hasNextInt()) {
            index = scan.nextInt();
        }
        return new Thing(index, name, parent);

    }

    /**
     * read scanner constructor for Dock
     * @param scan
     * @param mSeaport
     * @return 
     */
    private Dock readDock(Scanner scan, HashMap<Integer, SeaPort> mSeaport) {
        Thing t = readPort(scan);
        Thing parent = null;
        int pIndex = 0;
        if (scan.hasNextInt()) {
            pIndex = scan.nextInt();
        }
        if (mSeaport.get(pIndex) != null) {
            parent = mSeaport.get(pIndex);
        }
        return new Dock(t.index, t.name, parent);

    }

    /**
     * read scanner constructor for Person
     * @param scan
     * @param mSeaport
     * @return 
     */
    private Person readPerson(Scanner scan, HashMap<Integer, SeaPort> mSeaport) {
        Thing t = readPort(scan);
        Thing parent = null;
        int pIndex = 0;
        if (scan.hasNextInt()) {
            pIndex = scan.nextInt();
        }
        String skill = "";
        if (scan.hasNext()) {
            skill = scan.next();
        }
        if (mSeaport.get(pIndex) != null) {
            parent = mSeaport.get(pIndex);
        }
        String key = parent.name + "." + skill;
        if (skillCount.get(key) == null) {
            skillCount.put(key, 0);
        }
        if (personalSkillCount.get(key) == null) {
            personalSkillCount.put(key, 0);
        }
        skillCount.put(key, skillCount.get(key) + 1);
        personalSkillCount.put(key, personalSkillCount.get(key) + 1);
        return new Person(t.index, t.name, parent, skill);

    }

    /**
     * read scanner constructor for ship
     * @param scan
     * @param mDock
     * @param mSeaport
     * @return 
     */
    private Ship readShip(Scanner scan, HashMap<Integer, Dock> mDock, HashMap<Integer, SeaPort> mSeaport) {
        Thing t = readPort(scan);
        double draft = 0, length = 0, weight = 0, width = 0;
        int pIndex = 0;
        Thing parent = null;
        if (scan.hasNextInt()) {
            pIndex = scan.nextInt();
            if (mDock.get(pIndex) != null) {
                parent = mDock.get(pIndex);
            } else if (mSeaport.get(pIndex) != null) {
                parent = mSeaport.get(pIndex);
            }

        }
        if (scan.hasNextDouble()) {
            draft = scan.nextDouble();
            length = scan.nextDouble();
            weight = scan.nextDouble();
            width = scan.nextDouble();
        }

        Ship ship = new Ship(t.index, t.name, parent, draft, length, weight, width, false);
        ship.isDocked = false;
        if (mDock.get(pIndex) != null) {
            ship.isDocked = true;
            mSeaport.get(mDock.get(pIndex).parent.index).ships.add(ship);
            SeaPortProgram.messageArea.append(ship.index + " is docked " + ship.parent.name + "\n");

        } else {
            mSeaport.get(pIndex).ships.add(ship);
            mSeaport.get(pIndex).que.add(ship);
            //       System.out.println(ship.index + " is not docked " + ship.parent.name);
        }

        return ship;
    }

    /**
     * read scanner constructor for Passenger Ship
     * @param scan
     * @param mDock
     * @param mSeaport
     * @return 
     */
    private PassengerShip readPassengerShip(Scanner scan, HashMap<Integer, Dock> mDock, HashMap<Integer, SeaPort> mSeaport) {
        Ship s = readShip(scan, mDock, mSeaport);
        //     System.out.println("READ: " + s.isDocked);
        int numberOfOccupiedRooms = 0;
        int numberOfPassengers = 0;
        int numberOfRooms = 0;
        if (scan.hasNextInt()) {
            numberOfOccupiedRooms = scan.nextInt();
            numberOfPassengers = scan.nextInt();
            numberOfRooms = scan.nextInt();
        }
        return new PassengerShip(s.index, s.name, s.parent, s.draft, s.length, s.weight, s.width, numberOfOccupiedRooms, numberOfPassengers, numberOfRooms, s.isDocked);
    }

    /**
     * read scanner constructor for cargo Ship
     * @param scan
     * @param mDock
     * @param mSeaport
     * @return 
     */
    private CargoShip readCargoShip(Scanner scan, HashMap<Integer, Dock> mDock, HashMap<Integer, SeaPort> mSeaport) {
        Ship s = readShip(scan, mDock, mSeaport);
        double cargoValue = 0;
        double cargoVolume = 0;
        double cargoWeight = 0;
        if (scan.hasNextInt()) {
            cargoValue = scan.nextDouble();
            cargoVolume = scan.nextDouble();
            cargoWeight = scan.nextDouble();
        }
        return new CargoShip(s.index, s.name, s.parent, s.draft, s.length, s.weight, s.width, cargoValue, cargoVolume, cargoWeight, s.isDocked);
    }

    /**
     * read scanner constructor for Job
     * @param scan
     * @param mShip
     * @return 
     */
    private Job readJob(Scanner scan, HashMap<Integer, Ship> mShip) {
        Thing t = readPort(scan);
        double duration = 0;
        Thing parent = null;
        ArrayList<String> requirements = new ArrayList<>();
        if (scan.hasNextInt()) {
            int pIndex = scan.nextInt();
            if (mShip.get(pIndex) != null) {
                parent = mShip.get(pIndex);
            }

        }
        if (scan.hasNextDouble()) {
            duration = scan.nextDouble();
        }
        while (scan.hasNext()) {
            String stringSkill = scan.next();
            requirements.add(stringSkill);
        }
        if (parent == null) {
            SeaPortProgram.messageArea.append(t.name + "(" + t.index + ") has no ship assinged, discard job\n");
            return null;
        }
        Job job = new Job(t.index, t.name, parent, Status.WAITING, duration, requirements);
        //    System.out.println("AFTER : " + job.duration);
        ((Ship) parent).jobs.add(job);
        return job;
        //new Thread(this).start();
    }

}// END World Class
