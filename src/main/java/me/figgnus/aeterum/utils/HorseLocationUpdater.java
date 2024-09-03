package me.figgnus.aeterum.utils;

import me.figgnus.aeterum.AeterumX;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Horse;

import java.util.Map;
import java.util.UUID;

public class HorseLocationUpdater implements Runnable{
    private final HorseDataManager horseDataManager;
    private final AeterumX plugin;
    private final long interval;
    private int counter;

    public HorseLocationUpdater(HorseDataManager horseDataManager, AeterumX plugin, long interval) {
        this.horseDataManager = horseDataManager;
        this.plugin = plugin;
        this.interval = interval;
        this.counter = 0;
    }

    public void start() {
        Bukkit.getScheduler().runTaskTimer(plugin, this, 0L, interval);
    }

    @Override
    public void run() {
        // Iterate over all players who have horses registered
        for (UUID playerUUID : horseDataManager.getAllPlayerUUIDs()) {
            Map<Integer, HorseData> horses = horseDataManager.getPlayerHorses(playerUUID);
            for (Map.Entry<Integer, HorseData> entry : horses.entrySet()) {
                int customModelData = entry.getKey();
                HorseData horseData = entry.getValue();
                Horse horse = (Horse) Bukkit.getEntity(horseData.getHorseUUID());

                if (horse != null && horse.isValid()) {
                    Location horseLocation = horse.getLocation();
                    horseDataManager.setHorseData(playerUUID, customModelData, horse.getUniqueId(), horseLocation.getWorld().getName(), horseLocation);
                }
            }
        }
        // Increment the counter
        counter++;

        // Save the horses to config every 60 runs (every 5 minutes if interval is 5 seconds)
        if (counter >= 60) {
            horseDataManager.saveHorses();
            counter = 0; // Reset the counter after saving
        }
    }
}
