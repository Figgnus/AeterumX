package me.figgnus.aeterum.gui;

import me.figgnus.aeterum.AeterumX;
import me.figgnus.aeterum.items.CustomItems;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class RecipesGUI implements Listener {
    private final AeterumX plugin;
    public static List<ItemStack> items = new ArrayList<>();
    private Inventory mainInventory;

    public RecipesGUI(AeterumX plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        items.add(CustomItems.RANDOMIZER);
        items.add(CustomItems.FLOWER_HORSE_TAME);
        items.add(CustomItems.BETTER_BONEMEAL);
        items.add(CustomItems.GROWTH_POTION);
        items.add(CustomItems.HOE_OF_HARVEST);
        items.add(CustomItems.DRUNK_HORSE_TAME);
        items.add(CustomItems.PARTY_ATMOSPHERE);
        items.add(CustomItems.PARTY_BALL);
        items.add(CustomItems.RANDOM_EFFECT_POTION);
        items.add(CustomItems.ZOMBIE_HORSE_TAME);
        items.add(CustomItems.DARKNESS_POTION);
        items.add(CustomItems.DARK_PEARL);
        items.add(CustomItems.DARK_PORTAL);
        items.add(CustomItems.SPEED_HORSE_TAME);
        items.add(CustomItems.SPEED_HORSE_ABILITY);
        items.add(CustomItems.MESSENGER_PACK);
        items.add(CustomItems.SPEED_BOOTS);
        items.add(CustomItems.SEA_HORSE_TAME);
        items.add(CustomItems.WATER_BREATHING_CROWN);
        items.add(CustomItems.BETTER_TRIDENT);
        items.add(CustomItems.PEGASUS_TAME);
        items.add(CustomItems.PEGASUS_ABILITY);
        items.add(CustomItems.BREEDING_ITEM);
        items.add(CustomItems.WEATHER_CHANGER);
        items.add(CustomItems.LIGHTNING_SPEAR);


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
