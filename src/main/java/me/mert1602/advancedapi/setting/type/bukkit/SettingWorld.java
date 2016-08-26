package me.mert1602.advancedapi.setting.type.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.World;

import me.mert1602.advancedapi.BukkitLog;
import me.mert1602.advancedapi.basic.BasicInterface;
import me.mert1602.advancedapi.setting.BukkitSettingType;
import me.mert1602.advancedapi.setting.Setting;
import me.mert1602.advancedapi.setting.SettingLanguage;
import me.mert1602.advancedapi.setting.SettingManager;

public class SettingWorld extends Setting<World> {

	public SettingWorld(final SettingManager<? extends BasicInterface> settingManager, final String name, final String path, final boolean hasLanguage, final World defaultValue) {
		super(settingManager, name, path, BukkitSettingType.WORLD, hasLanguage, defaultValue);

		if(hasLanguage){

			for(final SettingLanguage lang : SettingLanguage.values()){
				this.getConfig().addDefault(lang.getPrePath() + path, defaultValue.getName());
			}

		} else this.getConfig().addDefault(path, defaultValue.getName());

	}

	@Override
	public World get(final SettingLanguage lang) {

		this.loadConfig();

		try{
			return this.hasLanguage() ?
					Bukkit.getWorld(this.getConfig().getString(lang.getPrePath() + this.getPath(), this.getDefaultObject().getName())) :
						Bukkit.getWorld(this.getConfig().getString(this.getPath(), this.getDefaultObject().getName()));
		}catch(final Throwable e){
			BukkitLog.error(e.getMessage());
			return this.getDefaultObject();
		}

	}

	@Override
	public void set(final World world, final SettingLanguage lang) {

		if(this.hasLanguage()){
			this.getConfig().set(lang.getPrePath() + this.getPath(), world.getName());
		} else {
			this.getConfig().set(this.getPath(), world.getName());
		}

		this.saveConfig();

	}

}
