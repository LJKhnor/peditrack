package joachim.lejeune.peditrack.service;

import joachim.lejeune.peditrack.model.user.RegistrationKey;
import joachim.lejeune.peditrack.repository.RegistrationKeyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class RegistrationKeyServiceTest {

    @Mock
    private RegistrationKeyRepository registrationKeyRepository;

    @InjectMocks
    private RegistrationKeyService registrationKeyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(registrationKeyRepository.findByKey(anyString())).thenReturn(Optional.empty());
        when(registrationKeyRepository.save(any())).thenAnswer(i -> i.getArgument(0));
    }

    @Test
    void shouldGenerateRequestedNumberOfKeys() {
        List<String> keys = registrationKeyService.generateKeys(5, 30);
        assertEquals(5, keys.size());
        verify(registrationKeyRepository, times(5)).save(any(RegistrationKey.class));
    }

    @Test
    void shouldGenerateKeysWithCorrectFormat() {
        List<String> keys = registrationKeyService.generateKeys(3, 30);
        keys.forEach(key -> assertTrue(key.matches("^[A-Z0-9]{4}(-[A-Z0-9]{4}){3}$"),
                "Key format invalid: " + key));
    }

    @Test
    void shouldGenerateUniqueKeys() {
        List<String> keys = registrationKeyService.generateKeys(10, 30);
        assertEquals(10, keys.stream().distinct().count());
    }

    @Test
    void shouldRetryOnDuplicate() {
        String duplicate = "AAAA-BBBB-CCCC-DDDD";
        when(registrationKeyRepository.findByKey(duplicate))
                .thenReturn(Optional.of(new RegistrationKey()))
                .thenReturn(Optional.empty());

        List<String> keys = registrationKeyService.generateKeys(1, 30);
        assertEquals(1, keys.size());
        assertNotEquals(duplicate, keys.get(0));
    }
}
