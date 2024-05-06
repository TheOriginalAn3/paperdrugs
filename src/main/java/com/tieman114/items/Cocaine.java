package com.tieman114.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.sound.Sound.Type;
import net.md_5.bungee.api.ChatColor;

public class Cocaine {
    private static final String CHAT_MESSAGE = "You feel a sudden kick...";

    @SuppressWarnings("deprecation")
    public static void use(Player player, ItemStack handheldItem) {
        player.sendMessage(ChatColor.DARK_PURPLE + CHAT_MESSAGE);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1200, 4));
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1200, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1200, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 1200, 4));
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1200, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1200, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 300, 1));

        int ammount = handheldItem.getAmount();
        if (ammount > 1) {
            handheldItem.setAmount(ammount - 1);
        } else if (ammount == 1) {
            player.setItemInHand(new ItemStack(Material.AIR));
        }
        
        player.updateInventory();
    }

}
