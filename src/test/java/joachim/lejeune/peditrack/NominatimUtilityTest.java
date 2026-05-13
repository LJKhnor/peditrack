package joachim.lejeune.peditrack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class NominatimUtilityTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void addressConverter() throws IOException {
        Point2D point2D = NominatimUtility.AddressConverter("chaussée de marche 250 5100 jambes");

        assertNotEquals(0.0, point2D.getX());
        assertNotEquals(0.0, point2D.getY());
    }
}
