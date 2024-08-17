package me.figgnus.aeterum.items;

import me.figgnus.aeterum.Plugin;
import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;


import java.util.List;


public class CustomItems {
    private final Plugin plugin;

    //Ids
    public static int BETTER_BONEMEAL_ID = 69001;
    public static int GROWTH_POTION_ID = 69002;
    public static int HOE_OF_HARVEST_ID = 69003;
    public static int FLOWER_HORSE_TAME_ID = 69004;
    public static int ZOMBIE_HORSE_TAME_ID = 69005;
    public static int DRUNK_HORSE_TAME_ID = 69006;
    public static int SPEED_HORSE_TAME_ID = 69007;
    public static int SPEED_HORSE_ABILITY_ID = 69008;
    public static int SEA_HORSE_TAME_ID = 69009;
    public static int PEGASUS_TAME_ID = 69010;
    public static int PEGASUS_ABILITY_ID = 69011;
    public static int PARTY_BALL_ID = 69012;
    public static int PARTY_ATMOSPHERE_ID = 69013;
    public static int RANDOM_EFFECT_POTION_ID = 69014;
    public static int RANDOMIZER_ID = 69015;
    public static int DARKNESS_POTION_ID = 69016;

    //Name
    private String BETTER_BONEMEAL_NAME = "Vylepšená Kostní Moučka";
    private String GROWTH_POTION_NAME = "Lektvar Růstu";
    private String HOE_OF_HARVEST_NAME = "Kosa Úrody";
    private String FLOWER_HORSE_TAME_NAME = "Sladké Jablko";
    private String ZOMBIE_HORSE_TAME_NAME = "Otrávené Jablko";
    private String DRUNK_HORSE_TAME_NAME = "Fermentované Jablko";
    private String SPEED_HORSE_TAME_NAME = "Rychlé Jablko";
    private String SPEED_HORSE_ABILITY_NAME = "Lektvar Rychlích Kopit";
    private String SEA_HORSE_TAME_NAME = "Slané Jablko";
    private String PEGASUS_TAME_NAME = "Levitující Jablko";
    private String PEGASUS_ABILITY_NAME = "Lektvar Levitujících Kopit";
    private String PARTY_BALL_NAME = "Party Koule";
    private String PARTY_ATMOSPHERE_NAME = "Party Atmosféra";
    private String RANDOM_EFFECT_POTION_NAME = "Záhadný Nápoj";
    private String RANDOMIZER_NAME = "Randomizer";
    private String DARKNESS_POTION_NAME = "Temný Lektvar";

    //Lore
    private List<String> BETTER_BONEMEAL_LORE = List.of(ChatColor.GRAY + "Bonemeal který se dá použít na Cactus a Sugar Cane");
    private List<String> GROWTH_POTION_LORE = List.of(ChatColor.GRAY + "Lektvar který urychlý růst rostlin v okolí hráče");
    private List<String> HOE_OF_HARVEST_LORE = List.of(ChatColor.GRAY + "Jestli-že někdo vý jak farmařit tak jsi to ty.");
    private List<String> FLOWER_HORSE_TAME_LORE = List.of(ChatColor.GRAY + "Jablko kterým Demeter může ochočit svého koňe");
    private List<String> ZOMBIE_HORSE_TAME_LORE = List.of(ChatColor.GRAY + "Jablko kterým Hades může ochočit svého koňe");
    private List<String> DRUNK_HORSE_TAME_LORE = List.of(ChatColor.GRAY + "Jablko kterým Dionysus může ochočit svého koňe");
    private List<String> SPEED_HORSE_TAME_LORE = List.of(ChatColor.GRAY + "Jablko kterým Hermes může ochočit svého koňe");
    private List<String> SPEED_HORSE_ABILITY_LORE = List.of(ChatColor.GRAY + "Sedni na svého koně, napij se a drž se");
    private List<String> SEA_HORSE_TAME_LORE = List.of(ChatColor.GRAY + "Jablko kterým Poseidon může ochočit svého koňe");
    private List<String> PEGASUS_TAME_LORE = List.of(ChatColor.GRAY + "Jablko kterým Zeus může ochočit svého koňe");
    private List<String> PEGASUS_ABILITY_LORE = List.of(ChatColor.GRAY + "Napij se když si na svém koni");
    private List<String> PARTY_BALL_LORE = List.of(ChatColor.GRAY + "Pozor na výbuch");
    private List<String> PARTY_ATMOSPHERE_LORE = List.of(ChatColor.GRAY + "Let's Party!!!");
    private List<String> RANDOM_EFFECT_POTION_LORE = List.of(ChatColor.GRAY + "Kdo vý co se může stát");
    private List<String> RANDOMIZER_LORE = List.of(ChatColor.GRAY + "Položí náhodný block s hotbaru");
    private List<String> DARKNESS_POTION_LORE = List.of(ChatColor.GRAY + "Oslep své nepřátele");

    //ItemStack
    private ItemStack BETTER_BONEMEAL = createCustomItem(Material.BONE_MEAL, BETTER_BONEMEAL_ID, BETTER_BONEMEAL_NAME, BETTER_BONEMEAL_LORE, null);
    private ItemStack GROWTH_POTION = createCustomItem(Material.POTION, GROWTH_POTION_ID, GROWTH_POTION_NAME, GROWTH_POTION_LORE, Color.fromRGB(63, 206, 130));
    private ItemStack HOE_OF_HARVEST = createCustomItem(Material.STICK, HOE_OF_HARVEST_ID, HOE_OF_HARVEST_NAME, HOE_OF_HARVEST_LORE, null);
    private ItemStack FLOWER_HORSE_TAME = createCustomItem(Material.APPLE, FLOWER_HORSE_TAME_ID, FLOWER_HORSE_TAME_NAME, FLOWER_HORSE_TAME_LORE, null);
    private ItemStack ZOMBIE_HORSE_TAME = createCustomItem(Material.APPLE, ZOMBIE_HORSE_TAME_ID, ZOMBIE_HORSE_TAME_NAME, ZOMBIE_HORSE_TAME_LORE, null);
    private ItemStack DRUNK_HORSE_TAME = createCustomItem(Material.APPLE, DRUNK_HORSE_TAME_ID, DRUNK_HORSE_TAME_NAME, DRUNK_HORSE_TAME_LORE, null);
    private ItemStack SPEED_HORSE_TAME = createCustomItem(Material.APPLE, SPEED_HORSE_TAME_ID, SPEED_HORSE_TAME_NAME, SPEED_HORSE_TAME_LORE, null);
    private ItemStack SPEED_HORSE_ABILITY = createCustomItem(Material.POTION, SPEED_HORSE_ABILITY_ID, SPEED_HORSE_ABILITY_NAME, SPEED_HORSE_ABILITY_LORE, Color.fromRGB(193, 193, 193));
    private ItemStack SEA_HORSE_TAME = createCustomItem(Material.APPLE, SEA_HORSE_TAME_ID, SEA_HORSE_TAME_NAME, SEA_HORSE_TAME_LORE, null);
    private ItemStack PEGASUS_TAME = createCustomItem(Material.APPLE, PEGASUS_TAME_ID, PEGASUS_TAME_NAME, PEGASUS_TAME_LORE, null);
    private ItemStack PEGASUS_ABILITY = createCustomItem(Material.POTION, PEGASUS_ABILITY_ID, PEGASUS_ABILITY_NAME, PEGASUS_ABILITY_LORE, Color.fromRGB(204,205,208));
    private ItemStack PARTY_BALL = createCustomItem(Material.SNOWBALL, PARTY_BALL_ID, PARTY_BALL_NAME, PARTY_BALL_LORE, null);
    private ItemStack PARTY_ATMOSPHERE = createCustomItem(Material.POTION, PARTY_ATMOSPHERE_ID, PARTY_ATMOSPHERE_NAME, PARTY_ATMOSPHERE_LORE, Color.fromRGB(255,158,54));
    private ItemStack RANDOM_EFFECT_POTION = createCustomItem(Material.POTION, RANDOM_EFFECT_POTION_ID, RANDOM_EFFECT_POTION_NAME, RANDOM_EFFECT_POTION_LORE, Color.fromRGB(43,158,54));
    private ItemStack RANDOMIZER = createCustomItem(Material.STICK, RANDOMIZER_ID, RANDOMIZER_NAME, RANDOMIZER_LORE, null);
    private ItemStack DARKNESS_POTION = createCustomItem(Material.POTION, DARKNESS_POTION_ID, DARKNESS_POTION_NAME, DARKNESS_POTION_LORE, Color.fromRGB(255,30,1));

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
        //Dionysus Key
        NamespacedKey drunkHorseTameKey = new NamespacedKey(plugin, "drunk_horse_tame_recipe");
        NamespacedKey partyBallKey = new NamespacedKey(plugin, "party_ball_recipe");
        NamespacedKey partyAtmosphereKey = new NamespacedKey(plugin, "party_atmosphere_recipe");
        NamespacedKey randomEffectPotionKey = new NamespacedKey(plugin, "random_effect_potion_recipe");
        //Hermes Key
        NamespacedKey speedHorseTameKey = new NamespacedKey(plugin, "speed_horse_tame_recipe");
        NamespacedKey speedHorseAbilityKey = new NamespacedKey(plugin, "speed_horse_ability_recipe");
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
        //Other Shape
        ShapelessRecipe randomizerRecipe = new ShapelessRecipe(randomizerKey, RANDOMIZER);
        randomizerRecipe.addIngredient( Material.IRON_PICKAXE);
        randomizerRecipe.addIngredient( Material.IRON_AXE);
        randomizerRecipe.addIngredient( Material.IRON_SHOVEL);
        randomizerRecipe.addIngredient( Material.IRON_HOE);

        //Demeter Register
        Bukkit.addRecipe(betterBonemealRecipe);
        Bukkit.addRecipe(growthPotionRecipe);
        Bukkit.addRecipe(hoeOfHarvestRecipe);
        Bukkit.addRecipe(flowerHorseTameRecipe);
        //Hades Register
        Bukkit.addRecipe(zombieHorseTameRecipe);
        Bukkit.addRecipe(darknessPotionRecipe);
        //Dionysus Register
        Bukkit.addRecipe(drunkHorseTameRecipe);
        Bukkit.addRecipe(partyBallRecipe);
        Bukkit.addRecipe(partyAtmosphereRecipe);
        Bukkit.addRecipe(randomPotionEffectRecipe);
        //Hermes Register
        Bukkit.addRecipe(speedHorseTameRecipe);
        Bukkit.addRecipe(speedHorseAbilityRecipe);
        //Poseidon Register
        Bukkit.addRecipe(seaHorseTameRecipe);
        //Zeus Register
        Bukkit.addRecipe(pegasusTameRecipe);
        Bukkit.addRecipe(pegasusAbilityRecipe);
        //Other Register
        Bukkit.addRecipe(randomizerRecipe);
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
