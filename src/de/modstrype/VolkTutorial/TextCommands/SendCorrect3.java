package de.modstrype.VolkTutorial.TextCommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.modstrype.VolkTutorial.Main.Main;
import de.modstrype.VolkTutorial.Volk.NPC.NPCBuilder.SecondNPCBuilder;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;

public class SendCorrect3 implements CommandExecutor{
	String Correct1 = Main.getconfig().getString("settings.Questions.Question3.CorrectAwnser3");
	String False1 = Main.getconfig().getString("settings.Questions.Question3.FalseAwnser1");
	String False2 = Main.getconfig().getString("settings.Questions.Question3.FalseAwnser2");

	@Override
	public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 0) {
				net.md_5.bungee.api.chat.TextComponent tc1 = new net.md_5.bungee.api.chat.TextComponent();
				net.md_5.bungee.api.chat.TextComponent tc2 = new net.md_5.bungee.api.chat.TextComponent();	
				net.md_5.bungee.api.chat.TextComponent tc3 = new net.md_5.bungee.api.chat.TextComponent();
				p.sendMessage("                            ");
				p.sendMessage("§c[§6" + SecondNPCBuilder.NAMENPC2 + "§c]: §aRichtig! §rKommen wir nun zur letzten Frage...");
				p.sendMessage("                            ");
				p.sendMessage("§c[§6" + SecondNPCBuilder.NAMENPC2 + "§c]: §6" + Main.getconfig().getString("settings.Questions.Question3"));
				tc1.setText("§c[§a" + Correct1 + "§c]" + " ");
				tc2.setText("§c[§a" + False1 + "§c]" + " ");
				tc3.setText("§c[§a" + False2 + "§c]" + " ");
				tc1.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/addComplete"));
				tc2.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/addFalse"));
				tc3.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/addFalse"));
				p.spigot().sendMessage(tc1, tc2, tc3);
				p.sendMessage("                            ");
				
				
			}
		}
		return false;
	}



}
