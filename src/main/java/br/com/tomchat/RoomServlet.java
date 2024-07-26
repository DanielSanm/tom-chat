package br.com.tomchat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.DeploymentException;

@WebServlet("/room")
public class RoomServlet extends HttpServlet {

	private static final long serialVersionUID = 328084198563966725L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException, ServletException  {
		WebSocketClient client = new WebSocketClient();
		
		try {
			client.connectToServer();
		} catch (DeploymentException | IOException e) {
			e.printStackTrace();
		}
		
		req.getRequestDispatcher("/room.jsp").forward(req, resp);
	}
}
