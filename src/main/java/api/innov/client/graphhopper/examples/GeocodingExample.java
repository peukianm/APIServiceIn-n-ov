package api.innov.client.graphhopper.examples;

import api.innov.client.graphhopper.api.GeocodingApi;
import api.innov.client.graphhopper.model.GeocodingLocation;
import api.innov.client.graphhopper.model.GeocodingResponse;
import api.innov.client.graphhopper.util.GHApiUtil;
/**
 * @author Michalis Pefkianakis
 *
 * A simple geocoding request.
 */
public class GeocodingExample {
    public static void main(String[] args) {
        new GeocodingExample().start();
    }

    private void start() {
        GeocodingApi geocoding = new GeocodingApi();
        geocoding.setApiClient(GHApiUtil.createClient());
        try {
            GeocodingResponse geocodingResponse = geocoding.getGeocode("bautzen", "de", 5, false, false, null, null);
            GeocodingLocation loc0 = geocodingResponse.getHits().get(0);
            System.out.println(loc0.getPoint() + ", " + loc0.getName() + ", " + loc0.getCountry() + ", " + loc0.getState());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
