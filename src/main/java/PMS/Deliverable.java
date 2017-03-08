package PMS;

import java.util.Date;

/**
 * Created by obinnaelobi on 3/7/2017.
 */
public class Deliverable {

    private Date deadline;
    private String name, description;

    public Deliverable(){}

    public Deliverable(String name, Date deadline){
        this.name = name;
        this.deadline = deadline;
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
