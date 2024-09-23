package me.figgnus.aeterum.listeners._other;

import me.figgnus.aeterum.AeterumX;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;

public class EnchantmentListener implements Listener {
    private final AeterumX plugin;

    public EnchantmentListener(AeterumX plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void onPrepareAnvilBook(PrepareAnvilEvent event) {
        ItemStack first = event.getInventory().getItem(0);
        ItemStack second = event.getInventory().getItem(1);
        ItemStack result = event.getResult();

        if (first == null || second == null || result == null) {
            return;
        }
        if (first.getType() != Material.ENCHANTED_BOOK || second.getType() != Material.ENCHANTED_BOOK) {
            return;
        }

        // Get the meta for enchanted books
        EnchantmentStorageMeta firstMeta = (EnchantmentStorageMeta) first.getItemMeta();
        EnchantmentStorageMeta secondMeta = (EnchantmentStorageMeta) second.getItemMeta();
        EnchantmentStorageMeta resultMeta = (EnchantmentStorageMeta) result.getItemMeta();

        if (firstMeta == null || secondMeta == null || resultMeta == null) {
            return;
        }


        // Get the enchantments from the first and second books
        Map<Enchantment, Integer> firstEnchantments = firstMeta.getStoredEnchants();
        Map<Enchantment, Integer> secondEnchantments = secondMeta.getStoredEnchants();


        for (Map.Entry<Enchantment, Integer> entry : firstEnchantments.entrySet()) {
            Enchantment enchantment = entry.getKey();
            int firstLevel = entry.getValue();
            int secondLevel = secondEnchantments.getOrDefault(enchantment, 0);

            // Calculate the resulting level for this enchantment
            int resultLevel = (firstLevel == secondLevel) ? firstLevel + 1 : Math.max(firstLevel, secondLevel);

            // Check if the resulting level exceeds the vanilla max level
            if (resultLevel > enchantment.getMaxLevel()) {
                event.setResult(null);
                return;
            }
        }
    }
    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        ItemStack first = event.getInventory().getItem(0);
        ItemStack second = event.getInventory().getItem(1);
        ItemStack result = event.getResult();

        if (first == null || second == null || result == null) {
            return;
        }
        if (!isToolOrArmor(first) || !isToolOrArmor(second)) {
            return;
        }

        ItemMeta resultMeta = result.getItemMeta();
        result.getEnchantments().forEach((ench, level) -> {
           int maxLevel = ench.getMaxLevel();
           if (level >= maxLevel) {
               resultMeta.removeEnchant(ench);
               resultMeta.addEnchant(ench, maxLevel, true);
           }

        });
        result.setItemMeta(resultMeta);
        new BukkitRunnable() {
            @Override
            public void run() {
                event.setResult(result);
            }
        }.runTaskLater(plugin, 1);
    }
    private boolean isToolOrArmor(ItemStack item) {
        Material type = item.getType();
        return type.name().endsWith("HELMET") || type.name().endsWith("SHELL") ||
                type.name().endsWith("CHESTPLATE") || type.name().endsWith("LEGGINGS") ||
                type.name().endsWith("BOOTS") || type.name().endsWith("SWORD") ||
                type.name().endsWith("MACE") || type.name().endsWith("AXE") ||
                type.name().endsWith("PICKAXE") || type.name().endsWith("SHOVEL") ||
                type.name().endsWith("HOE") || type.name().endsWith("BOW") ||
                type.name().endsWith("ROD") || type.name().endsWith("TRIDENT") ||
                type.name().endsWith("SHEARS") || type.name().endsWith("SHIELD") ||
                type.name().endsWith("ELYTRA") || type.name().endsWith("STEEL") ||
                type.name().endsWith("STICK") || type.name().endsWith("BRUSH") ||
                type.name().endsWith("COMPASS");

    }
}
