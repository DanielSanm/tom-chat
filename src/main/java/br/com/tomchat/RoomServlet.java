package br.com.tomchat;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.CloseReason;
import jakarta.websocket.CloseReason.CloseCode;
import jakarta.websocket.CloseReason.CloseCodes;
import jakarta.websocket.DeploymentException;

@WebServlet("/room")
public class RoomServlet extends HttpServlet {

	private static final long serialVersionUID = 328084198563966725L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException, ServletException  {
		
		req.setAttribute("messages", WebSocketServer.messageStack);
		req.getRequestDispatcher("room.jsp").forward(req, resp);
	}
}
