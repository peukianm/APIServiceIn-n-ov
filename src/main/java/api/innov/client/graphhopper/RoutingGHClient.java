package api.innov.client.graphhopper;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.innov.client.graphhopper.api.RoutingApi;
import api.innov.client.graphhopper.model.RouteResponse;
import api.innov.client.graphhopper.model.RouteResponsePath;
import api.innov.client.graphhopper.model.RouteResponsePathInstructions;
import api.innov.client.graphhopper.model.VehicleProfileId;
import api.innov.client.graphhopper.util.ApiException;
import api.innov.client.graphhopper.util.GHApiUtil;
import api.innov.common.APIServiceInnovCommon;
import api.innov.util.SystemParameters;
import okhttp3.*;
 
/**
 * @author Michalis Pefkianakis
 *
 */
public class RoutingGHClient {

	private static final Logger logger = LogManager.getLogger(RoutingGHClient.class);
	private static String URL = "";
	private static final String SERVICEURL = SystemParameters.getInstance().getProperty("GHURL_ROUTE");
	private static final String API_KEY = "&key=" + SystemParameters.getInstance().getProperty("API_KEY1");

	public static Response route(String params) throws Exception {
		URL = SERVICEURL + params + API_KEY;
		Request request = new Request.Builder().url(URL).get().build();
		OkHttpClient client = new OkHttpClient();
		Response response = client.newCall(request).execute();		
		return response;
	}
		
	
	@SuppressWarnings("unused")
	private void start() {
//		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );	 
//		WebTarget webTarget = client.target("http://localhost:8080/JerseyDemos/rest").path("employees");	 
//		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_XML);	 
//		Response response = invocationBuilder.post(Entity.entity(emp, MediaType.APPLICATION_XML));

		RoutingApi routing = new RoutingApi();
		routing.setApiClient(GHApiUtil.createClient());
		try {
			RouteResponse rsp = routing.getRoute(Arrays.asList("48.58467,11.57753", "48.572859,11.592464"),
					Arrays.<String>asList(), Arrays.<String>asList(), VehicleProfileId.CAR, "en", true,
					Arrays.<String>asList(), false, true, true, false, true, null, false, "fastest",
					Collections.<Integer>emptyList(), null, null, null, null, null, null, null, null, null, null);
			RouteResponsePath path = rsp.getPaths().get(0);
			RouteResponsePathInstructions instr = path.getInstructions().get(0);
			System.out.println(instr.getText());
		} catch (ApiException ex) {
			System.out.println(ex.getResponseBody());
			APIServiceInnovCommon.logServiceError(ex, logger);
			throw new RuntimeException(ex);
		}
	}

}
