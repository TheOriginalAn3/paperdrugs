package com.tieman114;

import org.bukkit.plugin.java.JavaPlugin;

import com.tieman114.commands.GetDrugsCommand;
import com.tieman114.commands.GetWTraderSpawnEggCommand;
import com.tieman114.items.CustomRecipes;
import com.tieman114.listeners.InventoryListener;
import com.tieman114.listeners.ItemClickListener;
import com.tieman114.listeners.WanderingTraderListener;

public class PaperDrugs extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getLogger().info("PaperDrugs enabled!");

        getServer().getPluginManager().registerEvents(new ItemClickListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        
        registerCommands();
        
        //PaperDrugsSettings.getInstance().load();
        CustomRecipes.register(this);
        getServer().getPluginManager().registerEvents(new WanderingTraderListener(), this);
    }
    // @EventHandler
    // public void onDisable() {
    //     Bukkit.getServer().getLogger().info("PaperDrugs disabled");
    // }

    void registerCommands() {
        getCommand("getWTEgg").setExecutor(new GetWTraderSpawnEggCommand());
        getCommand("getDrugs").setExecutor(new GetDrugsCommand());
        //getCommand("weed").setExecutor(new WeedCommand());
    }
}