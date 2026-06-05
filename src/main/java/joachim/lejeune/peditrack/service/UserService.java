package joachim.lejeune.peditrack.service;

import joachim.lejeune.peditrack.bodyDto.UserBodyDto;
import joachim.lejeune.peditrack.bodyDto.UserFactory;
import joachim.lejeune.peditrack.exceptions.UserAlreadyExistException;
import joachim.lejeune.peditrack.model.user.RegistrationKey;
import joachim.lejeune.peditrack.model.user.User;
import joachim.lejeune.peditrack.repository.RegistrationKeyRepository;
import joachim.lejeune.peditrack.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService{
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final RegistrationKeyRepository registrationKeyRepository;
    private static final Pattern KEY_PATTERN = Pattern.compile("^[A-Z0-9]{4}(-[A-Z0-9]{4}){3}$");

    private final UserFactory userFactory;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RegistrationKeyRepository registrationKeyRepository, UserFactory userFactory, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.registrationKeyRepository = registrationKeyRepository;
        this.userFactory = userFactory;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean findIfUserAlreadyExist(String email, String username) {
        return userRepository.findUserByEmailAndName(email, username) != null;
    }

    public User createUser(UserBodyDto userBodyDto) throws UserAlreadyExistException {
        LOG.trace("Enter method createUser");
        Optional<RegistrationKey> registrationKey = validateKey(userBodyDto.getRegistrationKey());
        if (userRepository.existsByUsername(userBodyDto.getUsername())) {
            throw new IllegalArgumentException("Username is already taken.");
        }
        if (userRepository.existsByEmail(userBodyDto.getMail())) {
            throw new IllegalArgumentException("Email is already in use.");
        }

        final User user = userFactory.convert(userBodyDto, passwordEncoder);
        registrationKey.ifPresent(key -> user.setActivationCode(key.getKey()));

        LOG.debug("Roles being saved: {}", user.getRole());

        final User userSaved = userRepository.save(user);

        // Met à jour la clé
        registrationKey.ifPresent(key -> {
            key.setUsed(true);
            registrationKeyRepository.save(key);
        });


        return userSaved;
    }

    private Optional<RegistrationKey> validateKey(String activationKey) {
        if(activationKey == null || !KEY_PATTERN.matcher(activationKey).matches()){
            throw new IllegalArgumentException("Registration key format");
        }
        Optional<RegistrationKey> registrationKeyOptional = registrationKeyRepository.findByKey(activationKey)
                .filter(k -> !k.isUsed() && k.isActive() && !k.isExpired());
        registrationKeyOptional
                .orElseThrow(() -> new IllegalArgumentException("Clé non autorisée, déjà utilisée ou expirée"));
        return registrationKeyOptional;
    }

    public List<User> findAllUser() {
        return  userRepository.findAll();
    }

    public Optional<User> findUserById(Long userId){
        return userRepository.findById(userId);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User setUserActive(Long userId, boolean active) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        user.setActive(active);
        return userRepository.save(user);
    }
}
