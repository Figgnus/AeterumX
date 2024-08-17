package me.figgnus.aeterum.listeners.hermes;

import me.figgnus.aeterum.Plugin;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.utils.GodUtils;
import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.GameMode;
import org.bukkit.Particle;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class SpeedHorseAbilityListener implements Listener {
    private final Plugin plugin;

    public SpeedHorseAbilityListener(Plugin plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (ItemUtils.isCustomItem(item, CustomItems.SPEED_HORSE_ABILITY_ID)){
            if (!player.hasPermission(GodUtils.hermesPermission)){
                player.sendMessage(GodUtils.permissionItemMessage);
                return;
            }
            Horse horse = (Horse) player.getVehicle();
            String metadataValue = plugin.getEntityMetadata(horse, SpeedHorseTameListener.SPEED_KEY);
            if ("true".equals(metadataValue)){
                if (player.getGameMode() == GameMode.SURVIVAL){
                    item.setAmount(item.getAmount() - 1);
                }
                // Apply levitation effect to the horse
                horse.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 3)); // 100 ticks = 5 seconds
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 3)); // 100 ticks = 5 seconds
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
