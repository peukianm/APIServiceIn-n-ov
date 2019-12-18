package api.innov.client.graphhopper.examples;

import api.innov.client.graphhopper.api.RoutingApi;
import api.innov.client.graphhopper.model.RouteResponse;
import api.innov.client.graphhopper.model.RouteResponsePath;
import api.innov.client.graphhopper.model.RouteResponsePathInstructions;
import api.innov.client.graphhopper.model.VehicleProfileId;
import api.innov.client.graphhopper.util.ApiException;
import api.innov.client.graphhopper.util.GHApiUtil;

import java.util.Arrays;
import java.util.Collections;

/**
 * A simple example for querying the Routing API.
 */
public class RoutingExample {
    public static void main(String[] args) {
        new RoutingExample().start();
    }

    private void start() {
        RoutingApi routing = new RoutingApi();
        routing.setApiClient(GHApiUtil.createClient());
        try {
            RouteResponse rsp = routing.getRoute(Arrays.asList("48.58467,11.57753", "48.572859,11.592464"),
                    Arrays.<String>asList(), Arrays.<String>asList(),
                    VehicleProfileId.CAR, "en", true, Arrays.<String>asList(), false,
                    true, true, false, true, null, false,
                    "fastest", Collections.<Integer>emptyList(), null, null, null,
                    null, null, null, null, null,
                    null, null);
            RouteResponsePath path = rsp.getPaths().get(0);
            RouteResponsePathInstructions instr = path.getInstructions().get(0);
            System.out.println(instr.getText());
        } catch (ApiException ex) {
            System.out.println(ex.getResponseBody());
            throw new RuntimeException(ex);
        }
    }
}
