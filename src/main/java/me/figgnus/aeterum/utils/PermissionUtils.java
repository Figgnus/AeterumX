package me.figgnus.aeterum.utils;

import me.figgnus.aeterum.AeterumX;
import org.bukkit.ChatColor;

public class PermissionUtils {
    public static final String permissionItemMessage = ChatColor.RED + "Nemáš oprávnění použít tento předmět.";
    public static final String permissionCommandMessage = ChatColor.RED + "Nemáš oprávnění použít tento příkaz";
    public static final String horseTameMessage = ChatColor.GOLD + "Nakrmil/a jsi koně. Nyní můžeš koně ochočit.";
    public static final String horseTransformMessage = ChatColor.GOLD + "Tvůj kůň se proměnil.";

    public static final String demeterBetterBonemeal = AeterumX.getPermission("BETTER_BONEMEAL");
    public static final String demeterHorseTame = AeterumX.getPermission("FLOWER_HORSE_TAME");
    public static final String demeterHorseAbility = AeterumX.getPermission("FLOWER_HORSE_ABILITY");
    public static final String demeterGrowthPotion = AeterumX.getPermission("GROWTH_POTION");
    public static final String demeterHoe = AeterumX.getPermission("HOE_OF_THE_HARVEST");

    public static final String dionysusHorseAbility = AeterumX.getPermission("DRUNK_HORSE_ABILITY");
    public static final String dionysusHorseTame = AeterumX.getPermission("DRUNK_HORSE_TAME");
    public static final String dionysusPartyAtmosphere = AeterumX.getPermission("PARTY_ATMOSPHERE");
    public static final String dionysusPartyBall = AeterumX.getPermission("PARTY_BALL");
    public static final String dionysusRandomEffectPotion = AeterumX.getPermission("RANDOM_EFFECT_POTION");

    public static final String hadesHorseTame = AeterumX.getPermission("ZOMBIE_HORSE_TAME");
    public static final String hadesHorseAbility = AeterumX.getPermission("ZOMBIE_HORSE_ABILITY");
    public static final String hadesDarknessPotion = AeterumX.getPermission("DARKNESS_POTION");
    public static final String hadesDarkPearl = AeterumX.getPermission("DARK_PEARL");
    public static final String hadesPortal = AeterumX.getPermission("DARK_PORTAL");

    public static final String hermesHorseTame = AeterumX.getPermission("SPEED_HORSE_TAME");
    public static final String hermesHorseAbility = AeterumX.getPermission("SPEED_HORSE_ABILITY");
    public static final String hermesFlyingItem = AeterumX.getPermission("FLYING_ITEM");
    public static final String hermesMessengerPack = AeterumX.getPermission("MESSENGER_PACK");
    public static final String hermesSpeedBoots = AeterumX.getPermission("SPEED_BOOTS");

    public static final String poseidonHorseTame = AeterumX.getPermission("SEA_HORSE_TAME");
    public static final String poseidonHorseAbility = AeterumX.getPermission("SEA_HORSE_ABILITY");
    public static final String poseidonCrown = AeterumX.getPermission("WATER_BREATHING_CROWN");
    public static final String poseidonTrident = AeterumX.getPermission("BETTER_TRIDENT");

    public static final String zeusHorseTame = AeterumX.getPermission("PEGASUS_HORSE_TAME");
    public static final String zeusHorseAbility = AeterumX.getPermission("PEGASUS_HORSE_ABILITY");
    public static final String zeusBreedingItem = AeterumX.getPermission("BREEDING_ITEM");
    public static final String zeusLightningSpear = AeterumX.getPermission("LIGHTNING_SPEAR");
    public static final String zeusWeatherChanger = AeterumX.getPermission("WEATHER_CHANGER");

    public static final String hadesTogglePermission = AeterumX.getPermission("HADES_COMMAND");
    public static final String poseidonTogglePermission = AeterumX.getPermission("POSEIDON_COMMAND");

    public static final String demeterGuiPermission = AeterumX.getPermission("DEMETER_GUI");
    public static final String dionysusGuiPermission = AeterumX.getPermission("DIONYSUS_GUI");
    public static final String hadesGuiPermission = AeterumX.getPermission("HADES_GUI");
    public static final String hermesGuiPermission = AeterumX.getPermission("HERMES_GUI");
    public static final String poseidonGuiPermission = AeterumX.getPermission("POSEIDON_GUI");
    public static final String zeusGuiPermission = AeterumX.getPermission("ZEUS_GUI");

    public static final String randomizerPermission = AeterumX.getPermission("RANDOMIZER");
}