package joachim.lejeune.peditrack;

import com.atlis.location.model.impl.Address;
import com.atlis.location.model.impl.MapPoint;
import com.atlis.location.nominatim.NominatimAPI;
import org.aspectj.weaver.Position;

import java.awt.geom.Point2D;

public class Utility {

    public static Point2D AdresseConverter(String street, String locality, String postalCode) {
        Point2D point = new Point2D.Float();
        String endpointUrl = "https://nominatim.openstreetmap.org/";
        float x = 0.0F;
        float y = 0.0F;
        // geocoding for x, y
        Address address = new Address();
        address.setStreet(street);
        address.setPostcode(postalCode);
        address.setCity(locality);
        MapPoint mapPoint = NominatimAPI.with(endpointUrl).getMapPointFromAddress(address,5);

        point.setLocation(x,y);
        return point;
    }
}
