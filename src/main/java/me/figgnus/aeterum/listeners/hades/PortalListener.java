package me.figgnus.aeterum.listeners.hades;

import me.figgnus.aeterum.Plugin;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.utils.GodUtils;
import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class PortalListener implements Listener {
    private final Plugin plugin;

    public PortalListener(Plugin plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onPlayerThrow(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (ItemUtils.isCustomItem(item, CustomItems.DARK_PORTAL_ID)){
            if (!player.hasPermission(GodUtils.hadesPermission)){
                player.sendMessage(GodUtils.permissionItemMessage);
                return;
            }
            Location loc = player.getLocation();
            createPortal(loc);
            player.sendMessage(ChatColor.GOLD + "Přemísťuješ se na nové místo. Zůstaň stát.");
            player.getWorld().playSound(loc, Sound.BLOCK_PORTAL_TRAVEL, 1.0F, 1.0F);
        }
    }

    public void createPortal(Location loc) {
        World world = loc.getWorld();

        // Create the oval portal
        new BukkitRunnable() {
            int ticks = 0;
            @Override
            public void run() {
                if (ticks >= 100) { // 5 seconds (20 ticks per second)
                    cancel();
                    return;
                }

                for (double y = 0; y <= 2; y += 0.05) {
                    double x = Math.sin(y * Math.PI);
                    double z = Math.cos(y * Math.PI);
                    world.spawnParticle(Particle.PORTAL, loc.clone().add(x, y, z), 1);
                    world.spawnParticle(Particle.PORTAL, loc.clone().add(-x, y, z), 1);
                }
                ticks++;
            }
        }.runTaskTimer(plugin, 0, 1);

        // Teleport players and remove the portal after a few seconds
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : world.getPlayers()) {
                    if (player.getLocation().distance(loc) <= 1) {
                        player.teleport(world.getEnvironment() == World.Environment.NETHER ? (Location) Bukkit.getWorld("world") : Bukkit.getWorld("world_nether").getSpawnLocation());
                    }
                }
                // Remove the portal particles (handled by canceling the particle task above)
            }
        }.runTaskLater(plugin, 100); // 5 seconds
    }
}
