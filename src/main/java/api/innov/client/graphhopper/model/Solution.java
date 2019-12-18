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

package api.innov.client.graphhopper.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import api.innov.client.graphhopper.model.Route;
import api.innov.client.graphhopper.model.SolutionUnassigned;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Only available if status field indicates &#x60;finished&#x60;.
 */
@Schema(description = "Only available if status field indicates `finished`.")

public class Solution {
  @SerializedName("costs")
  private Integer costs = null;

  @SerializedName("distance")
  private Integer distance = null;

  @SerializedName("time")
  private Long time = null;

  @SerializedName("transport_time")
  private Long transportTime = null;

  @SerializedName("max_operation_time")
  private Long maxOperationTime = null;

  @SerializedName("waiting_time")
  private Long waitingTime = null;

  @SerializedName("service_duration")
  private Long serviceDuration = null;

  @SerializedName("preparation_time")
  private Long preparationTime = null;

  @SerializedName("completion_time")
  private Long completionTime = null;

  @SerializedName("no_vehicles")
  private Integer noVehicles = null;

  @SerializedName("no_unassigned")
  private Integer noUnassigned = null;

  @SerializedName("routes")
  private List<Route> routes = null;

  @SerializedName("unassigned")
  private SolutionUnassigned unassigned = null;

  public Solution costs(Integer costs) {
    this.costs = costs;
    return this;
  }

   /**
   * Get costs
   * @return costs
  **/
  @Schema(description = "")
  public Integer getCosts() {
    return costs;
  }

  public void setCosts(Integer costs) {
    this.costs = costs;
  }

  public Solution distance(Integer distance) {
    this.distance = distance;
    return this;
  }

   /**
   * Overall distance travelled in meter, i.e. the sum of each route&#x27;s transport distance
   * @return distance
  **/
  @Schema(example = "1200", description = "Overall distance travelled in meter, i.e. the sum of each route's transport distance")
  public Integer getDistance() {
    return distance;
  }

  public void setDistance(Integer distance) {
    this.distance = distance;
  }

  public Solution time(Long time) {
    this.time = time;
    return this;
  }

   /**
   * Use &#x60;transport_time&#x60; instead.
   * @return time
  **/
  @Schema(description = "Use `transport_time` instead.")
  public Long getTime() {
    return time;
  }

  public void setTime(Long time) {
    this.time = time;
  }

  public Solution transportTime(Long transportTime) {
    this.transportTime = transportTime;
    return this;
  }

   /**
   * Overall time travelled in seconds, i.e. the sum of each route&#x27;s transport time.
   * @return transportTime
  **/
  @Schema(example = "12000", description = "Overall time travelled in seconds, i.e. the sum of each route's transport time.")
  public Long getTransportTime() {
    return transportTime;
  }

  public void setTransportTime(Long transportTime) {
    this.transportTime = transportTime;
  }

  public Solution maxOperationTime(Long maxOperationTime) {
    this.maxOperationTime = maxOperationTime;
    return this;
  }

   /**
   * Operation time of longest route in seconds.
   * @return maxOperationTime
  **/
  @Schema(example = "4000", description = "Operation time of longest route in seconds.")
  public Long getMaxOperationTime() {
    return maxOperationTime;
  }

  public void setMaxOperationTime(Long maxOperationTime) {
    this.maxOperationTime = maxOperationTime;
  }

  public Solution waitingTime(Long waitingTime) {
    this.waitingTime = waitingTime;
    return this;
  }

   /**
   * Overall waiting time in seconds.
   * @return waitingTime
  **/
  @Schema(example = "200", description = "Overall waiting time in seconds.")
  public Long getWaitingTime() {
    return waitingTime;
  }

  public void setWaitingTime(Long waitingTime) {
    this.waitingTime = waitingTime;
  }

  public Solution serviceDuration(Long serviceDuration) {
    this.serviceDuration = serviceDuration;
    return this;
  }

   /**
   * Overall service time in seconds.
   * @return serviceDuration
  **/
  @Schema(example = "1200", description = "Overall service time in seconds.")
  public Long getServiceDuration() {
    return serviceDuration;
  }

  public void setServiceDuration(Long serviceDuration) {
    this.serviceDuration = serviceDuration;
  }

  public Solution preparationTime(Long preparationTime) {
    this.preparationTime = preparationTime;
    return this;
  }

   /**
   * Overall preparation time in seconds.
   * @return preparationTime
  **/
  @Schema(description = "Overall preparation time in seconds.")
  public Long getPreparationTime() {
    return preparationTime;
  }

  public void setPreparationTime(Long preparationTime) {
    this.preparationTime = preparationTime;
  }

  public Solution completionTime(Long completionTime) {
    this.completionTime = completionTime;
    return this;
  }

   /**
   * Overall completion time in seconds, i.e. the sum of each routes/drivers operation time.
   * @return completionTime
  **/
  @Schema(example = "12000", description = "Overall completion time in seconds, i.e. the sum of each routes/drivers operation time.")
  public Long getCompletionTime() {
    return completionTime;
  }

  public void setCompletionTime(Long completionTime) {
    this.completionTime = completionTime;
  }

  public Solution noVehicles(Integer noVehicles) {
    this.noVehicles = noVehicles;
    return this;
  }

   /**
   * Number of employed vehicles.
   * @return noVehicles
  **/
  @Schema(example = "10", description = "Number of employed vehicles.")
  public Integer getNoVehicles() {
    return noVehicles;
  }

  public void setNoVehicles(Integer noVehicles) {
    this.noVehicles = noVehicles;
  }

  public Solution noUnassigned(Integer noUnassigned) {
    this.noUnassigned = noUnassigned;
    return this;
  }

   /**
   * Number of jobs that could not be assigned to final solution.
   * @return noUnassigned
  **/
  @Schema(example = "1", description = "Number of jobs that could not be assigned to final solution.")
  public Integer getNoUnassigned() {
    return noUnassigned;
  }

  public void setNoUnassigned(Integer noUnassigned) {
    this.noUnassigned = noUnassigned;
  }

  public Solution routes(List<Route> routes) {
    this.routes = routes;
    return this;
  }

  public Solution addRoutesItem(Route routesItem) {
    if (this.routes == null) {
      this.routes = new ArrayList<Route>();
    }
    this.routes.add(routesItem);
    return this;
  }

   /**
   * An array of routes
   * @return routes
  **/
  @Schema(description = "An array of routes")
  public List<Route> getRoutes() {
    return routes;
  }

  public void setRoutes(List<Route> routes) {
    this.routes = routes;
  }

  public Solution unassigned(SolutionUnassigned unassigned) {
    this.unassigned = unassigned;
    return this;
  }

   /**
   * Get unassigned
   * @return unassigned
  **/
  @Schema(description = "")
  public SolutionUnassigned getUnassigned() {
    return unassigned;
  }

  public void setUnassigned(SolutionUnassigned unassigned) {
    this.unassigned = unassigned;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Solution solution = (Solution) o;
    return Objects.equals(this.costs, solution.costs) &&
        Objects.equals(this.distance, solution.distance) &&
        Objects.equals(this.time, solution.time) &&
        Objects.equals(this.transportTime, solution.transportTime) &&
        Objects.equals(this.maxOperationTime, solution.maxOperationTime) &&
        Objects.equals(this.waitingTime, solution.waitingTime) &&
        Objects.equals(this.serviceDuration, solution.serviceDuration) &&
        Objects.equals(this.preparationTime, solution.preparationTime) &&
        Objects.equals(this.completionTime, solution.completionTime) &&
        Objects.equals(this.noVehicles, solution.noVehicles) &&
        Objects.equals(this.noUnassigned, solution.noUnassigned) &&
        Objects.equals(this.routes, solution.routes) &&
        Objects.equals(this.unassigned, solution.unassigned);
  }

  @Override
  public int hashCode() {
    return Objects.hash(costs, distance, time, transportTime, maxOperationTime, waitingTime, serviceDuration, preparationTime, completionTime, noVehicles, noUnassigned, routes, unassigned);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Solution {\n");
    
    sb.append("    costs: ").append(toIndentedString(costs)).append("\n");
    sb.append("    distance: ").append(toIndentedString(distance)).append("\n");
    sb.append("    time: ").append(toIndentedString(time)).append("\n");
    sb.append("    transportTime: ").append(toIndentedString(transportTime)).append("\n");
    sb.append("    maxOperationTime: ").append(toIndentedString(maxOperationTime)).append("\n");
    sb.append("    waitingTime: ").append(toIndentedString(waitingTime)).append("\n");
    sb.append("    serviceDuration: ").append(toIndentedString(serviceDuration)).append("\n");
    sb.append("    preparationTime: ").append(toIndentedString(preparationTime)).append("\n");
    sb.append("    completionTime: ").append(toIndentedString(completionTime)).append("\n");
    sb.append("    noVehicles: ").append(toIndentedString(noVehicles)).append("\n");
    sb.append("    noUnassigned: ").append(toIndentedString(noUnassigned)).append("\n");
    sb.append("    routes: ").append(toIndentedString(routes)).append("\n");
    sb.append("    unassigned: ").append(toIndentedString(unassigned)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
