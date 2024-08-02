package br.com.tomchat;

import com.google.gson.Gson;

import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;

public class MessageEncoder implements Encoder.Text<Message<?>>{

	@Override
	public String encode(Message<?> message) throws EncodeException {
		Gson gson = new Gson();
		return gson.toJson(message);
	}
}
