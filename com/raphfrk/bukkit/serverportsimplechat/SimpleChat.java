package com.raphfrk.bukkit.serverportsimplechat;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.raphfrk.bukkit.eventlinkapi.EventLinkAPI;
import com.raphfrk.bukkit.serverportcoreapi.ServerPortCoreAPI;

public class SimpleChat extends JavaPlugin {

	public static Server server;
	public static Logger log;
	
	EventLinkAPI eventLink;
	ServerPortCoreAPI serverPortCore;
	
	public PluginDescriptionFile pdfFile;
	public SimpleChatPlayerListener playerListener = new SimpleChatPlayerListener(this);
	public SimpleChatCustomListener customListener = new SimpleChatCustomListener(this);
	
	public DependencyManager dm = new DependencyManager(this);
	
	PluginManager pm;
	ServicesManager sm;
	
	File pluginDirectory;
	
	public void onDisable() {
		
	}

	public void onEnable() {
				
		server = getServer();
		pm = server.getPluginManager();
		sm = server.getServicesManager();
		
		log = server.getLogger();
		
		pluginDirectory = getDataFolder();
		
		if(!pluginDirectory.exists()) {
			pluginDirectory.mkdirs();
		}
		
		pdfFile = getDescription();
		
		if(dm.connectEventLink() && dm.connectServerPortCore() && pm.isPluginEnabled(this)) {
			log(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
		}
		
		pm.registerEvent(Type.PLAYER_CHAT, playerListener, Priority.Monitor, this);
		pm.registerEvent(Type.CUSTOM_EVENT, customListener, Priority.Normal, this);
		
		log("Started");
		
	}
	
	
	
	
	
	public static void log(String message) {
		log.info("[SimplePortal]: " + message);
	}
	
}
