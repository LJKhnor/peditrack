package joachim.lejeune.peditrack.controller.user;

import jakarta.validation.Valid;
import joachim.lejeune.peditrack.bodyDto.UserBodyDto;
import joachim.lejeune.peditrack.dto.UserDto;
import joachim.lejeune.peditrack.dto.factory.UserDtoFactory;
import joachim.lejeune.peditrack.exceptions.UserAlreadyExistException;
import joachim.lejeune.peditrack.model.user.User;
import joachim.lejeune.peditrack.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://127.0.0.1:5000", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final UserDtoFactory userDtoFactory;


    public UserController(UserService userService, UserDtoFactory userDtoFactory) {
        this.userService = userService;
        this.userDtoFactory = userDtoFactory;
    }

    /**
     * Get all the user
     *
     * @return a list of user
     */
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getUsers() {
        LOG.trace("Enter getUsers method");
        List<User> users = userService.findAllUser();
        return new ResponseEntity<>(users.stream()
                .map(userDtoFactory::convert)
                .collect(Collectors.toList()), HttpStatus.ACCEPTED);
    }

    /**
     * Verify if the user already exist
     *
     * @param email    of the user
     * @param username of the user
     * @return boolean
     */
    @GetMapping("/exist")
    public boolean userAlreadyExist(@RequestParam(value = "email") String email, @RequestParam(value = "username") String username) {
        LOG.trace("Enter method userAlreadyExist");

        return userService.findIfUserAlreadyExist(email, username);
    }

    /**
     * create a new user
     *
     * @param userBodyDto that provide info for the new user
     * @return the response that contain the new user
     */
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserBodyDto userBodyDto) throws Exception {
        LOG.info("Enter method createUser");
        User user = userService.createUser(userBodyDto);
        return new ResponseEntity<>(userDtoFactory.convert(user), HttpStatus.CREATED);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserBodyDto userBodyDto) {
        LOG.info("Enter method registerUser");
        try {
            User user = userService.createUser(userBodyDto);
            return ResponseEntity.ok("User registered successfully: " + user.getUsername());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (UserAlreadyExistException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/{userId}/info")
    public ResponseEntity<UserDto> getPatient(@PathVariable(value = "userId") Long userId){
        LOG.info("Enter method getPatient");
        Optional <User> optionalUser = userService.findUserById(userId);

        return optionalUser.map(user -> new ResponseEntity<>(userDtoFactory.convert(user), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
    @PostMapping("/{userId}/update")
    public ResponseEntity<?> updateUser(@PathVariable(value="userId") Long userId, @RequestBody UserBodyDto userBodyDto){
        LOG.info("Enter method updateUser");

        Optional<User> optionalUser = userService.findUserById(userId);
        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User user = optionalUser.get();
        user.setUsername(userBodyDto.getUsername());
        user.setStreet(userBodyDto.getStreet());
        user.setPostalCode(userBodyDto.getPostalCode());
        user.setLocality(userBodyDto.getLocality());

        User userUpdated = userService.saveUser(user);
        return new ResponseEntity<>(userDtoFactory.convert(userUpdated), HttpStatus.ACCEPTED);
    }
}
