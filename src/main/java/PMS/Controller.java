package PMS;

import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Created by zeningjiang on 3/2/2017.
 */


@org.springframework.stereotype.Controller
public class Controller {



    private UserRepo repo;
    public Controller(UserRepo name){
        repo =name;

    }

    @GetMapping("/home")
    public String addBuddyForm(Model model){
        return "home";
    }


    public UserRepo getRepo() {
        return repo;
    }
}
