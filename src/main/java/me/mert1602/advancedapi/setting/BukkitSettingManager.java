package me.mert1602.advancedapi.setting;

import java.io.File;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import lombok.Getter;
import me.mert1602.advancedapi.basic.BasicInterface;
import me.mert1602.advancedapi.setting.type.bukkit.SettingColor;
import me.mert1602.advancedapi.setting.type.bukkit.SettingItemStack;
import me.mert1602.advancedapi.setting.type.bukkit.SettingLocation;
import me.mert1602.advancedapi.setting.type.bukkit.SettingPotionEffect;
import me.mert1602.advancedapi.setting.type.bukkit.SettingVector;
import me.mert1602.advancedapi.setting.type.bukkit.SettingWorld;
import me.mert1602.advancedapi.setting.type.list.bukkit.SettingLocationList;
import me.mert1602.advancedapi.tool.ToolItem;

public abstract class BukkitSettingManager<C extends BasicInterface> extends SettingManager<C> implements IBukkitSettingManager<C> {

	@Getter private BukkitSettingFileContent fileContent;
	
	public BukkitSettingManager(final C basic, final String file) {
		this(basic, new BukkitSettingFileContent(null, new File(file)), true);
	}
	
	public BukkitSettingManager(final C basic, final String path, final String file) {
		this(basic, new BukkitSettingFileContent(null, new File(path, file)), true);
	}
	
	public BukkitSettingManager(final C basic, final String path, final String file, boolean autoRepair) {
		this(basic, new BukkitSettingFileContent(null, new File(path, file)), autoRepair);
	}
	
	public BukkitSettingManager(C basic, BukkitSettingFileContent fileContent, boolean autoRepair) {
		super(basic, fileContent, false);
		
		BukkitSettingType.getTypes();
		
		fileContent.setContent(this);
		
		this.fileContent = fileContent;
		
		if(autoRepair){

			this.addDefault();
			this.repair();

		}
		
	}
	
	@Override
	public SettingColor getColor(String name) {
		return this.getSetting(name) != null ?
				(SettingColor) this.getSetting(name) :
					new SettingColor(this, name, name, false, Color.WHITE);
	}
	
	@Override
	public SettingItemStack getItemStack(String name) {
		return this.getSetting(name) != null ?
				(SettingItemStack) this.getSetting(name) :
					new SettingItemStack(this, name, name, false, ToolItem.createItemStack(Material.STONE));
	}
	
	@Override
	public SettingLocation getLocation(String name) {
		return this.getSetting(name) != null ?
				(SettingLocation) this.getSetting(name) :
					new SettingLocation(this, name, name, false, new Location(Bukkit.getWorlds().get(0), 0, 0, 0));
	}
	
	@Override
	public SettingPotionEffect getPotionEffect(String name) {
		return this.getSetting(name) != null ?
				(SettingPotionEffect) this.getSetting(name) :
					new SettingPotionEffect(this, name, name, false, new PotionEffect(PotionEffectType.SPEED, 20, 1));
	}
	
	@Override
	public SettingVector getVector(String name) {
		return this.getSetting(name) != null ?
				(SettingVector) this.getSetting(name) :
					new SettingVector(this, name, name, false, new Vector());
	}
	
	@Override
	public SettingWorld getWorld(String name) {
		return this.getSetting(name) != null ?
				(SettingWorld) this.getSetting(name) :
					new SettingWorld(this, name, name, false, Bukkit.getWorlds().get(0));
	}
	
	@Override
	public SettingLocationList getLocationList(String name) {
		return this.getSetting(name) != null ?
				(SettingLocationList) this.getSetting(name) :
					new SettingLocationList(this, name, name, false, Arrays.asList(new Location(Bukkit.getWorlds().get(0), 0, 0, 0)));
	}
	
}
