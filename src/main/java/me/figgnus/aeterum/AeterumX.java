package me.figgnus.aeterum;

import me.figgnus.aeterum.gui.RecipesGUI;
import me.figgnus.aeterum.items.OraxenItems;
import me.figgnus.aeterum.listeners._other.EnchantmentListener;
import me.figgnus.aeterum.listeners._other.RandomizerListener;
import me.figgnus.aeterum.listeners._other.SnowBallDemageListener;
import me.figgnus.aeterum.listeners.demeter.*;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.listeners.dionysus.*;
import me.figgnus.aeterum.listeners.hades.*;
import me.figgnus.aeterum.listeners.hermes.*;
import me.figgnus.aeterum.listeners.poseidon.*;
import me.figgnus.aeterum.listeners.zeus.*;
import me.figgnus.aeterum.utils.AeterumCommandExecutor;
import me.figgnus.aeterum.utils.HorseDataManager;
import me.figgnus.aeterum.utils.HorseDeathListener;
import me.figgnus.aeterum.utils.HorseLocationUpdater;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class AeterumX extends JavaPlugin implements Listener {

    private static FileConfiguration config;
    private HorseDataManager horseDataManager;
    private NightVisionListener nightVision;
    private MessengerPackListener messengerPack;
    private DolphinGraceListener dolphinGrace;
    @Override
    public void onEnable() {
        loadConfig();
        //Register custom items
        new CustomItems(this);
        // Initialize the HorseDataManager and load data
        horseDataManager = new HorseDataManager(getDataFolder());
        horseDataManager.loadHorses();
        HorseLocationUpdater horseLocationUpdater = new HorseLocationUpdater(horseDataManager, this, 100L);
        horseLocationUpdater.start();
        // Initialize HorseManager with the data manager
        getServer().getPluginManager().registerEvents(new DemeterWhistleListener(horseDataManager, this), this);
        getServer().getPluginManager().registerEvents(new DionysusWhistleListener(horseDataManager, this), this);
        getServer().getPluginManager().registerEvents(new HadesWhistleListener(horseDataManager, this), this);
        getServer().getPluginManager().registerEvents(new HermesWhistleListener(horseDataManager, this), this);
        getServer().getPluginManager().registerEvents(new PoseidonWhistleListener(horseDataManager, this), this);
        getServer().getPluginManager().registerEvents(new ZeusWhistleListener(horseDataManager, this), this);

        getServer().getPluginManager().registerEvents(new HorseDeathListener(horseDataManager), this);


        //Listeners
        new BetterBonemealListener(this);
        new GrowthPotionListener(this);
        new HoeOfHarvestListener(this);
        new FlowerHorseAbilityListener(this);

        new ZombieHorseAbilityListener(this);
        new DarknessPotionListener(this);
        new DarkPearlListener(this);
        new PortalListener(this);
        nightVision = new NightVisionListener(this);

        new DrunkHorseAbilityListener(this);
        new PartyBallListener(this);
        new PartyAtmosphereListener(this);
        new RandomEffectPotionListener(this);

        new SpeedHorseAbilityListener(this);
        new FlyingItemListener(this);
        messengerPack = new MessengerPackListener(this);
        new SpeedBootsListener(this);

        new SeaHorseAbilityListener(this);
        dolphinGrace = new DolphinGraceListener(this);
        new BetterTridentListener(this);
        new SeaCrownListener(this);

        new PegasusAbilityListener(this);
        new BreedingItemListener(this);
        new LightningSpearListener(this);
        new WeatherChangerListener(this);

        new RandomizerListener(this);
//        new CustomTnTListener(this);
        new EnchantmentListener(this);

        new RecipesGUI(this);

        getCommand("dolphingrace").setExecutor(dolphinGrace);
        getCommand("nightvision").setExecutor(nightVision);
        getCommand("aeterum").setExecutor(new AeterumCommandExecutor(this));

        getCommand("aeterum").setTabCompleter(new AeterumCommandExecutor(this));

        getServer().getPluginManager().registerEvents(new SnowBallDemageListener(), this);

        Bukkit.getLogger().info("AeterumX | Loaded");
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
    public static Color getItemColor(String id) {
        int red = config.getInt("permissions." + id + ".color.red");
        int green = config.getInt("permissions." + id + ".color.green");
        int blue = config.getInt("permissions." + id + ".color.blue");
        return Color.fromRGB(red, green, blue);
    }
    public static String getOraxenId(String id) {
        return config.getString("permissions." + id + ".oraxen_id");
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (messengerPack != null) {
            messengerPack.saveAllInventories();
        }
        horseDataManager.saveHorses();
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
    public void setCustomOraxenData(ItemStack item, String value) {
        // Get the ItemMeta for the item
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            // Access the PersistentDataContainer from the meta
            PersistentDataContainer dataContainer = meta.getPersistentDataContainer();

            // Create a NamespacedKey for your plugin and the custom data key
            NamespacedKey dataKey = new NamespacedKey("oraxen", "id");

            // Set the custom data (e.g., a string in this case)
            dataContainer.set(dataKey, PersistentDataType.STRING, value);

            // Set the modified meta back to the item
            item.setItemMeta(meta);
        }
    }
    public String getCustomOraxenData(ItemStack item) {
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            PersistentDataContainer dataContainer = meta.getPersistentDataContainer();
            NamespacedKey dataKey = new NamespacedKey("oraxen", "id");

            // Retrieve the custom data (if present)
            if (dataContainer.has(dataKey, PersistentDataType.STRING)) {
                return dataContainer.get(dataKey, PersistentDataType.STRING);
            }
        }

        return null; // Return null if no custom data found
    }
}
