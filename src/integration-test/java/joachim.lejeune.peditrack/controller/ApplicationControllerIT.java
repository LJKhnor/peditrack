package joachim.lejeune.peditrack.controller;

import joachim.lejeune.peditrack.PeditrackApplicationIT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
public class ApplicationControllerIT extends PeditrackApplicationIT {
    @Autowired
    private MockMvc mockMvc;

}
