package joachim.lejeune.peditrack.controller.user;

import joachim.lejeune.peditrack.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/exist")
    public boolean userAlreadyExist(@RequestParam(value = "email") String email, @RequestParam(value = "username") String username) {
        LOG.trace("Enter method userAlreadyExist");

        return userService.findIfUserAlreadyExist(email, username);
    }
}
