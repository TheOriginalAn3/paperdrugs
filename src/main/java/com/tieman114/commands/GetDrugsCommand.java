package com.tieman114.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.tieman114.items.CustomRecipes;

public class GetDrugsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player player) {
            // player.getInventory().addItem(weed);
            player.getInventory().addItem(CustomRecipes.getWeed().asQuantity(16));
            player.getInventory().addItem(CustomRecipes.getCocaine().asQuantity(16));
            player.getInventory().addItem(CustomRecipes.getMidnightMarks().asQuantity(16));

        }

        return true;
    }
}
