package me.figgnus.aeterum.listeners._other;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class BreweryMenu {
    public static ItemStack cauldron = createBreweryItem("Brewery", Material.CAULDRON);

    private static ItemStack createBreweryItem(String name, Material material) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        item.setAmount(1);
        meta.setDisplayName(ChatColor.GREEN + name);
        item.setItemMeta(meta);
        return item;
    }
}
