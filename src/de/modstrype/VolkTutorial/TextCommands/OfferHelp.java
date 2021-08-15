package de.modstrype.VolkTutorial.TextCommands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.modstrype.VolkTutorial.Main.Main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;

public class OfferHelp implements CommandExecutor{
	public static ArrayList<String> isHelped = new ArrayList<String>();
	public static ArrayList<String> isHelping = new ArrayList<String>();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			Player t = Bukkit.getPlayer(args[0]);
			if(args.length == 1) {
				if(!(isHelped.contains(t.getName()))) {
					isHelped.add(t.getName());
					isHelping.add(p.getName());
				t.sendMessage(Main.MentorPrefix + "§aDeine Anfrage wurde angenommen! Der Spieler §6" + p.getName() + " §asteht dir nun für Fragen zur Verfügung");
				
				p.sendMessage(Main.MentorPrefix + "§aDu bist jetzt der Mentor für den Spieler " + args[0] + " Du kannst dich zu ihm teleportieren oder einen Chat anfangen.");
				for(Player all : Bukkit.getOnlinePlayers()) {
					if(all.hasPermission("mentor.showrequestTaken")) {
						all.sendMessage(Main.MentorPrefix + "§rDer §6Mentor " + p.getName() + " §rhilft nun dem §6Spieler " + t.getName());
					}
				}
				net.md_5.bungee.api.chat.TextComponent tc = new net.md_5.bungee.api.chat.TextComponent();
				net.md_5.bungee.api.chat.TextComponent tc2 = new net.md_5.bungee.api.chat.TextComponent();
				
				tc.setText("§c[§7Teleport§c] ");
				tc2.setText("§c[§7Chat§c]");
				
				tc.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/tpx " + args[0] ));
				tc2.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/mchat " + t.getName()));
				
				p.spigot().sendMessage(tc, tc2);
				
				}else {
					p.sendMessage(Main.MentorPrefix + "§cDiesem Spieler wird bereits geholfen!");
				}
				
				
			}
		}
		return false;
	}

}
