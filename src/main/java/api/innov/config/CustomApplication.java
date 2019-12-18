package api.innov.config;

import java.util.*;


import javax.json.stream.*;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jsonp.*;
import org.glassfish.jersey.server.*;


/** 
 * Manual Register JSONP
 *
 */
//@ApplicationPath("/services")
public class CustomApplication extends ResourceConfig 
{
    public CustomApplication() 
    {   	
    	register(JsonProcessingFeature.class);
        packages("api.innov");
        packages("org.glassfish.jersey.examples.jsonp");        
        property(JsonGenerator.PRETTY_PRINTING, true);
        
        //Todo Register Logger
//        register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME), Level.INFO,
//				LoggingFeature.Verbosity.PAYLOAD_ANY, 10000));
		
		//Register Auth Filter here
        //register(AuthenticationFilter.class);
 
    }
//    public Set<Class<?>> getClasses() {
//        Set<Class<?>> s = new HashSet<Class<?>>();
//        s.add(ResourceA.class);
//        return s;
//   }
}
