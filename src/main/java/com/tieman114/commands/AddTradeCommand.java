/**
 * This class represents the command executor for the "/addtrade" command.
 * It allows players to add new trades to a villager.
 * The command requires the player to be looking at a villager and provides arguments for the trade item and its cost.
 * The trade item can be a custom item or a normal item, and the cost can be a custom item or a normal item.
 * The command syntax is "/addtrade <tradeItemMaterial> <tradeItemAmount> <costMaterial> <costAmount> (<costMaterial2> <costAmount2>)".
 * If the command is executed successfully, a new trade is added to the villager's trades.
 */
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

import com.tieman114.items.CustomRecipes;

import net.md_5.bungee.api.ChatColor;

public class AddTradeCommand implements CommandExecutor {
    /**
     * Executes the command when it is triggered by a player.
     *
     * @param sender the command sender
     * @param command the command that was executed
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

        // Add a new trade to the villager
        // Check if the player has entered the correct amount of arguments
        if (args.length != 4 && args.length != 6) {
            Debugger.sendMessageToPlayer(player,
                    "Invalid amount of arguments. Please use /addtrade <tradeItemMaterial> <tradeItemAmmount> <costMaterial> <costAmmount> (<costMaterial2> <costAmmount2>)");
            return false;
        }

        // Check if tradeItemMaterial argument contains a custom item
        ItemStack tradeItemStack = null;
        switch (args[0].trim().toLowerCase()) {
            case "decorative_anvil":
                tradeItemStack = CustomRecipes.getDecorativeAnvil();
                tradeItemStack.setAmount(args[0].trim().isEmpty() ? 1 : Integer.parseInt(args[1].trim()));
                break;
            case "midnight_mark":
                tradeItemStack = CustomRecipes.getMidnightMarks();
                tradeItemStack.setAmount(args[0].trim().isEmpty() ? 1 : Integer.parseInt(args[1].trim()));
                break;
            case "midnight_marks":
                tradeItemStack = CustomRecipes.getMidnightMarks();
                tradeItemStack.setAmount(args[0].trim().isEmpty() ? 1 : Integer.parseInt(args[1].trim()));
                break;
            case "chemicals":
                tradeItemStack = CustomRecipes.getChemicals();
                tradeItemStack.setAmount(args[0].trim().isEmpty() ? 1 : Integer.parseInt(args[1].trim()));
                break;
            case "liquid_crystal_meth":
                tradeItemStack = CustomRecipes.getLiquidCrystalMeth();
                tradeItemStack.setAmount(args[0].trim().isEmpty() ? 1 : Integer.parseInt(args[1].trim()));
                break;
            case "crystal_meth":
                tradeItemStack = CustomRecipes.getCrystalMeth();
                tradeItemStack.setAmount(args[0].trim().isEmpty() ? 1 : Integer.parseInt(args[1].trim()));
                break;
            case "weed":
                tradeItemStack = CustomRecipes.getWeed();
                tradeItemStack.setAmount(args[0].trim().isEmpty() ? 1 : Integer.parseInt(args[1].trim()));
                break;
            case "cocaine":
                tradeItemStack = CustomRecipes.getCocaine();
                tradeItemStack.setAmount(args[0].trim().isEmpty() ? 1 : Integer.parseInt(args[1].trim()));
                break;
            default:
                Debugger.sendDebugToPlayer(player, "Custom Trade item not found in args[0].");
                break;
        }

        // Check if tradeItemMaterial argument contains a normal item
        Material tradeItemMaterial = null;
        if (tradeItemStack == null) {
            tradeItemMaterial = Material.matchMaterial(args[0].trim());
            if (tradeItemMaterial == null) {
                Debugger.sendMessageToPlayer(player, "Trade item not found in args[0]:" + args[0].trim());
                return false;
            }
        }

        // Check if tradeItemAmmount argument is a number and greater than 0
        int tradeItemAmmount = -1;
        if (tradeItemStack == null) {
            try {
                tradeItemAmmount = Integer.parseInt(args[1].trim());
                if (tradeItemAmmount < 1) {
                    Debugger.sendDebugToPlayer(player,
                            "Trade item ammount is 0 or couldn't be parsed in args[1]: " + args[1].trim());
                    return false;
                }
            } catch (Exception e) {
                Debugger.sendMessageToPlayer(player,
                        "Trade item ammount is 0 or couldn't be parsed in args[1]: " + args[1].trim());
                return false;
            }
        }

        // Create the tradeItemStack
        if (tradeItemStack == null) {
            tradeItemStack = new ItemStack(tradeItemMaterial, tradeItemAmmount);
        }

        // Check if costMaterial argument contains a custom item
        ItemStack costItemStack = null;
        switch (args[2].trim().toLowerCase()) {
            case "decorative_anvil":
                costItemStack = CustomRecipes.getDecorativeAnvil();
                costItemStack.setAmount(args[2].trim().isEmpty() ? 1 : Integer.parseInt(args[3].trim()));
                break;
            case "midnight_mark":
                costItemStack = CustomRecipes.getMidnightMarks();
                costItemStack.setAmount(args[2].trim().isEmpty() ? 1 : Integer.parseInt(args[3].trim()));
                break;
            case "midnight_marks":
                costItemStack = CustomRecipes.getMidnightMarks();
                costItemStack.setAmount(args[2].trim().isEmpty() ? 1 : Integer.parseInt(args[3].trim()));
                break;
            case "chemicals":
                costItemStack = CustomRecipes.getChemicals();
                costItemStack.setAmount(args[2].trim().isEmpty() ? 1 : Integer.parseInt(args[3].trim()));
                break;
            case "liquid_crystal_meth":
                costItemStack = CustomRecipes.getLiquidCrystalMeth();
                costItemStack.setAmount(args[2].trim().isEmpty() ? 1 : Integer.parseInt(args[3].trim()));
                break;
            case "crystal_meth":
                costItemStack = CustomRecipes.getCrystalMeth();
                costItemStack.setAmount(args[2].trim().isEmpty() ? 1 : Integer.parseInt(args[3].trim()));
                break;
            case "weed":
                costItemStack = CustomRecipes.getWeed();
                costItemStack.setAmount(args[2].trim().isEmpty() ? 1 : Integer.parseInt(args[3].trim()));
                break;
            case "cocaine":
                costItemStack = CustomRecipes.getCocaine();
                costItemStack.setAmount(args[2].trim().isEmpty() ? 1 : Integer.parseInt(args[3].trim()));
                break;
            default:
                Debugger.sendDebugToPlayer(player, "Custom Cost item not found in args[2].");
                break;
        }

        // Check if costMaterial argument contains a normal item
        Material costItem = null;
        if (costItemStack == null) {
            costItem = Material.matchMaterial(args[2].trim());
            if (costItem == null) {
                Debugger.sendMessageToPlayer(player, "Cost item not found in args[2]: " + args[2].trim());
                return false;
            }
        }

        // Check if costAmmount argument is a number and greater than 0
        int costAmmount = -1;
        if (costItemStack == null) {
            try {
                costAmmount = Integer.parseInt(args[3].trim());
                if (costAmmount < 1) {
                    Debugger.sendDebugToPlayer(player,
                            "Cost item ammount is 0 or could't be parsed in args[3]: " + args[3].trim());
                    return false;
                }
            } catch (Exception e) {
                Debugger.sendMessageToPlayer(player,
                        "Cost item ammount is 0 or could't be parsed in args[3]: " + args[3].trim());
                return false;
            }
        }

        // Create the costItemStack
        if (costItemStack == null) {
            costItemStack = new ItemStack(costItem, costAmmount);
        }

        // Check if there is a second cost Item
        ItemStack costItemStack2 = null;
        Material costMaterial2 = null;
        int costAmmount2 = -1;
        if (args.length == 6) {
            // Check if costMaterial2 argument contains a custom item
            switch (args[4].trim().toLowerCase()) {
                case "decorative_anvil":
                    costItemStack2 = CustomRecipes.getDecorativeAnvil();
                    costItemStack2.setAmount(args[4].trim().isEmpty() ? 1 : Integer.parseInt(args[5].trim()));
                    break;
                case "midnight_mark":
                    costItemStack2 = CustomRecipes.getMidnightMarks();
                    costItemStack2.setAmount(args[4].trim().isEmpty() ? 1 : Integer.parseInt(args[5].trim()));
                    break;
                case "midnight_marks":
                    costItemStack2 = CustomRecipes.getMidnightMarks();
                    costItemStack2.setAmount(args[4].trim().isEmpty() ? 1 : Integer.parseInt(args[5].trim()));
                    break;
                case "chemicals":
                    costItemStack2 = CustomRecipes.getChemicals();
                    costItemStack2.setAmount(args[4].trim().isEmpty() ? 1 : Integer.parseInt(args[5].trim()));
                    break;
                case "liquid_crystal_meth":
                    costItemStack2 = CustomRecipes.getLiquidCrystalMeth();
                    costItemStack2.setAmount(args[4].trim().isEmpty() ? 1 : Integer.parseInt(args[5].trim()));
                    break;
                case "crystal_meth":
                    costItemStack2 = CustomRecipes.getCrystalMeth();
                    costItemStack2.setAmount(args[4].trim().isEmpty() ? 1 : Integer.parseInt(args[5].trim()));
                    break;
                case "weed":
                    costItemStack2 = CustomRecipes.getWeed();
                    costItemStack2.setAmount(args[4].trim().isEmpty() ? 1 : Integer.parseInt(args[5].trim()));
                    break;
                case "cocaine":
                    costItemStack2 = CustomRecipes.getCocaine();
                    costItemStack2.setAmount(args[4].trim().isEmpty() ? 1 : Integer.parseInt(args[5].trim()));
                    break;
                default:
                    Debugger.sendDebugToPlayer(player, "Custom 2. Cost item not found in args[4].");
                    break;
            }

            // Check if costMaterial2 argument contains a normal item
            if (costItemStack2 == null) {
                costMaterial2 = Material.matchMaterial(args[4].trim());
                if (costMaterial2 == null) {
                    Debugger.sendMessageToPlayer(player, "2. Cost item not found in args[4]: " + args[4].trim());
                    return false;
                }
            }

            // Check if costAmmount2 argument is a number
            if (costItemStack2 == null) {
                try {
                    costAmmount2 = Integer.parseInt(args[5].trim());
                    if (costAmmount2 < 1) {
                        Debugger.sendDebugToPlayer(player,
                                "2. Cost item ammount is 0 or could't be parsed in args[5]: " + args[5].trim());
                        return false;
                    }
                } catch (Exception e) {
                    Debugger.sendMessageToPlayer(player,
                            "2. Cost item ammount is 0 or could't be parsed in args[5]: " + args[5].trim());
                    return false;
                }
            }

            // Create the costItemStack2
            if (costItemStack2 == null) {
                costItemStack2 = new ItemStack(costMaterial2, costAmmount2);
            }
        } else {
            Debugger.sendDebugToPlayer(player, "No 2. cost item found.");
        }

        MerchantRecipe newTrade = new MerchantRecipe(tradeItemStack, 9999999);
        newTrade.addIngredient(costItemStack);
        if (costMaterial2 != null) {
            newTrade.addIngredient(costItemStack2);
        }

        trades.add(newTrade);
        villager.setRecipes(trades);

        // Return a message to the player
        Debugger.sendMessageToPlayer(player,
                "Trade " + ChatColor.GREEN + "added to villager " + villager.getName() + ": " + ChatColor.GRAY
                        + newTrade.getIngredients().toString() + " -> " + newTrade.getResult().toString());
        return true;
    }

}
