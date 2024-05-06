package com.tieman114.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;

import com.tieman114.commands.Debugger;
import com.tieman114.fileManagers.AnvilLocationManager;
import com.tieman114.items.CustomRecipes;

public class BlockBreakListener implements Listener {
    // private JavaPlugin plugin;
    // public BlockPlaceListener(JavaPlugin plugin) {
    // this.plugin = plugin;
    // }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Debugger.sendDebugToPlayer(event.getPlayer(), "Broken Block <" + block.getType() + ">.");
        if ((block.getType() == Material.ANVIL) && AnvilLocationManager.getDecorativeAnvilLocations().contains(block.getLocation())) {
            // Prevent normal drops
            event.setDropItems(false);

            // Remove from tracked locations
            AnvilLocationManager.removeDecorativeAnvilLocation(block.getLocation());

            // Drop custom item
            block.getWorld().dropItemNaturally(block.getLocation(), CustomRecipes.getDecorativeAnvil());

            Debugger.sendDebugToPlayer(event.getPlayer(), "Broken Block <Decorative Anvil>.");
        } else Debugger.sendDebugToPlayer(event.getPlayer(), "Broken Block <" + block.getType() + ">.");

    }

    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent event) {
        if (event.getEntity() instanceof FallingBlock) {
            FallingBlock fallingBlock = (FallingBlock) event.getEntity();
            Block block = event.getBlock();

            // Check if custom anvil
            if ((fallingBlock.getBlockData().getMaterial() == Material.ANVIL) && AnvilLocationManager.getDecorativeAnvilLocations().contains(block.getLocation())) {
                // Prevent the block from changing (falling) (breaking or becoming a block)
                event.setCancelled(true);
            }
        }
    }
}
