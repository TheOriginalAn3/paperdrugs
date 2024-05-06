package com.tieman114.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tieman114.fileManagers.AnvilLocationManager;

public class ListDecorativeAnvilsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            for (Location location : AnvilLocationManager.getDecorativeAnvilLocations()) {
                Debugger.sendMessageToPlayer(player, location.toString());
            }
        } else
            sender.sendMessage("label");
        return true;
    }
}
