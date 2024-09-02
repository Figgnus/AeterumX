package me.figgnus.aeterum.utils;

import me.figgnus.aeterum.AeterumX;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class HorseUtils {
    public static void findAndTeleportHorse(Player player, String playerUniqueId, String god_tag, AeterumX plugin) {
        NamespacedKey key = new NamespacedKey(plugin, god_tag);

        // Iterate through all worlds in the server
        for (World world : Bukkit.getWorlds()) {
            // Iterate through all entities in the world
            for (Entity entity : world.getEntities()) {
                if (entity instanceof Horse) {
                    Horse horse = (Horse) entity;

                    // Check if the horse has the tag
                    if (horse.getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
                        String value = horse.getPersistentDataContainer().get(key, PersistentDataType.STRING);
                        if (value != null && value.equals(playerUniqueId)) {
                            // Teleport the horse to the player's location, even across dimensions
                            Location playerLocation = player.getLocation();
                            horse.teleport(playerLocation);
                            player.sendMessage("Horse with tag '" + playerUniqueId + "' teleported to you.");
                            return; // Exit after finding and teleporting the first match
                        }
                    }
                }
            }
        }
        player.sendMessage("No horse with the tag '" + playerUniqueId + "' found.");
    }
    public static void assignHorse(Player player, int customModelData, HorseDataManager horseDataManager, String HORSE_KEY, AeterumX plugin) {
        UUID horseUUID = horseDataManager.getHorseUUID(player.getUniqueId(), customModelData);
        Horse horse = horseUUID != null ? (Horse) Bukkit.getEntity(horseUUID) : null;

        if (horse != null && horse.isValid()) {
            player.sendMessage("You already have a horse assigned to this whistle!");
            return;
        }
        // Remove old horse data if it exists
        if (horseUUID != null) {
            horseDataManager.removeHorse(player.getUniqueId(), customModelData);
        }
        // Create a new horse
        Location loc = player.getLocation();
        horse = loc.getWorld().spawn(loc, Horse.class);
        horse.setOwner(player);
        horse.setCustomName(player.getName() + "'s Horse ");
        horse.setCustomNameVisible(true);
        horse.setColor(Horse.Color.GRAY);
        horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.32);
        horse.getAttribute(Attribute.GENERIC_JUMP_STRENGTH).setBaseValue(1.0);
        horse.setMaxHealth(30);
        horse.setHealth(30);
        horse.setInvulnerable(true);

        NamespacedKey key = new NamespacedKey(plugin, "horse_tag");
        horse.getPersistentDataContainer().set(key, PersistentDataType.STRING, player.getUniqueId().toString());
        plugin.setEntityMetadata(horse, HORSE_KEY, "true");
        // Save horse UUID to config
        horseDataManager.setHorseUUID(player.getUniqueId(), customModelData, horse.getUniqueId());
    }
}
