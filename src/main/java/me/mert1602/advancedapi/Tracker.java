package me.mert1602.advancedapi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import lombok.Getter;
import lombok.Setter;

public class Tracker implements Runnable{

	private final JavaPlugin plugin;

	private final Player shooter;
	@Getter private Player tracked;

	private final Entity trackingEntity;
	private final double radius;
	@Setter private Runnable finish;

	private final BukkitTask task;

	private boolean Stop;


	public Tracker(final JavaPlugin plugin, final Player player, final Entity trackingEntity, final double radius, final Runnable finish){

		this.plugin = plugin;

		this.shooter = player;
		this.trackingEntity = trackingEntity;
		this.radius = radius;
		this.finish = finish;

		this.Stop = false;

		this.task = Bukkit.getScheduler().runTaskTimerAsynchronously(this.plugin, this, 1L, 1L);

	}

	public Tracker(final JavaPlugin plugin, final Player player, final Entity trackingEntity, final double radius){

		this.plugin = plugin;

		this.shooter = player;
		this.trackingEntity = trackingEntity;
		this.radius = radius;

		this.Stop = false;

		this.task = Bukkit.getScheduler().runTaskTimerAsynchronously(this.plugin, this, 1L, 1L);

	}

	public boolean isClosed(){
		if(this.Stop == true){
			return true;
		}else{
			return false;
		}
	}

	private void stop(){

		this.task.cancel();
		Bukkit.getScheduler().cancelTask(this.task.getTaskId());

		if(this.finish == null) {
			return;
		}

		Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, this.finish);

	}

	@Override
	public void run() {

		if(this.Stop == true) {
			return;
		}

		if(this.trackingEntity.isOnGround() || this.trackingEntity.isDead()){

			this.Stop = true;

		}else{

			for(final Entity entity : this.trackingEntity.getNearbyEntities(this.radius, this.radius, this.radius)){

				if(entity instanceof Player){

					final Player trackedPlayer = (Player) entity;

					if(this.shooter.getName().equalsIgnoreCase(trackedPlayer.getName())){
						break;
					}

					this.Stop = true;
					this.tracked = trackedPlayer;
					this.stop();
				}

			}
		}
	}

}
