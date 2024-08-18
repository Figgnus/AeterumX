package me.figgnus.aeterum.gui;

import me.figgnus.aeterum.Plugin;
import me.figgnus.aeterum.items.CustomItems;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipesGUI implements CommandExecutor, Listener {
    private final Plugin plugin;

    private Inventory mainInventory;
    private Inventory toolInventory;
    private Inventory recipeInventory;

    private final List<Integer> blockedSpots = List.of(0,4,5,6,7,9,13,14,16,17,18,22,23,24,25,26);

    private final Map<ItemStack, List<ItemStack>> recipes = new HashMap<>();

    private final ItemStack backButton = createGuiItem(Material.BOOK, "Back", null);
    private final ItemStack toolCategoryItem = createGuiItem(Material.DIAMOND_PICKAXE, "Nástroje", null);
    private final ItemStack breweryCategoryItem = createGuiItem(Material.POTION, "Brewery", Color.fromRGB(55, 209, 122));


    public RecipesGUI(Plugin plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getCommand("recepty").setExecutor(this);

        recipes.put(CustomItems.RANDOMIZER, CustomItems.RANDOMIZER_RECIPE);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(sender instanceof Player player)){
            sender.sendMessage("Only players can use this command");
            return true;
        }
        openMainInventory(player);
        return false;

    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory clickedInventory = event.getClickedInventory();
        ItemStack clickedItem = event.getCurrentItem();

        if (clickedInventory == null || clickedItem == null) return;

        String title = event.getView().getTitle();

        if (title.contains("Aeterum")){
            event.setCancelled(true);
            if (clickedItem.equals(toolCategoryItem)){
                openToolInventory(player);
            }
            if (clickedItem.equals(CustomItems.RANDOMIZER)){
                openRecipeInventory(player, clickedItem);
            }
            if (clickedItem.equals(backButton)){
                openMainInventory(player);
            }
        }
    }

    private void openRecipeInventory(Player player, ItemStack clickedItem) {
        // Get the recipe for the clicked item
        List<ItemStack> recipe = recipes.get(clickedItem);

        if (recipe == null) {
            player.sendMessage("No recipe found for this item.");
            return;
        }

        // Create the inventory
        recipeInventory = Bukkit.createInventory(null, 27, "Aeterum " + clickedItem.getItemMeta().getDisplayName());
        fillWithBlockers(recipeInventory);

        // Define the slots for the 3x3 recipe grid
        int[] recipeSlots = {1, 2, 3, 10, 11, 12, 19, 20, 21};

        // Place the recipe items in the designated slots
        for (int i = 0; i < recipe.size() && i < recipeSlots.length; i++) {
            recipeInventory.setItem(recipeSlots[i], recipe.get(i));
        }

        // Place the output item in slot 15 (assuming the clicked item is the output)
        recipeInventory.setItem(15, clickedItem);

        recipeInventory.setItem(8, backButton);
        player.openInventory(recipeInventory);
    }

    private void fillWithBlockers(Inventory inventory) {
        ItemStack blocker = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        for (Integer blockedSpot : blockedSpots){
            inventory.setItem(blockedSpot, blocker);
        }
    }

    private void openToolInventory(Player player) {
        toolInventory = Bukkit.createInventory(null, 9, "Aeterum Nástroje");
        toolInventory.addItem(CustomItems.RANDOMIZER);
        toolInventory.setItem(8, backButton);
        player.openInventory(toolInventory);
    }

    private void openMainInventory(Player player) {
        mainInventory = Bukkit.createInventory(null, 9, "Aeterum Recepty");
        mainInventory.addItem(toolCategoryItem);
        mainInventory.addItem(breweryCategoryItem);
        player.openInventory(mainInventory);
    }
    private ItemStack createGuiItem(Material material, String name, Color color) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        if (item.getType() == Material.POTION || item.getType() == Material.SPLASH_POTION || item.getType() == Material.LINGERING_POTION){
            if (meta instanceof PotionMeta){
                PotionMeta potionMeta = (PotionMeta) meta;
                potionMeta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
                if (color != null){
                    potionMeta.setColor(color);
                }
            }
        }
        item.setItemMeta(meta);
        return item;
    }
}
