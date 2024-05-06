package com.tieman114.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import com.tieman114.items.Cocaine;
import com.tieman114.items.Weed;

import net.md_5.bungee.api.ChatColor;

public class ItemClickListener implements Listener{
    
    @SuppressWarnings("deprecation")
    @EventHandler
    public void onClick(final PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND || event.getAction() != Action.RIGHT_CLICK_AIR) {
            return;
        }

        Player player = event.getPlayer();
        ItemStack handheldItem = player.getItemInHand();

        if (handheldItem.hasItemMeta() && handheldItem.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Weed")) {
            player.sendMessage("R-Clicked on " + handheldItem.getItemMeta().getDisplayName());
            Weed.use(player, handheldItem);
        }
        if (handheldItem.hasItemMeta() && handheldItem.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Cocaine")) {
            player.sendMessage("R-Clicked on " + handheldItem.getItemMeta().getDisplayName());
            Cocaine.use(player, handheldItem);
        }

    }
}
