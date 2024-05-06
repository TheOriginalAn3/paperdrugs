package com.tieman114.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class MenuCommand implements CommandExecutor {
    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player player) {
            ItemStack weed = new ItemStack(Material.FERN);
            player.getInventory().addItem(weed);

            ItemMeta weedMeta = weed.getItemMeta();
            weedMeta.setDisplayName(ChatColor.GOLD + "Weed");
            weedMeta.addEnchant(Enchantment.DURABILITY, 1, true);

            weed.setItemMeta(weedMeta);
            player.getInventory().addItem(weed);
        }


        return true;
    }
}
