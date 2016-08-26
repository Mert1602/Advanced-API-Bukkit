package me.mert1602.advancedapi;

import java.util.Collection;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

public class LastPlayerStatus {

	private final Player bukkitPlayer;

	private boolean allowFlight;
	private boolean customNameVisible;

	private double maxHealth;
	private double health;

	private float exp;
	private float walkSpeed;

	private int foodLevel;
	private int fireticks;
	private int level;
	private int heldItemSlot;
	private int maximumAir;
	private int remainingAir;

	private String customName;
	private String displayName;
	private String playerListName;

	private GameMode gameMode;
	private Collection<PotionEffect> activePotionEffects;
	private Vector velocity;
	private ItemStack[] armorContents;
	private ItemStack[] inventoryContents;
	private Location location;
	private Location compassLocation;
	private Scoreboard scoreboard;

	public LastPlayerStatus(final Player bukkitPlayer) {
		this(bukkitPlayer, false);
	}

	public LastPlayerStatus(final Player bukkitPlayer, final boolean save) {

		this.bukkitPlayer = bukkitPlayer;

		if(save) {
			this.save();
		}

	}

	public void save(){

		this.allowFlight = this.bukkitPlayer.getAllowFlight();
		this.customNameVisible = this.bukkitPlayer.isCustomNameVisible();

		this.maxHealth = this.bukkitPlayer.getMaxHealth();
		this.health = this.bukkitPlayer.getHealth();

		this.exp = this.bukkitPlayer.getExp();
		this.walkSpeed = this.bukkitPlayer.getWalkSpeed();

		this.foodLevel = this.bukkitPlayer.getFoodLevel();
		this.fireticks = this.bukkitPlayer.getFireTicks();
		this.level = this.bukkitPlayer.getLevel();
		this.heldItemSlot = this.bukkitPlayer.getInventory().getHeldItemSlot();
		this.maximumAir = this.bukkitPlayer.getMaximumAir();
		this.remainingAir = this.bukkitPlayer.getRemainingAir();

		this.customName = this.bukkitPlayer.getCustomName();
		this.displayName = this.bukkitPlayer.getDisplayName();
		this.playerListName = this.bukkitPlayer.getPlayerListName();

		this.gameMode = this.bukkitPlayer.getGameMode();
		this.activePotionEffects = this.bukkitPlayer.getActivePotionEffects();
		this.velocity = this.bukkitPlayer.getVelocity();
		this.armorContents = this.bukkitPlayer.getInventory().getArmorContents();
		this.inventoryContents = this.bukkitPlayer.getInventory().getContents();
		this.location = this.bukkitPlayer.getLocation();
		this.compassLocation = this.bukkitPlayer.getCompassTarget();
		this.scoreboard = this.bukkitPlayer.getScoreboard();

	}

	public void reset(){

		this.bukkitPlayer.resetPlayerTime();
		this.bukkitPlayer.resetPlayerWeather();

		this.bukkitPlayer.teleport(this.location);

		this.bukkitPlayer.setAllowFlight(this.allowFlight);
		this.bukkitPlayer.setCustomNameVisible(this.customNameVisible);

		this.bukkitPlayer.setMaxHealth(this.maxHealth);
		this.bukkitPlayer.setHealth(this.health);

		this.bukkitPlayer.setExp(this.exp);
		this.bukkitPlayer.setWalkSpeed(this.walkSpeed);

		this.bukkitPlayer.setFoodLevel(this.foodLevel);
		this.bukkitPlayer.setFireTicks(this.fireticks);
		this.bukkitPlayer.setLevel(this.level);
		this.bukkitPlayer.getInventory().setHeldItemSlot(this.heldItemSlot);
		this.bukkitPlayer.setMaximumAir(this.maximumAir);
		this.bukkitPlayer.setRemainingAir(this.remainingAir);

		this.bukkitPlayer.setCustomName(this.customName);
		this.bukkitPlayer.setDisplayName(this.displayName);
		this.bukkitPlayer.setPlayerListName(this.playerListName);

		this.bukkitPlayer.setGameMode(this.gameMode);
		this.bukkitPlayer.addPotionEffects(this.activePotionEffects);
		this.bukkitPlayer.setVelocity(this.velocity);
		this.bukkitPlayer.getInventory().setArmorContents(this.armorContents);
		this.bukkitPlayer.getInventory().setContents(this.inventoryContents);
		this.bukkitPlayer.setCompassTarget(this.compassLocation);
		this.bukkitPlayer.setScoreboard(this.scoreboard);

	}

}
