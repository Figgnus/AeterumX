package me.figgnus.aeterum.gui;

import me.figgnus.aeterum.Plugin;
import me.figgnus.aeterum.items.CustomItems;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class RecipesGUI implements CommandExecutor, Listener {
    private final Plugin plugin;

    public RecipesGUI(Plugin plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getCommand("recepty").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(sender instanceof Player player)){
            sender.sendMessage("Only players can use this command");
            return true;
        }
        openMainWindow(player);
        return false;

    }
    private void openMainWindow(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9, "Nástroje");
        inventory.addItem(CustomItems.RANDOMIZER);
        player.openInventory(inventory);
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory clickedInventory = event.getClickedInventory();

        if (clickedInventory == null || event.getCurrentItem() == null) return;

        String title = event.getView().getTitle();
        
        if (title.equals("Nástroje")){
            event.setCancelled(true);

            openToolGUI(player);
        }
        if (title.equals("Recept")){
            event.setCancelled(true);
        }
    }

    private void openToolGUI(Player player) {
        Inventory toolInventory = Bukkit.createInventory(null, 27, "Recept");
        toolInventory.setItem(1, new ItemStack(Material.REDSTONE));
        toolInventory.setItem(2, new ItemStack(Material.ENDER_PEARL));
        toolInventory.setItem(3, new ItemStack(Material.REDSTONE));
        toolInventory.setItem(10, new ItemStack(Material.IRON_INGOT));
        toolInventory.setItem(11, new ItemStack(Material.BLAZE_ROD));
        toolInventory.setItem(12, new ItemStack(Material.IRON_INGOT));
        toolInventory.setItem(19, new ItemStack(Material.IRON_INGOT));
        toolInventory.setItem(20, new ItemStack(Material.REDSTONE));
        toolInventory.setItem(21, new ItemStack(Material.IRON_INGOT));

        toolInventory.setItem(16, CustomItems.RANDOMIZER);
        player.openInventory(toolInventory);
    }
}
