package de.modstrype.VolkTutorial.Commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.modstrype.VolkTutorial.Main.Main;
import de.modstrype.VolkTutorial.Volk.NPC.NPCBuilder.SecondNPCBuilder;

public class AddFalse implements CommandExecutor{
	public static ArrayList<String> addFalse = new ArrayList<String>();
	private int taskID;
	public static int privatecountdown = 10;
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 0) {
				if(!(addFalse.contains(p.getName()))) {
					addFalse.add(p.getName());
					taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
						int countdown = 10;
						@Override
						public void run() {
							switch(countdown) {
							case 10:
								privatecountdown = 10;
								p.sendMessage("§c[§6" + SecondNPCBuilder.NAMENPC2 + "§c]: §rDeine Antwort ist §cfalsch§r! Du solltest dir die Regeln nochmal durchlesen!");
								break;
							case 0: 
								addFalse.remove(p.getName());
								p.sendMessage("§c[§6" + SecondNPCBuilder.NAMENPC2 + "§c]: " + "§rSobald du dich sicher fühlst kannst du wieder zu mir kommen");
								Bukkit.getScheduler().cancelTask(taskID);
								break;
								default:
							}
							countdown --;
							privatecountdown --;
							
						}
					}, 0, 20);
				}else {
					p.sendMessage("test");
				}
			}
		}
		return false;
	}

}
