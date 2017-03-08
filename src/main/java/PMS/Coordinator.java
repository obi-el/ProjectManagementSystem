package PMS;

import java.util.ArrayList;

/**
 * Created by yashpatel on 3/8/2017.
 */
public class Coordinator extends User{

    public Coordinator(){super();}

    private ArrayList<Student> students;

    public Coordinator(String firstName, String lastName){
        super(firstName, lastName);
        students = new ArrayList<Student>();
    }

    public void createDeliverable(){
    }

    public ArrayList<Student> findUnassignedStudents(){
        ArrayList<Student> UAS = new ArrayList<Student>();
        for (Student s : students){
            if (s.getMyProject() == null)
                UAS.add(s);
        }
        return UAS;
    }

    public void contactUnassignedStudents(){
        ArrayList<Student> UAS = findUnassignedStudents();
        for(Student s : UAS){

            /** email a reminder to each student */
        }
    }
}
