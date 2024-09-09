package me.figgnus.aeterum.listeners.hermes;

import me.figgnus.aeterum.AeterumX;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.utils.PermissionUtils;
import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MessengerPackListener implements Listener {
    private final AeterumX plugin;
    private final Map<UUID, Inventory> playerInventories = new HashMap<>();
    private final File inventoryFolder;

    public MessengerPackListener(AeterumX plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        // Set up the folder for saving player inventories
        inventoryFolder = new File(plugin.getDataFolder(), "inventories");
        if (!inventoryFolder.exists()) {
            inventoryFolder.mkdirs();
        }
        // Start a repeating task to check player hunger and health
        startHungerCheckTask();
        // Schedule periodic inventory saves every 5 minutes (6000 ticks)
        plugin.getServer().getScheduler().runTaskTimer(plugin, this::saveAllInventories, 6000L, 6000L);
    }


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        if (ItemUtils.isCustomItem(item, CustomItems.MESSENGER_PACK.getItemMeta().getCustomModelData())) {
            if (!player.hasPermission(PermissionUtils.hermesMessengerPack)) {
                player.sendMessage(PermissionUtils.permissionItemMessage);
                return;
            }
            player.openInventory(getPlayerInventory(player));
        }
    }
    private void startHungerCheckTask() {
        plugin.getServer().getScheduler().runTaskTimer(plugin, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                checkPlayerHungerAndHealth(player);
            }
        }, 0L, 100L); // Run every 5 seconds (100 ticks)
    }

    // Method to check player's hunger and health
    private void checkPlayerHungerAndHealth(Player player) {
        int hunger = player.getFoodLevel();
        double health = player.getHealth();
        UUID playerUUID = player.getUniqueId();

        if (hunger < 20 && playerInventories.containsKey(playerUUID)) {
            // Get the player's custom inventory
            Inventory inventory = getPlayerInventory(player);

            // Define when to consume food
            boolean shouldConsumeFood = (health < player.getMaxHealth() && hunger <= 18) || hunger <= 12;

            if (shouldConsumeFood) {
                consumeFoodFromInventory(player, inventory);
            }
        }
    }
    // Method to consume food from the custom inventory
    private void consumeFoodFromInventory(Player player, Inventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (item != null && item.getType().isEdible()) {
                Material material = item.getType();
                int foodValue = getFoodValue(material); // Get hunger value
                float saturation = getSaturationValue(material); // Get saturation value

                // Calculate how much hunger to restore
                int hungerRestored = Math.min(20 - player.getFoodLevel(), foodValue);
                player.setFoodLevel(player.getFoodLevel() + hungerRestored);
                player.setSaturation(player.getSaturation() + saturation);

                player.sendMessage(ChatColor.GREEN + "Consumed " + material.name());

                // Reduce the amount of the food in the inventory slot
                if (item.getAmount() > 1) {
                    item.setAmount(item.getAmount() - 1);
                } else {
                    inventory.setItem(i, null);
                }

                return; // Stop after consuming one food item
            }
        }

        // If no food was found, send a message to the player
        player.sendMessage(ChatColor.RED + "No food found in your inventory.");
    }

    public Inventory getPlayerInventory(Player player) {
        UUID playerUUID = player.getUniqueId();
        if (!playerInventories.containsKey(playerUUID)) {
            playerInventories.put(playerUUID, loadInventory(playerUUID));
        }
        return playerInventories.get(playerUUID);
    }

    public void saveInventory(UUID playerUUID) {
        Inventory inventory = playerInventories.get(playerUUID);
        if (inventory == null) return;

        File inventoryFile = new File(inventoryFolder, playerUUID.toString() + ".yml");
        FileConfiguration inventoryConfig = YamlConfiguration.loadConfiguration(inventoryFile);

        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            inventoryConfig.set("inventory.slot" + i, item);
        }
        try {
            inventoryConfig.save(inventoryFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private int getFoodValue(Material material) {
        switch (material) {
            case BREAD:
                return 5;
            case COOKED_BEEF:
                return 8;
            case COOKED_CHICKEN:
                return 6;
            case APPLE:
                return 4;
            case GOLDEN_CARROT:
                return 6;
            case CARROT:
                return 3;
            // Add more food items as needed
            default:
                return 0; // For non-food items
        }
    }

    private float getSaturationValue(Material material) {
        switch (material) {
            case BREAD:
                return 0.6f;
            case COOKED_BEEF:
                return 12.8f;
            case COOKED_CHICKEN:
                return 0.6f;
            case APPLE:
                return 0.3f;
            case GOLDEN_CARROT:
                return 1.2f;
            case CARROT:
                return 0.6f;
            // Add more food items as needed
            default:
                return 0.0f;
        }
    }

    public Inventory loadInventory(UUID playerUUID) {
        Inventory inventory = Bukkit.createInventory(null, 54, "Poštovní Brašna");
        File inventoryFile = new File(inventoryFolder, playerUUID.toString() + ".yml");
        if (inventoryFile.exists()) {
            FileConfiguration inventoryConfig = YamlConfiguration.loadConfiguration(inventoryFile);
            for (int i = 0; i < inventory.getSize(); i++) {
                ItemStack item = inventoryConfig.getItemStack("inventory.slot" + i);
                inventory.setItem(i, item);
            }
        }
        return inventory;
    }

    public void saveAllInventories() {
        for (UUID playerUUID : playerInventories.keySet()) {
            saveInventory(playerUUID);
        }
    }
}
