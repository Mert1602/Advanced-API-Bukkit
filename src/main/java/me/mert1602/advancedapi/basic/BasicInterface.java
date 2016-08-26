package me.mert1602.advancedapi.basic;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public interface BasicInterface extends Plugin {

	public void addListener(PluginManager pluginManager);

	public void addCommands();

	public void addObject(Object... objects);



	public void preLoad();

	public void postLoad();



	public void preStart();

	public void postStart();



	public void preStop();

	public void postStop();



	public void reload();

	public void disable();

}
