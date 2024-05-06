package com.tieman114.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class CustomRecipes {
    @SuppressWarnings("deprecation")
    public static void register(Plugin plugin) {
        // Weed
        ItemStack weed = new ItemStack(Material.LARGE_FERN);
        ItemMeta weedMeta = weed.getItemMeta();
        weedMeta.setDisplayName(ChatColor.GOLD + "Weed");
        weedMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        weedMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        weed.setItemMeta(weedMeta);

        ShapelessRecipe recipe0 = new ShapelessRecipe(new NamespacedKey(plugin, "WeedRecipe"), weed);
        recipe0.addIngredient(3, Material.FERN);
        Bukkit.addRecipe(recipe0);
        
        // Cocaine
        ItemStack cocaine = new ItemStack(Material.SUGAR);
        ItemMeta cocaineMeta = cocaine.getItemMeta();
        cocaineMeta.setDisplayName(ChatColor.GOLD + "Cocaine");
        cocaineMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        cocaineMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        cocaine.setItemMeta(cocaineMeta);

        ShapelessRecipe recipe1 = new ShapelessRecipe(new NamespacedKey(plugin, "CocaineRecipe"), cocaine);
        recipe1.addIngredient(1, Material.FERN);
        recipe1.addIngredient(3, Material.GUNPOWDER);
        recipe1.addIngredient(3, Material.BROWN_MUSHROOM);
        Bukkit.addRecipe(recipe1);

    }
}
