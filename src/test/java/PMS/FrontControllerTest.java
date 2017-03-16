package PMS;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
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

    @Autowired
    private Controller controller;

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
       controller.getStudentRepo().save(new Student("Joe","Jonas","joe@gmm.com"));
      // boolean ans = controller.getUserRepo().exists("joe@gmm.com");
       //Assert.isTrue(ans, "Should be in there");

    }




}