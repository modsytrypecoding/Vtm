package de.modstrype.VolkTutorial.Mentor;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import de.modstrype.VolkTutorial.Commands.AddCompleted;
import de.modstrype.VolkTutorial.Main.Main;
import de.modstrype.VolkTutorial.TextCommands.OfferHelp;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;

public class MentorAdd implements CommandExecutor, Listener{
	public static ArrayList<String> Mentoren = new ArrayList<String>();
	public static ArrayList<Player> request = new ArrayList<Player>();


	public static String MentorChat = "§c[§rMc§c]: §r";
	
	@SuppressWarnings({ "deprecation", "unused" })
	@Override
	public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if(args.length == 2) {
				Player t = Bukkit.getPlayer(args[1]);
				if(p.hasPermission("mentor.add")) {
					if(args[0].equalsIgnoreCase("add")) {
						
						if(!(Mentoren.contains(t.getPlayer().getName()))) {
							Mentoren.add(t.getPlayer().getName());
							Main.getconfig().set("Mentoren", Mentoren);
							Main.saveconfig();
							t.sendMessage(Main.MentorPrefix + "§aDu bist nun ein Mentor!");
							p.sendMessage(Main.MentorPrefix + "§aDer Spieler " + args[1] + " ist nun ein Mentor");
							
						}else {
							p.sendMessage(Main.MentorPrefix + "Der Spieler " + args[1] + " ist bereits ein Mentor");
						}
					}else if(args[0].equalsIgnoreCase("remove")) {
						if(Mentoren.contains(t.getPlayer().getName())) {
							Mentoren.remove(t.getPlayer().getName());
							p.sendMessage(Main.MentorPrefix + "Der Spieler " + args[1] + " ist nun kein Mentor mehr!");
								
							
						}else {
							return true;
						}
					}
				
				}else {
					p.sendMessage(Main.MentorPrefix + "Dazu fehlen dir die Berechtigungen!");
				}
	
					
				}else if(args.length == 1){
					if(args[0].equalsIgnoreCase("request")) {
						if(OfferHelp.isHelped.contains(sender.getName())) {
							sender.sendMessage(Main.MentorPrefix + "§cDir wird bereits geholfen!");
						}else {
							if(!request.contains(sender)) {
								for(Player allMentor : Bukkit.getOnlinePlayers()) {
									if(Mentoren.contains(allMentor.getName())) {
										if(!(OfferHelp.isHelping.contains(allMentor.getName()))) {
											if(!AddCompleted.TutorialComplete.contains(sender.getName())) {
												net.md_5.bungee.api.chat.TextComponent tc = new net.md_5.bungee.api.chat.TextComponent();
												net.md_5.bungee.api.chat.TextComponent tc2 = new net.md_5.bungee.api.chat.TextComponent();
												net.md_5.bungee.api.chat.TextComponent tc3 = new net.md_5.bungee.api.chat.TextComponent();
												net.md_5.bungee.api.chat.TextComponent tc4 = new net.md_5.bungee.api.chat.TextComponent();
												tc.setText(Main.MentorPrefix + "§rDer Spieler §6" + sender.getName() + " §rbenötigt Hilfe! \n");
												tc2.setText(Main.MentorPrefix + "§rWillst du ihm Helfen?" );
												tc3.setText(" §7[§aJa§7] ");
												tc4.setText("§7[§cNein§7]");
												
												if(!(OfferHelp.isHelped.contains(sender.getName()))) {
													tc3.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/offerHelp " + sender.getName()));
													
													tc4.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/declineHelp"));
													
												}else {
													tc3.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/isHelped " + sender.getName()));
													tc3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§cDiesem Spieler wird bereits geholfen").create()));
													tc4.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/declineHelp"));
												}
												
												allMentor.spigot().sendMessage(tc, tc2 , tc3, tc4);
											}
											
										}else {
											
										}
									}else {
		
									}
								}
								sender.sendMessage(Main.MentorPrefix + "§aDeine Anfrage wurde an einen Mentor geschickt. Dir wird sofort geholfen.");

								int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
									int countdown = 10;
									@Override
									public void run() {
										switch(countdown) {
										case 10: 
											request.add(p);
											break;
										case 0: 
											request.remove(p);
											if(OfferHelp.isHelped.contains(p.getName()) || AddCompleted.TutorialComplete.contains(p.getName())) {
												return;
											}else {
												p.sendMessage(Main.MentorPrefix + "§rDu kannst nun erneut eine Anfrage schicken!");
											}
										
											break;
											default:
											
										}
										countdown --;
									}
								}, 0, 20);
								
							}else {
								sender.sendMessage(Main.MentorPrefix + "Du hast bereits eine Anfrage geschickt versuch es später erneut!");
							}
							
							
						}
						
						
						
						
					
				}
					if(args[0].equalsIgnoreCase("list")) {
						File rateFile = new File(Main.getPlugin().getDataFolder(), "Rating.yml");
						FileConfiguration rating = YamlConfiguration.loadConfiguration(rateFile);
						Inventory inv = Bukkit.createInventory(null, 9*4, "§6Mentor-List");
						if(Mentoren.isEmpty()) {
							p.sendMessage(Main.MentorPrefix + "Es gibt noch keine Mentoren!");
						}else {
							for(String online : Mentoren)  {
								OfflinePlayer offline = Bukkit.getOfflinePlayer(online);
								if(!offline.isOnline()) {
									p.sendMessage(Main.MentorPrefix + "Momentan ist leider kein Mentor online.");
									break;
								}else {
									Player onlineP = offline.getPlayer();
									if(Mentoren.contains(onlineP.getName())) {

										int ratingsPositiv = rating.getInt("Player." + onlineP.getName() + ".RatingsPositiv");
										int ratingsNegativ = rating.getInt("Player." + onlineP.getName() + ".RatingsNegativ");

										ItemStack Head = new ItemStack(Material.PLAYER_HEAD);
										SkullMeta HeadM = (SkullMeta) Head.getItemMeta();
										HeadM.setOwningPlayer(onlineP);
										HeadM.setDisplayName("§6" + onlineP.getName());
										ArrayList<String> lore = new ArrayList<String>();
										lore.add("§rPositive Bewertungen: §a" + ratingsPositiv);
										lore.add("§rNegative Bewertungen: §c" + ratingsNegativ);
										HeadM.setLore(lore);
										Head.setItemMeta(HeadM);

										inv.addItem(Head);


									}
									p.openInventory(inv);

								}
							}
						}




						
						
						rating.options().copyDefaults(true);
					}
					
					if(args[0].equalsIgnoreCase("help")) {
						if(p.hasPermission("mentor.help")) {
							p.sendMessage("                                                       ");
							p.sendMessage("                   §6Mentor-Command-List                  ");
							p.sendMessage("> §a/mentor §radd §6<Spieler>");
							p.sendMessage("> §a/mentor §rremove §6<Spieler>");
							p.sendMessage("> §a/mentor §rrequest");
							p.sendMessage("> §a/mentor §rlist");
							p.sendMessage("                                                       ");
						}else {
							p.sendMessage("                                                       ");
							p.sendMessage("                   §6Mentor-Command-List                  ");
							p.sendMessage("> §a/mentor §rrequest");
							p.sendMessage("> §a/mentor §rlist");
							p.sendMessage("                                                       ");
						}
						
					}
				
					
				
				}else {
					sender.sendMessage(Main.MentorPrefix + "§rBenutze §6/mentor help §r für eine Liste alle Befehle!");
				}
			}
		
		return false;
	}
	@EventHandler
	public void onoffClick(InventoryClickEvent e) {
		if(e.getClickedInventory() != null) {
			if(e.getView().getTitle().equals("§6Mentor-List")) {
				e.setCancelled(true);
			}
			
		}
	}

}
