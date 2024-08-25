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
import me.figgnus.aeterum.utils.TameCommandExecutor;
import me.figgnus.aeterum.utils.TameCommandTabCompleter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public final class Plugin extends JavaPlugin implements CommandExecutor, Listener {

    private static FileConfiguration config;

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

        getCommand("dolphingrace").setExecutor(dolphinGrace);
        getCommand("nightvision").setExecutor(nightVision);
        getCommand("tame").setExecutor(new TameCommandExecutor(this));
        getCommand("tame").setTabCompleter(new TameCommandTabCompleter());

        getServer().getPluginManager().registerEvents(new SnowBallDemageListener(), this);

        new CraftingPermissionListener(this);
        new RecipesGUI(this);
    }

    private void loadConfig() {
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            saveDefaultConfig();  // Creates default config.yml if it doesn't exist
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }
    public String getPermission(String god, Integer itemId) {
        return config.getString("permissions." + god + "." + itemId + ".permission");
    }
    public Integer getItemId(String god, Integer itemId) {
        return config.getInt("permissions." + god + "." + itemId + ".custom_id");
    }
    public static String getItemName(String god, Integer itemId) {
        return config.getString("permissions." + god + "." + itemId + ".name");
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
