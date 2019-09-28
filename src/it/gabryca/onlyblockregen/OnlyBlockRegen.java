package it.gabryca.onlyblockregen;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;

public class OnlyBlockRegen implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Configuration config = Main.getInstance().getConfig();
        Configuration messages = Main.getMessages();

        if (commandSender.hasPermission(config.getString("Permissions.OnlyBlockRegen-CommandList"))){
            commandSender.sendMessage("§7§lCommands:");
            commandSender.sendMessage("§7 - §b /OnlyBlockAdd");
            commandSender.sendMessage("§7 - §b /OnlyBlockRegen");
            commandSender.sendMessage("§7 - §b /OnlyBlockGUI");
        } else {
            commandSender.sendMessage(messages.getString("Messages.Warn-NoPermission") + " [" + config.getString("Permissions.OnlyBlockRegen-CommandList") + "]");
        }
        return true;
    }
}
