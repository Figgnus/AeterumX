package me.figgnus.aeterum.listeners._other;

import me.figgnus.aeterum.AeterumX;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;


public class CustomTnTListener implements Listener {
    private final AeterumX plugin;

    private static final String TNT_METADATA_KEY = "custom_tnt";
    private static final int FUSE_TICKS = 80; // 4 seconds like vanilla TNT fuse time
    private static final float EXPLOSION_STRENGTH = 10.0F; // Customize the explosion strength

    public CustomTnTListener(AeterumX plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        // Detect custom TNT placement
        if (isCustomTNT(event.getItemInHand())) {
            event.getBlock().setMetadata(TNT_METADATA_KEY, new FixedMetadataValue(plugin, true));
            Bukkit.getLogger().info("Custom TNT placed at " + event.getBlock().getLocation());
        }
    }

    @EventHandler
    public void onBlockClick(BlockBreakEvent event) {
        if (event.getBlock().hasMetadata(TNT_METADATA_KEY)) {
            Player player  = event.getPlayer();
            player.sendMessage("You broke a custom TNT!");
        }
    }
    @EventHandler
    public void onBlockIgnite(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        ItemStack item = event.getItem();

        if (item == null || item.getType() != Material.FLINT_AND_STEEL) {
            return;
        }

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK || block == null) {
            return;
        }

        if (block.hasMetadata(TNT_METADATA_KEY)) {
            Location location = block.getLocation();

            // Ignite custom TNT
            igniteTNT(location, block);
            location.getWorld().playSound(location, Sound.ENTITY_TNT_PRIMED, 1.0F, 1.0F);

            // Schedule particles and cancellation after a certain amount of time (like fuse time)
            new BukkitRunnable() {
                int countdown = 80; // 4 seconds (80 ticks) fuse time like vanilla TNT

                @Override
                public void run() {
                    if (countdown <= 0) {
                        // Stop particles when countdown ends (similar to explosion time)
                        this.cancel();
                    } else {
                        // Show particles while TNT is primed
                        block.getWorld().spawnParticle(Particle.SMOKE, block.getLocation().add(0.5, 0.5, 0.5), 10, 0.3, 0.3, 0.3, 0);
                        block.getWorld().spawnParticle(Particle.FLAME, block.getLocation().add(0.5, 0.5, 0.5), 10, 0.3, 0.3, 0.3, 0);
                        countdown -= 8; // Reduce the countdown with each iteration (every 8 ticks)
                    }
                }
            }.runTaskTimer(plugin, 0L, 8L); // 0L = no initial delay, 8L = repeat every 8 ticks
        }
    }

    private void igniteTNT(Location location, Block block) {
        // Schedule TNT explosion after fuse delay (like vanilla TNT)
        new BukkitRunnable() {
            @Override
            public void run() {
                block.setType(Material.AIR);
                location.getWorld().createExplosion(location, EXPLOSION_STRENGTH);
            }
        }.runTaskLater(plugin, FUSE_TICKS); // 80 ticks = 4 seconds (vanilla TNT fuse)
    }

    private boolean isCustomTNT(ItemStack item) {
        return item != null && item.hasItemMeta() &&
                item.getItemMeta().hasCustomModelData() &&
                item.getItemMeta().getCustomModelData() == 60099; // Your custom TNT's model data
    }
}
