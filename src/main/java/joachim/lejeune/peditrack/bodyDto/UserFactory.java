package joachim.lejeune.peditrack.bodyDto;

import joachim.lejeune.peditrack.model.role.Role;
import joachim.lejeune.peditrack.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserFactory {
    private static final Logger LOG = LoggerFactory.getLogger(UserFactory.class);

    public User convert(UserBodyDto userBodyDto, PasswordEncoder passwordEncoder) {
        User user =  new User();
        user.setUsername(userBodyDto.getUsername());
        user.setEmail(userBodyDto.getMail());
        user.setPassword(passwordEncoder.encode(userBodyDto.getPassword()));
        user.setRole(Role.USER.name());

        return user;
    }
}
