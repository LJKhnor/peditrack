package joachim.lejeune.peditrack.model.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FootTypeTest {
    @Test
    void testFromId_withValidId() {
        assertEquals(FootDeformityType.ORTEILS_EN_MARTEAU, FootDeformityType.fromId(1));
        assertEquals(FootDeformityType.HALLUX_VALGUS, FootDeformityType.fromId(2));
        assertEquals(FootDeformityType.OTHER, FootDeformityType.fromId(7));
    }

    @Test
    void testFromId_withInvalidId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> FootDeformityType.fromId(99));
        assertEquals("Foot deformity type not found for ID: 99", exception.getMessage());
    }

    @Test
    void testFromId_withBoundaryId() {
        assertEquals(FootDeformityType.NONE, FootDeformityType.fromId(0));
    }

    @Test
    void testValueForLabel_withValidLabel() {
        assertEquals(FootDeformityType.ORTEILS_EN_MARTEAU, FootDeformityType.valueForLabel("Orteils en marteau"));
        assertEquals(FootDeformityType.HALLUX_VALGUS, FootDeformityType.valueForLabel("Hallux valgus"));
        assertEquals(FootDeformityType.OTHER, FootDeformityType.valueForLabel("Autres"));
    }

    @Test
    void testValueForLabel_withCaseInsensitiveLabel() {
        assertEquals(FootDeformityType.HALLUX_VALGUS, FootDeformityType.valueForLabel("hallux valgus"));
        assertEquals(FootDeformityType.HALLUX_RIGIDUS, FootDeformityType.valueForLabel("hallux rigidus"));
    }

    @Test
    void testValueForLabel_withInvalidLabel() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> FootDeformityType.valueForLabel("Invalid Label"));
        assertEquals("Foot deformity type not found for label: Invalid Label", exception.getMessage());
    }

    @Test
    void testValueForLabel_NullLabel() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> FootDeformityType.valueForLabel(null));
        assertEquals("Foot deformity type not found for label: null", exception.getMessage());
    }

    @Test
    void testValueForLabel_withNullLabel() {
        assertEquals(FootDeformityType.NONE, FootDeformityType.valueForLabel(""));
    }

    @Test
    void testGetters() {
        FootDeformityType type = FootDeformityType.HALLUX_VALGUS;
        assertEquals(2, type.getId());
        assertEquals("Hallux valgus", type.getLabel());
        assertEquals("Déformation où le gros orteil est incliné vers les autres orteils.", type.getDescription());
    }
}