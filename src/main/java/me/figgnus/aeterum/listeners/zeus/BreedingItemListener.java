package me.figgnus.aeterum.listeners.zeus;

import me.figgnus.aeterum.AeterumX;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.items.OraxenItems;
import me.figgnus.aeterum.utils.PermissionUtils;
import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.GameMode;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Animals;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class BreedingItemListener implements Listener {
    private final AeterumX plugin;
    private final HashMap<UUID, Long> cooldown = new HashMap<>();
    private static final long COOLDOWN_TIME = 1000; // Cooldown time in milliseconds (1 second)

    public BreedingItemListener(AeterumX plugin) {
        this.plugin = plugin;


        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void  onPlayerInteract(PlayerInteractEntityEvent event){
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        UUID playerId = player.getUniqueId();
        if (ItemUtils.isCustomOraxenItem(itemInHand, OraxenItems.BREEDING_ITEM_NAME)){
            if (!player.hasPermission(PermissionUtils.zeusBreedingItem)){
                player.sendMessage(PermissionUtils.permissionItemMessage);
                event.setCancelled(true);
                return;
            }
            // Check cooldown
            if (cooldown.containsKey(playerId) && (System.currentTimeMillis() - cooldown.get(playerId)) < COOLDOWN_TIME) {
                return;
            }
            if (event.getRightClicked() instanceof Animals) {
                Animals animal = (Animals) event.getRightClicked();
                EntityType type = animal.getType();

                if (!animal.isAdult()) {
                    return;
                }
                player.getWorld().spawnParticle(Particle.HEART, animal.getLocation().add(0, 1, 0), 30);

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Animals baby = (Animals) animal.getWorld().spawnEntity(animal.getLocation(), type);
                        baby.setBaby();
                        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                        if (player.getGameMode() == GameMode.SURVIVAL) {
                            if (itemInHand.getAmount() == 1) {
                                player.getInventory().setItemInMainHand(null);
                            } else {
                                itemInHand.setAmount(itemInHand.getAmount() - 1);
                            }
                        }
                    }
                }.runTaskLater(plugin, 20L * 2); //2 seconds later


                // Update the cooldown
                cooldown.put(playerId, System.currentTimeMillis());
                event.setCancelled(true);
            }
        }
    }
}
