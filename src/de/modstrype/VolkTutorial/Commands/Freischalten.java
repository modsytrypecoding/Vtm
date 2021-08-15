package de.modstrype.VolkTutorial.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.modstrype.VolkTutorial.Main.Main;




public class Freischalten implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String lable, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 0) {
				FileConfiguration config = Main.getconfig();
				
				if(config.getString("SetFreischalten.World") == null) {
					System.out.println("FreischaltePunkt noch nicht gesetzt!");
				}else {
				World world = Bukkit.getWorld(config.getString("SetFreischalten.World"));
				double x = config.getDouble("SetFreischalten.X");
				double y = config.getDouble("SetFreischalten.Y");
				double z = config.getDouble("SetFreischalten.Z");
				float yaw = (float) config.getDouble("Freischalten.Yaw");
				float pitch = (float) config.getDouble("Freischalten.Pitch");
				Location location = new Location(world, x, y, z, yaw, pitch);
				if(AddCompleted.TutorialComplete.contains(p.getName())) {
					p.sendMessage("§cDieser Befehl funktioniert nur im Tutorial");
				}else {
					p.teleport(location);
					p.sendMessage("§c[§rTutorial§c]: §aDu hast die Server-Geschichte übersprungen und wurdest direkt zu den Völker-Vorstellungen geschickt!");
				}
			}
				
			}
		}
		return false;
	}

}
