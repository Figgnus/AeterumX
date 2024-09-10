package me.figgnus.aeterum.listeners.hermes;

import me.figgnus.aeterum.AeterumX;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.utils.PermissionUtils;
import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.FoodComponent;

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
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        getPlayerInventory(player);
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
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        if (ItemUtils.isCustomItem(event.getItemInHand(), CustomItems.MESSENGER_PACK.getItemMeta().getCustomModelData())) {
            event.setCancelled(true);
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
            boolean shouldConsumeFood = health < player.getMaxHealth() || hunger <= 12;

            if (shouldConsumeFood) {
                consumeFoodFromInventory(player, inventory);
            }
        }
    }
    // Method to consume food from the custom inventory
    private void consumeFoodFromInventory(Player player, Inventory inventory) {
        if (!player.getInventory().contains(CustomItems.MESSENGER_PACK)) {
            return;
        }

        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (item != null) {
                // First, get the item meta
                ItemMeta meta = item.getItemMeta();
                int foodValue = 0;
                float saturation = 0;

                if (meta != null && meta.hasFood()) {
                    // Get the food component (if it's a custom food)
                    FoodComponent foodComponent = meta.getFood();
                    foodValue = foodComponent.getNutrition();
                    saturation = foodComponent.getSaturation();
                }

                // If foodValue and saturation are still 0, use vanilla food values
                if (foodValue == 0 && saturation == 0) {
                    Material material = item.getType();
                    foodValue = getFoodValue(material);
                    saturation = getSaturationValue(material);
                }

                // Only consume if foodValue and saturation are valid
                if (foodValue > 0 && saturation > 0) {
                    // Calculate how much hunger to restore
                    int hungerRestored = Math.min(20 - player.getFoodLevel(), foodValue);
                    player.setFoodLevel(player.getFoodLevel() + hungerRestored);
                    player.setSaturation(player.getSaturation() + saturation);

                    player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EAT, 1, 1);

                    // Reduce the amount of the food in the inventory slot
                    if (item.getAmount() > 1) {
                        item.setAmount(item.getAmount() - 1);
                    } else {
                        inventory.setItem(i, null);
                    }

                    return; // Stop after consuming one food item
                }
            }
        }
    }

    private int getFoodValue(Material material) {
        switch (material) {
            case APPLE:
                return 4;
            case BAKED_POTATO:
                return 6;
            case BEETROOT:
                return 1;
            case BEETROOT_SOUP:
                return 6;
            case BREAD:
                return 5;
            case CARROT:
                return 3;
            case COOKED_CHICKEN:
                return 6;
            case COOKED_COD:
                return 5;
            case COOKED_MUTTON:
                return 6;
            case COOKED_PORKCHOP:
                return 8;
            case COOKED_RABBIT:
                return 5;
            case COOKED_SALMON:
                return 6;
            case COOKIE:
                return 2;
            case DRIED_KELP:
                return 1;
            case GLOW_BERRIES:
                return 2;
            case GOLDEN_CARROT:
                return 6;
            case HONEY_BOTTLE:
                return 6;
            case MELON_SLICE:
                return 2;
            case MUSHROOM_STEW:
                return 6;
            case PUMPKIN_PIE:
                return 8;
            case RABBIT_STEW:
                return 10;
            case COOKED_BEEF:
                return 8;
            case SWEET_BERRIES:
                return 2;
            default:
                return 0; // For non-food items
        }
    }

    private float getSaturationValue(Material material) {
        switch (material) {
            case APPLE:
                return 2.4f;
            case BAKED_POTATO:
                return 6f;
            case BEETROOT:
                return 1.2f;
            case BEETROOT_SOUP:
                return 7.2f;
            case BREAD:
                return 6f;
            case CARROT:
                return 3.6f;
            case COOKED_CHICKEN:
                return 7.2f;
            case COOKED_COD:
                return 6f;
            case COOKED_MUTTON:
                return 9.6f;
            case COOKED_PORKCHOP:
                return 12.8f;
            case COOKED_RABBIT:
                return 6f;
            case COOKED_SALMON:
                return 9.6f;
            case COOKIE:
                return 0.4f;
            case DRIED_KELP:
                return 0.6f;
            case GLOW_BERRIES:
                return 0.4f;
            case GOLDEN_CARROT:
                return 14.4f;
            case HONEY_BOTTLE:
                return 1.2f;
            case MELON_SLICE:
                return 1.2f;
            case MUSHROOM_STEW:
                return 7.2f;
            case PUMPKIN_PIE:
                return 4.8f;
            case RABBIT_STEW:
                return 12f;
            case COOKED_BEEF:
                return 12.8f;
            case SWEET_BERRIES:
                return 0.4f;
            default:
                return 0; // For non-food items
        }
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
        Bukkit.getLogger().info("AeterumX | Saved player inventories");
    }
}
