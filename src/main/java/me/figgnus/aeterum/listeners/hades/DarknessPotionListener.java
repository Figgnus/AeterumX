package me.figgnus.aeterum.listeners.hades;

import me.figgnus.aeterum.AeterumX;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.utils.PermissionUtils;
import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class DarknessPotionListener implements Listener {
    private final AeterumX plugin;

    public DarknessPotionListener(AeterumX plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onPlayerConsume(PlayerItemConsumeEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (ItemUtils.isCustomItem(item, CustomItems.DARKNESS_POTION.getItemMeta().getCustomModelData())){
            if (!player.hasPermission(PermissionUtils.hadesDarknessPotion)){
                player.sendMessage(PermissionUtils.permissionItemMessage);
                event.setCancelled(true);
                return;
            }
            applyEffect(player);
        }
    }

    private void applyEffect(Player player) {
        new DarknessEffectTask(player).runTaskTimer(plugin, 0, 20);
    }
    private static class DarknessEffectTask extends BukkitRunnable {
        private final Player player;
        private int interaction = 0;
        private final int radius = 10;

        private DarknessEffectTask(Player player) {
            this.player = player;
        }

        @Override
        public void run() {
            if (interaction >= 20) {
                cancel();
                return;
            }

            Location playerLocation = player.getLocation();

            // Get all nearby entities within the radius
            for (Entity nearbyEntity : player.getWorld().getNearbyEntities(playerLocation, radius, radius, radius)) {
                if (nearbyEntity.equals(player)) {
                    continue; // Skip the player who drank the potion
                }
                if (nearbyEntity instanceof LivingEntity) { // Ensure the entity is living (e.g., players, mobs, etc.)
                    LivingEntity livingEntity = (LivingEntity) nearbyEntity;
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 1));
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 40, 1));
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 40, 0));
                    livingEntity.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, livingEntity.getLocation(), 30, 0.5, 0.5, 0.5, 0);
                }
            }

            spawnParticles(player.getLocation());
            interaction++;
        }

        private void spawnParticles(Location center) {
            for (int i = 0; i < 360; i += 10) { // 10-degree increments
                double radians = Math.toRadians(i);
                double x = center.getX() + radius * Math.cos(radians);
                double z = center.getZ() + radius * Math.sin(radians);
                Location particleLocation = new Location(center.getWorld(), x, center.getY(), z);
                center.getWorld().spawnParticle(Particle.WITCH, particleLocation, 1);
            }
        }
    }
}
