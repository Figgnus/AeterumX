package me.figgnus.aeterum.listeners.poseidon;

import me.figgnus.aeterum.AeterumX;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.utils.ItemUtils;
import me.figgnus.aeterum.utils.PermissionUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SeaCrownListener implements Listener {
    private final AeterumX plugin;

    public SeaCrownListener(AeterumX plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        ItemStack item = event.getCurrentItem();
        if (ItemUtils.isCustomItem(item, CustomItems.WATER_BREATHING_CROWN.getItemMeta().getCustomModelData())){
            if (event.getAction() != InventoryAction.DROP_ONE_SLOT && event.getAction() != InventoryAction.DROP_ALL_SLOT){
                if (!event.getWhoClicked().hasPermission(PermissionUtils.poseidonCrown)){
                    event.getWhoClicked().sendMessage(PermissionUtils.permissionItemMessage);
                    event.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void onItemUse(PlayerInteractEvent event){
        ItemStack item = event.getItem();
        if (ItemUtils.isCustomItem(item, CustomItems.WATER_BREATHING_CROWN.getItemMeta().getCustomModelData())){
            if (!event.getPlayer().hasPermission(PermissionUtils.poseidonCrown)){
                event.getPlayer().sendMessage(PermissionUtils.permissionItemMessage);
                event.setCancelled(true);
            }
        }
    }
}
