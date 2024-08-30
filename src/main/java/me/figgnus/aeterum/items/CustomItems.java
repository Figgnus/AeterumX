package me.figgnus.aeterum.items;
import me.figgnus.aeterum.Plugin;

import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;

import java.util.*;


public class CustomItems {

    private static final String BETTER_BONEMEAL_NAME = Plugin.getItemName("BETTER_BONEMEAL");
    private static final String GROWTH_POTION_NAME = Plugin.getItemName("GROWTH_POTION");
    private static final String HOE_OF_HARVEST_NAME = Plugin.getItemName("HOE_OF_THE_HARVEST");
    private static final String FLOWER_HORSE_TAME_NAME = Plugin.getItemName("FLOWER_HORSE_TAME");
    private static final String ZOMBIE_HORSE_TAME_NAME = Plugin.getItemName("ZOMBIE_HORSE_TAME");
    private static final String DRUNK_HORSE_TAME_NAME = Plugin.getItemName("DRUNK_HORSE_TAME");
    private static final String SPEED_HORSE_TAME_NAME = Plugin.getItemName("SPEED_HORSE_TAME");
    private static final String SPEED_HORSE_ABILITY_NAME = Plugin.getItemName("SPEED_HORSE_ABILITY");
    private static final String SEA_HORSE_TAME_NAME = Plugin.getItemName("SEA_HORSE_TAME");
    private static final String PEGASUS_HORSE_TAME_NAME = Plugin.getItemName("PEGASUS_HORSE_TAME");
    private static final String PEGASUS_HORSE_ABILITY_NAME = Plugin.getItemName("PEGASUS_HORSE_ABILITY");
    private static final String PARTY_BALL_NAME = Plugin.getItemName("PARTY_BALL");
    private static final String PARTY_ATMOSPHERE_NAME = Plugin.getItemName("PARTY_ATMOSPHERE");
    private static final String RANDOM_EFFECT_POTION_NAME = Plugin.getItemName("RANDOM_EFFECT_POTION");
    private static final String RANDOMIZER_NAME = Plugin.getItemName("RANDOMIZER");
    private static final String DARKNESS_POTION_NAME = Plugin.getItemName("DARKNESS_POTION");
    private static final String DARK_PEARL_NAME = Plugin.getItemName("DARK_PEARL");
    private static final String DARK_PORTAL_NAME = Plugin.getItemName("DARK_PORTAL");
    private static final String FLYING_ITEM_NAME = Plugin.getItemName("FLYING_ITEM");
    private static final String MESSENGER_PACK_NAME = Plugin.getItemName("MESSENGER_PACK");
    private static final String SPEED_BOOTS_NAME = Plugin.getItemName("SPEED_BOOTS");
    private static final String BETTER_TRIDENT_NAME = Plugin.getItemName("BETTER_TRIDENT");
    private static final String WATER_BREATHING_CROWN_NAME = Plugin.getItemName("WATER_BREATHING_CROWN");
    private static final String BREEDING_ITEM_NAME = Plugin.getItemName("BREEDING_ITEM");
    private static final String LIGHTNING_SPEAR_NAME = Plugin.getItemName("LIGHTNING_SPEAR");
    private static final String WEATHER_CHANGER_NAME = Plugin.getItemName("WEATHER_CHANGER");

    //Lore
    private static final List<String> BETTER_BONEMEAL_LORE = List.of(ChatColor.GRAY + Plugin.getItemLore("BETTER_BONEMEAL"));
    private static final List<String> GROWTH_POTION_LORE = List.of(ChatColor.GRAY + Plugin.getItemLore("GROWTH_POTION"));
    private static final List<String> HOE_OF_HARVEST_LORE = List.of(ChatColor.GRAY + Plugin.getItemLore("HOE_OF_THE_HARVEST"));
    private static final List<String> FLOWER_HORSE_TAME_LORE = List.of(ChatColor.GRAY + Plugin.getItemLore("FLOWER_HORSE_TAME"));
    private static final List<String> ZOMBIE_HORSE_TAME_LORE = List.of(ChatColor.GRAY + Plugin.getItemLore("ZOMBIE_HORSE_TAME"));
    private static final List<String> DRUNK_HORSE_TAME_LORE = List.of(ChatColor.GRAY + Plugin.getItemLore("DRUNK_HORSE_TAME"));
    private static final List<String> SPEED_HORSE_TAME_LORE = List.of(ChatColor.GRAY + Plugin.getItemLore("SPEED_HORSE_TAME"));
    private static final List<String> SPEED_HORSE_ABILITY_LORE = List.of(ChatColor.GRAY + Plugin.getItemLore("SPEED_HORSE_ABILITY"));
    private static final List<String> SEA_HORSE_TAME_LORE = List.of(ChatColor.GRAY + Plugin.getItemLore("SEA_HORSE_TAME"));
    private static final List<String> PEGASUS_TAME_LORE = List.of(ChatColor.GRAY + Plugin.getItemLore("PEGASUS_HORSE_TAME"));
    private static final List<String> PEGASUS_ABILITY_LORE = List.of(ChatColor.GRAY + Plugin.getItemLore("PEGASUS_HORSE_ABILITY"));
    private static final List<String> PARTY_BALL_LORE = List.of(ChatColor.GRAY + Plugin.getItemLore("PARTY_BALL"));
    private static final List<String> PARTY_ATMOSPHERE_LORE = List.of(ChatColor.GRAY + Plugin.getItemLore("PARTY_ATMOSPHERE"));
    private static final List<String> RANDOM_EFFECT_POTION_LORE = List.of(ChatColor.GRAY + Plugin.getItemLore("RANDOM_EFFECT_POTION"));
    private static final List<String> RANDOMIZER_LORE = List.of(ChatColor.GRAY + Plugin.getItemLore("RANDOMIZER"));
    private static final List<String> DARKNESS_POTION_LORE = List.of(ChatColor.GRAY + Plugin.getItemLore("DARKNESS_POTION"));
    private static final List<String> DARK_PEARL_LORE = List.of(ChatColor.GRAY + Plugin.getItemLore("DARK_PEARL"));
    private static final List<String> DARK_PORTAL_LORE = List.of(ChatColor.GRAY + Plugin.getItemLore("DARK_PORTAL"));
    private static final List<String> FLYING_ITEM_LORE = List.of(ChatColor.GRAY + Plugin.getItemLore("FLYING_ITEM"));
    private static final List<String> MESSENGER_PACK_LORE = List.of(ChatColor.GRAY + Plugin.getItemLore("MESSENGER_PACK"));
    private static final List<String> SPEED_BOOTS_LORE = List.of(ChatColor.GRAY + Plugin.getItemLore("SPEED_BOOTS"));
    private static final List<String> BETTER_TRIDENT_LORE = List.of(ChatColor.GRAY + Plugin.getItemLore("BETTER_TRIDENT"));
    private static final List<String> WATER_BREATHING_CROW_LORE = List.of(ChatColor.GRAY + Plugin.getItemLore("WATER_BREATHING_CROWN"));
    private static final List<String> BREEDING_ITEM_LORE = List.of(ChatColor.GRAY + Plugin.getItemLore("BREEDING_ITEM"));
    private static final List<String> LIGHTNING_SPEAR_LORE = List.of(ChatColor.GRAY + Plugin.getItemLore("LIGHTNING_SPEAR"));
    private static final List<String> WEATHER_CHANGER_LORE = List.of(ChatColor.GRAY + Plugin.getItemLore("WEATHER_CHANGER"));

    //ItemStack
    public static final ItemStack BETTER_BONEMEAL = createCustomItem(Plugin.getItemMaterial("BETTER_BONEMEAL"), 60001, BETTER_BONEMEAL_NAME, BETTER_BONEMEAL_LORE, null);
    public static final ItemStack GROWTH_POTION = createCustomItem(Plugin.getItemMaterial("GROWTH_POTION"), 60002, GROWTH_POTION_NAME, GROWTH_POTION_LORE, Plugin.getItemColor("GROWTH_POTION"));
    public static final ItemStack HOE_OF_HARVEST = createCustomItem(Plugin.getItemMaterial("HOE_OF_THE_HARVEST"), 60003, HOE_OF_HARVEST_NAME, HOE_OF_HARVEST_LORE, null);
    public static final ItemStack FLOWER_HORSE_TAME = createCustomItem(Plugin.getItemMaterial("FLOWER_HORSE_TAME"), 60004, FLOWER_HORSE_TAME_NAME, FLOWER_HORSE_TAME_LORE, null);
    public static final ItemStack ZOMBIE_HORSE_TAME = createCustomItem(Plugin.getItemMaterial("ZOMBIE_HORSE_TAME"), 60005, ZOMBIE_HORSE_TAME_NAME, ZOMBIE_HORSE_TAME_LORE, null);
    public static final ItemStack DRUNK_HORSE_TAME = createCustomItem(Plugin.getItemMaterial("DRUNK_HORSE_TAME"), 60006, DRUNK_HORSE_TAME_NAME, DRUNK_HORSE_TAME_LORE, null);
    public static final ItemStack SPEED_HORSE_TAME = createCustomItem(Plugin.getItemMaterial("SPEED_HORSE_TAME"), 60007, SPEED_HORSE_TAME_NAME, SPEED_HORSE_TAME_LORE, null);
    public static final ItemStack SPEED_HORSE_ABILITY = createCustomItem(Plugin.getItemMaterial("SPEED_HORSE_ABILITY"), 60008, SPEED_HORSE_ABILITY_NAME, SPEED_HORSE_ABILITY_LORE, Plugin.getItemColor("SPEED_HORSE_ABILITY"));
    public static final ItemStack SEA_HORSE_TAME = createCustomItem(Plugin.getItemMaterial("SEA_HORSE_TAME"), 60009, SEA_HORSE_TAME_NAME, SEA_HORSE_TAME_LORE, null);
    public static final ItemStack PEGASUS_TAME = createCustomItem(Plugin.getItemMaterial("PEGASUS_HORSE_TAME"), 60010, PEGASUS_HORSE_TAME_NAME, PEGASUS_TAME_LORE, null);
    public static final ItemStack PEGASUS_ABILITY = createCustomItem(Plugin.getItemMaterial("PEGASUS_HORSE_ABILITY"), 60011, PEGASUS_HORSE_ABILITY_NAME, PEGASUS_ABILITY_LORE, Plugin.getItemColor("PEGASUS_HORSE_ABILITY"));
    public static final ItemStack PARTY_BALL = createCustomItem(Plugin.getItemMaterial("PARTY_BALL"), 60012, PARTY_BALL_NAME, PARTY_BALL_LORE, null);
    public static final ItemStack PARTY_ATMOSPHERE = createCustomItem(Plugin.getItemMaterial("PARTY_ATMOSPHERE"), 60013, PARTY_ATMOSPHERE_NAME, PARTY_ATMOSPHERE_LORE, Plugin.getItemColor("PARTY_ATMOSPHERE"));
    public static final ItemStack RANDOM_EFFECT_POTION = createCustomItem(Plugin.getItemMaterial("RANDOM_EFFECT_POTION"), 60014, RANDOM_EFFECT_POTION_NAME, RANDOM_EFFECT_POTION_LORE, Plugin.getItemColor("RANDOM_EFFECT_POTION"));
    public static final ItemStack RANDOMIZER = createCustomItem(Plugin.getItemMaterial("RANDOMIZER"), 60015, RANDOMIZER_NAME, RANDOMIZER_LORE, null);
    public static final ItemStack DARKNESS_POTION = createCustomItem(Plugin.getItemMaterial("DARKNESS_POTION"), 60016, DARKNESS_POTION_NAME, DARKNESS_POTION_LORE, Plugin.getItemColor("DARKNESS_POTION"));
    public static final ItemStack DARK_PEARL = createCustomItem(Plugin.getItemMaterial("DARK_PEARL"), 60017, DARK_PEARL_NAME, DARK_PEARL_LORE, null);
    public static final ItemStack DARK_PORTAL = createCustomItem(Plugin.getItemMaterial("DARK_PORTAL"), 60018, DARK_PORTAL_NAME, DARK_PORTAL_LORE, Plugin.getItemColor("DARK_PORTAL"));
    public static final ItemStack FLYING_ITEM = createCustomItem(Plugin.getItemMaterial("FLYING_ITEM"), 60019, FLYING_ITEM_NAME, FLYING_ITEM_LORE, null);
    public static final ItemStack MESSENGER_PACK = createCustomItem(Plugin.getItemMaterial("MESSENGER_PACK"), 60020, MESSENGER_PACK_NAME, MESSENGER_PACK_LORE, null);
    public static final ItemStack SPEED_BOOTS = createCustomItem(Plugin.getItemMaterial("SPEED_BOOTS"), 60021, SPEED_BOOTS_NAME, SPEED_BOOTS_LORE, null);
    public static final ItemStack BETTER_TRIDENT = createCustomItem(Plugin.getItemMaterial("BETTER_TRIDENT"), 60022, BETTER_TRIDENT_NAME, BETTER_TRIDENT_LORE, null);
    public static final ItemStack WATER_BREATHING_CROWN = createCustomItem(Plugin.getItemMaterial("WATER_BREATHING_CROWN"), 60023, WATER_BREATHING_CROWN_NAME, WATER_BREATHING_CROW_LORE, null);
    public static final ItemStack BREEDING_ITEM = createCustomItem(Plugin.getItemMaterial("BREEDING_ITEM"), 60024, BREEDING_ITEM_NAME, BREEDING_ITEM_LORE, null);
    public static final ItemStack LIGHTNING_SPEAR = createCustomItem(Plugin.getItemMaterial("LIGHTNING_SPEAR"), 60025, LIGHTNING_SPEAR_NAME, LIGHTNING_SPEAR_LORE, null);
    public static final ItemStack WEATHER_CHANGER = createCustomItem(Plugin.getItemMaterial("WEATHER_CHANGER"), 60026, WEATHER_CHANGER_NAME, WEATHER_CHANGER_LORE, Plugin.getItemColor("WEATHER_CHANGER"));


    // Lists of enchantments for items
    List<Map.Entry<Enchantment, Integer>> betterTridentEnchantments = new ArrayList<>();
    List<Map.Entry<Enchantment, Integer>> waterBreathingCrownEnchantments = new ArrayList<>();

    public CustomItems() {
        betterTridentEnchantments.add(new AbstractMap.SimpleEntry<>(Enchantment.RIPTIDE, 5));
        betterTridentEnchantments.add(new AbstractMap.SimpleEntry<>(Enchantment.IMPALING, 7));
        betterTridentEnchantments.add(new AbstractMap.SimpleEntry<>(Enchantment.UNBREAKING, 3));
        betterTridentEnchantments.add(new AbstractMap.SimpleEntry<>(Enchantment.MENDING, 1));

        waterBreathingCrownEnchantments.add(new AbstractMap.SimpleEntry<>(Enchantment.AQUA_AFFINITY, 1));
        waterBreathingCrownEnchantments.add(new AbstractMap.SimpleEntry<>(Enchantment.RESPIRATION, 5));
        waterBreathingCrownEnchantments.add(new AbstractMap.SimpleEntry<>(Enchantment.UNBREAKING, 3));
        waterBreathingCrownEnchantments.add(new AbstractMap.SimpleEntry<>(Enchantment.MENDING, 1));
        waterBreathingCrownEnchantments.add(new AbstractMap.SimpleEntry<>(Enchantment.PROTECTION, 5));

        ItemUtils.configureMeta(BETTER_TRIDENT, null, betterTridentEnchantments);
        ItemUtils.configureMeta(WATER_BREATHING_CROWN, null, waterBreathingCrownEnchantments);
    }


    private ShapedRecipe createShapedRecipeFromList(ItemStack result, List<ItemStack> ingredients, NamespacedKey key) {
        // Ensure that the ingredients list contains exactly 9 items
        if (ingredients.size() != 9) {
            throw new IllegalArgumentException("The ingredients list must contain exactly 9 ItemStacks.");
        }

        // Create the recipe
        ShapedRecipe recipe = new ShapedRecipe(key, result);

        // Create a map to track Material to Character mappings
        Map<Material, Character> materialToCharMap = new LinkedHashMap<>();
        char nextChar = 'A';

        // Assign unique characters to each material (ignoring AIR)
        for (ItemStack item : ingredients) {
            Material material = item.getType();
            if (material != Material.AIR && !materialToCharMap.containsKey(material)) {
                materialToCharMap.put(material, nextChar);
                nextChar++;
            }
        }

        // Convert List<ItemStack> to rows using the materialToCharMap
        String row1 = getRowFromItems(ingredients.subList(0, 3), materialToCharMap);
        String row2 = getRowFromItems(ingredients.subList(3, 6), materialToCharMap);
        String row3 = getRowFromItems(ingredients.subList(6, 9), materialToCharMap);

        // Define the shape of the recipe
        recipe.shape(row1, row2, row3);

        // Set the ingredients in the recipe
        for (Map.Entry<Material, Character> entry : materialToCharMap.entrySet()) {
            recipe.setIngredient(entry.getValue(), entry.getKey());
        }

        return recipe;
    }

    // Helper method to get a row string from a list of ItemStacks
    private String getRowFromItems(List<ItemStack> items, Map<Material, Character> materialToCharMap) {
        StringBuilder row = new StringBuilder();
        for (ItemStack item : items) {
            if (item.getType() == Material.AIR) {
                row.append(' ');
            } else {
                row.append(materialToCharMap.get(item.getType()));
            }
        }
        return row.toString();
    }

    public static ItemStack createCustomItem(Material material, int id, String displayName, List<String> lore, Color color) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + displayName);
        meta.setCustomModelData(id);
        meta.setLore(lore);
        if (itemStack.getType() == Material.POTION || itemStack.getType() == Material.SPLASH_POTION || itemStack.getType() == Material.LINGERING_POTION){
            if (meta instanceof PotionMeta){
                PotionMeta potionMeta = (PotionMeta) meta;
                potionMeta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
                if (color != null){
                    potionMeta.setColor(color);
                }
            }
        }

        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
