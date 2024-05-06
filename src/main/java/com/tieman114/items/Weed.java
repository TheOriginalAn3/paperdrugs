package com.tieman114.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.md_5.bungee.api.ChatColor;

public class Weed {
    private static final String CHAT_MESSAGE = "You start to feel the effects of the smoke...";

    @SuppressWarnings("deprecation")
    public static void use(Player player, ItemStack handheldItem) {
        player.sendMessage(ChatColor.DARK_PURPLE + CHAT_MESSAGE);
        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 1200, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1200, 0));

        int ammount = handheldItem.getAmount();
        if (ammount > 1) {
            handheldItem.setAmount(ammount - 1);
        } else if (ammount == 1) {
            player.setItemInHand(new ItemStack(Material.AIR));
        }
        
        player.updateInventory();
    }

}
