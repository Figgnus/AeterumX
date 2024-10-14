package me.figgnus.aeterum.listeners.hades;

import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.items.OraxenItems;
import me.figgnus.aeterum.utils.PermissionUtils;
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
    public void onPlayerUse(PlayerInteractEvent event) {
     ItemStack item = event.getItem();
        if (ItemUtils.isCustomOraxenItem(item, OraxenItems.DARK_PORTAL_NAME)) {
            if (!event.getPlayer().hasPermission(PermissionUtils.hadesPortal)) {
                event.getPlayer().sendMessage(PermissionUtils.permissionItemMessage);
                event.setCancelled(true);
            }
        }
    }


    @EventHandler
    public void onPotionSplash(ProjectileHitEvent event) {
        if (!(event.getEntity() instanceof ThrownPotion)){
            return;
        }
        ThrownPotion potion = (ThrownPotion) event.getEntity();
        if (!(potion.getShooter() instanceof Player)) return;

        Player player = (Player) potion.getShooter();
        ItemStack item = potion.getItem();
        if (ItemUtils.isCustomOraxenItem(item, OraxenItems.DARK_PORTAL_NAME)) {
            if (!player.hasPermission(PermissionUtils.hadesPortal)) {
                player.sendMessage(PermissionUtils.permissionItemMessage);
                return;
            }
            Location hitLocation = event.getHitBlock() != null ? event.getHitBlock().getLocation() : event.getHitEntity().getLocation();
            Location portalLocation = hitLocation.clone().add(0, 1, 0);
            createPortal(portalLocation);
            player.getWorld().playSound(portalLocation, Sound.BLOCK_PORTAL_AMBIENT, 1.0F, 1.0F);
        }
    }
    @EventHandler
    public void onItemThrow(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (ItemUtils.isCustomOraxenItem(item, OraxenItems.DARK_PORTAL_NAME)){

            if (player.getWorld().getName().equals("newgen_world_the_end")){
                player.sendMessage(ChatColor.RED + "Tento předmět nemůže být použit v Endu.");
                event.setCancelled(true);
            }
        }
    }

    public void createPortal(Location loc) {
        World world = loc.getWorld();

        // Store the reference to the teleportation task so we can cancel it later
        BukkitRunnable teleportTask = new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : world.getPlayers()) {
                    if (isPlayerInPortal(player, loc)) {
                        Location targetLocation;

                        if (world.getName().equals("newgen_world")) {
                            // If in Overworld, teleport to Nether
                            targetLocation = findSafeNetherLocation(new Location(Bukkit.getWorld("newgen_world_nether"), loc.getX() / 8, loc.getY(), loc.getZ() / 8));
                        } else if (world.getName().equals("newgen_world_nether")) {
                            // If in Nether, teleport to Overworld
                            targetLocation = findSafeOverworldLocation(new Location(Bukkit.getWorld("newgen_world"), loc.getX() * 8, loc.getY(), loc.getZ() * 8));
                        } else {
                            continue; // Not in Overworld or Nether, skip teleportation
                        }

                        if (targetLocation != null) {
                            player.teleport(targetLocation);
                            player.getWorld().playSound(targetLocation, Sound.BLOCK_PORTAL_TRAVEL, 1.0F, 1.0F);
                            player.playSound(player.getLocation(), Sound.BLOCK_PORTAL_TRAVEL, 1.0F, 1.0F);
                        } else {
                            player.sendMessage(ChatColor.RED + "A safe location could not be found!");
                        }
                    }
                }
            }
        };

        // Run teleportation task every tick
        teleportTask.runTaskTimer(plugin, 0, 1);

        // Create the rectangular portal (2 blocks wide, 3 blocks tall)
        new BukkitRunnable() {
            int ticks = 0;
            double angle = 0;

            @Override
            public void run() {
                if (ticks >= 200) { // 10 seconds (20 ticks per second)
                    // Cancel the teleportation task when the portal expires
                    teleportTask.cancel();
                    cancel(); // Stop particle task
                    return;
                }

                // Adjust angle and increment over time to rotate particles
                angle += Math.PI / 16; // Adjust angle increment for speed of the vortex
                double radius = 1.5;   // Radius of the vortex

                // Vortex 1: rotating clockwise
                for (int y = 0; y < 3; y++) {  // Control the height of the vortex
                    // Calculate particle positions using polar coordinates (circular motion)
                    double x1 = radius * Math.cos(angle + y);  // X position rotates
                    double z1 = radius * Math.sin(angle + y);  // Z position rotates
                    Location vortexLocation1 = loc.clone().add(x1, y, z1);  // Y controls height

                    // Spawn particles at calculated location for Vortex 1
                    spawnParticleAt(world, vortexLocation1);
                }

                // Vortex 2: rotating counterclockwise (by reversing the angle)
                for (int y = 0; y < 3; y++) {  // Control the height of the vortex
                    // Reverse the angle for the second vortex to spin the opposite way
                    double x2 = radius * Math.cos(-angle + y);  // X position rotates in reverse
                    double z2 = radius * Math.sin(-angle + y);  // Z position rotates in reverse
                    Location vortexLocation2 = loc.clone().add(x2, y, z2);  // Y controls height

                    // Spawn particles at calculated location for Vortex 2
                    spawnParticleAt(world, vortexLocation2);
                }

                ticks++;
            }
        }.runTaskTimer(plugin, 0, 1);

        // Remove portal and stop teleporting after 10 seconds
        new BukkitRunnable() {
            @Override
            public void run() {
                // Cancel the teleportation task when the portal disappears
                teleportTask.cancel();
            }
        }.runTaskLater(plugin, 200); // 10 seconds (20 ticks per second * 10)
    }
    private void spawnParticleAt(World world, Location location) {
        world.spawnParticle(Particle.PORTAL, location, 10, 0, 0, 0, 0);
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
            for (int y = 32; y <= 127; y++) { // Check from y=0 to y=127
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
    private Location findSafeOverworldLocation(Location loc) {
        World overworld = loc.getWorld();
        int startX = loc.getBlockX();
        int startZ = loc.getBlockZ();
        int initialRadius = 3; // Starting radius
        int maxRadius = 50; // Define a maximum radius limit to avoid infinite loops

        // Iterate over radius values starting from initialRadius
        for (int radius = initialRadius; radius <= maxRadius; radius++) {
            // Loop to find a safe location by checking y-coordinates first
            for (int y = 255; y >= 0; y--) { // Check from y=255 to y=0
                for (int x = startX - radius; x <= startX + radius; x++) {
                    for (int z = startZ - radius; z <= startZ + radius; z++) {
                        Location candidateLocation = new Location(overworld, x, y, z);

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