package PMS;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zeningjiang on 3/2/2017.
 */
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected Integer id;

    private String name;
    private String description;

    @ManyToOne
    private Professor supervisor;

    private HashMap<String, Integer> programs;

    @OneToMany
    private List<Student> members;

    public Project(){}

    public Project(String name, String description){
        this.name = name;
        this.description = description;
        members = new ArrayList<Student>();
        programs = new HashMap<String, Integer>();
    }

    public Professor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Professor supervisor) {
        this.supervisor = supervisor;
    }

    /*@OneToMany(mappedBy = "project")
    public ArrayList<Student> getMembers() {
        return members;
    }*/

    public List<Student> getMembers(){
        return members;
    }

    /*public void setMembers(ArrayList<Student> members) {
        this.members = members;
    }*/
    public void setMembers(List<Student> members){
        this.members = members;
    }

    public void addMembers(Student student){
        members.add(student);
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
