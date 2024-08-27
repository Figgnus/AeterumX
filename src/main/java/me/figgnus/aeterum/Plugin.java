package me.figgnus.aeterum;

import me.figgnus.aeterum.gui.RecipesGUI;
import me.figgnus.aeterum.listeners._other.CraftingPermissionListener;
import me.figgnus.aeterum.listeners._other.RandomizerListener;
import me.figgnus.aeterum.listeners._other.SnowBallDemageListener;
import me.figgnus.aeterum.listeners.demeter.*;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.listeners.dionysus.*;
import me.figgnus.aeterum.listeners.hades.*;
import me.figgnus.aeterum.listeners.hermes.*;
import me.figgnus.aeterum.listeners.poseidon.DolphinGraceListener;
import me.figgnus.aeterum.listeners.poseidon.SeaHorseAbilityListener;
import me.figgnus.aeterum.listeners.poseidon.SeaHorseTameListener;
import me.figgnus.aeterum.listeners.zeus.*;
import me.figgnus.aeterum.utils.ItemUtils;
import me.figgnus.aeterum.utils.TameCommandExecutor;
import me.figgnus.aeterum.utils.TameCommandTabCompleter;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class Plugin extends JavaPlugin implements CommandExecutor, Listener {

    private static FileConfiguration config;

    private RecipesGUI recipesGUI;

    private BetterBonemealListener betterBonemeal;
    private GrowthPotionListener growthPotion;
    private HoeOfHarvestListener hoeOfHarvest;
    private FlowerHorseTameListener flowerHorseTame;
    private FlowerHorseAbilityListener flowerHorseAbility;

    private ZombieHorseTameListener zombieHorseTame;
    private ZombieHorseAbilityListener zombieHorseAbility;
    private DarknessPotionListener darknessPotion;
    private DarkPearlListener darkPearl;
    private PortalListener portal;
    private NightVisionListener nightVision;

    private DrunkHorseTameListener drunkHorseTame;
    private DrunkHorseAbilityListener drunkHorseAbility;
    private PartyBallListener partyBall;
    private PartyAtmosphereListener partyAtmosphere;
    private RandomEffectPotionListener randomEffectPotion;

    private SpeedHorseTameListener speedHorseTame;
    private SpeedHorseAbilityListener speedHorseAbility;
    private FlyingItemListener flyingItem;
    private MessengerPackListener messengerPack;
    private SpeedBootsListener speedBoots;

    private SeaHorseTameListener seaHorseTame;
    private SeaHorseAbilityListener seaHorseAbility;
    private DolphinGraceListener dolphinGrace;

    private PegasusTameListener pegasusTame;
    private PegasusAbilityListener pegasusAbility;
    private BreedingItemListener breedingItem;
    private LightningSpearListener lightningSpear;
    private WeatherChangerListener weatherChanger;

    private RandomizerListener randomizer;


    @Override
    public void onEnable() {

        loadConfig();
        //Register custom items and recipes
        new CustomItems(this);


        //Listeners
        betterBonemeal = new BetterBonemealListener(this);
        growthPotion = new GrowthPotionListener(this);
        hoeOfHarvest = new HoeOfHarvestListener(this);
        flowerHorseTame = new FlowerHorseTameListener(this);
        flowerHorseAbility = new FlowerHorseAbilityListener(this);

        zombieHorseTame = new ZombieHorseTameListener(this);
        zombieHorseAbility = new ZombieHorseAbilityListener(this);
        darknessPotion = new DarknessPotionListener(this);
        darkPearl = new DarkPearlListener(this);
        portal = new PortalListener(this);
        nightVision = new NightVisionListener(this);

        drunkHorseTame = new DrunkHorseTameListener(this);
        drunkHorseAbility = new DrunkHorseAbilityListener(this);
        partyBall = new PartyBallListener(this);
        partyAtmosphere = new PartyAtmosphereListener(this);
        randomEffectPotion = new RandomEffectPotionListener(this);

        speedHorseTame = new SpeedHorseTameListener(this);
        speedHorseAbility = new SpeedHorseAbilityListener(this);
        flyingItem = new FlyingItemListener(this);
        messengerPack = new MessengerPackListener(this);
        speedBoots = new SpeedBootsListener(this);

        seaHorseTame = new SeaHorseTameListener(this);
        seaHorseAbility = new SeaHorseAbilityListener(this);
        dolphinGrace = new DolphinGraceListener(this);

        pegasusTame = new PegasusTameListener(this);
        pegasusAbility = new PegasusAbilityListener(this);
        breedingItem = new BreedingItemListener(this);
        lightningSpear = new LightningSpearListener(this);
        weatherChanger = new WeatherChangerListener(this);

        randomizer = new RandomizerListener(this);

        recipesGUI = new RecipesGUI(this);

        getCommand("dolphingrace").setExecutor(dolphinGrace);
        getCommand("nightvision").setExecutor(nightVision);
        getCommand("recepty").setExecutor(recipesGUI);
        getCommand("tame").setExecutor(new TameCommandExecutor(this));
        getCommand("tame").setTabCompleter(new TameCommandTabCompleter());

        getServer().getPluginManager().registerEvents(new SnowBallDemageListener(), this);

        new CraftingPermissionListener(this);

    }

    private void loadConfig() {
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            saveDefaultConfig();  // Creates default config.yml if it doesn't exist
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }
    public static String getPermission(String id) {
        return config.getString("permissions." + id + ".permission");
    }
    public static Integer getItemId(String id) {
        return config.getInt("permissions." + id + ".custom_id");
    }
    public static String getItemName(String id) {
        return config.getString("permissions." +id + ".name");
    }
    public static String getItemLore(String id) {
        return config.getString("permissions." + id + ".lore");
    }
    public static Material getItemMaterial(String id) {
        String nameOfMaterial = config.getString("permissions." + id + ".material");
        return Material.getMaterial(nameOfMaterial);
    }
    public static String getPotionType(String id) {
        return config.getString("permissions." + id + ".recipe_potion");
    }
    public static Color getItemColor(String id) {
        int red = config.getInt("permissions." + id + ".color.red");
        int green = config.getInt("permissions." + id + ".color.green");
        int blue = config.getInt("permissions." + id + ".color.blue");
        return Color.fromRGB(red, green, blue);
    }
    public static List<ItemStack> getRecipe(String id) {
        List<ItemStack> recipe = new ArrayList<>();
        String pathToRecipe = "permissions." + id + ".recipe";
        List<String> recipeConfig = config.getStringList(pathToRecipe);

        for (String row : recipeConfig) {
            String[] ingredients = row.split(":");

            for (String ingredient : ingredients) {
                Material material = Material.getMaterial(ingredient);

                if (material != null) {
                    if (material == Material.POTION || material == Material.SPLASH_POTION || material == Material.LINGERING_POTION) {
                        String potionTypeString = getPotionType(id);
                        try {
                            ItemStack potion = ItemUtils.createPotion(PotionType.valueOf(potionTypeString));
                            recipe.add(potion);
                        } catch (IllegalArgumentException e) {
                            // Invalid potion type, use default water potion
                            System.out.println("Invalid potion type: " + potionTypeString + ". Using WATER instead.");
                            ItemStack potion = ItemUtils.createPotion(PotionType.WATER);
                            recipe.add(potion);
                        }
                    } else {
                        recipe.add(new ItemStack(material));
                    }
                } else {
                    // Check if the ingredient matches a custom item identifier
                    boolean customItemAdded = false;

                    for (String item : config.getConfigurationSection("permissions").getKeys(false)) {
                        if (ingredient.equals(item)) {
                            // Create custom item and add it to the recipe list
                            ItemStack customItem = CustomItems.createCustomItem(
                                    getItemMaterial(item),
                                    getItemId(item),
                                    getItemName(item),
                                    getGrayLore(getItemLore(item)),
                                    getItemColor(item)
                            );
                            recipe.add(customItem);
                            customItemAdded = true;
                            break;
                        }
                    }

                    // If no custom item was found, add AIR to maintain the recipe structure
                    if (!customItemAdded) {
                        recipe.add(new ItemStack(Material.AIR));
                    }
                }
            }
        }
        return recipe;
    }
    private static List<String> getGrayLore(String lore) {
        List<String> grayLore = new ArrayList<>();
        if (lore != null && !lore.isEmpty()) {
            // Split the lore into lines and apply gray color to each line
            String[] lines = lore.split("\n");
            for (String line : lines) {
                grayLore.add(ChatColor.GRAY + line);
            }
        }
        return grayLore;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (messengerPack != null) {
            messengerPack.saveAllInventories();
        }
    }
    //methods for making metadata of entities persistent
    public void setEntityMetadata(Entity entity, String key, String value){
        NamespacedKey namespacedKey = new NamespacedKey(this, key);
        PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
        dataContainer.set(namespacedKey, PersistentDataType.STRING, value);
    }
    public String getEntityMetadata(Entity entity, String key) {
        NamespacedKey namespacedKey = new NamespacedKey(this, key);
        PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
        if (dataContainer.has(namespacedKey, PersistentDataType.STRING)) {
            return dataContainer.get(namespacedKey, PersistentDataType.STRING);
        }
        return null;
    }
}
