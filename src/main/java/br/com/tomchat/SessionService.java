package br.com.tomchat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.websocket.Session;

public class SessionService {
	
	private Map<String, Session> sessionPool = new ConcurrentHashMap<String, Session>();
	
	public Session getSession(String sessionId) {
		return sessionPool.get(sessionId);
	}
	
	public void addSession(Session session) {
		sessionPool.put(session.getId(), session);
	}
	
	public void removeSession(Session session) {
		sessionPool.remove(session.getId());
	}
}
