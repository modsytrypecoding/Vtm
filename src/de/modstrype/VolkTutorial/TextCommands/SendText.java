package de.modstrype.VolkTutorial.TextCommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.modstrype.VolkTutorial.Commands.TeleportCommand;
import de.modstrype.VolkTutorial.Volk.NPC.NPCBuilder.FirstNPCBuilder;

public class SendText implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
				if(args.length == 0) {
							if(!(TeleportCommand.History.contains(p))) {
								p.sendMessage("§c[§6" + FirstNPCBuilder.NAMENPC1 + "§c]: §r" + "Alles klar! Um die Server Geschichte kennenzulernen folge dem Weg nach Links.");
							}else {
								return true;
							}
							
						
					
				}else {
				
			}
		
	}
		return false;

}
}
