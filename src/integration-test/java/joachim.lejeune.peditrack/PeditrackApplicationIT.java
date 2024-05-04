package joachim.lejeune.peditrack;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.dockerjava.api.DockerClient;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

@Testcontainers
@ActiveProfiles("integration-test")
@SpringBootTest(classes = PeditrackApplication.class)
public abstract class PeditrackApplicationIT {
    private static final Logger LOG = LoggerFactory.getLogger(PeditrackApplicationIT.class);
    private static final String POSTGRES_IMAGE_NAME = "postgres:14.5";
    private static final Network network = createReusableNetwork("peditrack-IT");
    private static final PostgreSQLContainer<?> postgresContainer;

    static {
        postgresContainer = new PostgreSQLContainer<>(DockerImageName.parse(POSTGRES_IMAGE_NAME)
                .asCompatibleSubstituteFor("postgres"))
                .withNetwork(network)
                .withNetworkAliases("database")
                .withDatabaseName("peditrack_db_it")
                .withReuse(true);

        postgresContainer.start();
    }


    @DynamicPropertySource
    static void registerDynProperties(DynamicPropertyRegistry registry) {
        LOG.info("containers started, postgres is available on {} with username {} and password {}",
                postgresContainer.getJdbcUrl(),
                postgresContainer.getUsername(),
                postgresContainer.getPassword());

        registry.add("spring.datasource.hostname", postgresContainer::getHost);
        registry.add("spring.datasource.port", () -> postgresContainer.getMappedPort(5432));
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
    }

    protected ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new Jdk8Module());
        return mapper;
    }


    public static Network createReusableNetwork(String name) {
        final DockerClient dockerClient = DockerClientFactory.instance().client();
        final List<com.github.dockerjava.api.model.Network> networks = dockerClient.listNetworksCmd().exec();
        String id = networks.stream()
                .filter(network -> network.getName().equals(name)
                        && network.getLabels().equals(DockerClientFactory.DEFAULT_LABELS))
                .map(com.github.dockerjava.api.model.Network::getId)
                .findFirst()
                .orElseGet(() -> dockerClient.createNetworkCmd()
                        .withName(name)
                        .withCheckDuplicate(true)
                        .withLabels(DockerClientFactory.DEFAULT_LABELS)
                        .exec().getId());
        return new Network() {
            @Override
            public String getId() {
                return id;
            }

            @Override
            public void close() {
                // never close
            }

            @Override
            public Statement apply(Statement statement, Description description) {
                return statement;
            }
        };
    }
}