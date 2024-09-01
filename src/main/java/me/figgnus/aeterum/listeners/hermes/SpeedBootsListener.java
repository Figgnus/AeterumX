package me.figgnus.aeterum.listeners.hermes;

import me.figgnus.aeterum.AeterumX;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.utils.PermissionUtils;
import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.UUID;

public class SpeedBootsListener implements Listener {
    private final AeterumX plugin;
    private static final HashMap<UUID, Long> messageCooldowns = new HashMap<>();
    private static final long COOLDOWN_TIME = 5000;

    public SpeedBootsListener(AeterumX plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getBoots();

        if (ItemUtils.isCustomItem(item, CustomItems.SPEED_BOOTS.getItemMeta().getCustomModelData())){
            if (!(player.hasPermission(PermissionUtils.hermesSpeedBoots))){
                long currentTime = System.currentTimeMillis();
                UUID playerUUID = player.getUniqueId();
                if (!messageCooldowns.containsKey(playerUUID) || (currentTime - messageCooldowns.get(playerUUID) > COOLDOWN_TIME)){
                    player.sendMessage(PermissionUtils.permissionItemMessage);
                    messageCooldowns.put(playerUUID, currentTime);
                }
                return;
            }
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 2, false));
        }
    }
}
