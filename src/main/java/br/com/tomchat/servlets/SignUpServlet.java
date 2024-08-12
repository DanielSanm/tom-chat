package br.com.tomchat.servlets;

import java.io.IOException;

import br.com.tomchat.models.User;
import br.com.tomchat.protocol.WebSocketServer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

	private static final long serialVersionUID = -4562107685808128721L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("signup.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = new User(req.getParameter("firstname"), req.getParameter("lastname"), req.getParameter("nickname"), 
				req.getParameter("birthdate"), req.getParameter("email"), req.getParameter("phone"), 
				req.getParameter("password"));
		WebSocketServer.userList.add(user);
		resp.sendRedirect(req.getContextPath() + "/signin");
	}

}
