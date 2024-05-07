package com.tieman114.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.MerchantRecipe;

import net.md_5.bungee.api.ChatColor;

/**
 * A command executor for removing a trade from a villager.
 */
public class RemoveTradeCommand implements CommandExecutor{
    
    /**
     * Executes the remove trade command.
     * 
     * @param sender the command sender
     * @param command the command
     * @param label the command label
     * @param args the command arguments
     * @return true if the command was executed successfully, false otherwise
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        // Find out what villager the player is looking at
        // Check if the player is looking at a villager
        Villager villager = null;
        if (player.getTargetEntity(10) instanceof Villager) {
            villager = (Villager) player.getTargetEntity(10);
        } else {
            Debugger.sendMessageToPlayer(player, "You are not looking at a villager.");
            return false;
        }

        // Get the trades of that villager
        List<MerchantRecipe> trades = new ArrayList<>(villager.getRecipes());

        // Remove a trade from the villager
        int tradeNr = Integer.parseInt(args[0].trim())+1;
        if (tradeNr < 1 || tradeNr > trades.size()+1) {
            Debugger.sendMessageToPlayer(player, "Trade index out of bounds -> args[0]: " + args[0].trim());
            return false;
        }

        // Save the trade to remove
        MerchantRecipe tradeToRemove = trades.get(tradeNr-2);

        // Remove the trade
        trades.remove(tradeNr-2);

        // Check if the trade was removed
        if (trades.contains(tradeToRemove)) {
            Debugger.sendMessageToPlayer(player, "Trade was not removed.");
            return false;
        }

        // Set the new trades
        villager.setRecipes(trades);

        // Send a message to the player
        Debugger.sendMessageToPlayer(player,  "Trade " + ChatColor.RED + "removed from villager " + villager.getName() + ": "  + ChatColor.GRAY + tradeToRemove.getIngredients().toString() + " -> " + tradeToRemove.getResult().toString());
        return true;
    }
}
