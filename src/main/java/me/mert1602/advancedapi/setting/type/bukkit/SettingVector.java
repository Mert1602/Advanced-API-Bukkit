package me.mert1602.advancedapi.setting.type.bukkit;

import org.bukkit.util.Vector;

import me.mert1602.advancedapi.BukkitLog;
import me.mert1602.advancedapi.basic.BasicInterface;
import me.mert1602.advancedapi.setting.BukkitSettingType;
import me.mert1602.advancedapi.setting.Setting;
import me.mert1602.advancedapi.setting.SettingLanguage;
import me.mert1602.advancedapi.setting.SettingManager;
import me.mert1602.advancedapi.tool.ToolVector;

public class SettingVector extends Setting<Vector> {

	public SettingVector(final SettingManager<? extends BasicInterface> settingManager, final String name, final String path, final boolean hasLanguage, final Vector defaultValue) {
		super(settingManager, name, path, BukkitSettingType.VECTOR, hasLanguage, defaultValue);

		if(hasLanguage){

			for(final SettingLanguage lang : SettingLanguage.values()){
				this.getConfig().addDefault(lang.getPrePath() + path, ToolVector.vectorToString(defaultValue));
			}

		} else this.getConfig().addDefault(path, ToolVector.vectorToString(defaultValue));

	}

	@Override
	public Vector get(final SettingLanguage lang) {

		this.loadConfig();

		try{
			
			return this.hasLanguage() ?
					ToolVector.vectorFromString(this.getConfig().getString(lang.getPrePath() + this.getPath(), ToolVector.vectorToString(this.getDefaultObject()))) :
						ToolVector.vectorFromString(this.getConfig().getString(this.getPath(), ToolVector.vectorToString(this.getDefaultObject())));
			
		}catch(final Throwable e){
			BukkitLog.error(e.getMessage());
			return this.getDefaultObject();
		}

	}
	
	@Override
	public void set(Vector t, SettingLanguage lang) {
		
		if(this.hasLanguage()){
			this.getConfig().set(lang.getPrePath() + this.getPath(), ToolVector.vectorToString(t));
		} else {
			this.getConfig().set(this.getPath(), ToolVector.vectorToString(t));
		}

		this.saveConfig();
		
	}

}
