package joachim.lejeune.peditrack.controller.patient;

import joachim.lejeune.peditrack.controller.ApplicationControllerIT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SqlConfig(encoding = "UTF-8")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {
        "/joachim.lejeune.peditrack/clean-all.sql",
        "/joachim.lejeune.peditrack/controller/patient/create_patient.sql"})
class PatientControllerIT extends ApplicationControllerIT {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;


    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void getPatient() throws Exception {
        this.mockMvc.perform(get("/patients").contentType("application/json"))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("lejeune")))
                .andExpect(jsonPath("$[0].firstName", is("joachim")))
                .andExpect(jsonPath("$[0].personOfContact.id", is(2)))
                .andExpect(jsonPath("$[0].personOfContact.name", is("lizen")))
                .andExpect(jsonPath("$[0].personOfContact.firstName", is("valériane")))
                .andExpect(jsonPath("$[0].doctorId.id", is(3)))
                .andExpect(jsonPath("$[0].doctorId.name", is("smeets")))
                .andExpect(jsonPath("$[0].doctorId.firstName", is("morgane")))
        ;
    }

    @Test
    void getPatient_byId() throws Exception {
        this.mockMvc.perform(get("/patients/1"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("lejeune")))
                .andExpect(jsonPath("$.firstName", is("joachim")))
                .andExpect(jsonPath("$.personOfContact.id", is(2)))
                .andExpect(jsonPath("$.personOfContact.name", is("lizen")))
                .andExpect(jsonPath("$.personOfContact.firstName", is("valériane")))
                .andExpect(jsonPath("$.doctorId.id", is(3)))
                .andExpect(jsonPath("$.doctorId.name", is("smeets")))
                .andExpect(jsonPath("$.doctorId.firstName", is("morgane")))
        ;
    }
}