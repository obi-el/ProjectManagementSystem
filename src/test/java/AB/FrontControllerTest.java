package AB;

import static org.assertj.core.api.Assertions.assertThat;

import PMS.Controller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

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



}