package PMS;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeningjiang on 3/2/2017.
 */
@Entity
public class Professor extends User{
    @OneToMany
    List<Project> activeProjects;

    public Professor(){super();}

    public Professor(String firstName, String lastName){
        super(firstName, lastName);
        activeProjects = new ArrayList<Project>();
    }

    public void createProject(String name){
        Project proj = new Project(name);
        activeProjects.add(proj);
    }

    public List<Project> getActiveProjects() {
        return activeProjects;
    }

    public void setActiveProjects(List<Project> activeProjects) {
        this.activeProjects = activeProjects;
    }

}
