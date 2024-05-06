package com.tieman114.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;


import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class SpawnCustomVillagerCommand implements CommandExecutor {
    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length < 5) {
            if (sender instanceof Player) {
                Debugger.sendMessageToPlayer(player, "Args length < 5. " +
                        "Usage: /spawnvillager <name> <x> <y> <z> {[item1, item1Ammount]:[costItem1, costItem1Ammount]}...");
                return true;
            } else
                sender.sendMessage("Args length < 5. "
                        + "Usage: /spawnvillager <name> <x> <y> <z> {[item1, item1Ammount]:[costItem1, costItem1Ammount]}...");
        }
        try {
            String name = args[0];
            double x = Double.parseDouble(args[1]);
            double y = Double.parseDouble(args[2]);
            double z = Double.parseDouble(args[3]);
            World world = player.getWorld();

            if (world == null) {
                Debugger.sendMessageToPlayer(player, "Current World not found");
                return true;
            }

            Location location = new Location(world, x, y, z);

            List<MerchantRecipe> recipes = new ArrayList<>();
            for (int i = 4; i < args.length; i++) {
                String[] parts = args[i].replaceAll("[{}\\[\\] ]", "").split(":");
                String[] itemPart = parts[0].split(",");
                String[] costPart = parts[1].split(",");

                Material tradeItemMaterial = Material.matchMaterial(itemPart[0].trim());
                // Debugger.sendDebugToPlayer(player, "TradeItem: " + tradeItemMaterial.toString());
                int tradeItemAmmount = Integer.parseInt(itemPart[1].trim());
                // Debugger.sendDebugToPlayer(player, "TradeItemAmmount: " + tradeItemAmmount);
                Material costMaterial = Material.matchMaterial(costPart[0].trim());
                // Debugger.sendDebugToPlayer(player, "CostItem: "+costMaterial.toString());
                int costAmmount = Integer.parseInt(costPart[1].trim());
                // Debugger.sendDebugToPlayer(player, "CostItemAmmount: " + costAmmount);

                ItemStack tradeItem = new ItemStack(tradeItemMaterial, tradeItemAmmount);
                ItemStack costItem = new ItemStack(costMaterial, costAmmount);
                MerchantRecipe recipe = new MerchantRecipe(tradeItem, 99999);
                recipe.addIngredient(costItem);
                recipes.add(recipe);
                Debugger.sendDebugToPlayer(player, "Added trade: " + recipe.toString());
            }

            Villager villager = (Villager) world.spawnEntity(location, EntityType.VILLAGER);
            villager.setAI(false);
            villager.setCustomName(name);
            villager.setCustomNameVisible(true);
            villager.setRecipes(recipes);

            sender.sendMessage("Spawned Villager:\"" + name + "\" at X:" + x + " Y:" + y + " Z:" + z);
        } catch (NumberFormatException e) {
            sender.sendMessage("Invalid number format!");
        } catch (Exception e) {
            if (sender instanceof Player) {
                Debugger.sendMessageToPlayer(player,
                        ChatColor.RED + "Error processing the command. Check console log or enable debugStatements.");
                if (Debugger.shouldShowDebug(player)) {
                    Debugger.sendDebugToPlayer(player, e.getMessage());
                }
            } else
                sender.sendMessage("ERROR processing the command. Exception: " + e.getMessage());
        }

        return true;
    }

}
