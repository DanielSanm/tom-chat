package br.com.tomchat;

public class ClientMessage {
	
	private String clientId;
	private String text;
	private String datetime;
	
	public ClientMessage(String clientId, String text, String datetime) {
		this.clientId = clientId;
		this.text = text;
		this.datetime = datetime;
	}

	public String getClientId() {
		return clientId;
	}
	
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	
	
}
