package me.mert1602.advancedapi;

import me.mert1602.librarymanager.LibraryList;
import me.mert1602.librarymanager.LibraryManager;

public class AdvancedAPIBukkit {

	private static boolean librariesAdded = false;

	private AdvancedAPIBukkit() {}

	public static void init(){

		AdvancedAPI.init();

		AdvancedAPIBukkit.addLibraries();

	}

	private static void addLibraries(){

		if(AdvancedAPIBukkit.librariesAdded) return;
		AdvancedAPIBukkit.librariesAdded = true;

		Log.info("Adding Advanced-API-Bukkit Libraries...");

		LibraryList list = LibraryManager.createLibraryList("Advanced-API-Bukkit-Libraries.gson");

		list.addLibrary("metrics-R8-SNAPSHOT", "http://ni952812_2.fastdownload.nitrado.net/dev/metrics-R8-SNAPSHOT.jar");

		LibraryManager.registerLibraryList(list, false);

		Log.info("Advanced-API-Bukkit Libraries added!");

	}

}
