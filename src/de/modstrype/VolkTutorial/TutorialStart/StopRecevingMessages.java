package de.modstrype.VolkTutorial.TutorialStart;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;




public class StopRecevingMessages implements Listener{
	public static ArrayList<Player> NoChat = new ArrayList<Player>();	
	List<Player> recipients = new LinkedList<Player>();
	
	@EventHandler
	public void offChat(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		
		e.getRecipients().clear();
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(!NoChat.contains(p)) {
				recipients.add(p);
			}
			
		}
		if(NoChat.contains(player)) {
			e.getRecipients().addAll(recipients);
		}else {
			e.getRecipients().clear();
		}
	}
}
