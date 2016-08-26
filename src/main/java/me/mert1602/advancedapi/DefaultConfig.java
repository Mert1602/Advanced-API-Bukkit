package me.mert1602.advancedapi;

import me.mert1602.advancedapi.basic.BasicInterface;
import me.mert1602.advancedapi.setting.SettingManager;

public final class DefaultConfig extends SettingManager<BasicInterface> {

	public DefaultConfig(BasicInterface basic) {
		super(basic, basic.getDataFolder().getPath(), "config.yml");
	}

	@Override
	public void addDefault() {}

}
