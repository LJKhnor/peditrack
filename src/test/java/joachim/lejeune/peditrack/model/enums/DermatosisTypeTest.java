package joachim.lejeune.peditrack.model.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DermatosisTypeTest {
    @Test
    void testFromId_withValidId() {
        assertEquals(DermatosisType.ECZEMA, DermatosisType.fromId(1));
        assertEquals(DermatosisType.PSORIASIS, DermatosisType.fromId(2));
        assertEquals(DermatosisType.VERRUES, DermatosisType.fromId(3));
    }

    @Test
    void testFromId_withInvalidId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> DermatosisType.fromId(99));
        assertEquals("Dermatose type not found for ID: 99", exception.getMessage());
    }

    @Test
    void testFromId_withBoundaryId() {
        assertEquals(DermatosisType.NONE, DermatosisType.fromId(0));
    }

    @Test
    void testValueForLabel_withValidLabel() {
        assertEquals(DermatosisType.ECZEMA, DermatosisType.valueForLabel("Eczéma"));
        assertEquals(DermatosisType.PSORIASIS, DermatosisType.valueForLabel("Psoriasis"));
        assertEquals(DermatosisType.VERRUES, DermatosisType.valueForLabel("Verrues"));
    }

    @Test
    void testValueForLabel_withCaseInsensitiveLabel() {
        assertEquals(DermatosisType.ECZEMA, DermatosisType.valueForLabel("eczéma"));
        assertEquals(DermatosisType.PSORIASIS, DermatosisType.valueForLabel("psoRiasis"));
    }

    @Test
    void testValueForLabel_withInvalidLabel() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> DermatosisType.valueForLabel("Invalid Label"));
        assertEquals("Dermatosis type not found for label: Invalid Label", exception.getMessage());
    }

    @Test
    void testValueForLabel_NullLabel() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> DermatosisType.valueForLabel(null));
        assertEquals("Dermatosis type not found for label: null", exception.getMessage());
    }

    @Test
    void testValueForLabel_withEmptyLabel() {
        assertEquals(DermatosisType.NONE, DermatosisType.valueForLabel(""));
    }

    @Test
    void testGetters() {
        DermatosisType type = DermatosisType.PSORIASIS;
        assertEquals(2, type.getId());
        assertEquals("Psoriasis", type.getLabel());
        assertEquals("Affection chronique de la peau causant des plaques rouges.", type.getDescription());
    }
}