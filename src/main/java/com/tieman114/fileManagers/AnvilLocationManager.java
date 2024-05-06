package com.tieman114.fileManagers;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.tieman114.PaperDrugs;

public class AnvilLocationManager {
    private static JavaPlugin plugin = PaperDrugs.getProvidingPlugin(PaperDrugs.class);
    private static File configFile = new File(plugin.getDataFolder(), "decorative_anvils.yml");
    private static YamlConfiguration configuration = YamlConfiguration.loadConfiguration(configFile);
    private static Set<Location> decorativeAnvilLocations = new HashSet<>();

    public AnvilLocationManager() {
        loadLocations();
    }

    public static void saveLocations() {
        List<String> locationStrings = decorativeAnvilLocations.stream()
                .map(loc -> loc.getWorld().getName() + "," + loc.getX() + "," + loc.getY() + "," + loc.getZ())
                .collect(Collectors.toList());
        configuration.set("locations", locationStrings);
        try {
            configuration.save(configFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save Decorative Anvils: " + e.getMessage());
        }
    }

    public static void loadLocations() {
        List<String> locationStrings = configuration.getStringList("locations");
        // decorativeAnvilLocations.clear();
        if (!(decorativeAnvilLocations.isEmpty())) {
            plugin.getLogger().warning("DecorativeAnvilLocations is empty");
        } else {
            for (String locString : locationStrings) {
                String[] parts = locString.split(",");
                Location loc = new Location(plugin.getServer().getWorld(parts[0]), Double.parseDouble(parts[1]),
                Double.parseDouble(parts[2]), Double.parseDouble(parts[3]));
                decorativeAnvilLocations.add(loc);
            }
        }
    }

    public static Set<Location> getDecorativeAnvilLocations() {
        return decorativeAnvilLocations;
    }

    public static void setDecorativeAnvilLocations(Set<Location> decorativeAnvilLocations) {
        AnvilLocationManager.decorativeAnvilLocations = decorativeAnvilLocations;
    }

    public static synchronized void addDecorativeAnvilLocation(Location location) {
        decorativeAnvilLocations.add(location);
        saveLocations();
    }

    public static synchronized void removeDecorativeAnvilLocation(Location location) {
        decorativeAnvilLocations.remove(location);
        saveLocations();
    }
}
