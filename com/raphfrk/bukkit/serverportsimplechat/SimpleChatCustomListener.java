package com.raphfrk.bukkit.serverportsimplechat;

import org.bukkit.event.CustomEventListener;
import org.bukkit.event.Event;

public class SimpleChatCustomListener extends CustomEventListener {

	SimpleChat p;

	SimpleChatCustomListener(SimpleChat p) {
		this.p = p;
	}

	public void onCustomEvent(Event event) {
		if (event instanceof SimpleChatBroadcastEvent) {
			onCustomEvent((SimpleChatBroadcastEvent)event);
		}
	}

	public void onCustomEvent(SimpleChatBroadcastEvent event) {
		p.getServer().broadcastMessage(event.getMessage());
	}

}
