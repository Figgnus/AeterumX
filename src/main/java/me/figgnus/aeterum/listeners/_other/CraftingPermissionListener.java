package me.figgnus.aeterum.listeners._other;

import me.figgnus.aeterum.AeterumX;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

public class CraftingPermissionListener implements Listener {
    private final AeterumX plugin;

    private String message = "§cNemáš oprávnění vytvořit tento předmět!";

    public CraftingPermissionListener(AeterumX plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this , plugin);
    }

    @EventHandler
    public void onPrepareCraft(PrepareItemCraftEvent event) {
        CraftingInventory inventory = event.getInventory();
        ItemStack result = inventory.getResult();
        if (result == null || !result.hasItemMeta() || !result.getItemMeta().hasCustomModelData() ) {
            return;
        }
        int itemId = result.getItemMeta().getCustomModelData();
        Player player = (Player) event.getView().getPlayer();
        // Check all god's permissions for the crafted item
        for (String item : plugin.getConfig().getConfigurationSection("permissions").getKeys(false)) {
            int customId = plugin.getConfig().getInt("permissions." + item + ".custom_id");
            if (customId == itemId) {
                String permission = plugin.getConfig().getString("permissions." + item + ".permission");
                if (permission != null && !player.hasPermission(permission)) {
                    denyCrafting(inventory, player);
                    return;
                }
            }
        }
    }

    private void denyCrafting(CraftingInventory inventory, Player player) {
        inventory.setResult(new ItemStack(Material.AIR)); // Clear the result to prevent crafting
        player.sendMessage(message);
    }
}
