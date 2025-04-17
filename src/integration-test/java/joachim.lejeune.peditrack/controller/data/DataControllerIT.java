package joachim.lejeune.peditrack.controller.data;

import joachim.lejeune.peditrack.controller.ApplicationControllerIT;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SqlConfig(encoding = "UTF-8")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {
        "/joachim.lejeune.peditrack/clean-all.sql",
        "/joachim.lejeune.peditrack/controller/create_base.sql",
        "/joachim.lejeune.peditrack/controller/data/update_patient_with_point.sql"
})
public class DataControllerIT extends ApplicationControllerIT {

    private final String apiBaseDataMapUrl = "/api/data/map";

    @Test
    void getAllGeolocalistionDataMapForUser_IT() throws Exception {
        mockMvc.perform(get(apiBaseDataMapUrl).contentType("application/json"))

                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
