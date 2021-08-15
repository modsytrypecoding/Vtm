package de.modstrype.VolkTutorial.Volk.NPC.NPCBuilder;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.modstrype.VolkTutorial.Main.Main;

public class SecondNPCBuilder {
	public static final String NAMENPC2 = Main.getconfig().getString("settings.General.NPCName2");

	public SecondNPCBuilder(Location loc) {
		Villager NPC2 = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
			NPC2.setCustomName(NAMENPC2);
			NPC2.setCustomNameVisible(true);
			NPC2.setCollidable(false);
			NPC2.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 500, false, false));
		}
	}


