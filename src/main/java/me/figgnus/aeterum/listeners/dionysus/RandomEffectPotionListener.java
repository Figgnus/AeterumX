package me.figgnus.aeterum.listeners.dionysus;

import com.dre.brewery.BPlayer;
import me.figgnus.aeterum.AeterumX;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.utils.PermissionUtils;
import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Random;

public class RandomEffectPotionListener implements Listener {
    private final AeterumX plugin;
    private final List<PotionEffectType> goodEffects = List.of(PotionEffectType.SPEED,
            PotionEffectType.HASTE,
            PotionEffectType.STRENGTH,
            PotionEffectType.JUMP_BOOST,
            PotionEffectType.REGENERATION,
            PotionEffectType.RESISTANCE,
            PotionEffectType.FIRE_RESISTANCE,PotionEffectType.WATER_BREATHING,
            PotionEffectType.INVISIBILITY,
            PotionEffectType.NIGHT_VISION,
            PotionEffectType.SATURATION,
            PotionEffectType.GLOWING,
            PotionEffectType.LEVITATION,
            PotionEffectType.LUCK);
    private final List<PotionEffectType> badEffects = List.of(PotionEffectType.SLOWNESS,
            PotionEffectType.MINING_FATIGUE,
            PotionEffectType.NAUSEA,
            PotionEffectType.BLINDNESS,
            PotionEffectType.HUNGER,
            PotionEffectType.WEAKNESS,
            PotionEffectType.POISON,
            PotionEffectType.UNLUCK);
    private final int goodEffectChance = 10;
    private final int badEffectChance = 5;

    public RandomEffectPotionListener(AeterumX plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onItemConsume(PlayerItemConsumeEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (ItemUtils.isCustomItem(item, CustomItems.RANDOM_EFFECT_POTION.getItemMeta().getCustomModelData())){
            if (!player.hasPermission(PermissionUtils.dionysusRandomEffectPotion)){
                player.sendMessage(PermissionUtils.permissionItemMessage);
                event.setCancelled(true);
                return;
            }
            BPlayer bPlayer = BPlayer.get(player);
            if (bPlayer == null){
                bPlayer = new BPlayer(player.getUniqueId().toString());
            }
            int drunkenness = bPlayer.getDrunkeness() + 5;
            String command = "brew set " + player.getName() + " " + drunkenness;
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
            applyRandomEffect(player);
        }
    }

    private void applyRandomEffect(Player player) {
        Random random = new Random();
        int roll = random.nextInt(0, goodEffectChance + badEffectChance);
        if (roll < goodEffectChance) {
            applyGoodEffect(player);
        } else {
            applyBadEffect(player);
        }
    }

    private void applyBadEffect(Player player) {
        Random random = new Random();
        int index = random.nextInt(0, badEffects.size());
        int duration = random.nextInt(200, 2400);
        int amplifier = random.nextInt(1, 3);
        player.addPotionEffect(new PotionEffect(badEffects.get(index), duration, amplifier, false));
    }

    private void applyGoodEffect(Player player) {
        Random random = new Random();
        int index = random.nextInt(0, goodEffects.size());
        int duration = random.nextInt(200, 2400);
        int amplifier = random.nextInt(1, 5);
        player.addPotionEffect(new PotionEffect(goodEffects.get(index), duration, amplifier, false));
    }
}
