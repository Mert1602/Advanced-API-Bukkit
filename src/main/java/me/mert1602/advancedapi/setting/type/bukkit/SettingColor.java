package me.mert1602.advancedapi.setting.type.bukkit;

import org.bukkit.Color;

import me.mert1602.advancedapi.BukkitLog;
import me.mert1602.advancedapi.basic.BasicInterface;
import me.mert1602.advancedapi.setting.BukkitSettingType;
import me.mert1602.advancedapi.setting.Setting;
import me.mert1602.advancedapi.setting.SettingLanguage;
import me.mert1602.advancedapi.setting.SettingManager;

public class SettingColor extends Setting<Color> {

	public SettingColor(SettingManager<? extends BasicInterface> settingManager, String name, String path, boolean hasLanguage, Color defaultValue) {
		super(settingManager, name, path, BukkitSettingType.COLOR, hasLanguage, defaultValue);

		if(this.hasLanguage()){

			for(final SettingLanguage lang : SettingLanguage.values()){
				this.getConfig().addDefault(lang.getPrePath() + path, defaultValue.asRGB());
			}

		}else this.getConfig().addDefault(path, defaultValue.asRGB());

	}

	@Override
	public Color get(SettingLanguage lang) {

		this.loadConfig();

		try{

			return this.hasLanguage() ?
					Color.fromRGB(this.getConfig().getInt(lang.getPrePath() + this.getPath(), this.getDefaultObject().asRGB())) :
						Color.fromRGB(this.getConfig().getInt(this.getPath(), this.getDefaultObject().asRGB()));

		}catch(Throwable e){
			BukkitLog.error(e.getMessage());
			return this.getDefaultObject();
		}

	}
	
	@Override
	public void set(Color t, SettingLanguage lang) {
		
		if(this.hasLanguage()){
			this.getConfig().set(lang.getPrePath() + this.getPath(), t.asRGB());
		}else{
			this.getConfig().set(this.getPath(), t.asRGB());
		}
		
		this.saveConfig();
		
	}

}
