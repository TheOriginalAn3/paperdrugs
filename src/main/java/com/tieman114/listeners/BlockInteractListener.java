package com.tieman114.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
// import org.bukkit.NamespacedKey;
// import org.bukkit.block.TileState;
// import org.bukkit.persistence.PersistentDataType;
// import org.bukkit.plugin.Plugin;
// import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.inventory.EquipmentSlot;

import com.tieman114.commands.Debugger;
import com.tieman114.fileManagers.AnvilLocationManager;

import net.md_5.bungee.api.ChatColor;

public class BlockInteractListener implements Listener {

    // private JavaPlugin plugin;
    // public BlockInteractListener(JavaPlugin plugin) {
    // this.plugin = plugin;
    // }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();

        // Decorative Anvil
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getHand() == EquipmentSlot.HAND) {
                Debugger.sendDebugToPlayer(event.getPlayer(), "R-Clicked Block <" + block.getType() + "> with Main Hand");
            }

            if (block != null && (block.getType() == Material.ANVIL)) {
                if (AnvilLocationManager.getDecorativeAnvilLocations().contains(block.getLocation())) {
                    event.setCancelled(true);
                    Debugger.sendMessageToPlayer(event.getPlayer(),
                            ChatColor.GRAY + "This anvil is for decoration only!");
                }
            }

            // if (block.getState() instanceof TileState) {
            // TileState state = (TileState) block.getState();
            // int isDecorative = state.getPersistentDataContainer().get(new
            // NamespacedKey(plugin, "isDecorative"), PersistentDataType.INTEGER);
            // event.getPlayer().sendMessage("Clicked Block isDecorative " + isDecorative);
            // if (isDecorative == 1) {
            // event.setCancelled(true);
            // event.getPlayer().sendMessage(ChatColor.GRAY + "This anvil is for decoration
            // only!");
            // }
            // } else event.getPlayer().sendMessage(ChatColor.RED + "Clicked Block is not
            // instance of TileState!");
        } else
        try {
            Debugger.sendDebugToPlayer(event.getPlayer(), "L-Clicked Block <" + block.getType() + ">.");
        } catch (Exception e) {
            Debugger.sendDebugToPlayer(event.getPlayer(), "L-Clicked on <Air>.");
        }

    }
}
