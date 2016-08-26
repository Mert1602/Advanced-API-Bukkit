package me.mert1602.advancedapi.setting;

import java.io.File;

import org.bukkit.Bukkit;

import me.mert1602.advancedapi.BukkitLog;
import me.mert1602.advancedapi.basic.BasicInterface;
import me.mert1602.advancedapi.tool.Tool;

public class BukkitSettingFileContent extends SettingFileContent {

	private boolean configLoaded;

	public BukkitSettingFileContent(SettingManager<? extends BasicInterface> content, File file) {
		super(content, file);

		this.configLoaded = false;

	}

	@Override
	public SettingManager<? extends BasicInterface> getContent() {
		return (SettingManager<? extends BasicInterface>) super.getContent();
	}

	public void setContent(SettingManager<? extends BasicInterface> content) {
		super.setContent(content);
	}

	@Override
	public void loadConfig() {

		if(this.configLoaded) return;
		this.configLoaded = true;

		super.loadConfig();

		if(this.getContent() != null && this.getContent().getContent() != null){

			try{

				Bukkit.getScheduler().runTaskLater(this.getContent().getContent(), new Runnable() {

					@Override
					public void run() {
						BukkitSettingFileContent.this.configLoaded = false;
					}

				}, 20L * 5);

			}catch(Throwable e){

				BukkitLog.exception(e, this.getClass(), Tool.getLineNumber());

				this.configLoaded = false;

			}

		}else this.configLoaded = false;

	}

}
