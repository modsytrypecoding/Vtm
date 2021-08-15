package de.modstrype.VolkTutorial.TutorialStart;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.modstrype.VolkTutorial.Commands.AddCompleted;
import de.modstrype.VolkTutorial.Commands.Vtm;
import de.modstrype.VolkTutorial.Main.Main;
import de.modstrype.VolkTutorial.Mentor.MentorAdd;
import de.modstrype.VolkTutorial.Volk.NPC.NPCBuilder.FirstNPCBuilder;

import java.util.UUID;

public class JoinListener implements Listener{
	

	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Bukkit.getConsoleSender().sendMessage(Main.getconfig().getStringList("Mentoren").toString());
		Player p = e.getPlayer();
		p.setCollidable(false);
		if(AddCompleted.TutorialComplete.contains(p.getName())) {
			return;
		}else {
			StopRecevingMessages.NoChat.add(p);
		}
		if(p.hasPlayedBefore()) {
			if(Main.getconfig().getStringList("settings.Volk." + Main.getconfig().getStringList("settings.Items.GUI_Item_1").get(0)).contains(p.getName()) || Main.getconfig().getStringList("settings.Volk." + Main.getconfig().getStringList("settings.Items.GUI_Item_2").get(0)).contains(p.getName()) || Main.getconfig().getStringList("settings.Volk." + Main.getconfig().getStringList("settings.Items.GUI_Item_3").get(0)).contains(p.getName()) || Main.getconfig().getStringList("settings.Volk." + Main.getconfig().getStringList("settings.Items.GUI_Item_4").get(0)).contains(p.getName())) {
				if(AddCompleted.TutorialComplete.contains(p.getName())) {
					if(Main.getconfig().getString("TutorialFinishSpawn.World") == null) {
						System.out.println("FinsishPoint noch nicht gesetzt!");
					}else {
					World world2 = Bukkit.getWorld(Main.getconfig().getString("TutorialFinishSpawn.World"));
					double x2 = Main.getconfig().getDouble("TutorialFinishSpawn.X");
					double y2 = Main.getconfig().getDouble("TutorialFinishSpawn.Y");
					double z2 = Main.getconfig().getDouble("TutorialFinishSpawn.Z");
					float yaw2 = (float) Main.getconfig().getDouble("TutorialFinishSpawn.Yaw");
					float pitch2 = (float) Main.getconfig().getDouble("TutorialFinishSpawn.Pitch");
					Location locationTutorialFinishPoint = new Location(world2, x2, y2, z2, yaw2, pitch2);
					p.teleport(locationTutorialFinishPoint);
					}
				}else {	
					if(Main.getconfig().getString("TutorialSpawn.World") == null) {
						System.out.println("Der TutorialSpawn muss noch gesetzt werden!");
					}else {
					World world = Bukkit.getWorld(Main.getconfig().getString("TutorialSpawn.World"));
					double x = Main.getconfig().getDouble("TutorialSpawn.X");
					double y = Main.getconfig().getDouble("TutorialSpawn.Y");
					double z = Main.getconfig().getDouble("TutorialSpawn.Z");
					float yaw = (float) Main.getconfig().getDouble("TutorialSpawn.Yaw");
					float pitch = (float) Main.getconfig().getDouble("TutorialSpawn.Pitch");
					Location locationTutorialStart = new Location(world, x, y, z, yaw, pitch);
					p.teleport(locationTutorialStart);
					p.sendMessage("§c[§rTutorial§c]: Da du das Tutorial beim letzten mal nicht abgeschlossen hast wurdest du zum Anfang zurückgesetzt!");
					p.sendMessage("§c[§rTutorial§c]: §rDir steht es trotzdem offen jederzeit fragen zu stellen. Mit §6/mentor list §rkannst du dir die aktiven Mentoren anzeigen lassen. Mit §6/mentor request §rkannst du dir Hilfe anfordern.");
					}
					}
				
				if(Main.getconfig().getStringList("Menschen").contains(p.getName()) ) {
					Vtm.Volk1.add(p.getName());
					return;
				}
				if(Main.getconfig().getStringList("Halblinge").contains(p.getName()) ) {
					Vtm.Volk2.add(p.getName());
					return;
				}
				if(Main.getconfig().getStringList("Elfen").contains(p.getName()) ) {
					Vtm.Volk3.add(p.getName());
					return;
				}
				if(Main.getconfig().getStringList("Zwerge").contains(p.getName()) ) {
					Vtm.Volk4.add(p.getName());
					return;
				}
				if(Main.getconfig().getStringList("TutorialComplete").contains(p.getName())) {
					AddCompleted.TutorialComplete.add(p.getName());
				}
				if(Main.getconfig().getStringList("Mentoren").contains(p.getName())) {

					MentorAdd.Mentoren.remove(p.getName());
					MentorAdd.Mentoren.add(p.getName());
				}
			}else {
				if(Main.getconfig().getString("TutorialSpawn.World") == null) {
					System.out.println("Der TutorialSpawn muss noch gesetzt werden!");
				}else {
				World world = Bukkit.getWorld(Main.getconfig().getString("TutorialSpawn.World"));
				double x = Main.getconfig().getDouble("TutorialSpawn.X");
				double y = Main.getconfig().getDouble("TutorialSpawn.Y");
				double z = Main.getconfig().getDouble("TutorialSpawn.Z");
				float yaw = (float) Main.getconfig().getDouble("TutorialSpawn.Yaw");
				float pitch = (float) Main.getconfig().getDouble("TutorialSpawn.Pitch");
				Location locationTutorialStart = new Location(world, x, y, z, yaw, pitch);
				p.teleport(locationTutorialStart);
				p.sendMessage("§c[§rTutorial§c]: Da du das Tutorial beim letzten mal nicht abgeschlossen hast wurdest du zum Anfang zurückgesetzt!");
				p.sendMessage("§c[§rTutorial§c]: §rDir steht es trotzdem offen jederzeit fragen zu stellen. Mit §6/mentor list §rkannst du dir die aktiven Mentoren anzeigen lassen. Mit §6/mentor request §rkannst du dir Hilfe anfordern.");
				}
				}
			
			
			
			
		}else {
			if(Main.getconfig().getString("TutorialSpawn.World") == null) {
				System.out.println("Der TutorialSpawn muss noch gesetzt werden!");
			}else {
			World world = Bukkit.getWorld(Main.getconfig().getString("TutorialSpawn.World"));
			double x = Main.getconfig().getDouble("TutorialSpawn.X");
			double y = Main.getconfig().getDouble("TutorialSpawn.Y");
			double z = Main.getconfig().getDouble("TutorialSpawn.Z");
			float yaw = (float) Main.getconfig().getDouble("TutorialSpawn.Yaw");
			float pitch = (float) Main.getconfig().getDouble("TutorialSpawn.Pitch");
			Location locationTutorialStart = new Location(world, x, y, z, yaw, pitch);
			p.teleport(locationTutorialStart);
			p.sendMessage("§c[§rTutorial§c]: §aWillkommen §6" + p.getName() + "§a!");
			p.sendMessage("§rBevor du anfangen kannst den Server zu erkunden bitten wir dich, dich mit den Regeln unseres Server vertaut zu machen. Sprich doch mal mit §6" + FirstNPCBuilder.NAMENPC1 + " der sieht so aus als würde der mehr Wissen :)");
			p.sendMessage("§c[§rTutorial§c]: §rFalls du Fragen hast, oder du etwas nicht verstehst kannst du gerne §6/mentor list §reingeben um dir die aktiven Mentoren anzeigen zu lassen. Mit §6/mentor request §rkannst du dir Hilfe anfordern.");
			}
			}
				
				
			
		}
		
	}


