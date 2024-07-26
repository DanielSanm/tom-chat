package br.com.tomchat;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class WebSocketServer {
	
	@OnMessage
	public void messageReceiver(String message, Session session) {
		System.out.println("Message received (server): " + message);
		try {
			session.getBasicRemote().sendText("message received on server");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@OnOpen
	public void open(Session session) {
		System.out.println("Server handshake");
	}
	
	@OnClose
	public void close(CloseReason reason) {
		System.out.println(reason.getReasonPhrase());
	}
	
	@OnError
	public void error(Throwable t) {
		System.out.println(t.getMessage());
	}
}
