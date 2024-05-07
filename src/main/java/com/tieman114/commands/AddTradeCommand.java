package com.tieman114.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

public class AddTradeCommand implements CommandExecutor {
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
            // Return a message to the player
            Debugger.sendMessageToPlayer(player, "You are not looking at a villager.");
            return false;
        }

        // Get the trades of that villager
        List<MerchantRecipe> trades = new ArrayList<>(villager.getRecipes());

        // Add a new trade to the villager
        Material tradeItemMaterial = Material.matchMaterial(args[0].trim());
        if (tradeItemMaterial == null) {
            Debugger.sendMessageToPlayer(player, "Trade item not found -> args[0]:" + args[0].trim());
            return false;
        }
        int tradeItemAmmount = Integer.parseInt(args[1].trim());
        if (tradeItemAmmount == 0) {
            Debugger.sendMessageToPlayer(player, "Trade item ammount is 0 or couldn't be parsed -> Args[1]: " + args[1].trim());
            return false;
        }
        Material costMaterial = Material.matchMaterial(args[2].trim());
        if (costMaterial == null) {
            Debugger.sendMessageToPlayer(player, "Cost item not found -> args[2]: " + args[2].trim());
            return false;
        }

        int costAmmount = Integer.parseInt(args[3].trim());
        if (costAmmount == 0) {
            Debugger.sendMessageToPlayer(player, "Cost item ammount is 0 or could't be parsed -> args[3]: " + args[3].trim());
        }

        // Check if there is a second cost Item
        Material costMaterial2 = null;
        int costAmmount2 = 0;
        ItemStack costItemStack2 = null;
        if (args.length == 6) {
            costMaterial2 = Material.matchMaterial(args[4].trim());
            costAmmount2 = Integer.parseInt(args[5].trim());
            costItemStack2 = new ItemStack(costMaterial2, costAmmount2);
        } else Debugger.sendDebugToPlayer(player, "No 2. cost item found.");

        ItemStack tradeItemStack = new ItemStack(tradeItemMaterial, tradeItemAmmount);
        ItemStack costItemStack = new ItemStack(costMaterial, costAmmount);

        MerchantRecipe newTrade = new MerchantRecipe(tradeItemStack, 1);
        newTrade.addIngredient(costItemStack);
        if (costMaterial2 != null) {
            newTrade.addIngredient(costItemStack2);
        }

        trades.add(newTrade);
        villager.setRecipes(trades);

        // Return a message to the player
        Debugger.sendMessageToPlayer(player, "Trade added to villager: " + newTrade.getIngredients().toString() + " -> " + newTrade.getResult().toString());
        return true;
    }

}
