package it.gabryca.onlyblockregen;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class OnlyBlockGUI implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Configuration config = Main.getInstance().getConfig();
        Configuration messages = Main.getMessages();

        if (commandSender.hasPermission(config.getString("Permissions.OnlyBlockRegen-MainGUI"))) {
            if (commandSender instanceof Player){
                Player p = (Player) commandSender;
                OnlyBlockGUIMaker gui = new OnlyBlockGUIMaker(p);
                gui.open();
            } else {
                commandSender.sendMessage(messages.getString("Messages.Warn-ConsoleIsSender"));
            }
        } else {
            commandSender.sendMessage(messages.getString("Messages.Warn-NoPermission") + " [" + config.getString("Permissions.OnlyBlockRegen-MainGUI") + "]");
        }
        return true;
    }
}
