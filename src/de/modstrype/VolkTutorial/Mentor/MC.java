package de.modstrype.VolkTutorial.Mentor;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import de.modstrype.VolkTutorial.Main.Main;

public class MC implements CommandExecutor{
	public static ArrayList<Player> addtoTalk = new ArrayList<Player>();
	public static ArrayList<Player> createdTalk = new ArrayList<Player>();
	
	public static HashMap<String, BossBar> hm = new HashMap<>();
	ArrayList<BossBar> bars = new ArrayList<BossBar>();
	
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
				if(p.hasPermission("Volk.MentorChat")) {
					
					if(createdTalk.contains(p.getPlayer())) {
						p.sendMessage(Main.MentorPrefix + "§cDu befindest dich bereits in einem Gespräch!");
					}else {
						Player tchat = Bukkit.getPlayer(args[0]);
						addtoTalk.add(tchat);
						createdTalk.add(p);
						Main.getconfig().set(tchat.getName(), p.getName());
						Main.getconfig().set(p.getName(), tchat.getName());
						Main.saveconfig();
						tchat.sendMessage(Main.MentorPrefix + "§rDein §6Mentor §rhat einen privaten Chat eröffent. Du kannst nun im Chat schreiben! Alle Nachrichten die du schreibst gehen nur an deinen Mentor.");
						p.sendMessage(Main.MentorPrefix + "§aDu befindest dich nun in einem Gespräch mit §6" + tchat.getName());
						
						if(addtoTalk.contains(tchat) || createdTalk.contains(p)) {
							createNewBossBar(p, tchat);
						}
						
						
						
					}
					
				}else {
					p.sendMessage(Main.MentorPrefix + "§cDazu hast du keine Berechtigungen! ");
				}
			
		}
		
		return false;
	}
	
	public void createNewBossBar(Player Mentor,  Player Empfänger) {
		

			hm.put(Mentor.getName(), getNewBossBar());
			
			hm.get(Mentor.getName()).setTitle("Du hilfst momentan §6" + Empfänger.getName());
			hm.get(Mentor.getName()).addPlayer(Mentor);
			hm.get(Mentor.getName()).setVisible(true);
			

			hm.put(Empfänger.getName(), getNewBossBar());
			hm.get(Empfänger.getName()).setTitle("Im Gespräch mit §6" + Main.getconfig().getString(Empfänger.getName()));
			hm.get(Empfänger.getName()).addPlayer(Empfänger);
			hm.get(Empfänger.getName()).setVisible(true);
			
			
		return;
	}
	
	public BossBar getNewBossBar() {
		BossBar bar = Bukkit.createBossBar(null, BarColor.GREEN, BarStyle.SOLID);
		return bar;
	}
	
}
