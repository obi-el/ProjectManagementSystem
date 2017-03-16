package PMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by zeningjiang on 3/2/2017.
 */


@org.springframework.stereotype.Controller
public class Controller {



    private StudentRepo studentRepo;


    private profRepo profRepo;



    @Autowired
    public Controller(StudentRepo name, profRepo repo){
        this.studentRepo =name;
        this.profRepo = repo;

    }

    @GetMapping("/home")
    public String addBuddyForm(Model model){
        return "home";
    }


    public StudentRepo getStudentRepo() {
        return studentRepo;
    }

    public PMS.profRepo getProfRepo() {
        return profRepo;
    }



    @GetMapping(value ="/signin")
    public void signIn(@RequestParam(value="email" , required = true)String email, @RequestParam(value="password" , required = true)String password){
        String ret;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pw = encoder.encode(password);

    }

    @PostMapping(value ="/register")
    public void registration(){

    }

}
