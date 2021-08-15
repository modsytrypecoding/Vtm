package de.modstrype.VolkTutorial.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.modstrype.VolkTutorial.Main.Main;
import de.modstrype.VolkTutorial.Volk.NPC.NPCBuilder.FirstNPCBuilder;
import de.modstrype.VolkTutorial.Volk.NPC.NPCBuilder.SecondNPCBuilder;
import de.modstrype.VolkTutorial.Volk.NPC.NPCBuilder.VolkNPCBuilder;
import net.md_5.bungee.api.chat.ClickEvent;


public class Vtm implements CommandExecutor, Listener{
	private int taskID;
	ArrayList<String> hasTalked = new ArrayList<String>();
	ArrayList<String> hasTalked2 = new ArrayList<String>();
	
	String Correct2 = Main.getconfig().getString("settings.Questions.Question2.CorrectAwnser2");
	String Correct3 = Main.getconfig().getString("settings.Questions.Question3.CorrectAwnser3");
	
	private final String InvName = Main.getconfig().getString("settings.General.VolkGUIName");
	public static List<String> GUI_ITEMS_1 = new ArrayList<String>(); 
	public static List<String> GUI_ITEMS_2 = new ArrayList<String>(); 
	public static List<String> GUI_ITEMS_3 = new ArrayList<String>(); 
	public static List<String> GUI_ITEMS_4 = new ArrayList<String>();
	ArrayList<Player> IsNPCthere = new ArrayList<Player>();
	public static ArrayList<String> Volk1 = new ArrayList<>();
	public static ArrayList<String> Volk2 = new ArrayList<>();
	public static ArrayList<String> Volk3 = new ArrayList<>();
	public static ArrayList<String> Volk4 = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("setupstart")) {
					if(p.hasPermission("volk.setupstart")) {
						FileConfiguration config = Main.getconfig();
						config.set("TutorialSpawn.World", p.getWorld().getName());
						config.set("TutorialSpawn.X", p.getLocation().getX());
						config.set("TutorialSpawn.Y", p.getLocation().getY());
						config.set("TutorialSpawn.Z", p.getLocation().getZ());
						config.set("TutorialSpawn.Yaw", p.getLocation().getYaw());
						config.set("TutorialSpawn.Pitch", p.getLocation().getPitch());
						Main.saveconfig();
						p.sendMessage("§aStartupSpawn gesetzt!");
					}else {
						p.sendMessage("§cDir fehlt folgende Berechtigung (§7volk.setupstart§c)");
					}
				}
					
					
			
				if(args[0].equalsIgnoreCase("setupfinish")) {
					if(p.hasPermission("tutorial.setfinishspawn")) {
						FileConfiguration config = Main.getconfig();
						config.set("TutorialFinishSpawn.World", p.getWorld().getName());
						config.set("TutorialFinishSpawn.X", p.getLocation().getX());
						config.set("TutorialFinishSpawn.Y", p.getLocation().getY());
						config.set("TutorialFinishSpawn.Z", p.getLocation().getZ());
						config.set("TutorialFinishSpawn.Yaw", p.getLocation().getYaw());
						config.set("TutorialFinishSpawn.Pitch", p.getLocation().getPitch());
						Main.saveconfig();
						p.sendMessage("§aTutorialFinishPoint gesetzt!");
					}else {
						p.sendMessage("§cDazu hast du keine Berechtigung! (§7tutorial.setfinishspawn§c)" );
					}
					}
				if(args[0].equalsIgnoreCase("setNpc1")) {
					if(p.hasPermission("volk.setnpc1")) {
							taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
								int countdown = 1;
								@Override
								public void run() {
									switch(countdown) {
									case 1:
										for(Entity e : p.getWorld().getEntities()) {
											if(e instanceof Villager) {
												if(e.isCustomNameVisible()) {
													if(!(e.getCustomName().equals(VolkNPCBuilder.NAME) || e.getCustomName().equals(SecondNPCBuilder.NAMENPC2))) {
														e.remove();
														p.sendMessage("§aDer alte NPC wurde erfolgreich entfernt");
													}
														
			
												}	
											}
										}
										break;
										
									case 0:
										new FirstNPCBuilder(p.getLocation());
										p.sendMessage("§aDer NPC wurde erstellt!");
										Bukkit.getScheduler().cancelTask(taskID);
										break;
										default:
									}
									countdown--;
									
								}
							}, 0, 20);
						
						
					}
					
					
				}
				if(args[0].equalsIgnoreCase("setnpc2")) {
					if(p.hasPermission("volk.setnpc2")) {
							taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
								int countdown = 1;
								@Override
								public void run() {
									switch(countdown) {
									case 1:
										for(Entity e : p.getWorld().getEntities()) {
											if(e instanceof Villager) {
												if(e.isCustomNameVisible()) {
													if(!(e.getCustomName().equals(VolkNPCBuilder.NAME) || e.getCustomName().equals(FirstNPCBuilder.NAMENPC1))) {
														e.remove();
														p.sendMessage("§aDer alte NPC wurde erfolgreich entfernt");
													}
														
			
												}	
											}
										}
										break;
										
									case 0:
										new SecondNPCBuilder(p.getLocation());
										p.sendMessage("§aDer NPC wurde erstellt!");
										Bukkit.getScheduler().cancelTask(taskID);
										break;
										default:
									}
									countdown--;
									
								}
							}, 0, 20);
						
						
					}
					
					}
				if(args[0].equalsIgnoreCase("setvolknpc")) {
					if(p.hasPermission("volk.setnpc")) {
							taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
								int countdown = 1;
								@Override
								public void run() {
									switch(countdown) {
									case 1:
										for(Entity e : p.getWorld().getEntities()) {
											if(e instanceof Villager) {
												if(e.isCustomNameVisible()) {
													if(!(e.getCustomName().equals(FirstNPCBuilder.NAMENPC1) || e.getCustomName().equals(SecondNPCBuilder.NAMENPC2))) {
														e.remove();
														p.sendMessage("§aDer alte NPC wurde erfolgreich entfernt");
													}
															

											}
										}
										}
										break;
										
									case 0:
										new VolkNPCBuilder(p.getLocation());
										p.sendMessage("§aDer NPC wurde erstellt!");
										Bukkit.getScheduler().cancelTask(taskID);
										break;
										default:
									}
									countdown--;
									
								}
							}, 0, 20);
					
						
					}
					
					}
				
				if(args[0].equalsIgnoreCase("setRulePoint")) {
					if(p.hasPermission("volk.setRulePoint")) {
						FileConfiguration config = Main.getconfig();
						config.set("SetRulePoint.World", p.getWorld().getName());
						config.set("SetRulePoint.X", p.getLocation().getX());
						config.set("SetRulePoint.Y", p.getLocation().getY());
						config.set("SetRulePoint.Z", p.getLocation().getZ());
						config.set("SetRulePoint.Yaw", p.getLocation().getYaw());
						config.set("SetRulePoint.Pitch", p.getLocation().getPitch());
						Main.saveconfig();
						p.sendMessage("§aRulePoint gesetzt!");
					}else {
						p.sendMessage("§cDazu hast du keine Berechtigung! §c(§7setRulePoint§c)");
					}
				}
				
				if(args[0].equalsIgnoreCase("help")) {
					p.sendMessage("                                                       ");
					p.sendMessage("                   §6Vtm-Command-List                  ");
					p.sendMessage("> §a/vtm setnpc1");
					p.sendMessage("> §a/vtm setnpc2");
					p.sendMessage("> §a/vtm setvolkNpc");
					p.sendMessage("> §a/vtm setRulePoint");
					p.sendMessage("> §a/vtm setupstart");
					p.sendMessage("> §a/vtm setupfinish");
					p.sendMessage("> §a/vtm setFreischalten");
					p.sendMessage("                                                       ");
				}
				
				if(args[0].equalsIgnoreCase("setFreischalten")) {
					if(p.hasPermission("volk.setFreischalten")) {
						FileConfiguration config = Main.getconfig();
						config.set("SetFreischalten.World", p.getWorld().getName());
						config.set("SetFreischalten.X", p.getLocation().getX());
						config.set("SetFreischalten.Y", p.getLocation().getY());
						config.set("SetFreischalten.Z", p.getLocation().getZ());
						config.set("SetFreischalten.Yaw", p.getLocation().getYaw());
						config.set("SetFreischalten.Pitch", p.getLocation().getPitch());
						Main.saveconfig();
						p.sendMessage("§aFreischalte Punkt gesetzt!");
					}
				}
				
					
					
				
				
		}else {
			p.sendMessage("§c[§6Vtm§c]: §rBenutze §a/vtm help §r um dir eine Liste aller Commands anzuzeigen!");
		}
		
		
	}
		return false;
}
	
	

	@EventHandler
	public void handleNPCDamage(EntityDamageByEntityEvent e) {
		if(!(e.getEntity() instanceof Villager)) return;
		Villager NPC = (Villager) e.getEntity();
		if(!(NPC.isCustomNameVisible())) return;
		e.setCancelled(true);
		
		
	}
	
	
	@EventHandler
	public void onNearEntity(PlayerInteractEntityEvent e) {
		if(!(e.getRightClicked() instanceof Villager)) return;
		Villager NPC = (Villager) e.getRightClicked();
		if(NPC.getCustomName().equals(FirstNPCBuilder.NAMENPC1)) {
			e.setCancelled(true);    
		}
		
	}
	@EventHandler
	public void onNearEntity(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		for(Entity en : p.getNearbyEntities(3, 3, 3)) {
			if(en instanceof Villager) {
				if(en.isCustomNameVisible()) {
					if(en.getCustomName().equals(FirstNPCBuilder.NAMENPC1)) {
						if(!(hasTalked.contains(p.getName()))) {
							if(!(AddCompleted.TutorialComplete.contains(p.getName()))) {
							hasTalked.add(p.getName());
							taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
								int countdown = 60;
								
								@Override
								public void run() {
									switch(countdown) {
									case 60:

								        
								        
								        p.sendMessage("§c[§6"+ FirstNPCBuilder.NAMENPC1 + "§c]: §r" + "Hallo Reisender. Ab hier kannst du zwischen Zwei wegen wählen. Du könntest dir zum einen die Server Geschichte angucken um den Server besser kennenzulernen, oder ich kann dich direkt zu den Server-Regeln bringen.");
								        p.sendMessage("                                        ");
								        
								        
								        
								        break;
									case 59:
										p.sendMessage("                    Wie entscheidest du dich?");
										break;
									case 58:
										net.md_5.bungee.api.chat.TextComponent tc2 = new net.md_5.bungee.api.chat.TextComponent();	
								        net.md_5.bungee.api.chat.TextComponent tc3 = new net.md_5.bungee.api.chat.TextComponent();	
								        tc2.setText("       §c[§7Server Geschichte§c]        ");
								        tc3.setText("§c[§7Server-Regeln§c]");
								        
								        tc2.setClickEvent(new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/sendText"));
								        tc3.setClickEvent(new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/tpx"));
								        p.spigot().sendMessage(tc2, tc3);
										break;
									case 0: 
										hasTalked.remove(p.getName());
										break;
										default:
									}
									countdown --;
								}
							}, 0, 20);
							
						}
						}else {
							return;
						}
					}
					
				}
				
				
				
			}
		}
	
	}

	
	@EventHandler
	public void onNearEntity2(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		if(!(e.getRightClicked() instanceof Villager)) return;
		Villager NPC = (Villager) e.getRightClicked();
		if(NPC.getCustomName().equals(SecondNPCBuilder.NAMENPC2)) {
			if(!(AddFalse.addFalse.contains(p.getName()))) {
				e.setCancelled(true);
			}else {
				if(AddFalse.privatecountdown > 0) {
					p.sendMessage("§c[§6"+ SecondNPCBuilder.NAMENPC2 + "§c]: Du kannst erst in §a" + AddFalse.privatecountdown + " §cSekunden erneut zu mir kommen!");
				}else {
					return;
				}
				
			}
				
				}
		
			
			    
		}
		
	
	@EventHandler
	public void onNearEntity2(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		for(Entity en : p.getNearbyEntities(3, 3, 3)) {
			if(en instanceof Villager) {
				if(en.isCustomNameVisible()) {
					if(en.getCustomName().equals(SecondNPCBuilder.NAMENPC2)) {
						if(!(AddCompleted.TutorialComplete.contains(p.getName()))) {
							if(!(hasTalked2.contains(p.getName()))) {
								if(!(AddFalse.addFalse.contains(p.getName()))) {
									hasTalked2.add(p.getName());
									taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
										int countdown = 10;
										
										@Override
										public void run() {
											switch(countdown) {
											case 10:

										        
										        
										        p.sendMessage("§c[§6"+ SecondNPCBuilder.NAMENPC2 + "§c]: §r" + "Um das Tutorial zu beenden musst du dein Wissen unter Beweis stellen. Ich werde dir sobald du bereit bist drei Fragen zu den Server-Regeln stellen beantwortest du alle §arichtig §rkannst du anfangen zu spielen!");
										        p.sendMessage(" Beantwortest du eine Frage §cfalsch §rsolltest du dir die Regeln nochmals durchlesen!");
										        p.sendMessage("                                        ");
										        
										        
										        
										        break;
											case 9:
												p.sendMessage("                           Bist du bereit?");
												break;
											case 8:
												net.md_5.bungee.api.chat.TextComponent tc2 = new net.md_5.bungee.api.chat.TextComponent();	
										        net.md_5.bungee.api.chat.TextComponent tc3 = new net.md_5.bungee.api.chat.TextComponent();	
										        tc2.setText("                         §c[§7Ja§c]           ");
										        tc3.setText("§c[§7Nein§c]");
										        
										        tc2.setClickEvent(new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/sendCorrect1"));
										        tc3.setClickEvent(new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/sendTextNo"));
										        p.spigot().sendMessage(tc2, tc3);
												break;
											case 0: 
												hasTalked2.remove(p.getName());
												Bukkit.getScheduler().cancelTask(taskID);
												break;
												default:
											}
											countdown --;
										}
									}, 0, 20);
								}else {
									return;
								}
									
								
								
								
								
							}else {
								return;
							}
						}else {
							return;
						}
						
					}
					
				}
				
				
				
			}
		}


	}
	
  	
		
	
	
	private void openGUI(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9*3, InvName);
		String s1 = Main.getconfig().getStringList("settings.Items.GUI_Item_1").get(2);
		Material m1 = Material.matchMaterial(s1);
		ItemStack is1 = new ItemStack(m1);
		ItemMeta im1 = is1.getItemMeta();
		im1.setDisplayName(Main.getconfig().getStringList("settings.Items.GUI_Item_1").get(0));
		ArrayList<String> lore1 = new ArrayList<>();
		lore1.add(Main.getconfig().getStringList("settings.Items.GUI_Item_1").get(1));
		im1.setLore(lore1);
		is1.setItemMeta(im1);
		
		String s2 = Main.getconfig().getStringList("settings.Items.GUI_Item_2").get(2);
		Material m2 = Material.matchMaterial(s2);
		ItemStack is2 = new ItemStack(m2);
		ItemMeta im2 = is2.getItemMeta();
		im2.setDisplayName(Main.getconfig().getStringList("settings.Items.GUI_Item_2").get(0));
		ArrayList<String> lore2 = new ArrayList<>();
		lore2.add(Main.getconfig().getStringList("settings.Items.GUI_Item_2").get(1));
		im2.setLore(lore2);
		is2.setItemMeta(im2);
		
		String s3 = Main.getconfig().getStringList("settings.Items.GUI_Item_3").get(2);
		Material m3 = Material.matchMaterial(s3);
		ItemStack is3 = new ItemStack(m3);
		ItemMeta im3 = is3.getItemMeta();
		im3.setDisplayName(Main.getconfig().getStringList("settings.Items.GUI_Item_3").get(0));
		ArrayList<String> lore3 = new ArrayList<>();
		lore3.add(Main.getconfig().getStringList("settings.Items.GUI_Item_3").get(1));
		im3.setLore(lore3);
		is3.setItemMeta(im3);
		
		String s4 = Main.getconfig().getStringList("settings.Items.GUI_Item_4").get(2);
		Material m4 = Material.matchMaterial(s4);
		ItemStack is4 = new ItemStack(m4);
		ItemMeta im4 = is4.getItemMeta();
		im4.setDisplayName(Main.getconfig().getStringList("settings.Items.GUI_Item_4").get(0));
		ArrayList<String> lore4 = new ArrayList<>();
		lore4.add(Main.getconfig().getStringList("settings.Items.GUI_Item_4").get(1));
		im4.setLore(lore4);
		is4.setItemMeta(im4);
		
		inv.setItem(10, is1);
		inv.setItem(12, is2);
		inv.setItem(14, is3);
		inv.setItem(16, is4);
		p.openInventory(inv);

	}


	
	
	@EventHandler
	public void handleNPCinteract(PlayerInteractEntityEvent e) {
		if(!(e.getRightClicked() instanceof Villager)) return;
		Villager NPC = (Villager) e.getRightClicked();
		Player p = e.getPlayer();
		if(NPC.getCustomName().equals(VolkNPCBuilder.NAME)) {
			e.setCancelled(true);
			if(Volk1.contains(p.getName()) || Volk2.contains(p.getName()) || Volk3.contains(p.getName()) || Volk4.contains(p.getName())) {
				p.sendMessage("§c[§6" +VolkNPCBuilder.NAME + "§c]: " +"§cDu gehörst bereits einem Volk an!");
				
			}else {
				openGUI(e.getPlayer());
			}
			
		}
		
	}
	@EventHandler
	public void handleNavGUIclick(InventoryClickEvent event) {
		if(!(event.getWhoClicked() instanceof Player)) return;
			Player p = (Player) event.getWhoClicked();
			String s1 = Main.getconfig().getStringList("settings.Items.GUI_Item_1").get(2);
			Material m1 = Material.matchMaterial(s1);
			String s2 = Main.getconfig().getStringList("settings.Items.GUI_Item_2").get(2);
			Material m2 = Material.matchMaterial(s2);
			String s3 = Main.getconfig().getStringList("settings.Items.GUI_Item_3").get(2);
			Material m3 = Material.matchMaterial(s3);
			String s4 = Main.getconfig().getStringList("settings.Items.GUI_Item_4").get(2);
			Material m4 = Material.matchMaterial(s4);
					if(event.getView().getTitle().equals(InvName)) {
						if(event.getCurrentItem() != null) {
							if(event.getCurrentItem().getType().equals(m1)) {
								Volk1.add(p.getName());
								p.sendMessage("§6Du bist nun im Volk der: §a" + Main.getconfig().getStringList("settings.Items.GUI_Item_1").get(0));
								Main.getconfig().set("settings.Volk." + Main.getconfig().getStringList("settings.Items.GUI_Item_1").get(0), Volk1);
								Main.saveconfig();
								p.closeInventory();
						}
							if(event.getCurrentItem().getType().equals(m2)) {
								Volk2.add(p.getName());
								p.sendMessage("§6Du bist nun im Volk der: §a" + Main.getconfig().getStringList("settings.Items.GUI_Item_2").get(0));
								Main.getconfig().set("settings.Volk." + Main.getconfig().getStringList("settings.Items.GUI_Item_2").get(0), Volk2);
								Main.saveconfig();
								p.closeInventory();
						}
							if(event.getCurrentItem().getType().equals(m3)) {
								Volk3.add(p.getName());
								p.sendMessage("§6Du bist nun im Volk der: §a" + Main.getconfig().getStringList("settings.Items.GUI_Item_3").get(0));
								Main.getconfig().set("settings.Volk." + Main.getconfig().getStringList("settings.Items.GUI_Item_3").get(0), Volk3);
								Main.saveconfig();
								p.closeInventory();
						}
							if(event.getCurrentItem().getType().equals(m4)) {
								Volk4.add(p.getName());
								p.sendMessage("§6Du bist nun im Volk der: §a" + Main.getconfig().getStringList("settings.Items.GUI_Item_4").get(0));
								Main.getconfig().set("settings.Volk." + Main.getconfig().getStringList("settings.Items.GUI_Item_4").get(0), Volk4);
								Main.saveconfig();
								p.closeInventory();
						}
						}
						
						
							
						
						
					}
				
				
					
						
				}
	
	@EventHandler
	public void handleNPCDamage2(EntityDamageByEntityEvent e) {
		if(!(e.getEntity() instanceof Villager)) return;
		Villager NPC = (Villager) e.getEntity();
		if(!(NPC.isCustomNameVisible())) return;
			e.setCancelled(true);
		
		
	}
	@EventHandler
	public void onEntitiyTargetVillager(EntityTargetEvent e)  {
		if(e.getTarget() instanceof Villager) {
			if(e.getTarget().isCustomNameVisible()) {
				if(e.getEntity() instanceof Monster) {
					e.setCancelled(true);
					return;
				} 
			}
			
		}
		
	}
	@EventHandler 
	public void onMovePlayer(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if(Volk1.contains(p.getName()) || Volk2.contains(p.getName()) || Volk3.contains(p.getName()) || Volk4.contains(p.getName())) {
			e.setCancelled(false);
		}else {
			for(Entity en : p.getWorld().getEntities()) {
				if(en instanceof Villager) {
					if(en.isCustomNameVisible()) {
						if(!(en.getCustomName().equals(FirstNPCBuilder.NAMENPC1) || en.getCustomName().equals(SecondNPCBuilder.NAMENPC2))) {
							Location locNPC = en.getLocation();
							if((p.getLocation().getBlockY() -1 > locNPC.getBlockY() )) {
								e.setCancelled(false);
							}else {
								if(p.getLocation().getBlockX() == locNPC.getBlockX() -3 ) {
									p.teleport(new Location(p.getWorld(), p.getLocation().getBlockX() + 5, p.getLocation().getBlockY(), p.getLocation().getBlockZ(), p.getLocation().getYaw(), p.getLocation().getPitch()));
									p.sendMessage("§c[§6" + Main.getconfig().getString("settings.General.VolkNPCName") + "§c]: " +"§cBitte wähle zuerst dein §6Volk§c!");
								}
								
							}
						}
						
						
							
					}	
				}
			}
		}
		
		}
	
}
