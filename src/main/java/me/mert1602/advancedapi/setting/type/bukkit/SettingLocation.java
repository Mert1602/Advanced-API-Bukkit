package me.mert1602.advancedapi.setting.type.bukkit;

import org.bukkit.Location;

import me.mert1602.advancedapi.BukkitLog;
import me.mert1602.advancedapi.basic.BasicInterface;
import me.mert1602.advancedapi.setting.BukkitSettingType;
import me.mert1602.advancedapi.setting.Setting;
import me.mert1602.advancedapi.setting.SettingLanguage;
import me.mert1602.advancedapi.setting.SettingManager;
import me.mert1602.advancedapi.tool.ToolLocation;

public final class SettingLocation extends Setting<Location> {

	public SettingLocation(final SettingManager<? extends BasicInterface> settingManager, final String name, final String path, final boolean hasLanguage, final Location defaultValue) {
		super(settingManager, name, path, BukkitSettingType.LOCATION, hasLanguage, defaultValue);

		if(hasLanguage){

			for (final SettingLanguage lang : SettingLanguage.values()) {
				this.getConfig().addDefault(lang.getPrePath() + this.getPath(), ToolLocation.locationToString(defaultValue));
			}

		} else this.getConfig().addDefault(path, ToolLocation.locationToString(defaultValue));

	}

	@Override
	public Location get(final SettingLanguage lang) {

		this.loadConfig();

		try{

			return this.hasLanguage() ?
					ToolLocation.locationFromString(this.getConfig().getString(lang.getPrePath() + this.getPath(), ToolLocation.locationToString(this.getDefaultObject()))) :
						ToolLocation.locationFromString(this.getConfig().getString(this.getPath(), ToolLocation.locationToString(this.getDefaultObject())));

		}catch (final Throwable e){
			BukkitLog.error(e.getMessage());
			return this.getDefaultObject();
		}

	}

	@Override
	public void set(final Location location, final SettingLanguage lang) {

		if(this.hasLanguage()){
			this.getConfig().set(lang.getPrePath() + this.getPath(), ToolLocation.locationToString(location));
		} else {
			this.getConfig().set(this.getPath(), ToolLocation.locationToString(location));
		}

		this.saveConfig();

	}

}
