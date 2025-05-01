package joachim.lejeune.peditrack.service;

import joachim.lejeune.peditrack.bodyDto.UserBodyDto;
import joachim.lejeune.peditrack.bodyDto.UserFactory;
import joachim.lejeune.peditrack.exceptions.UserAlreadyExistException;
import joachim.lejeune.peditrack.model.user.User;
import joachim.lejeune.peditrack.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    private final UserFactory userFactory;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserFactory userFactory, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean findIfUserAlreadyExist(String email, String username) {
        return userRepository.findUserByEmailAndName(email, username) != null;
    }

    public User createUser(UserBodyDto userBodyDto) throws UserAlreadyExistException {
        LOG.trace("Enter method createUser");
        if (userRepository.existsByUsername(userBodyDto.getUsername())) {
            throw new IllegalArgumentException("Username is already taken.");
        }
        if (userRepository.existsByEmail(userBodyDto.getMail())) {
            throw new IllegalArgumentException("Email is already in use.");
        }

        final User user = userFactory.convert(userBodyDto, passwordEncoder);
        LOG.debug("Roles being saved: {}", user.getRole());

        return userRepository.save(user);
    }

    public List<User> findAllUser() {
        return  userRepository.findAll();
    }

    public User updateUser(UserBodyDto userBodyDto) {
        final User user = userFactory.convert(userBodyDto, passwordEncoder);
        return userRepository.save(user);
    }
}
