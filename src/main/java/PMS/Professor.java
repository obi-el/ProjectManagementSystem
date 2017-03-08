package PMS;

import javax.persistence.OneToMany;
import java.util.ArrayList;

/**
 * Created by zeningjiang on 3/2/2017.
 */
public class Professor extends User{
    @OneToMany
    ArrayList<Project> activeProjects;

    public Professor(){super();}

    public Professor(String firstName, String lastName){
        super(firstName, lastName);
        activeProjects = new ArrayList<Project>();
    }

    public void createProject(String name){
        Project proj = new Project(name);
        activeProjects.add(proj);
    }

    public ArrayList<Project> getActiveProjects() {
        return activeProjects;
    }

    public void setActiveProjects(ArrayList<Project> activeProjects) {
        this.activeProjects = activeProjects;
    }

}
