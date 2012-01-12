/*******************************************************************************
 * Copyright (C) 2012 Raphfrk
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
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
