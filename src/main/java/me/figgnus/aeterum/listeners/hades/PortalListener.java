package me.figgnus.aeterum.listeners.hades;

import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.utils.GodUtils;
import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class PortalListener implements Listener {
    private final Plugin plugin;

    public PortalListener(Plugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPotionSplash(ProjectileHitEvent event) {

        ThrownPotion potion = (ThrownPotion) event.getEntity();
        if (!(potion.getShooter() instanceof Player)) return;

        Player player = (Player) potion.getShooter();
        ItemStack item = potion.getItem();
        if (ItemUtils.isCustomItem(item, CustomItems.DARK_PORTAL.getItemMeta().getCustomModelData())) {
            if (!player.hasPermission(GodUtils.hadesPermission)) {
                player.sendMessage(GodUtils.permissionItemMessage);
                return;
            }
            Location hitLocation = event.getHitBlock() != null ? event.getHitBlock().getLocation() : event.getHitEntity().getLocation();
            createPortal(hitLocation);
            player.getWorld().playSound(hitLocation, Sound.BLOCK_PORTAL_AMBIENT, 1.0F, 1.0F);
        }
    }
    @EventHandler
    public void onItemThrow(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (ItemUtils.isCustomItem(item, CustomItems.DARK_PORTAL.getItemMeta().getCustomModelData())){

            if (player.getWorld().getName().equals("world_nether")){
                player.sendMessage(ChatColor.RED + "Tento předmět nemůže být použit v Netheru.");
                event.setCancelled(true);
            }
        }
    }

    public void createPortal(Location loc) {
        World world = loc.getWorld();

        // Create the rectangular portal (2 blocks wide, 3 blocks tall)
        new BukkitRunnable() {
            int ticks = 0;

            @Override
            public void run() {
                if (ticks >= 200) { // 10 seconds (20 ticks per second)
                    cancel();
                    return;
                }

                // Generate the portal frame particles
                for (int y = 0; y <= 2; y++) {
                    for (int x = -1; x <= 1; x++) {
                        world.spawnParticle(Particle.PORTAL, loc.clone().add(x, y, 0), 10);
                        world.spawnParticle(Particle.PORTAL, loc.clone().add(x, y, -1), 10);
                        world.spawnParticle(Particle.PORTAL, loc.clone().add(x, y + 1, 0), 10);
                        world.spawnParticle(Particle.PORTAL, loc.clone().add(x, y + 1, -1), 10);
                    }
                }

                ticks++;
            }
        }.runTaskTimer(plugin, 0, 1);

        // Teleport players who enter the portal
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : world.getPlayers()) {
                    if (isPlayerInPortal(player, loc)) {
                        Location netherLocation = findSafeNetherLocation(new Location(Bukkit.getWorld("world_nether"), loc.getX() / 8, loc.getY(), loc.getZ() / 8));
                        if (netherLocation != null) {
                            player.teleport(netherLocation);
                            player.getWorld().playSound(netherLocation, Sound.BLOCK_PORTAL_TRAVEL, 1.0F, 1.0F);
                            player.playSound(player.getLocation(), Sound.BLOCK_PORTAL_TRAVEL, 1.0F, 1.0F);
                        } else {
                            player.sendMessage(ChatColor.RED + "A safe location could not be found in the Nether!");
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 1); // Check every tick

        // Remove portal and stop teleporting after 10 seconds
        new BukkitRunnable() {
            @Override
            public void run() {
                // Cancel the portal creation task
                this.cancel();
            }
        }.runTaskLater(plugin, 200); // 10 seconds (20 ticks per second * 10)
    }

    private boolean isPlayerInPortal(Player player, Location loc) {
        Location playerLocation = player.getLocation();
        Vector portalMin = loc.clone().add(-1, 0, -1).toVector(); // Bottom corner of portal
        Vector portalMax = loc.clone().add(1, 2, 0).toVector();   // Top corner of portal

        return playerLocation.toVector().isInAABB(portalMin, portalMax);
    }
    // Method to find a safe location in the Nether
    private Location findSafeNetherLocation(Location loc) {
        World nether = loc.getWorld();
        int startX = loc.getBlockX();
        int startZ = loc.getBlockZ();
        int initialRadius = 3; // Starting radius
        int maxRadius = 50; // Define a maximum radius limit to avoid infinite loops

        // Iterate over radius values starting from initialRadius
        for (int radius = initialRadius; radius <= maxRadius; radius++) {
            // Loop to find a safe location by checking y-coordinates first
            for (int y = 0; y <= 127; y++) { // Check from y=0 to y=127
                for (int x = startX - radius; x <= startX + radius; x++) {
                    for (int z = startZ - radius; z <= startZ + radius; z++) {
                        Location candidateLocation = new Location(nether, x, y, z);

                        // Check if the location is safe
                        if (isSafeLocation(candidateLocation)) {
                            return candidateLocation.add(0.5, 1, 0.5); // Center the player on the block
                        }
                    }
                }
            }
            // If no valid location is found within the current radius, increase the radius and retry
        }

        return null; // Return null if no safe location is found after all radius attempts
    }

    // Check if the location is safe for the player to stand
    private boolean isSafeLocation(Location loc) {
        World world = loc.getWorld();

        // Get blocks at the target location
        Block feetBlock = loc.getBlock();
        Block headBlock = loc.add(0, 1, 0).getBlock();
        Block groundBlock = loc.add(0, -2, 0).getBlock();

        // Check if the player's feet and head can fit (i.e., air) and the ground is solid
        return feetBlock.isPassable() && headBlock.isPassable() && groundBlock.getType().isSolid() && !groundBlock.getType().equals(Material.LAVA) && !feetBlock.getType().equals(Material.LAVA) && !headBlock.getType().equals(Material.LAVA);
    }
}
