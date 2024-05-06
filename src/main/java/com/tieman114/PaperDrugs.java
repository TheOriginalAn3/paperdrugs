package com.tieman114;

import org.bukkit.plugin.java.JavaPlugin;

public class PaperDrugs extends JavaPlugin{

    @Override
    public void onEnable() {
        getLogger().info("PaperDrugs has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("PaperDrugs has been disabled!");
    }
}