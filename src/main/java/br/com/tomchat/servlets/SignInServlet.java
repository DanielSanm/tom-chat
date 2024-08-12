package br.com.tomchat.servlets;

import java.io.IOException;

import br.com.tomchat.models.User;
import br.com.tomchat.protocol.WebSocketServer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/signin")
public class SignInServlet extends HttpServlet {

	private static final long serialVersionUID = -6057129968852153057L;
	
	static {
		WebSocketServer.userList.add(new User("Administrator", "", "admin", "0000-00-00", "admin@email.com", "00000000", "admin"));
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("signin.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nickname = req.getParameter("nickname");
        String password = req.getParameter("password");
        
        User validUser = null;
        for (User user : WebSocketServer.userList) {
        	if (user.getNickName().equals(nickname) && user.getPassword().equals(password)) {
        		validUser = user;
        		break;
            }
        }
        
        if (validUser != null) {
        	HttpSession session = req.getSession();
        	session.setAttribute("user", validUser);
        	resp.sendRedirect("room");
        } else {
        	req.setAttribute("errorMessage", "The username or password are invalid!");
        	req.getRequestDispatcher("signin.jsp").forward(req, resp);
        }
	}
}
