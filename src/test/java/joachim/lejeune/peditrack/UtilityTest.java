package joachim.lejeune.peditrack;

import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UtilityTest {

    @Test
    void AdresseConverter_test() throws IOException {
        String adresse = "chaussée de marche 250 5100 jambes";

        Point2D result = NominatimUtility.AddressConverter(adresse);

        // Coordinates for Jambes, Belgium (lat ~50.46, lon ~4.88)
        assertEquals(50.465, result.getX(), 4.0);
        assertEquals(4.870, result.getY(), 4.0);
    }

    @Test
    void AddressConverter_WrongAddress() throws Exception {
        String adresse = "XXXXXXXXXXX 999999 YYYYYYYYYYY";

        Point2D result = NominatimUtility.AddressConverter(adresse);

        assertEquals(0.0, result.getX(), 0.0001);
        assertEquals(0.0, result.getY(), 0.0001);
    }
}
