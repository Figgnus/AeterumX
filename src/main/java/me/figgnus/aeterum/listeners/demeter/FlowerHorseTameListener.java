package me.figgnus.aeterum.listeners.demeter;

import me.figgnus.aeterum.Plugin;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.utils.GodUtils;
import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class FlowerHorseTameListener implements Listener {
    private final Plugin plugin;
    private final String METADATA_KEY = "DemeterFeed";
    public static final String SEED_KEY = "Seed";
    Random random = new Random();

    public FlowerHorseTameListener(Plugin plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Horse) {
            Horse horse = (Horse) event.getRightClicked();
            Player player = event.getPlayer();
            ItemStack item = player.getInventory().getItemInMainHand();

            if (ItemUtils.isCustomItem(item, CustomItems.FLOWER_HORSE_TAME.getItemMeta().getCustomModelData())){
                if (!player.hasPermission(GodUtils.demeterPermission)) {
                    player.sendMessage(GodUtils.permissionItemMessage);
                    return;
                }
                // Consume one item from the stack
                if (player.getGameMode() == GameMode.SURVIVAL){
                    item.setAmount(item.getAmount() - 1);
                }
                // Set metadata to indicate the horse has been fed the special item
                plugin.setEntityMetadata(horse, METADATA_KEY, "true");
                // Set metadata to indicate the horse has frost walker ability
                plugin.setEntityMetadata(horse, SEED_KEY, "true");

                player.sendMessage(GodUtils.horseTameMessage);
            }
        }
    }
    @EventHandler
    public void onEntityTame(EntityTameEvent event) {
        if (event.getEntity() instanceof Horse) {
            Horse horse = (Horse) event.getEntity();
            Player player = (Player) event.getOwner();

            String metadataValue = plugin.getEntityMetadata(horse, METADATA_KEY);

            // Check if the horse has been fed the special item
            if ("true".equals(metadataValue)) {
                double speed = random.nextDouble(0.3, 0.3375);
                double jump = random.nextDouble(0.9, 1.1);
                int health = random.nextInt(25, 30);

                // Change horse appearance and stats
                horse.setColor(Horse.Color.GRAY);
                horse.setOwner(player);

                // Set horse stats
                horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed); // Fast speed
                horse.getAttribute(Attribute.GENERIC_JUMP_STRENGTH).setBaseValue(jump); // High jump
                horse.setMaxHealth(health);
                horse.setHealth(health);

                player.sendMessage(GodUtils.horseTransformMessage);
            }
        }
    }
}
