package joachim.lejeune.peditrack.model.enums;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CirculationTypeTest {

    @Test
    void testFromId_ValidId() {
        // Test pour chaque ID valide
        assertEquals(CirculationType.NONE, CirculationType.fromId(0));
        assertEquals(CirculationType.COLD_FEET, CirculationType.fromId(1));
        assertEquals(CirculationType.VARICOSITIES, CirculationType.fromId(2));
        assertEquals(CirculationType.BLUISH_FEET, CirculationType.fromId(3));
        assertEquals(CirculationType.BLUSHES, CirculationType.fromId(4));
        assertEquals(CirculationType.EDEMA, CirculationType.fromId(5));
    }

    @Test
    void testFromId_InvalidId() {
        // Test avec un ID invalide
        Exception exception = assertThrows(IllegalArgumentException.class, () -> CirculationType.fromId(99));
        assertEquals("Circulation type not found for ID: 99", exception.getMessage());
    }

    @Test
    void testValueForLabel_ValidLabel() {
        // Test pour chaque label valide
        assertEquals(CirculationType.NONE, CirculationType.valueForLabel(""));
        assertEquals(CirculationType.COLD_FEET, CirculationType.valueForLabel("Pieds froids"));
        assertEquals(CirculationType.VARICOSITIES, CirculationType.valueForLabel("Varicosités"));
        assertEquals(CirculationType.BLUISH_FEET, CirculationType.valueForLabel("Pieds bleutés"));
        assertEquals(CirculationType.BLUSHES, CirculationType.valueForLabel("Rougeurs"));
        assertEquals(CirculationType.EDEMA, CirculationType.valueForLabel("Œdèmes"));
    }

    @Test
    void testValueForLabel_InvalidLabel() {
        // Test avec un label invalide
        Exception exception = assertThrows(IllegalArgumentException.class, () -> CirculationType.valueForLabel("Inconnu"));
        assertEquals("Circulation type not found for label: Inconnu", exception.getMessage());
    }
    @Test
    void testValueForLabel_NullLabel() {
        // Test avec un label invalide
        Exception exception = assertThrows(IllegalArgumentException.class, () -> CirculationType.valueForLabel(null));
        assertEquals("Circulation type not found for label: null", exception.getMessage());
    }

    @Test
    void testValueForLabel_EmptyLabel() {
        // Test avec un label null
        assertEquals(CirculationType.NONE, CirculationType.valueForLabel(""));
    }

    @Test
    void testGetters() {
        // Test des getters pour une valeur spécifique
        CirculationType type = CirculationType.COLD_FEET;
        assertEquals(1, type.getId());
        assertEquals("Pieds froids", type.getLabel());
        assertEquals("Réduction de la circulation, provoquant une sensation de froid.", type.getDescription());
    }
}