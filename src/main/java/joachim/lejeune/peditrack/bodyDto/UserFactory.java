package joachim.lejeune.peditrack.bodyDto;

import joachim.lejeune.peditrack.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {
    private static final Logger LOG = LoggerFactory.getLogger(UserFactory.class);


    public User convert(UserBodyDto userBodyDto) {
        User user =  new User();
        user.setName(userBodyDto.getName());
        user.setEmail(userBodyDto.getEmail());
        user.setPassword(userBodyDto.getPassword());

        return user;
    }
}
