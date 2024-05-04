package joachim.lejeune.peditrack.controller.patient;

import joachim.lejeune.peditrack.controller.ApplicationControllerIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SqlConfig(encoding = "UTF-8")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {
        "/joachim.lejeune.peditrack/clean-all.sql",
        "/joachim.lejeune.peditrack/controller/patient/create_patient.sql" })
class PatientControllerIT extends ApplicationControllerIT {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void getPatient() throws Exception{
        this.mockMvc.perform(get("/patient"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getPatient_oneById() throws Exception {
        this.mockMvc.perform(get("/patient/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}