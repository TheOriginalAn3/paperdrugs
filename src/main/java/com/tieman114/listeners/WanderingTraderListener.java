package com.tieman114.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import com.tieman114.items.CustomRecipes;

public class WanderingTraderListener implements Listener {

    // private ItemStack air = null;

    @EventHandler
    public void onWanderingTraderSpawn(CreatureSpawnEvent event) {
        // Make sure to only run on wandering trader spawn
        if (!(event.getEntity() instanceof WanderingTrader)) {
            return;
        }

        WanderingTrader trader = (WanderingTrader)event.getEntity();
        List<MerchantRecipe> newTrades = new ArrayList<>(trader.getRecipes());

        // Empty "separator" trade
        MerchantRecipe midnightMarks = new MerchantRecipe(CustomRecipes.getMidnightMarks(), 0, 100, true);
        midnightMarks.addIngredient(new ItemStack(Material.EMERALD, 64));
        midnightMarks.shouldIgnoreDiscounts();
        newTrades.add(midnightMarks);

        // Weed Trade
        MerchantRecipe weed = new MerchantRecipe(CustomRecipes.getWeed().asQuantity(5), 7);
        weed.addIngredient(CustomRecipes.getMidnightMarks().asQuantity(5));
        newTrades.add(weed);

        // Cocaein Trade
        MerchantRecipe cocaine = new MerchantRecipe(CustomRecipes.getCocaine().asQuantity(2), 1);
        cocaine.addIngredient(CustomRecipes.getMidnightMarks().asQuantity(8));
        newTrades.add(cocaine);


        trader.setRecipes(newTrades);
    }

    // @SuppressWarnings("deprecation")
    // private void createEmptyItemStack() {
    //     if (air == null) {
    //         air = new ItemStack(Material.AIR);
    //         ItemMeta airMeta = air.getItemMeta();
    //         airMeta.setDisplayName(ChatColor.DARK_PURPLE + "NIGHT-MARKET");
    //         air.setItemMeta(airMeta);
    //     }
    // }

    // private ItemStack getEmptyItemStack(){
    //     if (air == null) {
    //         createEmptyItemStack();
    //     }
    //     return air;
    // }
}
