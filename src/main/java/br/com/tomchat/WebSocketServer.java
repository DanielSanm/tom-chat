package br.com.tomchat;

import java.io.IOException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArraySet;

import com.google.gson.Gson;

import jakarta.websocket.CloseReason;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class WebSocketServer {

	private static Set<Session> clientPool = new CopyOnWriteArraySet<Session>();
	public static Queue<String> messageQueue = new ConcurrentLinkedQueue<>();
	private final Gson gson = new Gson();

	public <T> void sendMessage(Message<T> message) {
		for (Session session : clientPool) {
			try {
				String json = gson.toJson(message);
				System.out.println("SERVER = Message to clients: " + json);
				session.getBasicRemote().sendText(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@OnMessage
	public void messageReceiver(String message, Session session) {
		messageQueue.add(message);
		System.out.println("SERVER = Message from client(" + session.getId() + "): " + message);
		sendMessage(new Message<>("message", message));
	}

	@OnOpen
	public void open(Session session) {
		System.out.println("SERVER = Client(" + session.getId() + ") connected!");
		clientPool.add(session);
		sendMessage(new Message<>("history", messageQueue));
		sendMessage(new Message<>("client-count", clientPool.size()));
	}

	@OnClose
	public void close(Session session, CloseReason reason) {
		System.out.println("SERVER = Client(" + session.getId() + ") closed: " + reason.getCloseCode().toString());
		clientPool.remove(session);
		sendMessage(new Message<>("client-count", clientPool.size()));
	}

	@OnError
	public void error(Throwable t) {
		t.printStackTrace();
	}
}
