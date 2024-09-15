package joachim.lejeune.peditrack.controller.user;

import joachim.lejeune.peditrack.bodyDto.UserBodyDto;
import joachim.lejeune.peditrack.controller.ApplicationControllerIT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SqlConfig(encoding = "UTF-8")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {
        "/joachim.lejeune.peditrack/clean-all.sql",
        "/joachim.lejeune.peditrack/controller/user/create_user.sql"})
public class UserControllerIT extends ApplicationControllerIT {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;


    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void userAlreadyExist() throws Exception {
        this.mockMvc.perform(get("/users/exist")
                        .contentType("application/json")
                        .param("email", "joachim@test.be")
                        .param("username", "joachim"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(false)))
        ;
    }

    @Test
    void createUser() throws Exception {
        UserBodyDto userBodyDto = new UserBodyDto("jojo", "123", "jojo@test.be");
        this.mockMvc.perform(post("/user")
                        .contentType("application/json")
                        .content(objectMapper().writeValueAsString(userBodyDto)))
                .andExpect(status().isOk());
    }
}
