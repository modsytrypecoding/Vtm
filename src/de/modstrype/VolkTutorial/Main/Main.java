package de.modstrype.VolkTutorial.Main;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


import de.modstrype.VolkTutorial.Commands.AddCompleted;
import de.modstrype.VolkTutorial.Commands.AddFalse;
import de.modstrype.VolkTutorial.Commands.Freischalten;
import de.modstrype.VolkTutorial.Commands.Rate;
import de.modstrype.VolkTutorial.Commands.TeleportCommand;
import de.modstrype.VolkTutorial.Commands.Vtm;

import de.modstrype.VolkTutorial.Mentor.MC;
import de.modstrype.VolkTutorial.Mentor.MentorAdd;
import de.modstrype.VolkTutorial.TextCommands.DeclineHelp;
import de.modstrype.VolkTutorial.TextCommands.IsHelped;
import de.modstrype.VolkTutorial.TextCommands.OfferHelp;
import de.modstrype.VolkTutorial.TextCommands.SendCorrect3;
import de.modstrype.VolkTutorial.TextCommands.SendText;
import de.modstrype.VolkTutorial.TextCommands.TextNo;
import de.modstrype.VolkTutorial.TextCommands.sendCorrect1;
import de.modstrype.VolkTutorial.TextCommands.sendCorrect2;
import de.modstrype.VolkTutorial.TutorialStart.JoinListener;
import de.modstrype.VolkTutorial.TutorialStart.StopRecevingMessages;
import de.modstrype.VolkTutorial.TutorialStart.StopSendingMessages;


public class Main extends JavaPlugin{
	public static final String MentorPrefix = "§c[§6Mentor§c]: ";
	private static Main plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
	static final File configFile = new File("plugins//Volk-Tutorial//config.yml");
	private static YamlConfiguration configfileConfiguration = YamlConfiguration.loadConfiguration(configFile);
	
	
	public void onEnable() {
		plugin = this;
		
		logger.info(" ");
		logger.info("--------------------------------------------------------");
		logger.info(this.getName()  + " hat gestartet");
		logger.info("Falls Fehler auftreten sollten, bitte melden!");
		logger.info("Version: 1.1");
		logger.info("--------------------------------------------------------");
		logger.info(" ");
		
		
		File statsFile = new File(getDataFolder(), "Rating.yml");
		if(!statsFile.exists()) {
		}try {
			statsFile.createNewFile();
		}catch (IOException ex) {
			ex.printStackTrace();
		}
		Main.loadConfig();
		
		
		getCommand("sendText").setExecutor(new SendText());
		getCommand("sendCorrect1").setExecutor(new sendCorrect1());
		getCommand("sendCorrect2").setExecutor(new sendCorrect2());
		getCommand("sendCorrect3").setExecutor(new SendCorrect3());
		getCommand("addFalse").setExecutor(new AddFalse());
		getCommand("addComplete").setExecutor(new AddCompleted());
		getCommand("tpx").setExecutor(new TeleportCommand());
		getCommand("vtm").setExecutor(new Vtm());
		getCommand("mentor").setExecutor(new MentorAdd());
		getCommand("offerHelp").setExecutor(new OfferHelp());
		getCommand("isHelped").setExecutor(new IsHelped());
		getCommand("declineHelp").setExecutor(new DeclineHelp());
		getCommand("freischalten").setExecutor(new Freischalten());
		getCommand("mchat").setExecutor(new MC());
		getCommand("rate").setExecutor(new Rate());
		getCommand("sendTextNo").setExecutor(new TextNo());
	
		
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new JoinListener(), this);
		pluginManager.registerEvents(new StopSendingMessages(), this);
		pluginManager.registerEvents(new Vtm(), this);
		pluginManager.registerEvents(new AddCompleted(), this);
		pluginManager.registerEvents(new StopRecevingMessages(), this);
		pluginManager.registerEvents(new Rate(), this);
		pluginManager.registerEvents(new MentorAdd(), this);
		
		
	}
	
	
	public static Main getPlugin() {
		return plugin;
	}

	public static void loadConfig() {
		if(!configFile.exists() || !configfileConfiguration.isSet("settings")) {
			configfileConfiguration.createSection("settings.General");
			configfileConfiguration.createSection("settings.Volk");
			configfileConfiguration.createSection("settings.Questions");
			configfileConfiguration.createSection("settings.Items");
			configfileConfiguration.createSection("settings.Messages");

			configfileConfiguration.set("settings.General.VolkNPCName", "Gustav");
			configfileConfiguration.set("settings.General.NPCName1", "Klaus");
			configfileConfiguration.set("settings.General.NPCName2", "Günther");
			configfileConfiguration.set("settings.General.VolkGUIName", "§cVolkentscheidung");
			configfileConfiguration.set("settings.Questions.Question1", "Question1");
			configfileConfiguration.set("settings.Questions.Question1.CorrectAwnser1", "Test1");
			configfileConfiguration.set("settings.Questions.Question1.FalseAwnser1", "Test1");
			configfileConfiguration.set("settings.Questions.Question1.FalseAwnser2", "Test1");
			configfileConfiguration.set("settings.Questions.Question2", "settings.Questions.Question2");
			configfileConfiguration.set("settings.Questions.Question2.CorrectAwnser2", "Test2");
			configfileConfiguration.set("settings.Questions.Question2.FalseAwnser1", "Test2");
			configfileConfiguration.set("settings.Questions.Question2.FalseAwnser2", "Test2");
			configfileConfiguration.set("settings.Questions.Question3", "settings.Questions.Question3");
			configfileConfiguration.set("settings.Questions.Question3.CorrectAwnser3", "Test3");
			configfileConfiguration.set("settings.Questions.Question3.FalseAwnser1", "Test3");
			configfileConfiguration.set("settings.Questions.Question3.FalseAwnser2", "Test3");
			configfileConfiguration.set("settings.Items.GUI_Item_1", Vtm.GUI_ITEMS_1);
			Vtm.GUI_ITEMS_1.add("Menschen");
			Vtm.GUI_ITEMS_1.add("TestLore");
			Vtm.GUI_ITEMS_1.add("sea_lantern");
			configfileConfiguration.set("settings.Items.GUI_Item_2", Vtm.GUI_ITEMS_2);
			Vtm.GUI_ITEMS_2.add("Halblinge");
			Vtm.GUI_ITEMS_2.add("TestLore");
			Vtm.GUI_ITEMS_2.add("sea_lantern");
			configfileConfiguration.set("settings.Items.GUI_Item_3", Vtm.GUI_ITEMS_3);
			Vtm.GUI_ITEMS_3.add("Elfen");
			Vtm.GUI_ITEMS_3.add("TestLore");
			Vtm.GUI_ITEMS_3.add("sea_lantern");
			configfileConfiguration.set("settings.Items.GUI_Item_4", Vtm.GUI_ITEMS_4);
			Vtm.GUI_ITEMS_4.add("Zwerge");
			Vtm.GUI_ITEMS_4.add("TestLore");
			Vtm.GUI_ITEMS_4.add("sea_lantern");
			configfileConfiguration.set("settings.Volk." + Main.getconfig().getStringList("settings.Items.GUI_Item_1").get(0), Vtm.Volk1);
			configfileConfiguration.set("settings.Volk." + Main.getconfig().getStringList("settings.Items.GUI_Item_2").get(0), Vtm.Volk2);
			configfileConfiguration.set("settings.Volk." + Main.getconfig().getStringList("settings.Items.GUI_Item_3").get(0), Vtm.Volk3);
			configfileConfiguration.set("settings.Volk." + Main.getconfig().getStringList("settings.Items.GUI_Item_4").get(0), Vtm.Volk4);
			configfileConfiguration.set("settings.Messages.ArgstoShort", "§cBitte benutze §6/searchPlayer <Name>");
			configfileConfiguration.set("settings.Messages.PlayerHasntPlayed", "§cDieser Spieler war noch nie auf diesem Server");
			configfileConfiguration.set("settings.Messages.NoPermissions", "§cDazu hast du keine Berechtigung!");
			configfileConfiguration.set("settings.Messages.InvName", "§cInformationen");


			try {
				configfileConfiguration.save(configFile);
				configfileConfiguration = YamlConfiguration.loadConfiguration(configFile);
			}catch (IOException e) {
				getPlugin().getLogger().info("[Error] Erstellen der Config.yml" + e);

			}
		}

	}
	public static YamlConfiguration getconfig() {
		return configfileConfiguration;
	}

	public static void saveconfig()
	{
		try {
			configfileConfiguration.save(configFile);
			configfileConfiguration = YamlConfiguration.loadConfiguration(configFile);
		} catch (IOException e) {
			getPlugin().getLogger().info("Fehler beim Speichern der config.yml: " + e);
		}
	}
	
	public void onDisable() {
		logger.info(" ");
		logger.info("-------------------------------------------");
		logger.info(this.getName() + " wird beendet!");
		logger.info("Das Plugin ist deaktiviert.");
		logger.info("/pluginmanager reload Volk-Tutorial um das Plugin erneut zu laden!");
		logger.info("-------------------------------------------");
		logger.info(" ");
	}

}
