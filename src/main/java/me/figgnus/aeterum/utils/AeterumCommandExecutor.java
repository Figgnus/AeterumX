package me.figgnus.aeterum.utils;

import me.figgnus.aeterum.AeterumX;
import me.figgnus.aeterum.gui.RecipesGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AeterumCommandExecutor implements CommandExecutor, TabCompleter {
    private final AeterumX plugin;
    Random random = new Random();

    public AeterumCommandExecutor(AeterumX plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command");
            return true;
        }
        if (args.length == 0) {
            player.sendMessage("Usage : /aeterum <tame> <action>");
            return true;
        }
        String action1 = args[0].toLowerCase();

        switch (action1) {
            case "items":
                openMainInventory(player);
                break;
            default:
                player.sendMessage("Unknown action: " + action1);
                break;

        }
        return true;
    }

    private void spawnHorse(Player player, EntityType entityType, String metaKey, Horse.Color color) {
        Entity entity = player.getWorld().spawnEntity(player.getLocation(), entityType);
        if (entity instanceof Horse) {
            Horse horse = (Horse) entity;
            horse.setOwner(player);
            horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
            plugin.setEntityMetadata(horse, metaKey, "true");

            double speed = random.nextDouble(0.3, 0.3375);
            double jump = random.nextDouble(0.9, 1.1);
            int health = random.nextInt(25, 30);

            if (color != null) {
                horse.setColor(color);
            }

            horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);
            horse.getAttribute(Attribute.GENERIC_JUMP_STRENGTH).setBaseValue(jump);
            horse.setMaxHealth(health);
            horse.setHealth(health);

            player.sendMessage(ChatColor.GREEN + "A " + horse.getCustomName() + " has been spawned!");
        } else if (entity instanceof ZombieHorse) {
            ZombieHorse horse = (ZombieHorse) entity;
            horse.setOwner(player);
            horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
            plugin.setEntityMetadata(horse, metaKey, "true");

            double speed = random.nextDouble(0.3, 0.3375);
            double jump = random.nextDouble(0.9, 1.1);
            int health = random.nextInt(25, 30);

            horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);
            horse.getAttribute(Attribute.GENERIC_JUMP_STRENGTH).setBaseValue(jump);
            horse.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, -1, 1));
            horse.setMaxHealth(health);
            horse.setHealth(health);

            player.sendMessage(ChatColor.GREEN + "A " + horse.getCustomName() + " has been spawned!");
        }
    }
    private void openMainInventory(Player player){
        Inventory mainInventory = plugin.getServer().createInventory(null, 27, "Aeterum");
        for (ItemStack item : RecipesGUI.items) {
            mainInventory.addItem(item);
        }
        player.openInventory(mainInventory);
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player player)){
            return null;
        }
        if (args.length == 1){
            return Arrays.asList("tame", "items");
        } else if (args.length == 2){
            if (args[0].equalsIgnoreCase("tame")){
                return Arrays.asList("demeter", "dionysus", "hades", "hermes", "poseidon", "zeus");
            }
        }
        return new ArrayList<>();
    }
}
