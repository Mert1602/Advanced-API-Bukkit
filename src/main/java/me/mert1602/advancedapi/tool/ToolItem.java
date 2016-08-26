package me.mert1602.advancedapi.tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public final class ToolItem {

	private ToolItem() {}

	public static ItemStack createItemStack(final Material type){
		return new ItemStack(type);
	}

	public static ItemStack createItemStack(final Material type, final int amount){
		return new ItemStack(type, amount);
	}

	public static ItemStack createItemStack(final Material type, final String displayname){

		final ItemStack itemstack = new ItemStack(type);
		final ItemMeta itemmeta = itemstack.getItemMeta();

		itemmeta.setDisplayName(displayname);

		itemstack.setItemMeta(itemmeta);

		return itemstack;
	}

	public static ItemStack createItemStack(final Material type, final String displayname, final List<String> lore){

		final ItemStack itemstack = new ItemStack(type);
		final ItemMeta itemmeta = itemstack.getItemMeta();

		itemmeta.setDisplayName(displayname);
		itemmeta.setLore(lore);

		itemstack.setItemMeta(itemmeta);

		return itemstack;
	}

	public static ItemStack createItemStack(final Material type, final int amount, final String displayname){

		final ItemStack itemstack = new ItemStack(type, amount);
		final ItemMeta itemmeta = itemstack.getItemMeta();

		itemmeta.setDisplayName(displayname);

		itemstack.setItemMeta(itemmeta);

		return itemstack;
	}

	public static ItemStack createItemStack(final Material type, final int amount, final String displayname, final List<String> lore){

		final ItemStack itemstack = new ItemStack(type, amount);
		final ItemMeta itemmeta = itemstack.getItemMeta();

		itemmeta.setDisplayName(displayname);
		itemmeta.setLore(lore);

		itemstack.setItemMeta(itemmeta);

		return itemstack;
	}

	public static ItemStack createItemStack(final Material type, final int amount, final short damage, final String displayname){

		final ItemStack itemstack = new ItemStack(type, amount, damage);
		final ItemMeta itemmeta = itemstack.getItemMeta();

		itemmeta.setDisplayName(displayname);

		itemstack.setItemMeta(itemmeta);

		return itemstack;
	}

	public static ItemStack createItemStack(final Material type, final int amount, final short damage, final String displayname, final List<String> lore){

		final ItemStack itemstack = new ItemStack(type, amount, damage);
		final ItemMeta itemmeta = itemstack.getItemMeta();

		itemmeta.setDisplayName(displayname);
		itemmeta.setLore(lore);

		itemstack.setItemMeta(itemmeta);

		return itemstack;
	}

	public static ItemStack createItemStack(final Material type, final int amount, final short damage, final byte data, final String displayname){

		final ItemStack itemstack = new ItemStack(type, amount, damage, data);
		final ItemMeta itemmeta = itemstack.getItemMeta();

		itemmeta.setDisplayName(displayname);

		itemstack.setItemMeta(itemmeta);

		return itemstack;
	}

	public static ItemStack createItemStack(final Material type, final int amount, final short damage, final byte data, final String displayname, final List<String> lore){

		final ItemStack itemstack = new ItemStack(type, amount, damage, data);
		final ItemMeta itemmeta = itemstack.getItemMeta();

		itemmeta.setDisplayName(displayname);
		itemmeta.setLore(lore);

		itemstack.setItemMeta(itemmeta);

		return itemstack;
	}

	public static ItemStack createLeathArmorMetaItemStack(final Material type, final Color color){

		final ItemStack itemstack = ToolItem.createItemStack(type);
		final LeatherArmorMeta itemmeta = (LeatherArmorMeta) itemstack.getItemMeta();

		itemmeta.setColor(color);
		itemstack.setItemMeta(itemmeta);

		return itemstack;
	}


	public static ItemStack createItemStackWithData(final Material type, final byte data){
		return ToolItem.createItemStack(type, 1, (short) 0, data, "");
	}





	public static String itemStackToString(final ItemStack itemStack){

		String itemstackString = "";

		if((itemStack.getType() == Material.POTION) && (Potion.fromItemStack(itemStack) != null) && (Potion.fromItemStack(itemStack).getType() != null)){

			final Potion potion = Potion.fromItemStack(itemStack);

			itemstackString = itemstackString + "potion=" + potion.getType().toString().toLowerCase() + ";";
			itemstackString = itemstackString + "level=" + potion.getLevel() + ";";
			itemstackString = itemstackString + "splash=" + potion.isSplash() + ";";
			itemstackString = itemstackString + "extended=" + potion.hasExtendedDuration() + ";";

			itemstackString = itemstackString + "data=" + itemStack.getData().getData() + ";";
			itemstackString = itemstackString + "amount=" + itemStack.getAmount() + ";";
			itemstackString = itemstackString + "durability=" + itemStack.getDurability() + ";";

			//DisplayName
			if(itemStack.getItemMeta().hasDisplayName()){
				itemstackString = itemstackString + "displayname=" + itemStack.getItemMeta().getDisplayName() + ";";
			} else {
				itemstackString = itemstackString + "displayname=" + "null" + ";";
			}

			//Lore
			if(itemStack.getItemMeta().hasLore()){

				for(final String lore : itemStack.getItemMeta().getLore()){
					itemstackString = itemstackString + "lore=" + lore + ";";
				}

			} else {
				itemstackString = itemstackString + "lore=" + "null" + ";";
			}

		}else if(itemStack.hasItemMeta() && (itemStack.getItemMeta() instanceof SkullMeta) && ((SkullMeta)itemStack.getItemMeta()).hasOwner()){

			final SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();

			itemstackString = itemstackString + "skull=" + skullMeta.getOwner() + ";";

			itemstackString = itemstackString + "type=" + itemStack.getType().toString().toLowerCase() + ";";
			itemstackString = itemstackString + "data=" + itemStack.getData().getData() + ";";
			itemstackString = itemstackString + "amount=" + itemStack.getAmount() + ";";
			itemstackString = itemstackString + "durability=" + itemStack.getDurability() + ";";

			//Enchantment
			for(final Enchantment ench : itemStack.getEnchantments().keySet()){
				itemstackString = itemstackString + "enchantment:type=" + ench.getName().toLowerCase() + ",level=" + itemStack.getEnchantments().get(ench) + ";";
			}

			//Displayname
			if(itemStack.getItemMeta().hasDisplayName()){
				itemstackString = itemstackString + "displayname=" + itemStack.getItemMeta().getDisplayName() + ";";
			} else {
				itemstackString = itemstackString + "displayname=" + "null" + ";";
			}

			//Lore
			if(itemStack.getItemMeta().hasLore()){

				for(final String lore : itemStack.getItemMeta().getLore()){
					itemstackString = itemstackString + "lore=" + lore + ";";
				}

			} else {
				itemstackString = itemstackString + "lore=" + "null" + ";";
			}

		}else if(itemStack.hasItemMeta() && (itemStack.getItemMeta() instanceof LeatherArmorMeta)){

			final LeatherArmorMeta armorMeta = (LeatherArmorMeta) itemStack.getItemMeta();

			itemstackString = itemstackString + "leatherArmor=" + itemStack.getType().toString().toLowerCase() + ";";
			itemstackString = itemstackString + "color=" + armorMeta.getColor().asRGB() + ";";

			itemstackString = itemstackString + "data=" + itemStack.getData().getData() + ";";
			itemstackString = itemstackString + "amount=" + itemStack.getAmount() + ";";
			itemstackString = itemstackString + "durability=" + itemStack.getDurability() + ";";

			//Enchantment
			for(final Enchantment ench : itemStack.getEnchantments().keySet()){
				itemstackString = itemstackString + "enchantment:type=" + ench.getName().toLowerCase() + ",level=" + itemStack.getEnchantments().get(ench) + ";";
			}

			//Displayname
			if(itemStack.getItemMeta().hasDisplayName()){
				itemstackString = itemstackString + "displayname=" + itemStack.getItemMeta().getDisplayName() + ";";
			} else {
				itemstackString = itemstackString + "displayname=" + "null" + ";";
			}

			//Lore
			if(itemStack.getItemMeta().hasLore()){

				for(final String lore : itemStack.getItemMeta().getLore()){
					itemstackString = itemstackString + "lore=" + lore + ";";
				}

			} else {
				itemstackString = itemstackString + "lore=" + "null" + ";";
			}

		}else{

			itemstackString = itemstackString + "type=" + itemStack.getType().toString().toLowerCase() + ";";
			itemstackString = itemstackString + "data=" + itemStack.getData().getData() + ";";
			itemstackString = itemstackString + "amount=" + itemStack.getAmount() + ";";
			itemstackString = itemstackString + "durability=" + itemStack.getDurability() + ";";


			for(final Enchantment ench : itemStack.getEnchantments().keySet()){
				itemstackString = itemstackString + "enchantment:type=" + ench.getName().toLowerCase() + ",level=" + itemStack.getEnchantments().get(ench) + ";";
			}

			//Displayname
			if(itemStack.getItemMeta().hasDisplayName()){
				itemstackString = itemstackString + "displayname=" + itemStack.getItemMeta().getDisplayName() + ";";
			} else {
				itemstackString = itemstackString + "displayname=" + "null" + ";";
			}

			//Lore
			if(itemStack.getItemMeta().hasLore()){

				for(final String lore : itemStack.getItemMeta().getLore()){
					itemstackString = itemstackString + "lore=" + lore + ";";
				}

			} else {
				itemstackString = itemstackString + "lore=" + "null" + ";";
			}

		}

		return itemstackString;
	}

	public static ItemStack itemStackFromString(String text){

		if(text.startsWith("slot")){

			final StringBuilder sb = new StringBuilder();

			for(final String s : text.split(";")){

				if(s.startsWith("slot")) {
					continue;
				}
				sb.append(s + ";");

			}

			text = sb.toString();

		}

		final List<String> textList = ToolString.getStringParts(text, ";");

		Material type = Material.STONE;
		byte data = 0;
		int amount = 1;
		short durability = 0;

		final HashMap<Enchantment, Integer> enchantments = new HashMap<Enchantment, Integer>();

		ItemStack itemstack = null;
		ItemMeta itemmeta = null;
		List<String> lore;

		boolean isPotion = false;
		boolean isSkull = false;
		boolean isLeatherArmor = false;

		if(text.startsWith("skull")){

			isSkull = true;

			String owner = "";

			for(String t : textList){

				while(ToolString.isChar(t, 0, ' ')){
					t = ToolString.removeChars(t, 1);
				}

				if(t.startsWith("skull")){
					owner = ToolString.getValue(t, "=", "skull");
				}

			}

			itemstack = new ItemStack(Material.SKULL_ITEM, 1, (short)0, (byte)3);
			itemmeta = itemstack.getItemMeta();

			((SkullMeta)itemmeta).setOwner(owner);

		}else if(text.startsWith("potion")){

			isPotion = true;

			PotionType potionType = PotionType.SPEED;
			int level = 1;
			boolean splash = false;
			boolean extended = false;


			for(String t : textList){

				while(ToolString.isChar(t, 0, ' ')){
					t = ToolString.removeChars(t, 1);
				}

				if(t.startsWith("potion")){

					if(PotionType.valueOf(ToolString.getValue(t, "=", "potion").toUpperCase()) != null){
						potionType = PotionType.valueOf(ToolString.getValue(t, "=", "potion").toUpperCase());
					}

				}else if(t.startsWith("level")){

					if(ToolNumber.isNumeric(ToolString.getValue(t, "=", "level"))){
						level = Integer.valueOf(ToolString.getValue(t, "=", "level"));
					}

				}else if(t.startsWith("splash")){
					splash = Boolean.valueOf(ToolString.getValue(t, "=", "splash"));
				}else if(t.startsWith("extended")){
					extended = Boolean.valueOf(ToolString.getValue(t, "=", "extended"));
				}

			}

			final Potion potion = new Potion(potionType, level, splash, extended);

			itemstack = potion.toItemStack(amount);
			itemmeta = itemstack.getItemMeta();

		}else if(text.startsWith("leatherArmor")){

			isLeatherArmor = true;

			Material armorType = Material.LEATHER_BOOTS;
			Color color = Color.fromRGB(1);

			for(String t : textList){

				while(ToolString.isChar(t, 0, ' ')){
					t = ToolString.removeChars(t, 1);
				}

				if(t.startsWith("leatherArmor")){

					if(Material.matchMaterial(ToolString.getValue(t, "=", "leatherArmor").toUpperCase()) != null){
						armorType = Material.matchMaterial(ToolString.getValue(t, "=", "leatherArmor").toUpperCase());
					}

				}else if(t.startsWith("color")){

					if(ToolNumber.isNumeric(ToolString.getValue(t, "=", "color"))){
						color = Color.fromRGB(Integer.valueOf(ToolString.getValue(t, "=", "color")));
					}

				}

			}

			itemstack = new ItemStack(armorType);
			itemmeta = itemstack.getItemMeta();

			((LeatherArmorMeta)itemmeta).setColor(color);

		}

		for(String t : textList){

			while(ToolString.isChar(t, 0, ' ')){
				t = ToolString.removeChars(t, 1);
			}

			if(t.startsWith("type")){

				if(Material.matchMaterial(ToolString.getValue(t, "=", "type").toUpperCase()) != null){
					type = Material.matchMaterial(ToolString.getValue(t, "=", "type").toUpperCase());
				}

			}else if(t.startsWith("data")){

				if(ToolNumber.isNumeric(ToolString.getValue(t, "=", "data"))){
					data = Byte.valueOf(ToolString.getValue(t, "=", "data"));
				}

			}else if(t.startsWith("amount")){

				if(ToolNumber.isNumeric(ToolString.getValue(t, "=", "amount"))){
					amount = Integer.valueOf(ToolString.getValue(t, "=", "amount"));
				}

			}else if(t.startsWith("damage")){

				if(ToolNumber.isNumeric(ToolString.getValue(t, "=", "damage"))){
					durability = Short.valueOf(ToolString.getValue(t, "=", "damage"));
				}

			}else if(t.startsWith("enchantment")){

				Enchantment ench = null;
				int level = 1602;

				while(ToolString.isChar(t, 0, ' ')){
					t = ToolString.removeChars(t, 1);
				}

				t = t.replaceAll("enchantment:", "");

				final List<String> enchantmentTextList = ToolString.getStringParts(t, ",");

				for(String t2 : enchantmentTextList){

					while(ToolString.isChar(t2, 0, ' ')){
						t2 = ToolString.removeChars(t2, 1);
					}

					if(t2.startsWith("type")){

						if(Enchantment.getByName(ToolString.getValue(t2, "=", "type").toUpperCase()) != null){

							ench = Enchantment.getByName(ToolString.getValue(t2, "=", "type").toUpperCase());

						}else if(ToolNumber.isNumeric(ToolString.getValue(t2, "=", "type"))){

							if(Enchantment.getById(Integer.valueOf(ToolString.getValue(t2, "=", "type"))) != null){
								ench = Enchantment.getById(Integer.valueOf(ToolString.getValue(t2, "=", "type")));
							}

						}

					}else if(t2.startsWith("level")){

						if(ToolNumber.isNumeric(ToolString.getValue(t2, "=", "level"))){
							level = Integer.valueOf(ToolString.getValue(t2, "=", "level"));
						}

					}

				}

				if(ench != null){

					if(level == 1602) {
						level = 1;
					}

					enchantments.put(ench, level);

				}

			}

		}

		if((isSkull == false) && (isPotion == false) && (isLeatherArmor == false)){

			itemstack = new ItemStack(type, amount, durability, data);
			itemstack.addUnsafeEnchantments(enchantments);
			itemmeta = itemstack.getItemMeta();

		}else{

			itemstack.addUnsafeEnchantments(enchantments);
			itemstack.setAmount(amount);

		}


		lore = new ArrayList<String>();

		for(String t : textList){

			while(ToolString.isChar(t, 0, ' ')){
				t = ToolString.removeChars(t, 1);
			}

			if(t.startsWith("displayname")){

				if(ToolString.getValue(t, "=", "displayname").equalsIgnoreCase("null") == false){
					itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ToolString.getValue(t, "=", "displayname")));
				}

			}else if(t.startsWith("lore")){

				if(ToolString.getValue(t, "=", "lore").equalsIgnoreCase("null") == false){
					lore.add(ChatColor.translateAlternateColorCodes('&', ToolString.getValue(t, "=", "lore")));
				}

			}

		}

		itemmeta.setLore(lore);
		itemstack.setItemMeta(itemmeta);

		return itemstack;
	}

	public static List<String> itemStackStringListFromInventory(final Inventory inventory){

		final List<String> contentList = new ArrayList<String>();

		for(int i = 0; i < inventory.getSize() ; i++){

			if(inventory.getItem(i) == null) {
				continue;
			}
			if(inventory.getItem(i).getType() == Material.AIR) {
				continue;
			}

			contentList.add("slot=" + i + ";" + ToolItem.itemStackToString(inventory.getItem(i)));

		}

		return contentList;
	}

	public static void setInventoryContents(Inventory inventory, final List<String> itemStackStringList){

		for(final String s : itemStackStringList){

			int slot = 1602;

			for(final String t : s.split(";")){

				if(t.startsWith("slot") == false) {
					continue;
				}

				slot = Integer.valueOf(ToolString.getValue(t, "=", "slot"));

			}

			final ItemStack itemStack = ToolItem.itemStackFromString(s);

			if(slot == 1602){
				inventory.addItem(itemStack);
			} else {
				inventory.setItem(slot, itemStack);
			}

		}

	}
	
	public static boolean canHaveCustomDurability(ItemStack item){
		return item.getType().getMaxDurability() != 0;
    }

}
