package PMS;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

/**
 * Created by obinnaelobi on 2/9/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class FrontControllerTest {

    Student test;
    Professor test2;

    @Autowired
    private Controller controller;

    // Should rename to @BeforeTestMethod
    // e.g. Creating an similar object and share for all @Test
    @Before
    public void runBeforeTestMethod() {
       test =  new Student("Joe","Jonas","joe@gmm.com", "12345");
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
        Assert.notNull(controller.getStudentRepo(), "Repository must not be null!");
    }


    @Test
    public void repoAddTest() throws Exception{
       controller.getStudentRepo().save(test);
       boolean ans = controller.getStudentRepo().existsByEmail("joe@gmm.com");
       Assert.isTrue(ans, "Should be in there");


    }

    @Test
    public void repoDeleteTest() throws Exception{
        System.out.println(controller.getStudentRepo().count());
        Student s = new Student("jo", "jon", "Jon@gm.com", "12345");
        controller.getStudentRepo().save(s);
        assertThat(controller.getStudentRepo().existsByEmail(s.getEmail())).isTrue();

        controller.getStudentRepo().delete(s);
        long ans = controller.getStudentRepo().count();
        System.out.println(ans);
        assertThat(controller.getStudentRepo().existsByEmail(s.getEmail())).isFalse();

    }

    @Test
    public void repoFindTest() throws Exception{

        User ans = controller.getStudentRepo().findByEmail("joe@gmm.com");
        Assert.isTrue(ans.toString().equals(test.toString()), "Should be the same");


    }







}