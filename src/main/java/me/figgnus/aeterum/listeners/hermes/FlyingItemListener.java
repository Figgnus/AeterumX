package me.figgnus.aeterum.listeners.hermes;

import me.figgnus.aeterum.AeterumX;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.utils.PermissionUtils;
import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class FlyingItemListener implements Listener {
    private final AeterumX plugin;

    public FlyingItemListener(AeterumX plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    private void onItemRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (ItemUtils.isCustomItem(item, CustomItems.FLYING_ITEM.getItemMeta().getCustomModelData())){
            if (!(player.hasPermission(PermissionUtils.hermesFlyingItem))){
                player.sendMessage(PermissionUtils.permissionItemMessage);
                event.setCancelled(true);
                return;
            }
            if (event.getAction() == Action.RIGHT_CLICK_AIR){
                ItemMeta meta = item.getItemMeta();
                Damageable damageable = (Damageable) meta;
                int currentDamage = damageable.getDamage();
                if (currentDamage == item.getType().getMaxDurability()){
                    return;
                }
                if (event.getPlayer().isGliding()) {
                    // Propel the player
                    Vector direction = event.getPlayer().getLocation().getDirection();
                    event.getPlayer().setVelocity(direction.multiply(1.5)); // Adjust the multiplier for speed

                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1f,1f);

                    // Reduce the durability of the item
                    adjustItemDurability(item, 1);
                }
            }
        }
    }
    private void adjustItemDurability(ItemStack item, int i) {
        // Check if the item has meta data
        if (item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();

            // Check if the meta data is an instance of Damageable
            if (meta instanceof Damageable damageable) {

                // Get current damage
                int currentDamage = damageable.getDamage();

                // Modify the damage (increase for wear, decrease for repair)
                damageable.setDamage(currentDamage + i);

                // Check for over-damage (optional, if you want to prevent breaking)
                if (damageable.getDamage() > item.getType().getMaxDurability()) {
                    damageable.setDamage(item.getType().getMaxDurability());
                }
                // Set the modified meta back to the item
                item.setItemMeta((ItemMeta) damageable);
            }
        }
    }
}
