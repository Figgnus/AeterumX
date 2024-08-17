package me.figgnus.aeterum.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;

public class ItemUtils {

    public static boolean isCustomItem(ItemStack item, int id){
        return item != null && item.getItemMeta() != null && item.getItemMeta().hasCustomModelData() &&
                item.getItemMeta().getCustomModelData() == id;
    }
    // Creates basic potion
    public static ItemStack createPotion(PotionType potionType){
        ItemStack potion = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) potion.getItemMeta();
        assert meta != null;
        meta.setBasePotionType(potionType);
        potion.setItemMeta(meta);
        return potion;
    }
}
