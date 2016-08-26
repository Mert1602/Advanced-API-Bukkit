package me.mert1602.advancedapi;

import org.bukkit.Location;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitUtil;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;

public class Area implements Removeable {

	private CuboidSelection area;
	private EditSession editSession;
	private CuboidClipboard cuboidClipboard;

	private Location from;
	private Location to;

	public Area(Location from, Location to) {

		this.from = from;
		this.to = to;

	}

	public CuboidSelection get(){
		return this.area;
	}



	public boolean isValid(){

		if(this.from == null || this.to == null) return false;
		if(this.from.getWorld().equals(this.to.getWorld()) == false) return false;

		return true;

	}

	public boolean has(){
		return this.isValid() ? this.area != null : false;
	}



	public void load(){

		if(this.isValid() == false) return;

		try {
			this.area = new CuboidSelection(this.from.getWorld(), this.from, this.to);
		} catch (Throwable e) {
			this.area = null;
		}

	}

	public void save(){

		if(this.isValid() == false) return;

		this.load();

		if(this.has() == false) return;

		this.editSession = new EditSession(new BukkitWorld(this.from.getWorld()), 2000000);

		Vector min = new Vector(this.area.getNativeMinimumPoint());
		Vector max = new Vector(this.area.getNativeMaximumPoint());

		this.cuboidClipboard = new CuboidClipboard(max.subtract(min).add(new Vector(1, 1, 1)), min);
		this.cuboidClipboard.copy(this.editSession);

	}

	public void reset(){

		if(this.isValid() == false) return;
		if(this.has() == false) return;

		if (this.editSession == null || this.cuboidClipboard == null) return;

		Vector origin = new Vector(BukkitUtil.toVector(this.from));

		try {
			this.cuboidClipboard.paste(this.editSession, origin, false, true);
		} catch (MaxChangedBlocksException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void remove() {

		this.area = null;
		this.editSession = null;
		this.cuboidClipboard = null;

		this.from = null;
		this.to = null;

	}

}
