package joachim.lejeune.peditrack.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordConstraintValidatorTest {

    private PasswordConstraintValidator validator;

    @BeforeEach
    void setUp() {
        validator = new PasswordConstraintValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Password1!",
            "Abcdefg1@",
            "MyP@ssw0rd",
            "Secure#9pass"
    })
    void shouldAcceptValidPassword(String password) {
        assertTrue(validator.isValid(password, null));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "password",        // pas de majuscule, chiffre, spécial
            "PASSWORD1!",      // pas de minuscule
            "Password!",       // pas de chiffre
            "Password1",       // pas de spécial
            "Pa1!",            // trop court
            ""                 // vide
    })
    void shouldRejectInvalidPassword(String password) {
        assertFalse(validator.isValid(password, null));
    }

    @org.junit.jupiter.api.Test
    void shouldRejectNullPassword() {
        assertFalse(validator.isValid(null, null));
    }
}