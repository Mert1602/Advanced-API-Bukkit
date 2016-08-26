package me.mert1602.advancedapi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.mert1602.advancedapi.basic.BasicInterface;
import me.mert1602.advancedapi.event.PlayerDurabilityEvent;
import me.mert1602.advancedapi.tool.ToolItem;

public class DurabilityListener extends Content<BasicInterface> implements Listener {
	
	private static boolean registered = false;
	
	public DurabilityListener(BasicInterface content) {
		super(content);
		
		if(registered) return;
		registered = true;
		
		Bukkit.getPluginManager().registerEvents(this, content);
		
	}
	
	private void run(Entity entity){
		
		if(entity == null) return;
		if(entity instanceof Player == false) return;
		
		Player player = (Player) entity;
		
		this.run(player, player.getItemInHand());
	}
	
	private void run(Entity entity, ItemStack itemstack){
		
		if(entity == null || itemstack == null) return;
		if(entity instanceof Player == false) return;
		if(ToolItem.canHaveCustomDurability(itemstack) == false) return;
		
		new DurabilityTask((Player) entity, itemstack);
		
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void on(BlockBreakEvent e){
		this.run(e.getPlayer());
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void on(EntityDamageEvent e){
		
		if(e.getEntity() instanceof Player == false) return;
		
		Player player = (Player) e.getEntity();
		
		if(player.getInventory().getArmorContents() == null) return;
		
		for(ItemStack itemstack : player.getInventory().getArmorContents()){
			
			if(itemstack == null) continue;
			
			this.run(player, itemstack);
			
		}
		
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void on(EntityDamageByEntityEvent e){
		this.run(e.getDamager());
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void on(EntityShootBowEvent e){
		this.run(e.getEntity());
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void on(PlayerInteractEvent e){
		this.run(e.getPlayer());
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void on(PlayerShearEntityEvent e){
		this.run(e.getPlayer());
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void on(PlayerFishEvent e){
		this.run(e.getPlayer());
	}
	
	public class DurabilityTask extends BukkitRunnable {

		private Player player;
		private ItemStack itemstack;
		private short oldDurability;
		
		public DurabilityTask(Player player, ItemStack itemstack) {
			
			this.player = player;
            this.itemstack = itemstack;
            this.oldDurability = itemstack.getDurability();
            
            this.runTaskLater(DurabilityListener.this.getContent(), 1);
		}
		
		@Override
		public void run() {
			
			int difference = this.oldDurability - this.itemstack.getDurability();
			
			if(difference == 0) return;
			
			if(difference < 0 || difference > 0){
				
				PlayerDurabilityEvent event = new PlayerDurabilityEvent(this.player, this.itemstack, this.oldDurability);
				Bukkit.getPluginManager().callEvent(event);
				
				if(event.isCancelled()){
					this.itemstack.setDurability(this.oldDurability);
				}else if(this.itemstack.getDurability() != event.getNewDurability()){
					this.itemstack.setDurability(event.getNewDurability());
				}
				
			}
			
		}
		
	}

}
