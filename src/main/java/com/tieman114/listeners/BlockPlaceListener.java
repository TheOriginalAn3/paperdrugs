package com.tieman114.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.tieman114.commands.Debugger;
import com.tieman114.fileManagers.AnvilLocationManager;

import net.md_5.bungee.api.ChatColor;

public class BlockPlaceListener implements Listener {
    // private JavaPlugin plugin;
    // public BlockPlaceListener(JavaPlugin plugin) {
    // this.plugin = plugin;
    // }

    

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        ItemStack item = event.getPlayer().getItemInHand();

        if (item.getType() == Material.ANVIL) {
            ItemMeta meta = item.getItemMeta();
            if (meta.hasDisplayName() && meta.getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "Decorative Anvil")) {
                AnvilLocationManager.addDecorativeAnvilLocation(event.getBlockPlaced().getLocation());
                Debugger.sendDebugToPlayer(event.getPlayer(), "Decorative Anvil placed.");
            }

            /*
             * Block block = event.getBlockPlaced();
             * 
             * if (block.getState() instanceof TileState) {
             * TileState state = (TileState) block.getState();
             * state.getPersistentDataContainer().set(new NamespacedKey(plugin,
             * "isDecorative"), PersistentDataType.INTEGER, 1);
             * state.update();
             * event.getPlayer().sendMessage("Placed Decorative Anvil");
             * } else {
             * event.getPlayer().sendMessage(ChatColor.RED +
             * "event.getBlock().getState() is not instanceof TileState");
             * }
             */
        } else
            Debugger.sendDebugToPlayer(event.getPlayer(), "Placed block <" + item.getType() + ">.");
    }
}
