package de.modstrype.VolkTutorial.Api;

import de.modstrype.VolkTutorial.Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class VtmApi extends JavaPlugin {
    public static class RealApi {
        public String getPlayerVolk(Player p) {
            Bukkit.getConsoleSender().sendMessage(p.getName());
            if(!unserializeArrayList(PlayersInList()).contains(p.getName())) {
                return null;
            }

            for(String key : Main.getconfig().getConfigurationSection("settings.Volk").getKeys(true)) {
                ArrayList<ArrayList<String>> temp = PlayerInVolk(key);
                for(ArrayList<String> temp2 : temp) {
                    if(temp2.contains(p.getName())) {
                        return key;
                    }
                }
            }
            return null;
        }

        public ArrayList<List<String>> PlayersInList() {
            ArrayList<List<String>> storage = new ArrayList<>();
            storage.add(Main.getconfig().getStringList("settings.Volk." + Main.getconfig().getStringList("settings.Items.GUI_Item_1").get(0)));
            storage.add(Main.getconfig().getStringList("settings.Volk." + Main.getconfig().getStringList("settings.Items.GUI_Item_2").get(0)));
            storage.add(Main.getconfig().getStringList("settings.Volk." + Main.getconfig().getStringList("settings.Items.GUI_Item_3").get(0)));
            storage.add(Main.getconfig().getStringList("settings.Volk." + Main.getconfig().getStringList("settings.Items.GUI_Item_4").get(0)));

            Bukkit.getConsoleSender().sendMessage(storage.toString());
            return storage;


        }

        public ArrayList<ArrayList<String>> PlayerInVolk(String Volk) {
            ArrayList<ArrayList<String>> temp_return_list = new ArrayList<>();
            temp_return_list.add(new ArrayList<>(Main.getconfig().getStringList("settings." + "Volk." + Volk)));
            return temp_return_list;
        }
    }
    public static ArrayList<String> unserializeArrayList(ArrayList<List<String>> temp)
    {
        ArrayList<String> result = new ArrayList<>();
        for(List<String> inner_list : temp)
        {
            for(String result_string : inner_list)
            {
                result.add(result_string);
            }
        }
        return result;
    }



}
