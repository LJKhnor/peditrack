package joachim.lejeune.peditrack.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import joachim.lejeune.peditrack.PeditrackApplicationIT;
import joachim.lejeune.peditrack.controller.auth.UserDetailsImpl;
import joachim.lejeune.peditrack.model.user.User;
import joachim.lejeune.peditrack.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Collection;

@AutoConfigureMockMvc
public class ApplicationControllerIT extends PeditrackApplicationIT {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;
    protected MockMvc mockMvc;
    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        // Créez un utilisateur simulé
        User user = userRepository.getReferenceById(1L);
        Collection<? extends GrantedAuthority> authorities = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_USER"));
        UserDetailsImpl mockUserDetails = new UserDetailsImpl(
                1L,
                "admin",
                "lejeunejoachim@hotmail.com",
                "1234",
                authorities,
                user);

        // Créez une Authentication simulée avec cet utilisateur
        Authentication mockAuthentication = new UsernamePasswordAuthenticationToken(mockUserDetails, null, mockUserDetails.getAuthorities());

        // Mettez l'Authentication simulée dans le SecurityContext
        SecurityContextHolder.getContext().setAuthentication(mockAuthentication);

    }

    @AfterEach
    void tearDown() {
        // Nettoyage du SecurityContext après chaque test
        SecurityContextHolder.clearContext();
    }
}
