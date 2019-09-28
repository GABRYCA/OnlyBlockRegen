package it.gabryca.onlyblockregen;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

class MessagesYML {

    private File file = new File(Main.getInstance().getDataFolder()+"/messages.yml");
    private FileConfiguration conf;

    MessagesYML() {
        if(!file.exists()){
            try {
                file.createNewFile();
                conf = YamlConfiguration.loadConfiguration(file);
                conf.createSection("Messages");
                conf.set("Messages.Warn-NoPermission", "§cSorry, but you don't have the permission to do that!");
                conf.set("Messages.Block-Add-Success", "§aBlock added with success");
                conf.set("Messages.Warn-ConsoleIsSender", "§cOnly Players can use this command (Hi console!)");
                conf.set("Messages.Blocks", "Blocks");
                conf.set("Messages.Delay", "Delay");
                conf.set("Messages.Click-Open", "§aClick to open");
                conf.set("Messages.Warn-NotMaterial", "Sorry, but that's not a valid block");
                conf.set("Messages.Block-Changed", "Block edited with success");
                conf.set("Messages.Warn-Format", "Wrong format, try to use something like /onlyblockadd <Block> <BlockAfterBreak> <DelayInSeconds>");
                conf.set("Messages.Delete", "Right click to delete ALL blocks");
                conf.set("Messages.BlocksDeletedSuccess", "All blocks deleted with success");
                conf.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        conf = YamlConfiguration.loadConfiguration(file);
    }

    FileConfiguration getFile(){
        return conf;
    }

}
