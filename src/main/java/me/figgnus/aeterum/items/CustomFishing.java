package me.figgnus.aeterum.items;

import me.figgnus.aeterum.Plugin;
import net.momirealms.customfishing.api.BukkitCustomFishingPlugin;
import net.momirealms.customfishing.api.mechanic.item.CustomFishingItem;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;


public class CustomFishing {
    private final Plugin plugin;
    public static final List<String> BEGINNER_ROD_LORE = List.of("Fishing", "for beginners");

    public static final ItemStack BEGINNER_ROD = createFishingItem(80001, Material.FISHING_ROD, "Beginner Rod", Color.WHITE, BEGINNER_ROD_LORE);

    public static final List<ItemStack> BEGINNER_ROD_RECIPE = List.of(
            new ItemStack(Material.AIR), new ItemStack(Material.KELP), new ItemStack(Material.KELP),
            new ItemStack(Material.KELP), new ItemStack(Material.FISHING_ROD), new ItemStack(Material.KELP),
            new ItemStack(Material.AIR), new ItemStack(Material.KELP), new ItemStack(Material.AIR)
    );
    private static ItemStack createFishingItem(Integer id, Material material, String name, Color color, List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(color + name);
        meta.setLore(lore);
        meta.setCustomModelData(id);
        item.setItemMeta(meta);
        return item;
    }

    public CustomFishing(Plugin plugin) {
        this.plugin = plugin;

    }
}
