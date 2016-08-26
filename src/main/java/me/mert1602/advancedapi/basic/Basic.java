package me.mert1602.advancedapi.basic;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.mert1602.advancedapi.AdvancedAPIBukkit;
import me.mert1602.advancedapi.BukkitLog;
import me.mert1602.advancedapi.DefaultConfig;
import me.mert1602.advancedapi.DurabilityListener;
import me.mert1602.advancedapi.command.BasicCommandListener;
import me.mert1602.advancedapi.setting.SettingManager;
import me.mert1602.advancedapi.setting.SettingManagerInjector;

public abstract class Basic extends JavaPlugin implements BasicInterface {

	private static boolean settingManagerInjectorAdded;

	private List<Object> list;
	@Getter private SettingManager<?> defaultConfig;
	private boolean disabled;

	public Basic() {

		AdvancedAPIBukkit.init();

		//Create classes if not exists
		new SettingManagerInjector(this.defaultConfig);

		this.list = new ArrayList<Object>();
		this.defaultConfig = new DefaultConfig(this);
		this.disabled = false;

		if(Basic.settingManagerInjectorAdded == false){

			Basic.settingManagerInjectorAdded = true;

			this.addObject(SettingManagerInjector.getInstance());

		}

		BukkitLog.setPlugin(this);

	}

	public SettingManagerInjector getSettingManagerInjector(){
		return SettingManagerInjector.getInstance();
	}



	@Override
	public final void addObject(final Object... objects){

		for(final Object object : objects){
			this.list.add(object);
		}

	}



	@Override
	public final void onLoad() {

		this.loadLocal();

	}

	@Override
	public final void onEnable() {

		new BasicCommandListener(this);
		new DurabilityListener(this);

		this.startLocal();

	}

	@Override
	public final void onDisable() {

		this.stopLocal();

	}



	private final void loadLocal(){

		if(this.disabled){
			this.disable();
			return;
		}

		this.getLogger().info("=============================================");
		this.getLogger().info("...");

		this.preLoad();

		for(final Object object : this.list){

			if((object instanceof Loadable) == false) continue;
			Loadable loadable = (Loadable) object;

			loadable.load();

		}

		this.postLoad();

		this.getLogger().info("=============================================");
		this.getLogger().info("Loaded!");

	}

	private final void startLocal() {

		if(this.disabled){
			this.disable();
			return;
		}

		this.getLogger().info("==================================================");
		this.getLogger().info("...");

		this.preStart();

		this.addListener(Bukkit.getPluginManager());
		this.addCommands();

		for(final Object object : this.list){

			if((object instanceof Startable) == false) continue;
			Startable startable = (Startable) object;

			startable.start();

		}

		this.postStart();

		this.getLogger().info("==================================================");
		this.getLogger().info("Enabled!");

	}

	private final void stopLocal() {

		if(this.disabled){
			this.disable();
			return;
		}

		this.getLogger().info("==================================================");
		this.getLogger().info("...");

		this.preStop();

		for(final Object object : this.list){

			if((object instanceof Stopable) == false) continue;
			Stopable stopable = (Stopable) object;

			stopable.stop();

		}

		this.postStop();

		this.getLogger().info("==================================================");
		this.getLogger().info("Disabled!");

	}



	@Override
	public final void reload() {

		if(this.disabled){
			this.disable();
			return;
		}

		this.getLogger().info("Reloading " + this.getDescription().getFullName());

		this.getLogger().info("==================================================");
		this.getLogger().info("...");

		for(final Object object : this.list){

			if((object instanceof Reloadable) == false) continue;
			Reloadable reloadable = (Reloadable) object;

			reloadable.reload();

		}

		this.getLogger().info("==================================================");
		this.getLogger().info("Reloaded!");

	}

	@Override
	public final void disable(){

		if(this.disabled == false){
			this.disabled = true;
		}

		if(this.isEnabled()){

			Bukkit.getPluginManager().disablePlugin(this);
			this.getLogger().warning("Disabled!");

		}

	}

}
