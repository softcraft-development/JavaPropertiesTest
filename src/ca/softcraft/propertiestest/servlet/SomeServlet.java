package ca.softcraft.propertiestest.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SomeServlet extends HttpServlet {

		/**
	 * 
	 */
	private static final long serialVersionUID = -7384978447296979960L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setStatus(200);
		response.getWriter().println("This is SomeServlet: " + new Date());
//		ServletContext context = getServletContext();
//		InputStream resourceAsStream = context.getResourceAsStream("/WEB-INF/test.properties");
		InputStream resourceAsStream = getClass().getResourceAsStream("/ca/softcraft/propertiestest/packaged.properties");
		Properties properties = new Properties();
		properties.load(resourceAsStream);
		response.getWriter().println("The value of myproperty is: " + properties.getProperty("myproperty"));
	}

	@SuppressWarnings("unused")
	private void serveUpAnyFile(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			MalformedURLException {
		response.setContentType("text/plain");
		response.setStatus(200);
		response.getWriter().println("This is SomeServlet: " + new Date());
		
		response.getWriter().println("PathInfo: " + request.getPathInfo());
		if (request.getPathInfo() != null){
			ServletContext context = getServletContext();

			URL url = context.getResource(request.getPathInfo());
			response.getWriter().println("URL: " + url);
			
			try {
				InputStream resource = context.getResourceAsStream(request.getPathInfo());
				int b = resource.read();
				while (b > 0){
					response.getWriter().write((char) b);
					b = resource.read();
				}
				resource.close();
			}
			catch (Exception e)
			{
				e.printStackTrace(response.getWriter());
			}
		}
	}

}
