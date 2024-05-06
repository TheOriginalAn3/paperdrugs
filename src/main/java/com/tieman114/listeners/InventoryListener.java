package com.tieman114.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.ItemMeta;

@SuppressWarnings("deprecation")
public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){

        if (e.getWhoClicked() instanceof Player player){

            var clickedItem = e.getCurrentItem();

            if (clickedItem.getType() == Material.FLOWERING_AZALEA){
                player.sendMessage("You clicked the flower!");

                if (e.isRightClick()){
                    clickedItem.setType(Material.DIAMOND);
                    ItemMeta diamondMeta = clickedItem.getItemMeta();
                    diamondMeta.setDisplayName(ChatColor.AQUA + "DIAMOND");
                    clickedItem.setItemMeta(diamondMeta);
                }

            }else if(clickedItem.getType() == Material.BEEF){
                player.sendMessage("You clicked the beef!");
            }else{
                player.sendMessage("You clicked something else.");
                player.sendMessage(clickedItem.getType().toString());
            }

        }

    }

}