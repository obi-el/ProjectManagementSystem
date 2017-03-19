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
    public Controller(StudentRepo name, profRepo repo) {
        this.studentRepo = name;
        this.profRepo = repo;

    }

    @GetMapping({"/home", "", "/"})
    public String firstPage(Model model) {
       model.addAttribute("login", new User());
        return "home";
    }


    public StudentRepo getStudentRepo() {
        return studentRepo;
    }

    public PMS.profRepo getProfRepo() {
        return profRepo;
    }


    @PostMapping(value = "/signin")
    public String signIn(Model model, @RequestParam(value = "email", required = true) String email, @RequestParam(value = "password", required = true) String password) {
        String ret;
        model.addAttribute("login", new User());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (studentRepo.existsByEmail(email) && encoder.matches(password, studentRepo.findByEmail(email).getPassword())) {
            model.addAttribute("user", studentRepo.findByEmail(email));
            model.addAttribute("signedin", true);
            ret = "studentPage";
        } else if (profRepo.existsByEmail(email) && encoder.matches(password, profRepo.findByEmail(email).getPassword())) {
            model.addAttribute("user", profRepo.findByEmail(email));
            model.addAttribute("signedin", true);
            ret = "profPage";
        } else {
            model.addAttribute("signedin", false);
            ret = "home";
        }


        return ret;
    }



    @PostMapping(value = "/register")
    public String registration(Model model, @RequestParam(value = "firstName", required = true) String firstName, @RequestParam(value = "lastName", required = true) String lastName, @RequestParam(value = "email", required = true) String email, @RequestParam(value = "password", required = true) String password, @RequestParam(value = "userType", required = true) String userType) {
        String ret;
        model.addAttribute("login", new Register());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pw = encoder.encode(password);
        if (userType.equals("student")) {
            Student s = new Student(firstName, lastName, email, pw);
            model.addAttribute("user", s);
            model.addAttribute("signedin", true);
            studentRepo.save(s);
            ret = "studentPage";
        } else if (userType.equals("professor")) {
            Professor p = new Professor(firstName, lastName, email, pw);
            model.addAttribute("user", p);
            model.addAttribute("signedin", true);
            profRepo.save(p);
            ret = "profPage";
        } else ret = "redirect:error";

        return ret;

    }
}
