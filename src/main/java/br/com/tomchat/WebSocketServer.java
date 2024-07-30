package br.com.tomchat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import jakarta.websocket.CloseReason;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class WebSocketServer {
	
	private final SessionService sessionService = new SessionService();
	
	public static final List<String> messageStack = new CopyOnWriteArrayList<>();
	
	@OnMessage
	public void messageReceiver(String message, Session session) {
		System.out.println("Message received from Client(" + session.getId() + "): " + message);
		messageStack.add(message);
		
		try {
			session.getBasicRemote().sendText("message received on server");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@OnOpen
	public void open(Session session) {
		String id = session.getId();
		System.out.println("Server handshake; Client id: " + id);
		sessionService.addSession(session);		
	}
	
	@OnClose
	public void close(Session session, CloseReason reason) {
		System.out.println(reason.getReasonPhrase());
		sessionService.removeSession(session);
	}
	
	@OnError
	public void error(Throwable t) {
		System.out.println(t.getMessage());
	}
}
