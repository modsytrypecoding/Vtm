package de.modstrype.VolkTutorial.TutorialStart;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.modstrype.VolkTutorial.Main.Main;
import de.modstrype.VolkTutorial.Mentor.MC;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;


public class StopSendingMessages implements Listener{
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onStopMessage(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if(p.getWorld().getName().equals("Tutorial")) {



				for (Player allTeam : Bukkit.getOnlinePlayers()) {
					if (allTeam.hasPermission("volk.showMSG")) {
						if (MC.addtoTalk.contains(p) || MC.createdTalk.contains(p)) {
						} else {
							allTeam.sendMessage("§c[§rTutorial§c] §a" + e.getPlayer().getName() + "§a: §6" + e.getMessage());

							e.setCancelled(true);
						}


					} else {

					}
				}


				if (!(MC.addtoTalk.contains(p) || MC.createdTalk.contains(p))) {

					p.sendMessage("§c[§rTutorial§c]: §cDu kannst keine Nachrichten schreiben solange du dich im Tutorial befindest!");
					e.setCancelled(true);


				} else {
					e.setCancelled(true);
				}


				if (MC.addtoTalk.contains(p)) {
					Player Mentor = Bukkit.getPlayer(Main.getconfig().getString(p.getName()));
					Mentor.sendMessage("(§7Neuling§r) " + p.getName() + ": " + e.getMessage());
					e.getPlayer().sendMessage("(§7Neuling§r) " + p.getName() + ": " + e.getMessage());

				}
				if (MC.createdTalk.contains(p)) {
					Player target = Bukkit.getPlayer(Main.getconfig().getString(p.getName()));
					target.sendMessage("(§aMentor§r) " + p.getName() + ": " + e.getMessage());
					e.getPlayer().sendMessage("(§aMentor§r) " + p.getName() + ": " + e.getMessage());

				}

		}
	}
}
	



