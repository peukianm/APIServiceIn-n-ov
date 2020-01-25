package api.innov.client.graphhopper;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.innov.util.SystemParameters;
import okhttp3.*;
 
/**
 * @author Michalis Pefkianakis
 *
 */
public class GeocodingGHClient {

	private static final Logger logger = LogManager.getLogger(GeocodingGHClient.class);
	private static String URL = "";
	private static final String SERVICEURL = SystemParameters.getInstance().getProperty("GHURL_GEOCODING");
	private static final String API_KEY = "&key=" + SystemParameters.getInstance().getProperty("API_KEY1");

	public static Response geocode(String params) throws Exception {
		URL = SERVICEURL + params + API_KEY;
	
		Request request = new Request.Builder().url(URL).get().build();
		OkHttpClient client = new OkHttpClient();
		Response response = client.newCall(request).execute();		
		return response;
	}
		


}
