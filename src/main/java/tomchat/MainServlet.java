package tomchat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.DeploymentException;

@WebServlet("/room")
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 328084198563966725L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws IOException, ServletException  {
		
		System.out.println("\nservlet get");
		Client c = new Client();
		
		try {
			c.connectToServer();
		} catch (DeploymentException | IOException e) {
			e.printStackTrace();
		}
	}
}
