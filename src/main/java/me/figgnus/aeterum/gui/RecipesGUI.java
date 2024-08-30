package me.figgnus.aeterum.gui;

import me.figgnus.aeterum.Plugin;
import me.figgnus.aeterum.items.CustomItems;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class RecipesGUI implements Listener {
    private final Plugin plugin;
    public static List<ItemStack> items = List.of(
            CustomItems.RANDOMIZER,
        CustomItems.FLOWER_HORSE_TAME,
        CustomItems.BETTER_BONEMEAL,
        CustomItems.GROWTH_POTION,
        CustomItems.HOE_OF_HARVEST,
        CustomItems.DRUNK_HORSE_TAME,
        CustomItems.PARTY_ATMOSPHERE,
        CustomItems.PARTY_BALL,
        CustomItems.RANDOM_EFFECT_POTION,
        CustomItems.ZOMBIE_HORSE_TAME,
        CustomItems.DARKNESS_POTION,
        CustomItems.DARK_PEARL,
        CustomItems.DARK_PORTAL,
        CustomItems.SPEED_HORSE_TAME,
        CustomItems.SPEED_HORSE_ABILITY,
        CustomItems.FLYING_ITEM,
        CustomItems.MESSENGER_PACK,
        CustomItems.SPEED_BOOTS,
        CustomItems.SEA_HORSE_TAME,
        CustomItems.WATER_BREATHING_CROWN,
        CustomItems.BETTER_TRIDENT,
        CustomItems.PEGASUS_TAME,
        CustomItems.PEGASUS_ABILITY,
        CustomItems.BREEDING_ITEM,
        CustomItems.WEATHER_CHANGER,
        CustomItems.LIGHTNING_SPEAR
    );
    private Inventory mainInventory;

    public RecipesGUI(Plugin plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory clickedInventory = event.getClickedInventory();
        ItemStack clickedItem = event.getCurrentItem();

        if (clickedInventory == null || clickedItem == null) return;
        if (clickedItem.getItemMeta() == null) return;

        String title = event.getView().getTitle();

        if (title.contains("Aeterum")){
            event.setCancelled(true);
            if (items.contains(clickedItem)){
                if (!player.hasPermission("aeterum.admin")){
                    return;
                }
                player.getInventory().addItem(clickedItem);
            }
        }
    }
}
