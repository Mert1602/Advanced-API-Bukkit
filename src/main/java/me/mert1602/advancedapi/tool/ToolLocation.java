package me.mert1602.advancedapi.tool;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import me.mert1602.advancedapi.BukkitLog;

public class ToolLocation {

	private ToolLocation(){}

	public static String locationToString(final Location location){

		final StringBuilder sb = new StringBuilder();

		sb.append(location.getWorld().getName() + ";");
		sb.append(location.getX() + ";");
		sb.append(location.getY() + ";");
		sb.append(location.getZ() + ";");
		sb.append(location.getYaw() + ";");
		sb.append(location.getPitch());

		return sb.toString();
	}

	public static Location locationFromString(final String locationString){

		final String[] args = locationString.split(";");
		
		try{
			
			return new Location(
					Bukkit.getWorld(args[0]),
					Double.valueOf(args[1]),
					Double.valueOf(args[2]),
					Double.valueOf(args[3]),
					Float.valueOf(args[4]),
					Float.valueOf(args[5])
					);
			
		}catch(Throwable e){
			BukkitLog.exception(e, ToolLocation.class, Tool.getLineNumber());
			return new Location(Bukkit.getWorlds().get(0), 0, 0, 0);
		}

	}

	public static List<Entity> getNearbyEntities(Location location, double radius){

		List<Entity> entities = new ArrayList<Entity>();

		for(Entity entity : location.getWorld().getEntities()){

			if(location.distance(entity.getLocation()) <= radius){
				entities.add(entity);
			}

		}

		return entities;

	}

	public static List<Player> getNearbyPlayers(Location location, double radius){

		List<Player> list = new ArrayList<Player>();

		for(Entity entity : getNearbyEntities(location, radius)){

			if(entity instanceof Player == false) continue;

			list.add((Player) entity);

		}

		return list;

	}

}
