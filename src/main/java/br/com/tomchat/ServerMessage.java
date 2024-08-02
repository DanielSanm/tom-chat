package br.com.tomchat;

public class ServerMessage<T> {

	private String type;
	private T content;
	
	public ServerMessage(String type, T content) {
		this.type = type;
		this.content = content;
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
	
	
}
