package me.figgnus.aeterum.listeners.hades;

import me.figgnus.aeterum.AeterumX;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.utils.HorseData;
import me.figgnus.aeterum.utils.HorseDataManager;
import me.figgnus.aeterum.utils.ItemUtils;
import me.figgnus.aeterum.utils.PermissionUtils;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.entity.SkeletonHorse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.UUID;

public class HadesWhistleListener implements Listener {
    private final HorseDataManager horseDataManager;
    private final AeterumX plugin;
    public static final String HORSE_KEY = "Hades";

    public HadesWhistleListener(HorseDataManager horseDataManager, AeterumX plugin) {
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
        if (ItemUtils.isCustomItem(item, CustomItems.ZOMBIE_HORSE_TAME.getItemMeta().getCustomModelData())) {
            if (!(player.hasPermission(PermissionUtils.hadesHorseTame))) {
                player.sendMessage(PermissionUtils.permissionItemMessage);
                event.setCancelled(true);
                return;
            }
            if (player.hasCooldown(item.getType())) {
                player.sendMessage(ChatColor.YELLOW + "Nemáš dost dechu pro zatroubení. Počkej chvilku.");
                return;
            }
            player.setCooldown(item.getType(), 140);
            // Ensure the player uses the item in the main hand
            if (event.getHand() != EquipmentSlot.HAND) return;

            UUID playerUUID = player.getUniqueId();
            int customModelData = item.getItemMeta().getCustomModelData();
            HorseData horseData = horseDataManager.getHorseData(playerUUID, customModelData);

            if (horseData == null) {
                player.sendMessage(ChatColor.GOLD + "Nový nejlepší přítel.");
                assignHorse(player, customModelData);
            }
            findAndTeleportHorse(player, playerUUID.toString(), customModelData);
        }
    }
    public void findAndTeleportHorse(Player player, String playerUniqueId, Integer customModelData) {
        UUID playerUUID = UUID.fromString(playerUniqueId);

        // Check if the horse is alive
        if (!horseDataManager.isHorseAlive(playerUUID, customModelData)) {
            player.sendMessage(ChatColor.GOLD + "Tvůj kůň zemřel. Na jeho místo příchází nový.");
            assignHorse(player, customModelData);
        }

        // Retrieve the last known horse data
        HorseData horseData = horseDataManager.getHorseData(playerUUID, customModelData);

        if (horseData != null) {
            World world = Bukkit.getWorld(horseData.getHorseWorld());
            if (world == null) {
                player.sendMessage(ChatColor.RED + "Horse world not found.");
                return;
            }

            Location horseLocation = horseData.getHorseLocation();
            Chunk originalChunk = horseLocation.getChunk();

            // Force-load the original chunk where the horse was last known to be
            originalChunk.load(true);

            // Attempt to find the horse in the original chunk
            SkeletonHorse horse = (SkeletonHorse) Bukkit.getEntity(horseData.getHorseUUID());

            if (horse != null) {
                // Teleport the horse to the player
                Location playerLocation = player.getLocation();
                Location safeLocation = getSafeLocationBehindPlayer(player);
                if (safeLocation == null){
                    safeLocation = playerLocation;
                }
                horse.teleport(safeLocation);
                player.sendMessage(ChatColor.GREEN + "Whoosh!");

                // Unload the chunk after teleporting the horse
                Bukkit.getScheduler().runTaskLater(plugin, () -> originalChunk.unload(), 20L); // Unload after 1 second
                return; // Exit if horse is found in the original chunk
            }

            // If the horse is not found in the original chunk, check the surrounding 3x3 chunks
            boolean horseFound = false;
            for (int dx = -1; dx <= 1; dx++) {
                for (int dz = -1; dz <= 1; dz++) {
                    if (horseFound) break; // Exit loop if horse is found

                    // Load the surrounding chunk at (x + dx, z + dz)
                    Chunk currentChunk = world.getChunkAt(originalChunk.getX() + dx, originalChunk.getZ() + dz);
                    currentChunk.load(true);

                    // Try to find the horse in this chunk
                    horse = (SkeletonHorse) Bukkit.getEntity(horseData.getHorseUUID());

                    if (horse != null) {
                        // Teleport the horse to the player
                        Location playerLocation = player.getLocation();
                        Location safeLocation = getSafeLocationBehindPlayer(player);
                        if (safeLocation == null){
                            safeLocation = playerLocation;
                        }
                        horse.teleport(safeLocation);
                        player.sendMessage(ChatColor.GREEN + "Whoosh!");
                        horseFound = true;
                    }

                    // Unload the chunk after checking
                    Bukkit.getScheduler().runTaskLater(plugin, () -> currentChunk.unload(), 20L); // Unload after 1 second
                }
            }

            if (!horseFound) {
                player.sendMessage(ChatColor.RED + "Nepodařilo se najít tvého koně. Poslední lokace: " + horseData.getHorseLocation());
            }
        } else {
            player.sendMessage(ChatColor.RED + "Nepodařilo se najít tvého koně.");
        }
    }

    public void assignHorse(Player player, int customModelData) {
        UUID playerUUID = player.getUniqueId();

        // Retrieve existing horse data if it exists
        HorseData horseData = horseDataManager.getHorseData(playerUUID, customModelData);
        SkeletonHorse horse = null;

        // Check if the horse already exists in the world
        if (horseData != null) {
            horse = (SkeletonHorse) Bukkit.getEntity(horseData.getHorseUUID());
        }

        // If the horse exists and is valid, no need to create a new one
        if (horse != null && horse.isValid()) {
            return;
        }

        // Remove old horse data if the horse was invalid or non-existent
        if (horseData != null) {
            horseDataManager.removeHorse(playerUUID, customModelData);
        }

        // Create a new horse at the player's location
        horse = player.getWorld().spawn(player.getLocation(), SkeletonHorse.class);
        Location horseLocation = horse.getLocation(); // Get the current location of the player for the new horse
        World horseWorld = horseLocation.getWorld();

        // Set horse properties
        horse.setOwner(player);
        horse.setCustomName(player.getName() + "'s Horse");
        horse.setCustomNameVisible(true);
        horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.32);
        horse.getAttribute(Attribute.GENERIC_JUMP_STRENGTH).setBaseValue(1.0);
        horse.setMaxHealth(30);
        horse.setHealth(30);
        horse.setInvulnerable(true);
        horse.getAttribute(Attribute.GENERIC_STEP_HEIGHT).setBaseValue(2.2);
        horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));



        Location safeLocation = getSafeLocationBehindPlayer(player);
        if (safeLocation == null) {
            safeLocation = player.getLocation();
        }

        horse.teleport(safeLocation);

        // Optional: Set additional metadata if needed
        plugin.setEntityMetadata(horse, HORSE_KEY, "true");

        // Save the horse data (UUID, world, and horse location) to the data manager
        horseDataManager.setHorseData(playerUUID, customModelData, horse.getUniqueId(), horseWorld.getName(), horseLocation, true);
        horseDataManager.saveHorses();
    }
    private Location getSafeLocationBehindPlayer(Player player) {
        Location playerLocation = player.getLocation();
        Vector direction = playerLocation.getDirection().normalize(); // Players location
        Location behindPlayer = playerLocation.subtract(direction.multiply(5)); // 5 blocks behind the player

        // Check for safe location
        for (int i = 0; i < 5; i++) { // Check range of distances behind the player
            Location checkLocation = behindPlayer.clone().add(direction.multiply(i));
            // Verify if the location is safe (not lava, not solid blocks, etc.)
            if (isSafeLocation(checkLocation)) {
                return checkLocation; // Return the first safe location found
            }
        }
        return null;
    }

    private boolean isSafeLocation(Location checkLocation) {
        Block block = checkLocation.getBlock();
        Block blockBelow = checkLocation.clone().add(0, -1, 0).getBlock();

        // Check if the block and block below are safe
        return block.isPassable() && block.getType() != Material.LAVA && blockBelow.getType().isSolid();
    }
}
