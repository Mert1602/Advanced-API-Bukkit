package me.mert1602.advancedapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import lombok.Getter;
import me.mert1602.advancedapi.basic.BasicInterface;
import me.mert1602.advancedapi.tool.ToolInventory;

public class AdvancedInventory extends Content<BasicInterface> {

	private final static List<AdvancedInventory> advancedInventorys;
	private static boolean listenerRegistered = false;

	static{
		advancedInventorys = new ArrayList<AdvancedInventory>();
	}

	public static AdvancedInventory[] getAdvancedInventorys(){
		return AdvancedInventory.advancedInventorys.toArray(new AdvancedInventory[0]);
	}



	@Getter private final String inventoryName;
	private final ItemStack siteItemStack;
	@Getter private final ItemStack nextPageItemStack;
	@Getter private final ItemStack previewPageItemStack;
	@Getter private final boolean removeOnClose;

	@Getter private List<ItemStack> itemStackList;
	@Getter private HashMap<Integer, List<ItemStack>> inventoryMap;

	public AdvancedInventory(final BasicInterface plugin, final String inventoryName,
			final ItemStack siteItemStack, final ItemStack nextPageItemStack, final ItemStack previewPageItemStack,
			final boolean removeOnClose, final ItemStack... itemStackList) {
		this(plugin, inventoryName, siteItemStack, nextPageItemStack, previewPageItemStack, removeOnClose, Arrays.asList(itemStackList));
	}

	public AdvancedInventory(final BasicInterface plugin, final String inventoryName,
			final ItemStack siteItemStack, final ItemStack nextPageItemStack, final ItemStack previewPageItemStack,
			final boolean removeOnClose, final List<ItemStack> itemStackList) {

		super(plugin);

		this.inventoryName = inventoryName;
		this.siteItemStack = siteItemStack;
		this.nextPageItemStack = nextPageItemStack;
		this.previewPageItemStack = previewPageItemStack;
		this.removeOnClose = removeOnClose;

		this.setItemStackList(itemStackList);

		AdvancedInventory.advancedInventorys.add(this);

		if(AdvancedInventory.listenerRegistered == false){
			Bukkit.getPluginManager().registerEvents(new AdvancedInventoryListener(), plugin);
			AdvancedInventory.listenerRegistered = true;
		}

	}

	public ItemStack getSiteItemStack(final int page){

		final ItemStack itemStack = this.siteItemStack.clone();
		final ItemMeta itemMeta = itemStack.getItemMeta();

		if(itemMeta.hasDisplayName()){
			itemMeta.setDisplayName(itemMeta.getDisplayName().replaceAll("xpagex", page + ""));
		}

		if(itemMeta.hasLore()){

			final List<String> newLore = new ArrayList<String>();

			for(final String s : itemMeta.getLore()){
				newLore.add(s.replaceAll("xpagex", page + ""));
			}

			itemMeta.setLore(newLore);

		}

		itemStack.setItemMeta(itemMeta);
		itemStack.setAmount(page);

		return itemStack;
	}

	public Inventory getInventory(){
		return this.getInventory(1);
	}

	public Inventory getInventory(final int site){

		if(AdvancedInventory.advancedInventorys.contains(this) == false){
			AdvancedInventory.advancedInventorys.add(this);
		}

		int inventorySize = 1;

		if(this.inventoryMap.containsKey(site)){

			if(this.inventoryMap.get(site).size() > 45){
				inventorySize = 6;
			}else if(this.inventoryMap.get(site).size() > 36){
				inventorySize = 5;
			}else if(this.inventoryMap.get(site).size() > 27){
				inventorySize = 4;
			}else if(this.inventoryMap.get(site).size() > 18){
				inventorySize = 3;
			}else if(this.inventoryMap.get(site).size() > 9){
				inventorySize = 2;
			}

			inventorySize++;

		}

		final Inventory inv = ToolInventory.createInventory(this.inventoryName, inventorySize);

		int i = 0;

		if(this.inventoryMap.containsKey(site)){

			for(final ItemStack itemStack : this.inventoryMap.get(site)){
				inv.setItem(i, itemStack); i++;
			}

		}

		if(this.inventoryMap.containsKey(site + 1) || this.inventoryMap.containsKey(site - 1)){
			inv.setItem(inv.getSize() - 5, this.getSiteItemStack(site).clone());
		}

		if(this.inventoryMap.containsKey(site + 1)){
			inv.setItem(inv.getSize() - 3, this.nextPageItemStack);
		}

		if(this.inventoryMap.containsKey(site - 1)){
			inv.setItem(inv.getSize() - 7, this.previewPageItemStack);
		}

		return inv;
	}

	public void setItemStackList(final List<ItemStack> itemStackList){

		this.inventoryMap = new HashMap<Integer, List<ItemStack>>();
		this.itemStackList = itemStackList;

		int site = 1;
		int currentInventorySize = 0;

		List<ItemStack> siteItemStackList = new ArrayList<ItemStack>();;

		for(int i = 0; i < this.itemStackList.size(); i++){

			siteItemStackList.add(this.itemStackList.get(i));

			currentInventorySize++;

			if(currentInventorySize == 45){

				this.inventoryMap.put(site, siteItemStackList);

				siteItemStackList = new ArrayList<ItemStack>();
				currentInventorySize = 0;
				site++;

			}

		}

		if(siteItemStackList.size() > 0){
			this.inventoryMap.put(site, siteItemStackList);
		}

	}

	private class AdvancedInventoryListener implements Listener {

		@EventHandler
		public void on(final InventoryCloseEvent e){

			for(final AdvancedInventory ai : AdvancedInventory.getAdvancedInventorys()){

				if(ai.getInventoryName() != e.getInventory().getTitle())  continue;
				if(ai.isRemoveOnClose() == false) continue;

				AdvancedInventory.advancedInventorys.remove(ai);

			}

		}

		@EventHandler
		public void on(final InventoryClickEvent e){

			if(e.getCurrentItem() == null) return;

			for(final AdvancedInventory ai : AdvancedInventory.getAdvancedInventorys()){

				if(ai.getInventoryName() != e.getInventory().getTitle()) continue;

				e.setCancelled(true);

				if(e.getInventory().getItem(e.getInventory().getSize() - 5) == null) continue;
				if(e.getInventory().getItem(e.getInventory().getSize() - 5).hasItemMeta() == false) continue;
				if(e.getInventory().getItem(e.getInventory().getSize() - 5).getItemMeta().hasDisplayName() == false) continue;

				final Integer currentSite = e.getInventory().getItem(e.getInventory().getSize() - 5).getAmount();

				if(e.getCurrentItem().equals(ai.getNextPageItemStack())){

					if(ai.getInventoryMap().containsKey(currentSite + 1) == false){
						e.setCurrentItem(null);
						return;
					}

					e.getWhoClicked().closeInventory();
					e.getWhoClicked().openInventory(ai.getInventory(currentSite + 1));

					return;

				}else if(e.getCurrentItem().equals(ai.getPreviewPageItemStack())){

					if(ai.getInventoryMap().containsKey(currentSite - 1) == false){
						e.setCurrentItem(null);
						return;
					}

					e.getWhoClicked().closeInventory();
					e.getWhoClicked().openInventory(ai.getInventory(currentSite - 1));

					return;

				}
			}
		}
	}
}
