package cz.wake.manager;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	private Main instance;
	
	public void onEnable(){
		instance = this;
	}
	
	public void onDisable(){
		instance = null;
	}

	public Main getInstance(){
		return instance;
	}
}
