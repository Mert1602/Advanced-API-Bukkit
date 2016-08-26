package me.mert1602.advancedapi.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.block.CommandBlock;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import lombok.Getter;
import me.mert1602.advancedapi.basic.BasicContentManager;
import me.mert1602.advancedapi.basic.BasicInterface;

public abstract class BasicCommand<C extends BasicInterface> extends BasicContentManager<SubCommand<?>, C> implements Command<C> {

	private final static List<Command<?>> basicCommands = new ArrayList<Command<?>>();

	public static List<Command<?>> getSettingManagers(){
		return Collections.unmodifiableList(new ArrayList<Command<?>>(BasicCommand.basicCommands));
	}
	
	@Getter private String command;
	@Getter private String helpPermission;
	@Getter private String[] aliases;

	public BasicCommand(C content, String command, String helpPermission, String... aliases) {
		super(content);

		this.command = command;
		this.helpPermission = helpPermission;
		this.aliases = aliases;

		BasicCommand.basicCommands.add(this);
		
		this.addDefault();

	}

	
	
	@Override
	public final void runCommand(Object object, String args){

		if(object instanceof Player){

			Player bukkitPlayer = (Player) object;

			if(bukkitPlayer.hasPermission(this.helpPermission) == false){
				bukkitPlayer.sendMessage(this.getMessage_NoPermission());
				return;
			}
			
			if((args.length() < 1)){
				
				this.runPlayerCommandWithoutArgs(bukkitPlayer);
				
			}else if(args.equalsIgnoreCase("help")){
				
				bukkitPlayer.sendMessage(this.getMessage_Help(bukkitPlayer));
				
			}else{
				
				for(SubCommand<?> subCommand : this.getList()){

					SubCommandResult result = subCommand.runLocalPlayerSubCommand(bukkitPlayer, args);

					if(result == SubCommandResult.OTHER) continue;

					if(result == SubCommandResult.NOPERMISSION){
						bukkitPlayer.sendMessage(this.getMessage_NoPermission());
					}else if(result == SubCommandResult.WRONG){
						bukkitPlayer.sendMessage(this.getMessage_Wrong(bukkitPlayer, subCommand));
					}

					return;

				}

				bukkitPlayer.sendMessage(this.getMessage_SubCommandNotFound());
				
			}

		}else if(object instanceof ConsoleCommandSender){

			ConsoleCommandSender bukkitConsole = (ConsoleCommandSender) object;
			
			if((args.length() < 1)){
				
				this.runConsoleCommandWithoutArgs(bukkitConsole);
				
			}else if(args.equalsIgnoreCase("help")){
				
				bukkitConsole.sendMessage(this.getMessage_Help(bukkitConsole));
				
			}else{
				
				for(SubCommand<?> subCommand : this.getList()){

					SubCommandResult result = subCommand.runLocalConsoleSubCommand(bukkitConsole, args);

					if(result == SubCommandResult.OTHER) continue;

					if(result == SubCommandResult.WRONG){
						bukkitConsole.sendMessage(this.getMessage_Wrong(bukkitConsole, subCommand));
					}

					return;

				}

				bukkitConsole.sendMessage(this.getMessage_SubCommandNotFound());
				
			}

		}else if(object instanceof CommandBlock){

			CommandBlock bukkitBlock = (CommandBlock) object;
			
			if((args.length() < 1)){
				
				this.runBlockCommandWithoutArgs(bukkitBlock);

			}else{
				
				for(SubCommand<?> subCommand : this.getList()){

					SubCommandResult result = subCommand.runLocalBlockSubCommand(bukkitBlock, args);

					if(result == SubCommandResult.OTHER) continue;

					return;

				}
				
			}

		}

	}
	
	@Override
	public final void addFolder() {}

	@Override
	public abstract void addDefault();

}
