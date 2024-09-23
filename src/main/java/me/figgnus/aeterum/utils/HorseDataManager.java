package me.figgnus.aeterum.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class HorseDataManager {
    private final File file;
    private FileConfiguration config;
    private final Map<UUID, Map<Integer, HorseData>> playerHorses;

    public HorseDataManager(File dataFolder) {
        this.file = new File(dataFolder, "horses.yml");
        this.playerHorses = new HashMap<>();

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Load the config
        this.config = YamlConfiguration.loadConfiguration(file);
    }
    public void loadHorses() {
        for (String playerKey : config.getKeys(false)) {
            UUID playerUUID = UUID.fromString(playerKey);
            Map<Integer, HorseData> horses = new HashMap<>();
            for (String customModelDataKey : config.getConfigurationSection(playerKey).getKeys(false)) {
                int customModelData = Integer.parseInt(customModelDataKey);
                String basePath = playerKey + "." + customModelDataKey;
                UUID horseUUID = UUID.fromString(config.getString(basePath + ".horseUUID"));
                String horseWorld = config.getString(basePath + ".horseWorld");
                Location horseLocation = config.getLocation(basePath + ".horseLocation");
                boolean horseAlive = config.getBoolean(basePath + ".horseAlive", true);
                horses.put(customModelData, new HorseData(horseUUID, horseWorld, horseLocation, horseAlive));
            }
            playerHorses.put(playerUUID, horses);
        }
    }

    public void saveHorses() {
        for (UUID playerUUID : playerHorses.keySet()) {
            Map<Integer, HorseData> horses = playerHorses.get(playerUUID);
            for (Integer customModelData : horses.keySet()) {
                HorseData horseData = horses.get(customModelData);
                String basePath = playerUUID.toString() + "." + customModelData;
                config.set(basePath + ".horseUUID", horseData.getHorseUUID().toString());
                config.set(basePath + ".horseWorld", horseData.getHorseWorld());
                config.set(basePath + ".horseLocation", horseData.getHorseLocation());
                config.set(basePath + ".horseAlive", horseData.isHorseAlive());  // Save alive status
            }
        }
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean isHorseAlive(UUID playerUUID, int customModelData) {
        HorseData horseData = getHorseData(playerUUID, customModelData);
        return horseData != null && horseData.isHorseAlive();
    }
    public void setHorseAlive(UUID playerUUID, int customModelData, boolean isAlive) {
        HorseData horseData = getHorseData(playerUUID, customModelData);
        if (horseData != null) {
            horseData.setHorseAlive(isAlive);
        }
    }
    public Set<UUID> getAllPlayerUUIDs() {
        return playerHorses.keySet();
    }

    public Map<Integer, HorseData> getPlayerHorses(UUID playerUUID) {
        return playerHorses.get(playerUUID);
    }

    public HorseData getHorseData(UUID playerUUID, int customModelData) {
        Map<Integer, HorseData> horses = playerHorses.get(playerUUID);
        return (horses != null) ? horses.get(customModelData) : null;
    }

    public void setHorseData(UUID playerUUID, int customModelData, UUID horseUUID, String horseWorld, Location horseLocation, boolean isAlive) {
        HorseData horseData = new HorseData(horseUUID, horseWorld, horseLocation, isAlive);
        playerHorses.computeIfAbsent(playerUUID, k -> new HashMap<>()).put(customModelData, horseData);
    }

    public void removeHorse(UUID playerUUID, int customModelData) {
        Map<Integer, HorseData> horses = playerHorses.get(playerUUID);
        if (horses != null) {
            horses.remove(customModelData);
            if (horses.isEmpty()) {
                playerHorses.remove(playerUUID);
            }
        }
    }
}
