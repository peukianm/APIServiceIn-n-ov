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
import api.innov.client.graphhopper.model.JobId;
import api.innov.client.graphhopper.model.MatrixResponse;
import api.innov.client.graphhopper.model.VehicleProfileId;
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

public class MatrixApi {
    private ApiClient apiClient;

    public MatrixApi() {
        this(Configuration.getDefaultApiClient());
    }

    public MatrixApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Build call for calculateMatrix
     * @param body  (optional)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call calculateMatrixCall(Object body, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = body;
        
        // create path and map variables
        String localVarPath = "/matrix/calculate";

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
            "application/json"
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
    private com.squareup.okhttp.Call calculateMatrixValidateBeforeCall(Object body, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        com.squareup.okhttp.Call call = calculateMatrixCall(body, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Post a matrix request in batch mode
     * The Batch Matrix allows using matrices with more locations and works asynchronously - similar to our Route Optimization:  * Create a HTTP POST request against &#x60;/matrix/calculate&#x60; and add the key in the URL: &#x60;/matrix/calculate?key&#x3D;[YOUR_KEY]&#x60;. This will give you the &#x60;job_id&#x60; from the response json like &#x60;{ \&quot;job_id\&quot;: \&quot;7ac65787-fb99-4e02-a832-2c3010c70097\&quot; }&#x60;  * Poll via HTTP GET requests every second against &#x60;/matrix/solution/[job_id]&#x60;  Here are some full examples via curl: &#x60;&#x60;&#x60;bash $ curl -X POST -H \&quot;Content-Type: application/json\&quot; \&quot;https://graphhopper.com/api/1/matrix/calculate?key&#x3D;[YOUR_KEY]\&quot; -d &#x27;{\&quot;points\&quot;:[[13.29895,52.48696],[13.370876,52.489575],[13.439026,52.511206]]}&#x27; {\&quot;job_id\&quot;:\&quot;7ac65787-fb99-4e02-a832-2c3010c70097\&quot;} &#x60;&#x60;&#x60;  Pick the returned &#x60;job_id&#x60; and use it in the next GET requests: &#x60;&#x60;&#x60;bash $ curl -X GET \&quot;https://graphhopper.com/api/1/matrix/solution/7ac65787-fb99-4e02-a832-2c3010c70097?key&#x3D;[YOUR_KEY]\&quot; {\&quot;status\&quot;:\&quot;waiting\&quot;} &#x60;&#x60;&#x60;  When the calculation is finished (&#x60;status:finished&#x60;) the JSON response will contain the full matrix JSON under &#x60;solution&#x60;: &#x60;&#x60;&#x60;bash $ curl -X GET \&quot;https://graphhopper.com/api/1/matrix/solution/7ac65787-fb99-4e02-a832-2c3010c70097?key&#x3D;[YOUR_KEY]\&quot; {\&quot;solution\&quot;:{\&quot;weights\&quot;:[[0.0,470.453,945.414],[503.793,0.0,580.871],[970.49,569.511,0.0]],\&quot;info\&quot;:{\&quot;copyrights\&quot;:[\&quot;GraphHopper\&quot;,\&quot;OpenStreetMap contributors\&quot;]}},\&quot;status\&quot;:\&quot;finished\&quot;} &#x60;&#x60;&#x60;  Please note that if an error occured while calculation the JSON will not have a status but contain directly the error message e.g.: &#x60;&#x60;&#x60;json {\&quot;message\&quot;:\&quot;Cannot find from_points: 1\&quot;,\&quot;hints\&quot;:[{...}]} &#x60;&#x60;&#x60; 
     * @param body  (optional)
     * @return JobId
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public JobId calculateMatrix(Object body) throws ApiException {
        ApiResponse<JobId> resp = calculateMatrixWithHttpInfo(body);
        return resp.getData();
    }

    /**
     * Post a matrix request in batch mode
     * The Batch Matrix allows using matrices with more locations and works asynchronously - similar to our Route Optimization:  * Create a HTTP POST request against &#x60;/matrix/calculate&#x60; and add the key in the URL: &#x60;/matrix/calculate?key&#x3D;[YOUR_KEY]&#x60;. This will give you the &#x60;job_id&#x60; from the response json like &#x60;{ \&quot;job_id\&quot;: \&quot;7ac65787-fb99-4e02-a832-2c3010c70097\&quot; }&#x60;  * Poll via HTTP GET requests every second against &#x60;/matrix/solution/[job_id]&#x60;  Here are some full examples via curl: &#x60;&#x60;&#x60;bash $ curl -X POST -H \&quot;Content-Type: application/json\&quot; \&quot;https://graphhopper.com/api/1/matrix/calculate?key&#x3D;[YOUR_KEY]\&quot; -d &#x27;{\&quot;points\&quot;:[[13.29895,52.48696],[13.370876,52.489575],[13.439026,52.511206]]}&#x27; {\&quot;job_id\&quot;:\&quot;7ac65787-fb99-4e02-a832-2c3010c70097\&quot;} &#x60;&#x60;&#x60;  Pick the returned &#x60;job_id&#x60; and use it in the next GET requests: &#x60;&#x60;&#x60;bash $ curl -X GET \&quot;https://graphhopper.com/api/1/matrix/solution/7ac65787-fb99-4e02-a832-2c3010c70097?key&#x3D;[YOUR_KEY]\&quot; {\&quot;status\&quot;:\&quot;waiting\&quot;} &#x60;&#x60;&#x60;  When the calculation is finished (&#x60;status:finished&#x60;) the JSON response will contain the full matrix JSON under &#x60;solution&#x60;: &#x60;&#x60;&#x60;bash $ curl -X GET \&quot;https://graphhopper.com/api/1/matrix/solution/7ac65787-fb99-4e02-a832-2c3010c70097?key&#x3D;[YOUR_KEY]\&quot; {\&quot;solution\&quot;:{\&quot;weights\&quot;:[[0.0,470.453,945.414],[503.793,0.0,580.871],[970.49,569.511,0.0]],\&quot;info\&quot;:{\&quot;copyrights\&quot;:[\&quot;GraphHopper\&quot;,\&quot;OpenStreetMap contributors\&quot;]}},\&quot;status\&quot;:\&quot;finished\&quot;} &#x60;&#x60;&#x60;  Please note that if an error occured while calculation the JSON will not have a status but contain directly the error message e.g.: &#x60;&#x60;&#x60;json {\&quot;message\&quot;:\&quot;Cannot find from_points: 1\&quot;,\&quot;hints\&quot;:[{...}]} &#x60;&#x60;&#x60; 
     * @param body  (optional)
     * @return ApiResponse&lt;JobId&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<JobId> calculateMatrixWithHttpInfo(Object body) throws ApiException {
        com.squareup.okhttp.Call call = calculateMatrixValidateBeforeCall(body, null, null);
        Type localVarReturnType = new TypeToken<JobId>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Post a matrix request in batch mode (asynchronously)
     * The Batch Matrix allows using matrices with more locations and works asynchronously - similar to our Route Optimization:  * Create a HTTP POST request against &#x60;/matrix/calculate&#x60; and add the key in the URL: &#x60;/matrix/calculate?key&#x3D;[YOUR_KEY]&#x60;. This will give you the &#x60;job_id&#x60; from the response json like &#x60;{ \&quot;job_id\&quot;: \&quot;7ac65787-fb99-4e02-a832-2c3010c70097\&quot; }&#x60;  * Poll via HTTP GET requests every second against &#x60;/matrix/solution/[job_id]&#x60;  Here are some full examples via curl: &#x60;&#x60;&#x60;bash $ curl -X POST -H \&quot;Content-Type: application/json\&quot; \&quot;https://graphhopper.com/api/1/matrix/calculate?key&#x3D;[YOUR_KEY]\&quot; -d &#x27;{\&quot;points\&quot;:[[13.29895,52.48696],[13.370876,52.489575],[13.439026,52.511206]]}&#x27; {\&quot;job_id\&quot;:\&quot;7ac65787-fb99-4e02-a832-2c3010c70097\&quot;} &#x60;&#x60;&#x60;  Pick the returned &#x60;job_id&#x60; and use it in the next GET requests: &#x60;&#x60;&#x60;bash $ curl -X GET \&quot;https://graphhopper.com/api/1/matrix/solution/7ac65787-fb99-4e02-a832-2c3010c70097?key&#x3D;[YOUR_KEY]\&quot; {\&quot;status\&quot;:\&quot;waiting\&quot;} &#x60;&#x60;&#x60;  When the calculation is finished (&#x60;status:finished&#x60;) the JSON response will contain the full matrix JSON under &#x60;solution&#x60;: &#x60;&#x60;&#x60;bash $ curl -X GET \&quot;https://graphhopper.com/api/1/matrix/solution/7ac65787-fb99-4e02-a832-2c3010c70097?key&#x3D;[YOUR_KEY]\&quot; {\&quot;solution\&quot;:{\&quot;weights\&quot;:[[0.0,470.453,945.414],[503.793,0.0,580.871],[970.49,569.511,0.0]],\&quot;info\&quot;:{\&quot;copyrights\&quot;:[\&quot;GraphHopper\&quot;,\&quot;OpenStreetMap contributors\&quot;]}},\&quot;status\&quot;:\&quot;finished\&quot;} &#x60;&#x60;&#x60;  Please note that if an error occured while calculation the JSON will not have a status but contain directly the error message e.g.: &#x60;&#x60;&#x60;json {\&quot;message\&quot;:\&quot;Cannot find from_points: 1\&quot;,\&quot;hints\&quot;:[{...}]} &#x60;&#x60;&#x60; 
     * @param body  (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call calculateMatrixAsync(Object body, final ApiCallback<JobId> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = calculateMatrixValidateBeforeCall(body, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<JobId>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for getMatrix
     * @param point Specify multiple points in &#x60;latitude,longitude&#x60; for which the weight-, route-, time- or distance-matrix should be calculated. In this case the starts are identical to the destinations. If there are N points, then NxN entries will be calculated. The order of the point parameter is important. Specify at least three points. Cannot be used together with from_point or to_point. (optional)
     * @param fromPoint The starting points for the routes in &#x60;latitude,longitude&#x60;. E.g. if you want to calculate the three routes A-&amp;gt;1, A-&amp;gt;2, A-&amp;gt;3 then you have one from_point parameter and three to_point parameters. (optional)
     * @param toPoint The destination points for the routes in &#x60;latitude,longitude&#x60;. (optional)
     * @param pointHint Optional parameter. Specifies a hint for each &#x60;point&#x60; parameter to prefer a certain street for the closest location lookup. E.g. if there is an address or house with two or more neighboring streets you can control for which street the closest location is looked up. (optional)
     * @param fromPointHint For the from_point parameter. See point_hint (optional)
     * @param toPointHint For the to_point parameter. See point_hint (optional)
     * @param snapPrevention Optional parameter to avoid snapping to a certain road class or road environment. Current supported values &#x60;motorway&#x60;, &#x60;trunk&#x60;, &#x60;ferry&#x60;, &#x60;tunnel&#x60;, &#x60;bridge&#x60; and &#x60;ford&#x60;. Multiple values are specified like &#x60;snap_prevention&#x3D;ferry&amp;snap_prevention&#x3D;motorway&#x60;  (optional)
     * @param outArray Specifies which arrays should be included in the response. Specify one or more of the following options &#x27;weights&#x27;, &#x27;times&#x27;, &#x27;distances&#x27;. To specify more than one array use e.g. out_array&#x3D;times&amp;out_array&#x3D;distances. The units of the entries of distances are meters, of times are seconds and of weights is arbitrary and it can differ for different vehicles or versions of this API. (optional)
     * @param vehicle The vehicle profile for which the matrix should be calculated. (optional)
     * @param failFast Specifies whether or not the matrix calculation should return with an error as soon as possible in case some points cannot be found or some points are not connected. If set to &#x60;false&#x60; the time/weight/distance matrix will be calculated for all valid points and contain the &#x60;null&#x60; value for all entries that could not be calculated. The &#x60;hint&#x60; field of the response will also contain additional information about what went wrong (see its documentation). (optional)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getMatrixCall(List<String> point, List<String> fromPoint, List<String> toPoint, List<String> pointHint, List<String> fromPointHint, List<String> toPointHint, List<String> snapPrevention, List<String> outArray, VehicleProfileId vehicle, Boolean failFast, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/matrix";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (point != null)
        localVarCollectionQueryParams.addAll(apiClient.parameterToPairs("multi", "point", point));
        if (fromPoint != null)
        localVarCollectionQueryParams.addAll(apiClient.parameterToPairs("multi", "from_point", fromPoint));
        if (toPoint != null)
        localVarCollectionQueryParams.addAll(apiClient.parameterToPairs("multi", "to_point", toPoint));
        if (pointHint != null)
        localVarCollectionQueryParams.addAll(apiClient.parameterToPairs("multi", "point_hint", pointHint));
        if (fromPointHint != null)
        localVarCollectionQueryParams.addAll(apiClient.parameterToPairs("multi", "from_point_hint", fromPointHint));
        if (toPointHint != null)
        localVarCollectionQueryParams.addAll(apiClient.parameterToPairs("multi", "to_point_hint", toPointHint));
        if (snapPrevention != null)
        localVarCollectionQueryParams.addAll(apiClient.parameterToPairs("multi", "snap_prevention", snapPrevention));
        if (outArray != null)
        localVarCollectionQueryParams.addAll(apiClient.parameterToPairs("multi", "out_array", outArray));
        if (vehicle != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("vehicle", vehicle));
        if (failFast != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("fail_fast", failFast));

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
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getMatrixValidateBeforeCall(List<String> point, List<String> fromPoint, List<String> toPoint, List<String> pointHint, List<String> fromPointHint, List<String> toPointHint, List<String> snapPrevention, List<String> outArray, VehicleProfileId vehicle, Boolean failFast, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        com.squareup.okhttp.Call call = getMatrixCall(point, fromPoint, toPoint, pointHint, fromPointHint, toPointHint, snapPrevention, outArray, vehicle, failFast, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Execute a matrix request
     * ### Introduction  ![Matrix Example](./img/matrix-example.png)  The Matrix is part of the [GraphHopper Directions API](https://graphhopper.com/#directions-api) and with this API you can calculate many-to-many distances, times or routes a lot more efficient than calling the Routing multiple times.  In the [Routing](#tag/Routing-API) we support multiple points, so called &#x27;via points&#x27;, which results in one route being calculated. The Matrix results in NxM routes or more precise NxM weights, distances or times being calculated but is a lot faster compared to NxM single requests. The most simple example is a tourist trying to decide which pizza is close to him instead of using beeline distance she can calculate a 1x4 matrix. Or a delivery service in the need of often big NxN matrices to solve vehicle routing problems. E.g. the [GraphHopper Route Optimization](#tag/Route-Optimization-API) uses the Matrix under the hood to achieve this.  Some other use case scenarios for the Matrix:   * Logistic problems often pick up many items from and deliver them to many locations.  * Calculating detours with many possible points in-between and selecting the best e.g. interesting for ridesharing or taxi applications. For this 1-to-many requests are necessary.  * Finding the best tour for a tourist in the need to visit as many points of interests as possible.  * ...  ### API Clients and Examples  See the [clients](#section/API-Clients) section in the main document and [live examples](https://graphhopper.com/api/1/examples/#matrix).  ### Description  The Matrix calculates the well known distance-matrix for a set of points, i.e. it calculates all the distances between every point combination. But we do not stop there, we also offer a time-, weight- and route-matrix. The weight-matrix can be used as raw input for e.g. a vehicle routing problem ([VRP](http://en.wikipedia.org/wiki/Vehicle_routing_problem)) and is more precise than a time- or distance-matrix. E.g. for bike routes the actual weight of a route (e.g. the \&quot;beauty\&quot;) is what you want to decide if a route is &#x27;better&#x27; and not always the taken time or distance.  A simple illustration for a 3x3 matrix with identical from and to points:   -          |to_point1|to_point2|to_point3 :-----------|:--------|:--------|:-------- from_point1 |0        |1-&gt;2     | 1-&gt;3 from_point2 |2-&gt;1     |0        | 2-&gt;3 from_point3 |3-&gt;1     |3-&gt;2     | 0  A simple illustration for a 1x3 matrix with different start- and end-points:   -          | to_point1  | to_point2 | t_point3 :-----------|:-----------|:----------|:-------- from_pointA |A-&gt;1        |A-&gt;2       |A-&gt;3   For every route 1-&gt;2, 1-3, ... or A-&gt;1,A-&gt;2,A-&gt;3 you can return only the weight, the time and the distance. To calculate full routes you can use the [Routing](#tag/Routing-API) which also has a lot of different parameters.  ### Limits and Counts  The cost for one request depends on the number of locations and is documented [here](https://graphhopper.com/api/1/docs/FAQ/#what-is-one-credit).  One request should not exceed the Matrix location limit depending on the package, see the pricing in our dashboard. 
     * @param point Specify multiple points in &#x60;latitude,longitude&#x60; for which the weight-, route-, time- or distance-matrix should be calculated. In this case the starts are identical to the destinations. If there are N points, then NxN entries will be calculated. The order of the point parameter is important. Specify at least three points. Cannot be used together with from_point or to_point. (optional)
     * @param fromPoint The starting points for the routes in &#x60;latitude,longitude&#x60;. E.g. if you want to calculate the three routes A-&amp;gt;1, A-&amp;gt;2, A-&amp;gt;3 then you have one from_point parameter and three to_point parameters. (optional)
     * @param toPoint The destination points for the routes in &#x60;latitude,longitude&#x60;. (optional)
     * @param pointHint Optional parameter. Specifies a hint for each &#x60;point&#x60; parameter to prefer a certain street for the closest location lookup. E.g. if there is an address or house with two or more neighboring streets you can control for which street the closest location is looked up. (optional)
     * @param fromPointHint For the from_point parameter. See point_hint (optional)
     * @param toPointHint For the to_point parameter. See point_hint (optional)
     * @param snapPrevention Optional parameter to avoid snapping to a certain road class or road environment. Current supported values &#x60;motorway&#x60;, &#x60;trunk&#x60;, &#x60;ferry&#x60;, &#x60;tunnel&#x60;, &#x60;bridge&#x60; and &#x60;ford&#x60;. Multiple values are specified like &#x60;snap_prevention&#x3D;ferry&amp;snap_prevention&#x3D;motorway&#x60;  (optional)
     * @param outArray Specifies which arrays should be included in the response. Specify one or more of the following options &#x27;weights&#x27;, &#x27;times&#x27;, &#x27;distances&#x27;. To specify more than one array use e.g. out_array&#x3D;times&amp;out_array&#x3D;distances. The units of the entries of distances are meters, of times are seconds and of weights is arbitrary and it can differ for different vehicles or versions of this API. (optional)
     * @param vehicle The vehicle profile for which the matrix should be calculated. (optional)
     * @param failFast Specifies whether or not the matrix calculation should return with an error as soon as possible in case some points cannot be found or some points are not connected. If set to &#x60;false&#x60; the time/weight/distance matrix will be calculated for all valid points and contain the &#x60;null&#x60; value for all entries that could not be calculated. The &#x60;hint&#x60; field of the response will also contain additional information about what went wrong (see its documentation). (optional)
     * @return MatrixResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public MatrixResponse getMatrix(List<String> point, List<String> fromPoint, List<String> toPoint, List<String> pointHint, List<String> fromPointHint, List<String> toPointHint, List<String> snapPrevention, List<String> outArray, VehicleProfileId vehicle, Boolean failFast) throws ApiException {
        ApiResponse<MatrixResponse> resp = getMatrixWithHttpInfo(point, fromPoint, toPoint, pointHint, fromPointHint, toPointHint, snapPrevention, outArray, vehicle, failFast);
        return resp.getData();
    }

    /**
     * Execute a matrix request
     * ### Introduction  ![Matrix Example](./img/matrix-example.png)  The Matrix is part of the [GraphHopper Directions API](https://graphhopper.com/#directions-api) and with this API you can calculate many-to-many distances, times or routes a lot more efficient than calling the Routing multiple times.  In the [Routing](#tag/Routing-API) we support multiple points, so called &#x27;via points&#x27;, which results in one route being calculated. The Matrix results in NxM routes or more precise NxM weights, distances or times being calculated but is a lot faster compared to NxM single requests. The most simple example is a tourist trying to decide which pizza is close to him instead of using beeline distance she can calculate a 1x4 matrix. Or a delivery service in the need of often big NxN matrices to solve vehicle routing problems. E.g. the [GraphHopper Route Optimization](#tag/Route-Optimization-API) uses the Matrix under the hood to achieve this.  Some other use case scenarios for the Matrix:   * Logistic problems often pick up many items from and deliver them to many locations.  * Calculating detours with many possible points in-between and selecting the best e.g. interesting for ridesharing or taxi applications. For this 1-to-many requests are necessary.  * Finding the best tour for a tourist in the need to visit as many points of interests as possible.  * ...  ### API Clients and Examples  See the [clients](#section/API-Clients) section in the main document and [live examples](https://graphhopper.com/api/1/examples/#matrix).  ### Description  The Matrix calculates the well known distance-matrix for a set of points, i.e. it calculates all the distances between every point combination. But we do not stop there, we also offer a time-, weight- and route-matrix. The weight-matrix can be used as raw input for e.g. a vehicle routing problem ([VRP](http://en.wikipedia.org/wiki/Vehicle_routing_problem)) and is more precise than a time- or distance-matrix. E.g. for bike routes the actual weight of a route (e.g. the \&quot;beauty\&quot;) is what you want to decide if a route is &#x27;better&#x27; and not always the taken time or distance.  A simple illustration for a 3x3 matrix with identical from and to points:   -          |to_point1|to_point2|to_point3 :-----------|:--------|:--------|:-------- from_point1 |0        |1-&gt;2     | 1-&gt;3 from_point2 |2-&gt;1     |0        | 2-&gt;3 from_point3 |3-&gt;1     |3-&gt;2     | 0  A simple illustration for a 1x3 matrix with different start- and end-points:   -          | to_point1  | to_point2 | t_point3 :-----------|:-----------|:----------|:-------- from_pointA |A-&gt;1        |A-&gt;2       |A-&gt;3   For every route 1-&gt;2, 1-3, ... or A-&gt;1,A-&gt;2,A-&gt;3 you can return only the weight, the time and the distance. To calculate full routes you can use the [Routing](#tag/Routing-API) which also has a lot of different parameters.  ### Limits and Counts  The cost for one request depends on the number of locations and is documented [here](https://graphhopper.com/api/1/docs/FAQ/#what-is-one-credit).  One request should not exceed the Matrix location limit depending on the package, see the pricing in our dashboard. 
     * @param point Specify multiple points in &#x60;latitude,longitude&#x60; for which the weight-, route-, time- or distance-matrix should be calculated. In this case the starts are identical to the destinations. If there are N points, then NxN entries will be calculated. The order of the point parameter is important. Specify at least three points. Cannot be used together with from_point or to_point. (optional)
     * @param fromPoint The starting points for the routes in &#x60;latitude,longitude&#x60;. E.g. if you want to calculate the three routes A-&amp;gt;1, A-&amp;gt;2, A-&amp;gt;3 then you have one from_point parameter and three to_point parameters. (optional)
     * @param toPoint The destination points for the routes in &#x60;latitude,longitude&#x60;. (optional)
     * @param pointHint Optional parameter. Specifies a hint for each &#x60;point&#x60; parameter to prefer a certain street for the closest location lookup. E.g. if there is an address or house with two or more neighboring streets you can control for which street the closest location is looked up. (optional)
     * @param fromPointHint For the from_point parameter. See point_hint (optional)
     * @param toPointHint For the to_point parameter. See point_hint (optional)
     * @param snapPrevention Optional parameter to avoid snapping to a certain road class or road environment. Current supported values &#x60;motorway&#x60;, &#x60;trunk&#x60;, &#x60;ferry&#x60;, &#x60;tunnel&#x60;, &#x60;bridge&#x60; and &#x60;ford&#x60;. Multiple values are specified like &#x60;snap_prevention&#x3D;ferry&amp;snap_prevention&#x3D;motorway&#x60;  (optional)
     * @param outArray Specifies which arrays should be included in the response. Specify one or more of the following options &#x27;weights&#x27;, &#x27;times&#x27;, &#x27;distances&#x27;. To specify more than one array use e.g. out_array&#x3D;times&amp;out_array&#x3D;distances. The units of the entries of distances are meters, of times are seconds and of weights is arbitrary and it can differ for different vehicles or versions of this API. (optional)
     * @param vehicle The vehicle profile for which the matrix should be calculated. (optional)
     * @param failFast Specifies whether or not the matrix calculation should return with an error as soon as possible in case some points cannot be found or some points are not connected. If set to &#x60;false&#x60; the time/weight/distance matrix will be calculated for all valid points and contain the &#x60;null&#x60; value for all entries that could not be calculated. The &#x60;hint&#x60; field of the response will also contain additional information about what went wrong (see its documentation). (optional)
     * @return ApiResponse&lt;MatrixResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<MatrixResponse> getMatrixWithHttpInfo(List<String> point, List<String> fromPoint, List<String> toPoint, List<String> pointHint, List<String> fromPointHint, List<String> toPointHint, List<String> snapPrevention, List<String> outArray, VehicleProfileId vehicle, Boolean failFast) throws ApiException {
        com.squareup.okhttp.Call call = getMatrixValidateBeforeCall(point, fromPoint, toPoint, pointHint, fromPointHint, toPointHint, snapPrevention, outArray, vehicle, failFast, null, null);
        Type localVarReturnType = new TypeToken<MatrixResponse>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Execute a matrix request (asynchronously)
     * ### Introduction  ![Matrix Example](./img/matrix-example.png)  The Matrix is part of the [GraphHopper Directions API](https://graphhopper.com/#directions-api) and with this API you can calculate many-to-many distances, times or routes a lot more efficient than calling the Routing multiple times.  In the [Routing](#tag/Routing-API) we support multiple points, so called &#x27;via points&#x27;, which results in one route being calculated. The Matrix results in NxM routes or more precise NxM weights, distances or times being calculated but is a lot faster compared to NxM single requests. The most simple example is a tourist trying to decide which pizza is close to him instead of using beeline distance she can calculate a 1x4 matrix. Or a delivery service in the need of often big NxN matrices to solve vehicle routing problems. E.g. the [GraphHopper Route Optimization](#tag/Route-Optimization-API) uses the Matrix under the hood to achieve this.  Some other use case scenarios for the Matrix:   * Logistic problems often pick up many items from and deliver them to many locations.  * Calculating detours with many possible points in-between and selecting the best e.g. interesting for ridesharing or taxi applications. For this 1-to-many requests are necessary.  * Finding the best tour for a tourist in the need to visit as many points of interests as possible.  * ...  ### API Clients and Examples  See the [clients](#section/API-Clients) section in the main document and [live examples](https://graphhopper.com/api/1/examples/#matrix).  ### Description  The Matrix calculates the well known distance-matrix for a set of points, i.e. it calculates all the distances between every point combination. But we do not stop there, we also offer a time-, weight- and route-matrix. The weight-matrix can be used as raw input for e.g. a vehicle routing problem ([VRP](http://en.wikipedia.org/wiki/Vehicle_routing_problem)) and is more precise than a time- or distance-matrix. E.g. for bike routes the actual weight of a route (e.g. the \&quot;beauty\&quot;) is what you want to decide if a route is &#x27;better&#x27; and not always the taken time or distance.  A simple illustration for a 3x3 matrix with identical from and to points:   -          |to_point1|to_point2|to_point3 :-----------|:--------|:--------|:-------- from_point1 |0        |1-&gt;2     | 1-&gt;3 from_point2 |2-&gt;1     |0        | 2-&gt;3 from_point3 |3-&gt;1     |3-&gt;2     | 0  A simple illustration for a 1x3 matrix with different start- and end-points:   -          | to_point1  | to_point2 | t_point3 :-----------|:-----------|:----------|:-------- from_pointA |A-&gt;1        |A-&gt;2       |A-&gt;3   For every route 1-&gt;2, 1-3, ... or A-&gt;1,A-&gt;2,A-&gt;3 you can return only the weight, the time and the distance. To calculate full routes you can use the [Routing](#tag/Routing-API) which also has a lot of different parameters.  ### Limits and Counts  The cost for one request depends on the number of locations and is documented [here](https://graphhopper.com/api/1/docs/FAQ/#what-is-one-credit).  One request should not exceed the Matrix location limit depending on the package, see the pricing in our dashboard. 
     * @param point Specify multiple points in &#x60;latitude,longitude&#x60; for which the weight-, route-, time- or distance-matrix should be calculated. In this case the starts are identical to the destinations. If there are N points, then NxN entries will be calculated. The order of the point parameter is important. Specify at least three points. Cannot be used together with from_point or to_point. (optional)
     * @param fromPoint The starting points for the routes in &#x60;latitude,longitude&#x60;. E.g. if you want to calculate the three routes A-&amp;gt;1, A-&amp;gt;2, A-&amp;gt;3 then you have one from_point parameter and three to_point parameters. (optional)
     * @param toPoint The destination points for the routes in &#x60;latitude,longitude&#x60;. (optional)
     * @param pointHint Optional parameter. Specifies a hint for each &#x60;point&#x60; parameter to prefer a certain street for the closest location lookup. E.g. if there is an address or house with two or more neighboring streets you can control for which street the closest location is looked up. (optional)
     * @param fromPointHint For the from_point parameter. See point_hint (optional)
     * @param toPointHint For the to_point parameter. See point_hint (optional)
     * @param snapPrevention Optional parameter to avoid snapping to a certain road class or road environment. Current supported values &#x60;motorway&#x60;, &#x60;trunk&#x60;, &#x60;ferry&#x60;, &#x60;tunnel&#x60;, &#x60;bridge&#x60; and &#x60;ford&#x60;. Multiple values are specified like &#x60;snap_prevention&#x3D;ferry&amp;snap_prevention&#x3D;motorway&#x60;  (optional)
     * @param outArray Specifies which arrays should be included in the response. Specify one or more of the following options &#x27;weights&#x27;, &#x27;times&#x27;, &#x27;distances&#x27;. To specify more than one array use e.g. out_array&#x3D;times&amp;out_array&#x3D;distances. The units of the entries of distances are meters, of times are seconds and of weights is arbitrary and it can differ for different vehicles or versions of this API. (optional)
     * @param vehicle The vehicle profile for which the matrix should be calculated. (optional)
     * @param failFast Specifies whether or not the matrix calculation should return with an error as soon as possible in case some points cannot be found or some points are not connected. If set to &#x60;false&#x60; the time/weight/distance matrix will be calculated for all valid points and contain the &#x60;null&#x60; value for all entries that could not be calculated. The &#x60;hint&#x60; field of the response will also contain additional information about what went wrong (see its documentation). (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getMatrixAsync(List<String> point, List<String> fromPoint, List<String> toPoint, List<String> pointHint, List<String> fromPointHint, List<String> toPointHint, List<String> snapPrevention, List<String> outArray, VehicleProfileId vehicle, Boolean failFast, final ApiCallback<MatrixResponse> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = getMatrixValidateBeforeCall(point, fromPoint, toPoint, pointHint, fromPointHint, toPointHint, snapPrevention, outArray, vehicle, failFast, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<MatrixResponse>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for getMatrixSolution
     * @param jobId Request solution with jobId (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getMatrixSolutionCall(String jobId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/matrix/solution/{jobId}"
            .replaceAll("\\{" + "jobId" + "\\}", apiClient.escapeString(jobId.toString()));

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
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getMatrixSolutionValidateBeforeCall(String jobId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        // verify the required parameter 'jobId' is set
        if (jobId == null) {
            throw new ApiException("Missing the required parameter 'jobId' when calling getMatrixSolution(Async)");
        }
        
        com.squareup.okhttp.Call call = getMatrixSolutionCall(jobId, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Retrieve matrix
     * This endpoint returns the solution of posted matrix request. You can fetch it with the job_id, you have been sent. 
     * @param jobId Request solution with jobId (required)
     * @return MatrixResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public MatrixResponse getMatrixSolution(String jobId) throws ApiException {
        ApiResponse<MatrixResponse> resp = getMatrixSolutionWithHttpInfo(jobId);
        return resp.getData();
    }

    /**
     * Retrieve matrix
     * This endpoint returns the solution of posted matrix request. You can fetch it with the job_id, you have been sent. 
     * @param jobId Request solution with jobId (required)
     * @return ApiResponse&lt;MatrixResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<MatrixResponse> getMatrixSolutionWithHttpInfo(String jobId) throws ApiException {
        com.squareup.okhttp.Call call = getMatrixSolutionValidateBeforeCall(jobId, null, null);
        Type localVarReturnType = new TypeToken<MatrixResponse>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Retrieve matrix (asynchronously)
     * This endpoint returns the solution of posted matrix request. You can fetch it with the job_id, you have been sent. 
     * @param jobId Request solution with jobId (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getMatrixSolutionAsync(String jobId, final ApiCallback<MatrixResponse> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = getMatrixSolutionValidateBeforeCall(jobId, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<MatrixResponse>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for postMatrix
     * @param body  (optional)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call postMatrixCall(Object body, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = body;
        
        // create path and map variables
        String localVarPath = "/matrix";

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
            "application/json"
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
    private com.squareup.okhttp.Call postMatrixValidateBeforeCall(Object body, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        com.squareup.okhttp.Call call = postMatrixCall(body, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Execute a matrix request
     * The GET request has an URL length limitation, which hurts for many locations per request. In those cases use a HTTP POST request with JSON data as input. The only parameter in the URL will be the key. Both request scenarios are identical except that all singular parameter names are named as their plural for a POST request. All effected parameters are: &#x60;points&#x60;, &#x60;from_points&#x60;, &#x60;to_points&#x60;, and &#x60;out_array&#x60;.  *Please note that in opposite to the GET endpoint, points are specified in &#x60;longitude, latitude&#x60;*.  For example &#x60;point&#x3D;10,11&amp;point&#x3D;20,22&#x60; will be converted to the following JSON &#x60;points&#x60; array: &#x60;&#x60;&#x60;json { \&quot;points\&quot;: [[11,10], [22,20]] } &#x60;&#x60;&#x60; Note that also the order changes to &#x60;[longitude,latitude]&#x60; similar to [GeoJson](http://geojson.org/geojson-spec.html#examples).  Example: &#x60;&#x60;&#x60;bash curl -X POST -H \&quot;Content-Type: application/json\&quot; \&quot;https://graphhopper.com/api/1/matrix?key&#x3D;[YOUR_KEY]\&quot; -d &#x27;{\&quot;elevation\&quot;:false,\&quot;out_arrays\&quot;:[\&quot;weights\&quot;],\&quot;from_points\&quot;:[[-0.087891,51.534377],[-0.090637,51.467697],[-0.171833,51.521241],[-0.211487,51.473685]],\&quot;to_points\&quot;:[[-0.087891,51.534377],[-0.090637,51.467697],[-0.171833,51.521241],[-0.211487,51.473685]],\&quot;vehicle\&quot;:\&quot;car\&quot;}&#x27; &#x60;&#x60;&#x60; 
     * @param body  (optional)
     * @return MatrixResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public MatrixResponse postMatrix(Object body) throws ApiException {
        ApiResponse<MatrixResponse> resp = postMatrixWithHttpInfo(body);
        return resp.getData();
    }

    /**
     * Execute a matrix request
     * The GET request has an URL length limitation, which hurts for many locations per request. In those cases use a HTTP POST request with JSON data as input. The only parameter in the URL will be the key. Both request scenarios are identical except that all singular parameter names are named as their plural for a POST request. All effected parameters are: &#x60;points&#x60;, &#x60;from_points&#x60;, &#x60;to_points&#x60;, and &#x60;out_array&#x60;.  *Please note that in opposite to the GET endpoint, points are specified in &#x60;longitude, latitude&#x60;*.  For example &#x60;point&#x3D;10,11&amp;point&#x3D;20,22&#x60; will be converted to the following JSON &#x60;points&#x60; array: &#x60;&#x60;&#x60;json { \&quot;points\&quot;: [[11,10], [22,20]] } &#x60;&#x60;&#x60; Note that also the order changes to &#x60;[longitude,latitude]&#x60; similar to [GeoJson](http://geojson.org/geojson-spec.html#examples).  Example: &#x60;&#x60;&#x60;bash curl -X POST -H \&quot;Content-Type: application/json\&quot; \&quot;https://graphhopper.com/api/1/matrix?key&#x3D;[YOUR_KEY]\&quot; -d &#x27;{\&quot;elevation\&quot;:false,\&quot;out_arrays\&quot;:[\&quot;weights\&quot;],\&quot;from_points\&quot;:[[-0.087891,51.534377],[-0.090637,51.467697],[-0.171833,51.521241],[-0.211487,51.473685]],\&quot;to_points\&quot;:[[-0.087891,51.534377],[-0.090637,51.467697],[-0.171833,51.521241],[-0.211487,51.473685]],\&quot;vehicle\&quot;:\&quot;car\&quot;}&#x27; &#x60;&#x60;&#x60; 
     * @param body  (optional)
     * @return ApiResponse&lt;MatrixResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<MatrixResponse> postMatrixWithHttpInfo(Object body) throws ApiException {
        com.squareup.okhttp.Call call = postMatrixValidateBeforeCall(body, null, null);
        Type localVarReturnType = new TypeToken<MatrixResponse>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Execute a matrix request (asynchronously)
     * The GET request has an URL length limitation, which hurts for many locations per request. In those cases use a HTTP POST request with JSON data as input. The only parameter in the URL will be the key. Both request scenarios are identical except that all singular parameter names are named as their plural for a POST request. All effected parameters are: &#x60;points&#x60;, &#x60;from_points&#x60;, &#x60;to_points&#x60;, and &#x60;out_array&#x60;.  *Please note that in opposite to the GET endpoint, points are specified in &#x60;longitude, latitude&#x60;*.  For example &#x60;point&#x3D;10,11&amp;point&#x3D;20,22&#x60; will be converted to the following JSON &#x60;points&#x60; array: &#x60;&#x60;&#x60;json { \&quot;points\&quot;: [[11,10], [22,20]] } &#x60;&#x60;&#x60; Note that also the order changes to &#x60;[longitude,latitude]&#x60; similar to [GeoJson](http://geojson.org/geojson-spec.html#examples).  Example: &#x60;&#x60;&#x60;bash curl -X POST -H \&quot;Content-Type: application/json\&quot; \&quot;https://graphhopper.com/api/1/matrix?key&#x3D;[YOUR_KEY]\&quot; -d &#x27;{\&quot;elevation\&quot;:false,\&quot;out_arrays\&quot;:[\&quot;weights\&quot;],\&quot;from_points\&quot;:[[-0.087891,51.534377],[-0.090637,51.467697],[-0.171833,51.521241],[-0.211487,51.473685]],\&quot;to_points\&quot;:[[-0.087891,51.534377],[-0.090637,51.467697],[-0.171833,51.521241],[-0.211487,51.473685]],\&quot;vehicle\&quot;:\&quot;car\&quot;}&#x27; &#x60;&#x60;&#x60; 
     * @param body  (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call postMatrixAsync(Object body, final ApiCallback<MatrixResponse> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = postMatrixValidateBeforeCall(body, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<MatrixResponse>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
