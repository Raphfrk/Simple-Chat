package com.raphfrk.bukkit.serverportsimplechat;

import java.util.Set;

import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;

public class SimpleChatPlayerListener extends PlayerListener {

	SimpleChat p;
	
	SimpleChatPlayerListener(SimpleChat p) {
		this.p = p;
	}
	
	@Override
	public void onPlayerChat(PlayerChatEvent event) {

		if (event.isCancelled()) {
			return;
		}
		
		Set<String> servers = p.eventLink.copyEntries("servers");

		String serverName = p.eventLink.getServerName();
		
		String message = "<" + event.getPlayer().getDisplayName() + "> " + event.getMessage();
		
		SimpleChatBroadcastEvent e = new SimpleChatBroadcastEvent(message);
		
		for (String server : servers) {
			if (!server.equalsIgnoreCase(serverName)) {
				p.eventLink.sendEvent(server, e);
			}
		}
		
	}
}
