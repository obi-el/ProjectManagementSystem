package PMS;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by yashpatel on 3/8/2017.
 */
@Entity
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected Integer id;

    protected String firstName;
    protected String lastName;


    protected String email;

    protected String password;

    @ManyToMany
    protected List<Project> projects;

    public User(){}

    public User(String firstName, String lastName, String email ){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Project> getProjects(){
        return projects;
    }

    public void setProject(List<Project> projects){
        this.projects = projects;
    }



}
