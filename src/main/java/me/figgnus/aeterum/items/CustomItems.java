package me.figgnus.aeterum.items;
import me.figgnus.aeterum.Plugin;

import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;

import java.util.*;


public class CustomItems {
    private final Plugin plugin;
    public static List<ItemStack> BETTER_BONEMEAL_RECIPE;
    public static List<ItemStack> GROWTH_POTION_RECIPE;
    public static List<ItemStack> RANDOMIZER_RECIPE;
    public static List<ItemStack> HOE_OF_HARVEST_RECIPE;
    public static List<ItemStack> FLOWER_HORSE_TAME_RECIPE;
    public static List<ItemStack> PARTY_ATMOSPHERE_RECIPE;
    public static List<ItemStack> PARTY_BALL_RECIPE;
    public static List<ItemStack> RANDOM_EFFECT_POTION_RECIPE;
    public static List<ItemStack> DRUNK_HORSE_TAME_RECIPE;
    public static List<ItemStack> DARKNESS_POTION_RECIPE;
    public static List<ItemStack> DARK_PEARL_RECIPE;
    public static List<ItemStack> DARK_PORTAL_RECIPE;

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
    public static final ItemStack BETTER_BONEMEAL = createCustomItem(Material.BONE_MEAL, 60001, BETTER_BONEMEAL_NAME, BETTER_BONEMEAL_LORE, null);
    public static final ItemStack GROWTH_POTION = createCustomItem(Material.POTION, 60002, GROWTH_POTION_NAME, GROWTH_POTION_LORE, Color.fromRGB(63, 206, 130));
    public static final ItemStack HOE_OF_HARVEST = createCustomItem(Material.NETHERITE_HOE, 60003, HOE_OF_HARVEST_NAME, HOE_OF_HARVEST_LORE, null);
    public static final ItemStack FLOWER_HORSE_TAME = createCustomItem(Material.GOLDEN_APPLE, 60004, FLOWER_HORSE_TAME_NAME, FLOWER_HORSE_TAME_LORE, null);
    public static final ItemStack ZOMBIE_HORSE_TAME = createCustomItem(Material.GOLDEN_APPLE, 60005, ZOMBIE_HORSE_TAME_NAME, ZOMBIE_HORSE_TAME_LORE, null);
    public static final ItemStack DRUNK_HORSE_TAME = createCustomItem(Material.GOLDEN_APPLE, 60006, DRUNK_HORSE_TAME_NAME, DRUNK_HORSE_TAME_LORE, null);
    public static final ItemStack SPEED_HORSE_TAME = createCustomItem(Material.GOLDEN_APPLE, 60007, SPEED_HORSE_TAME_NAME, SPEED_HORSE_TAME_LORE, null);
    public static final ItemStack SPEED_HORSE_ABILITY = createCustomItem(Material.POTION, 60008, SPEED_HORSE_ABILITY_NAME, SPEED_HORSE_ABILITY_LORE, Color.fromRGB(193, 193, 193));
    public static final ItemStack SEA_HORSE_TAME = createCustomItem(Material.GOLDEN_APPLE, 60009, SEA_HORSE_TAME_NAME, SEA_HORSE_TAME_LORE, null);
    public static final ItemStack PEGASUS_TAME = createCustomItem(Material.GOLDEN_APPLE, 60010, PEGASUS_HORSE_TAME_NAME, PEGASUS_TAME_LORE, null);
    public static final ItemStack PEGASUS_ABILITY = createCustomItem(Material.POTION, 60011, PEGASUS_HORSE_ABILITY_NAME, PEGASUS_ABILITY_LORE, Color.fromRGB(204,205,208));
    public static final ItemStack PARTY_BALL = createCustomItem(Material.SNOWBALL, 60012, PARTY_BALL_NAME, PARTY_BALL_LORE, null);
    public static final ItemStack PARTY_ATMOSPHERE = createCustomItem(Material.POTION, 60013, PARTY_ATMOSPHERE_NAME, PARTY_ATMOSPHERE_LORE, Color.fromRGB(255,158,54));
    public static final ItemStack RANDOM_EFFECT_POTION = createCustomItem(Material.POTION, 60014, RANDOM_EFFECT_POTION_NAME, RANDOM_EFFECT_POTION_LORE, Color.fromRGB(43,158,54));
    public static final ItemStack RANDOMIZER = createCustomItem(Material.IRON_SHOVEL, 60015, RANDOMIZER_NAME, RANDOMIZER_LORE, null);
    public static final ItemStack DARKNESS_POTION = createCustomItem(Material.POTION, 60016, DARKNESS_POTION_NAME, DARKNESS_POTION_LORE, Color.fromRGB(255,30,1));
    public static final ItemStack DARK_PEARL = createCustomItem(Material.ENDER_PEARL, 60017, DARK_PEARL_NAME, DARK_PEARL_LORE, null);
    public static final ItemStack DARK_PORTAL = createCustomItem(Material.SPLASH_POTION, 60018, DARK_PORTAL_NAME, DARK_PORTAL_LORE, Color.fromRGB(255,30,50));
    public static final ItemStack FLYING_ITEM = createCustomItem(Material.STONE_SWORD, 60019, FLYING_ITEM_NAME, FLYING_ITEM_LORE, null);
    public static final ItemStack MESSENGER_PACK = createCustomItem(Material.BUNDLE, 60020, MESSENGER_PACK_NAME, MESSENGER_PACK_LORE, null);
    public static final ItemStack SPEED_BOOTS = createCustomItem(Material.IRON_BOOTS, 60021, SPEED_BOOTS_NAME, SPEED_BOOTS_LORE, null);
    public static final ItemStack BETTER_TRIDENT = createCustomItem(Material.TRIDENT, 60022, BETTER_TRIDENT_NAME, BETTER_TRIDENT_LORE, null);
    public static final ItemStack WATER_BREATHING_CROWN = createCustomItem(Material.TURTLE_HELMET, 60023, WATER_BREATHING_CROWN_NAME, WATER_BREATHING_CROW_LORE, null);
    public static final ItemStack BREEDING_ITEM = createCustomItem(Material.SLIME_BALL, 60024, BREEDING_ITEM_NAME, BREEDING_ITEM_LORE, null);
    public static final ItemStack LIGHTNING_SPEAR = createCustomItem(Material.TRIDENT, 60025, LIGHTNING_SPEAR_NAME, LIGHTNING_SPEAR_LORE, null);
    public static final ItemStack WEATHER_CHANGER = createCustomItem(Material.POTION, 60026, WEATHER_CHANGER_NAME, WEATHER_CHANGER_LORE, Color.fromRGB(165, 161, 215));

    // TO_DO add inviz frame

    // Recipes
    public static final List<ItemStack> LIST = List.of(
            new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR),
            new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR),
            new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR)
    );
//    public static final List<ItemStack> RANDOMIZER_RECIPE = List.of(
//            new ItemStack(Material.REDSTONE), new ItemStack(Material.ENDER_PEARL), new ItemStack(Material.REDSTONE),
//            new ItemStack(Material.IRON_INGOT), new ItemStack(Material.ELYTRA), new ItemStack(Material.IRON_INGOT),
//            new ItemStack(Material.IRON_INGOT), new ItemStack(Material.REDSTONE), new ItemStack(Material.IRON_INGOT)
//    );
//    public static final List<ItemStack> BETTER_BONEMEAL_RECIPE = List.of(
//            new ItemStack(Material.AIR), new ItemStack(Material.AMETHYST_SHARD), new ItemStack(Material.AIR),
//            new ItemStack(Material.AMETHYST_SHARD), new ItemStack(Material.BONE_MEAL), new ItemStack(Material.AMETHYST_SHARD),
//            new ItemStack(Material.AIR), new ItemStack(Material.AMETHYST_SHARD), new ItemStack(Material.AIR)
//    );
//    public static final List<ItemStack> GROWTH_POTION_RECIPE = List.of(
//            new ItemStack(Material.AIR), BETTER_BONEMEAL, new ItemStack(Material.AIR),
//            BETTER_BONEMEAL, ItemUtils.createPotion(PotionType.REGENERATION), BETTER_BONEMEAL,
//            new ItemStack(Material.AIR), BETTER_BONEMEAL, new ItemStack(Material.AIR)
//    );
//    public static final List<ItemStack> HOE_OF_HARVEST_RECIPE = List.of(
//            new ItemStack(Material.GOLD_INGOT), new ItemStack(Material.WHEAT), new ItemStack(Material.GOLD_INGOT),
//            new ItemStack(Material.DIAMOND), new ItemStack(Material.NETHERITE_HOE), new ItemStack(Material.DIAMOND),
//            new ItemStack(Material.AIR), new ItemStack(Material.EMERALD), new ItemStack(Material.AIR)
//    );
//    public static final List<ItemStack> FLOWER_HORSE_TAME_RECIPE = List.of(
//            new ItemStack(Material.AIR), new ItemStack(Material.OXEYE_DAISY), new ItemStack(Material.AIR),
//            new ItemStack(Material.SPORE_BLOSSOM), new ItemStack(Material.GOLDEN_APPLE), new ItemStack(Material.SPORE_BLOSSOM),
//            new ItemStack(Material.AIR), new ItemStack(Material.SPORE_BLOSSOM), new ItemStack(Material.AIR)
//    );
    public static final List<ItemStack> ZOMBIE_HORSE_TAME_RECIPE = List.of(
            new ItemStack(Material.AIR), new ItemStack(Material.WITHER_ROSE), new ItemStack(Material.AIR),
            new ItemStack(Material.WITHER_ROSE), new ItemStack(Material.GOLDEN_APPLE), new ItemStack(Material.WITHER_ROSE),
            new ItemStack(Material.AIR), new ItemStack(Material.WITHER_ROSE), new ItemStack(Material.AIR)
    );
//    public static final List<ItemStack> DARKNESS_POTION_RECIPE = List.of(
//            new ItemStack(Material.AIR), new ItemStack(Material.BLACK_DYE), new ItemStack(Material.AIR),
//            new ItemStack(Material.WITHER_ROSE), new ItemStack(Material.PHANTOM_MEMBRANE), new ItemStack(Material.WITHER_ROSE),
//            new ItemStack(Material.AIR), new ItemStack(Material.FERMENTED_SPIDER_EYE), new ItemStack(Material.AIR)
//    );
//    public static final List<ItemStack> DARK_PEARL_RECIPE = List.of(
//            new ItemStack(Material.AIR), new ItemStack(Material.PHANTOM_MEMBRANE), new ItemStack(Material.AIR),
//            new ItemStack(Material.PHANTOM_MEMBRANE), new ItemStack(Material.ENDER_PEARL), new ItemStack(Material.PHANTOM_MEMBRANE),
//            new ItemStack(Material.AIR), new ItemStack(Material.PHANTOM_MEMBRANE), new ItemStack(Material.AIR)
//    );
//    public static final List<ItemStack> DARK_PORTAL_RECIPE = List.of(
//            new ItemStack(Material.AIR), new ItemStack(Material.GLASS_BOTTLE), new ItemStack(Material.AIR),
//            new ItemStack(Material.OBSIDIAN), new ItemStack(Material.DRAGON_BREATH), new ItemStack(Material.OBSIDIAN),
//            new ItemStack(Material.GHAST_TEAR), new ItemStack(Material.CRYING_OBSIDIAN), new ItemStack(Material.GHAST_TEAR)
//    );
//    public static final List<ItemStack> DRUNK_HORSE_TAME_RECIPE = List.of(
//            new ItemStack(Material.AIR), RANDOM_EFFECT_POTION, new ItemStack(Material.AIR),
//            RANDOM_EFFECT_POTION, new ItemStack(Material.GOLDEN_APPLE), RANDOM_EFFECT_POTION,
//            new ItemStack(Material.AIR), RANDOM_EFFECT_POTION, new ItemStack(Material.AIR)
//    );
//    public static final List<ItemStack> PARTY_BALL_RECIPE = List.of(
//            new ItemStack(Material.GUNPOWDER), new ItemStack(Material.GLOWSTONE_DUST), new ItemStack(Material.GUNPOWDER),
//            new ItemStack(Material.GLOWSTONE_DUST), new ItemStack(Material.SLIME_BALL), new ItemStack(Material.GLOWSTONE_DUST),
//            new ItemStack(Material.AIR), new ItemStack(Material.FIRE_CHARGE), new ItemStack(Material.AIR)
//    );
//    public static final List<ItemStack> PARTY_ATMOSPHERE_RECIPE = List.of(
//            new ItemStack(Material.GLOWSTONE_DUST), new ItemStack(Material.GLASS_BOTTLE), new ItemStack(Material.GLOWSTONE_DUST),
//            new ItemStack(Material.HONEY_BOTTLE), new ItemStack(Material.GOLDEN_APPLE), new ItemStack(Material.HONEY_BOTTLE),
//            new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR)
//    );
//    public static final List<ItemStack> RANDOM_EFFECT_POTION_RECIPE = List.of(
//            new ItemStack(Material.RABBIT_FOOT), new ItemStack(Material.GOLDEN_CARROT), new ItemStack(Material.GHAST_TEAR),
//            new ItemStack(Material.FERMENTED_SPIDER_EYE), new ItemStack(Material.GLASS_BOTTLE), new ItemStack(Material.PHANTOM_MEMBRANE),
//            new ItemStack(Material.AIR), new ItemStack(Material.GLOWSTONE_DUST), new ItemStack(Material.AIR)
//    );
    public static final List<ItemStack> SPEED_HORSE_TAME_RECIPE = List.of(
            new ItemStack(Material.AIR), ItemUtils.createPotion(PotionType.SWIFTNESS), new ItemStack(Material.AIR),
            ItemUtils.createPotion(PotionType.SWIFTNESS), new ItemStack(Material.GOLDEN_APPLE), ItemUtils.createPotion(PotionType.SWIFTNESS),
            new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR)
    );
    public static final List<ItemStack> SPEED_HORSE_ABILITY_RECIPE = List.of(
            new ItemStack(Material.WHEAT), new ItemStack(Material.SUGAR), new ItemStack(Material.WHEAT),
            new ItemStack(Material.AIR), new ItemStack(Material.GLASS_BOTTLE), new ItemStack(Material.AIR),
            new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR)
    );
    public static final List<ItemStack> FLYING_ITEM_RECIPE = List.of(
            new ItemStack(Material.BLAZE_POWDER), new ItemStack(Material.PHANTOM_MEMBRANE), new ItemStack(Material.BLAZE_POWDER),
            new ItemStack(Material.END_ROD), new ItemStack(Material.STICK), new ItemStack(Material.END_ROD),
            new ItemStack(Material.FEATHER), new ItemStack(Material.STICK), new ItemStack(Material.FEATHER)
    );
    public static final List<ItemStack> MESSENGER_PACK_RECIPE = List.of(
            new ItemStack(Material.LEATHER), new ItemStack(Material.STRING), new ItemStack(Material.LEATHER),
            new ItemStack(Material.CHEST), new ItemStack(Material.GOLDEN_APPLE), new ItemStack(Material.CHEST),
            new ItemStack(Material.LEATHER), new ItemStack(Material.STRING), new ItemStack(Material.LEATHER)
    );
    public static final List<ItemStack> SPEED_BOOTS_RECIPE = List.of(
            new ItemStack(Material.LEATHER), new ItemStack(Material.AIR), new ItemStack(Material.LEATHER),
            new ItemStack(Material.SUGAR), new ItemStack(Material.IRON_BOOTS), new ItemStack(Material.SUGAR),
            new ItemStack(Material.FEATHER), new ItemStack(Material.AIR), new ItemStack(Material.FEATHER)
    );
    public static final List<ItemStack> SEA_HORSE_TAME_RECIPE = List.of(
            new ItemStack(Material.AIR), new ItemStack(Material.NAUTILUS_SHELL), new ItemStack(Material.AIR),
            new ItemStack(Material.NAUTILUS_SHELL), new ItemStack(Material.GOLDEN_APPLE), new ItemStack(Material.NAUTILUS_SHELL),
            new ItemStack(Material.AIR), new ItemStack(Material.NAUTILUS_SHELL), new ItemStack(Material.AIR)
    );
    public static final List<ItemStack> PEGASUS_TAME_RECIPE = List.of(
            new ItemStack(Material.AIR), new ItemStack(Material.WIND_CHARGE), new ItemStack(Material.AIR),
            new ItemStack(Material.WIND_CHARGE), new ItemStack(Material.GOLDEN_APPLE), new ItemStack(Material.WIND_CHARGE),
            new ItemStack(Material.AIR), new ItemStack(Material.WIND_CHARGE), new ItemStack(Material.AIR)
    );
    public static final List<ItemStack> PEGASUS_ABILITY_RECIPE = List.of(
            new ItemStack(Material.AIR), new ItemStack(Material.FEATHER), new ItemStack(Material.AIR),
            new ItemStack(Material.FEATHER), new ItemStack(Material.GLASS_BOTTLE), new ItemStack(Material.FEATHER),
            new ItemStack(Material.AIR), new ItemStack(Material.FEATHER), new ItemStack(Material.AIR)
    );
    public static final List<ItemStack> BETTER_TRIDENT_RECIPE = List.of(
            new ItemStack(Material.AIR), new ItemStack(Material.HEART_OF_THE_SEA), new ItemStack(Material.AIR),
            new ItemStack(Material.NAUTILUS_SHELL), new ItemStack(Material.TRIDENT), new ItemStack(Material.NAUTILUS_SHELL),
            new ItemStack(Material.NAUTILUS_SHELL), new ItemStack(Material.BOOK), new ItemStack(Material.NAUTILUS_SHELL)
    );
    public static final List<ItemStack> WATER_BREATHING_CROWN_RECIPE = List.of(
            new ItemStack(Material.NAUTILUS_SHELL), new ItemStack(Material.NAUTILUS_SHELL), new ItemStack(Material.NAUTILUS_SHELL),
            new ItemStack(Material.NAUTILUS_SHELL), new ItemStack(Material.TURTLE_HELMET), new ItemStack(Material.NAUTILUS_SHELL),
            new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR)
    );
    public static final List<ItemStack> BREEDING_ITEM_RECIPE = List.of(
            new ItemStack(Material.AIR), new ItemStack(Material.CARROT), new ItemStack(Material.AIR),
            new ItemStack(Material.BEETROOT), new ItemStack(Material.SLIME_BALL), new ItemStack(Material.WHEAT),
            new ItemStack(Material.AIR), new ItemStack(Material.POTATO), new ItemStack(Material.AIR)
    );
    public static final List<ItemStack> LIGHTNING_SPEAR_RECIPE = List.of(
            new ItemStack(Material.IRON_INGOT), new ItemStack(Material.AIR), new ItemStack(Material.IRON_INGOT),
            new ItemStack(Material.STICK), new ItemStack(Material.TRIDENT), new ItemStack(Material.STICK),
            new ItemStack(Material.AIR), new ItemStack(Material.LIGHTNING_ROD), new ItemStack(Material.AIR)
    );
    public static final List<ItemStack> WEATHER_CHANGER_RECIPE = List.of(
            new ItemStack(Material.AIR), new ItemStack(Material.NETHER_STAR), new ItemStack(Material.AIR),
            new ItemStack(Material.DIAMOND), new ItemStack(Material.REDSTONE), new ItemStack(Material.DIAMOND),
            new ItemStack(Material.IRON_INGOT), new ItemStack(Material.BLAZE_ROD), new ItemStack(Material.IRON_INGOT)
    );
    public static final List<ItemStack> INFINITE_DEBUG_STICK_RECIPE = List.of(
            new ItemStack(Material.BEACON), new ItemStack(Material.BREEZE_ROD), new ItemStack(Material.BEACON),
            new ItemStack(Material.AIR), new ItemStack(Material.NETHERITE_BLOCK), new ItemStack(Material.AIR),
            new ItemStack(Material.AIR), new ItemStack(Material.NETHER_STAR), new ItemStack(Material.AIR)
    );
    public static final List<ItemStack> BASIC_DEBUG_STICK_RECIPE = List.of(
            new ItemStack(Material.AIR), new ItemStack(Material.DIAMOND), new ItemStack(Material.AIR),
            new ItemStack(Material.DIAMOND), new ItemStack(Material.BREEZE_ROD), new ItemStack(Material.DIAMOND),
            new ItemStack(Material.AIR), new ItemStack(Material.DIAMOND), new ItemStack(Material.AIR)
    );
    public static ItemStack createInfiniteDebugStick() {
        ItemStack itemStack = new ItemStack(Material.DEAD_BUSH);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "NEKONEČNÝ DEBUG STICK");
        // Set the lore (description) of the item with color and multiple lines
        meta.setLore(Arrays.asList(
                ChatColor.GOLD + "PRAVÉ-KLIKNUTÍ",
                ChatColor.GRAY + "pro změnění hodnoty",
                "",
                ChatColor.GREEN + "LEVÉ-KLIKNUTÍ",
                ChatColor.GRAY + "pro změnění vlastnosti",
                "",
                ChatColor.RED + "SHIFT + PRAVÉ-KLIKNUTÍ",
                ChatColor.GRAY + "pro vymazání trvalých hodnot",
                "",
                ChatColor.DARK_PURPLE + "SHIFT + LEVÉ-KLIKNUTÍ",
                ChatColor.GRAY + "pro vidění všech vlastností",
                "",
                ChatColor.YELLOW + "Výdrž: " + ChatColor.GOLD + "∞"
        ));
        meta.setUnbreakable(true);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
    public static ItemStack createBasicDebugStick() {
        // Create the item stack with the desired material (in this case, a blaze rod)
        ItemStack item = new ItemStack(Material.BREEZE_ROD);

        // Get the item meta (for setting name, lore, etc.)
        ItemMeta meta = item.getItemMeta();

        // Set the custom name (with color)
        meta.setDisplayName(ChatColor.AQUA + "ZÁKLADNÍ DEBUG STICK");

        // Set the lore (description) of the item with color and multiple lines
        meta.setLore(Arrays.asList(
                ChatColor.GOLD + "PRAVÉ-KLIKNUTÍ",
                ChatColor.GRAY + "pro změnění hodnoty",
                "",
                ChatColor.GREEN + "LEVÉ-KLIKNUTÍ",
                ChatColor.GRAY + "pro změnění vlastnosti",
                "",
                ChatColor.RED + "SHIFT + PRAVÉ-KLIKNUTÍ",
                ChatColor.GRAY + "pro vymazání trvalých hodnot",
                "",
                ChatColor.DARK_PURPLE + "SHIFT + LEVÉ-KLIKNUTÍ",
                ChatColor.GRAY + "pro vidění všech vlastností",
                "",
                ChatColor.YELLOW + "Výdrž: " + ChatColor.GREEN + "250"
        ));
        // Set the meta back to the item
        item.setItemMeta(meta);

        return item;
    }



    // Lists of enchantments for items
    List<Map.Entry<Enchantment, Integer>> betterTridentEnchantments = new ArrayList<>();
    List<Map.Entry<Enchantment, Integer>> waterBreathingCrownEnchantments = new ArrayList<>();

    public CustomItems(Plugin plugin) {
        this.plugin = plugin;

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
        //create and register recipes
        registerRecipes(plugin);
    }


    private void registerRecipes(Plugin plugin) {
        BETTER_BONEMEAL_RECIPE = Plugin.getRecipe("BETTER_BONEMEAL");
        GROWTH_POTION_RECIPE = Plugin.getRecipe("GROWTH_POTION");
        RANDOMIZER_RECIPE = Plugin.getRecipe("RANDOMIZER");
        HOE_OF_HARVEST_RECIPE = Plugin.getRecipe("HOE_OF_THE_HARVEST");
        FLOWER_HORSE_TAME_RECIPE = Plugin.getRecipe("FLOWER_HORSE_TAME");
        PARTY_ATMOSPHERE_RECIPE = Plugin.getRecipe("PARTY_ATMOSPHERE");
        PARTY_BALL_RECIPE = Plugin.getRecipe("PARTY_BALL");
        RANDOM_EFFECT_POTION_RECIPE = Plugin.getRecipe("RANDOM_EFFECT_POTION");
        DRUNK_HORSE_TAME_RECIPE = Plugin.getRecipe("DRUNK_HORSE_TAME");
        DARKNESS_POTION_RECIPE = Plugin.getRecipe("DARKNESS_POTION");
        DARK_PEARL_RECIPE = Plugin.getRecipe("DARK_PEARL");
        DARK_PORTAL_RECIPE = Plugin.getRecipe("DARK_PORTAL");

        //Demeter Key
        NamespacedKey betterBonemealkey = new NamespacedKey(plugin, "better_bonemeal_recipe");
        NamespacedKey growthPotionKey = new NamespacedKey(plugin, "growth_potion_recipe");
        NamespacedKey hoeOfHarvestKey = new NamespacedKey(plugin, "hoe_of_harvest_recipe");
        NamespacedKey flowerHorseTameKey = new NamespacedKey(plugin, "flower_horse_tame_recipe");
        //Hades Key
        NamespacedKey zombieHorseTameKey = new NamespacedKey(plugin, "zombie_horse_tame_recipe");
        NamespacedKey darknessPotionKey = new NamespacedKey(plugin, "darkness_potion_recipe");
        NamespacedKey darkPearlKey = new NamespacedKey(plugin, "dark_pearl_recipe");
        NamespacedKey darkPortalKey = new NamespacedKey(plugin, "dark_portal_recipe");
        //Dionysus Key
        NamespacedKey drunkHorseTameKey = new NamespacedKey(plugin, "drunk_horse_tame_recipe");
        NamespacedKey partyBallKey = new NamespacedKey(plugin, "party_ball_recipe");
        NamespacedKey partyAtmosphereKey = new NamespacedKey(plugin, "party_atmosphere_recipe");
        NamespacedKey randomEffectPotionKey = new NamespacedKey(plugin, "random_effect_potion_recipe");
        //Hermes Key
        NamespacedKey speedHorseTameKey = new NamespacedKey(plugin, "speed_horse_tame_recipe");
        NamespacedKey speedHorseAbilityKey = new NamespacedKey(plugin, "speed_horse_ability_recipe");
        NamespacedKey flyingItemKey = new NamespacedKey(plugin, "flying_item_recipe");
        NamespacedKey messengerPackKey = new NamespacedKey(plugin, "messenger_pack_recipe");
        NamespacedKey speedBootsKey = new NamespacedKey(plugin, "speed_boots_recipe");
        //Poseidon Key
        NamespacedKey seaHorseTameKey = new NamespacedKey(plugin, "sea_horse_tame_recipe");
        NamespacedKey betterTridentKey = new NamespacedKey(plugin, "better_trident_recipe");
        NamespacedKey waterBreathingCrownKey = new NamespacedKey(plugin, "water_breathing_crown_recipe");
        //Zeus Key
        NamespacedKey pegasusTameKey = new NamespacedKey(plugin, "pegasus_tame_recipe");
        NamespacedKey pegasusAbilityKey = new NamespacedKey(plugin, "pegasus_ability_recipe");
        NamespacedKey breedingItemKey = new NamespacedKey(plugin, "breeding_item_recipe");
        NamespacedKey lightningSpearKey = new NamespacedKey(plugin, "lightning_spear_recipe");
        NamespacedKey weatherChangerKey = new NamespacedKey(plugin, "weather_changer_recipe");
        //Other Key
        NamespacedKey randomizerKey = new NamespacedKey(plugin, "randomizer_recipe");
        NamespacedKey infiniteDebugKey = new NamespacedKey(plugin, "infinite_debug_recipe");

        //Demeter
        ShapedRecipe betterBonemealRecipe = createShapedRecipeFromList(BETTER_BONEMEAL, BETTER_BONEMEAL_RECIPE, betterBonemealkey);
        ShapedRecipe growthPotionRecipe = createShapedRecipeFromList(GROWTH_POTION, GROWTH_POTION_RECIPE, growthPotionKey);
        ShapedRecipe hoeOfHarvestRecipe = createShapedRecipeFromList(HOE_OF_HARVEST, HOE_OF_HARVEST_RECIPE, hoeOfHarvestKey);
        ShapedRecipe flowerHorseTameRecipe = createShapedRecipeFromList(FLOWER_HORSE_TAME, FLOWER_HORSE_TAME_RECIPE, flowerHorseTameKey);
        //Hades
        ShapedRecipe zombieHorseTameRecipe = createShapedRecipeFromList(ZOMBIE_HORSE_TAME, ZOMBIE_HORSE_TAME_RECIPE, zombieHorseTameKey, " S ", "SAS", " S ");
        ShapedRecipe darknessPotionRecipe = createShapedRecipeFromList(DARKNESS_POTION, DARKNESS_POTION_RECIPE, darknessPotionKey);
        ShapedRecipe darkPearlRecipe = createShapedRecipeFromList(DARK_PEARL, DARK_PEARL_RECIPE, darkPearlKey);
        ShapedRecipe darkPortalRecipe = createShapedRecipeFromList(DARK_PORTAL, DARK_PORTAL_RECIPE, darkPortalKey);
        //Dionysus
        ShapedRecipe drunkHorseTameRecipe = createShapedRecipeFromList(DRUNK_HORSE_TAME, DRUNK_HORSE_TAME_RECIPE, drunkHorseTameKey);
        ShapedRecipe partyBallRecipe = createShapedRecipeFromList(PARTY_BALL, PARTY_BALL_RECIPE, partyBallKey);
        ShapedRecipe partyAtmosphereRecipe = createShapedRecipeFromList(PARTY_ATMOSPHERE, PARTY_ATMOSPHERE_RECIPE, partyAtmosphereKey);
        ShapedRecipe randomPotionEffectRecipe = createShapedRecipeFromList(RANDOM_EFFECT_POTION, RANDOM_EFFECT_POTION_RECIPE, randomEffectPotionKey);
        //Hermes
        ShapedRecipe speedHorseTameRecipe = createShapedRecipeFromList(SPEED_HORSE_TAME, SPEED_HORSE_TAME_RECIPE, speedHorseTameKey, " S ", "SGS", "   ");
        ShapedRecipe speedHorseAbilityRecipe = createShapedRecipeFromList(SPEED_HORSE_ABILITY, SPEED_HORSE_ABILITY_RECIPE, speedHorseAbilityKey, "WSW", " G ", "   ");
        ShapedRecipe flyingItemRecipe = createShapedRecipeFromList(FLYING_ITEM, FLYING_ITEM_RECIPE, flyingItemKey, "BPB", "ESE", "FSF");
        ShapedRecipe messengerPackRecipe = createShapedRecipeFromList(MESSENGER_PACK, MESSENGER_PACK_RECIPE, messengerPackKey, "LSL", "CLC", "LSL");
        ShapedRecipe speedBootsRecipe = createShapedRecipeFromList(SPEED_BOOTS, SPEED_BOOTS_RECIPE, speedBootsKey, "L L", "CBC", "L L");
        //Poseidon
        ShapedRecipe seaHorseTameRecipe = createShapedRecipeFromList(SEA_HORSE_TAME, SEA_HORSE_TAME_RECIPE, seaHorseTameKey, " N ", "NAN", " N ");
        ShapedRecipe betterTridentRecipe = createShapedRecipeFromList(BETTER_TRIDENT, BETTER_TRIDENT_RECIPE, betterTridentKey, " N ", "HTH", "HBH");
        ShapedRecipe waterBreathingCrownRecipe = createShapedRecipeFromList(WATER_BREATHING_CROWN, WATER_BREATHING_CROWN_RECIPE, waterBreathingCrownKey, "NNN", "NHN", "   ");
        //Zeus
        ShapedRecipe pegasusTameRecipe = createShapedRecipeFromList(PEGASUS_TAME, PEGASUS_TAME_RECIPE, pegasusTameKey, " N ", "NAN", " N ");
        ShapedRecipe pegasusAbilityRecipe = createShapedRecipeFromList(PEGASUS_ABILITY, PEGASUS_ABILITY_RECIPE, pegasusAbilityKey, " N ", "NAN", " N ");
        ShapedRecipe breedingItemRecipe = createShapedRecipeFromList(BREEDING_ITEM, BREEDING_ITEM_RECIPE, breedingItemKey, " C ", "BSW", " P ");
        ShapedRecipe lightningSpearRecipe = createShapedRecipeFromList(LIGHTNING_SPEAR, LIGHTNING_SPEAR_RECIPE, lightningSpearKey, "I I", "SBS", " L ");
        ShapedRecipe weatherChangerRecipe = createShapedRecipeFromList(WEATHER_CHANGER, WEATHER_CHANGER_RECIPE, weatherChangerKey, " N ", "DRD", "IBI");
        // Other
        ShapedRecipe randomizerRecipe = createShapedRecipeFromList(RANDOMIZER, RANDOMIZER_RECIPE, randomizerKey);
        Bukkit.addRecipe(randomizerRecipe);
        //Demeter Register
        Bukkit.addRecipe(betterBonemealRecipe);
        Bukkit.addRecipe(growthPotionRecipe);
        Bukkit.addRecipe(hoeOfHarvestRecipe);
        Bukkit.addRecipe(flowerHorseTameRecipe);
        //Hades Register
        Bukkit.addRecipe(zombieHorseTameRecipe);
        Bukkit.addRecipe(darknessPotionRecipe);
        Bukkit.addRecipe(darkPearlRecipe);
        Bukkit.addRecipe(darkPortalRecipe);
        //Dionysus Register
        Bukkit.addRecipe(drunkHorseTameRecipe);
        Bukkit.addRecipe(partyBallRecipe);
        Bukkit.addRecipe(partyAtmosphereRecipe);
        Bukkit.addRecipe(randomPotionEffectRecipe);
        //Hermes Register
        Bukkit.addRecipe(speedHorseTameRecipe);
        Bukkit.addRecipe(speedHorseAbilityRecipe);
        Bukkit.addRecipe(flyingItemRecipe);
        Bukkit.addRecipe(messengerPackRecipe);
        Bukkit.addRecipe(speedBootsRecipe);
        //Poseidon Register
        Bukkit.addRecipe(seaHorseTameRecipe);
        Bukkit.addRecipe(betterTridentRecipe);
        Bukkit.addRecipe(waterBreathingCrownRecipe);
        //Zeus Register
        Bukkit.addRecipe(pegasusTameRecipe);
        Bukkit.addRecipe(pegasusAbilityRecipe);
        Bukkit.addRecipe(breedingItemRecipe);
        Bukkit.addRecipe(lightningSpearRecipe);
        Bukkit.addRecipe(weatherChangerRecipe);

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

    private ShapedRecipe createShapedRecipeFromList(ItemStack result, List<ItemStack> ingredients, NamespacedKey key, String row1, String row2, String row3) {
        // Create a mutable copy of the ingredients list
        List<ItemStack> mutableIngredients = new ArrayList<>(ingredients);

        ArrayList<Character> charList = new ArrayList<>();
        ShapedRecipe recipe = new ShapedRecipe(key, result);

        // Define the shape of the recipe
        recipe.shape(row1, row2, row3);

        // Combine the rows to form a single string for easy processing
        String combined = row1 + row2 + row3;
        for (int i = 0; i < 9; i++) {
            char charAt = combined.charAt(i);
            charList.add(charAt);
        }

        // Remove White Spaces
        charList.removeIf(ch -> ch == ' ');

        // Remove duplicates by converting to a LinkedHashSet (preserves order)
        Set<Character> charSet = new LinkedHashSet<>(charList);

        // Convert back to an ArrayList if needed
        ArrayList<Character> uniqueCharList = new ArrayList<>(charSet);

        // Remove all instances of Material.AIR from mutableIngredients
        mutableIngredients.removeIf(item -> item.getType() == Material.AIR);

        // Remove duplicates from ingredients
        Set<ItemStack> uniqueItemStackSet = new LinkedHashSet<>(mutableIngredients);

        // Convert back to an ArrayList if needed
        List<ItemStack> uniqueItemStackList = new ArrayList<>(uniqueItemStackSet);

        // Assign each character in uniqueCharList to the corresponding Material from uniqueItemStackList
        for (int i = 0; i < uniqueCharList.size(); i++) {
            char ingredientChar = uniqueCharList.get(i);
            Material material = uniqueItemStackList.get(i).getType();
            recipe.setIngredient(ingredientChar, material);
        }
        return recipe;
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
