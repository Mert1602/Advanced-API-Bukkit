package me.mert1602.advancedapi.tool;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public final class ToolInventory {

	private ToolInventory() {}

	public static Inventory createInventory(final int rows){
		return Bukkit.createInventory(null, 9*rows);
	}

	public static Inventory createInventory(final InventoryHolder owner, final int rows){
		return Bukkit.createInventory(owner, 9*rows);
	}

	public static Inventory createInventory(final String inventorytitle, final int rows){
		return Bukkit.createInventory(null, 9*rows, inventorytitle);
	}

	public static Inventory createInventory(final InventoryHolder owner, final int rows, final String inventorytitle){
		return Bukkit.createInventory(owner, 9*rows, inventorytitle);
	}

	public static Inventory createInventory(final InventoryType type){
		return Bukkit.createInventory(null, type);
	}

	public static Inventory createInventory(final InventoryHolder owner, final InventoryType type){
		return Bukkit.createInventory(owner, type);
	}

	public static Inventory createInventory(final InventoryType type, final String inventorytitle){
		return Bukkit.createInventory(null, type, inventorytitle);
	}

	public static Inventory createInventory(final InventoryHolder owner, final InventoryType type, final String inventorytitle){
		return Bukkit.createInventory(owner, type, inventorytitle);
	}



	public static void fillInventory(final Inventory inv, final ItemStack itemstack){

		for(int i = 0; i < inv.getSize(); i++){

			if((inv.getItem(i) == null) || (inv.getItem(i).getType() == Material.AIR)){
				inv.setItem(i, itemstack);
			}

		}

	}

	public static void fillInventoryRow(final Inventory inv, final ItemStack itemstack, final int startSlot, final int endSlot){

		for(int i = startSlot; i < (endSlot + 1); i++){

			if((inv.getItem(i) == null) || (inv.getItem(i).getType() == Material.AIR)){
				inv.setItem(i, itemstack);
			}

		}

	}

}
