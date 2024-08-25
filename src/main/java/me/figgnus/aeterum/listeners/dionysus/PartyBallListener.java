package me.figgnus.aeterum.listeners.dionysus;

import me.figgnus.aeterum.Plugin;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.utils.PermissionUtils;
import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

public class PartyBallListener implements Listener {
    private final Plugin plugin;
    private NamespacedKey throwableKey;

    public PartyBallListener(Plugin plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onEntityHit(ProjectileHitEvent event){
        if (event.getEntity() instanceof Snowball snowball){
            if (snowball.getShooter() instanceof  Player player){
                ItemStack item = snowball.getItem();
                if (ItemUtils.isCustomItem(item, CustomItems.PARTY_BALL.getItemMeta().getCustomModelData())){
                    if (!player.hasPermission(PermissionUtils.dionysusPartyBall)){
                        player.sendMessage(PermissionUtils.permissionItemMessage);
                        return;
                    }
                    Entity hitEntity = event.getHitEntity();
                    Block hitBlock = event.getHitBlock();

                    Location hitLocation;
                    if (hitEntity != null) {
                        hitLocation = hitEntity.getLocation();
                    } else if (hitBlock != null) {
                        hitLocation = hitBlock.getLocation();
                    } else {
                        return; // No valid hit location
                    }
                    // Adjust the Y-coordinate to spawn fireworks above the hit location
                    hitLocation.setY(hitLocation.getY() + 1);
                    // Spawn and launch fireworks upwards
                    for (int i = 0; i < 5; i++) {
                        Firework firework = (Firework) hitLocation.getWorld().spawnEntity(hitLocation, EntityType.FIREWORK_ROCKET);
                        FireworkMeta fireworkMeta = firework.getFireworkMeta();
                        fireworkMeta.addEffect(FireworkEffect.builder().withColor(org.bukkit.Color.RED, org.bukkit.Color.GREEN, org.bukkit.Color.BLUE).flicker(true).build());
                        fireworkMeta.setPower(2); // Set the power of the firework (1 is a good default)
                        firework.setFireworkMeta(fireworkMeta);
                    }
                }
            }
        }
    }
}
