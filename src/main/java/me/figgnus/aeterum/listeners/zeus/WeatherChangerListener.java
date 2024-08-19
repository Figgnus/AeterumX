package me.figgnus.aeterum.listeners.zeus;

import me.figgnus.aeterum.Plugin;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.utils.GodUtils;
import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class WeatherChangerListener implements Listener {
    private final Plugin plugin;
    private int ticks = 1200; // 1200 is 1 minute

    public WeatherChangerListener(Plugin plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onPlayerConsume(PlayerItemConsumeEvent event){
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        if (ItemUtils.isCustomItem(item, CustomItems.WEATHER_CHANGER.getItemMeta().getCustomModelData())){
            if (!player.hasPermission(GodUtils.zeusPermission)){
                player.sendMessage(GodUtils.permissionItemMessage);
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
