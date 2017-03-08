package PMS;

import org.hibernate.bytecode.spi.ProxyFactoryFactory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

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
    private Professor supervisor;

    private ArrayList<Student> members;

    public Project(){}

    public Project(String name){
        this.name = name;
        members = new ArrayList<Student>();
    }

    public Professor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Professor supervisor) {
        this.supervisor = supervisor;
    }

    public ArrayList<Student> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Student> members) {
        this.members = members;
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
