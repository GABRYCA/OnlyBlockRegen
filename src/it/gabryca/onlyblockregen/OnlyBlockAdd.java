package it.gabryca.onlyblockregen;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;

public class OnlyBlockAdd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Configuration config = Main.getInstance().getConfig();
        Configuration message = Main.getMessages();

        if (!(commandSender.hasPermission(config.getString("Permissions.AddBlockPermission")))){
            commandSender.sendMessage(ChatColor.RED + message.getString("Messages.Warn-NoPermission") + " [" + config.getString("Permissions.AddBlockPermission") + "]");
            return true;
        }

        if (!(strings.length == 3)){
            commandSender.sendMessage(ChatColor.RED + message.getString("Messages.Warn-Format"));
            return true;
        }

        if (Material.getMaterial(strings[0]) == null){
            commandSender.sendMessage(ChatColor.RED + message.getString("Messages.Warn-NotMaterial") + " [ " + strings[0] + " ]");
            return true;
        }

        if (Material.getMaterial(strings[1]) == null){
            commandSender.sendMessage(ChatColor.RED + message.getString("Messages.Warn-NotMaterial") + " [ " + strings[1] + " ]");
            return true;
        }

        int delay = Integer.parseInt(strings[2]);
        if (config.getString("blocks." + strings[0] + ".block") == null) {
            config.set("blocks." + strings[0] + ".block", strings[0]);
            config.set("blocks." + strings[0] + ".delayblock", strings[1]);
            config.set("blocks." + strings[0] + ".delay", delay);
            Main.getInstance().saveConfig();
            commandSender.sendMessage("§a" + message.get("Messages.Block-Add-Success"));
        } else {
            config.set("blocks." + strings[0] + ".block", strings[0]);
            config.set("blocks." + strings[0] + ".delayblock", strings[1]);
            config.set("blocks." + strings[0] + ".delay", delay);
            Main.getInstance().saveConfig();
            commandSender.sendMessage("§a" + message.get("Messages.Block-Changed"));
        }
        return true;
    }
}
