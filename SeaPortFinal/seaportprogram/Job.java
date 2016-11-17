/**
 * File:Job.java
 * Author:Cesar Lopez
 * Date: October 15, 2016
 * Purpose: class implements runnable for job threads and progress bar.
 */
package seaportprogram;

import java.awt.Color;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class Job extends Thing implements Runnable {

    ArrayList<String> requirements = new ArrayList<>();
    double duration = 0;

    Ship ship = null;
    Dock dock = null;
    JProgressBar progressBar;// = new JProgressBar();
    JButton pauseButton = new JButton("Stop");
    JButton cancelButton = new JButton("Cancel");
    Status status;
    int value = 0;
    int timeProgress = 0;
    SeaPort sPort = null;
    HashMap<String, Integer> skillCount;
    HashMap<String, Integer> personalSkillCount;

    enum Status {
        RUNNING, SUSPENDED, WAITING, DONE, CANCEL, PENDING
    };

    public Job(int index, String name, Thing parent,
            Status status, double duration, ArrayList<String> requirements) {
        super(index, name, parent);
        
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        this.requirements = requirements;
        this.status = status;
        this.duration = duration;

    }// End of Job Constructor

    void showStatus(Status st) {
        status = st;
        switch (status) {
            case RUNNING:
                pauseButton.setBackground(Color.GREEN);
                pauseButton.setText("Pause");

                break;
            case PENDING:
                pauseButton.setBackground(Color.YELLOW);
                pauseButton.setText("Pending");
                break;
            case SUSPENDED:
                pauseButton.setBackground(Color.YELLOW);
                pauseButton.setText("Suspended");
                break;
            case WAITING:
                pauseButton.setBackground(Color.ORANGE);
                pauseButton.setText("Waiting turn");
                break;
            case DONE:
                pauseButton.setBackground(Color.GREEN);
                pauseButton.setText("Done");
                break;
            case CANCEL:
                pauseButton.setBackground(Color.RED);
                pauseButton.setText("Canceled");
                break;
        } // end switch on status
    } // end showStatus

    @Override
    public void run() {
    	
    	
        sPort = getSeaPort(this.parent);

        synchronized (sPort) {
            while (status == Status.WAITING) {
                showStatus(Status.WAITING);
                for (Dock dock : sPort.docks) {
                    if (dock.ship == null) {
                        dock.ship = this.ship;
                        SeaPortProgram.messageArea.append(this.ship.index + " is docked at " + dock.index + "\n");
                        this.status = Status.PENDING;
                        showStatus(this.status);
                        break;
                    }
                }
            }

        }

        boolean shouldStartJob = true;
        String key = null;

        outerloop:
        while (status == Status.PENDING) {
            if (sPort.name != null) {
                synchronized (status) {
                    for (String r : this.requirements) {
                        key = sPort.name + "." + r;
                        //       		
                        if (skillCount.get(key) == null) {
                            status = Status.CANCEL;
                            showStatus(status);
                            cancelButton.setEnabled(false);
                            pauseButton.setEnabled(false);
                           
                            SeaPortProgram.messageArea.append("ERROR: Cannot find required resource at the port, cancelling job: " + name + "(index =" + index + ")\n\n");
                            shouldStartJob = false;
                            break outerloop;
                        }
                        synchronized (String.valueOf(skillCount.get(key))) {
                            if (skillCount.get(key) == 0) {
                                shouldStartJob = false;
                                continue outerloop;
                            } else {
                                shouldStartJob = true;
                            }
                        }
                    }
                    if (shouldStartJob) {
                        ArrayList<Job> jobs = ((Ship) this.parent).jobs;
                        boolean allJobsDone = true;
                        for (Job j : jobs) {
                            if (status == Status.PENDING && j.status == Status.RUNNING) {
                                allJobsDone = false;
                                break;
                            }
                        }
                        if (allJobsDone) {
                            status = Status.RUNNING;
                            showStatus(status);
                            updatePersonsCount(sPort.name, this.requirements, -1);
                            shouldStartJob = false;
                            try {
                                Thread.sleep(100);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;

                        }
                    }
                }
            }
        }

        while (timeProgress < 100 && status == Status.RUNNING) {
            showStatus(status);
            try {
                progressBar.setValue(timeProgress++);
                progressBar.repaint();
                //   status = Status.DONE;                                               

                Thread.sleep(100);

            } catch (Exception e) {
                System.out.println("error line 133 Job class");//
            }
        }
        try {
            //   System.out.println("Release ship");
              synchronized (sPort) {
            if (status == Status.RUNNING) {
                status = Status.DONE;
                showStatus(status);
                progressBar.setValue(100);
                progressBar.repaint();
                this.ship.onComplete(this.ship, dock);
                updatePersonsCount(sPort.name, this.requirements, 1);
                refreshResourcePanel();
                progressBar.setString("Job Done");
                cancelButton.setEnabled(false);
                pauseButton.setEnabled(false);
                sPort.notifyAll();
            }
              }
        } catch (Exception e) {
            System.out.println("Error from between line 113-127 job class ");
            e.printStackTrace();
        }

    }

    void updatePersonsCount(String portname, ArrayList<String> requirements, int kount) {
        String key;
        for (String requirement : requirements) {
            key = sPort.name + "." + requirement;
            //    System.out.println(key);
            //  if (skillCount.get(key)!=null)
            skillCount.put(key, skillCount.get(key) + kount);
            //System.out.println(key + " increased by " + kount + " to " + skillCount.get(key));
        }
        refreshResourcePanel();
    }

    void refreshResourcePanel() {
        SortedSet<String> keys = new TreeSet<>(personalSkillCount.keySet());
        SeaPortProgram.resourceArea.setText("");
        String[] keyParts;
        String prevPortName = "";
        for (String key : keys) {
            keyParts = key.split("\\.");
            //      System.out.println(key + " " + keyParts.length);
            if (!prevPortName.equals(keyParts[0])) {
                SeaPortProgram.resourceArea.append("Port : " + keyParts[0]);
            }
            SeaPortProgram.resourceArea.append("\t" + keyParts[1] + " : " + skillCount.get(key) + "/" + personalSkillCount.get(key) + " availabe \n");
            prevPortName = keyParts[0];
        }
    }

    @Override
    public String toString() {
        String st = "Job: " + super.toString() + " " + requirements;
        return st;
    }

    private SeaPort getSeaPort(Thing s) {
        //SeaPort sPort = null;
        ship = (Ship) s;
        if (ship.parent != null && ship.parent.parent != null) {
            sPort = (SeaPort) ship.parent.parent;
        } else {
            sPort = (SeaPort) ship.parent;
        }
        return sPort;
    }

    public void setProgressBar(JProgressBar progBar) {
        progressBar = progBar;
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setValue(value);
        progressBar.setStringPainted(true);
        progressBar.setForeground(Color.BLUE);
        progressBar.repaint();
        progressBar.invalidate();
    }

    public void setPauseButton(JButton button) {
        pauseButton = button;
        pauseButton.addActionListener((ActionEvent e) -> {
            if (status == Status.RUNNING) {
                this.status = (Status.SUSPENDED);
                pauseButton.setText("Resume");
                progressBar.setForeground(Color.YELLOW);
            } else if (status == Status.SUSPENDED) {
                this.status = (Status.RUNNING);
                pauseButton.setText("Pause");
                progressBar.setForeground(Color.BLUE);
                //    System.out.println(time);
                Thread t = new Thread(this);
                t.start();
            }
        });
    }

    public void setCancelButton(JButton button) {
        cancelButton = button;
        cancelButton.addActionListener((ActionEvent e) -> {
            try {
                if (status != Status.CANCEL) {
                    this.status = (Status.CANCEL);
                    this.ship.onComplete(this.ship, dock);
                    progressBar.setForeground(Color.RED);

                }
            } catch (Exception error) {
                System.out.println("Cancel was pressed");
            }
        });

    }

}
