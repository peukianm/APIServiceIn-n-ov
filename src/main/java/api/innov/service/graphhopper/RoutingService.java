package api.innov.service.graphhopper;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.innov.common.APIServiceInnovCommon;

/**
 * @author Michalis Pefkianakis
 *
 */
@Path("/dd")
public class RoutingService {
	
	
	private static final Logger logger = LogManager.getLogger(RoutingService.class);

	@GET  
	@Path("/{message}")
	public Response getMsg(@PathParam("message") String msg, @HeaderParam("username") String username,
			@HeaderParam("password") String password, @HeaderParam("apikey") String apikey) {
		
		try {
			if(logger.isDebugEnabled()) logger.debug("java logging level is DEBUG Enabled");  
			String output = "Message requested : " + msg;
			// Simply return the parameter passed as message
			return Response.status(200).entity(output).build();
		} catch (Exception e) {
			e.printStackTrace();
			APIServiceInnovCommon.logServiceError(e, logger);
			return Response.status(500).entity("ERROR").build();
		}
	}
}
