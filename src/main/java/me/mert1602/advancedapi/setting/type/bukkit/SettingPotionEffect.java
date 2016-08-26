package me.mert1602.advancedapi.setting.type.bukkit;

import org.bukkit.potion.PotionEffect;

import me.mert1602.advancedapi.BukkitLog;
import me.mert1602.advancedapi.basic.BasicInterface;
import me.mert1602.advancedapi.setting.BukkitSettingType;
import me.mert1602.advancedapi.setting.Setting;
import me.mert1602.advancedapi.setting.SettingLanguage;
import me.mert1602.advancedapi.setting.SettingManager;
import me.mert1602.advancedapi.tool.ToolEffect;

public class SettingPotionEffect extends Setting<PotionEffect> {

	public SettingPotionEffect(SettingManager<? extends BasicInterface> settingManager, String name, String path,
			boolean hasLanguage, PotionEffect defaultObject) {
		super(settingManager, name, path, BukkitSettingType.POTIONEFFECT, hasLanguage, defaultObject);

		if(hasLanguage){

			for (final SettingLanguage lang : SettingLanguage.values()) {
				this.getConfig().addDefault(lang.getPrePath() + this.getPath(), ToolEffect.potionEffectToString(defaultObject));
			}

		} else this.getConfig().addDefault(path, ToolEffect.potionEffectToString(defaultObject));

	}

	@Override
	public PotionEffect get(SettingLanguage lang) {

		this.loadConfig();

		try{

			return this.hasLanguage() ?
					ToolEffect.potionEffectFromString(this.getConfig().getString(lang.getPrePath() + this.getPath(), ToolEffect.potionEffectToString(this.getDefaultObject()))) :
						ToolEffect.potionEffectFromString(this.getConfig().getString(this.getPath(), ToolEffect.potionEffectToString(this.getDefaultObject())));

		}catch(Throwable e){
			BukkitLog.error(e.getMessage());
			return this.getDefaultObject();
		}

	}

	@Override
	public void set(PotionEffect t, SettingLanguage lang) {

		if(this.hasLanguage()){
			this.getConfig().set(lang.getPrePath() + this.getPath(), ToolEffect.potionEffectToString(t));
		}else{
			this.getConfig().set(this.getPath(), ToolEffect.potionEffectToString(t));
		}

		this.saveConfig();

	}

}
