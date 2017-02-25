/**
 * THis class should define student information.
 */
package studentdatabase;

/**
 *
 * @author CeL
 */
public class Student {
    
    /**
     * defining instance variable for the student name and major
     * and two variables that are used to compute the GPA.
     */
    private String name;
    private String major;
    private int totalQualityPoints;
    private int totalCredits;

    /**
     * A constructor that is used when new student records are created. It
     * should accept the name and major as parameters and initialize the field
     * that are used to compute the GPA to zero.
     *
     * @param name
     * @param major
     */
    public Student(String name, String major) {
        this.name = name;
        this.major = major;
        this.totalQualityPoints = 0;
        this.totalCredits = 0;

    }

    /**
     *Method should should accept the COURSE GRADE and CREDIT HOURS 
     * and update the variables used to compute the GPA
     * 
     * @param courseGrade
     * @param creditHours
     */
    public void courseCompleted(String courseGrade, int creditHours) {

        double letterValue = 0.0;

        switch (courseGrade) {
            case "A":
                letterValue = 4.0;
                break;
            case "B":
                letterValue = 3.0;
                break;
            case "C":
                letterValue = 2.0;
                break;
            case "D":
                letterValue = 1.0;
                break;
            case "F":
                letterValue = 0.0;
                break;
            default:
                break;
        }
        totalCredits += creditHours;
        totalQualityPoints += (letterValue * creditHours);
    }
    
   
    /**
     * method should override toString and return a labeled string
     * containing the student name, major and GPA.

     * 
     * @return 
     */     
    @Override
    public String toString() {
        double gpa = 0.00;
       
        if (totalCredits == 0) {  
          return ("Student: " + name + "\nMajor: " + major + "\nGPA: 4.0");
          
        }else{
            gpa = totalQualityPoints / totalCredits;
            
        }
        return ("Student: " + name + "\nMajor: " + major +"\nGPA: " + gpa);       
    }
}
