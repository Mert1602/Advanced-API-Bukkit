package me.mert1602.advancedapi.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

import lombok.Getter;
import lombok.Setter;

public class PlayerDurabilityEvent extends PlayerEvent implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	
	@Getter @Setter private boolean cancelled;
	@Getter private ItemStack itemstack;
	@Getter private short oldDurability;
	@Getter @Setter private short newDurability;
	
	public PlayerDurabilityEvent(Player player, ItemStack itemstack, short oldDurability) {
		super(player);
		
		this.cancelled = false;
		this.itemstack = itemstack;
		this.oldDurability = oldDurability;
		this.newDurability = this.itemstack.getDurability();
		
	}
	
	public int getDifference(){
		return this.oldDurability - this.newDurability;
	}
	
	@Override
	public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
