package PMS;

import static org.assertj.core.api.Assertions.assertThat;

        import org.junit.*;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.test.context.junit4.SpringRunner;
        import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by obinnaelobi on 3/30/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CoordinatorTest {
    Coordinator cod;
    List<Student> testList;

    @Before
    public void runBeforeTestMethod() {
        cod =  new Coordinator("Rick","Sanchez","coordinatorricksanchez@gmail.com", "dcba4321");
        testList = new ArrayList<Student>();
        testList.add(new Student("Joe","Jonas","obi_el@yahoo.com", "12345"));
        testList.add(new Student("Jod","Jonas","yash.astro@gmail.com", "123455"));

    }

    @Test
    public void notifyStudents() throws Exception{
        cod.contactUnassignedStudents(testList, cod.getPassword());
    }


}
