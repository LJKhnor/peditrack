package joachim.lejeune.peditrack.controller.patient;

import joachim.lejeune.peditrack.controller.ApplicationControllerIT;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

@SqlConfig(encoding = "UTF-8")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"/joachim/lejeune/peditrack/clean-all.sql"})
class PatientControllerIT extends ApplicationControllerIT {

}