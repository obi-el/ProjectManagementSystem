package PMS;

import java.util.ArrayList;

/**
 * Created by zeningjiang on 3/2/2017.
 */
public class Professor extends User{

    ArrayList<Project> activeProjects;

    public Professor(){super();}

    public Professor(String firstName, String lastName){
        super(firstName, lastName);
        activeProjects = new ArrayList<Project>();
    }

    public ArrayList<Project> getActiveProjects() {
        return activeProjects;
    }

    public void setActiveProjects(ArrayList<Project> activeProjects) {
        this.activeProjects = activeProjects;
    }

}
