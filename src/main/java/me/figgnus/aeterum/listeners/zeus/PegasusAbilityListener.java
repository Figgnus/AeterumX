package me.figgnus.aeterum.listeners.zeus;

import me.figgnus.aeterum.AeterumX;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.items.OraxenItems;
import me.figgnus.aeterum.listeners.hermes.HermesWhistleListener;
import me.figgnus.aeterum.utils.PermissionUtils;
import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class PegasusAbilityListener implements Listener {
    private final AeterumX plugin;
    private int duration = 300;

    public PegasusAbilityListener(AeterumX plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        // Check if the player is riding a horse
        if (player.isInsideVehicle() && player.getVehicle() instanceof Horse) {
            Horse horse = (Horse) player.getVehicle();
            String metadataValue = plugin.getEntityMetadata(horse, ZeusWhistleListener.HORSE_KEY);

            // Check if the horse has the Seed  ability
            if ("true".equals(metadataValue)) {

                Location horseLocation = horse.getLocation();
                horseLocation.getWorld().spawnParticle(Particle.ENCHANT, horseLocation.clone().add(0, 0.5, 0), 1, 0.2, 0.05, 0.2, 0.01);
                horseLocation.getWorld().spawnParticle(Particle.CLOUD, horseLocation.clone().add(0, 0.5, 0), 1, 0.2, 0.05, 0.2, 0.01);
            }
        }
    }
    @EventHandler
    public void onItemUse(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (ItemUtils.isCustomOraxenItem(item, OraxenItems.PEGASUS_HORSE_ABILITY_NAME)){
            if (!event.getPlayer().hasPermission(PermissionUtils.zeusHorseAbility)){
                event.getPlayer().sendMessage(PermissionUtils.permissionItemMessage);
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (ItemUtils.isCustomOraxenItem(item, OraxenItems.PEGASUS_HORSE_ABILITY_NAME)){
            if (!player.hasPermission(PermissionUtils.zeusHorseAbility)){
                player.sendMessage(PermissionUtils.ridingPermissionMessage);
                event.setCancelled(true);
                return;
            }
            Horse horse = (Horse) player.getVehicle();
            String metadataValue = plugin.getEntityMetadata(horse, ZeusWhistleListener.HORSE_KEY);
            if ("true".equals(metadataValue)){
                if (player.getGameMode() == GameMode.SURVIVAL){
                    item.setAmount(item.getAmount() - 1);
                }
                // Apply levitation effect to the horse
                horse.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, duration, 1)); // 100 ticks = 5 seconds
                player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, duration, 1)); // 100 ticks = 5 seconds
                horse.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, duration, 25));

                // Schedule a task to apply fall damage immunity after Levitation ends
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        // Check if the horse and player still have the levitation effect
                        if (!horse.hasPotionEffect(PotionEffectType.LEVITATION)) {
                            // Apply fall damage immunity to the horse and player
                            horse.setMetadata("fallDamageImmune", new FixedMetadataValue(plugin, true));
                            player.setMetadata("fallDamageImmune", new FixedMetadataValue(plugin, true));

                            // Remove fall damage immunity after a duration
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    horse.removeMetadata("fallDamageImmune", plugin);
                                    player.removeMetadata("fallDamageImmune", plugin);
                                }
                            }.runTaskLater(plugin, 100); // 100 ticks = 5 seconds
                        }
                    }
                }.runTaskLater(plugin, getRemainingLevitationDuration(player) + 1); // Schedule the task after levitation duration ends
                // Schedule a repeating task to spawn particles while levitating
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (!horse.hasPotionEffect(PotionEffectType.LEVITATION)) {
                            this.cancel(); // Cancel the task if the horse is no longer levitating
                            return;
                        }
                        // Spawn particles at the horse's location
                        horse.getWorld().spawnParticle(Particle.CLOUD, horse.getLocation().add(0, 1, 0), 10, 0.5, 0.5, 0.5, 0.05);
                    }
                }.runTaskTimer(plugin, 0, 5); // Run task every 5 ticks (0.25 seconds)
            }
        }
    }
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            Entity entity = event.getEntity();
            if (entity.hasMetadata("fallDamageImmune")) {
                event.setCancelled(true); // Cancel the fall damage
            }
        }
    }
    private long getRemainingLevitationDuration(Player player) {
        for (PotionEffect effect : player.getActivePotionEffects()) {
            if (effect.getType().equals(PotionEffectType.LEVITATION)) {
                return effect.getDuration();
            }
        }
        return 0;
    }
}
