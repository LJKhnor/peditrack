package joachim.lejeune.peditrack.service;

import joachim.lejeune.peditrack.bodyDto.UserBodyDto;
import joachim.lejeune.peditrack.bodyDto.UserFactory;
import joachim.lejeune.peditrack.controller.auth.JwtUtils;
import joachim.lejeune.peditrack.exceptions.UserAlreadyExistException;
import joachim.lejeune.peditrack.model.user.User;
import joachim.lejeune.peditrack.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserService{
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private static final Pattern KEY_PATTERN = Pattern.compile("^[A-Z0-9]{4}(-[A-Z0-9]{4}){3}$");

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
        validateKey(dto.getRegistrationKey());
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

    private boolean validateKey(String activationKey) {
        if(activationKey == null || !KEY_PATTERN.matcher(activationKey).matches()){
            throw new IllegalArgumentException("Informat key format");
        }
        return registrationKeyRepository.findByKeyValue(key)
                .filter(k -> !k.isUsed() && k.isActive())
                .orElseThrow(() -> new IllegalArgumentException("Clé non autorisée ou déjà utilisée"));
    }

    public List<User> findAllUser() {
        return  userRepository.findAll();
    }
}
