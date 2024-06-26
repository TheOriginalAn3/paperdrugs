package com.tieman114;

import org.bukkit.plugin.java.JavaPlugin;

import com.tieman114.commands.AddTradeCommand;
import com.tieman114.commands.ChangeVillagerYawCommand;
import com.tieman114.commands.GetDrugsCommand;
import com.tieman114.commands.GetWTraderSpawnEggCommand;
import com.tieman114.commands.ListDecorativeAnvilsCommand;
import com.tieman114.commands.RemoveTradeCommand;
import com.tieman114.commands.ShowDebugStatementsCommand;
import com.tieman114.commands.SpawnCustomVillagerCommand;
import com.tieman114.fileManagers.AnvilLocationManager;
import com.tieman114.items.CustomRecipes;
import com.tieman114.listeners.BlockBreakListener;
import com.tieman114.listeners.BlockInteractListener;
import com.tieman114.listeners.BlockPlaceListener;
import com.tieman114.listeners.ItemClickListener;
import com.tieman114.listeners.WanderingTraderListener;

public class PaperDrugs extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getLogger().info("PaperDrugs enabled!");

        AnvilLocationManager.loadLocations();

        getServer().getPluginManager().registerEvents(new ItemClickListener(), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
        getServer().getPluginManager().registerEvents(new BlockInteractListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        // getServer().getPluginManager().registerEvents(new InventoryListener(), this);

        registerCommands();

        CustomRecipes.register(this);
        getServer().getPluginManager().registerEvents(new WanderingTraderListener(), this);
    }

    @Override
    public void onDisable() {
        AnvilLocationManager.saveLocations();
    }

    void registerCommands() {
        getCommand("paperdrugs.getwtegg").setExecutor(new GetWTraderSpawnEggCommand());
        getCommand("paperdrugs.getdrugs").setExecutor(new GetDrugsCommand());
        getCommand("paperdrugs.showdebugstatements").setExecutor(new ShowDebugStatementsCommand());
        getCommand("paperdrugs.listdecorativeanvils").setExecutor(new ListDecorativeAnvilsCommand());
        getCommand("paperdrugs.spawncustomvillager").setExecutor(new SpawnCustomVillagerCommand());
        getCommand("paperdrugs.changevillageryaw").setExecutor(new ChangeVillagerYawCommand());
        getCommand("paperdrugs.addtrade").setExecutor(new AddTradeCommand());
        getCommand("paperdrugs.removetrade").setExecutor(new RemoveTradeCommand());
    }
}