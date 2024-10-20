package me.figgnus.aeterum.utils;

import me.figgnus.aeterum.AeterumX;
import me.figgnus.aeterum.listeners.demeter.DemeterWhistleListener;
import me.figgnus.aeterum.listeners.dionysus.DionysusWhistleListener;
import me.figgnus.aeterum.listeners.hades.HadesWhistleListener;
import me.figgnus.aeterum.listeners.hermes.HermesWhistleListener;
import me.figgnus.aeterum.listeners.poseidon.PoseidonWhistleListener;
import me.figgnus.aeterum.listeners.zeus.ZeusWhistleListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.Arrays;
import java.util.List;


public class UnsummonHorseListener implements Listener {
    private final AeterumX plugin;
    private final List<String> customHorseKeys = Arrays.asList(
            DemeterWhistleListener.HORSE_KEY,  // Existing key
            DionysusWhistleListener.HORSE_KEY,
            HadesWhistleListener.HORSE_KEY,
            HermesWhistleListener.HORSE_KEY,
            PoseidonWhistleListener.HORSE_KEY,
            ZeusWhistleListener.HORSE_KEY
    );

    public UnsummonHorseListener(AeterumX plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();

        // Check if the entity is a type of horse
        if (entity instanceof AbstractHorse) {
            AbstractHorse horse = (AbstractHorse) entity;

            // Loop through the list of custom horse keys to check if the entity has custom data
            boolean isCustomHorse = false;
            for (String horseKey : customHorseKeys) {
                String customHorseData = plugin.getEntityMetadata(horse, horseKey);
                if (customHorseData != null && customHorseData.equals("true")) {
                    isCustomHorse = true;
                    break;
                }
            }

            // If it's not a custom horse, return
            if (!isCustomHorse) {
                return;
            }

            // Unsummon the horse and mark it with a "unsummoned" state
            if (player.isSneaking()) {
                HorseUtils.unsummonHorse(horse, plugin);
                // Close the inventory on the next tick
                Bukkit.getScheduler().runTask(plugin, player::closeInventory);

                player.sendMessage(ChatColor.GOLD + "Kůň zmizel. Vrátí se po zatroubení na roh.");
            }
        }
    }
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Entity entity = event.getRightClicked();

        // Check if the entity is a horse and unsummoned
        if (entity instanceof AbstractHorse) {
            String isUnsummoned = plugin.getEntityMetadata(entity, "unsummoned");

            // If the horse is in the unsummoned state, cancel interaction
            if (isUnsummoned != null && isUnsummoned.equals("true")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();

        // Check if the entity is a horse and unsummoned
        if (entity instanceof AbstractHorse) {
            String isUnsummoned = plugin.getEntityMetadata(entity, "unsummoned");

            // If the horse is in the unsummoned state, cancel damage events
            if (isUnsummoned != null && isUnsummoned.equals("true")) {
                event.setCancelled(true);
            }
        }
    }
}
