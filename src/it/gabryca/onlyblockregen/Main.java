package it.gabryca.onlyblockregen;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main config;

    static Main getInstance(){
        return config;
    }

    @Override
    public void onEnable() {
        System.out.println(ChatColor.GREEN + "[OnlyBlockRegen] Plugin enabled with success!");
        Bukkit.getPluginManager().registerEvents(new listeners(),this);
        getCommand("OnlyBlockRegen").setExecutor(new OnlyBlockRegen());
        getCommand("OnlyBlockGUI").setExecutor(new OnlyBlockGUI());
        getCommand("OnlyBlockAdd").setExecutor(new OnlyBlockAdd());
        this.saveDefaultConfig();
        config = this;
        this.saveConfig();
        new MessagesYML();
    }
    @Override
    public void onDisable() {
        System.out.println(ChatColor.GREEN + "[OnlyBlockRegen] Plugin disabled with success!");
    }

    static FileConfiguration getMessages(){
        MessagesYML messages = new MessagesYML();
        return messages.getFile();
    }
}
