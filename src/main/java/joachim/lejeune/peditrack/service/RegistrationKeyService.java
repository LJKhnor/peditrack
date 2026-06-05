package joachim.lejeune.peditrack.service;

import joachim.lejeune.peditrack.model.user.RegistrationKey;
import joachim.lejeune.peditrack.repository.RegistrationKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationKeyService {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SEGMENT_LENGTH = 4;
    private static final int SEGMENT_COUNT = 4;
    private static final SecureRandom RANDOM = new SecureRandom();

    @Autowired
    private RegistrationKeyRepository registrationKeyRepository;

    public List<String> generateKeys(int count, int validityDays) {
        List<String> generated = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            String keyValue = generateUniqueKeyValue();
            RegistrationKey key = new RegistrationKey();
            key.setKey(keyValue);
            key.setActive(true);
            key.setUsed(false);
            key.setExpiresAt(OffsetDateTime.now().plusDays(validityDays));
            registrationKeyRepository.save(key);
            generated.add(keyValue);
        }
        return generated;
    }

    private String generateUniqueKeyValue() {
        String keyValue;
        do {
            keyValue = generateKeyValue();
        } while (registrationKeyRepository.findByKey(keyValue).isPresent());
        return keyValue;
    }

    private String generateKeyValue() {
        StringBuilder sb = new StringBuilder();
        for (int s = 0; s < SEGMENT_COUNT; s++) {
            if (s > 0) sb.append('-');
            for (int c = 0; c < SEGMENT_LENGTH; c++) {
                sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
            }
        }
        return sb.toString();
    }
}
