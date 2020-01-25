package api.innov.client.graphhopper;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import api.innov.client.graphhopper.api.RouteOptimizationApi;
import api.innov.client.graphhopper.util.GHApiUtil;
import api.innov.common.APIServiceInnovCommon;
import api.innov.util.CallbackFuture;
import api.innov.util.SystemParameters;
import okhttp3.*;

public class RouteGHOptimizationClient {

	private static final Logger logger = LogManager.getLogger(RouteGHOptimizationClient.class);
	private static String URL = "";
	private static final String SERVICEURL = SystemParameters.getInstance().getProperty("GHURL_OPTROUTE");

	private static Response outpResponse;
	private static String job_id = null;

	public static api.innov.client.graphhopper.model.Response run(String JSONDATA) throws Exception {
		RequestBody body = RequestBody.create(JSONDATA, MediaType.parse("application/json; charset=utf-8"));

		final OkHttpClient client = new OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS)
				.writeTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).build();

		URL = SERVICEURL + "&key=" + SystemParameters.getInstance().getProperty("API_KEY1");
		final Request request = new Request.Builder().url(URL).post(body).build();

		try {
			CallbackFuture future = new CallbackFuture();
			client.newCall(request).enqueue(future);
			outpResponse = future.get();

			String jsonData = outpResponse.body().string();
			JSONObject Jobject = new JSONObject(jsonData);
			job_id = (String) Jobject.get("job_id");

			api.innov.client.graphhopper.model.Response rsp;
			RouteOptimizationApi vrpApi = new RouteOptimizationApi();
			vrpApi.setApiClient(GHApiUtil.createClient());
			Thread.sleep(10000);
			while (true) {
				rsp = vrpApi.getSolution(job_id);
				if (rsp.getStatus().equals(api.innov.client.graphhopper.model.Response.StatusEnum.FINISHED)) {
					break;
				}
				Thread.sleep(200);
			}
			return rsp;
		} catch (Exception e) {
			e.printStackTrace();
			APIServiceInnovCommon.logServiceError(e, logger);
			return null;
		}
	}

	public static api.innov.client.graphhopper.model.Response getSolution(String jobID) throws Exception {
		try {
			api.innov.client.graphhopper.model.Response rsp;
			RouteOptimizationApi vrpApi = new RouteOptimizationApi();
			vrpApi.setApiClient(GHApiUtil.createClient());
			while (true) {
				rsp = vrpApi.getSolution(jobID);
				if (rsp.getStatus().equals(api.innov.client.graphhopper.model.Response.StatusEnum.FINISHED)) {
					break;
				}
				Thread.sleep(200);
			}
			return rsp;
		} catch (Exception e) {
			e.printStackTrace();
			APIServiceInnovCommon.logServiceError(e, logger);
			return null;
		}
	}

	public static api.innov.client.graphhopper.model.Response getSolutionForPooling(String jobID) throws Exception {
		try {
			api.innov.client.graphhopper.model.Response rsp;
			RouteOptimizationApi vrpApi = new RouteOptimizationApi();
			vrpApi.setApiClient(GHApiUtil.createClient());
			rsp = vrpApi.getSolution(jobID);
			return rsp;
		} catch (Exception e) {
			e.printStackTrace();
			APIServiceInnovCommon.logServiceError(e, logger);
			return null;
		}
	}

	public static String getJob(String JSONDATA) throws Exception {

		RequestBody body = RequestBody.create(JSONDATA, MediaType.parse("application/json; charset=utf-8"));
		final OkHttpClient client = new OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS)
				.writeTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).build();

		URL = SERVICEURL + "&key=" + SystemParameters.getInstance().getProperty("API_KEY1");
		final Request request = new Request.Builder().url(URL).post(body).build();

		try {
			CallbackFuture future = new CallbackFuture();
			client.newCall(request).enqueue(future);
			outpResponse = future.get();
			String jobID = outpResponse.body().string();
			return jobID;
		} catch (Exception e) {
			e.printStackTrace();
			APIServiceInnovCommon.logServiceError(e, logger);
			return null;
		}
	}

}
