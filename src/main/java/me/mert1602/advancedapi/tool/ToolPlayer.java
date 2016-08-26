package me.mert1602.advancedapi.tool;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class ToolPlayer {

	private ToolPlayer() {}

	public static void reset(final Player player, final GameMode gamemode){

		player.resetMaxHealth();
		player.resetPlayerTime();
		player.resetPlayerWeather();

		player.setHealth(player.getMaxHealth());
		player.setFoodLevel(20);
		player.setFireTicks(0);
		player.setGameMode(gamemode);
		player.setWalkSpeed(0.2F);
		player.setLevel(0);
		player.setExp(0.0F);

		for(final PotionEffect effect : player.getActivePotionEffects()){
			player.removePotionEffect(effect.getType());
		}

		player.getInventory().clear();
		player.getInventory().setArmorContents(null);

	}

	public static void sendMessage(final Player player, final String... strings){
		player.sendMessage(strings);
	}

	public static void sendGlobalMessage(final String... strings){

		for(final Player player : Bukkit.getOnlinePlayers()){
			ToolPlayer.sendMessage(player, strings);
		}

	}

	public static void setGlobalLevelBar(final int level){

		for(final Player player : Bukkit.getOnlinePlayers()){
			player.setLevel(level);
		}

	}



	public static void addEffect(final Player player, final String text){

		final String[] stringList = text.split(",");

		PotionEffectType type = null;
		int duration = 20*10;
		int amplifier = 1;
		boolean ambient = false;

		for(String s : stringList){

			if(s.contains("type:")){

				s = s.replace(" ", "");
				s = s.replaceAll("type:", "");

				if(PotionEffectType.getByName(s.toUpperCase()) != null){
					type = PotionEffectType.getByName(s.toUpperCase());
				}

			}else if(s.contains("duration:")){

				s = s.replace(" ", "");
				s = s.replaceAll("duration:", "");
				duration = ToolNumber.fetchInteger(s) * 20;

			}else if(s.contains("amplifier:")){

				s = s.replace(" ", "");
				s = s.replaceAll("amplifier:", "");
				amplifier = ToolNumber.fetchInteger(s);

			}else if(s.contains("ambient:")){

				s = s.replace(" ", "");
				s = s.replaceAll("ambient:", "");
				ambient = Boolean.valueOf(s);

			}

		}

		if(type != null){
			player.addPotionEffect(new PotionEffect(type, duration, amplifier, ambient), true);
		}

	}

}
