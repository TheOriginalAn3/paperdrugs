package com.tieman114.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Class to handle debug messages and error messages for the plugin.
 */
@SuppressWarnings("deprecation")
public class Debugger {
    /**
     * Map to store the debug preferences for each player.
     * Key: Player object
     * Value: Boolean indicating whether debug mode is enabled for the player
     */
    public static Map<Player, Boolean> debugPreferences = new HashMap<Player, Boolean>();

    /**
     * Map to store the error preferences for each player.
     * Key: Player object
     * Value: Boolean indicating whether error messages are enabled for the player
     */
    public static Map<Player, Boolean> errorPreferences = new HashMap<Player, Boolean>();

    /**
     * Sends a debug message to the specified player if debug mode is enabled for the player.
     * 
     * @param player  The player to send the debug message to
     * @param message The debug message to send
     */
    public static void sendDebugToPlayer(Player player, String message) {
        if (debugPreferences.getOrDefault(player, false)) {
            player.sendMessage(ChatColor.RED + "DEBUG: " + ChatColor.GRAY + message);
        }
    }

    /**
     * Sends a general message to the specified player.
     * 
     * @param player  The player to send the message to
     * @param message The message to send
     */
    public static void sendMessageToPlayer(Player player, String message) {
        player.sendMessage(ChatColor.GRAY + message);
    }

    /**
     * Sends an error message to the specified player if error messages are enabled for the player.
     * 
     * @param player The player to send the error message to
     * @param error  The error message to send
     */
    public static void sendErrorToPlayer(Player player, String error) {
        if (errorPreferences.getOrDefault(player, false)) {
            player.sendMessage(ChatColor.RED + "ERROR: " + ChatColor.GRAY + error);
        }
    }
}
