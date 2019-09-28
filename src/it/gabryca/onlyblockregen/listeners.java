package it.gabryca.onlyblockregen;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.List;

public class listeners implements Listener{

    private List<Block> unbreakable = new ArrayList<>();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Configuration config = Main.getInstance().getConfig();

        if (e.getPlayer().hasPermission("Permission.BlockBreak")) {
            return;
        }

        if (unbreakable.contains(e.getBlock())) {
            e.setCancelled(true);
            return;
        }

        Material originalMaterial = e.getBlock().getType();

        if (!config.isSet("blocks." + originalMaterial)) {
            return;
        }

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
            String delayType = config.getString("blocks." + originalMaterial + ".delayblock");
            e.getBlock().setType(Material.getMaterial(delayType));
        });

        unbreakable.add(e.getBlock());

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
            e.getBlock().setType(originalMaterial);
            unbreakable.remove(e.getBlock());
        }, 20L * config.getInt("blocks." + originalMaterial + ".delay"));
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        Configuration config = Main.getInstance().getConfig();
        Configuration message = Main.getMessages();

        Player p = (Player) e.getWhoClicked();

        if (e.getCurrentItem() == null) {
            return;
        }

        if (e.getView().getTitle().equals("§d" + "OnlyBlockRegen")){

            if (e.getCurrentItem() == null){
                return;
            }

            if (!(e.getCurrentItem().hasItemMeta())){
                return;
            }

            if (e.getCurrentItem().getItemMeta().getDisplayName().substring(2).equals("§6" + message.getString("Messages.Blocks"))){
                BlocksGUI gui = new BlocksGUI(p);
                p.closeInventory();
                gui.open();
            }
        }

        if (e.getView().getTitle().equals("§7" + "Blocks")){

            if (e.getCurrentItem() == null){
                return;
            }

            if (!(e.isRightClick())){
                return;
            }

                if (e.getCurrentItem().hasItemMeta()){
                    String block = e.getCurrentItem().getItemMeta().getDisplayName().substring(2);
                    config.set("blocks." + block, null);
                    config.set("blocks", null);
                    Main.getInstance().saveConfig();
                    BlocksGUI gui = new BlocksGUI(p);
                    p.closeInventory();
                    gui.open();
                    p.sendMessage("§a" + message.getString("Messages.BlocksDeletedSuccess"));
                }
        }
    }
}
