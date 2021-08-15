package de.modstrype.VolkTutorial.Commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.modstrype.VolkTutorial.Main.Main;


public class Rate implements CommandExecutor, Listener{
	Inventory inv = Bukkit.createInventory(null, 9*3, "§6Bewerten");
	ArrayList<Player> rated = new ArrayList<Player>();
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 1) {
				if(!(p.getWorld().getName().equalsIgnoreCase("Tutorial"))) {
					if (!(rated.contains(p))) {
						rated.add(p);
						ItemStack redWool = new ItemStack(Material.RED_WOOL);
						ItemMeta redMeta = redWool.getItemMeta();
						redMeta.setDisplayName("§cSchlecht");
						redMeta.addEnchant(Enchantment.LUCK, 3, true);
						redMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
						redWool.setItemMeta(redMeta);

						redWool.setAmount(1);
						inv.setItem(16, redWool);


						ItemStack greenWool = new ItemStack(Material.GREEN_WOOL);
						ItemMeta greenMeta = redWool.getItemMeta();
						greenMeta.setDisplayName("§aGut");
						greenMeta.addEnchant(Enchantment.LUCK, 3, true);
						greenMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
						greenWool.setItemMeta(greenMeta);

						greenWool.setAmount(1);
						inv.setItem(10, greenWool);


						ItemStack Barrier = new ItemStack(Material.BARRIER);
						ItemMeta b = Barrier.getItemMeta();
						b.setDisplayName("§7Inventar schließen. (Keine Bewertung)");
						Barrier.setItemMeta(b);

						Barrier.setAmount(1);
						inv.setItem(22, Barrier);


						p.openInventory(inv);

					} else {
						p.sendMessage("§cDu hast deine Bewertung schon abegegeben!");
					}

				}
			}else {
			}
		}
		return false;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();

			if(e.getClickedInventory() != null) {
				if(e.getView().getTitle().equals("§6Bewerten")) {
					if(e.getCurrentItem() != null) {
						switch(e.getCurrentItem().getType()) {

						case GREEN_WOOL: 	
							Player tg = Bukkit.getPlayer(Main.getconfig().getString(p.getName()));
							if(tg != null) {
								p.sendMessage("§rDanke für deine §apositive §rBewertung");
								tg.sendMessage("Du wurdest §apositiv §rBewertet!");
								addRatingPositiv(tg.getName());
								Main.getconfig().set(p.getName(), null);
								Main.saveconfig();
								p.closeInventory();
								break;
								
							}
							
							
							
						case RED_WOOL:
							Player tr = Bukkit.getPlayer(Main.getconfig().getString(p.getName()));
							if(tr != null) {
								p.sendMessage("Deine §cnegative §rBewertung wurde abgegeben!");
								tr.sendMessage("Du wurdest §cnegativ §rBewertet!");
								tr.sendMessage("Du denkst diese Bewertung war unberechtigt? Geh auf unsern Discord und wende dich dort an die anwesenden Supporter und/oder Moderatoren.");
								
								addRatingNegativ(tr.getName());
								Main.getconfig().set(p.getName(), null);
								Main.saveconfig();
								p.closeInventory();
								break;
							}
							
							
						case BARRIER:
							p.closeInventory();
							break;
							
						default:
							e.setCancelled(true);
							break;
						}
					}
				}
			}
				
	}
	
	public int getRatingsPositiv(String player) {
		File rateFile = new File(Main.getPlugin().getDataFolder(), "Rating.yml");
		FileConfiguration rate = YamlConfiguration.loadConfiguration(rateFile);
		int rates = rate.get("Player." + player + ".RatingsPositiv") != null ? rate.getInt("Player."+player+".RatingsPositiv") : 0;
		return rates;
		
	}
	public int getRatingsNegativ(String player) {
		File rateFile = new File(Main.getPlugin().getDataFolder(), "Rating.yml");
		FileConfiguration rate = YamlConfiguration.loadConfiguration(rateFile);
		int rates = rate.get("Player." + player + ".RatingsNegativ") != null ? rate.getInt("Player."+player+".RatingsNegativ") : 0;
		return rates;
		
	}
	
	public void addRatingPositiv(String player) {
		File rateFile = new File(Main.getPlugin().getDataFolder(), "Rating.yml");
		FileConfiguration rate =YamlConfiguration.loadConfiguration(rateFile);
		rate.set("Player." + player + ".RatingsPositiv", getRatingsPositiv(player) + 1);
		try {
			rate.save(rateFile);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	public void addRatingNegativ(String player) {
		File rateFile = new File(Main.getPlugin().getDataFolder(), "Rating.yml");
		FileConfiguration rate =YamlConfiguration.loadConfiguration(rateFile);
		rate.set("Player." + player + ".RatingsNegativ", getRatingsNegativ(player) + 1);
		try {
			rate.save(rateFile);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	

}
