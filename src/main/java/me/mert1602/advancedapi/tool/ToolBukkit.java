package me.mert1602.advancedapi.tool;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.mert1602.advancedapi.ReflectionUtils;
import me.mert1602.advancedapi.ReflectionUtilsBukkit;

public final class ToolBukkit {

	private ToolBukkit() {}
	
	public static final Location EMPTY_LOCATION_LIST[] = new Location[0];

	public static void sendPacket(final Player player, final Object packet){

		try{

			final Object entityPlayer = ReflectionUtils.invokeMethod(player, "getHandle");
			final Object playerConnection = ReflectionUtils.getValue(entityPlayer, true, "playerConnection");

			ReflectionUtils.invokeMethod(playerConnection, "sendPacket", packet);

		}catch(final Throwable e){}

	}

	public static Object getIChatBaseComponent(final String text){

		try{

			for(final Class<?> clazz : ReflectionUtilsBukkit.PackageType.MINECRAFT_SERVER.getClass("IChatBaseComponent").getClasses()){

				if(clazz.getSimpleName().equalsIgnoreCase("ChatSerializer") == false) {
					return null;
				}

				final String revisedText = "{\"text\": \"" + text + "\"}";

				return ReflectionUtils.invokeMethod(null, clazz, "a", revisedText);
			}

			return null;

		}catch(final Throwable e){
			return null;
		}
	}

	public static ChatColor getRandomChatColor(){

		final List<ChatColor> ccList = new ArrayList<ChatColor>();

		for(final ChatColor cc : ChatColor.values()){
			if(cc.isColor() && (cc != ChatColor.WHITE)) {
				ccList.add(cc);
			}
		}

		return ccList.get(Tool.getRandom().nextInt(ccList.size()));
	}

}
