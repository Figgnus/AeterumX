package me.figgnus.aeterum.listeners.hermes;

import me.figgnus.aeterum.AeterumX;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.items.OraxenItems;
import me.figgnus.aeterum.listeners.demeter.DemeterWhistleListener;
import me.figgnus.aeterum.utils.PermissionUtils;
import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class SpeedHorseAbilityListener implements Listener {
    private final AeterumX plugin;
    private int duration = 300;

    public SpeedHorseAbilityListener(AeterumX plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        // Check if the player is riding a horse
        if (player.isInsideVehicle() && player.getVehicle() instanceof Horse) {
            Horse horse = (Horse) player.getVehicle();
            String metadataValue = plugin.getEntityMetadata(horse, HermesWhistleListener.HORSE_KEY);

            // Check if the horse has the Seed  ability
            if ("true".equals(metadataValue)) {

                Location horseLocation = horse.getLocation();
                horseLocation.getWorld().spawnParticle(Particle.FIREWORK, horseLocation.clone().add(0, 0.5, 0), 1, 0.2, 0.05, 0.2, 0.01);
                horseLocation.getWorld().spawnParticle(Particle.CLOUD, horseLocation.clone().add(0, 0.5, 0), 1, 0.2, 0.05, 0.2, 0.01);
            }
        }
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (ItemUtils.isCustomOraxenItem(item, OraxenItems.SPEED_HORSE_ABILITY_NAME)){
            if (!event.getPlayer().hasPermission(PermissionUtils.hermesHorseAbility)){
                event.getPlayer().sendMessage(PermissionUtils.permissionItemMessage);
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (ItemUtils.isCustomOraxenItem(item, OraxenItems.SPEED_HORSE_ABILITY_NAME)){
            if (!player.hasPermission(PermissionUtils.hermesHorseAbility)){
                player.sendMessage(PermissionUtils.ridingPermissionMessage);
                return;
            }
            Horse horse = (Horse) player.getVehicle();
            String metadataValue = plugin.getEntityMetadata(horse, HermesWhistleListener.HORSE_KEY);
            if ("true".equals(metadataValue)){
                if (player.getGameMode() == GameMode.SURVIVAL){
                    item.setAmount(item.getAmount() - 1);
                }
                // Apply levitation effect to the horse
                horse.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, duration, 3)); // 100 ticks = 5 seconds
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, duration, 3)); // 100 ticks = 5 seconds
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (!horse.hasPotionEffect(PotionEffectType.SPEED)) {
                            this.cancel(); // Cancel the task if the horse no longer has the speed effect
                            return;
                        }
                        // Spawn particles at the horse's location
                        horse.getWorld().spawnParticle(Particle.CLOUD, horse.getLocation().add(0, 1, 0), 10, 0.5, 0.5, 0.5, 0.02);
                    }
                }.runTaskTimer(plugin, 0, 5); // Run task every 5 ticks (0.25 seconds)
            }
        }
    }
}
