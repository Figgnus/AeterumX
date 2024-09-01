package me.figgnus.aeterum.listeners.demeter;

import me.figgnus.aeterum.AeterumX;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.utils.HorseDataManager;
import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class DemeterWhistleListener implements Listener {
    private final HorseDataManager horseDataManager;
    private final AeterumX plugin;
    public static final String HORSE_KEY = "Demeter";

    public DemeterWhistleListener(HorseDataManager horseDataManager, AeterumX plugin) {
        this.horseDataManager = horseDataManager;
        this.plugin = plugin;
    }

    public void assignHorse(Player player, int customModelData) {
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
        horse.setCustomName(player.getName() + "'s Horse " + customModelData);
        horse.setCustomNameVisible(true);
        tahAHorse(horse, "test");
        plugin.setEntityMetadata(horse, HORSE_KEY, "true");
        horse.setColor(Horse.Color.GRAY);
        horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.31);
        horse.getAttribute(Attribute.GENERIC_JUMP_STRENGTH).setBaseValue(1.0);
        horse.setMaxHealth(30);
        horse.setHealth(30);

        horseDataManager.setHorseUUID(player.getUniqueId(), customModelData, horse.getUniqueId());
    }

    @EventHandler
    public void onWhistleUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (ItemUtils.isCustomItem(item, CustomItems.FLOWER_HORSE_TAME.getItemMeta().getCustomModelData())) {
            ItemMeta meta = item.getItemMeta();
            int customModelData = meta.getCustomModelData();
            // Ensure the player uses the item in the main hand
            if (event.getHand() != EquipmentSlot.HAND) return;

            UUID horseUUID = horseDataManager.getHorseUUID(player.getUniqueId(), customModelData);

            if (horseUUID == null) {
                player.sendMessage("You don't have a horse for this whistle yet! Here's one.");
                assignHorse(player, customModelData);
                horseUUID = horseDataManager.getHorseUUID(player.getUniqueId(), customModelData);
            }

            Horse horse = (Horse) Bukkit.getEntity(horseUUID);
            if (horse != null) {
                findAndTeleportHorse(player, "test" );
            }else{
                player.sendMessage("Could not find a horse for this whistle! With value: test");
            }
        }
    }
    private void tahAHorse(Horse horse, String value) {
        NamespacedKey key = new NamespacedKey(plugin, "horse_tag");
        horse.getPersistentDataContainer().set(key, PersistentDataType.STRING, value);
    }
    public void findAndTeleportHorse(Player player, String tagValue) {
        NamespacedKey key = new NamespacedKey(plugin, "horse_tag");

        // Iterate through all entities in the world (or specific world if needed)
        for (Entity entity : player.getWorld().getEntities()) {
            if (entity instanceof Horse) {
                Horse horse = (Horse) entity;

                // Check if the horse has the tag
                if (horse.getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
                    String value = horse.getPersistentDataContainer().get(key, PersistentDataType.STRING);
                    if (value != null && value.equals(tagValue)) {
                        // Teleport the horse to the player
                        horse.teleport(player.getLocation());
                        player.sendMessage("Horse with tag '" + tagValue + "' teleported to you.");
                        return; // Exit after finding and teleporting the first match
                    }
                }
            }
        }

        player.sendMessage("No horse with the tag '" + tagValue + "' found.");
    }
}
