package me.mert1602.advancedapi.tool;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ToolEffect {

	private ToolEffect(){}

	public static void addPotionEffect(Player bukkitPlayer, String... strings){
		addPotionEffect(bukkitPlayer, true, strings);
	}

	public static void addPotionEffect(Player bukkitPlayer, boolean force, String... strings){

		for(String string : strings){

			if(potionEffectFromString(string) == null) continue;

			addPotionEffect(bukkitPlayer, force, potionEffectFromString(string));

		}

	}

	public static void addPotionEffect(Player bukkitPlayer, PotionEffect... effects){
		addPotionEffect(bukkitPlayer, true, effects);
	}

	public static void addPotionEffect(Player bukkitPlayer, boolean force, PotionEffect... effects){

		for(PotionEffect effect : effects){
			bukkitPlayer.addPotionEffect(effect, force);
		}

	}

	public static void clearPotionEffect(Player bukkitPlayer){

		for(PotionEffect effect : bukkitPlayer.getActivePotionEffects()){
			bukkitPlayer.removePotionEffect(effect.getType());
		}

	}



	public static PotionEffect potionEffectFromString(String text){

		String[] stringList = text.split(";");
		PotionEffectType type = null;
		int duration = 20;
		int amplifier = 1;
		boolean ambient = true;

		for(String string : stringList){

			if(string.startsWith("type=")){

				if(PotionEffectType.getByName(ToolString.getValue(string, "=", "type")) != null){
					type = PotionEffectType.getByName(ToolString.getValue(string, "=", "type"));
				}

			}else if(string.startsWith("duration=")){

				if(ToolNumber.isNumeric(ToolString.getValue(string, "=", "duration"))){
					duration = Integer.valueOf(ToolString.getValue(string, "=", "duration"));
				}

			}else if(string.startsWith("amplifier=")){

				if(ToolNumber.isNumeric(ToolString.getValue(string, "=", "amplifier"))){
					amplifier = Integer.valueOf(ToolString.getValue(string, "=", "amplifier"));
				}

			}else if(string.startsWith("ambient=")){

				ambient = Boolean.valueOf(ToolString.getValue(string, "=", "ambient"));

			}

		}

		if(type == null) return null;

		return new PotionEffect(type, duration, amplifier, ambient);
	}

	public static String potionEffectToString(PotionEffect effect){

		StringBuilder sb = new StringBuilder();
		PotionEffectType type = effect.getType();
		int duration = effect.getDuration();
		int amplifier = effect.getAmplifier();
		boolean ambient = true;

		sb.append("type=" + type.getName().toLowerCase() + ";");
		sb.append("duration=" + duration + ";");
		sb.append("amplifier=" + amplifier + ";");
		sb.append("ambient=" + ambient + ";");

		return sb.toString();
	}

}
