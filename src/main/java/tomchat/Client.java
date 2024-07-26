package tomchat;

import java.io.IOException;
import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class Client {
	
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
