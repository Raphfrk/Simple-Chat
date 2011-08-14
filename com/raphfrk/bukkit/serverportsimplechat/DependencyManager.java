package com.raphfrk.bukkit.serverportsimplechat;

import org.bukkit.plugin.RegisteredServiceProvider;

import com.raphfrk.bukkit.eventlinkapi.EventLinkAPI;
import com.raphfrk.bukkit.serverportcoreapi.ServerPortCoreAPI;

public class DependencyManager {

	SimpleChat p;

	DependencyManager(SimpleChat p) {
		this.p = p;
	}

	boolean connectEventLink() {
		RegisteredServiceProvider<EventLinkAPI> eventLinkProvider = p.sm.getRegistration(EventLinkAPI.class);
		if(eventLinkProvider != null) {
			p.eventLink = eventLinkProvider.getProvider();
			if(p.eventLink != null) {
				SimpleChat.log("Connected to Event Link API service");
				return true;

			}
		}
		SimpleChat.log(p.pdfFile.getName() + " requires EventLink service provider");
		return false;
	}

	boolean connectServerPortCore() {

		RegisteredServiceProvider<ServerPortCoreAPI> serverPortCoreProvider = p.sm.getRegistration(ServerPortCoreAPI.class);
		if(serverPortCoreProvider != null) {
			p.serverPortCore = serverPortCoreProvider.getProvider();
			if(p.serverPortCore != null) {
				SimpleChat.log("Connected to Server Port Core API service");
				return true;
			}
		} 
		SimpleChat.log(p.pdfFile.getName() + " requires ServerPortCore service provider");
		return false;
	}

}
