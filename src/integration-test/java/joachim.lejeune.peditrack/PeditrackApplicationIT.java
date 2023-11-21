package joachim.lejeune.peditrack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

@ActiveProfiles("integration-test")
@SpringBootTest(classes = PeditrackApplication.class)
@Testcontainers
public abstract class PeditrackApplicationIT{
    private static final Logger LOG = LoggerFactory.getLogger(PeditrackApplicationIT.class);

    private static final String POSTGRES_IMAGE_NAME = "";
}