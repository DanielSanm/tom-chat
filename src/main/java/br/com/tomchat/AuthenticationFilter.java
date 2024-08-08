package br.com.tomchat;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession(false);
		boolean loggedIn = (session != null && session.getAttribute("user") != null);
		boolean loginRequest = req.getRequestURI().equals(req.getContextPath() + "/login.jsp");

		if (loggedIn || loginRequest) {
			chain.doFilter(request, response); // Usuário autenticado ou acessando página de login
		} else {
			res.sendRedirect(req.getContextPath() + "/login.jsp"); // Redireciona para a página de login
		}
	}

}
