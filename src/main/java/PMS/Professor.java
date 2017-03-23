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
    /**
     * create projects
     * @param name - project title
     */
    public void createProject(String name, String description){
        Project proj = new Project(name, description);
        activeProjects.add(proj);
    }

    /**
     * assigns student to a particular project
     * @param p - project to be assigned to
     * @param s - student to be assigned
     */
    public void assignStudent(Project p, Student s){
        p.addMembers(s);
    }

    public List<Project> getActiveProjects() {
        return activeProjects;
    }

    public void setActiveProjects(List<Project> activeProjects) {
        this.activeProjects = activeProjects;
    }

}
