package me.figgnus.aeterum.listeners.dionysus;

import com.dre.brewery.BPlayer;
import me.figgnus.aeterum.Plugin;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.utils.GodUtils;
import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;


public class PartyAtmosphereListener implements Listener {
    private final Plugin plugin;


    public PartyAtmosphereListener(Plugin plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onPlayerConsume(PlayerItemConsumeEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (ItemUtils.isCustomItem(item, CustomItems.PARTY_ATMOSPHERE.getItemMeta().getCustomModelData())){
            if (!player.hasPermission(GodUtils.dionysusPermission)){
                player.sendMessage(GodUtils.permissionItemMessage);
                return;
            }
            BPlayer bPlayer = BPlayer.get(player);
            if (bPlayer == null){
                bPlayer = new BPlayer(player.getUniqueId().toString());
            }
            int drunkenness = bPlayer.getDrunkeness() + 10;
            //player.performCommand("brew " + player.getName() + " " + drunkenness);
            String command = "brew " + player.getName() + " " + drunkenness;
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);

            // Apply effects based on the new drunkenness level
            applyEffect(player);
        }
    }

    private void applyEffect(Player player) {
        new PotionEffectTask(player).runTaskTimer(plugin, 0, 20);
    }
    private static class PotionEffectTask extends BukkitRunnable {
        private final Player player;
        private int iterations = 0;
        private final int radius = 10;

        public PotionEffectTask(Player player) {
            this.player = player;
        }

        @Override
        public void run() {
            if (iterations >= 60) { // Stop after 1 minute (12 * 5 seconds)
                cancel();
                return;
            }
            for (Player nearbyPlayer : player.getWorld().getPlayers()) {
                if (nearbyPlayer.getLocation().distance(player.getLocation()) <= radius) {
                    nearbyPlayer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 1));
                    nearbyPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 100, 1));
                    nearbyPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 1));
                    nearbyPlayer.getWorld().spawnParticle(Particle.HAPPY_VILLAGER, nearbyPlayer.getLocation(), 30, 0.5, 0.5, 0.5, 0);
                }
            }
            spawnParticles(player.getLocation());
            iterations++;
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
