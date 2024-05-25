package joachim.lejeune.peditrack.service;

import joachim.lejeune.peditrack.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean findIfUserAlreadyExist(String email, String username) {
        return userRepository.findUserByEmailAndName(email, username) != null;
    }
}
