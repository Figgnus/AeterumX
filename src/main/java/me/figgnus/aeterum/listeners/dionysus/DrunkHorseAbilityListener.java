package me.figgnus.aeterum.listeners.dionysus;

import com.dre.brewery.BPlayer;
import me.figgnus.aeterum.AeterumX;
import me.figgnus.aeterum.listeners.demeter.DemeterWhistleListener;
import me.figgnus.aeterum.utils.PermissionUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DrunkHorseAbilityListener implements Listener {
    private final AeterumX plugin;
    private final Map<UUID, BukkitRunnable> horseTasks = new HashMap<>();

    public DrunkHorseAbilityListener(AeterumX plugin) {
        this.plugin = plugin;
        startPeriodicTask();

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        // Check if the player is riding a horse
        if (player.isInsideVehicle() && player.getVehicle() instanceof Horse) {
            Horse horse = (Horse) player.getVehicle();
            String metadataValue = plugin.getEntityMetadata(horse, DionysusWhistleListener.HORSE_KEY);

            // Check if the horse has the Seed  ability
            if ("true".equals(metadataValue)) {

                Location horseLocation = horse.getLocation();
                horseLocation.getWorld().spawnParticle(Particle.HAPPY_VILLAGER, horseLocation.clone().add(0, 0.5, 0), 1, 0.2, 0.05, 0.2, 0.01);
                horseLocation.getWorld().spawnParticle(Particle.CLOUD, horseLocation.clone().add(0, 0.5, 0), 1, 0.2, 0.05, 0.2, 0.01);
            }
        }
    }

    private void startPeriodicTask() {
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.isInsideVehicle() && player.getVehicle() instanceof Horse) {
                        Horse horse = (Horse) player.getVehicle();
                        String metadataValue = plugin.getEntityMetadata(horse, DionysusWhistleListener.HORSE_KEY);
                        if ("true".equals(metadataValue)) {
                            if (!player.hasPermission(PermissionUtils.dionysusHorseAbility)) {
                                player.sendMessage(PermissionUtils.ridingPermissionMessage);
                                return;
                            }
                            startAuraTask(player, horse);
                        }
                    }
                }
            }
        };
        task.runTaskTimer(plugin, 0, 20); // Run task every second (20 ticks)
    }

    private void startAuraTask(Player player, Horse horse) {
        UUID horseUUID = horse.getUniqueId();

        // Cancel any existing task for this horse
        if (horseTasks.containsKey(horseUUID)) {
            horseTasks.get(horseUUID).cancel();
        }

        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                if (horse.isDead() || !player.isOnline() || !player.isInsideVehicle() || !player.getVehicle().equals(horse)) {
                    this.cancel();
                    horseTasks.remove(horseUUID);
                    return;
                }

                // Get the player's drunkenness level
                BPlayer bPlayer = BPlayer.get(player);
                // Check if bPlayer is null
                if (bPlayer == null) {
                    return; // Skip this iteration if bPlayer is null
                }
                int drunkenness = bPlayer.getDrunkeness();

                if (drunkenness > 0) {
                    // Calculate damage based on drunkenness level
                    double damage = drunkenness * 0.03; // Example: 0.1 damage per drunkenness level
                    // Apply damage to nearby entities
                    for (Entity entity : horse.getNearbyEntities(2, 2, 2)) {
                        if (entity instanceof LivingEntity && entity != player) {
                            ((LivingEntity) entity).damage(damage);
                            // Apply knockback effect
                            Vector knockback = entity.getLocation().toVector().subtract(horse.getLocation().toVector()).normalize().multiply(1);
                            entity.setVelocity(knockback);

                            // Spawn particle effects at the location of the damaged entity
                            Location loc = entity.getLocation();
                            entity.getWorld().spawnParticle(Particle.WITCH, loc, 10, 0.5, 0.5, 0.5, 0.01);
                        }
                    }
                }
            }
        };

        task.runTaskTimer(plugin, 0, 20); // Run task every second (20 ticks)
        horseTasks.put(horseUUID, task);
    }
}
