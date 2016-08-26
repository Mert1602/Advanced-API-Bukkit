package me.mert1602.advancedapi.setting;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import lombok.Getter;
import me.mert1602.advancedapi.ReflectionUtilsBukkit;
import me.mert1602.advancedapi.setting.type.bukkit.SettingColor;
import me.mert1602.advancedapi.setting.type.bukkit.SettingItemStack;
import me.mert1602.advancedapi.setting.type.bukkit.SettingLocation;
import me.mert1602.advancedapi.setting.type.bukkit.SettingPotionEffect;
import me.mert1602.advancedapi.setting.type.bukkit.SettingVector;
import me.mert1602.advancedapi.setting.type.bukkit.SettingWorld;
import me.mert1602.advancedapi.setting.type.list.bukkit.SettingLocationList;
import me.mert1602.advancedapi.tool.ToolItem;

public class BukkitSettingType {
	
	/**
	 * Singel
	 */
	
	public static final SettingType COLOR 			= new SettingType(SettingColor.class, 			Color.WHITE);
	public static final SettingType ITEMSTACK 		= new SettingType(SettingItemStack.class, 		ItemStack.class) {
		
		@Override
		public Object getDefaultObject() {
			
			if(Bukkit.getServer() == null) return null;
			if(Bukkit.getItemFactory() == null) return null;
			
			return ToolItem.createItemStack(Material.STONE);
		}
		
	};
	public static final SettingType LOCATION 		= new SettingType(SettingLocation.class, 		Location.class) {
		
		@Override
		public Object getDefaultObject() {
			
			if(Bukkit.getServer() == null) return null;
			if(Bukkit.getWorlds() == null) return null;
			if(Bukkit.getWorlds().size() < 1) return null;
			
			return new Location(Bukkit.getWorlds().get(0), 0, 0, 0);
		}
		
	};
	public static final SettingType POTIONEFFECT 	= new SettingType(SettingPotionEffect.class, 	PotionEffect.class) {
		
		@Override
		public Object getDefaultObject() {
			
			if(Bukkit.getServer() == null) return null;
			
			return new PotionEffect(PotionEffectType.SPEED, 20, 1);
		}
		
	};
	public static final SettingType VECTOR 			= new SettingType(SettingVector.class, 			new Vector());
	public static final SettingType WORLD 			= new SettingType(SettingWorld.class, 			World.class) {
		
		@Override
		public Object getDefaultObject() {
			
			if(Bukkit.getServer() == null) return null;
			if(Bukkit.getWorlds() == null) return null;
			if(Bukkit.getWorlds().size() < 1) return null;
			
			return Bukkit.getWorlds().get(0);
		}
		
	};
	
	/**
	 * List
	 */
	
	public static final SettingType LOCATIONLIST 	= new SettingType(SettingLocationList.class, 	true, Location.class) {
		
		@Override
		public Object getDefaultObject() {
			
			if(Bukkit.getServer() == null) return null;
			if(Bukkit.getWorlds() == null) return null;
			if(Bukkit.getWorlds().size() < 1) return null;
			
			return new Location(Bukkit.getWorlds().get(0), 0, 0, 0);
		}
		
	};
	
	@Getter private static final List<SettingType> types;
	
	static{
		
		types = new ArrayList<SettingType>();
		
		for(Field field : BukkitSettingType.class.getDeclaredFields()){

			Class<?> returnType = ReflectionUtilsBukkit.DataType.getPrimitive(field.getType());

			if(Modifier.isStatic(field.getModifiers()) == false) continue;
			if(returnType.isAssignableFrom(SettingType.class) == false) continue;

			if(field.isAccessible() == false){
				field.setAccessible(true);
			}

			try {
				types.add((SettingType) ReflectionUtilsBukkit.getValue(null, BukkitSettingType.class, true, field.getName()));
			} catch (Throwable e) {}

			if(field.isAccessible()){
				field.setAccessible(false);
			}

		}
		
		SettingType.getTypes().addAll(types);
		
	}

}
