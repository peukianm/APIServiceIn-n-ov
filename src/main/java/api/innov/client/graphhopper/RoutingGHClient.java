package api.innov.client.graphhopper;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.PathWrapper;
import com.graphhopper.api.GraphHopperWeb;
import com.graphhopper.util.InstructionList;
import com.graphhopper.util.PointList;
import com.graphhopper.util.shapes.GHPoint;

import api.innov.client.graphhopper.util.ApiClient;
import api.innov.client.graphhopper.util.Configuration;
import io.swagger.client.*;
import io.swagger.client.auth.*;
import io.swagger.client.model.*;
import okhttp3.OkHttpClient;
import io.swagger.client.api.*;

public class RoutingGHClient {
	
	public static void main(String[] args) {

		// SWAGGER!!!  run once thread safe
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		ApiKeyAuth api_key = (ApiKeyAuth) defaultClient.getAuthentication("api_key");
		api_key.setApiKey("403448b0-df50-4d00-873c-b63a4479c313");
		//"78da6e9a-273e-43d1-bdda-8f24e007a1fa"	
		
		
		
		// HC CLIENT!!!!
		GraphHopperWeb gh = new GraphHopperWeb();
		// insert your key here
		gh.setKey("403448b0-df50-4d00-873c-b63a4479c313");
		// change timeout, default is 5 seconds
		gh.setDownloader(new OkHttpClient.Builder().
		                connectTimeout(5, TimeUnit.SECONDS).
		                readTimeout(5, TimeUnit.SECONDS).build());
		
		
		
		
		
		// specify at least two coordinates
		GHRequest req = new GHRequest().
		   addPoint(new GHPoint(49.6724, 11.3494)).
		   addPoint(new GHPoint(49.6550, 11.4180));
		// Set vehicle like car, bike, foot, ...
		req.setVehicle("bike");
		// Optionally enable/disable elevation in output PointList, currently bike and foot support elevation, default is false
		req.getHints().put("elevation", false);
		// Optionally enable/disable turn instruction information, defaults is true
		req.getHints().put("instructions", true);
		// Optionally enable/disable path geometry information, default is true
		req.getHints().put("calc_points", true);
		// note: turn off instructions and calcPoints if you just need the distance or time 
		// information to make calculation and transmission faster
		//
		// Optionally set specific locale for instruction information, supports already over 25 languages,
		// defaults to English
		req.setLocale(Locale.GERMAN);

		GHResponse fullRes = gh.route(req);
		        
		if(fullRes.hasErrors()) {
		   // handle or throw exceptions res.getErrors()
		   return;
		}

		PathWrapper res = fullRes.getBest();
		// get path geometry information (latitude, longitude and optionally elevation)
		PointList pl = res.getPoints();
		// distance of the full path, in meter
		double distance = res.getDistance();
		// time of the full path, in milliseconds
		long millis = res.getTime();
		// get information per turn instruction
		InstructionList il = res.getInstructions();		
	}
	
	
	
	

}
