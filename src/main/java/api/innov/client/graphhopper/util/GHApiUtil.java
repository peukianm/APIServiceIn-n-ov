package api.innov.client.graphhopper.util;

import api.innov.client.graphhopper.util.ApiClient;

public class GHApiUtil {

    public static ApiClient createClient() {
        ApiClient client = new ApiClient().setDebugging(true);
        //client.setApiKey(System.getProperty("graphhopper.key", ""));
        client.setApiKey("403448b0-df50-4d00-873c-b63a4479c313");
        return client;
    }
}
