package me.figgnus.aeterum.utils;

import me.figgnus.aeterum.Plugin;
import org.bukkit.ChatColor;

public class PermissionUtils {
    public static final String permissionItemMessage = ChatColor.RED + "Nemáš oprávnění použít tento předmět.";
    public static final String permissionCommandMessage = ChatColor.RED + "Nemáš oprávnění použít tento příkaz";
    public static final String horseTameMessage = ChatColor.GOLD + "Nakrmil/a jsi koně. Nyní můžeš koně ochočit.";
    public static final String horseTransformMessage = ChatColor.GOLD + "Tvůj kůň se proměnil.";

    public static final String demeterBetterBonemeal = Plugin.getPermission("BETTER_BONEMEAL");
    public static final String demeterHorseTame = Plugin.getPermission("FLOWER_HORSE_TAME");
    public static final String demeterHorseAbility = Plugin.getPermission("FLOWER_HORSE_ABILITY");
    public static final String demeterGrowthPotion = Plugin.getPermission("GROWTH_POTION");
    public static final String demeterHoe = Plugin.getPermission("HOE_OF_THE_HARVEST");

    public static final String dionysusHorseAbility = Plugin.getPermission("DRUNK_HORSE_ABILITY");
    public static final String dionysusHorseTame = Plugin.getPermission("DRUNK_HORSE_TAME");
    public static final String dionysusPartyAtmosphere = Plugin.getPermission("PARTY_ATMOSPHERE");
    public static final String dionysusPartyBall = Plugin.getPermission("PARTY_BALL");
    public static final String dionysusRandomEffectPotion = Plugin.getPermission("RANDOM_EFFECT_POTION");

    public static final String hadesHorseTame = Plugin.getPermission("ZOMBIE_HORSE_TAME");
    public static final String hadesHorseAbility = Plugin.getPermission("ZOMBIE_HORSE_ABILITY");
    public static final String hadesDarknessPotion = Plugin.getPermission("DARKNESS_POTION");
    public static final String hadesDarkPearl = Plugin.getPermission("DARK_PEARL");
    public static final String hadesPortal = Plugin.getPermission("DARK_PORTAL");

    public static final String hermesHorseTame = Plugin.getPermission("SPEED_HORSE_TAME");
    public static final String hermesHorseAbility = Plugin.getPermission("SPEED_HORSE_ABILITY");
    public static final String hermesFlyingItem = Plugin.getPermission("FLYING_ITEM");
    public static final String hermesMessengerPack = Plugin.getPermission("MESSENGER_PACK");
    public static final String hermesSpeedBoots = Plugin.getPermission("SPEED_BOOTS");

    public static final String poseidonHorseTame = Plugin.getPermission("SEA_HORSE_TAME");
    public static final String poseidonHorseAbility = Plugin.getPermission("SEA_HORSE_ABILITY");
    public static final String poseidonCrown = Plugin.getPermission("WATER_BREATHING_CROWN");
    public static final String poseidonTrident = Plugin.getPermission("BETTER_TRIDENT");

    public static final String zeusHorseTame = Plugin.getPermission("PEGASUS_HORSE_TAME");
    public static final String zeusHorseAbility = Plugin.getPermission("PEGASUS_HORSE_ABILITY");
    public static final String zeusBreedingItem = Plugin.getPermission("BREEDING_ITEM");
    public static final String zeusLightningSpear = Plugin.getPermission("LIGHTNING_SPEAR");
    public static final String zeusWeatherChanger = Plugin.getPermission("WEATHER_CHANGER");

    public static final String hadesTogglePermission = Plugin.getPermission("HADES_COMMAND");
    public static final String poseidonTogglePermission = Plugin.getPermission("POSEIDON_COMMAND");

    public static final String demeterGuiPermission = Plugin.getPermission("DEMETER_GUI");
    public static final String dionysusGuiPermission = Plugin.getPermission("DIONYSUS_GUI");
    public static final String hadesGuiPermission = Plugin.getPermission("HADES_GUI");
    public static final String hermesGuiPermission = Plugin.getPermission("HERMES_GUI");
    public static final String poseidonGuiPermission = Plugin.getPermission("POSEIDON_GUI");
    public static final String zeusGuiPermission = Plugin.getPermission("ZEUS_GUI");

    public static final String randomizerPermission = Plugin.getPermission("RANDOMIZER");
}