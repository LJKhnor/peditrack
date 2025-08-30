package joachim.lejeune.peditrack.controller.user;

import joachim.lejeune.peditrack.bodyDto.UserBodyDto;
import joachim.lejeune.peditrack.controller.ApplicationControllerIT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SqlConfig(encoding = "UTF-8")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {
        "/joachim.lejeune.peditrack/clean-all.sql",
        "/joachim.lejeune.peditrack/controller/create_base.sql"})
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
        this.mockMvc.perform(get("/api/users/exist")
                        .contentType("application/json")
                        .param("email", "lejeunejoachim@hotmail.com")
                        .param("username", "admin"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(true)))
        ;
    }

    @Test
    void updateUser_withAddress() throws Exception{
        UserBodyDto userBodyDto = getUserBodyDto();
        String userJson = objectMapper.writeValueAsString(userBodyDto);
        mockMvc.perform(post("/api/users/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andDo(print());
    }

    private static UserBodyDto getUserBodyDto() {
        UserBodyDto userBodyDto = new UserBodyDto();
        userBodyDto.setUsername("admin");
        userBodyDto.setPassword("1234");
        userBodyDto.setLocality("Jambes");
        userBodyDto.setPostalCode("5100");
        userBodyDto.setAddress("chaussée de marche 250");
        return userBodyDto;
    }
}
