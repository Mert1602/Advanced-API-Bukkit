package me.mert1602.advancedapi.command;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CommandBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import me.mert1602.advancedapi.Content;
import me.mert1602.advancedapi.basic.BasicInterface;

public class BasicCommandListener extends Content<BasicInterface> implements Listener {

	private static boolean registered = false;

	public BasicCommandListener(BasicInterface content) {
		super(content);

		if(registered) return;
		registered = true;
		
		Bukkit.getPluginManager().registerEvents(this, content);

	}

	@EventHandler
	public void on(PlayerCommandPreprocessEvent e){

		String message = e.getMessage().substring(1);
		String commandName = message.split(" ")[0];
		String args = message.substring(commandName.length()).trim();
		
		for(Command<?> command : BasicCommand.getSettingManagers()){
			
			boolean commandFound = false;
			
			if(command.getCommand().equalsIgnoreCase(commandName)){
				commandFound = true;
			}
			
			for(String alias : command.getAliases()){
				
				if(commandFound) break;
				
				if(alias.equalsIgnoreCase(commandName)){
					commandFound = true;
					break;
				}
				
			}
			
			if(commandFound == false) continue;
			
			e.setCancelled(true);
			
			command.runCommand(e.getPlayer(), args);
			
			return;
		}

	}

	@EventHandler
	public void on(ServerCommandEvent e){

		String message = e.getCommand();
		String commandName = message.split(" ")[0];
		String args = message.substring(commandName.length()).trim();
		
		for(Command<?> command : BasicCommand.getSettingManagers()){

			boolean commandFound = false;
			
			if(command.getCommand().equalsIgnoreCase(commandName)){
				commandFound = true;
			}
			
			for(String alias : command.getAliases()){
				
				if(commandFound) break;
				
				if(alias.equalsIgnoreCase(commandName)){
					commandFound = true;
					break;
				}
				
			}
			
			if(commandFound == false) continue;
			
			e.setCancelled(true);
			
			command.runCommand(e.getSender(), args);
				
			return;
		}

	}
	
	@EventHandler
	public void on(BlockRedstoneEvent e){
		
		Block block = e.getBlock();
		
		if(block == null) return;
		if(e.getOldCurrent() != 0) return;
		if(e.getNewCurrent() < 1) return;
		
		BlockState state = block.getState();
		
		if(state == null) return;
		if((state instanceof CommandBlock) == false) return;
		
		CommandBlock cmdBlock = (CommandBlock) state;
		
		String message = cmdBlock.getCommand().startsWith("/") ? cmdBlock.getCommand().substring(1) : cmdBlock.getCommand();
		String commandName = message.split(" ")[0];
		String args = message.substring(commandName.length()).trim();
		
		for(Command<?> command : BasicCommand.getSettingManagers()){

			boolean commandFound = false;
			
			if(command.getCommand().equalsIgnoreCase(commandName)){
				commandFound = true;
			}
			
			for(String alias : command.getAliases()){
				
				if(commandFound) break;
				
				if(alias.equalsIgnoreCase(commandName)){
					commandFound = true;
					break;
				}
				
			}
			
			if(commandFound == false) continue;
			
			e.setNewCurrent(0);
			
			command.runCommand(cmdBlock, args);
				
			return;
		}
		
		
	}

}
