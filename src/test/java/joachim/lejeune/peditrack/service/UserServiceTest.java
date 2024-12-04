package joachim.lejeune.peditrack.service;

import joachim.lejeune.peditrack.bodyDto.UserBodyDto;
import joachim.lejeune.peditrack.bodyDto.UserFactory;
import joachim.lejeune.peditrack.exceptions.UserAlreadyExistException;
import joachim.lejeune.peditrack.model.user.User;
import joachim.lejeune.peditrack.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

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
        // Arrange
        String email = "test@example.com";
        String username = "testuser";
        when(userRepository.findUserByEmailAndName(email, username)).thenReturn(new User());

        // Act
        boolean exists = userService.findIfUserAlreadyExist(email, username);

        // Assert
        assertTrue(exists);
        verify(userRepository, times(1)).findUserByEmailAndName(email, username);

    }
    @Test
    void shouldThrowExceptionWhenCreatingUserWithExistingUsername() {
        // Arrange
        UserBodyDto userBodyDto = new UserBodyDto("testuser","123","test@example.com");

        when(userRepository.existsByUsername(userBodyDto.getUsername())).thenReturn(true);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userService.createUser(userBodyDto));

        assertEquals("Username is already taken.", exception.getMessage());
        verify(userRepository, times(1)).existsByUsername(userBodyDto.getUsername());
        verify(userRepository, never()).existsByEmail(userBodyDto.getMail());
    }
    @Test
    void shouldCreateUserWhenValid() throws UserAlreadyExistException {
        // Arrange
        UserBodyDto userBodyDto = new UserBodyDto("newuser","password","newuser@example.com");

        User user = new User();
        user.setUsername("newuser");
        user.setRole("USER");

        when(userRepository.existsByUsername(userBodyDto.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(userBodyDto.getMail())).thenReturn(false);
        when(userFactory.convert(userBodyDto, passwordEncoder)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        // Act
        User createdUser = userService.createUser(userBodyDto);

        // Assert
        assertNotNull(createdUser);
        assertEquals("newuser", createdUser.getUsername());
        verify(userRepository, times(1)).existsByUsername(userBodyDto.getUsername());
        verify(userRepository, times(1)).existsByEmail(userBodyDto.getMail());
        verify(userRepository, times(1)).save(user);
    }
    @Test
    void shouldFindAllUsers() {
        // Arrange
        when(userRepository.findAll()).thenReturn(List.of(new User(), new User()));

        // Act
        List<User> users = userService.findAllUser();

        // Assert
        assertNotNull(users);
        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }
}