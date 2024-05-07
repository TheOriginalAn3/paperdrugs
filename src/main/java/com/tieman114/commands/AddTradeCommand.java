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
     * @param sender  the command sender
     * @param command the command that was executed
     * @param label   the command label
     * @param args    the command arguments
     * @return true if the command was executed successfully, false otherwise
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        Villager villager = findTargetVillager(player);
        if (villager == null) {
            Debugger.sendMessageToPlayer(player, "You are not looking at a villager.");
            return false;
        }

        if (!validateArguments(player, args)) {
            return false;
        }

        ItemStack tradeItemStack = createTradeItemStack(args[0], args[1]);
        if (tradeItemStack == null) {
            Material tradeItemMaterial = Material.matchMaterial(args[0].trim());
            if (tradeItemMaterial == null) {
                Debugger.sendMessageToPlayer(player, "Trade item not found in args[0]: " + args[0].trim());
                return false;
            }
            int tradeItemAmount = parseAmount(args[1]);
            if (tradeItemAmount < 1) {
                Debugger.sendDebugToPlayer(player,
                        "Trade item amount is 0 or couldn't be parsed in args[1]: " + args[1].trim());
                return false;
            }
            tradeItemStack = new ItemStack(tradeItemMaterial, tradeItemAmount);
        }

        ItemStack costItemStack = createCostItemStack(args[2], args[3]);
        if (costItemStack == null) {
            Material costItemMaterial = Material.matchMaterial(args[2].trim());
            if (costItemMaterial == null) {
                Debugger.sendMessageToPlayer(player, "Cost item not found in args[2]: " + args[2].trim());
                return false;
            }
            int costItemAmount = parseAmount(args[3]);
            if (costItemAmount < 1) {
                Debugger.sendDebugToPlayer(player,
                        "Cost item amount is 0 or couldn't be parsed in args[3]: " + args[3].trim());
                return false;
            }
            costItemStack = new ItemStack(costItemMaterial, costItemAmount);
        }

        ItemStack costItemStack2 = null;
        if (args.length == 6) {
            costItemStack2 = createCostItemStack(args[4], args[5]);
            if (costItemStack2 == null) {
                Material costItemMaterial2 = Material.matchMaterial(args[4].trim());
                if (costItemMaterial2 == null) {
                    Debugger.sendMessageToPlayer(player, "2. Cost item not found in args[4]: " + args[4].trim());
                    return false;
                }
                int costItemAmount2 = parseAmount(args[5]);
                if (costItemAmount2 < 1) {
                    Debugger.sendDebugToPlayer(player,
                            "2. Cost item amount is 0 or couldn't be parsed in args[5]: " + args[5].trim());
                    return false;
                }
                costItemStack2 = new ItemStack(costItemMaterial2, costItemAmount2);
            }
        } else {
            Debugger.sendDebugToPlayer(player, "No 2. cost item found.");
        }

        addTradeToVillager(villager, tradeItemStack, costItemStack, costItemStack2, player);

        return true;
    }

    /**
     * Finds the villager that the player is looking at.
     *
     * @param player the player that is looking at the villager
     * @return the villager that the player is looking at, or null if the player is
     *         not looking at a villager
     */
    private Villager findTargetVillager(Player player) {
        if (player.getTargetEntity(10) instanceof Villager) {
            return (Villager) player.getTargetEntity(10);
        }
        return null;
    }


    /**
     * Validates the command arguments.
     *
     * @param player the player that executed the command
     * @param args   the command arguments
     * @return true if the arguments are valid, false otherwise
     */
    private boolean validateArguments(Player player, String[] args) {
        if (args.length != 4 && args.length != 6) {
            Debugger.sendMessageToPlayer(player, "Invalid number of arguments.");
            return false;
        }
        return true;
    }

    /**
     * Creates an ItemStack for the trade item.
     *
     * @param material the material of the trade item
     * @param amount   the amount of the trade item
     * @return the ItemStack for the trade item, or null if the trade item is a
     *         custom item
     */
    private ItemStack createTradeItemStack(String material, String amount) {
        if (CustomRecipes.isCustomItem(material)) {
            return CustomRecipes.getCustomItem(material);
        }
        return null;
    }

    /**
     * Creates an ItemStack for the cost item.
     *
     * @param material the material of the cost item
     * @param amount   the amount of the cost item
     * @return the ItemStack for the cost item, or null if the cost item is a custom
     *         item
     */
    private ItemStack createCostItemStack(String material, String amount) {
        if (CustomRecipes.isCustomItem(material)) {
            return CustomRecipes.getCustomItem(material);
        }
        return null;
    }

    /**
     * Parses the amount of an item.
     *
     * @param amount the amount of the item
     * @return the parsed amount, or -1 if the amount couldn't be parsed
     */
    private int parseAmount(String amount) {
        try {
            return Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Adds a new trade to a villager.
     *
     * @param villager       the villager to add the trade to
     * @param tradeItemStack the ItemStack of the trade item
     * @param costItemStack  the ItemStack of the cost item
     * @param player         the player that executed the command
     */
    private void addTradeToVillager(Villager villager, ItemStack tradeItemStack, ItemStack costItemStack,
            ItemStack costItemStack2, Player player) {
        MerchantRecipe trade = new MerchantRecipe(tradeItemStack, Integer.MAX_VALUE);
        trade.addIngredient(costItemStack);
        if (costItemStack2 != null) {
            trade.addIngredient(costItemStack2);
        }
        List<MerchantRecipe> trades = new ArrayList<>(villager.getRecipes());
        trades.add(trade);
        villager.setRecipes(trades);
        Debugger.sendMessageToPlayer(player, ChatColor.GREEN + "Trade added successfully!");
    }

}