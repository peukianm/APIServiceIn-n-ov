package api.innov.support;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;


import api.innov.client.graphhopper.api.RouteOptimizationApi;
import api.innov.client.graphhopper.util.GHApiUtil;
import okhttp3.*;

public class TestFunct {

	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static Response outpResponse;
	
	public static void main(String[] args) {

		String credentials = "{\r\n" + "	\"username\": \"admin\",\r\n" + "	\"password\": \"123456\"\r\n" + "}";
		String optrouteparams = "{\r\n" + 
				"  \"vehicles\" : [\r\n" + 
				"   {\r\n" + 
				"     \"vehicle_id\": \"my_vehicle\",\r\n" + 
				"     \"start_address\": {\r\n" + 
				"         \"location_id\": \"berlin\",\r\n" + 
				"         \"lon\": 13.406,\r\n" + 
				"         \"lat\": 52.537\r\n" + 
				"     },\r\n" + 
				"     \"type_id\": \"my_bike\"\r\n" + 
				"   }\r\n" + 
				"],\r\n" + 
				"  \"vehicle_types\" : [\r\n" + 
				"    {\r\n" + 
				"      \"type_id\" : \"my_bike\",\r\n" + 
				"      \"profile\" : \"bike\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"services\" : [\r\n" + 
				"   {\r\n" + 
				"     \"id\": \"hamburg\",\r\n" + 
				"     \"name\": \"visit_hamburg\",\r\n" + 
				"     \"address\": {\r\n" + 
				"       \"location_id\": \"hamburg\",\r\n" + 
				"       \"lon\": 9.999,\r\n" + 
				"       \"lat\": 53.552\r\n" + 
				"     }\r\n" + 
				"   },\r\n" + 
				"   {\r\n" + 
				"     \"id\": \"munich\",\r\n" + 
				"     \"name\": \"visit_munich\",\r\n" + 
				"     \"address\": {\r\n" + 
				"       \"location_id\": \"munich\",\r\n" + 
				"       \"lon\": 11.570,\r\n" + 
				"       \"lat\": 48.145\r\n" + 
				"     }\r\n" + 
				"   },\r\n" + 
				"   {\r\n" + 
				"     \"id\": \"cologne\",\r\n" + 
				"     \"name\": \"visit_cologne\",\r\n" + 
				"     \"address\": {\r\n" + 
				"       \"location_id\": \"cologne\",\r\n" + 
				"       \"lon\": 6.957,\r\n" + 
				"       \"lat\": 50.936\r\n" + 
				"     }\r\n" + 
				"   },\r\n" + 
				"   {\r\n" + 
				"     \"id\": \"frankfurt\",\r\n" + 
				"     \"name\": \"visit_frankfurt\",\r\n" + 
				"     \"address\": {\r\n" + 
				"       \"location_id\": \"frankfurt\",\r\n" + 
				"       \"lon\": 8.670,\r\n" + 
				"       \"lat\": 50.109\r\n" + 
				"     }\r\n" + 
				"   }\r\n" + 
				"]\r\n" +  
				"}";
		

		//RequestBody body = RequestBody.create(JSON, optrouteparams);
		
		RequestBody body = RequestBody.create(
				optrouteparams,
                MediaType.parse("application/json; charset=utf-8")
        );
		
		
		final OkHttpClient client = new OkHttpClient.Builder()
				.connectTimeout(120, TimeUnit.SECONDS)
				.writeTimeout(120, TimeUnit.SECONDS)
				.readTimeout(120, TimeUnit.SECONDS)
				.build();
				
			
        
		
		final Request request = new Request.Builder()
		        
				// .url("http://localhost:8080/APIServiceInnov/GH/routing")
				//.url("http://localhost:8080/APIServiceInnov/authenticate")
				.url("http://localhost:8080/APIServiceInnov/GH/optroute")
				//.url("https://graphhopper.com/api/1/vrp/optimize?key=403448b0-df50-4d00-873c-b63a4479c313")
				// .header("Accept", "application/json")
				// .header("Content-Type", "application/json")
				 .addHeader("Authorization",
				 "eyJJUCI6IjA6MDowOjA6MDowOjA6MSIsImFsZyI6IkhTMjU2In0.eyJqdGkiOiIxIiwiaWF0IjoxNTc5Njc2MjQzLCJzdWIiOiJBUElJTk5PViIsImlzcyI6ImFkbWluIiwiSVAiOiIwOjA6MDowOjA6MDowOjEiLCJleHAiOjE1ODAwMzYyNDN9.iK8fC9acdcCSGKFhpCxFbOmHi9-isCx65reTpe4LZjY")
				// .addHeader("message","point=51.131,12.414&point=48.224,3.867&vehicle=car&locale=de&calc_points=false")
				.post(body)
				// .get()
				.build();

		
		
		try {
			System.out.println("Before Client CalL");

			// Future
			CallbackFuture future = new CallbackFuture();
			client.newCall(request).enqueue(future);
			outpResponse = future.get();
			//System.out.println("!!!!!!!!! response=" + outpResponse.body().string() + outpResponse.code());
			String jsonData = outpResponse.body().string();
		    JSONObject Jobject = new JSONObject(jsonData);
		    String job_id = (String) Jobject.get("job_id"); 
			System.out.println("job_id="+job_id);
//			String URL1 = "https://graphhopper.com/api/1/vrp/solution/"+job_id+"?key=403448b0-df50-4d00-873c-b63a4479c313";
//			System.out.println(URL1);
//			
//			api.innov.client.graphhopper.model.Response rsp;
//			RouteOptimizationApi vrpApi = new RouteOptimizationApi();
//			vrpApi.setApiClient(GHApiUtil.createClient());
//			while (true) {
//	            rsp = vrpApi.getSolution(job_id);
//	            if (rsp.getStatus().equals(api.innov.client.graphhopper.model.Response.StatusEnum.FINISHED)) {
//	                break;
//	            }
//	            Thread.sleep(200);
//	        }
//	        System.out.println(rsp);
			
			

			//Asynchronous call
//			client.newCall(request).enqueue(new Callback() {
//				
//				@Override
//				public void onFailure(Request call, IOException e) {
//					e.printStackTrace();
//				}
//
//				@Override
//				public void onResponse(Response response) throws IOException {
//					try (ResponseBody responseBody = response.body()) {
//						if (!response.isSuccessful())
//							throw new IOException("Unexpected code " + response);
//
//						outpResponse = response;
//						System.out.println("inside onResponse="+outpResponse.body().string() );
//						Headers responseHeaders = response.headers();
//						for (int i = 0, size = responseHeaders.size(); i < size; i++) {
//							System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
//						}
//
//						System.out.println(responseBody.string());
//					}
//				}
//			});
//			
//			System.out.println("end="+outpResponse.body().string() );

		} //catch (InterruptedException | ExecutionException | IOException e) {
			catch (Exception e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

class CallbackFuture extends CompletableFuture<Response> implements Callback {

	@Override
	public void onResponse(Call call, Response response) {
		System.out.println("ON RESPONSE");
		super.complete(response);
	}

	@Override
	public void onFailure(Call call, IOException e) {
		System.out.println("ON FAILURE");
		super.completeExceptionally(e);
	}

	
	public void onFailure(Request arg0, IOException arg1) {
		System.out.println("ON FAILURE OVERIDE");
		arg1.printStackTrace();
	}

	
	public void onResponse(Response response) throws IOException {
		System.out.println("ON RESPONSE OVERRIDE");
		super.complete(response);
	}

}
