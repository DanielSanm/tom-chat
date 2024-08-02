package br.com.tomchat;

public class Message<T> {

	public static final String HISTORY_LIST = "history-list";
	public static final String CLIENT_COUNT = "client-count";
	public static final String DATA = "message-data";
	public static final String CLIENT_EXIT = "client-exit";

	private String connectionId;
	private String type;
	private T content;
	private String dateTime;
	private String origin;

	public Message(String connectionId, String type, T content, String dateTime, String origin) {
		this.connectionId = connectionId;
		this.type = type;
		this.content = content;
		this.dateTime = dateTime;
		this.origin = origin;
	}

	public String getConnectionId() {
		return connectionId;
	}

	public void setConnectionId(String connectionId) {
		this.connectionId = connectionId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String sender) {
		this.origin = sender;
	}
}
