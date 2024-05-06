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

    private static ItemStack weed = null;
    private static ItemStack cocaine = null;
    private static ItemStack midnightMarks = null;

    public static void register(Plugin plugin) {
        // Weed
        createWeedItemStack();
        // ShapelessRecipe recipe0 = new ShapelessRecipe(new NamespacedKey(plugin, "WeedRecipe"), weed);
        // recipe0.addIngredient(3, Material.FERN);
        // Bukkit.addRecipe(recipe0);

        // Cocaine
        createCocaineItemStack();
        // ShapelessRecipe recipe1 = new ShapelessRecipe(new NamespacedKey(plugin, "CocaineRecipe"), cocaine);
        // recipe1.addIngredient(1, Material.FERN);
        // recipe1.addIngredient(3, Material.GUNPOWDER);
        // recipe1.addIngredient(3, Material.BROWN_MUSHROOM);
        // Bukkit.addRecipe(recipe1);

        // Midnight Marks
        createMidnightMarks();

    }

    @SuppressWarnings("deprecation")
    private static void createWeedItemStack() {
        if (weed == null) {
            weed = new ItemStack(Material.LARGE_FERN);
            ItemMeta weedMeta = weed.getItemMeta();
            weedMeta.setDisplayName(ChatColor.GOLD + "Weed");
            weedMeta.addEnchant(Enchantment.DURABILITY, 1, true);
            weedMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            weed.setItemMeta(weedMeta);
        }

    }

    @SuppressWarnings("deprecation")
    private static void createCocaineItemStack() {
        if (cocaine == null) {
            cocaine = new ItemStack(Material.SUGAR);
            ItemMeta cocaineMeta = cocaine.getItemMeta();
            cocaineMeta.setDisplayName(ChatColor.GOLD + "Cocaine");
            cocaineMeta.addEnchant(Enchantment.DURABILITY, 1, true);
            cocaineMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            cocaine.setItemMeta(cocaineMeta);
        }

    }

    @SuppressWarnings("deprecation")
    private static void createMidnightMarks() {
        if (midnightMarks == null) {
            midnightMarks = new ItemStack(Material.AMETHYST_SHARD);
            ItemMeta midnightMarksMeta = midnightMarks.getItemMeta();
            midnightMarksMeta.setDisplayName(ChatColor.DARK_PURPLE + "Midnight Marks");
            midnightMarksMeta.addEnchant(Enchantment.DURABILITY, 1, true);
            midnightMarksMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            midnightMarksMeta.setCustomModelData(100000);
            midnightMarks.setItemMeta(midnightMarksMeta);
        }

    }

    public static ItemStack getWeed() {
        if (weed != null) {
            return weed;
        } else
            throw new NullPointerException("Cannot return value of ItemStack weed because weed is NULL");
    }

    public static ItemStack getCocaine() {
        if (cocaine != null) {
            return cocaine;
        } else
            throw new NullPointerException("Cannot return value of ItemStack cocaine because cocaine is NULL");
    }

    public static ItemStack getMidnightMarks() {
        if (midnightMarks != null) {
            return midnightMarks;
        } else
            throw new NullPointerException("Cannot return value of ItemStack midnightMarks because midnightMarks is NULL");
    }

}
