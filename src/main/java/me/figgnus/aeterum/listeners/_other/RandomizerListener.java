package me.figgnus.aeterum.listeners._other;

import me.figgnus.aeterum.AeterumX;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.utils.ItemUtils;
import me.figgnus.aeterum.utils.PermissionUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class RandomizerListener implements Listener {
    private final AeterumX plugin;
    private final HashMap<UUID, Long> cooldowns = new HashMap<>();

    public RandomizerListener(AeterumX plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        // Check if the player right-clicked
        if (event.getAction().name().startsWith("RIGHT_CLICK")) {
            Player player = event.getPlayer();

            // Check if the player has the randomizer item in their offhand
            ItemStack offHandItem = player.getInventory().getItemInOffHand();
            if (ItemUtils.isCustomItem(offHandItem, CustomItems.RANDOMIZER.getItemMeta().getCustomModelData())) {

                if (!player.hasPermission(PermissionUtils.randomizerPermission)) {
                    player.sendMessage(PermissionUtils.permissionItemMessage);
                    return;
                }
                // Collect hotbar slots that contain blocks
                List<Integer> blockSlots = new ArrayList<>();
                for (int i = 0; i < 9; i++) {
                    ItemStack item = player.getInventory().getItem(i);
                    if (item != null && item.getType().isBlock()) {
                        blockSlots.add(i);
                    }
                }

                if (!blockSlots.isEmpty()) {
                    // Pick a random slot from the list of block slots
                    Random random = new Random();
                    int randomIndex = random.nextInt(blockSlots.size());
                    int selectedSlot = blockSlots.get(randomIndex);

                    // Set the selected hotbar slot
                    player.getInventory().setHeldItemSlot(selectedSlot);
                }
            }
        }
    }
}
