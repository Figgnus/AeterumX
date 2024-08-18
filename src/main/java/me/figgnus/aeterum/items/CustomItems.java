package me.figgnus.aeterum.items;

import me.figgnus.aeterum.Plugin;
import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;


import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class CustomItems {
    private final Plugin plugin;

    //Lore
    private static final List<String> BETTER_BONEMEAL_LORE = List.of(ChatColor.GRAY + "Bonemeal který se dá použít na Cactus a Sugar Cane");
    private static final List<String> GROWTH_POTION_LORE = List.of(ChatColor.GRAY + "Lektvar který urychlý růst rostlin v okolí hráče");
    private static final List<String> HOE_OF_HARVEST_LORE = List.of(ChatColor.GRAY + "Jestli-že někdo vý jak farmařit tak jsi to ty.");
    private static final List<String> FLOWER_HORSE_TAME_LORE = List.of(ChatColor.GRAY + "Jablko kterým Demeter může ochočit svého koňe");
    private static final List<String> ZOMBIE_HORSE_TAME_LORE = List.of(ChatColor.GRAY + "Jablko kterým Hades může ochočit svého koňe");
    private static final List<String> DRUNK_HORSE_TAME_LORE = List.of(ChatColor.GRAY + "Jablko kterým Dionysus může ochočit svého koňe");
    private static final List<String> SPEED_HORSE_TAME_LORE = List.of(ChatColor.GRAY + "Jablko kterým Hermes může ochočit svého koňe");
    private static final List<String> SPEED_HORSE_ABILITY_LORE = List.of(ChatColor.GRAY + "Sedni na svého koně, napij se a drž se");
    private static final List<String> SEA_HORSE_TAME_LORE = List.of(ChatColor.GRAY + "Jablko kterým Poseidon může ochočit svého koňe");
    private static final List<String> PEGASUS_TAME_LORE = List.of(ChatColor.GRAY + "Jablko kterým Zeus může ochočit svého koňe");
    private static final List<String> PEGASUS_ABILITY_LORE = List.of(ChatColor.GRAY + "Napij se když si na svém koni");
    private static final List<String> PARTY_BALL_LORE = List.of(ChatColor.GRAY + "Pozor na výbuch");
    private static final List<String> PARTY_ATMOSPHERE_LORE = List.of(ChatColor.GRAY + "Let's Party!!!");
    private static final List<String> RANDOM_EFFECT_POTION_LORE = List.of(ChatColor.GRAY + "Kdo vý co se může stát");
    private static final List<String> RANDOMIZER_LORE = List.of(ChatColor.GRAY + "Položí náhodný block s hotbaru");
    private static final List<String> DARKNESS_POTION_LORE = List.of(ChatColor.GRAY + "Oslep své nepřátele");
    private static final List<String> DARK_PEARL_LORE = List.of(ChatColor.GRAY + "Prostě Ender Pearl. Možná trochu lepší.");
    private static final List<String> DARK_PORTAL_LORE = List.of(ChatColor.GRAY + "Kde se objevíš?");
    private static final List<String> FLYING_ITEM_LORE = List.of(ChatColor.GRAY + "Málo raketek? Použij toto.");
    private static final List<String> MESSENGER_PACK_LORE = List.of(ChatColor.GRAY + "Bůh zpráv potřebuje pořádnou brašnu");
    private static final List<String> SPEED_BOOTS_LORE = List.of(ChatColor.GRAY + "Tyhle botky vypadají rychle. Nezakopni.");

    //ItemStack
    public static final ItemStack BETTER_BONEMEAL = createCustomItem(Material.BONE_MEAL, 60001, "Vylepšená Kostní Moučka", BETTER_BONEMEAL_LORE, null);
    public static final ItemStack GROWTH_POTION = createCustomItem(Material.POTION, 60002, "Lektvar Růstu", GROWTH_POTION_LORE, Color.fromRGB(63, 206, 130));
    public static final ItemStack HOE_OF_HARVEST = createCustomItem(Material.STICK, 60003, "Kosa Úrody", HOE_OF_HARVEST_LORE, null);
    public static final ItemStack FLOWER_HORSE_TAME = createCustomItem(Material.APPLE, 60004, "Sladké Jablko", FLOWER_HORSE_TAME_LORE, null);
    public static final ItemStack ZOMBIE_HORSE_TAME = createCustomItem(Material.APPLE, 60005, "Otrávené Jablko", ZOMBIE_HORSE_TAME_LORE, null);
    public static final ItemStack DRUNK_HORSE_TAME = createCustomItem(Material.APPLE, 60006, "Fermentované Jablko", DRUNK_HORSE_TAME_LORE, null);
    public static final ItemStack SPEED_HORSE_TAME = createCustomItem(Material.APPLE, 60007, "Rychlé Jablko", SPEED_HORSE_TAME_LORE, null);
    public static final ItemStack SPEED_HORSE_ABILITY = createCustomItem(Material.POTION, 60008, "Lektvar Rychlích Kopit", SPEED_HORSE_ABILITY_LORE, Color.fromRGB(193, 193, 193));
    public static final ItemStack SEA_HORSE_TAME = createCustomItem(Material.APPLE, 60009, "Slané Jablko", SEA_HORSE_TAME_LORE, null);
    public static final ItemStack PEGASUS_TAME = createCustomItem(Material.APPLE, 60010, "Levitující Jablko", PEGASUS_TAME_LORE, null);
    public static final ItemStack PEGASUS_ABILITY = createCustomItem(Material.POTION, 60011, "Lektvar Levitujících Kopit", PEGASUS_ABILITY_LORE, Color.fromRGB(204,205,208));
    public static final ItemStack PARTY_BALL = createCustomItem(Material.SNOWBALL, 60012, "Party Koule", PARTY_BALL_LORE, null);
    public static final ItemStack PARTY_ATMOSPHERE = createCustomItem(Material.POTION, 60013, "Party Atmosféra", PARTY_ATMOSPHERE_LORE, Color.fromRGB(255,158,54));
    public static final ItemStack RANDOM_EFFECT_POTION = createCustomItem(Material.POTION, 60014, "Záhadný Nápoj", RANDOM_EFFECT_POTION_LORE, Color.fromRGB(43,158,54));
    public static final ItemStack RANDOMIZER = createCustomItem(Material.STICK, 60015, "Randomizer", RANDOMIZER_LORE, null);
    public static final ItemStack DARKNESS_POTION = createCustomItem(Material.POTION, 60016, "Temný Lektvar", DARKNESS_POTION_LORE, Color.fromRGB(255,30,1));
    public static final ItemStack DARK_PEARL = createCustomItem(Material.ENDER_PEARL, 60017, "Temná Perla", DARK_PEARL_LORE, null);
    public static final ItemStack DARK_PORTAL = createCustomItem(Material.SPLASH_POTION, 60018, "Temný Portál", DARK_PORTAL_LORE, Color.fromRGB(255,30,50));
    public static final ItemStack FLYING_ITEM = createCustomItem(Material.STONE_SWORD, 60019, "Nekonečná Raketka", FLYING_ITEM_LORE, null);
    public static final ItemStack MESSENGER_PACK = createCustomItem(Material.BUNDLE, 60020, "Poštovní Brašna", MESSENGER_PACK_LORE, null);
    public static final ItemStack SPEED_BOOTS = createCustomItem(Material.IRON_BOOTS, 60021, "Rychlé botky", SPEED_BOOTS_LORE, null);

    public static final List<ItemStack> RANDOMIZER_RECIPE = List.of(
            new ItemStack(Material.REDSTONE), new ItemStack(Material.ENDER_PEARL), new ItemStack(Material.REDSTONE),
            new ItemStack(Material.IRON_INGOT), new ItemStack(Material.BLAZE_ROD), new ItemStack(Material.IRON_INGOT),
            new ItemStack(Material.IRON_INGOT), new ItemStack(Material.AIR), new ItemStack(Material.IRON_INGOT)
    );

    public CustomItems(Plugin plugin) {
        this.plugin = plugin;
        //create and register recipes
        registerRecipes(plugin);
    }

    private void registerRecipes(Plugin plugin) {
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
        //Zeus Key
        NamespacedKey pegasusTameKey = new NamespacedKey(plugin, "pegasus_tame_recipe");
        NamespacedKey pegasusAbilityKey = new NamespacedKey(plugin, "pegasus_ability_recipe");
        //Other Key
        NamespacedKey randomizerKey = new NamespacedKey(plugin, "randomizer_recipe");

        //Demeter Shape
        ShapedRecipe betterBonemealRecipe = new ShapedRecipe(betterBonemealkey, BETTER_BONEMEAL);
        betterBonemealRecipe.shape("BB ", "BB ", "   ");
        betterBonemealRecipe.setIngredient('B', Material.BONE_MEAL);
        ShapedRecipe growthPotionRecipe = new ShapedRecipe(growthPotionKey, GROWTH_POTION);
        growthPotionRecipe.shape(" B ", "BGB", " B ");
        growthPotionRecipe.setIngredient('B', new RecipeChoice.ExactChoice(BETTER_BONEMEAL));
        growthPotionRecipe.setIngredient('G', Material.GLASS_BOTTLE);
        ShapedRecipe hoeOfHarvestRecipe = new ShapedRecipe(hoeOfHarvestKey, HOE_OF_HARVEST);
        hoeOfHarvestRecipe.shape("   "," H ", "   ");
        hoeOfHarvestRecipe.setIngredient('H', Material.IRON_HOE);
        ShapedRecipe flowerHorseTameRecipe = new ShapedRecipe(flowerHorseTameKey, FLOWER_HORSE_TAME);
        flowerHorseTameRecipe.shape(" S ", "SAS", " S ");
        flowerHorseTameRecipe.setIngredient('S', Material.SUGAR);
        flowerHorseTameRecipe.setIngredient('A', Material.APPLE);
        //Hades Shape
        ShapedRecipe zombieHorseTameRecipe = new ShapedRecipe(zombieHorseTameKey, ZOMBIE_HORSE_TAME);
        zombieHorseTameRecipe.shape(" W ", "WAW", " W ");
        zombieHorseTameRecipe.setIngredient('W', Material.WITHER_ROSE);
        zombieHorseTameRecipe.setIngredient('A', Material.APPLE);
        ShapedRecipe darknessPotionRecipe = new ShapedRecipe(darknessPotionKey, DARKNESS_POTION);
        darknessPotionRecipe.shape(" B ", "BGB" , " B ");
        darknessPotionRecipe.setIngredient('B', new RecipeChoice.ExactChoice(ItemUtils.createPotion(PotionType.NIGHT_VISION)));
        darknessPotionRecipe.setIngredient('G', Material.GLASS_BOTTLE);
        ShapedRecipe darkPearlRecipe = new ShapedRecipe(darkPearlKey, DARK_PEARL);
        darkPearlRecipe.shape(" B ", "BEB", " B ");
        darkPearlRecipe.setIngredient('B', Material.BLAZE_POWDER);
        darkPearlRecipe.setIngredient('E', Material.ENDER_PEARL);
        ShapedRecipe darkPortalRecipe = new ShapedRecipe(darkPortalKey, DARK_PORTAL);
        darkPortalRecipe.shape(" A ", "AGA", " A ");
        darkPortalRecipe.setIngredient('A', Material.ANCIENT_DEBRIS);
        darkPortalRecipe.setIngredient('G', Material.GLASS_BOTTLE);
        //Dionysus Shape
        ShapedRecipe drunkHorseTameRecipe = new ShapedRecipe(drunkHorseTameKey, DRUNK_HORSE_TAME);
        drunkHorseTameRecipe.shape(" S ", "SAS", " S ");
        drunkHorseTameRecipe.setIngredient('S', Material.SUGAR_CANE);
        drunkHorseTameRecipe.setIngredient('A', Material.APPLE);
        ShapedRecipe partyBallRecipe = new ShapedRecipe(partyBallKey, PARTY_BALL);
        partyBallRecipe.shape(" F ", "FBF", " F ");
        partyBallRecipe.setIngredient('F', Material.FIREWORK_ROCKET);
        partyBallRecipe.setIngredient('B', Material.FIRE_CHARGE);
        ShapedRecipe partyAtmosphereRecipe = new ShapedRecipe(partyAtmosphereKey, PARTY_ATMOSPHERE);
        partyAtmosphereRecipe.shape(" P ", "PGP", " P ");
        partyAtmosphereRecipe.setIngredient('P', Material.FIREWORK_ROCKET);
        partyAtmosphereRecipe.setIngredient('G', Material.GLASS_BOTTLE);
        ShapedRecipe randomPotionEffectRecipe = new ShapedRecipe(randomEffectPotionKey, RANDOM_EFFECT_POTION);
        randomPotionEffectRecipe.shape(" C ", "CBC", " C ");
        randomPotionEffectRecipe.setIngredient('C', Material.COD);
        randomPotionEffectRecipe.setIngredient('B', Material.GLASS_BOTTLE);
        //Hermes Shape
        ShapedRecipe speedHorseTameRecipe = new ShapedRecipe(speedHorseTameKey, SPEED_HORSE_TAME);
        speedHorseTameRecipe.shape(" P ", "PAP", " P ");
        speedHorseTameRecipe.setIngredient('P', new RecipeChoice.ExactChoice(ItemUtils.createPotion(PotionType.SWIFTNESS)));
        speedHorseTameRecipe.setIngredient('A', Material.APPLE);
        ShapedRecipe speedHorseAbilityRecipe = new ShapedRecipe(speedHorseAbilityKey, SPEED_HORSE_ABILITY);
        speedHorseAbilityRecipe.shape("   ", " P ", "   ");
        speedHorseAbilityRecipe.setIngredient('P', new RecipeChoice.ExactChoice(ItemUtils.createPotion(PotionType.SWIFTNESS)));
        ShapedRecipe flyingItemRecipe = new ShapedRecipe(flyingItemKey, FLYING_ITEM);
        flyingItemRecipe.shape(" F ", "FSF", " F ");
        flyingItemRecipe.setIngredient('F', Material.FIREWORK_ROCKET);
        flyingItemRecipe.setIngredient('S', Material.STICK);
        ShapedRecipe messengerPackRecipe = new ShapedRecipe(messengerPackKey, MESSENGER_PACK);
        messengerPackRecipe.shape("   ", " B ", "   ");
        messengerPackRecipe.setIngredient('B', Material.LEATHER);
        ShapedRecipe speedBootsRecipe = new ShapedRecipe(speedBootsKey, SPEED_BOOTS);
        speedBootsRecipe.shape("   ", " S ", "   ");
        speedBootsRecipe.setIngredient('S', Material.IRON_BOOTS);
        //Poseidon Shape
        ShapedRecipe seaHorseTameRecipe = new ShapedRecipe(seaHorseTameKey, SEA_HORSE_TAME);
        seaHorseTameRecipe.shape(" S ", "SAS", " S ");
        seaHorseTameRecipe.setIngredient('S', Material.SEAGRASS);
        seaHorseTameRecipe.setIngredient('A', Material.APPLE);
        //Zeus Shape
        ShapedRecipe pegasusTameRecipe = new ShapedRecipe(pegasusTameKey, PEGASUS_TAME);
        pegasusTameRecipe.shape(" S ", "SAS", " S ");
        pegasusTameRecipe.setIngredient('A', Material.APPLE);
        pegasusTameRecipe.setIngredient('S', new RecipeChoice.ExactChoice(ItemUtils.createPotion(PotionType.SLOW_FALLING)));
        ShapedRecipe pegasusAbilityRecipe = new ShapedRecipe(pegasusAbilityKey, PEGASUS_ABILITY);
        pegasusAbilityRecipe.shape("   ", " P ", "   ");
        pegasusAbilityRecipe.setIngredient('P', new RecipeChoice.ExactChoice(ItemUtils.createPotion(PotionType.SLOW_FALLING)));
//        //Other Shape
//        ShapedRecipe randomizerRecipe = new ShapedRecipe(randomizerKey, RANDOMIZER);
//        randomizerRecipe.shape("RER", "IBI", "IRI");
//        randomizerRecipe.setIngredient('R', Material.REDSTONE);
//        randomizerRecipe.setIngredient('E', Material.ENDER_PEARL);
//        randomizerRecipe.setIngredient('I', Material.IRON_INGOT);
//        randomizerRecipe.setIngredient('B', Material.BLAZE_ROD);
        ShapedRecipe randomizerRecipe = createShapedRecipeFromList(RANDOMIZER, RANDOMIZER_RECIPE, randomizerKey, "RER", "IBI", "I I");
        Bukkit.addRecipe(randomizerRecipe);
//        //Demeter Register
//        Bukkit.addRecipe(betterBonemealRecipe);
//        Bukkit.addRecipe(growthPotionRecipe);
//        Bukkit.addRecipe(hoeOfHarvestRecipe);
//        Bukkit.addRecipe(flowerHorseTameRecipe);
//        //Hades Register
//        Bukkit.addRecipe(zombieHorseTameRecipe);
//        Bukkit.addRecipe(darknessPotionRecipe);
//        Bukkit.addRecipe(darkPearlRecipe);
//        Bukkit.addRecipe(darkPortalRecipe);
//        //Dionysus Register
//        Bukkit.addRecipe(drunkHorseTameRecipe);
//        Bukkit.addRecipe(partyBallRecipe);
//        Bukkit.addRecipe(partyAtmosphereRecipe);
//        Bukkit.addRecipe(randomPotionEffectRecipe);
//        //Hermes Register
//        Bukkit.addRecipe(speedHorseTameRecipe);
//        Bukkit.addRecipe(speedHorseAbilityRecipe);
//        Bukkit.addRecipe(flyingItemRecipe);
//        Bukkit.addRecipe(messengerPackRecipe);
//        Bukkit.addRecipe(speedBootsRecipe);
//        //Poseidon Register
//        Bukkit.addRecipe(seaHorseTameRecipe);
//        //Zeus Register
//        Bukkit.addRecipe(pegasusTameRecipe);
//        Bukkit.addRecipe(pegasusAbilityRecipe);

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
