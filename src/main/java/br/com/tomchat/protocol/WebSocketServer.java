package br.com.tomchat.protocol;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import com.google.gson.Gson;

import br.com.tomchat.models.User;
import jakarta.websocket.CloseReason;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class WebSocketServer {
	private final Gson gson = new Gson();
	
	private final Set<Session> clientPool = new CopyOnWriteArraySet<>();
	private final Queue<Message<String>> timeline = new ConcurrentLinkedQueue<>();
	public static final List<User> userList = new CopyOnWriteArrayList<>(); 

	public <T> void broadcast(Message<T> message) {
		for (Session session : clientPool) {
			
			try {
				String json = gson.toJson(message);
				System.out.println("SERVER = " + json);
				session.getBasicRemote().sendText(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private <T> Message<T> createMessage(String connectionId, String type, T content, Context origin) {
		String now = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		return new Message<T>(connectionId, type, content, now, origin.toString());
	}

	@OnMessage
	public void messageReceiver(String message, Session session) {
		var msg = createMessage(session.getId(), Message.DATA, message, Context.CLIENT);
		timeline.add(msg);
		broadcast(msg);
	}

	@OnOpen
	public void open(Session session) {
		System.out.println("SERVER = Client(" + session.getId() + ") connected!");
		var msg = createMessage(session.getId(), Message.CLIENT_ENTRY, "Guest " + session.getId() + " has entered!", Context.CLIENT);
		clientPool.add(session);
		broadcast(createMessage(session.getId(), Message.HISTORY_LIST,  timeline, Context.SERVER));
		broadcast(msg);
		broadcast(createMessage(session.getId(), Message.CLIENT_COUNT, clientPool.size(), Context.SERVER));
		timeline.add(msg);
	}

	@OnClose
	public void close(Session session, CloseReason reason) {
		System.out.println("SERVER = Client(" + session.getId() + ") disconnected: " + reason.getCloseCode().toString());
		var msg = createMessage(session.getId(), Message.CLIENT_EXIT, "Guest " + session.getId() + " has left!", Context.CLIENT);
		clientPool.remove(session);
		timeline.add(msg);
		broadcast(msg);
		broadcast(createMessage(session.getId(), Message.CLIENT_COUNT, clientPool.size(), Context.SERVER));
	}

	@OnError
	public void error(Throwable t) {
		t.printStackTrace();
	}
	
	private enum Context {
		CLIENT, SERVER;
	}
}
