package api.innov.client.graphhopper.util;

import api.innov.client.graphhopper.util.ApiClient;
import api.innov.util.SystemParameters;

public class GHApiUtil {

    public static ApiClient createClient() {
        ApiClient client = new ApiClient().setDebugging(true);
        //client.setApiKey(System.getProperty("graphhopper.key", ""));
        System.out.println("API in GH UTILITY="+SystemParameters.getInstance().getProperty("API_KEY1"));
        client.setApiKey(SystemParameters.getInstance().getProperty("API_KEY1"));
        return client;
    }
}
