package de.modstrype.VolkTutorial.Volk.NPC.NPCBuilder;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.modstrype.VolkTutorial.Main.Main;

public class VolkNPCBuilder {
	
	public static final String NAME = Main.getconfig().getString("settings.General.VolkNPCName");
	
	
	public VolkNPCBuilder(Location location) {
		Villager NPC = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);
		NPC.setCustomName(NAME);
		NPC.setCustomNameVisible(true);
		NPC.setCollidable(false);
		NPC.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 500, false, false));
		
	}
	
	

}
