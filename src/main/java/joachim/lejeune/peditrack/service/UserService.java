package joachim.lejeune.peditrack.service;

import joachim.lejeune.peditrack.bodyDto.UserBodyDto;
import joachim.lejeune.peditrack.bodyDto.UserFactory;
import joachim.lejeune.peditrack.exceptions.UserAlreadyExistException;
import joachim.lejeune.peditrack.model.user.User;
import joachim.lejeune.peditrack.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    private final UserFactory userFactory;

    public UserService(UserRepository userRepository, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    public boolean findIfUserAlreadyExist(String email, String username) {
        return userRepository.findUserByEmailAndName(email, username) != null;
    }

    public User createUser(UserBodyDto userBodyDto) throws UserAlreadyExistException {
        LOG.trace("Enter method createUser");
        boolean userAlreadyExist =  findIfUserAlreadyExist(userBodyDto.getEmail(), userBodyDto.getName());
        if(userAlreadyExist){
            throw new UserAlreadyExistException("User already exist");
        }

        final User user = userFactory.convert(userBodyDto);

        return userRepository.save(user);
    }

    public List<User> findAllUser() {
        return  userRepository.findAll();
    }
}
