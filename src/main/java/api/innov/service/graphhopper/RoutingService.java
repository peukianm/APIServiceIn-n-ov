package api.innov.service.graphhopper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import api.innov.client.graphhopper.GeocodingGHClient;
import api.innov.client.graphhopper.RouteGHOptimizationClient;
import api.innov.client.graphhopper.RoutingGHClient;
import api.innov.common.APIServiceInnovCommon;
import api.innov.exception.InvalidRequestParamsException;
import api.innov.support.Credentials;
import api.innov.support.UserObject;
import api.innov.util.APIServiceInnovUtil;
import api.innov.util.SystemParameters;
import io.jsonwebtoken.Claims;


/**
 * @author Michalis Pefkianakis
 *
 */

@Path("/")
public class RoutingService {

	@Resource
	WebServiceContext webServiceContext;

	private static final Logger logger = LogManager.getLogger(RoutingService.class);

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/authenticate")
	public Response authenticateUser(@Context HttpServletRequest requestContext, Credentials credentials) {

		try {			
			// Authenticate the user using the credentials provided
			UserObject user = APIServiceInnovCommon.authenticate(credentials);
			if (user == null)
				return Response.status(Response.Status.FORBIDDEN).build();

			// Issue a token for the user
			//TODO Add specific User
			String jwt = APIServiceInnovUtil.createJWT("1", "admin", SystemParameters.getInstance().getProperty("JWT_SUBJECT"), Integer.parseInt(SystemParameters.getInstance().getProperty("JWT_DURATION")),
					requestContext.getLocalAddr());
			
			// Return the token on the response
			return Response.ok().entity("AUthentication success").cookie(new NewCookie("APIServiceInnovAuthCookie", jwt)).build();

		} catch (Exception e) {
			APIServiceInnovCommon.logServiceError(e, logger);
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}

	@GET
	@Path("/GH/routing")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response routingGH(@Context HttpServletRequest requestContext, @HeaderParam("message") String msg,
			@HeaderParam("Authorization") String jwtToken)  {
		try {			
			//Check Token validity
			if (jwtToken == null)
				return Response.status(Response.Status.FORBIDDEN).build();					
			try {
				//Check Sender IP
				Claims claims = APIServiceInnovUtil.decodeJWT(jwtToken);
				if (!claims.get("IP").equals(requestContext.getLocalAddr())) 
					throw new io.jsonwebtoken.IncorrectClaimException(null, claims, "Invalid Sender IP");
				if (msg != null) {					
					okhttp3.Response okResponse = RoutingGHClient.route(msg);										
					return Response.status(Response.Status.fromStatusCode(okResponse.code())).cookie(new NewCookie("APIServiceInnovAuthCookie", jwtToken)).
							entity(okResponse.body().string()).build();					
				} else 
					throw new InvalidRequestParamsException();
				
			} catch (io.jsonwebtoken.MalformedJwtException e) {
				System.out.println("INVALID TOKEN");
				return Response.status(Response.Status.FORBIDDEN).build();
			} catch (io.jsonwebtoken.ExpiredJwtException e) {
				System.out.println("ExpiredToken TOKEN");
				return Response.status(Response.Status.FORBIDDEN).build();
			} catch(io.jsonwebtoken.IncorrectClaimException e) {
				System.out.println("Invalid IP");
				return Response.status(Response.Status.FORBIDDEN).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			APIServiceInnovCommon.logServiceError(e, logger);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("AN Error happend during servicing routing request").build();
		}
	}
 
	
	@POST
	@Path("/GH/optroute")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response optRouteDataSolution(@Context HttpServletRequest requestContext, String msg,
			@HeaderParam("Authorization") String jwtToken) {
		try {			
			if (jwtToken == null)
				return Response.status(Response.Status.FORBIDDEN).build();					
			try { 
				Claims claims = APIServiceInnovUtil.decodeJWT(jwtToken);
				if (!claims.get("IP").equals(requestContext.getLocalAddr()))
					throw new io.jsonwebtoken.IncorrectClaimException(null, claims, "Invalid Sender IP");
				if (msg != null) {
					api.innov.client.graphhopper.model.Response ghr = RouteGHOptimizationClient.run(msg);
					if (ghr == null) throw new Exception();						
					return Response.status(Response.Status.OK).cookie(new NewCookie("APIServiceInnovAuthCookie", jwtToken)).entity(ghr).build();					
				} else {
					throw new InvalidRequestParamsException();
				}
			} catch (io.jsonwebtoken.MalformedJwtException e) {								
				return Response.status(Response.Status.FORBIDDEN).build();
			} catch (io.jsonwebtoken.ExpiredJwtException e) {				
				return Response.status(Response.Status.FORBIDDEN).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			APIServiceInnovCommon.logServiceError(e, logger);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("AN Error happend during servicing route optimization request").build();
		}
	}
	
	  
	@GET
	@Path("/GH/optrouteresult/{jobid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response optRouteJobSolution(@Context HttpServletRequest requestContext, @PathParam("jobid") String jobid,
			@HeaderParam("Authorization") String jwtToken) {
		try {			
			if (jwtToken == null)
				return Response.status(Response.Status.FORBIDDEN).build();					
			try { 
				Claims claims = APIServiceInnovUtil.decodeJWT(jwtToken);
				if (!claims.get("IP").equals(requestContext.getLocalAddr()))
					throw new io.jsonwebtoken.IncorrectClaimException(null, claims, "Invalid Sender IP");
				if (jobid != null) {
					api.innov.client.graphhopper.model.Response ghr = RouteGHOptimizationClient.getSolution(jobid);
					if (ghr == null) throw new Exception();						
					return Response.status(Response.Status.OK).cookie(new NewCookie("APIServiceInnovAuthCookie", jwtToken)).entity(ghr).build();					
				} else {
					throw new InvalidRequestParamsException();
				}
			} catch (io.jsonwebtoken.MalformedJwtException e) {
				System.out.println("INVALID TOKEN");
				return Response.status(Response.Status.FORBIDDEN).build();
			} catch (io.jsonwebtoken.ExpiredJwtException e) {
				System.out.println("ExpiredToken TOKEN");
				return Response.status(Response.Status.FORBIDDEN).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			APIServiceInnovCommon.logServiceError(e, logger);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("AN Error happend during servicing route optimization request").build();
		}
	}
	
	
	@GET
	@Path("/GH/optroutecheck/{jobid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response optRouteJobCheck(@Context HttpServletRequest requestContext, @PathParam("jobid") String jobid,
			@HeaderParam("Authorization") String jwtToken) {
		try {			
			if (jwtToken == null)
				return Response.status(Response.Status.FORBIDDEN).build();					
			try { 
				Claims claims = APIServiceInnovUtil.decodeJWT(jwtToken);
				if (!claims.get("IP").equals(requestContext.getLocalAddr()))
					throw new io.jsonwebtoken.IncorrectClaimException(null, claims, "Invalid Sender IP");
				if (jobid != null) {
					api.innov.client.graphhopper.model.Response ghr = RouteGHOptimizationClient.getSolutionForPooling(jobid);
					if (ghr == null) throw new Exception();
					if (ghr.getStatus().equals(api.innov.client.graphhopper.model.Response.StatusEnum.FINISHED))  {
						return Response.status(Response.Status.OK).cookie(new NewCookie("APIServiceInnovAuthCookie", jwtToken)).entity(ghr).build();
					} else {
						JSONObject stat = new JSONObject();
						stat.put("job_id",ghr.getJobId());
						stat.put("status", ghr.getStatus()); 						
						return Response.status(Response.Status.OK).cookie(new NewCookie("APIServiceInnovAuthCookie", jwtToken)).entity(stat).build();
					}
				} else {
					throw new InvalidRequestParamsException();
				}
			} catch (io.jsonwebtoken.MalformedJwtException e) {
				System.out.println("INVALID TOKEN");
				return Response.status(Response.Status.FORBIDDEN).build();
			} catch (io.jsonwebtoken.ExpiredJwtException e) {
				System.out.println("ExpiredToken TOKEN");
				return Response.status(Response.Status.FORBIDDEN).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			APIServiceInnovCommon.logServiceError(e, logger);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("AN Error happend during servicing route optimization request").build();
		}
	}
	
	
	@POST
	@Path("/GH/optroutejob")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response optRouteDataJob(@Context HttpServletRequest requestContext, String msg,
			@HeaderParam("Authorization") String jwtToken) {
		try {			
			if (jwtToken == null)
				return Response.status(Response.Status.FORBIDDEN).build();					
			try { 
				Claims claims = APIServiceInnovUtil.decodeJWT(jwtToken);
				if (!claims.get("IP").equals(requestContext.getLocalAddr()))
					throw new io.jsonwebtoken.IncorrectClaimException(null, claims, "Invalid Sender IP");
				if (msg != null) {
					String JSONJOB = RouteGHOptimizationClient.getJob(msg);
					if (JSONJOB == null) throw new Exception();						
					return Response.status(Response.Status.OK).cookie(new NewCookie("APIServiceInnovAuthCookie", jwtToken)).entity(JSONJOB).build();					
				} else {
					throw new InvalidRequestParamsException();
				}
			} catch (io.jsonwebtoken.MalformedJwtException e) {
				System.out.println("INVALID TOKEN");
				return Response.status(Response.Status.FORBIDDEN).build();
			} catch (io.jsonwebtoken.ExpiredJwtException e) {
				System.out.println("Expired TOKEN");
				return Response.status(Response.Status.FORBIDDEN).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			APIServiceInnovCommon.logServiceError(e, logger);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An Error happend during servicing route optimizition request").build();
		}
	}	
	
	@GET
	@Path("/GH/geocoding")
	@Produces(MediaType.APPLICATION_JSON)
	public Response geocoding(@Context HttpServletRequest requestContext, @HeaderParam("message") String msg,
			@HeaderParam("Authorization") String jwtToken) {
		try {			
			if (jwtToken == null)
				return Response.status(Response.Status.FORBIDDEN).build();					
			try { 
				Claims claims = APIServiceInnovUtil.decodeJWT(jwtToken);
				if (!claims.get("IP").equals(requestContext.getLocalAddr()))
					throw new io.jsonwebtoken.IncorrectClaimException(null, claims, "Invalid Sender IP");
				if (msg != null) {
					okhttp3.Response resp = GeocodingGHClient.geocode(msg);
					if (resp == null) throw new Exception();						
					return Response.status(Response.Status.fromStatusCode(resp.code())).cookie(new NewCookie("APIServiceInnovAuthCookie", jwtToken)).
							entity(resp.body().string()).build();	
				} else {
					throw new InvalidRequestParamsException();
				}
			} catch (io.jsonwebtoken.MalformedJwtException e) {
				System.out.println("INVALID TOKEN");
				return Response.status(Response.Status.FORBIDDEN).build();
			} catch (io.jsonwebtoken.ExpiredJwtException e) {
				System.out.println("ExpiredToken TOKEN");
				return Response.status(Response.Status.FORBIDDEN).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			APIServiceInnovCommon.logServiceError(e, logger);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An Error happend during geolocading request").build();
		}
	}
}


