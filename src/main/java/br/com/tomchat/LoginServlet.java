package br.com.tomchat;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -6057129968852153057L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if ("admin".equals(username) && "admin".equals(password)) {
        	HttpSession session = request.getSession();
        	session.setAttribute("user", username);
        	response.sendRedirect("room.jsp");
        } else {
        	request.setAttribute("errorMessage", "The username or password are invalid!");
        	request.getRequestDispatcher("login.jsp").forward(request, response);
        }
	}
}
