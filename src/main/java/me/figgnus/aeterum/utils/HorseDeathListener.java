package me.figgnus.aeterum.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Map;
import java.util.UUID;

public class HorseDeathListener implements Listener {
    private final HorseDataManager horseDataManager;

    public HorseDeathListener(HorseDataManager horseDataManager) {
        this.horseDataManager = horseDataManager;
    }

    @EventHandler
    public void onHorseDeath(EntityDeathEvent event){
        Entity entity = event.getEntity();

        // Check it he dead entity is a horse
        if (entity instanceof Horse){
            Horse horse = (Horse) entity;

            // Get horse's owner
            if (horse.getOwner() != null && horse.getOwner() instanceof Player){
                Player owner = (Player) horse.getOwner();
                UUID ownerUUID = owner.getUniqueId();

                // Find the horse data based on its UUID
                Map<Integer, HorseData> playerHorses = horseDataManager.getPlayerHorses(ownerUUID);
                if (playerHorses != null){
                    for (Map.Entry<Integer, HorseData> entry : playerHorses.entrySet()){
                        HorseData horseData = entry.getValue();
                        if (horseData.getHorseUUID().equals(horse.getUniqueId())){
                            // Mark the horse as dead in HorseData
                            horseDataManager.setHorseAlive(ownerUUID, entry.getKey(), false);
                            horseDataManager.saveHorses();
                            owner.sendMessage(ChatColor.RED + "Tvůj kůň zemřel :( Zatrub na roh a přide nový.");
                            break;
                        }
                    }
                }
            }
        }
    }
}
