package PMS;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by obinnaelobi on 2/9/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class mockMVCApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }


    @Test
    public void shouldReturnHome() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("home")));
        this.mockMvc.perform(get("/home")).andDo(print()).andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("home")));

    }

//
    @Test
    @WithMockUser(roles = "USER")
    public void shouldReturnStudentPage() throws Exception {

        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("home")));

        this.mockMvc.perform(post("/register")
                .with(csrf())
                .param("firstName","joe" )
                .param("lastName","joe")
                .param("email", "a@a.com")
                .param("password", "0000")
                .param("userType", "student")).andDo(print()).andExpect(status().is3xxRedirection())
                .andExpect(content().string(containsString("")));

        this.mockMvc.perform(post("/signin")
                .with(csrf())
                .param("firstName","joe" )
                .param("lastName","joe")
                .param("email", "a@a.com")
                .param("password", "0000")
                .param("userType", "student")).andDo(print()).andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("")));

    }

    @Test
    @WithMockUser(roles = "USER")
    public void shouldReturnProfessorPage() throws Exception {

        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("home")));

        this.mockMvc.perform(post("/register")
                .with(csrf())
                .param("firstName","prof" )
                .param("lastName","prof")
                .param("email", "prof@email.com")
                .param("password", "12345")
                .param("userType", "professor")).andDo(print()).andExpect(status().is3xxRedirection())
                .andExpect(content().string(containsString("")));

        this.mockMvc.perform(post("/signin")
                .with(csrf())
                .param("firstName","prof" )
                .param("lastName","prof")
                .param("email", "prof@email.com")
                .param("password", "12345")
                .param("userType", "professor")).andDo(print()).andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("")));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void shouldReturnCoordinatorPage() throws Exception {

        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("home")));

        this.mockMvc.perform(post("/register")
                .with(csrf())
                .param("firstName","cord" )
                .param("lastName","cord")
                .param("email", "cord@email.com")
                .param("password", "12345")
                .param("userType", "coordinator")).andDo(print()).andExpect(status().is3xxRedirection())
                .andExpect(content().string(containsString("")));

        this.mockMvc.perform(post("/signin")
                .with(csrf())
                .param("firstName","cord" )
                .param("lastName","cord")
                .param("email", "cord@email.com")
                .param("password", "12345")
                .param("userType", "coordinator")).andDo(print()).andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("")));

    }

    @Test
    @WithMockUser(roles = "USER")
    public void duplicateStudentTest() throws Exception {

        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("home")));

        this.mockMvc.perform(post("/register")
                .with(csrf())
                .param("firstName","joe" )
                .param("lastName","joe")
                .param("email", "a@a.com")
                .param("password", "0000")
                .param("userType", "student")).andDo(print()).andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("")));

    }

}
