package com.tieman114.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GetWTraderSpawnEggCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            ItemStack wtSpawnEgg = new ItemStack(Material.WANDERING_TRADER_SPAWN_EGG);
            player.getInventory().addItem(wtSpawnEgg);
        }
        return true;
    }
}
