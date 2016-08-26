package me.mert1602.advancedapi.command;

import org.bukkit.block.CommandBlock;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.mert1602.advancedapi.basic.BasicInterface;
import me.mert1602.advancedapi.basic.ContentManager;

public interface Command<C extends BasicInterface> extends ContentManager<SubCommand<?>, C> {

	public String getCommand();

	public String getHelpPermission();

	public String[] getAliases();



	public String[] getMessage_NoPermission();

	public String[] getMessage_Help(CommandSender commandSender);

	public String[] getMessage_Wrong(CommandSender sender, SubCommand<?> subCommand);

	public String[] getMessage_SubCommandNotFound();



	public void runCommand(Object object, String args);



	public void runPlayerCommandWithoutArgs(Player bukkitPlayer);

	public void runConsoleCommandWithoutArgs(ConsoleCommandSender bukkitConsole);

	public void runBlockCommandWithoutArgs(CommandBlock bukkitBlock);

}
