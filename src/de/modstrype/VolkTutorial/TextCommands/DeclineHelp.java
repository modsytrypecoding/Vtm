package de.modstrype.VolkTutorial.TextCommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.modstrype.VolkTutorial.Main.Main;

public class DeclineHelp implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 0) {
				p.sendMessage(Main.MentorPrefix + "�aAnfrage erfolgreich abgelehnt!");
			}
		}

		return false;
	}

}
