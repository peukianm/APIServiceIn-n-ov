package api.innov.servlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import api.innov.util.StringToolbox;
import api.innov.util.SystemParameters;

/**
 * @author Michalis Pefkianakis
 *
 */

public class BootstrapServlet extends HttpServlet {

	// private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static String SYSTEM_PROPERTIES_FILE = new String();

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
     
		SYSTEM_PROPERTIES_FILE = StringToolbox.replaceAll(this.getServletContext().getRealPath(""), "\\", "/")
				+ "config/system.properties";
      
		// Initiliazing Propertyfile
		SystemParameters.getInstance(SYSTEM_PROPERTIES_FILE, this.getServletContext().getRealPath(""), true);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
