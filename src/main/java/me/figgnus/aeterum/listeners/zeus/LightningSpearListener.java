package me.figgnus.aeterum.listeners.zeus;

import me.figgnus.aeterum.Plugin;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.utils.GodUtils;
import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public class LightningSpearListener implements Listener {
    private final Plugin plugin;

    public LightningSpearListener(Plugin plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onEntityHit(ProjectileHitEvent event){
        if (event.getEntity() instanceof Trident trident){
            if (trident.getShooter() instanceof Player player){
                ItemStack item = trident.getItem();
                if (ItemUtils.isCustomItem(item, CustomItems.LIGHTNING_SPEAR.getItemMeta().getCustomModelData())){
                    if (!player.hasPermission(GodUtils.zeusLightningSpear)){
                        player.sendMessage(GodUtils.permissionItemMessage);
                        return;
                    }
                    if (event.getHitEntity() != null){
                        Entity hitEntity = event.getHitEntity();
                        hitEntity.getWorld().strikeLightning(hitEntity.getLocation());
                    } else if (event.getHitBlock() != null) {
                        trident.getWorld().strikeLightning(event.getHitBlock().getLocation());
                    }
                }
            }
        }
    }
}
