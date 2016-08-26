package me.mert1602.advancedapi.tool;

import org.bukkit.util.Vector;

import me.mert1602.advancedapi.BukkitLog;

public class ToolVector {
	
	private ToolVector() {}
	
	public static String vectorToString(final Vector vector){

		final StringBuilder sb = new StringBuilder();

		sb.append(vector.getX() + ";");
		sb.append(vector.getY() + ";");
		sb.append(vector.getZ());

		return sb.toString();
	}
	
	public static Vector vectorFromString(final String vectorString){

		final String[] args = vectorString.split(";");

		try{
			return new Vector(Double.valueOf(args[0]), Double.valueOf(args[1]), Double.valueOf(args[2]));
		}catch(Throwable e){
			BukkitLog.exception(e, ToolVector.class, Tool.getLineNumber());
			return new Vector();
		}

	}

}
