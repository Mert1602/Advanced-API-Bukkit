package me.mert1602.advancedapi;

import java.util.logging.Level;

import org.bukkit.plugin.Plugin;

public class BukkitLog {

	private static Plugin plugin;

	private BukkitLog(){}

	public static void setPlugin(Plugin plugin){

		if(BukkitLog.plugin != null) return;
		BukkitLog.plugin = plugin;

	}

	public static void info(String... strings){

		if(BukkitLog.plugin == null){

			BukkitLog.log("[Info]", strings);

		}else{

			for(String s : strings){
				BukkitLog.plugin.getLogger().info(s);
			}

		}

	}

	public static void error(String... strings){

		if(BukkitLog.plugin == null){

			BukkitLog.log("[Error]", strings);

		}else{

			for(String s : strings){
				BukkitLog.plugin.getLogger().severe(s);
			}

		}

	}

	public static void debug(String... strings){

		if(BukkitLog.plugin == null){

			BukkitLog.log("[Debug]", strings);

		}else{

			for(String s : strings){
				BukkitLog.plugin.getLogger().log(Level.parse("Debug"), s);
			}

		}

	}

	public static void exception(Throwable throwable, Class<?> clazz, int line){

		if(BukkitLog.plugin == null){
			BukkitLog.log("[Error]", "'" + throwable.getMessage() + "' in '" + clazz.getName() + "' at line '" + line + "'");
		}else{
			BukkitLog.plugin.getLogger().severe("'" + throwable.getMessage() + "' in '" + clazz.getName() + "' at line '" + line + "'");
		}

	}



	public static void log(String prefix, String... strings){

		for(String s : strings){

			if(BukkitLog.plugin == null){
				System.out.println("[" + prefix + "] " + s);
			}else{
				System.out.println("[" + BukkitLog.plugin.getName() + "] [" + prefix + "] " + s);
			}

		}

	}

}
