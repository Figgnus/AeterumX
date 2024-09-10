package me.figgnus.aeterum.listeners.zeus;

import me.figgnus.aeterum.AeterumX;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.utils.PermissionUtils;
import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class WeatherChangerListener implements Listener {
    private final AeterumX plugin;
    private int ticks = 1200; // 1200 is 1 minute

    public WeatherChangerListener(AeterumX plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onItemUse(PlayerInteractEvent event){
        ItemStack item = event.getItem();
        if (ItemUtils.isCustomItem(item, CustomItems.WEATHER_CHANGER.getItemMeta().getCustomModelData())){
            if (!event.getPlayer().hasPermission(PermissionUtils.zeusWeatherChanger)){
                event.getPlayer().sendMessage(PermissionUtils.permissionItemMessage);
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onPlayerConsume(PlayerItemConsumeEvent event){
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        if (ItemUtils.isCustomItem(item, CustomItems.WEATHER_CHANGER.getItemMeta().getCustomModelData())){
            if (!player.hasPermission(PermissionUtils.zeusWeatherChanger)){
                player.sendMessage(PermissionUtils.permissionItemMessage);
                return;
            }
            boolean wasStormy = player.getWorld().hasStorm();
            player.getWorld().setStorm(true);
            player.sendMessage("Zvedá se bouře na dalších " + ticks / 20 + " sekund.");
            // Schedule a task to revert the weather after 1 minute (1200 ticks)
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.getWorld().setStorm(wasStormy);
                    player.sendMessage("Počasí se vrací do normálu.");
                }
            }.runTaskLater(plugin, ticks);
        }
    }
}
