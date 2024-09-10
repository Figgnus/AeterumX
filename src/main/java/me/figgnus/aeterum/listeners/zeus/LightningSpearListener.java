package me.figgnus.aeterum.listeners.zeus;

import me.figgnus.aeterum.AeterumX;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.utils.PermissionUtils;
import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class LightningSpearListener implements Listener {
    private final AeterumX plugin;

    public LightningSpearListener(AeterumX plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onEntityHit(ProjectileHitEvent event){
        if (event.getEntity() instanceof Trident trident){
            if (trident.getShooter() instanceof Player player){
                ItemStack item = trident.getItem();
                if (ItemUtils.isCustomItem(item, CustomItems.LIGHTNING_SPEAR.getItemMeta().getCustomModelData())){
                    if (!player.hasPermission(PermissionUtils.zeusLightningSpear)){
                        player.sendMessage(PermissionUtils.permissionItemMessage);
                        return;
                    }
                    // Check if it hits an entity first
                    if (event.getHitEntity() != null) {
                        Entity hitEntity = event.getHitEntity();
                        hitEntity.getWorld().strikeLightning(hitEntity.getLocation());

                        // Add metadata to prevent lightning strike on block hit
                        trident.setMetadata("lightning_summoned", new FixedMetadataValue(plugin, true));
                    }
                    // If it hits a block, check if lightning was already summoned
                    else if (event.getHitBlock() != null && !trident.hasMetadata("lightning_summoned")) {
                        trident.getWorld().strikeLightning(event.getHitBlock().getLocation());
                    }
                }
            }
        }
    }
}
