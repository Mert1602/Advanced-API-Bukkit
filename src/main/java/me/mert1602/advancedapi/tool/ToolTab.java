package me.mert1602.advancedapi.tool;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.mert1602.advancedapi.ReflectionUtils;
import me.mert1602.advancedapi.ReflectionUtilsBukkit;

public class ToolTab {

	private ToolTab() {}

	public static void sendTabList(final Player bukkitPlayer, final String header, final String footer){

		try{

			final Object packet = ReflectionUtils.instantiateObject(
					ReflectionUtilsBukkit.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutPlayerListHeaderFooter"), ToolBukkit.getIChatBaseComponent(header));

			ReflectionUtils.setValue(packet, true, "b", ToolBukkit.getIChatBaseComponent(footer));
			ToolBukkit.sendPacket(bukkitPlayer, packet);

		}catch(final Throwable e){}

	}

	public static void sendTabHeader(final Player bukkitPlayer, final String header){

		try{

			final Object packet = ReflectionUtils.instantiateObject(
					ReflectionUtilsBukkit.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutPlayerListHeaderFooter"), ToolBukkit.getIChatBaseComponent(header));

			ToolBukkit.sendPacket(bukkitPlayer, packet);

		}catch(final Throwable e){}

	}

	public static void sendTabFooter(final Player bukkitPlayer, final String footer){

		try{

			final Object packet = ReflectionUtils.instantiateObject(
					ReflectionUtilsBukkit.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutPlayerListHeaderFooter"), ToolBukkit.getIChatBaseComponent(""));

			ReflectionUtils.setValue(packet, true, "b", ToolBukkit.getIChatBaseComponent(footer));
			ToolBukkit.sendPacket(bukkitPlayer, packet);

		}catch(final Throwable e){}

	}



	public static void sendGlobalTabList(final String header, final String footer){

		for(final Player bukkitPlayer : Bukkit.getOnlinePlayers()){
			ToolTab.sendTabList(bukkitPlayer, header, footer);
		}

	}

	public static void sendGlobalTabHeader(final String header){

		for(final Player bukkitPlayer : Bukkit.getOnlinePlayers()){
			ToolTab.sendTabHeader(bukkitPlayer, header);
		}

	}

	public static void sendGlobalTabFooter(final String footer){

		for(final Player bukkitPlayer : Bukkit.getOnlinePlayers()){
			ToolTab.sendTabFooter(bukkitPlayer, footer);
		}

	}

}
