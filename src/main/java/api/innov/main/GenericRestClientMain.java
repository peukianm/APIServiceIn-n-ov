package api.innov.main;

import java.util.Arrays;
import java.util.List;
import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
import api.innov.support.test.*;

public class GenericRestClientMain {
	
	public static void main(String[] args) {
		//Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8084/TestMavenJersey").path("employees");
	      
	    Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
	    Response response = invocationBuilder.get();
	      
	    Employees employees = response.readEntity(Employees.class);
	    List<Employee> listOfEmployees = employees.getEmployeeList();
	          
	    System.out.println(response.getCookies());
	    System.out.println(response.getStatus());
	    System.out.println(Arrays.toString( listOfEmployees.toArray(new Employee[listOfEmployees.size()]) ));

	}

}
