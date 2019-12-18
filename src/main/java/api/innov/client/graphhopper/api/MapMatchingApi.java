/*
 * GraphHopper Directions API
 *  With the [GraphHopper Directions API](https://www.graphhopper.com/products/) you can integrate A-to-B route planning, turn-by-turn navigation, route optimization, isochrone calculations and other tools in your application.  The GraphHopper Directions API consists of the following RESTful web services:   * [Routing](#tag/Routing-API),  * [Route Optimization](#tag/Route-Optimization-API),  * [Isochrone](#tag/Isochrone-API),  * [Map Matching](#tag/Map-Matching-API),  * [Matrix](#tag/Matrix-API) and  * [Geocoding](#tag/Geocoding-API).  # Explore our APIs  To play and see the Route Optimization in action try our [route editor](https://graphhopper.com/blog/2015/07/21/graphhoppers-new-route-optimization-editor/)  which available in the [dashboard](https://graphhopper.com/dashboard/). See how the Routing and Geocoding is integrated in  our route planner website [GraphHopper Maps](https://graphhopper.com/maps) ([sources](https://github.com/graphhopper/graphhopper/tree/0.12/web/src/main/resources/assets)).  And [see below](#section/Explore-our-APIs/Insomnia) for a collection of requests for [Insomnia](https://insomnia.rest/) and [Postman](https://www.getpostman.com/). The request file contains all example requests from this documentation.  ## Get started  1. To use the GraphHopper Directions API you sign up [here](https://graphhopper.com/dashboard/#/register) and create an API key. 2. Read the documentation of the desired API part below. 3. Start using the GraphHopper Directions API. [Our API clients](#section/Explore-our-APIs/API-Clients) can speed up the integration.  To use the GraphHopper Directions API commercially, you can buy paid package [in the dashboard](https://graphhopper.com/dashboard/#/pricing).  ## Contact Us  If you have problems or questions see the following information:   * [FAQ](https://graphhopper.com/api/1/docs/FAQ/)  * [Public forum](https://discuss.graphhopper.com/c/directions-api)       * [Contact us](https://www.graphhopper.com/contact-form/)  To get informed about the newest features and development follow us at [twitter](https://twitter.com/graphhopper/) or [our blog](https://graphhopper.com/blog/).  Furthermore you can watch [this git repository](https://github.com/graphhopper/directions-api-doc) of this documentation, sign up at our [dashboard](https://graphhopper.com/dashboard/) to get the newsletter or sign up at [our forum](https://discuss.graphhopper.com/c/directions-api). Pick the channel you like most.  ## API Client Libraries  To speed up development and make coding easier, we offer the following client libraries:   * [JavaScript client](https://github.com/graphhopper/directions-api-js-client) - try the [live examples](https://graphhopper.com/api/1/examples/)  * [Others](https://github.com/graphhopper/directions-api-clients) like C#, Ruby, PHP, Python, ... automatically created for the Route Optimization  ### Bandwidth reduction  If you create your own client, make sure it supports http/2 and gzipped responses for best speed.  If you use the Matrix or Route Optimization and want to solve large problems, we recommend you to reduce bandwidth by [compressing your POST request](https://gist.github.com/karussell/82851e303ea7b3459b2dea01f18949f4) and specifying the header as follows: `Content-Encoding: gzip`.  ## Insomnia  To explore our APIs with [Insomnia](https://insomnia.rest/), follow these steps:  1. Open Insomnia and Import [our workspace](https://raw.githubusercontent.com/graphhopper/directions-api-doc/master/web/restclients/GraphHopper-Direction-API-Insomnia.json). 2. Specify [your API key](https://graphhopper.com/dashboard/#/register) in your workspace: Manage Environments -> Base Environment -> `\"api_key\": your API key` 3. Start exploring  ![Insomnia](./img/insomnia.png)  ## Postman  To explore our APIs with [Postman](https://www.getpostman.com/), follow these steps:  1. Import our [request collections](https://raw.githubusercontent.com/graphhopper/directions-api-doc/master/web/restclients/graphhopper_directions_api.postman_collection.json) as well as our [environment file](https://raw.githubusercontent.com/graphhopper/directions-api-doc/master/web/restclients/graphhopper_directions_api.postman_environment.json). 2. Specify [your API key](https://graphhopper.com/dashboard/#/register) in your environment: `\"api_key\": your API key` 3. Start exploring  ![Postman](./img/postman.png)  # Map Data and Routing Profiles  Currently, our main data source is [OpenStreetMap](https://www.openstreetmap.org). We also integrated other network data providers. This chapter gives an overview about the options you have.  ## OpenStreetMap  #### Geographical Coverage  [OpenStreetMap](https://www.openstreetmap.org) covers the entire world. If you want to convince yourself whether we can offer appropriate data for your region, please visit [GraphHopper Maps](https://graphhopper.com/maps/). You can edit and modify OpenStreetMap data if you find that important information is missing, for example, a weight restriction for a bridge. [Here](https://wiki.openstreetmap.org/wiki/Beginners%27_guide) is a beginner's guide that shows how to add data.  If you edited data, we usually consider your data after 1 week at latest.  #### Supported Vehicle Profiles  The Routing, Matrix and Route Optimizations support the following vehicle profiles:  Name       | Description           | Restrictions              | Icon -----------|:----------------------|:--------------------------|:--------------------------------------------------------- car        | Car mode              | car access                | ![car image](https://graphhopper.com/maps/img/car.png) small_truck| Small truck like a Mercedes Sprinter, Ford Transit or Iveco Daily | height=2.7m, width=2+0.4m, length=5.5m, weight=2080+1400 kg | ![small truck image](https://graphhopper.com/maps/img/small_truck.png) truck      | Truck like a MAN or Mercedes-Benz Actros | height=3.7m, width=2.6+0.5m, length=12m, weight=13000 + 13000 kg, hgv=yes, 3 Axes | ![truck image](https://graphhopper.com/maps/img/truck.png) scooter    | Moped mode | Fast inner city, often used for food delivery, is able to ignore certain bollards, maximum speed of roughly 50km/h | ![scooter image](https://graphhopper.com/maps/img/scooter.png) foot       | Pedestrian or walking | foot access         | ![foot image](https://graphhopper.com/maps/img/foot.png) hike       | Pedestrian or walking with priority for more beautiful hiking tours and potentially a bit longer than `foot`  | foot access         | ![hike image](https://graphhopper.com/maps/img/hike.png) bike       | Trekking bike avoiding hills | bike access  | ![bike image](https://graphhopper.com/maps/img/bike.png) mtb        | Mountainbike          | bike access         | ![Mountainbike image](https://graphhopper.com/maps/img/mtb.png) racingbike| Bike preferring roads | bike access         | ![racingbike image](https://graphhopper.com/maps/img/racingbike.png)  **Please note, that turn restrictions are considered only with `ch.disable=true`.**  For the free package you can only choose from `car`, `bike` or `foot`.  We also offer a sophisticated `motorcycle` profile powered by the [Kurviger](https://kurviger.de/en) Routing. Kurviger favors curves and slopes while avoiding cities and highways.  Also we offer custom vehicle profiles with different properties, different speed profiles or different access options. To find out more about custom profiles, please [contact us](https://www.graphhopper.com/contact-form/).  ## TomTom  If you need to consider traffic, you can purchase the TomTom add-on.  Please note:   * Currently we only offer this for our [Route Optimization](#tag/Route-Optimization-API).  * This add-on uses the TomTom road network and historical traffic information only. Live traffic is not yet considered. Read more about [how this works](https://www.graphhopper.com/blog/2017/11/06/time-dependent-optimization/).  * Additionally to our terms your end users need to accept the [TomTom Eula](https://www.graphhopper.com/tomtom-end-user-license-agreement/).  * We do *not* use the TomTom web services. We only use their data with our software.   [Contact us](https://www.graphhopper.com/contact-form/) for more details.  #### Geographical Coverage  We offer  - Europe including Russia - North, Central and South America - Saudi Arabia - United Arab Emirates - South Africa - Australia  #### Supported Vehicle Profiles  Name       | Description           | Restrictions              | Icon -----------|:----------------------|:--------------------------|:--------------------------------------------------------- car        | Car mode              | car access                | ![car image](https://graphhopper.com/maps/img/car.png) small_truck| Small truck like a Mercedes Sprinter, Ford Transit or Iveco Daily | height=2.7m, width=2+0.4m, length=5.5m, weight=2080+1400 kg | ![small truck image](https://graphhopper.com/maps/img/small_truck.png) 
 *
 * OpenAPI spec version: 1.0.0
 * Contact: support@graphhopper.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package api.innov.client.graphhopper.api;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import api.innov.client.graphhopper.model.GHError;
import api.innov.client.graphhopper.model.RouteResponse;
import api.innov.client.graphhopper.util.ApiCallback;
import api.innov.client.graphhopper.util.ApiClient;
import api.innov.client.graphhopper.util.ApiException;
import api.innov.client.graphhopper.util.ApiResponse;
import api.innov.client.graphhopper.util.Configuration;
import api.innov.client.graphhopper.util.Pair;
import api.innov.client.graphhopper.util.ProgressRequestBody;
import api.innov.client.graphhopper.util.ProgressResponseBody;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapMatchingApi {
    private ApiClient apiClient;

    public MapMatchingApi() {
        this(Configuration.getDefaultApiClient());
    }

    public MapMatchingApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Build call for postGPX
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call postGPXCall(final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/match";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "api_key" };
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call postGPXValidateBeforeCall(final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        com.squareup.okhttp.Call call = postGPXCall(progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Map-match a GPX file
     * ### Example You get an example response for a GPX via:  &#x60;&#x60;&#x60; curl -XPOST -H \&quot;Content-Type: application/gpx+xml\&quot; \&quot;https://graphhopper.com/api/1/match?vehicle&#x3D;car&amp;key&#x3D;[YOUR_KEY]\&quot; --data @/path/to/some.gpx &#x60;&#x60;&#x60;  ### Introduction ![Map Matching screenshot](./img/map-matching-example.gif)  The Map Matching is part of the GraphHopper Directions API and with this API you can snap measured GPS points typically as GPX files to a digital road network to e.g. clean data or attach certain data like elevation or turn instructions to it. Read more at Wikipedia.  In the example screenshot above and demo you see the Map Matching in action where the black line is the GPS track and the green one is matched result.  Most of the times, you can simply POST a GPX file, but some of the request parameters of the [Routing](#tag/Routing-API) apply here, too.  ### API Clients and Examples See the [clients](#section/API-Clients) section in the main documentation, and [live examples](https://graphhopper.com/api/1/examples/#map-matching).  ### Limits and Counts The cost for one request depends on the number of GPS location and is documented [here](https://graphhopper.com/api/1/docs/FAQ/).  One request should not exceed the Map Matching location limit depending on the package, see the pricing in our dashboard. 
     * @return RouteResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public RouteResponse postGPX() throws ApiException {
        ApiResponse<RouteResponse> resp = postGPXWithHttpInfo();
        return resp.getData();
    }

    /**
     * Map-match a GPX file
     * ### Example You get an example response for a GPX via:  &#x60;&#x60;&#x60; curl -XPOST -H \&quot;Content-Type: application/gpx+xml\&quot; \&quot;https://graphhopper.com/api/1/match?vehicle&#x3D;car&amp;key&#x3D;[YOUR_KEY]\&quot; --data @/path/to/some.gpx &#x60;&#x60;&#x60;  ### Introduction ![Map Matching screenshot](./img/map-matching-example.gif)  The Map Matching is part of the GraphHopper Directions API and with this API you can snap measured GPS points typically as GPX files to a digital road network to e.g. clean data or attach certain data like elevation or turn instructions to it. Read more at Wikipedia.  In the example screenshot above and demo you see the Map Matching in action where the black line is the GPS track and the green one is matched result.  Most of the times, you can simply POST a GPX file, but some of the request parameters of the [Routing](#tag/Routing-API) apply here, too.  ### API Clients and Examples See the [clients](#section/API-Clients) section in the main documentation, and [live examples](https://graphhopper.com/api/1/examples/#map-matching).  ### Limits and Counts The cost for one request depends on the number of GPS location and is documented [here](https://graphhopper.com/api/1/docs/FAQ/).  One request should not exceed the Map Matching location limit depending on the package, see the pricing in our dashboard. 
     * @return ApiResponse&lt;RouteResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<RouteResponse> postGPXWithHttpInfo() throws ApiException {
        com.squareup.okhttp.Call call = postGPXValidateBeforeCall(null, null);
        Type localVarReturnType = new TypeToken<RouteResponse>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Map-match a GPX file (asynchronously)
     * ### Example You get an example response for a GPX via:  &#x60;&#x60;&#x60; curl -XPOST -H \&quot;Content-Type: application/gpx+xml\&quot; \&quot;https://graphhopper.com/api/1/match?vehicle&#x3D;car&amp;key&#x3D;[YOUR_KEY]\&quot; --data @/path/to/some.gpx &#x60;&#x60;&#x60;  ### Introduction ![Map Matching screenshot](./img/map-matching-example.gif)  The Map Matching is part of the GraphHopper Directions API and with this API you can snap measured GPS points typically as GPX files to a digital road network to e.g. clean data or attach certain data like elevation or turn instructions to it. Read more at Wikipedia.  In the example screenshot above and demo you see the Map Matching in action where the black line is the GPS track and the green one is matched result.  Most of the times, you can simply POST a GPX file, but some of the request parameters of the [Routing](#tag/Routing-API) apply here, too.  ### API Clients and Examples See the [clients](#section/API-Clients) section in the main documentation, and [live examples](https://graphhopper.com/api/1/examples/#map-matching).  ### Limits and Counts The cost for one request depends on the number of GPS location and is documented [here](https://graphhopper.com/api/1/docs/FAQ/).  One request should not exceed the Map Matching location limit depending on the package, see the pricing in our dashboard. 
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call postGPXAsync(final ApiCallback<RouteResponse> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = postGPXValidateBeforeCall(progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<RouteResponse>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
