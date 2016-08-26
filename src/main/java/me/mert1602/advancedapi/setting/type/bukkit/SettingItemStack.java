package me.mert1602.advancedapi.setting.type.bukkit;

import org.bukkit.inventory.ItemStack;

import me.mert1602.advancedapi.BukkitLog;
import me.mert1602.advancedapi.basic.BasicInterface;
import me.mert1602.advancedapi.setting.BukkitSettingType;
import me.mert1602.advancedapi.setting.Setting;
import me.mert1602.advancedapi.setting.SettingLanguage;
import me.mert1602.advancedapi.setting.SettingManager;
import me.mert1602.advancedapi.tool.ToolItem;

public final class SettingItemStack extends Setting<ItemStack> {

	public SettingItemStack(final SettingManager<? extends BasicInterface> settingManager, final String name, final String path, final boolean hasLanguage, final ItemStack defaultValue) {
		super(settingManager, name, path, BukkitSettingType.ITEMSTACK, hasLanguage, defaultValue);

		if (hasLanguage) {

			for (final SettingLanguage lang : SettingLanguage.values()) {
				this.getConfig().addDefault(lang.getPrePath() + this.getPath(), ToolItem.itemStackToString(defaultValue));
			}

		} else this.getConfig().addDefault(path, ToolItem.itemStackToString(defaultValue));

	}

	@Override
	public ItemStack get(final SettingLanguage languageType) {

		this.loadConfig();

		try {

			String text = this.hasLanguage() ?
					this.getConfig().getString(languageType.getPrePath() + this.getPath()) :
						this.getConfig().getString(this.getPath());

			return text.equalsIgnoreCase("null") ? null : ToolItem.itemStackFromString(text);

		} catch (final Throwable e) {
			BukkitLog.error(e.getMessage());
			return this.getDefaultObject();
		}

	}

	@Override
	public void set(final ItemStack itemstack, final SettingLanguage type) {

		if (itemstack == null) {

			if (this.hasLanguage()) {
				this.getConfig().set(SettingLanguage.getDefaultLanguage().getPrePath() + this.getPath(), "null");
			} else {
				this.getConfig().set(this.getPath(), "null");
			}

		} else {

			if (this.hasLanguage()) {
				this.getConfig().set(SettingLanguage.getDefaultLanguage().getPrePath() + this.getPath(),
						ToolItem.itemStackToString(itemstack));
			} else {
				this.getConfig().set(this.getPath(), ToolItem.itemStackToString(itemstack));
			}

		}

		this.saveConfig();

	}

}
