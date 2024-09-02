package me.figgnus.aeterum.listeners.dionysus;

import me.figgnus.aeterum.AeterumX;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.utils.HorseDataManager;
import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class DionysusWhistleListener implements Listener {
    private final HorseDataManager horseDataManager;
    private final AeterumX plugin;
    public static final String HORSE_KEY = "Dionysus";
    private String dionysus_tag = "dionysus_tag";

    public DionysusWhistleListener(HorseDataManager horseDataManager, AeterumX plugin) {
        this.horseDataManager = horseDataManager;
        this.plugin = plugin;
    }
    @EventHandler
    public void onWhistleUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        // Check if the action is a right-click action
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        if (ItemUtils.isCustomItem(item, CustomItems.DRUNK_HORSE_TAME.getItemMeta().getCustomModelData())) {
            ItemMeta meta = item.getItemMeta();
            int customModelData = meta.getCustomModelData();
            // Ensure the player uses the item in the main hand
            if (event.getHand() != EquipmentSlot.HAND) return;

            UUID horseUUID = horseDataManager.getHorseUUID(player.getUniqueId(), customModelData);

            if (horseUUID == null) {
                player.sendMessage(ChatColor.GOLD + "Nový nejlepší přítel.");
                assignHorse(player, customModelData);
                horseUUID = horseDataManager.getHorseUUID(player.getUniqueId(), customModelData);
            }

            Horse horse = (Horse) Bukkit.getEntity(horseUUID);
            if (horse != null) {
                findAndTeleportHorse(player, player.getUniqueId().toString());
            }else{
                player.sendMessage(ChatColor.GOLD + "Tvůj kůň zemřel. Na jeho místo přichází nový.");
                assignHorse(player, customModelData);
            }
        }
    }
    public void findAndTeleportHorse(Player player, String playerUniqueId) {
        NamespacedKey key = new NamespacedKey(plugin, dionysus_tag);

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
                            player.sendMessage(ChatColor.GREEN + "Tvůj kůň přichází.");
                            return; // Exit after finding and teleporting the first match
                        }
                    }
                }
            }
        }
        player.sendMessage(ChatColor.RED + "Tvůj kůň se ztratil.");
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
        horse.setCustomName(player.getName() + "'s Horse ");
        horse.setCustomNameVisible(true);
        horse.setColor(Horse.Color.CHESTNUT);
        horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.32);
        horse.getAttribute(Attribute.GENERIC_JUMP_STRENGTH).setBaseValue(1.0);
        horse.setMaxHealth(30);
        horse.setHealth(30);
        horse.setInvulnerable(true);

        NamespacedKey key = new NamespacedKey(plugin, dionysus_tag);
        horse.getPersistentDataContainer().set(key, PersistentDataType.STRING, player.getUniqueId().toString());
        plugin.setEntityMetadata(horse, HORSE_KEY, "true");
        // Save horse UUID to config
        horseDataManager.setHorseUUID(player.getUniqueId(), customModelData, horse.getUniqueId());
    }
}
