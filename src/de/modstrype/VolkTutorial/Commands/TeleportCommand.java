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

import de.modstrype.VolkTutorial.Main.Main;
import de.modstrype.VolkTutorial.Volk.NPC.NPCBuilder.FirstNPCBuilder;



public class TeleportCommand implements CommandExecutor{
	public static ArrayList<Player> History = new ArrayList<Player>();
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
		
		
		FileConfiguration config = Main.getconfig();
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
					if(args.length == 0) {
						if(config.getString("SetRulePoint.World") == null) {
							System.out.println("RulePoint noch nicht gesetz!");
						}else {
						World world2 = Bukkit.getWorld(config.getString("SetRulePoint.World"));
						double x2 = config.getDouble("SetRulePoint.X");
						double y2 = config.getDouble("SetRulePoint.Y");
						double z2 = config.getDouble("SetRulePoint.Z");
						float yaw2 = (float) config.getDouble("SetRulePoint.Yaw");
						float pitch2 = (float) config.getDouble("SetRulePoint.Pitch");
						Location RulePoint = new Location(world2, x2, y2, z2, yaw2, pitch2);
						History.add(p);
						p.teleport(RulePoint);
						p.sendMessage("§c[§6"+ FirstNPCBuilder.NAMENPC1 + "§c]: §rAlles Klar! Folge einfach dem Weg weiter hinunter.");
						}
						
						
					
						
						}else if(args.length == 1) {
							Player t = Bukkit.getPlayer(args[0]);
							if(args[0].equalsIgnoreCase(t.getName())) {
								p.teleport(t.getLocation());
						}
					}
					
					
				
			
				
		}else {
			
		}
	
		return false;
	}
}


