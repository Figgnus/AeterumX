package me.figgnus.aeterum.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HorseDataManager {
    private  final File file;
    private  FileConfiguration config;
    private  final Map<UUID, Map<Integer, UUID>> playerHorses;

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
            Map<Integer, UUID> horses = new HashMap<>();
            for (String customModelDataKey : config.getConfigurationSection(playerKey).getKeys(false)) {
                int customModelData = Integer.parseInt(customModelDataKey);
                UUID horseUUID = UUID.fromString(config.getString(playerKey + "." + customModelDataKey));
                horses.put(customModelData, horseUUID);
            }
            playerHorses.put(playerUUID, horses);
        }
    }

    public void saveHorses() {
        for (UUID playerUUID : playerHorses.keySet()) {
            Map<Integer, UUID> horses = playerHorses.get(playerUUID);
            for (Integer customModelData : horses.keySet()) {
                config.set(playerUUID.toString() + "." + customModelData, horses.get(customModelData).toString());
            }
        }
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UUID getHorseUUID(UUID playerUUID, int customModelData) {
        Map<Integer, UUID> horses = playerHorses.get(playerUUID);
        return (horses != null) ? horses.get(customModelData) : null;
    }

    public void setHorseUUID(UUID playerUUID, int customModelData, UUID horseUUID) {
        playerHorses.computeIfAbsent(playerUUID, k -> new HashMap<>()).put(customModelData, horseUUID);
    }

    public void removeHorse(UUID playerUUID, int customModelData) {
        Map<Integer, UUID> horses = playerHorses.get(playerUUID);
        if (horses != null) {
            horses.remove(customModelData);
            if (horses.isEmpty()) {
                playerHorses.remove(playerUUID);
            }
        }
    }
}
