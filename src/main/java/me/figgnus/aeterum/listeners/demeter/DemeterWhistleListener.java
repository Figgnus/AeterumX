package me.figgnus.aeterum.listeners.demeter;

import me.figgnus.aeterum.AeterumX;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.utils.HorseDataManager;
import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
        if (horseUUID != null) {
            Horse horse = (Horse) Bukkit.getEntity(horseUUID);
            if (horse != null && horse.isValid()) {
                player.sendMessage("You already have a horse assigned to this whistle!");
                return;
            } else {
                horseDataManager.removeHorse(player.getUniqueId(), customModelData);
            }
        }

        Location loc = player.getLocation();
        Horse horse = loc.getWorld().spawn(loc, Horse.class);
        horse.setOwner(player);
        horse.setCustomName(player.getName() + "'s Horse " + customModelData);
        horse.setCustomNameVisible(true);
        plugin.setEntityMetadata(horse, player.getUniqueId().toString(), "true");
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
            if (horse != null && horse.isValid()) {
                horse.teleport(player.getLocation());
                player.sendMessage("Your horse has been summoned!");
            } else {
                player.sendMessage("It seems your horse is lost. Let's get you a new one.");
                assignHorse(player, customModelData);
            }
        }
    }
}
