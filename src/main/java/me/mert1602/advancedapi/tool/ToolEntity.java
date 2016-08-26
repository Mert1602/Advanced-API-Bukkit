package me.mert1602.advancedapi.tool;

import org.bukkit.entity.Creature;

import me.mert1602.advancedapi.ReflectionUtils;
import me.mert1602.advancedapi.ReflectionUtilsBukkit;

public class ToolEntity {

	private ToolEntity() {}

	public static void clearPaths(final Creature creature){

		try {

			final Object entityCreature = ReflectionUtils.invokeMethod(creature, "getHandle");
			final Object goalSelector = ReflectionUtils.getValue(entityCreature, true, "goalSelector");
			final Object targetSelector = ReflectionUtils.getValue(entityCreature, true, "targetSelector");

			ReflectionUtils.setValue(goalSelector, true, "b", ReflectionUtils.instantiateObject(ReflectionUtilsBukkit.PackageType.MINECRAFT_SERVER.getClass("util.UnsafeList")));
			ReflectionUtils.setValue(goalSelector, true, "c", ReflectionUtils.instantiateObject(ReflectionUtilsBukkit.PackageType.MINECRAFT_SERVER.getClass("util.UnsafeList")));

			ReflectionUtils.setValue(targetSelector, true, "b", ReflectionUtils.instantiateObject(ReflectionUtilsBukkit.PackageType.MINECRAFT_SERVER.getClass("util.UnsafeList")));
			ReflectionUtils.setValue(targetSelector, true, "c", ReflectionUtils.instantiateObject(ReflectionUtilsBukkit.PackageType.MINECRAFT_SERVER.getClass("util.UnsafeList")));

		} catch (final Throwable e) {}

	}

}
