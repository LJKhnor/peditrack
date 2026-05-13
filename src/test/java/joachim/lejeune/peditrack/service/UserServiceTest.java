package joachim.lejeune.peditrack.service;

import joachim.lejeune.peditrack.bodyDto.UserBodyDto;
import joachim.lejeune.peditrack.bodyDto.UserFactory;
import joachim.lejeune.peditrack.exceptions.UserAlreadyExistException;
import joachim.lejeune.peditrack.model.user.RegistrationKey;
import joachim.lejeune.peditrack.model.user.User;
import joachim.lejeune.peditrack.repository.RegistrationKeyRepository;
import joachim.lejeune.peditrack.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RegistrationKeyRepository registrationKeyRepository;

    @Mock
    private UserFactory userFactory;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldFindIfUserAlreadyExists() {
        String email = "test@example.com";
        String username = "testuser";
        when(userRepository.findUserByEmailAndName(email, username)).thenReturn(new User());

        boolean exists = userService.findIfUserAlreadyExist(email, username);

        assertTrue(exists);
        verify(userRepository, times(1)).findUserByEmailAndName(email, username);
    }

    @Test
    void shouldThrowExceptionWhenCreatingUserWithExistingUsername() {
        UserBodyDto userBodyDto = new UserBodyDto("testuser", "123", "test@example.com", "AB12-CD34-EF56-GH78");

        RegistrationKey mockKey = new RegistrationKey();
        mockKey.setUsed(false);
        mockKey.setActive(true);
        when(registrationKeyRepository.findByKey("AB12-CD34-EF56-GH78")).thenReturn(Optional.of(mockKey));
        when(userRepository.existsByUsername(userBodyDto.getUsername())).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> userService.createUser(userBodyDto));

        assertEquals("Username is already taken.", exception.getMessage());
        verify(registrationKeyRepository, times(1)).findByKey("AB12-CD34-EF56-GH78");
        verify(userRepository, times(1)).existsByUsername(userBodyDto.getUsername());
        verify(userRepository, never()).existsByEmail(userBodyDto.getMail());
    }

    @Test
    void shouldCreateUserWhenValid() throws UserAlreadyExistException {
        UserBodyDto userBodyDto = new UserBodyDto("newuser", "password", "newuser@example.com", "AB12-CD34-EF56-GH78");

        User user = new User();
        user.setUsername("newuser");
        user.setRole("USER");

        RegistrationKey mockKey = new RegistrationKey();
        mockKey.setUsed(false);
        mockKey.setActive(true);
        when(registrationKeyRepository.findByKey("AB12-CD34-EF56-GH78")).thenReturn(Optional.of(mockKey));
        when(userRepository.existsByUsername(userBodyDto.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(userBodyDto.getMail())).thenReturn(false);
        when(userFactory.convert(userBodyDto, passwordEncoder)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(registrationKeyRepository.save(any())).thenReturn(mockKey);

        User createdUser = userService.createUser(userBodyDto);

        assertNotNull(createdUser);
        assertEquals("newuser", createdUser.getUsername());
        verify(registrationKeyRepository, times(1)).findByKey("AB12-CD34-EF56-GH78");
        verify(userRepository, times(1)).existsByUsername(userBodyDto.getUsername());
        verify(userRepository, times(1)).existsByEmail(userBodyDto.getMail());
        verify(userRepository, times(1)).save(user);
        verify(registrationKeyRepository, times(1)).save(any());
    }

    @Test
    void shouldFindAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(new User(), new User()));

        List<User> users = userService.findAllUser();

        assertNotNull(users);
        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }
}
