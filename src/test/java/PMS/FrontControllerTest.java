package PMS;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.springframework.util.ExceptionTypeFilter;

/**
 * Created by obinnaelobi on 2/9/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class FrontControllerTest {

    Student stud1;
    Professor prof1;
    Coordinator coord1;

    @Autowired
    private Controller controller;

    // Should rename to @BeforeTestMethod
    // e.g. Creating an similar object and share for all @Test
    @Before
    public void runBeforeTestMethod() {
       stud1 =  new Student("stud1","stud1","stud1@email.com", "12345");
       prof1 =  new Professor("prof1","prof1","prof1@email.com", "12345");
       coord1 = new Coordinator("coord1","coord1","coord1@email.com","12345");
    }

    @Test
    public void contexLoads() throws Exception{
        assertThat(controller).isNotNull();
    }

    @Test
    public void studentRepoTest() throws Exception{
        Assert.notNull(controller.getStudentRepo(), "Repository must not be null!");
    }

    @Test
    public void profRepoTest() throws Exception{
        Assert.notNull(controller.getProfRepo(), "Repository must not be null!");
    }

    @Test
    public void coordRepoTest() throws Exception{
        Assert.notNull(controller.getCoordinatorRepo(), "Repository must not be null!");
    }

    @Test
    public void repoAddTest() throws Exception{
        //Student
        controller.getStudentRepo().save(stud1);
        boolean ans = controller.getStudentRepo().existsByEmail("stud1@email.com");
        Assert.isTrue(ans, "Should be in there");

        //Professor
        controller.getProfRepo().save(prof1);
        ans = controller.getProfRepo().existsByEmail("prof1@email.com");
        Assert.isTrue(ans, "Should be in there");

        //Coordinator
        controller.getCoordinatorRepo().save(coord1);
        ans = controller.getCoordinatorRepo().existsByEmail("coord1@email.com");
        Assert.isTrue(ans, "Should be in there");


    }

    @Test
    public void repoDeleteTest() throws Exception{
        //Student
        long startcount = controller.getStudentRepo().count();
        Student s = new Student("stud2", "stud2", "stud2@email.com", "12345");
        controller.getStudentRepo().save(s);
        assertThat(controller.getStudentRepo().existsByEmail(s.getEmail())).isTrue();
        long ans = controller.getStudentRepo().count();
        assertThat(ans).isEqualTo(startcount+1);
        controller.getStudentRepo().delete(s);
        ans = controller.getStudentRepo().count();
        assertThat(ans).isEqualTo(startcount);
        assertThat(controller.getStudentRepo().existsByEmail(s.getEmail())).isFalse();
        assertThat(controller.getStudentRepo().existsByEmail(stud1.getEmail())).isTrue();

        //Professor
        startcount = controller.getProfRepo().count();
        Professor p = new Professor("prof2","prof2","prof2@email.com", "12345");
        controller.getProfRepo().save(p);
        assertThat(controller.getProfRepo().existsByEmail(p.getEmail())).isTrue();
        ans = controller.getProfRepo().count();
        assertThat(ans).isEqualTo(startcount+1);
        controller.getProfRepo().delete(p);
        ans = controller.getProfRepo().count();
        assertThat(ans).isEqualTo(startcount);
        assertThat(controller.getProfRepo().existsByEmail(p.getEmail())).isFalse();
        assertThat(controller.getProfRepo().existsByEmail(prof1.getEmail())).isTrue();

        //Coordinator
        startcount = controller.getCoordinatorRepo().count();
        Coordinator c = new Coordinator("coord2","coord2","coord2@email.com","12345");
        controller.getCoordinatorRepo().save(c);
        assertThat(controller.getCoordinatorRepo().existsByEmail(c.getEmail())).isTrue();
        ans = controller.getCoordinatorRepo().count();
        assertThat(ans).isEqualTo(startcount+1);
        controller.getCoordinatorRepo().delete(c);
        ans = controller.getCoordinatorRepo().count();
        assertThat(ans).isEqualTo(startcount);
        assertThat(controller.getCoordinatorRepo().existsByEmail(c.getEmail())).isFalse();
        assertThat(controller.getCoordinatorRepo().existsByEmail(coord1.getEmail())).isTrue();

    }

    @Test
    public void repoFindTest() throws Exception{

        User ans = controller.getStudentRepo().findByEmail("stud1@email.com");
        Assert.isTrue(ans.toString().equals(stud1.toString()), "Should be the same");

    }

}