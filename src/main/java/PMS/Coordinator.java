package PMS;

import javax.persistence.Entity;
import java.util.ArrayList;

/**
 * Created by yashpatel on 3/8/2017.
 */
@Entity
public class Coordinator extends User{

    public Coordinator(){super();}

    private ArrayList<Student> students;

    public Coordinator(String firstName, String lastName, String email, String password){
        super(firstName, lastName, email, password);
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
