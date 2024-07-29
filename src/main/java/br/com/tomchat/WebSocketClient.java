package br.com.tomchat;

import java.io.IOException;
import java.net.URI;

import jakarta.websocket.ClientEndpoint;
import jakarta.websocket.CloseReason;
import jakarta.websocket.ContainerProvider;
import jakarta.websocket.DeploymentException;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.WebSocketContainer;



@ClientEndpoint
public class WebSocketClient {
	
	public void connectToServer() throws DeploymentException, IOException {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		String uri = "ws://localhost:8080/tomchat/websocket";
		container.connectToServer(this, URI.create(uri));
	}
	
	@OnMessage
	public void messageReceiver(String message, Session session) {
		System.out.println("Message received (Client): " + message);
	}

	@OnOpen
	public void open(Session session) {
		try {
			session.getBasicRemote().sendText("sending from client");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Client handshake");
	}

	@OnClose
	public void close(CloseReason c) {
		System.out.println(c.getReasonPhrase());
	}

	@OnError
	public void error(Throwable t) {
		System.out.println(t.getMessage());
	}
}
