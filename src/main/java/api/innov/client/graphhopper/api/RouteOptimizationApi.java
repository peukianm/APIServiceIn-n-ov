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


import api.innov.client.graphhopper.model.BadRequest;
import api.innov.client.graphhopper.model.InlineResponse404;
import api.innov.client.graphhopper.model.InternalErrorMessage;
import api.innov.client.graphhopper.model.JobId;
import api.innov.client.graphhopper.model.Request;
import api.innov.client.graphhopper.model.Response;
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

public class RouteOptimizationApi {
    private ApiClient apiClient;

    public RouteOptimizationApi() {
        this(Configuration.getDefaultApiClient());
    }

    public RouteOptimizationApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Build call for getSolution
     * @param jobId Request solution with jobId (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getSolutionCall(String jobId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/vrp/solution/{jobId}"
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
    private com.squareup.okhttp.Call getSolutionValidateBeforeCall(String jobId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        // verify the required parameter 'jobId' is set
        if (jobId == null) {
            throw new ApiException("Missing the required parameter 'jobId' when calling getSolution(Async)");
        }
        
        com.squareup.okhttp.Call call = getSolutionCall(jobId, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Retrieve solution
     * This endpoint returns the solution of posted vehicle routing or traveling salesman problems. You can fetch it with the job_id, you have been sent. 
     * @param jobId Request solution with jobId (required)
     * @return Response
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public Response getSolution(String jobId) throws ApiException {
        ApiResponse<Response> resp = getSolutionWithHttpInfo(jobId);
        return resp.getData();
    }

    /**
     * Retrieve solution
     * This endpoint returns the solution of posted vehicle routing or traveling salesman problems. You can fetch it with the job_id, you have been sent. 
     * @param jobId Request solution with jobId (required)
     * @return ApiResponse&lt;Response&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Response> getSolutionWithHttpInfo(String jobId) throws ApiException {
        com.squareup.okhttp.Call call = getSolutionValidateBeforeCall(jobId, null, null);
        Type localVarReturnType = new TypeToken<Response>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Retrieve solution (asynchronously)
     * This endpoint returns the solution of posted vehicle routing or traveling salesman problems. You can fetch it with the job_id, you have been sent. 
     * @param jobId Request solution with jobId (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getSolutionAsync(String jobId, final ApiCallback<Response> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = getSolutionValidateBeforeCall(jobId, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<Response>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for solveVRP
     * @param body Request object that contains the problem to be solved (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call solveVRPCall(Request body, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = body;
        
        // create path and map variables
        String localVarPath = "/vrp/optimize";

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
    private com.squareup.okhttp.Call solveVRPValidateBeforeCall(Request body, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new ApiException("Missing the required parameter 'body' when calling solveVRP(Async)");
        }
        
        com.squareup.okhttp.Call call = solveVRPCall(body, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Solve vehicle routing problem
     *  ### Getting Started  This tutorial - [Getting Started with the Optimization API](https://www.graphhopper.com/blog/2019/05/17/getting-started-with-the-optimization-api-traveling-salesman-problem/) - illustrates the very basic steps and building blocks to model your vehicle routing problem.  This only shows you an excerpt of what is possible with our API. You can find the entire specification of the required request (Request Body Schema) as well as the response below. It is an interactive documentation of nested objects. By default, you only see the root objects such as \&quot;vehicles\&quot;, \&quot;vehicle_types\&quot;, \&quot;objectives\&quot; etc.. To unfold detailed information about each root object, you just need to click on it as shown here  ![Nested Doc](./img/nested-doc.gif)  To explore the specification, we can recommend you to extend the problem in the tutorial above and either use our [route editor](https://www.graphhopper.com/blog/2015/07/21/graphhoppers-new-route-optimization-editor/) in the Directions API dashboard or the REST clients such as Insomnia or Postman as described [here](https://docs.graphhopper.com/#section/Explore-our-APIs). Additionally, you can find a ready-to-use example in the Request Samples Panel on the right (or below). You can easily unfold it by clicking the + or you can just copy it all and paste it to our route editor or your REST client.  Furthermore, we provide [a number of tutorials](https://www.graphhopper.com/tutorial/) illustrating how to use this API and how to model your vehicle routing problems. Here, you can find a list of tutorials that might help you to get started quickly:  - [Getting Start with the Optimization API - Traveling Salesman Problem](https://www.graphhopper.com/blog/2019/05/17/getting-started-with-the-optimization-api-traveling-salesman-problem/) - [How to solve a traveling salesman problem with a week-planning horizon?](https://www.graphhopper.com/blog/2016/05/30/how-to-solve-a-traveling-salesman-problem-with-a-week-planning-horizon/) - [How to schedule technicians with skills and multiple dependencies between tasks?](https://www.graphhopper.com/blog/2016/06/03/how-to-route-technicians-with-skills-and-multiple-dependencies-between-tasks/) - [What is the difference between the min. of completion time and min. transport time?](https://www.graphhopper.com/blog/2016/06/20/what-is-the-difference-between-the-minimization-of-completion-time-and-minimizing-transport-time/) - [How to model multiple delivery routes with a single vehicle?](https://www.graphhopper.com/blog/2016/07/21/how-to-model-multiple-delivery-routes-with-a-single-vehicle/) 
     * @param body Request object that contains the problem to be solved (required)
     * @return JobId
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public JobId solveVRP(Request body) throws ApiException {
        ApiResponse<JobId> resp = solveVRPWithHttpInfo(body);
        return resp.getData();
    }

    /**
     * Solve vehicle routing problem
     *  ### Getting Started  This tutorial - [Getting Started with the Optimization API](https://www.graphhopper.com/blog/2019/05/17/getting-started-with-the-optimization-api-traveling-salesman-problem/) - illustrates the very basic steps and building blocks to model your vehicle routing problem.  This only shows you an excerpt of what is possible with our API. You can find the entire specification of the required request (Request Body Schema) as well as the response below. It is an interactive documentation of nested objects. By default, you only see the root objects such as \&quot;vehicles\&quot;, \&quot;vehicle_types\&quot;, \&quot;objectives\&quot; etc.. To unfold detailed information about each root object, you just need to click on it as shown here  ![Nested Doc](./img/nested-doc.gif)  To explore the specification, we can recommend you to extend the problem in the tutorial above and either use our [route editor](https://www.graphhopper.com/blog/2015/07/21/graphhoppers-new-route-optimization-editor/) in the Directions API dashboard or the REST clients such as Insomnia or Postman as described [here](https://docs.graphhopper.com/#section/Explore-our-APIs). Additionally, you can find a ready-to-use example in the Request Samples Panel on the right (or below). You can easily unfold it by clicking the + or you can just copy it all and paste it to our route editor or your REST client.  Furthermore, we provide [a number of tutorials](https://www.graphhopper.com/tutorial/) illustrating how to use this API and how to model your vehicle routing problems. Here, you can find a list of tutorials that might help you to get started quickly:  - [Getting Start with the Optimization API - Traveling Salesman Problem](https://www.graphhopper.com/blog/2019/05/17/getting-started-with-the-optimization-api-traveling-salesman-problem/) - [How to solve a traveling salesman problem with a week-planning horizon?](https://www.graphhopper.com/blog/2016/05/30/how-to-solve-a-traveling-salesman-problem-with-a-week-planning-horizon/) - [How to schedule technicians with skills and multiple dependencies between tasks?](https://www.graphhopper.com/blog/2016/06/03/how-to-route-technicians-with-skills-and-multiple-dependencies-between-tasks/) - [What is the difference between the min. of completion time and min. transport time?](https://www.graphhopper.com/blog/2016/06/20/what-is-the-difference-between-the-minimization-of-completion-time-and-minimizing-transport-time/) - [How to model multiple delivery routes with a single vehicle?](https://www.graphhopper.com/blog/2016/07/21/how-to-model-multiple-delivery-routes-with-a-single-vehicle/) 
     * @param body Request object that contains the problem to be solved (required)
     * @return ApiResponse&lt;JobId&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<JobId> solveVRPWithHttpInfo(Request body) throws ApiException {
        com.squareup.okhttp.Call call = solveVRPValidateBeforeCall(body, null, null);
        Type localVarReturnType = new TypeToken<JobId>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Solve vehicle routing problem (asynchronously)
     *  ### Getting Started  This tutorial - [Getting Started with the Optimization API](https://www.graphhopper.com/blog/2019/05/17/getting-started-with-the-optimization-api-traveling-salesman-problem/) - illustrates the very basic steps and building blocks to model your vehicle routing problem.  This only shows you an excerpt of what is possible with our API. You can find the entire specification of the required request (Request Body Schema) as well as the response below. It is an interactive documentation of nested objects. By default, you only see the root objects such as \&quot;vehicles\&quot;, \&quot;vehicle_types\&quot;, \&quot;objectives\&quot; etc.. To unfold detailed information about each root object, you just need to click on it as shown here  ![Nested Doc](./img/nested-doc.gif)  To explore the specification, we can recommend you to extend the problem in the tutorial above and either use our [route editor](https://www.graphhopper.com/blog/2015/07/21/graphhoppers-new-route-optimization-editor/) in the Directions API dashboard or the REST clients such as Insomnia or Postman as described [here](https://docs.graphhopper.com/#section/Explore-our-APIs). Additionally, you can find a ready-to-use example in the Request Samples Panel on the right (or below). You can easily unfold it by clicking the + or you can just copy it all and paste it to our route editor or your REST client.  Furthermore, we provide [a number of tutorials](https://www.graphhopper.com/tutorial/) illustrating how to use this API and how to model your vehicle routing problems. Here, you can find a list of tutorials that might help you to get started quickly:  - [Getting Start with the Optimization API - Traveling Salesman Problem](https://www.graphhopper.com/blog/2019/05/17/getting-started-with-the-optimization-api-traveling-salesman-problem/) - [How to solve a traveling salesman problem with a week-planning horizon?](https://www.graphhopper.com/blog/2016/05/30/how-to-solve-a-traveling-salesman-problem-with-a-week-planning-horizon/) - [How to schedule technicians with skills and multiple dependencies between tasks?](https://www.graphhopper.com/blog/2016/06/03/how-to-route-technicians-with-skills-and-multiple-dependencies-between-tasks/) - [What is the difference between the min. of completion time and min. transport time?](https://www.graphhopper.com/blog/2016/06/20/what-is-the-difference-between-the-minimization-of-completion-time-and-minimizing-transport-time/) - [How to model multiple delivery routes with a single vehicle?](https://www.graphhopper.com/blog/2016/07/21/how-to-model-multiple-delivery-routes-with-a-single-vehicle/) 
     * @param body Request object that contains the problem to be solved (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call solveVRPAsync(Request body, final ApiCallback<JobId> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = solveVRPValidateBeforeCall(body, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<JobId>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
