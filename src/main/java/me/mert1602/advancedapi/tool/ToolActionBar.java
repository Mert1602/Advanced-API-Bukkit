package me.mert1602.advancedapi.tool;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.mert1602.advancedapi.ReflectionUtils;
import me.mert1602.advancedapi.ReflectionUtilsBukkit;

public class ToolActionBar {

	private ToolActionBar() {}

	public static void sendActionBar(final Player bukkitPlayer, final String message){

		try {

			final Object packet = ReflectionUtils.instantiateObject(ReflectionUtilsBukkit.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutChat"),
					ToolBukkit.getIChatBaseComponent(message), (byte) 2);

			ToolBukkit.sendPacket(bukkitPlayer, packet);

		} catch (final Throwable e) {}

	}

	public static void sendGlobalActionBar(final String message){

		for(final Player player : Bukkit.getOnlinePlayers()){
			ToolActionBar.sendActionBar(player, message);
		}

	}

}
