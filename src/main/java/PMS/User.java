package PMS;

import javax.persistence.*;

/**
 * Created by yashpatel on 3/8/2017.
 */
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected Integer id;

    protected String firstName;
    protected String lastName;

    public User(){}

    public User(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
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
}
