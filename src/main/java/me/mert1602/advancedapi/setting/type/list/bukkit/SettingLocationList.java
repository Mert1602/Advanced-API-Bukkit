package me.mert1602.advancedapi.setting.type.list.bukkit;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

import me.mert1602.advancedapi.BukkitLog;
import me.mert1602.advancedapi.basic.BasicInterface;
import me.mert1602.advancedapi.setting.BukkitSettingType;
import me.mert1602.advancedapi.setting.SettingLanguage;
import me.mert1602.advancedapi.setting.SettingList;
import me.mert1602.advancedapi.setting.SettingManager;
import me.mert1602.advancedapi.tool.ToolBukkit;
import me.mert1602.advancedapi.tool.ToolLocation;

public final class SettingLocationList extends SettingList<Location> {

	public SettingLocationList(final SettingManager<? extends BasicInterface> settingManager, final String name, final String path, final boolean hasLanguage, final List<Location> defaultValue) {
		super(settingManager, name, path, BukkitSettingType.LOCATIONLIST, hasLanguage, defaultValue);

		if (hasLanguage) {

			for (final SettingLanguage lang : SettingLanguage.values()) {

				final List<String> newDefaultValue = new ArrayList<String>();

				for(final Location location : defaultValue){
					newDefaultValue.add(ToolLocation.locationToString(location));
				}

				this.getConfig().addDefault(lang.getPrePath() + path, newDefaultValue);

			}

		}else{

			final List<String> locationStrings = new ArrayList<String>();

			for(final Location location : defaultValue){
				locationStrings.add(ToolLocation.locationToString(location));
			}

			this.getConfig().addDefault(path, locationStrings);

		}

	}

	@Override
	public Location[] getArray(final SettingLanguage lang) {
		return this.get(lang).toArray(ToolBukkit.EMPTY_LOCATION_LIST);
	}

	@Override
	public List<Location> get(final SettingLanguage lang) {

		this.loadConfig();

		try{

			final List<Location> list = new ArrayList<Location>();

			if(this.hasLanguage()){

				for(final String locationString : this.getConfig().getStringList(lang.getPrePath() + this.getPath())){
					list.add(ToolLocation.locationFromString(locationString));
				}

			}else{

				for(final String locationString : this.getConfig().getStringList(this.getPath())){
					list.add(ToolLocation.locationFromString(locationString));
				}

				return list;

			}

			return list;

		}catch(final Throwable e){
			BukkitLog.error(e.getMessage());
			return this.getDefaultObject();
		}

	}

	@Override
	public void set(final List<Location> list, final SettingLanguage lang) {

		final List<String> locationStrings = new ArrayList<String>();

		for(final Location location : list){
			locationStrings.add(ToolLocation.locationToString(location));
		}

		if(this.hasLanguage()){
			this.getConfig().set(lang.getPrePath() + this.getPath(), locationStrings);
		} else {
			this.getConfig().set(this.getPath(), locationStrings);
		}

		this.saveConfig();

	}

}
