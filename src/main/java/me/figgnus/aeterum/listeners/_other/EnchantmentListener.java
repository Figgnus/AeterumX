package me.figgnus.aeterum.listeners._other;

import me.figgnus.aeterum.AeterumX;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

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

        ItemStack first = event.getInventory().getItem(0); // Slot 0
        ItemStack second = event.getInventory().getItem(1); // Slot 1
        ItemStack result = event.getResult(); // The result of combining

        // Ensure both items and result exist
        if (first == null || second == null || result == null) {
            return;
        }
        if (!isToolOrArmor(first) || !isToolOrArmor(second)) {
            return;
        }


        // Get the result item's meta
        ItemMeta resultMeta = result.getItemMeta();

        // Check all enchantments from both items
        for (Map.Entry<Enchantment, Integer> entry : first.getEnchantments().entrySet()) {
            Enchantment enchantment = entry.getKey();
            int level = entry.getValue();
            int maxLevel = enchantment.getMaxLevel();

            // If the enchantment level is higher than the max, keep it
            if (level > maxLevel) {
                resultMeta.addEnchant(enchantment, level, true);
            } else {
                resultMeta.addEnchant(enchantment, maxLevel, true);
            }
        }

        for (Map.Entry<Enchantment, Integer> entry : second.getEnchantments().entrySet()) {
            Enchantment enchantment = entry.getKey();
            int level = entry.getValue();
            int maxLevel = enchantment.getMaxLevel();

            // Check if result already contains this enchantment
            if (resultMeta.hasEnchant(enchantment)) {
                int existingLevel = resultMeta.getEnchantLevel(enchantment);
                // Keep the higher level between the two items
                if (level > existingLevel) {
                    resultMeta.removeEnchant(enchantment);
                    resultMeta.addEnchant(enchantment, level > maxLevel ? level : maxLevel, true);
                }
            } else {
                // Add the enchantment if it's not already in the result
                resultMeta.addEnchant(enchantment, level > maxLevel ? level : maxLevel, true);
            }
        }

        // Set the updated meta back to the result item
        result.setItemMeta(resultMeta);

        // Set the modified result in the event
        event.setResult(result);
    }
//    @EventHandler
//    public void onPrepareAnvil(PrepareAnvilEvent event) {
//        ItemStack first = event.getInventory().getItem(0);
//        ItemStack second = event.getInventory().getItem(1);
//        ItemStack result = event.getResult();
//
//        if (first == null || second == null || result == null) {
//            return;
//        }
//        if (!isToolOrArmor(first) || !isToolOrArmor(second)) {
//            return;
//        }
//
//        ItemMeta resultMeta = result.getItemMeta();
//        result.getEnchantments().forEach((ench, level) -> {
//           int maxLevel = ench.getMaxLevel();
//           if (level >= maxLevel) {
//               first.getEnchantments().forEach((ench2, level2) -> {
//                   int maxLevel2 = ench2.getMaxLevel();
//                   if (level2 > maxLevel2) {
//                       resultMeta.removeEnchant(ench2);
//                       resultMeta.addEnchant(ench2, level2, true);
//                   }
//               });
//               second.getEnchantments().forEach((ench2, level2) -> {
//                   int maxLevel2 = ench2.getMaxLevel();
//                   if (level2 > maxLevel2) {
//                       resultMeta.removeEnchant(ench2);
//                       resultMeta.addEnchant(ench2, level2, true);
//                   }
//               });
//               resultMeta.removeEnchant(ench);
//               resultMeta.addEnchant(ench, maxLevel, true);
//           }
//
//        });
//        result.setItemMeta(resultMeta);
//        event.setResult(null);
//        event.setResult(result);
//    }
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
