package me.figgnus.aeterum.listeners.hermes;

import me.figgnus.aeterum.AeterumX;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.utils.PermissionUtils;
import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.Bukkit;
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
    }
}
