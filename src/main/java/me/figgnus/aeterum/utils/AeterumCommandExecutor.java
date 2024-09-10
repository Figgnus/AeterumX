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
            player.sendMessage("Usage : /aeterum <action>");
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
    private void openMainInventory(Player player){
        Inventory mainInventory = plugin.getServer().createInventory(null, 54, "Aeterum");
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
            return Arrays.asList("items");
        }
        return new ArrayList<>();
    }
}
