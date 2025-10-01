package joachim.lejeune.peditrack.controller.user;

import joachim.lejeune.peditrack.bodyDto.PatientBodyDto;
import joachim.lejeune.peditrack.bodyDto.UserBodyDto;
import joachim.lejeune.peditrack.controller.ApplicationControllerIT;
import joachim.lejeune.peditrack.dto.UserDto;
import joachim.lejeune.peditrack.model.role.Role;
import joachim.lejeune.peditrack.model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    void getUsers_shouldReturnListOfUsers() throws Exception {

        mockMvc.perform(get("/api/users/all"))
                .andExpect(status().isAccepted())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("admin")));
    }

    @Test
    void registerUser_shouldReturnOkWhenUserCreated() throws Exception {

        UserBodyDto userBodyDto = getUserBodyDto();

        // Convertir en JSON
        String userJson = objectMapper.writeValueAsString(userBodyDto);

        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(content().string("User registered successfully: machin"));
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

    private UserBodyDto getUserBodyDto() {
        UserBodyDto userBodyDto= new UserBodyDto("machin","abcdefgh", "machin@truc.be");
        userBodyDto.setRoles(List.of(Role.USER.name()));
        return userBodyDto;
    }
}
