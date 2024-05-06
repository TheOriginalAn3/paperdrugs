package com.tieman114.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Debugger {
    private static Map<Player, Boolean> debugPreferences = new HashMap<>();

    public static void sendMessageToPlayer(Player p, String m) {
        p.sendMessage(m);
    }

    public static void sendDebugToPlayer(Player p, String m) {
        if (shouldShowDebug(p)) {
            p.sendMessage(ChatColor.RED + "Debug: " + ChatColor.GRAY + m);
        }
    }


    public static boolean shouldShowDebug(Player player) {
        return debugPreferences.getOrDefault(player, false);
    }

    public static Map<Player, Boolean> getDebugPreferences() {
        return debugPreferences;
    }

    public static Boolean getDebugPreferences(Player p) {
        return debugPreferences.get(p);
    }
}
