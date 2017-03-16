package PMS;

import org.springframework.context.annotation.Bean;

/**
 * Created by obinnaelobi on 3/16/2017.
 */
public class Login {

    private String email;
    private String password;


    public Login(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }





}
