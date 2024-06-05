package joachim.lejeune.peditrack.controller.user;

import jakarta.validation.Valid;
import joachim.lejeune.peditrack.bodyDto.UserBodyDto;
import joachim.lejeune.peditrack.dto.UserDto;
import joachim.lejeune.peditrack.dto.factory.UserDtoFactory;
import joachim.lejeune.peditrack.model.user.User;
import joachim.lejeune.peditrack.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://127.0.0.1:5000", maxAge = 3600)
@RestController
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final UserDtoFactory userDtoFactory;

    public UserController(UserService userService, UserDtoFactory userDtoFactory) {
        this.userService = userService;
        this.userDtoFactory = userDtoFactory;
    }

    @GetMapping("/users/exist")
    public boolean userAlreadyExist(@RequestParam(value = "email") String email, @RequestParam(value = "username") String username) {
        LOG.trace("Enter method userAlreadyExist");

        return userService.findIfUserAlreadyExist(email, username);
    }
    @PostMapping("/user")
    public ResponseEntity<UserDto> createUser(@Valid UserBodyDto userBodyDto){
        LOG.info("Enter method createUser");
        User user = userService.createUser(userBodyDto);
        return new ResponseEntity<>(userDtoFactory.convert(user), HttpStatus.CREATED);
    }
}
