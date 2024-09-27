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

import java.util.HashMap;
import java.util.Map;

public class EnchantmentListener implements Listener {
//    public class ItemCopyUtil {
//
//        // Method to clone an ItemStack using NMS
//        public static BukkitItemStack copyItem(BukkitItemStack original) {
//            // Convert Bukkit ItemStack to NMS ItemStack
//            ItemStack nmsOriginal = CraftItemStack.asNMSCopy(original);
//
//            // Clone the NMS ItemStack
//            ItemStack nmsCopy = nmsOriginal.cloneItemStack();
//
//            // Convert the cloned NMS ItemStack back to a Bukkit ItemStack
//            return CraftItemStack.asBukkitCopy(nmsCopy);
//        }
//    }
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

        // Ensure both items exist
        if (first == null || second == null) {
            return;
        }

        // Ensure both items are the same type (pickaxe/pickaxe, shovel/shovel, etc.)
        if (first.getType() != second.getType()) {
            return; // Exit if items are not the same type
        }

        // Ensure both items are tools or armor
        if (!isToolOrArmor(first) || !isToolOrArmor(second)) {
            return;
        }

        // Create the result ItemStack (based on the first item)
        ItemStack resultItem = new ItemStack(first.getType());
        ItemMeta resultMeta = resultItem.getItemMeta();

        // Apply all enchantments from the first item to the result item
        Map<Enchantment, Integer> combinedEnchantments = new HashMap<>(first.getEnchantments());

        // Try to apply enchantments from the second item
        for (Map.Entry<Enchantment, Integer> entry : second.getEnchantments().entrySet()) {
            Enchantment enchantment = entry.getKey();
            int secondLevel = entry.getValue();

            // Check if the result already has this enchantment from the first item
            if (combinedEnchantments.containsKey(enchantment)) {
                int firstLevel = combinedEnchantments.get(enchantment);
                // Combine logic: If levels are equal, increase by 1, otherwise take the highest
                int resultLevel = (firstLevel == secondLevel) ? firstLevel + 1 : Math.max(firstLevel, secondLevel);
                resultLevel = Math.min(resultLevel, enchantment.getMaxLevel()); // Cap at max level
                combinedEnchantments.put(enchantment, resultLevel);
            } else {
                // Only apply the second item's enchantment if it does not conflict with the already applied ones
                boolean conflicts = false;
                for (Enchantment existingEnch : combinedEnchantments.keySet()) {
                    if (enchantment.conflictsWith(existingEnch)) {
                        conflicts = true;
                        break;
                    }
                }
                // If no conflict, add the enchantment from the second item
                if (!conflicts) {
                    combinedEnchantments.put(enchantment, secondLevel);
                }
            }
        }

        // Apply the combined enchantments to the result item
        for (Map.Entry<Enchantment, Integer> entry : combinedEnchantments.entrySet()) {
            resultMeta.addEnchant(entry.getKey(), entry.getValue(), true);
        }

        // Set the item's display name if renamed
        String renameText = event.getView().getRenameText();
        if (renameText != null && !renameText.isEmpty()) {
            resultMeta.setDisplayName(renameText);
        }

        // Set the updated meta back to the result item
        resultItem.setItemMeta(resultMeta);

        // Set the modified result in the event
        event.setResult(resultItem);
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
