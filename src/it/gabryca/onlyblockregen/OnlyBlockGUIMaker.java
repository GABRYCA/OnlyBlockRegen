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

class OnlyBlockGUIMaker {

    private Player p;

     OnlyBlockGUIMaker(Player p) {
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

            Configuration config = Main.getInstance().getConfig();
            Configuration message = Main.getMessages();

            Inventory inv = Bukkit.createInventory(null,27,"§dOnlyBlockRegen");

            List<String> lore = new ArrayList<String>();
            lore.add(message.getString("Messages.Click-Open"));
            String display = "§6" + message.getString("Messages.Blocks");
            inv.setItem(13,createButton(Material.PAPER, 1, lore, "§6" + display));

            this.p.openInventory(inv);
        }
    }