package joachim.lejeune.peditrack.controller.data;

import joachim.lejeune.peditrack.controller.ApplicationControllerIT;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

@SqlConfig(encoding = "UTF-8")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {
        "/joachim.lejeune.peditrack/clean-all.sql",
        "/joachim.lejeune.peditrack/controller/create_base.sql"})
public class DataControllerIT extends ApplicationControllerIT {
}
