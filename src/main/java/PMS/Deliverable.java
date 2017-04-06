package PMS;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by obinnaelobi on 3/7/2017.
 */
@Entity
public class Deliverable {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected Integer id;


    private Date deadline;
    private String name, description;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    private String grade;



    @ManyToOne
    private Project project;

    public Deliverable(){}

    public Deliverable(String name, Date deadline){
        this.name = name;
        this.deadline = deadline;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
