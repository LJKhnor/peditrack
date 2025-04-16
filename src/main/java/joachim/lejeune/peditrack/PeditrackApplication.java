package joachim.lejeune.peditrack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PeditrackApplication {

    private static final Logger LOG = LoggerFactory.getLogger(PeditrackApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PeditrackApplication.class, args);
    }

}
