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
import api.innov.client.graphhopper.model.Stop;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Shipment
 */


public class Shipment {
  @SerializedName("id")
  private String id = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("priority")
  private Integer priority = 2;

  @SerializedName("pickup")
  private Stop pickup = null;

  @SerializedName("delivery")
  private Stop delivery = null;

  @SerializedName("size")
  private List<Integer> size = null;

  @SerializedName("required_skills")
  private List<String> requiredSkills = null;

  @SerializedName("allowed_vehicles")
  private List<String> allowedVehicles = null;

  @SerializedName("disallowed_vehicles")
  private List<String> disallowedVehicles = null;

  @SerializedName("max_time_in_vehicle")
  private Long maxTimeInVehicle = null;

  public Shipment id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Specifies the id of the shipment. Ids need to be unique so there must not be two services/shipments with the same id.
   * @return id
  **/
  @Schema(example = "7fe77504-7df8-4497-843c-02d70b6490ce", required = true, description = "Specifies the id of the shipment. Ids need to be unique so there must not be two services/shipments with the same id.")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Shipment name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Meaningful name for shipment, e.g. \&quot;pickup and deliver pizza to Peter\&quot;.
   * @return name
  **/
  @Schema(example = "pickup and deliver pizza to Peter", description = "Meaningful name for shipment, e.g. \"pickup and deliver pizza to Peter\".")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Shipment priority(Integer priority) {
    this.priority = priority;
    return this;
  }

   /**
   * Specifies the priority. Can be 1 &#x3D; high priority to 10 &#x3D; low priority. Often there are more services/shipments than the available vehicle fleet can handle. Then you could assign priorities to differentiate high priority tasks from those that can be served later or omitted at all.
   * @return priority
  **/
  @Schema(example = "1", description = "Specifies the priority. Can be 1 = high priority to 10 = low priority. Often there are more services/shipments than the available vehicle fleet can handle. Then you could assign priorities to differentiate high priority tasks from those that can be served later or omitted at all.")
  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public Shipment pickup(Stop pickup) {
    this.pickup = pickup;
    return this;
  }

   /**
   * Get pickup
   * @return pickup
  **/
  @Schema(required = true, description = "")
  public Stop getPickup() {
    return pickup;
  }

  public void setPickup(Stop pickup) {
    this.pickup = pickup;
  }

  public Shipment delivery(Stop delivery) {
    this.delivery = delivery;
    return this;
  }

   /**
   * Get delivery
   * @return delivery
  **/
  @Schema(required = true, description = "")
  public Stop getDelivery() {
    return delivery;
  }

  public void setDelivery(Stop delivery) {
    this.delivery = delivery;
  }

  public Shipment size(List<Integer> size) {
    this.size = size;
    return this;
  }

  public Shipment addSizeItem(Integer sizeItem) {
    if (this.size == null) {
      this.size = new ArrayList<Integer>();
    }
    this.size.add(sizeItem);
    return this;
  }

   /**
   * Size can have multiple dimensions and should be in line with the capacity dimension array of the vehicle type. For example, if the item that needs to be delivered has two size dimension, volume and weight, then specify it as follow [ 20, 5 ] assuming a volume of 20 and a weight of 5.
   * @return size
  **/
  @Schema(example = "[3]", description = "Size can have multiple dimensions and should be in line with the capacity dimension array of the vehicle type. For example, if the item that needs to be delivered has two size dimension, volume and weight, then specify it as follow [ 20, 5 ] assuming a volume of 20 and a weight of 5.")
  public List<Integer> getSize() {
    return size;
  }

  public void setSize(List<Integer> size) {
    this.size = size;
  }

  public Shipment requiredSkills(List<String> requiredSkills) {
    this.requiredSkills = requiredSkills;
    return this;
  }

  public Shipment addRequiredSkillsItem(String requiredSkillsItem) {
    if (this.requiredSkills == null) {
      this.requiredSkills = new ArrayList<String>();
    }
    this.requiredSkills.add(requiredSkillsItem);
    return this;
  }

   /**
   * Specifies an array of required skills, i.e. array of string (not case sensitive). For example, if this shipment needs to be conducted by a technician having a &#x60;drilling_machine&#x60; and a &#x60;screw_driver&#x60; then specify the array as follows: &#x60;[\&quot;drilling_machine\&quot;,\&quot;screw_driver\&quot;]&#x60;. This means that the service can only be done by a vehicle (technician) that has the skills &#x60;drilling_machine&#x60; AND &#x60;screw_driver&#x60; in its skill array. Otherwise it remains unassigned.
   * @return requiredSkills
  **/
  @Schema(example = "[\"drilling_machine\",\"screw_driver\"]", description = "Specifies an array of required skills, i.e. array of string (not case sensitive). For example, if this shipment needs to be conducted by a technician having a `drilling_machine` and a `screw_driver` then specify the array as follows: `[\"drilling_machine\",\"screw_driver\"]`. This means that the service can only be done by a vehicle (technician) that has the skills `drilling_machine` AND `screw_driver` in its skill array. Otherwise it remains unassigned.")
  public List<String> getRequiredSkills() {
    return requiredSkills;
  }

  public void setRequiredSkills(List<String> requiredSkills) {
    this.requiredSkills = requiredSkills;
  }

  public Shipment allowedVehicles(List<String> allowedVehicles) {
    this.allowedVehicles = allowedVehicles;
    return this;
  }

  public Shipment addAllowedVehiclesItem(String allowedVehiclesItem) {
    if (this.allowedVehicles == null) {
      this.allowedVehicles = new ArrayList<String>();
    }
    this.allowedVehicles.add(allowedVehiclesItem);
    return this;
  }

   /**
   * Specifies an array of allowed vehicles, i.e. array of vehicle ids. For example, if this shipment can only be conducted EITHER by \&quot;technician_peter\&quot; OR \&quot;technician_stefan\&quot; specify this as follows: [\&quot;technician_peter\&quot;,\&quot;technician_stefan\&quot;].
   * @return allowedVehicles
  **/
  @Schema(example = "[\"technician_peter\",\"technician_stefan\"]", description = "Specifies an array of allowed vehicles, i.e. array of vehicle ids. For example, if this shipment can only be conducted EITHER by \"technician_peter\" OR \"technician_stefan\" specify this as follows: [\"technician_peter\",\"technician_stefan\"].")
  public List<String> getAllowedVehicles() {
    return allowedVehicles;
  }

  public void setAllowedVehicles(List<String> allowedVehicles) {
    this.allowedVehicles = allowedVehicles;
  }

  public Shipment disallowedVehicles(List<String> disallowedVehicles) {
    this.disallowedVehicles = disallowedVehicles;
    return this;
  }

  public Shipment addDisallowedVehiclesItem(String disallowedVehiclesItem) {
    if (this.disallowedVehicles == null) {
      this.disallowedVehicles = new ArrayList<String>();
    }
    this.disallowedVehicles.add(disallowedVehiclesItem);
    return this;
  }

   /**
   * Specifies an array of disallowed vehicles, i.e. array of vehicle ids.
   * @return disallowedVehicles
  **/
  @Schema(example = "[\"driver-A\",\"driver-B\"]", description = "Specifies an array of disallowed vehicles, i.e. array of vehicle ids.")
  public List<String> getDisallowedVehicles() {
    return disallowedVehicles;
  }

  public void setDisallowedVehicles(List<String> disallowedVehicles) {
    this.disallowedVehicles = disallowedVehicles;
  }

  public Shipment maxTimeInVehicle(Long maxTimeInVehicle) {
    this.maxTimeInVehicle = maxTimeInVehicle;
    return this;
  }

   /**
   * Specifies the maximum time in seconds a shipment can stay in the vehicle.
   * @return maxTimeInVehicle
  **/
  @Schema(example = "1800", description = "Specifies the maximum time in seconds a shipment can stay in the vehicle.")
  public Long getMaxTimeInVehicle() {
    return maxTimeInVehicle;
  }

  public void setMaxTimeInVehicle(Long maxTimeInVehicle) {
    this.maxTimeInVehicle = maxTimeInVehicle;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Shipment shipment = (Shipment) o;
    return Objects.equals(this.id, shipment.id) &&
        Objects.equals(this.name, shipment.name) &&
        Objects.equals(this.priority, shipment.priority) &&
        Objects.equals(this.pickup, shipment.pickup) &&
        Objects.equals(this.delivery, shipment.delivery) &&
        Objects.equals(this.size, shipment.size) &&
        Objects.equals(this.requiredSkills, shipment.requiredSkills) &&
        Objects.equals(this.allowedVehicles, shipment.allowedVehicles) &&
        Objects.equals(this.disallowedVehicles, shipment.disallowedVehicles) &&
        Objects.equals(this.maxTimeInVehicle, shipment.maxTimeInVehicle);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, priority, pickup, delivery, size, requiredSkills, allowedVehicles, disallowedVehicles, maxTimeInVehicle);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Shipment {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
    sb.append("    pickup: ").append(toIndentedString(pickup)).append("\n");
    sb.append("    delivery: ").append(toIndentedString(delivery)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("    requiredSkills: ").append(toIndentedString(requiredSkills)).append("\n");
    sb.append("    allowedVehicles: ").append(toIndentedString(allowedVehicles)).append("\n");
    sb.append("    disallowedVehicles: ").append(toIndentedString(disallowedVehicles)).append("\n");
    sb.append("    maxTimeInVehicle: ").append(toIndentedString(maxTimeInVehicle)).append("\n");
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
