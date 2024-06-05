package joachim.lejeune.peditrack.dto.factory;

import joachim.lejeune.peditrack.dto.UserDto;
import joachim.lejeune.peditrack.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoFactory {

    public UserDtoFactory() {
    }

    public UserDto convert(User user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());

        return dto;
    }
}
