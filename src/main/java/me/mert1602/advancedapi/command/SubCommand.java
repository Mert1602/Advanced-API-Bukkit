package me.mert1602.advancedapi.command;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;

import org.bukkit.block.CommandBlock;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.mert1602.advancedapi.ContentInterface;
import me.mert1602.advancedapi.basic.BasicInterface;

public interface SubCommand<T extends Command<? extends BasicInterface>> extends ContentInterface<T> {

	public String getSubCommand();

	public List<String> getDefinedArgs();

	public int getUndefinedArgs();



	public boolean hasPlayerSubCommandAnnotation(Class<? extends Annotation> annotation);

	public boolean hasConsoleSubCommandAnnotation(Class<? extends Annotation> annotation);



	public SubCommandPermission getPlayerSubCommandPermission();

	public SubCommandDescription getPlayerSubCommandDescription();

	public SubCommandUsage getPlayerSubCommandUsage();



	public SubCommandDescription getConsoleSubCommandDescription();

	public SubCommandUsage getConsoleSubCommandUsage();



	public SubCommandResult runLocalPlayerSubCommand(Player bukkitPlayer, String args);

	public SubCommandResult runLocalConsoleSubCommand(ConsoleCommandSender console, String args);

	public SubCommandResult runLocalBlockSubCommand(CommandBlock bukkitBlock, String args);



	public SubCommandResult runPlayerSubCommand(Player bukkitPlayer, HashMap<Integer, String> args);

	public SubCommandResult runConsoleSubCommand(ConsoleCommandSender bukkitConsole, HashMap<Integer, String> args);

	public SubCommandResult runBlockSubCommand(CommandBlock bukkitBlock, HashMap<Integer, String> args);

}
