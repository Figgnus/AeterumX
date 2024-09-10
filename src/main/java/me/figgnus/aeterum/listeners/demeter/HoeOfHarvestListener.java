package me.figgnus.aeterum.listeners.demeter;

import me.figgnus.aeterum.AeterumX;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.utils.PermissionUtils;
import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class HoeOfHarvestListener implements Listener {
    private final AeterumX plugin;
    private final HashMap<UUID, Long> cooldowns = new HashMap<>();

    public HoeOfHarvestListener(AeterumX plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onItemUse(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        UUID playerUuid = player.getUniqueId();
        if (ItemUtils.isCustomItem(item, CustomItems.HOE_OF_HARVEST.getItemMeta().getCustomModelData())){
            if (!player.hasPermission(PermissionUtils.demeterHoe)){
                player.sendMessage(PermissionUtils.permissionItemMessage);
                event.setCancelled(true);
                return;
            }
            if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
                return;
            }
            //Check cooldown
            if (cooldowns.containsKey(playerUuid)) {
                long lastPlaced = cooldowns.get(playerUuid);
                long timeNow = System.currentTimeMillis();

                if (timeNow - lastPlaced < 100) {
                    return;
                }
            }
            Block clickedBlock = event.getClickedBlock();
            if (clickedBlock == null || !isFullyGrownCrop(clickedBlock)){
                return;
            }
            int radius = 3; // The radius of the circular area

            for (int dx = -radius; dx <= radius; dx++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    // Check if the current (dx, dz) coordinate is within the circle
                    if (Math.sqrt(dx * dx + dz * dz) <= radius) {
                        Block block = clickedBlock.getRelative(dx, 0, dz);
                        if (isFullyGrownCrop(block)) {
                            harvestAndReplant(block, player);
                        }
                    }
                }
            }
            player.playSound(player.getLocation(), Sound.BLOCK_CROP_BREAK, 1.0f, 1.0f);
            cooldowns.put(playerUuid, System.currentTimeMillis());
        }
    }
    private boolean isFullyGrownCrop(Block block) {
        if (block.getBlockData() instanceof Ageable) {
            Ageable ageable = (Ageable) block.getBlockData();
            return ageable.getAge() == ageable.getMaximumAge();
        }
        return false;
    }
    private void harvestAndReplant(Block block, Player player) {
        Material cropType = block.getType();
        block.setType(Material.AIR); // Remove the crop

        List<ItemStack> itemsToAdd = new ArrayList<>();

        switch (cropType) {
            case WHEAT:
                itemsToAdd.add(new ItemStack(Material.WHEAT, 1));
                itemsToAdd.add(new ItemStack(Material.WHEAT_SEEDS, 1));
                break;
            case POTATOES:
                itemsToAdd.add(new ItemStack(Material.POTATO, 2));
                break;
            case CARROTS:
                itemsToAdd.add(new ItemStack(Material.CARROT, 2));
                break;
            case BEETROOTS:
                itemsToAdd.add(new ItemStack(Material.BEETROOT, 1));
                itemsToAdd.add(new ItemStack(Material.BEETROOT_SEEDS, 1));
                break;
            default:
                // Handle other crops if necessary
                break;
        }

        // Check if inventory has enough space
        boolean hasSpace = true;
        for (ItemStack itemStack : itemsToAdd) {
            if (player.getInventory().firstEmpty() == -1) {
                hasSpace = false;
                break;
            }
        }

        if (hasSpace) {
            // Add items to inventory
            for (ItemStack itemStack : itemsToAdd) {
                player.getInventory().addItem(itemStack);
            }
        } else {
            // Drop items if inventory is full
            Location dropLocation = player.getLocation();
            for (ItemStack itemStack : itemsToAdd) {
                player.getWorld().dropItemNaturally(dropLocation, itemStack);
            }
        }
        // Replant the crop
        block.setType(cropType);
        if (block.getBlockData() instanceof Ageable) {
            Ageable ageable = (Ageable) block.getBlockData();
            ageable.setAge(0); // Reset the age to zero (replanting)
            block.setBlockData(ageable);
        }
    }
}
