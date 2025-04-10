package joachim.lejeune.peditrack;

import com.atlis.location.model.impl.Address;
import com.atlis.location.model.impl.MapPoint;
import com.atlis.location.nominatim.NominatimAPI;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UtilityTest {
    @Mock
    private NominatimAPI nominatimAPI;
    @Test
    void AdresseConverter_test(){
        // Arrange
        String adresse = "chaussée de marche 250";
        String locality = "jambes";
        String postalCode = "5100";

        // Fake coordonnées pour test
        double expectedX = 50.465;
        double expectedY = 4.870;

        // Mock de MapPoint
        MapPoint mockMapPoint = mock(MapPoint.class);
        when(mockMapPoint.getLatitude()).thenReturn(expectedX);
        when(mockMapPoint.getLongitude()).thenReturn(expectedY);

        // Mock de NominatimAPI
//        when(nominatimAPI.getMapPointFromAddress(any(Address.class), eq(5)))
//                .thenReturn(mockMapPoint);

        // Act
        Point2D result = Utility.AdresseConverter(adresse, locality,postalCode);

        // Assert
        assertEquals(expectedX, result.getX());
        assertEquals(expectedY, result.getY());
    }

}