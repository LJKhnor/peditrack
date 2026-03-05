package joachim.lejeune.peditrack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

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
        assertNotNull(point2D.getX());
        assertNotNull(point2D.getY());

        assertNotEquals(point2D.getX(), 0.0);
        assertNotEquals(point2D.getY(), 0.0);
    }
}