package cz.wake.manager.commads;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cz.wake.manager.particles.ParticlesAPI;

public class Particles_command implements CommandExecutor{
	
	ParticlesAPI partAPI = new ParticlesAPI();
	
	@Override
	public boolean onCommand(CommandSender Sender, Command Command, String String, String[] ArrayOfString){
		if(Sender instanceof Player){
			Player player = (Player)Sender;
			if((Command.getName().equalsIgnoreCase("part"))){
				if(ArrayOfString.length == 0){
					partAPI.openParticlesMenu(player);
					return true;
				}
				return true;
			}
		}
		return false;
	}

}
