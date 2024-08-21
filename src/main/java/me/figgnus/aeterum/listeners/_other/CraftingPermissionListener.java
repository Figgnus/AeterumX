package me.figgnus.aeterum.listeners._other;

import me.figgnus.aeterum.Plugin;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.utils.GodUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class CraftingPermissionListener implements Listener {
    private final Plugin plugin;

    private String message = "§cNemáš oprávnění vytvořit tento předmět!";

    private final List<ItemStack> demeterItems = List.of(CustomItems.BETTER_BONEMEAL, CustomItems.FLOWER_HORSE_TAME, CustomItems.GROWTH_POTION, CustomItems.HOE_OF_HARVEST);
    private final List<ItemStack> dionysusItems = List.of(CustomItems.DRUNK_HORSE_TAME, CustomItems.PARTY_ATMOSPHERE, CustomItems.PARTY_BALL, CustomItems.RANDOM_EFFECT_POTION);
    private final List<ItemStack> hadesItems = List.of(CustomItems.ZOMBIE_HORSE_TAME, CustomItems.DARK_PEARL, CustomItems.DARK_PORTAL, CustomItems.DARKNESS_POTION);
    private final List<ItemStack> poseidonItems = List.of(CustomItems.SEA_HORSE_TAME, CustomItems.WATER_BREATHING_CROWN, CustomItems.BETTER_TRIDENT);
    private final List<ItemStack> zeusItems = List.of(CustomItems.PEGASUS_TAME, CustomItems.PEGASUS_ABILITY, CustomItems.BREEDING_ITEM, CustomItems.LIGHTNING_SPEAR, CustomItems.WEATHER_CHANGER);
    private final List<ItemStack> hermesItems = List.of(CustomItems.SPEED_HORSE_TAME, CustomItems.SPEED_HORSE_ABILITY, CustomItems.SPEED_BOOTS, CustomItems.FLYING_ITEM, CustomItems.MESSENGER_PACK);

    public CraftingPermissionListener(Plugin plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this , plugin);
    }
    @EventHandler
    public void onPrepareCraft(PrepareItemCraftEvent event) {
        CraftingInventory inventory = event.getInventory();
        ItemStack result = inventory.getResult();

        if (result != null && demeterItems.contains(result)) {
            Player player = (Player) event.getView().getPlayer();
            if (!player.hasPermission(GodUtils.demeterPermission)) {
                inventory.setResult(new ItemStack(Material.AIR)); // Clear the result to prevent crafting
                player.sendMessage(message);
            }
        }
        if (result != null && dionysusItems.contains(result)) {
            Player player = (Player) event.getView().getPlayer();
            if (!player.hasPermission(GodUtils.dionysusPermission)) {
                inventory.setResult(new ItemStack(Material.AIR)); // Clear the result to prevent crafting
                player.sendMessage(message);
            }
        }
        if (result != null && hadesItems.contains(result)) {
            Player player = (Player) event.getView().getPlayer();
            if (!player.hasPermission(GodUtils.hadesPermission)) {
                inventory.setResult(new ItemStack(Material.AIR)); // Clear the result to prevent crafting
                player.sendMessage(message);
            }
        }
        if (result != null && poseidonItems.contains(result)) {
            Player player = (Player) event.getView().getPlayer();
            if (!player.hasPermission(GodUtils.poseidonPermission)) {
                inventory.setResult(new ItemStack(Material.AIR)); // Clear the result to prevent crafting
                player.sendMessage(message);
            }
        }
        if (result != null && hermesItems.contains(result)) {
            Player player = (Player) event.getView().getPlayer();
            if (!player.hasPermission(GodUtils.hermesPermission)) {
                inventory.setResult(new ItemStack(Material.AIR)); // Clear the result to prevent crafting
                player.sendMessage(message);
            }
        }
        if (result != null && zeusItems.contains(result)) {
            Player player = (Player) event.getView().getPlayer();
            if (!player.hasPermission(GodUtils.zeusPermission)) {
                inventory.setResult(new ItemStack(Material.AIR)); // Clear the result to prevent crafting
                player.sendMessage(message);
            }
        }
    }
}
