package me.mert1602.advancedapi.setting;

import me.mert1602.advancedapi.basic.BasicInterface;
import me.mert1602.advancedapi.setting.type.bukkit.SettingColor;
import me.mert1602.advancedapi.setting.type.bukkit.SettingItemStack;
import me.mert1602.advancedapi.setting.type.bukkit.SettingLocation;
import me.mert1602.advancedapi.setting.type.bukkit.SettingPotionEffect;
import me.mert1602.advancedapi.setting.type.bukkit.SettingVector;
import me.mert1602.advancedapi.setting.type.bukkit.SettingWorld;
import me.mert1602.advancedapi.setting.type.list.bukkit.SettingLocationList;

public interface IBukkitSettingManager<C extends BasicInterface> extends ISettingManager<C> {
	
	@Override
	public BukkitSettingFileContent getFileContent();
	
	
	
	public SettingColor getColor(String name);
	
	public SettingItemStack getItemStack(String name);
	
	public SettingLocation getLocation(String name);
	
	public SettingPotionEffect getPotionEffect(String name);
	
	public SettingVector getVector(String name);
	
	public SettingWorld getWorld(String name);
	
	
	
	public SettingLocationList getLocationList(String name);

}
