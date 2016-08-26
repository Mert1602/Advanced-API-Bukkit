package me.mert1602.advancedapi.tool;

import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class ToolAction {

	private ToolAction() {}

	public static void velocity(final Entity ent, final double str, final double yAdd, final double yMax, final boolean groundBoost){
		ToolAction.velocity(ent, ent.getLocation().getDirection(), str, false, 0.0D, yAdd, yMax, groundBoost);
	}

	public static void velocity(final Entity ent, final Vector vec, final double str, final boolean ySet, final double yBase, final double yAdd, final double yMax, final boolean groundBoost){

		if ((Double.isNaN(vec.getX())) || (Double.isNaN(vec.getY())) || (Double.isNaN(vec.getZ())) || (vec.length() == 0.0D)) {
			return;
		}

		if (ySet) {
			vec.setY(yBase);
		}

		vec.normalize();
		vec.multiply(str);
		vec.setY(vec.getY() + yAdd);

		if (vec.getY() > yMax) {
			vec.setY(yMax);
		}

		if ((groundBoost) && (ent.isOnGround())) {
			vec.setY(vec.getY() + 0.2D);
		}

		ent.setFallDistance(0.0F);
		ent.setVelocity(vec);

	}

}
