package me.figgnus.aeterum.listeners.demeter;

import me.figgnus.aeterum.AeterumX;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.items.OraxenItems;
import me.figgnus.aeterum.utils.PermissionUtils;
import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class BetterBonemealListener implements Listener {
    private final AeterumX plugin;
    private final HashMap<UUID, Long> cooldowns = new HashMap<>();
    private static final int COOLDOWN_TIME = 100;

    public BetterBonemealListener(AeterumX pligin) {
        this.plugin = pligin;

        pligin.getServer().getPluginManager().registerEvents(this, pligin);
    }
    @EventHandler
    private void onBlockRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (ItemUtils.isCustomOraxenItem(item, OraxenItems.BETTER_BONEMEAL_NAME)){
            if (!player.hasPermission(PermissionUtils.demeterBetterBonemeal)){
                player.sendMessage(PermissionUtils.permissionItemMessage);
                event.setCancelled(true);
                return;
            }
            long currentTime = System.currentTimeMillis();
            if (cooldowns.containsKey(player.getUniqueId())) {
                long lastUseTime = cooldowns.get(player.getUniqueId());
                if (currentTime - lastUseTime < COOLDOWN_TIME) {
                    return;
                }
            }
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK){
                Block block = event.getClickedBlock();
                if (block != null && (block.getType() == Material.CACTUS || block.getType() == Material.SUGAR_CANE)) {
                    Block above = block.getRelative(BlockFace.UP);
                    if (above.getType() == Material.AIR) {
                        // Grow cactus or sugar cane
                        above.setType(block.getType());
                        spawnGrowthParticle(above.getLocation().add(0.5, 0.5, 0.5));
                        playSound(above.getLocation());
                        if (player.getGameMode() == GameMode.SURVIVAL) {
                            event.getItem().setAmount(event.getItem().getAmount() - 1);
                        }
                        cooldowns.put(player.getUniqueId(), currentTime);
                    }
                }
            }
        }
    }
    private void spawnGrowthParticle(Location location) {
        location.getWorld().spawnParticle(Particle.HAPPY_VILLAGER, location, 30, 0.5, 0.5, 0.5, 0);
    }
    private void playSound(Location location){
        location.getWorld().playSound(location, Sound.BLOCK_COMPOSTER_FILL, 1.0F, 1.0F);
    }
}
