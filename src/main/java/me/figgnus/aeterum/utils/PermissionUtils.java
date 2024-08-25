package me.figgnus.aeterum.utils;

import me.figgnus.aeterum.Plugin;
import org.bukkit.ChatColor;

public class PermissionUtils {
    public static final String permissionItemMessage = ChatColor.RED + "Nemáš oprávnění použít tento předmět.";
    public static final String permissionCommandMessage = ChatColor.RED + "Nemáš oprávnění použít tento příkaz";
    public static final String horseTameMessage = ChatColor.GOLD + "Nakrmil/a jsi koně. Nyní můžeš koně ochočit.";
    public static final String horseTransformMessage = ChatColor.GOLD + "Tvůj kůň se proměnil.";

    public static final String demeterBetterBonemeal = Plugin.getPermission("demeter", 60001);
    public static final String demeterHorseTame = Plugin.getPermission("demeter", 60004);
    public static final String demeterHorseAbility = Plugin.getPermission("demeter", 70001);
    public static final String demeterGrowthPotion = Plugin.getPermission("demeter", 60002);
    public static final String demeterHoe = Plugin.getPermission("demeter", 60003);

    public static final String dionysusHorseAbility = Plugin.getPermission("dionysus", 70002);
    public static final String dionysusHorseTame = Plugin.getPermission("dionysus", 60006);
    public static final String dionysusPartyAtmosphere = Plugin.getPermission("dionysus", 60013);
    public static final String dionysusPartyBall = Plugin.getPermission("dionysus", 60012);
    public static final String dionysusRandomEffectPotion = Plugin.getPermission("dionysus", 60014);

    public static final String hadesHorseTame = Plugin.getPermission("hades", 60005);
    public static final String hadesHorseAbility = Plugin.getPermission("hades", 70003);
    public static final String hadesDarknessPotion = Plugin.getPermission("hades", 60016);
    public static final String hadesDarkPearl = Plugin.getPermission("hades", 60017);
    public static final String hadesPortal = Plugin.getPermission("hades", 60018);

    public static final String hermesHorseTame = Plugin.getPermission("hermes", 60007);
    public static final String hermesHorseAbility = Plugin.getPermission("hermes", 60008);
    public static final String hermesFlyingItem = Plugin.getPermission("hermes", 60019);
    public static final String hermesMessengerPack = Plugin.getPermission("hermes", 60020);
    public static final String hermesSpeedBoots = Plugin.getPermission("hermes", 60021);

    public static final String poseidonHorseTame = Plugin.getPermission("poseidon", 60009);
    public static final String poseidonHorseAbility = Plugin.getPermission("poseidon", 70005);
    public static final String poseidonCrown = Plugin.getPermission("poseidon", 60023);
    public static final String poseidonTrident = Plugin.getPermission("poseidon", 60022);

    public static final String zeusHorseTame = Plugin.getPermission("zeus", 60010);
    public static final String zeusHorseAbility = Plugin.getPermission("zeus", 60011);
    public static final String zeusBreedingItem = Plugin.getPermission("zeus", 60024);
    public static final String zeusLightningSpear = Plugin.getPermission("zeus", 60025);
    public static final String zeusWeatherChanger = Plugin.getPermission("zeus", 60026);

    public static final String hadesTogglePermission = "aeterum.hades.command";
    public static final String poseidonTogglePermission = "aeterum.poseidon.command";

    public static final String demeterGuiPermission = Plugin.getPermission("demeter", 80001);
    public static final String dionysusGuiPermission = Plugin.getPermission("dionysus", 80002);
    public static final String hadesGuiPermission = Plugin.getPermission("hades", 80003);
    public static final String hermesGuiPermission = Plugin.getPermission("hermes", 80004);
    public static final String poseidonGuiPermission = Plugin.getPermission("poseidon", 80005);
    public static final String zeusGuiPermission = Plugin.getPermission("zeus", 80006);

    public static final String randomizerPermission = "aeterum.trowel.use";
}