package me.figgnus.aeterum.listeners.demeter;

import me.figgnus.aeterum.Plugin;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.utils.PermissionUtils;
import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class GrowthPotionListener implements Listener {
    private final Plugin plugin;

    public GrowthPotionListener(Plugin plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack consumedItem = event.getItem();
        if (ItemUtils.isCustomItem(consumedItem, CustomItems.GROWTH_POTION.getItemMeta().getCustomModelData())) {
            if (!player.hasPermission(PermissionUtils.demeterGrowthPotion)){
                player.sendMessage(PermissionUtils.permissionItemMessage);
                return;
            }
            if (player.getGameMode() == GameMode.SURVIVAL){
                consumedItem.setAmount(consumedItem.getAmount() - 1);
            }
            applyGrowthEffect(event.getPlayer());
        }
    }
    private void applyGrowthEffect(Player player) {
        int totalStages = 6;
        // Apply custom effect to grow plants around the player
        new GrowthTask(plugin, player, totalStages).runTaskTimer(plugin, 0, 20); // Runs every second for 5 seconds
    }
    private static class GrowthTask extends BukkitRunnable {

        private final Plugin plugin;
        private final Player player;
        private final int totalStages;
        private int counter = 0;
        private int radius = 10;

        public GrowthTask(Plugin plugin, Player player, int totalStages){
            this.plugin = plugin;
            this.player = player;
            this.totalStages = totalStages;
        }

        @Override
        public void run() {
            if (counter >= 5) {
                this.cancel();
                return;
            }
            growPlantsAroundPlayer(player);
            counter++;
        }

        private void growPlantsAroundPlayer(Player player) {
            World world = player.getWorld();
            Location location = player.getLocation();
            spawnParticleCircle(player.getLocation());

            for (int x = -10; x <= 10; x++) {
                for (int z = -10; z <= 10; z++) {
                    for (int y = -1; y <= 1; y++) {
                        // Check if the block is within the circular radius
                        if (Math.sqrt(x * x + z * z) <= 10) {
                            Block block = world.getBlockAt(location.clone().add(x, y, z));
                            if (isGrowablePlant(block.getType())) {
                                incrementPlantGrowthStage(block);
                            }
                        }
                    }
                }
            }
        }

        private void spawnParticleCircle(Location center) {
            for (int i = 0; i < 360; i += 10) { // 10-degree increments
                double radians = Math.toRadians(i);
                double x = center.getX() + radius * Math.cos(radians);
                double z = center.getZ() + radius * Math.sin(radians);
                Location particleLocation = new Location(center.getWorld(), x, center.getY(), z);
                center.getWorld().spawnParticle(Particle.WITCH, particleLocation, 5);
            }
        }

        private void incrementPlantGrowthStage(Block block) {
            if (block.getBlockData() instanceof Ageable) {
                Ageable ageable = (Ageable) block.getBlockData();
                int newAge = ageable.getAge() + 1;
                if (newAge > ageable.getMaximumAge()) {
                    newAge = ageable.getMaximumAge();
                }
                ageable.setAge(newAge);
                block.setBlockData(ageable);

                // Add particle effect
                block.getWorld().spawnParticle(Particle.HAPPY_VILLAGER, block.getLocation().add(0.5, 0.5, 0.5), 10, 0.3, 0.3, 0.3, 0);
            }
        }

        private boolean isGrowablePlant(Material material) {
            return material == Material.WHEAT || material == Material.CARROTS || material == Material.POTATOES || material == Material.BEETROOTS;
        }
    }
}
