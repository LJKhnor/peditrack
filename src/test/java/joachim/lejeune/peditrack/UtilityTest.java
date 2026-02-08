package joachim.lejeune.peditrack;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.awt.geom.Point2D;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

@AutoConfigureMockMvc
class UtilityTest {

    @Disabled
    @Test
    void AdresseConverter_test() throws IOException {
        // Arrange
        String adresse = "chaussée de marche 250";

        // Fake coordonnées pour test
        double expectedX = 50.465;
        double expectedY = 4.870;

        // Act
        Point2D result = NominatimUtility.AddressConverter(adresse);

        // Assert
        assertEquals(expectedX, result.getX(),4.0);
        assertEquals(expectedY, result.getY(),4.0);
    }

    @Disabled
    @Test
    void AddressConverter_WrongAddress() throws Exception{
        String adresse = "chausée de arche 250";

        // Fake coordonnées pour test
        double expectedX = 0.0;
        double expectedY = 0.0;

        // Act
        Point2D result = NominatimUtility.AddressConverter(adresse);

        // Assert
        assertEquals(expectedX, result.getX(),4.0);
        assertEquals(expectedY, result.getY(),4.0);
    }

}