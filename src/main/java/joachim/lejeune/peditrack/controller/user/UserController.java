package joachim.lejeune.peditrack.controller.user;

import jakarta.validation.Valid;
import joachim.lejeune.peditrack.bodyDto.UserBodyDto;
import joachim.lejeune.peditrack.controller.auth.UserDetailsImpl;
import joachim.lejeune.peditrack.dto.UserDto;
import joachim.lejeune.peditrack.dto.factory.UserDtoFactory;
import joachim.lejeune.peditrack.exceptions.UserAlreadyExistException;
import joachim.lejeune.peditrack.model.user.User;
import joachim.lejeune.peditrack.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
                .map(user -> userDtoFactory.convert(user))
                .collect(Collectors.toList()), HttpStatus.ACCEPTED);
    }
    @GetMapping("/info")
    public ResponseEntity<UserDto> getUserInfosById(){
        LOG.info("Enter method getUserInfosById");
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(userDetails.isEnabled()){
            Long id = userDetails.getId();
            return userService.findUserById(id).map(user -> {
                return new ResponseEntity<>(userDtoFactory.convert(user), HttpStatus.OK);
            }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
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

    @PostMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserBodyDto userBodyDto){
        LOG.info("Enter method upDateUser");
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(userDetails.isEnabled()){
            Long id = userDetails.getId();
            User user = userService.updateUser(id, userBodyDto);
            return new ResponseEntity<>(userDtoFactory.convert(user), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserBodyDto userBodyDto) {
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
}
