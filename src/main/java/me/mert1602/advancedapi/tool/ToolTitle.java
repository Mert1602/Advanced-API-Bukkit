package me.mert1602.advancedapi.tool;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.mert1602.advancedapi.ReflectionUtils;
import me.mert1602.advancedapi.ReflectionUtilsBukkit;

public class ToolTitle {

	private ToolTitle() {}

	public static void sendTitle(final Player bukkitPlayer, final String title){

		try{

			for(final Class<?> clazz : ReflectionUtilsBukkit.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutTitle").getClasses()){

				if(clazz.getSimpleName().equalsIgnoreCase("EnumTitleAction") == false) {
					return;
				}

				final Object enumTitleAction_TITLE =  ReflectionUtils.getValue(null, clazz, true, "TITLE");

				final Object packet = ReflectionUtils.instantiateObject(
						ReflectionUtilsBukkit.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutTitle"),
						enumTitleAction_TITLE,
						ToolBukkit.getIChatBaseComponent(title));

				ToolBukkit.sendPacket(bukkitPlayer, packet);

				return;
			}

		}catch(final Throwable e){}

	}

	public static void sendTitle(final Player bukkitPlayer, final String title, final int fadein, final int show, final int fadeout){

		try{

			for(final Class<?> clazz : ReflectionUtilsBukkit.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutTitle").getClasses()){

				if(clazz.getSimpleName().equalsIgnoreCase("EnumTitleAction") == false) {
					return;
				}

				final Object enumTitleAction_TITLE =  ReflectionUtils.getValue(null, clazz, true, "TITLE");

				final Object packet = ReflectionUtils.instantiateObject(
						ReflectionUtilsBukkit.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutTitle"),
						fadein, show, fadeout);

				final Object packet1 = ReflectionUtils.instantiateObject(
						ReflectionUtilsBukkit.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutTitle"),
						enumTitleAction_TITLE,
						ToolBukkit.getIChatBaseComponent(title));

				ToolBukkit.sendPacket(bukkitPlayer, packet);
				ToolBukkit.sendPacket(bukkitPlayer, packet1);

				return;
			}

		}catch(final Throwable e){}

	}

	public static void sendSubTitle(final Player bukkitPlayer, final String subTitle){

		try{

			for(final Class<?> clazz : ReflectionUtilsBukkit.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutTitle").getClasses()){

				if(clazz.getSimpleName().equalsIgnoreCase("EnumTitleAction") == false) {
					return;
				}

				final Object enumTitleAction_SUBTITLE =  ReflectionUtils.getValue(null, clazz, true, "SUBTITLE");
				final Object enumTitleAction_TITLE =  ReflectionUtils.getValue(null, clazz, true, "TITLE");

				final Object packet = ReflectionUtils.instantiateObject(
						ReflectionUtilsBukkit.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutTitle"),
						enumTitleAction_SUBTITLE,
						ToolBukkit.getIChatBaseComponent(subTitle));

				final Object packet1 = ReflectionUtils.instantiateObject(
						ReflectionUtilsBukkit.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutTitle"),
						enumTitleAction_TITLE,
						ToolBukkit.getIChatBaseComponent(""));

				ToolBukkit.sendPacket(bukkitPlayer, packet);
				ToolBukkit.sendPacket(bukkitPlayer, packet1);

				return;
			}

		}catch(final Throwable e){}

	}

	public static void sendSubTitle(final Player bukkitPlayer, final String subTitle, final int fadein, final int show, final int fadeout){

		try{

			for(final Class<?> clazz : ReflectionUtilsBukkit.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutTitle").getClasses()){

				if(clazz.getSimpleName().equalsIgnoreCase("EnumTitleAction") == false) {
					return;
				}

				final Object enumTitleAction_SUBTITLE =  ReflectionUtils.getValue(null, clazz, true, "SUBTITLE");
				final Object enumTitleAction_TITLE =  ReflectionUtils.getValue(null, clazz, true, "TITLE");

				final Object packet = ReflectionUtils.instantiateObject(
						ReflectionUtilsBukkit.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutTitle"),
						fadein, show, fadeout);

				final Object packet1 = ReflectionUtils.instantiateObject(
						ReflectionUtilsBukkit.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutTitle"),
						enumTitleAction_SUBTITLE,
						ToolBukkit.getIChatBaseComponent(subTitle));

				final Object packet2 = ReflectionUtils.instantiateObject(
						ReflectionUtilsBukkit.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutTitle"),
						enumTitleAction_TITLE,
						ToolBukkit.getIChatBaseComponent(""));

				ToolBukkit.sendPacket(bukkitPlayer, packet);
				ToolBukkit.sendPacket(bukkitPlayer, packet1);
				ToolBukkit.sendPacket(bukkitPlayer, packet2);

				return;
			}

		}catch(final Throwable e){}

	}

	public static void sendTitle(final Player bukkitPlayer, final String title, final String subTitle){

		try{

			for(final Class<?> clazz : ReflectionUtilsBukkit.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutTitle").getClasses()){

				if(clazz.getSimpleName().equalsIgnoreCase("EnumTitleAction") == false) {
					return;
				}

				final Object enumTitleAction_SUBTITLE =  ReflectionUtils.getValue(null, clazz, true, "SUBTITLE");
				final Object enumTitleAction_TITLE =  ReflectionUtils.getValue(null, clazz, true, "TITLE");

				final Object packet = ReflectionUtils.instantiateObject(
						ReflectionUtilsBukkit.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutTitle"),
						enumTitleAction_SUBTITLE,
						ToolBukkit.getIChatBaseComponent(subTitle));

				final Object packet1 = ReflectionUtils.instantiateObject(
						ReflectionUtilsBukkit.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutTitle"),
						enumTitleAction_TITLE,
						ToolBukkit.getIChatBaseComponent(title));

				ToolBukkit.sendPacket(bukkitPlayer, packet);
				ToolBukkit.sendPacket(bukkitPlayer, packet1);

				return;
			}

		}catch(final Throwable e){}

	}

	public static void sendTitle(final Player bukkitPlayer, final String title, final String subTitle, final int fadein, final int show, final int fadeout){

		try{

			for(final Class<?> clazz : ReflectionUtilsBukkit.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutTitle").getClasses()){

				if(clazz.getSimpleName().equalsIgnoreCase("EnumTitleAction") == false) {
					return;
				}

				final Object enumTitleAction_SUBTITLE =  ReflectionUtils.getValue(null, clazz, true, "SUBTITLE");
				final Object enumTitleAction_TITLE =  ReflectionUtils.getValue(null, clazz, true, "TITLE");

				final Object packet = ReflectionUtils.instantiateObject(
						ReflectionUtilsBukkit.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutTitle"),
						fadein, show, fadeout);

				final Object packet1 = ReflectionUtils.instantiateObject(
						ReflectionUtilsBukkit.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutTitle"),
						enumTitleAction_SUBTITLE,
						ToolBukkit.getIChatBaseComponent(subTitle));

				final Object packet2 = ReflectionUtils.instantiateObject(
						ReflectionUtilsBukkit.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutTitle"),
						enumTitleAction_TITLE,
						ToolBukkit.getIChatBaseComponent(title));

				ToolBukkit.sendPacket(bukkitPlayer, packet);
				ToolBukkit.sendPacket(bukkitPlayer, packet1);
				ToolBukkit.sendPacket(bukkitPlayer, packet2);

				return;
			}

		}catch(final Throwable e){}

	}



	public static void sendGlobalTitle(final String title){

		for(final Player player : Bukkit.getOnlinePlayers()){
			ToolTitle.sendTitle(player, title);
		}

	}

	public static void sendGlobalTitle(final String title, final int fadein, final int show, final int fadeout){

		for(final Player player : Bukkit.getOnlinePlayers()){
			ToolTitle.sendTitle(player, title, fadein, show, fadeout);
		}

	}

	public static void sendGlobalSubTitle(final String subTitle){

		for(final Player player : Bukkit.getOnlinePlayers()){
			ToolTitle.sendSubTitle(player, subTitle);
		}

	}

	public static void sendGlobalSubTitle(final String subTitle, final int fadein, final int show, final int fadeout){

		for(final Player player : Bukkit.getOnlinePlayers()){
			ToolTitle.sendSubTitle(player, subTitle, fadein, show, fadeout);
		}

	}

	public static void sendGlobalTitle(final String title, final String subTitle){

		for(final Player player : Bukkit.getOnlinePlayers()){
			ToolTitle.sendTitle(player, title, subTitle);
		}

	}

	public static void sendGlobalTitle(final String title, final String subTitle, final int fadein, final int show, final int fadeout){

		for(final Player player : Bukkit.getOnlinePlayers()){
			ToolTitle.sendTitle(player, title, subTitle, fadein, show, fadeout);
		}

	}

}
