package it.gabryca.onlyblockregen;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class BlocksGUI {

    Configuration config = Main.getInstance().getConfig();
    Configuration message = Main.getMessages();
    int dimension = 0;

    private Player p;

    BlocksGUI(Player p){
        this.p = p;
    }

    private ItemStack createButton(Material id, int amount, List<String> lore, String display) {

        ItemStack item = new ItemStack(id, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(display);
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    void open() {

        if (config.getConfigurationSection("blocks") == null){
            return;
        }
            Set<String> shops = config.getConfigurationSection("blocks").getKeys(false);
            int num = shops.size();
            while (dimension < num + 8) {
                dimension = dimension + 9;
            }
            Inventory inv = Bukkit.createInventory(null, dimension, "§7Blocks");
            for (String key : shops) {
                if (!(Material.getMaterial(config.getString("blocks." + key + ".block")) == null)) {
                    List<String> lore = new ArrayList<String>();
                    lore.add("§2" + message.getString("Messages.Blocks") + ": §7" + config.getString("blocks." + key + ".block"));
                    lore.add("§2" + message.getString("Messages.Delay") + ": §6" + config.getString("blocks." + key + ".delay"));
                    lore.add("§c" + message.getString("Messages.Delete"));
                    inv.addItem(createButton(Material.valueOf(String.valueOf(config.getString("blocks." + key + ".block"))), 1, lore, "§6" + config.getString("blocks." + key + ".block")));
                } else {
                    return;
                }
            }
            this.p.openInventory(inv);
    }
}
