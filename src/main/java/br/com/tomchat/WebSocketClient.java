package br.com.tomchat;

import java.io.IOException;
import java.net.URI;

import jakarta.websocket.ClientEndpoint;
import jakarta.websocket.CloseReason;
import jakarta.websocket.CloseReason.CloseCodes;
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
	
	private Session mSession;
	
	public void connectToServer() throws DeploymentException, IOException {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		String uri = "ws://localhost:8080/tomchat/websocket";
		container.connectToServer(this, URI.create(uri));
	}
	
	public void sendMessage(String message) {
		try {
			System.out.println("Client("+ mSession.getId() + ") Message: " + message);
			mSession.getBasicRemote().sendText(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeClient() {
		this.close(new CloseReason(CloseCodes.NORMAL_CLOSURE, "finished"));
	}
	
	@OnMessage
	public void messageReceiver(String message, Session session) {
		System.out.println("Message received (Client): " + message);
	}

	@OnOpen
	public void open(Session session) {
		mSession = session;
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
