package com.raphfrk.bukkit.serverportsimplechat;

import org.bukkit.event.Event;

public class SimpleChatBroadcastEvent extends Event {

	private static final long serialVersionUID = 1L;

	private String message;
	
	SimpleChatBroadcastEvent(String message) {
		super("SimpleChatBroadcastEvent");
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
}
