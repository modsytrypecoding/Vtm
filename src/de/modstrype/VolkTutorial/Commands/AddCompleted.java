package de.modstrype.VolkTutorial.Commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import de.modstrype.VolkTutorial.Main.Main;
import de.modstrype.VolkTutorial.Mentor.MC;
import de.modstrype.VolkTutorial.TextCommands.OfferHelp;
import de.modstrype.VolkTutorial.TutorialStart.StopRecevingMessages;
import de.modstrype.VolkTutorial.Volk.NPC.NPCBuilder.SecondNPCBuilder;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;

public class AddCompleted implements CommandExecutor, Listener{
	@SuppressWarnings("unused")
	private int taskID;
	private int taskID2;
	public static ArrayList<String> TutorialComplete = new ArrayList<String>();
	
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
		FileConfiguration config = Main.getconfig();
		World world2 = Bukkit.getWorld(config.getString("TutorialFinishSpawn.World"));
		double x2 = config.getDouble("TutorialFinishSpawn.X");
		double y2 = config.getDouble("TutorialFinishSpawn.Y");
		double z2 = config.getDouble("TutorialFinishSpawn.Z");
		float yaw2 = (float) config.getDouble("TutorialFinishSpawn.Yaw");
		float pitch2 = (float) config.getDouble("TutorialFinishSpawn.Pitch");
		Location locationTutorialFinishPoint = new Location(world2, x2, y2, z2, yaw2, pitch2);
		if(sender instanceof Player) {
			Player p = (Player) sender;
			TutorialComplete.add(p.getName());
			Main.getconfig().set("TutorialComplete", TutorialComplete);
			Main.saveconfig();
			taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
				int countdown = 2;
				@Override
				public void run() {
					switch(countdown) {
					case 2: 
						p.sendMessage("§c[§6" + SecondNPCBuilder.NAMENPC2 + "§c]: §aRichtig! §rDu hast das Tutorial abgeschlossen. Du wirst in den nächsten Sekunden teleportiert.");
						Main.getconfig().set("Complete", TutorialComplete);
						Main.saveconfig();
						break;
					case 0:
						p.teleport(locationTutorialFinishPoint);
						taskID2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
							int countdown2 = 10;
							@SuppressWarnings("deprecation")
							@Override
							public void run() {
								switch(countdown2) {
								case 10:
									if(OfferHelp.isHelped.contains(p.getName()) ) {
										net.md_5.bungee.api.chat.TextComponent tc = new net.md_5.bungee.api.chat.TextComponent();
										net.md_5.bungee.api.chat.TextComponent tc2 = new net.md_5.bungee.api.chat.TextComponent();
										net.md_5.bungee.api.chat.TextComponent tc3 = new net.md_5.bungee.api.chat.TextComponent();
										tc.setText("Das Gespräch zwischen dir und dem Mentor wurde automatisch beendet! Du kannst nun frei im Chat schreiben. \n" );
										tc2.setText(Main.MentorPrefix + "§rDu kannst deinen Mentor zusätzlich bewerten, falls dieser einen guten Eindruck bei dir hinterlassen hat!");
										
										tc3.setText("§7[§6Bewerten§7]");
										tc3.setClickEvent(new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/rate " + Main.getconfig().getString(p.getName())));
										tc3.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, new ComponentBuilder("Bewerte deinen Mentor").create()));
										p.spigot().sendMessage(tc, tc2, tc3);
										
									}else {
										return;
									}
									
									break;
								case 9:
									MC.hm.get(Main.getconfig().getString(p.getName())).setTitle("Der Spieler hat das Tutorial abgeschlossen!");
									MC.hm.get(p.getName()).removePlayer(p);
									MC.hm.get(p.getName()).setVisible(false);
									OfferHelp.isHelped.remove(p.getName());
									MC.addtoTalk.remove(p);
									StopRecevingMessages.NoChat.remove(p);
								case 4:
									MC.hm.get(Main.getconfig().getString(p.getName())).setTitle("Du kannst neuen Leute helfen! Sobald diese Leiste verschwindet");
									break;
								case 0:
									MC.hm.get(Main.getconfig().getString(p.getName())).setVisible(false);
									Bukkit.getScheduler().cancelTask(taskID2);
									break;
									default:
								}
								countdown2 --;
							}
							
						}, 0, 20);
						
						break;
						default:
					}
					countdown --;
					
					
				}
			}, 0, 20);
			
		}
		return false;
	}
	
	@EventHandler 
	public void onCheck(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if(MC.hm.containsKey(p.getName())) {
			if(!MC.hm.get(p.getName()).isVisible()) {
				if(OfferHelp.isHelping.contains(p.getName())) {
					if(MC.createdTalk.contains(p)) {
						OfferHelp.isHelping.remove(p.getName());
						Main.getconfig().set(p.getName(), null);
						Main.saveconfig();
						MC.createdTalk.remove(p);
						MC.hm.remove(p.getName());
					}
				}
			}
		}
		
		
	}
}
	
