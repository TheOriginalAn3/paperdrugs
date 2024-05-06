package com.tieman114.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class ChangeVillagerYawCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be run by a player.");
            return true;
        }
        
        Player player = (Player) sender;
        if (args.length != 1) {
            Debugger.sendMessageToPlayer(player, "Usage: /editcustomvillager.changelookdirection <yaw>");
            return true;
        }

        float yaw;
        try {
            yaw = Float.parseFloat(args[0]);
        } catch (NumberFormatException e) {
            Debugger.sendMessageToPlayer(player, "Invalid yaw value. Please enter a valid number.");
            return true;
        }

        Entity target = getNearestEntityInSight(player, 50);  // Scan up to 50 blocks

        if (target instanceof Villager) {
            Villager villager = (Villager) target;
            // Set villager's rotation to the specified yaw
            Location newLocation = villager.getLocation().clone();
            newLocation.setYaw(yaw);
            newLocation.setPitch(0); // You can also allow setting the pitch or just reset to 0
            villager.teleport(newLocation);
            Debugger.sendMessageToPlayer(player, "Villager changed to face yaw: " + yaw);
        } else {
            Debugger.sendMessageToPlayer(player, "You are not looking at a villager.");
        }

        return true;
    }

    private Entity getNearestEntityInSight(Player player, int range) {
        List<Entity> nearbyEntities = player.getNearbyEntities(range, range, range);
        ArrayList<Block> sightBlock = (ArrayList<Block>) player.getLineOfSight(null, range);
        List<Location> sight = sightBlock.stream().map(Block::getLocation).collect(Collectors.toList());

        for (Location location : sight) {
            for (Entity entity : nearbyEntities) {
                if (entity.getLocation().distance(location) < 1.5) {
                    return entity;
                }
            }
        }
        return null;
    }
}
