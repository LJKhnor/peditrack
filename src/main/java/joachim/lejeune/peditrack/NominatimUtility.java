package joachim.lejeune.peditrack;


import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Component
public class NominatimUtility {


    public static Point2D AddressConverter(String street) throws IOException {

        String endpointUrl = "https://nominatim.openstreetmap.org/search?q=" + URLEncoder.encode(street, "UTF-8") + "&format=json&addressdetails=1";
        Point2D point = new Point2D.Float();

        JSONArray results = getResultAddressList(endpointUrl);
        double lat = 0.0;
        double lon = 0.0;
        if (results.length() > 0){
            JSONObject firstResult = results.getJSONObject(0);

            lat = firstResult.getDouble("lat");
            lon = firstResult.getDouble("lon");
        }
        point.setLocation(lat, lon);
        return point;
    }

    private static @NotNull JSONArray getResultAddressList(String endpointUrl) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(endpointUrl).openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "VotreApplication/1.0 (votre.email@example.com)");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder responseStr = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            responseStr.append(inputLine);
        }
        in.close();

        JSONArray results = new JSONArray(responseStr.toString());
        return results;
    }
}
