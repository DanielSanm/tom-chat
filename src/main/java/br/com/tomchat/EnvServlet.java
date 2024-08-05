package br.com.tomchat;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/env")
public class EnvServlet extends HttpServlet {

	private static final long serialVersionUID = -7540244099817606444L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ipAddress = Config.getProperty("serverIp");
        
        if (ipAddress == null || ipAddress.isEmpty()) {
        	resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server ip not found or not configured.");
        	return;
        }
        resp.setStatus(HttpServletResponse.SC_OK);
    	resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String json = String.format("{\"serverIp\":\"%s\"}", ipAddress);
        resp.getWriter().write(json);
        
    }
}
