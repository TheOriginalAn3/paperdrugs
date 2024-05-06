package com.tieman114;

import org.bukkit.plugin.java.JavaPlugin;

import com.tieman114.commands.MenuCommand;
import com.tieman114.items.CustomRecipes;
import com.tieman114.listeners.InventoryListener;
import com.tieman114.listeners.ItemClickListener;

public class PaperDrugs extends JavaPlugin{

    @Override
    public void onEnable() {
        getLogger().info("PaperDrugs has been enabled!");
        getServer().getPluginManager().registerEvents(new ItemClickListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);

        getCommand("menu").setExecutor(new MenuCommand());
        //getCommand("weed").setExecutor(new WeedCommand());

        //PaperDrugsSettings.getInstance().load();
        CustomRecipes.register(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("PaperDrugs has been disabled!");
    }
}