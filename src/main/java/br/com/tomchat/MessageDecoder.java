package br.com.tomchat;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;

public class MessageDecoder implements Decoder.Text<Message<?>> {
	
	private static final Gson gson = new Gson();

	@Override
	public Message<?> decode(String s) throws DecodeException {
		try {
			return gson.fromJson(s, new TypeToken<Message<?>>(){}.getType());
		} catch (JsonSyntaxException e) {
			throw new DecodeException(s, "Unable to decode text to Message", e);
		}
	}

	@Override
	public boolean willDecode(String s) {
		return s != null;
	}

}
