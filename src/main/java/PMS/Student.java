package PMS;

import javax.persistence.*;

/**
 * Created by zeningjiang on 3/2/2017.
 */
@Entity
public class  Student extends User {
    /*@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id = null;*/

    private Project myProject;

    public Student(){super();}

    public Student(String firstName, String lastName){
        super(firstName, lastName);
    }

    public Project getMyProject() {
        return myProject;
    }

    public void setMyProject(Project myProject) {
        this.myProject = myProject;
    }





}
