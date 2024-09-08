package me.figgnus.aeterum.items;
import me.figgnus.aeterum.AeterumX;

import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;

import java.util.*;

import static me.figgnus.aeterum.utils.ItemUtils.getProfile;


public class CustomItems {

    private static final String BETTER_BONEMEAL_NAME = AeterumX.getItemName("BETTER_BONEMEAL");
    private static final String GROWTH_POTION_NAME = AeterumX.getItemName("GROWTH_POTION");
    private static final String HOE_OF_HARVEST_NAME = AeterumX.getItemName("HOE_OF_THE_HARVEST");
    private static final String FLOWER_HORSE_TAME_NAME = AeterumX.getItemName("FLOWER_HORSE_TAME");
    private static final String ZOMBIE_HORSE_TAME_NAME = AeterumX.getItemName("ZOMBIE_HORSE_TAME");
    private static final String DRUNK_HORSE_TAME_NAME = AeterumX.getItemName("DRUNK_HORSE_TAME");
    private static final String SPEED_HORSE_TAME_NAME = AeterumX.getItemName("SPEED_HORSE_TAME");
    private static final String SPEED_HORSE_ABILITY_NAME = AeterumX.getItemName("SPEED_HORSE_ABILITY");
    private static final String SEA_HORSE_TAME_NAME = AeterumX.getItemName("SEA_HORSE_TAME");
    private static final String PEGASUS_HORSE_TAME_NAME = AeterumX.getItemName("PEGASUS_HORSE_TAME");
    private static final String PEGASUS_HORSE_ABILITY_NAME = AeterumX.getItemName("PEGASUS_HORSE_ABILITY");
    private static final String PARTY_BALL_NAME = AeterumX.getItemName("PARTY_BALL");
    private static final String PARTY_ATMOSPHERE_NAME = AeterumX.getItemName("PARTY_ATMOSPHERE");
    private static final String RANDOM_EFFECT_POTION_NAME = AeterumX.getItemName("RANDOM_EFFECT_POTION");
    private static final String RANDOMIZER_NAME = AeterumX.getItemName("RANDOMIZER");
    private static final String DARKNESS_POTION_NAME = AeterumX.getItemName("DARKNESS_POTION");
    private static final String DARK_PEARL_NAME = AeterumX.getItemName("DARK_PEARL");
    private static final String DARK_PORTAL_NAME = AeterumX.getItemName("DARK_PORTAL");
    private static final String FLYING_ITEM_NAME = AeterumX.getItemName("FLYING_ITEM");
    private static final String MESSENGER_PACK_NAME = AeterumX.getItemName("MESSENGER_PACK");
    private static final String SPEED_BOOTS_NAME = AeterumX.getItemName("SPEED_BOOTS");
    private static final String BETTER_TRIDENT_NAME = AeterumX.getItemName("BETTER_TRIDENT");
    private static final String WATER_BREATHING_CROWN_NAME = AeterumX.getItemName("WATER_BREATHING_CROWN");
    private static final String BREEDING_ITEM_NAME = AeterumX.getItemName("BREEDING_ITEM");
    private static final String LIGHTNING_SPEAR_NAME = AeterumX.getItemName("LIGHTNING_SPEAR");
    private static final String WEATHER_CHANGER_NAME = AeterumX.getItemName("WEATHER_CHANGER");

    //Lore
    private static final List<String> BETTER_BONEMEAL_LORE = List.of(ChatColor.GRAY + AeterumX.getItemLore("BETTER_BONEMEAL"));
    private static final List<String> GROWTH_POTION_LORE = List.of(ChatColor.GRAY + AeterumX.getItemLore("GROWTH_POTION"));
    private static final List<String> HOE_OF_HARVEST_LORE = List.of(ChatColor.GRAY + AeterumX.getItemLore("HOE_OF_THE_HARVEST"));
    private static final List<String> FLOWER_HORSE_TAME_LORE = List.of(ChatColor.GRAY + AeterumX.getItemLore("FLOWER_HORSE_TAME"));
    private static final List<String> ZOMBIE_HORSE_TAME_LORE = List.of(ChatColor.GRAY + AeterumX.getItemLore("ZOMBIE_HORSE_TAME"));
    private static final List<String> DRUNK_HORSE_TAME_LORE = List.of(ChatColor.GRAY + AeterumX.getItemLore("DRUNK_HORSE_TAME"));
    private static final List<String> SPEED_HORSE_TAME_LORE = List.of(ChatColor.GRAY + AeterumX.getItemLore("SPEED_HORSE_TAME"));
    private static final List<String> SPEED_HORSE_ABILITY_LORE = List.of(ChatColor.GRAY + AeterumX.getItemLore("SPEED_HORSE_ABILITY"));
    private static final List<String> SEA_HORSE_TAME_LORE = List.of(ChatColor.GRAY + AeterumX.getItemLore("SEA_HORSE_TAME"));
    private static final List<String> PEGASUS_TAME_LORE = List.of(ChatColor.GRAY + AeterumX.getItemLore("PEGASUS_HORSE_TAME"));
    private static final List<String> PEGASUS_ABILITY_LORE = List.of(ChatColor.GRAY + AeterumX.getItemLore("PEGASUS_HORSE_ABILITY"));
    private static final List<String> PARTY_BALL_LORE = List.of(ChatColor.GRAY + AeterumX.getItemLore("PARTY_BALL"));
    private static final List<String> PARTY_ATMOSPHERE_LORE = List.of(ChatColor.GRAY + AeterumX.getItemLore("PARTY_ATMOSPHERE"));
    private static final List<String> RANDOM_EFFECT_POTION_LORE = List.of(ChatColor.GRAY + AeterumX.getItemLore("RANDOM_EFFECT_POTION"));
    private static final List<String> RANDOMIZER_LORE = List.of(ChatColor.GRAY + AeterumX.getItemLore("RANDOMIZER"));
    private static final List<String> DARKNESS_POTION_LORE = List.of(ChatColor.GRAY + AeterumX.getItemLore("DARKNESS_POTION"));
    private static final List<String> DARK_PEARL_LORE = List.of(ChatColor.GRAY + AeterumX.getItemLore("DARK_PEARL"));
    private static final List<String> DARK_PORTAL_LORE = List.of(ChatColor.GRAY + AeterumX.getItemLore("DARK_PORTAL"));
    private static final List<String> FLYING_ITEM_LORE = List.of(ChatColor.GRAY + AeterumX.getItemLore("FLYING_ITEM"));
    private static final List<String> MESSENGER_PACK_LORE = List.of(ChatColor.GRAY + AeterumX.getItemLore("MESSENGER_PACK"));
    private static final List<String> SPEED_BOOTS_LORE = List.of(ChatColor.GRAY + AeterumX.getItemLore("SPEED_BOOTS"));
    private static final List<String> BETTER_TRIDENT_LORE = List.of(ChatColor.GRAY + AeterumX.getItemLore("BETTER_TRIDENT"));
    private static final List<String> WATER_BREATHING_CROW_LORE = List.of(ChatColor.GRAY + AeterumX.getItemLore("WATER_BREATHING_CROWN"));
    private static final List<String> BREEDING_ITEM_LORE = List.of(ChatColor.GRAY + AeterumX.getItemLore("BREEDING_ITEM"));
    private static final List<String> LIGHTNING_SPEAR_LORE = List.of(ChatColor.GRAY + AeterumX.getItemLore("LIGHTNING_SPEAR"));
    private static final List<String> WEATHER_CHANGER_LORE = List.of(ChatColor.GRAY + AeterumX.getItemLore("WEATHER_CHANGER"));

    //ItemStack
    public static final ItemStack BETTER_BONEMEAL = createCustomItem(AeterumX.getItemMaterial("BETTER_BONEMEAL"), 60001, BETTER_BONEMEAL_NAME, BETTER_BONEMEAL_LORE, null, null);
    public static final ItemStack GROWTH_POTION = createCustomItem(AeterumX.getItemMaterial("GROWTH_POTION"), 60002, GROWTH_POTION_NAME, GROWTH_POTION_LORE, AeterumX.getItemColor("GROWTH_POTION"), null);
    public static final ItemStack HOE_OF_HARVEST = createCustomItem(AeterumX.getItemMaterial("HOE_OF_THE_HARVEST"), 60003, HOE_OF_HARVEST_NAME, HOE_OF_HARVEST_LORE, null, null);
    public static final ItemStack FLOWER_HORSE_TAME = createCustomItem(AeterumX.getItemMaterial("FLOWER_HORSE_TAME"), 60004, FLOWER_HORSE_TAME_NAME, FLOWER_HORSE_TAME_LORE, null, null);
    public static final ItemStack ZOMBIE_HORSE_TAME = createCustomItem(AeterumX.getItemMaterial("ZOMBIE_HORSE_TAME"), 60005, ZOMBIE_HORSE_TAME_NAME, ZOMBIE_HORSE_TAME_LORE, null, null);
    public static final ItemStack DRUNK_HORSE_TAME = createCustomItem(AeterumX.getItemMaterial("DRUNK_HORSE_TAME"), 60006, DRUNK_HORSE_TAME_NAME, DRUNK_HORSE_TAME_LORE, null, null);
    public static final ItemStack SPEED_HORSE_TAME = createCustomItem(AeterumX.getItemMaterial("SPEED_HORSE_TAME"), 60007, SPEED_HORSE_TAME_NAME, SPEED_HORSE_TAME_LORE, null, null);
    public static final ItemStack SPEED_HORSE_ABILITY = createCustomItem(AeterumX.getItemMaterial("SPEED_HORSE_ABILITY"), 60008, SPEED_HORSE_ABILITY_NAME, SPEED_HORSE_ABILITY_LORE, AeterumX.getItemColor("SPEED_HORSE_ABILITY"), null);
    public static final ItemStack SEA_HORSE_TAME = createCustomItem(AeterumX.getItemMaterial("SEA_HORSE_TAME"), 60009, SEA_HORSE_TAME_NAME, SEA_HORSE_TAME_LORE, null, null);
    public static final ItemStack PEGASUS_TAME = createCustomItem(AeterumX.getItemMaterial("PEGASUS_HORSE_TAME"), 60010, PEGASUS_HORSE_TAME_NAME, PEGASUS_TAME_LORE, null, null);
    public static final ItemStack PEGASUS_ABILITY = createCustomItem(AeterumX.getItemMaterial("PEGASUS_HORSE_ABILITY"), 60011, PEGASUS_HORSE_ABILITY_NAME, PEGASUS_ABILITY_LORE, AeterumX.getItemColor("PEGASUS_HORSE_ABILITY"), null);
    public static final ItemStack PARTY_BALL = createCustomItem(AeterumX.getItemMaterial("PARTY_BALL"), 60012, PARTY_BALL_NAME, PARTY_BALL_LORE, null, null);
    public static final ItemStack PARTY_ATMOSPHERE = createCustomItem(AeterumX.getItemMaterial("PARTY_ATMOSPHERE"), 60013, PARTY_ATMOSPHERE_NAME, PARTY_ATMOSPHERE_LORE, AeterumX.getItemColor("PARTY_ATMOSPHERE"), null);
    public static final ItemStack RANDOM_EFFECT_POTION = createCustomItem(AeterumX.getItemMaterial("RANDOM_EFFECT_POTION"), 60014, RANDOM_EFFECT_POTION_NAME, RANDOM_EFFECT_POTION_LORE, AeterumX.getItemColor("RANDOM_EFFECT_POTION"), null);
    public static final ItemStack RANDOMIZER = createCustomItem(AeterumX.getItemMaterial("RANDOMIZER"), 60015, RANDOMIZER_NAME, RANDOMIZER_LORE, null, null);
    public static final ItemStack DARKNESS_POTION = createCustomItem(AeterumX.getItemMaterial("DARKNESS_POTION"), 60016, DARKNESS_POTION_NAME, DARKNESS_POTION_LORE, AeterumX.getItemColor("DARKNESS_POTION"), null);
    public static final ItemStack DARK_PEARL = createCustomItem(AeterumX.getItemMaterial("DARK_PEARL"), 60017, DARK_PEARL_NAME, DARK_PEARL_LORE, null, null);
    public static final ItemStack DARK_PORTAL = createCustomItem(AeterumX.getItemMaterial("DARK_PORTAL"), 60018, DARK_PORTAL_NAME, DARK_PORTAL_LORE, AeterumX.getItemColor("DARK_PORTAL"), null);
    public static final ItemStack FLYING_ITEM = createCustomItem(AeterumX.getItemMaterial("FLYING_ITEM"), 60019, FLYING_ITEM_NAME, FLYING_ITEM_LORE, null, null);
    public static final ItemStack MESSENGER_PACK = createCustomItem(AeterumX.getItemMaterial("MESSENGER_PACK"), 60020, MESSENGER_PACK_NAME, MESSENGER_PACK_LORE, null, "e92dfa61a324207488e45c79d0355d2163b7a3b35f0e958f88f5423484360978");
    public static final ItemStack SPEED_BOOTS = createCustomItem(AeterumX.getItemMaterial("SPEED_BOOTS"), 60021, SPEED_BOOTS_NAME, SPEED_BOOTS_LORE, null, null);
    public static final ItemStack BETTER_TRIDENT = createCustomItem(AeterumX.getItemMaterial("BETTER_TRIDENT"), 60022, BETTER_TRIDENT_NAME, BETTER_TRIDENT_LORE, null, null);
    public static final ItemStack WATER_BREATHING_CROWN = createCustomItem(AeterumX.getItemMaterial("WATER_BREATHING_CROWN"), 60023, WATER_BREATHING_CROWN_NAME, WATER_BREATHING_CROW_LORE, null, null);
    public static final ItemStack BREEDING_ITEM = createCustomItem(AeterumX.getItemMaterial("BREEDING_ITEM"), 60024, BREEDING_ITEM_NAME, BREEDING_ITEM_LORE, null, null);
    public static final ItemStack LIGHTNING_SPEAR = createCustomItem(AeterumX.getItemMaterial("LIGHTNING_SPEAR"), 60025, LIGHTNING_SPEAR_NAME, LIGHTNING_SPEAR_LORE, null, null);
    public static final ItemStack WEATHER_CHANGER = createCustomItem(AeterumX.getItemMaterial("WEATHER_CHANGER"), 60026, WEATHER_CHANGER_NAME, WEATHER_CHANGER_LORE, AeterumX.getItemColor("WEATHER_CHANGER"), null);


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


    public static ItemStack createCustomItem(Material material, int id, String displayName, List<String> lore, Color color, String texture) {
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
        if (itemStack.getType() == Material.PLAYER_HEAD){
            if (meta instanceof SkullMeta){
                String url = "https://textures.minecraft.net/texture/" + texture;
                PlayerProfile profile = getProfile(url);
                ((SkullMeta) meta).setOwnerProfile(profile);
            }
        }

        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
