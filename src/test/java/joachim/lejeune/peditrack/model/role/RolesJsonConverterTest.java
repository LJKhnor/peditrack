package joachim.lejeune.peditrack.model.role;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RolesJsonConverterTest {

    private RolesJsonConverter converter;

    @BeforeEach
    void setUp() {
        converter = new RolesJsonConverter();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void convertToDatabaseColumn() {
        List<String> roles = Arrays.asList("ADMIN", "USER");

        String json = converter.convertToDatabaseColumn(roles);

        assertNotNull(json);
        assertTrue(json.contains("ADMIN"));
        assertTrue(json.contains("USER"));
    }

    @Test
    void convertToEntityAttribute() {
        String json = "[\"ADMIN\",\"USER\"]";

        List<String> roles = converter.convertToEntityAttribute(json);

        assertNotNull(roles);
        assertEquals(2, roles.size());
        assertTrue(roles.contains("ADMIN"));
        assertTrue(roles.contains("USER"));
    }
}
