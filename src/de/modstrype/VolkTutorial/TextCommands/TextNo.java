package de.modstrype.VolkTutorial.TextCommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.modstrype.VolkTutorial.Volk.NPC.NPCBuilder.SecondNPCBuilder;

public class TextNo implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
		if(sender instanceof Player) {
			Player p= (Player) sender;
			if(args.length == 0) {
				p.sendMessage("§c[§6"+ SecondNPCBuilder.NAMENPC2 + "§c]: §rAlles Klar, komm einfach wieder zu mir sobald du dich bereit fühlst.");
			}
		}
		return false;
	}

}
