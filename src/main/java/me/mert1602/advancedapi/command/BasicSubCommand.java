package me.mert1602.advancedapi.command;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.block.CommandBlock;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import lombok.Getter;
import me.mert1602.advancedapi.Content;
import me.mert1602.advancedapi.BukkitLog;
import me.mert1602.advancedapi.basic.BasicInterface;
import me.mert1602.advancedapi.tool.Tool;

public abstract class BasicSubCommand<C extends Command<? extends BasicInterface>> extends Content<C> implements SubCommand<C> {

	@Getter private String subCommand;

	public BasicSubCommand(C content, String subCommand) {
		super(content);

		this.subCommand = subCommand;

	}
	
	@Override
	public List<String> getDefinedArgs(){
		
		List<String> list = new ArrayList<String>();
		
		for(String s : this.subCommand.split(" ")){
			
			if(s.equalsIgnoreCase("xargsx")) continue;
			
			list.add(s);
			
		}
		
		return list;
		
	}

	@Override
	public int getUndefinedArgs(){

		int undefinedArgs = 0;

		for(String localArg : this.subCommand.split(" ")){

			if(localArg.equalsIgnoreCase("xargsx")){
				undefinedArgs++;
			}

		}

		return undefinedArgs;
	}

	@Override
	public boolean hasPlayerSubCommandAnnotation(Class<? extends Annotation> annotation) {
		try {
			return this.getClass().getDeclaredMethod("runPlayerSubCommand", Player.class, new HashMap<Integer, String>().getClass()).getAnnotation(annotation) != null;
		} catch (final Throwable e) {
			return false;
		}
	}

	@Override
	public boolean hasConsoleSubCommandAnnotation(Class<? extends Annotation> annotation) {
		try {
			return this.getClass().getDeclaredMethod("runConsoleSubCommand", ConsoleCommandSender.class, new HashMap<Integer, String>().getClass()).getAnnotation(annotation) != null;
		} catch (final Throwable e) {
			return false;
		}
	}



	@Override
	public SubCommandPermission getPlayerSubCommandPermission() {
		try {
			return this.getClass().getDeclaredMethod("runPlayerSubCommand", Player.class, new HashMap<Integer, String>().getClass()).getAnnotation(SubCommandPermission.class);
		} catch (Throwable e) {
			return null;
		}
	}

	@Override
	public SubCommandDescription getPlayerSubCommandDescription() {
		try {
			return this.getClass().getDeclaredMethod("runPlayerSubCommand", Player.class, new HashMap<Integer, String>().getClass()).getAnnotation(SubCommandDescription.class);
		} catch (Throwable e) {
			return null;
		}
	}

	@Override
	public SubCommandUsage getPlayerSubCommandUsage() {
		try {
			return this.getClass().getDeclaredMethod("runPlayerSubCommand", Player.class, new HashMap<Integer, String>().getClass()).getAnnotation(SubCommandUsage.class);
		} catch (Throwable e) {
			return null;
		}
	}



	@Override
	public SubCommandDescription getConsoleSubCommandDescription() {
		try {
			return this.getClass().getDeclaredMethod("runConsoleSubCommand", ConsoleCommandSender.class, new HashMap<Integer, String>().getClass()).getAnnotation(SubCommandDescription.class);
		} catch (Throwable e) {
			return null;
		}
	}

	@Override
	public SubCommandUsage getConsoleSubCommandUsage() {
		try {
			return this.getClass().getDeclaredMethod("runConsoleSubCommand", ConsoleCommandSender.class, new HashMap<Integer, String>().getClass()).getAnnotation(SubCommandUsage.class);
		} catch (Throwable e) {
			return null;
		}
	}

	@Override
	public SubCommandResult runLocalPlayerSubCommand(Player bukkitPlayer, String subCommand) {

		try{
			
			//Check defined args
			for(String definedArg : this.getDefinedArgs()){
				
				if(subCommand.contains(definedArg) == false){
					return SubCommandResult.OTHER;
				}
				
			}

			//Fetch undefined args
			int fetchedUndefinedArgs = 0;

			for(String arg : subCommand.split(" ")){
				
				if(this.subCommand.contains(arg)) continue;
				
				fetchedUndefinedArgs++;

			}

			//Check if the undefined args length are same
			if(this.getUndefinedArgs() != fetchedUndefinedArgs){
				return SubCommandResult.OTHER;
			}
			
			//Check Permision
			if(this.hasPlayerSubCommandAnnotation(SubCommandPermission.class)){

				if(bukkitPlayer.hasPermission(this.getPlayerSubCommandPermission().value()) == false){
					return SubCommandResult.NOPERMISSION;
				}

			}

			//Create new args
			HashMap<Integer, String> subCommandMap = new HashMap<Integer, String>();
			int i = 0;

			for(String arg : subCommand.split(" ")){
				
				if(this.subCommand.contains(arg)) continue;
				
				subCommandMap.put(i, arg); i++;
				
			}
			
			return this.runPlayerSubCommand(bukkitPlayer, subCommandMap);

		}catch(Throwable e){
			BukkitLog.exception(e, this.getClass(), Tool.getLineNumber());
			return SubCommandResult.OTHER;
		}

	}

	@Override
	public SubCommandResult runLocalConsoleSubCommand(ConsoleCommandSender bukkitConsole, String subCommand) {

		try{
			
			//Check defined args
			for(String definedArg : this.getDefinedArgs()){
				
				if(subCommand.contains(definedArg) == false){
					return SubCommandResult.OTHER;
				}
				
			}

			//Fetch undefined args
			int fetchedUndefinedArgs = 0;

			for(String arg : subCommand.split(" ")){
				
				if(this.subCommand.contains(arg)) continue;
				
				fetchedUndefinedArgs++;

			}

			//Check if the undefined args length are same
			if(this.getUndefinedArgs() != fetchedUndefinedArgs){
				return SubCommandResult.OTHER;
			}

			//Create new args
			HashMap<Integer, String> subCommandMap = new HashMap<Integer, String>();
			int i = 0;

			for(String arg : subCommand.split(" ")){
				
				if(this.subCommand.contains(arg)) continue;
				
				subCommandMap.put(i, arg); i++;
				
			}
			
			return this.runConsoleSubCommand(bukkitConsole, subCommandMap);

		}catch(Throwable e){
			BukkitLog.exception(e, this.getClass(), Tool.getLineNumber());
			return SubCommandResult.OTHER;
		}

	}

	@Override
	public SubCommandResult runLocalBlockSubCommand(CommandBlock bukkitBlock, String subCommand) {

		try{
			
			//Check defined args
			for(String definedArg : this.getDefinedArgs()){
				
				if(subCommand.contains(definedArg) == false){
					return SubCommandResult.OTHER;
				}
				
			}

			//Fetch undefined args
			int fetchedUndefinedArgs = 0;

			for(String arg : subCommand.split(" ")){
				
				if(this.subCommand.contains(arg)) continue;
				
				fetchedUndefinedArgs++;

			}

			//Check if the undefined args length are same
			if(this.getUndefinedArgs() != fetchedUndefinedArgs){
				return SubCommandResult.OTHER;
			}

			//Create new args
			HashMap<Integer, String> subCommandMap = new HashMap<Integer, String>();
			int i = 0;

			for(String arg : subCommand.split(" ")){
				
				if(this.subCommand.contains(arg)) continue;
				
				subCommandMap.put(i, arg); i++;
				
			}
			
			return this.runBlockSubCommand(bukkitBlock, subCommandMap);

		}catch(Throwable e){
			BukkitLog.exception(e, this.getClass(), Tool.getLineNumber());
			return SubCommandResult.OTHER;
		}

	}

}
