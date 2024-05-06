package com.tieman114.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.md_5.bungee.api.ChatColor;

public class ShowDebugStatementsCommand implements CommandExecutor{
    

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                boolean enableDebug;
                try {
                    enableDebug = Boolean.parseBoolean(args[0]);
                } catch (Exception e) {
                    player.sendMessage(ChatColor.RED + "Invalid argument. Please use true or false");
                    return false;
                }
                Debugger.getDebugPreferences().put(player, enableDebug);
                Debugger.sendMessageToPlayer(player, ChatColor.GREEN + "Debug statements are now " + (enableDebug ? "enabled" : "disabled"));
            } else {
                Debugger.sendMessageToPlayer(player, ChatColor.RED + "Usage: /showDebugStatements <true/false>");
                return false;
            }
        } else {
            sender.sendMessage("This command can only be run by a player.");
            return false;
        }
        return true;
    }
}
