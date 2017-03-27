package PMS;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
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

    @OneToMany
    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @OneToMany
    List<Project> projects;

    public Professor(){super();}

    public Professor(String firstName, String lastName, String email, String password){
        super(firstName, lastName, email, password);
        activeProjects = new ArrayList<Project>();
    }

    public void createProject(String name,String  description){
        Project proj = new Project(name, description);
        activeProjects.add(proj);
    }

    public List<Project> getActiveProjects() {
        return activeProjects;
    }

    public void setActiveProjects(List<Project> activeProjects) {
        this.activeProjects = activeProjects;
    }

    public void giveGrade(Deliverable dev, String grade){
        dev.setGrade(grade);
    }

}
