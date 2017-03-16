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
    public String firstPage(Model model){
        model.addAttribute("login", new User());
        return "home";
    }


    public StudentRepo getStudentRepo() {
        return studentRepo;
    }

    public PMS.profRepo getProfRepo() {
        return profRepo;
    }



    @PostMapping(value ="/signin")
    public String signIn(Model model, @RequestParam(value="email" , required = true)String email, @RequestParam(value="password" , required = true)String password){
        String ret;
        model.addAttribute("login", new Login());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(studentRepo.existsByEmail(email) && encoder.matches(password, studentRepo.findByEmail(email).getPassword())){
            model.addAttribute("user", studentRepo.findByEmail(email));
            model.addAttribute("signedin", true);
            ret = "studentPage";
        }
        else if(profRepo.existsByEmail(email) && encoder.matches(password, profRepo.findByEmail(email).getPassword())){
            model.addAttribute("user", profRepo.findByEmail(email));
            model.addAttribute("signedin", true);
            ret = "profPage";
        }
        else{
            model.addAttribute("signedin", false);
            ret = "home";
        }


        return ret;
    }

    @GetMapping("/signin")
    public String homeForm(Model model){
        model.addAttribute("login", new Login());
        return "home";
    }

    @PostMapping(value ="/register")
    public void registration(Model model ,@RequestParam(value="firstName" , required = true)String firstName, @RequestParam(value="lastName" , required = true)String lastName, @RequestParam(value="email" , required = true)String email, @RequestParam(value="password" , required = true)String password){



    }

}
