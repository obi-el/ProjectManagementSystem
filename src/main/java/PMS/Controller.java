package PMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by zeningjiang on 3/2/2017.
 */


@org.springframework.stereotype.Controller
public class Controller {

    private Coordinator cod;
    private StudentRepo studentRepo;
    private profRepo profRepo;
    private ProjRepo projRepo;


    @Autowired


    public Controller(StudentRepo studentRepo, profRepo profRepo, ProjRepo projRepo) {
        this.studentRepo = studentRepo;
        this.profRepo = profRepo;
         this.projRepo = projRepo;
        cod =  new Coordinator("Rick","Sanchez","coordinatorricksanchez@gmail.com", "dcba4321");

    }

    @GetMapping({"/home", "", "/"})
    public String firstPage(Model model, HttpSession session) {
        model.addAttribute("login", new User());
        if (session.getAttribute(SessionVariables.signedin) == null) return "home";
        else {
            boolean signedin = (Boolean) session.getAttribute(SessionVariables.signedin);
            User curUser = (User) session.getAttribute(SessionVariables.user);
            if (signedin == true) {
                if(curUser instanceof Coordinator) return "coordPage";
                String ret = (curUser instanceof Student) ? "studentPage" : "profPage";
                return ret;
            }

        }

        return "home";
    }

    @GetMapping({"/studentPage"})
    public String studentPage() {
        return "studentPage";
    }

    @GetMapping({"/profPage"})
    public String profPage() {
        return "profPage";
    }

    @GetMapping({"/coordPage"})
    public String coordPage(){
        return "coordPage";
    }

    public StudentRepo getStudentRepo() {
        return studentRepo;
    }

    public PMS.profRepo getProfRepo() {
        return profRepo;
    }


    @PostMapping(value = "/signin")
    public String signIn(Model model, HttpSession session, @RequestParam(value = "email", required = true) String email, @RequestParam(value = "password", required = true) String password) {
        String ret;
        model.addAttribute("login", new Login());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (studentRepo.existsByEmail(email) && encoder.matches(password, studentRepo.findByEmail(email).getPassword())) {
            session.setAttribute(SessionVariables.user, studentRepo.findByEmail(email));
            session.setAttribute(SessionVariables.signedin, true);
            ret = "studentPage";
        } else if (profRepo.existsByEmail(email) && encoder.matches(password, profRepo.findByEmail(email).getPassword())) {
            session.setAttribute(SessionVariables.user, profRepo.findByEmail(email));
            session.setAttribute(SessionVariables.signedin, true);
            ret = "profPage";
        } else if(cod.getEmail().equals(email) && password.matches(cod.getPassword())){
            session.setAttribute(SessionVariables.user, cod);
            session.setAttribute(SessionVariables.signedin, true);
            ret = "coordPage";
        }

        else {
            session.setAttribute(SessionVariables.signedin, false);
            ret = "home";
        }

        return ret;
    }



    @GetMapping(value = "/logoutPage")
    public String logout(HttpSession session){
        session.removeAttribute(SessionVariables.user);
        session.removeAttribute(SessionVariables.signedin);
        session.invalidate();
        return "redirect:home";
    }

    @PostMapping(value = "/coordPage/sendreminder")
    public String sendReminder(){
        try{
            cod.contactUnassignedStudents(studentRepo.findAll(), cod.getPassword() );
        }catch (Exception e){
            return "error";
        }
         return "coordPage";

    }

    @GetMapping(value = "/allProjects")
    public
    @ResponseBody
    List<Project> getProjects() {
        return this.projRepo.findAll();
    }

    @PostMapping("/studentPage/pickProject")
    public String pickProject(HttpSession session, Model model, @RequestParam(value = "projectName", required = true) String projectName) {
        User user = (User) session.getAttribute(SessionVariables.user);
        Student user2 = (Student) user;
        if(user2.hasProject) return"error";
        user2.setMyProject(projRepo.findByName(projectName));
        user2.setHasProject(true);
        model.addAttribute("project", projRepo.findByName(projectName));

        Project proj = projRepo.findByName(projectName);
        proj.addMembers(user2);

        return "studentPage";
    }


    @PostMapping(value = "/register")
    public String registration(Model model, HttpSession session, @RequestParam(value = "firstName", required = true) String firstName, @RequestParam(value = "lastName", required = true) String lastName, @RequestParam(value = "email", required = true) String email, @RequestParam(value = "password", required = true) String password, @RequestParam(value = "userType", required = true) String userType) {
        String ret;
        model.addAttribute("login", new Register());
        if (studentRepo.existsByEmail(email) || profRepo.existsByEmail(email)) {
            session.setAttribute(SessionVariables.signedin, false);
            return "home";

        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pw = encoder.encode(password);
        if (userType.equals("student")) {
            Student s = new Student(firstName, lastName, email, pw);
            session.setAttribute(SessionVariables.user, s);
            session.setAttribute(SessionVariables.signedin, true);
            studentRepo.save(s);
            ret = "redirect:studentPage";
        } else if (userType.equals("professor")) {
            Professor p = new Professor(firstName, lastName, email, pw);
            session.setAttribute(SessionVariables.user, p);
            session.setAttribute(SessionVariables.signedin, true);
            profRepo.save(p);


              ret = "redirect:profPage";
        }

        else ret = "redirect:error";



        return ret;

    }

    @RequestMapping("/file")
    public String file() {
        return "/file";
    }

    @RequestMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "Upload error," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Upload error," + e.getMessage();
            }
            return "redirect:file";
        } else {
            return "File Empty";
        }
    }
}

