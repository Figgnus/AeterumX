package me.figgnus.aeterum.gui;

import me.figgnus.aeterum.Plugin;
import me.figgnus.aeterum.items.CustomItems;
import me.figgnus.aeterum.items.CustomFishing;
import me.figgnus.aeterum.utils.PermissionUtils;
import me.figgnus.aeterum.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

import java.util.*;

public class RecipesGUI implements CommandExecutor, Listener {
    private final Plugin plugin;

    private Inventory mainInventory;
    private Inventory toolInventory;
    private Inventory demeterInventory;
    private Inventory dionysusInventory;
    private Inventory hadesInventory;
    private Inventory poseidonInventory;
    private Inventory zeusInventory;
    private Inventory hermesInventory;
    private Inventory recipeInventory;
    private Inventory customFishingInventory;

    private Inventory rodsSubInventory;

    private final List<Integer> blockedSpots = List.of(0,1,2,6,7,9,11,15,17,18,19,20,24,25,26);
    private final Stack<Inventory> inventoryHistory = new Stack<>();
    private final Map<ItemStack, List<ItemStack>> recipes = new HashMap<>();

    private final ItemStack backButton = createGuiItem(Material.BOOK, "Back", null);
    private final ItemStack craftingTableItem = createGuiItem(Material.CRAFTING_TABLE, "Vytvořit v Crafting Table", null);
    private final ItemStack toolCategoryItem = createGuiItem(Material.DIAMOND_PICKAXE, "Nástroje", null);
    private final ItemStack demeterCategoryItem = ItemUtils.createHead("d2fe0f2e6c0ffeefbb84c32e71876b68dcbf7ac9e8420a3d1bf593aa21a8374a", "Démétér");
    private final ItemStack dionysusCategoryItem = ItemUtils.createHead("b2b0a1ca399f35dc54519c4c996f9629a510c49938151f759ec8f07041e78566", "Dionýsos");
    private final ItemStack hermesCategoryItem = ItemUtils.createHead("ae8e5160314bb7caa54d3e8d1be8e3a924b245e1c6a6d0a559c83d17f98ba4ce", "Hermes");
    private final ItemStack hadesCategoryItem = ItemUtils.createHead("492b27824182f9b81c7cf463ec7cd10b05e0640d38b56c8873196f19168f63ad", "Hades");
    private final ItemStack poseidonCategoryItem = ItemUtils.createHead("1f716c1a80da85d5e6784c336b2583d61dc76de3d99a1984d3e593721e21327", "Poseidon");
    private final ItemStack zeusCategoryItem = ItemUtils.createHead("dcd9ddf4fb9e25e62d2e98595d5168de2b3367ba78f3697be1c479f35102ad76", "Zeus");
    private final ItemStack breweryCategoryItem = createGuiItem(Material.POTION, "Brewery", Color.fromRGB(55, 209, 122));
    private final ItemStack customFishingCategoryItem = createGuiItem(Material.FISHING_ROD, "Custom Fishing", null);
    private final ItemStack rodsSubCategoryItem = createGuiItem(Material.FISHING_ROD, "Pruty", null);



    public RecipesGUI(Plugin plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        recipes.put(CustomItems.RANDOMIZER, CustomItems.RANDOMIZER_RECIPE);
        recipes.put(CustomItems.createInfiniteDebugStick(), CustomItems.INFINITE_DEBUG_STICK_RECIPE);
        recipes.put(CustomItems.createBasicDebugStick(), CustomItems.BASIC_DEBUG_STICK_RECIPE);

        recipes.put(CustomItems.FLOWER_HORSE_TAME, CustomItems.FLOWER_HORSE_TAME_RECIPE);
        recipes.put(CustomItems.BETTER_BONEMEAL, CustomItems.BETTER_BONEMEAL_RECIPE);
        recipes.put(CustomItems.GROWTH_POTION, CustomItems.GROWTH_POTION_RECIPE);
        recipes.put(CustomItems.HOE_OF_HARVEST, CustomItems.HOE_OF_HARVEST_RECIPE);

        recipes.put(CustomItems.DRUNK_HORSE_TAME, CustomItems.DRUNK_HORSE_TAME_RECIPE);
        recipes.put(CustomItems.PARTY_ATMOSPHERE, CustomItems.PARTY_ATMOSPHERE_RECIPE);
        recipes.put(CustomItems.PARTY_BALL, CustomItems.PARTY_BALL_RECIPE);
        recipes.put(CustomItems.RANDOM_EFFECT_POTION, CustomItems.RANDOM_EFFECT_POTION_RECIPE);

        recipes.put(CustomItems.ZOMBIE_HORSE_TAME, CustomItems.ZOMBIE_HORSE_TAME_RECIPE);
        recipes.put(CustomItems.DARKNESS_POTION, CustomItems.DARKNESS_POTION_RECIPE);
        recipes.put(CustomItems.DARK_PEARL, CustomItems.DARK_PEARL_RECIPE);
        recipes.put(CustomItems.DARK_PORTAL, CustomItems.DARK_PORTAL_RECIPE);

        recipes.put(CustomItems.SPEED_HORSE_TAME, CustomItems.SPEED_HORSE_TAME_RECIPE);
        recipes.put(CustomItems.SPEED_HORSE_ABILITY, CustomItems.SPEED_HORSE_ABILITY_RECIPE);
        recipes.put(CustomItems.FLYING_ITEM, CustomItems.FLYING_ITEM_RECIPE);
        recipes.put(CustomItems.MESSENGER_PACK, CustomItems.MESSENGER_PACK_RECIPE);
        recipes.put(CustomItems.SPEED_BOOTS, CustomItems.SPEED_BOOTS_RECIPE);

        recipes.put(CustomItems.SEA_HORSE_TAME, CustomItems.SEA_HORSE_TAME_RECIPE);
        recipes.put(CustomItems.WATER_BREATHING_CROWN, CustomItems.WATER_BREATHING_CROWN_RECIPE);
        recipes.put(CustomItems.BETTER_TRIDENT, CustomItems.BETTER_TRIDENT_RECIPE);

        recipes.put(CustomItems.PEGASUS_TAME, CustomItems.PEGASUS_TAME_RECIPE);
        recipes.put(CustomItems.PEGASUS_ABILITY, CustomItems.PEGASUS_ABILITY_RECIPE);
        recipes.put(CustomItems.BREEDING_ITEM, CustomItems.BREEDING_ITEM_RECIPE);
        recipes.put(CustomItems.WEATHER_CHANGER, CustomItems.WEATHER_CHANGER_RECIPE);
        recipes.put(CustomItems.LIGHTNING_SPEAR, CustomItems.LIGHTNING_SPEAR_RECIPE);

        // Custom Fishing Recipes
        recipes.put(CustomFishing.BEGINNER_ROD, CustomFishing.BEGINNER_ROD_RECIPE);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(sender instanceof Player player)){
            sender.sendMessage("Only players can use this command");
            return true;
        }
        openMainInventory(player);
        return true;
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory clickedInventory = event.getClickedInventory();
        ItemStack clickedItem = event.getCurrentItem();

        if (clickedInventory == null || clickedItem == null) return;
        if (clickedItem.getItemMeta() == null) return;

        String title = event.getView().getTitle();

        if (title.contains("Aeterum")){
            event.setCancelled(true);
            if (title.contains(clickedItem.getItemMeta().getDisplayName())){
                if (!player.hasPermission("aeterum.admin")){
                    return;
                }
                player.getInventory().addItem(clickedItem);
            }
            if (recipes.containsKey(clickedItem)){
                openRecipeInventory(player, clickedItem);
            }
            if (clickedItem.equals(toolCategoryItem)){
                openToolInventory(player);
            }
            if (clickedItem.equals(breweryCategoryItem)){
                player.sendMessage("Tato funkce bude brzy přidána.");
            }
            if (clickedItem.equals(customFishingCategoryItem)){
                openCustomFishingInventory(player);
            }
            if (clickedItem.equals(demeterCategoryItem)){
                openDemeterInventory(player);
            }
            if (clickedItem.equals(dionysusCategoryItem)){
                openDionysusInventory(player);
            }
            if (clickedItem.equals(hermesCategoryItem)){
                openHermesInventory(player);
            }
            if (clickedItem.equals(hadesCategoryItem)){
                openHadesInventory(player);
            }
            if (clickedItem.equals(poseidonCategoryItem)){
                openPoseidonInventory(player);
            }
            if (clickedItem.equals(zeusCategoryItem)){
                openZeusInventory(player);
            }
            if (clickedItem.equals(backButton)){
                openMainInventory(player);
            }
            if (clickedItem.equals(rodsSubCategoryItem)){
                openRodsInventory(player);
            }
        }
    }

    private void openRodsInventory(Player player) {
        rodsSubInventory = Bukkit.createInventory(null, 9, "Aeterum " + "Rods");
        rodsSubInventory.setItem(8, backButton);
        rodsSubInventory.addItem(CustomFishing.BEGINNER_ROD);
        player.openInventory(rodsSubInventory);
    }

    private void openCustomFishingInventory(Player player) {
        customFishingInventory = Bukkit.createInventory(null, 9, "Aeterum " + "Custom Fishing");
        customFishingInventory.addItem(rodsSubCategoryItem);
        customFishingInventory.setItem(8, backButton);
        player.openInventory(customFishingInventory);
    }

    private void openZeusInventory(Player player) {
        zeusInventory = Bukkit.createInventory(null, 9, "Aeterum " + "Zeus");
        zeusInventory.setItem(8, backButton);
        zeusInventory.addItem(CustomItems.PEGASUS_TAME);
        zeusInventory.addItem(CustomItems.PEGASUS_ABILITY);
        zeusInventory.addItem(CustomItems.WEATHER_CHANGER);
        zeusInventory.addItem(CustomItems.LIGHTNING_SPEAR);
        zeusInventory.addItem(CustomItems.BREEDING_ITEM);
        player.openInventory(zeusInventory);
    }

    private void openPoseidonInventory(Player player) {
        poseidonInventory = Bukkit.createInventory(null, 9, "Aeterum " + "Poseidon");
        poseidonInventory.setItem(8, backButton);
        poseidonInventory.addItem(CustomItems.SEA_HORSE_TAME);
        poseidonInventory.addItem(CustomItems.BETTER_TRIDENT);
        poseidonInventory.addItem(CustomItems.WATER_BREATHING_CROWN);
        player.openInventory(poseidonInventory);
    }

    private void openHadesInventory(Player player) {
        hadesInventory = Bukkit.createInventory(null, 9, "Aeterum " + "Hades");
        hadesInventory.setItem(8, backButton);
        hadesInventory.addItem(CustomItems.ZOMBIE_HORSE_TAME);
        hadesInventory.addItem(CustomItems.DARKNESS_POTION);
        hadesInventory.addItem(CustomItems.DARK_PEARL);
        hadesInventory.addItem(CustomItems.DARK_PORTAL);
        player.openInventory(hadesInventory);
    }

    private void openHermesInventory(Player player) {
        hermesInventory = Bukkit.createInventory(null, 9, "Aeterum " + "Hermes");
        hermesInventory.setItem(8, backButton);
        hermesInventory.addItem(CustomItems.SPEED_HORSE_TAME);
        hermesInventory.addItem(CustomItems.SPEED_HORSE_ABILITY);
        hermesInventory.addItem(CustomItems.FLYING_ITEM);
        hermesInventory.addItem(CustomItems.SPEED_BOOTS);
        hermesInventory.addItem(CustomItems.MESSENGER_PACK);
        player.openInventory(hermesInventory);
    }

    private void openDionysusInventory(Player player) {
        dionysusInventory = Bukkit.createInventory(null, 9, "Aeterum " + "Dionýsos");
        dionysusInventory.setItem(8, backButton);
        dionysusInventory.addItem(CustomItems.DRUNK_HORSE_TAME);
        dionysusInventory.addItem(CustomItems.PARTY_ATMOSPHERE);
        dionysusInventory.addItem(CustomItems.PARTY_BALL);
        dionysusInventory.addItem(CustomItems.RANDOM_EFFECT_POTION);
        player.openInventory(dionysusInventory);
    }

    private void openDemeterInventory(Player player) {
        demeterInventory = Bukkit.createInventory(null, 9, "Aeterum " + "Démétér");
        demeterInventory.addItem(CustomItems.FLOWER_HORSE_TAME);
        demeterInventory.addItem(CustomItems.BETTER_BONEMEAL);
        demeterInventory.addItem(CustomItems.GROWTH_POTION);
        demeterInventory.addItem(CustomItems.HOE_OF_HARVEST);
        demeterInventory.setItem(8, backButton);
        player.openInventory(demeterInventory);
    }

    private void openToolInventory(Player player) {
        toolInventory = Bukkit.createInventory(null, 9, "Aeterum Nástroje");
        toolInventory.addItem(CustomItems.RANDOMIZER);
        toolInventory.addItem(CustomItems.createBasicDebugStick());
        toolInventory.addItem(CustomItems.createInfiniteDebugStick());
        toolInventory.setItem(8, backButton);
        player.openInventory(toolInventory);
    }

    private void openMainInventory(Player player) {
        mainInventory = Bukkit.createInventory(null, 9, "Aeterum Recepty");
        mainInventory.addItem(toolCategoryItem);
        mainInventory.addItem(breweryCategoryItem);
        mainInventory.addItem(customFishingCategoryItem);
        if (player.hasPermission(PermissionUtils.demeterGuiPermission)){
            mainInventory.addItem(demeterCategoryItem);
        }
        if (player.hasPermission(PermissionUtils.dionysusGuiPermission)){
            mainInventory.addItem(dionysusCategoryItem);
        }
        if (player.hasPermission(PermissionUtils.hermesGuiPermission)){
            mainInventory.addItem(hermesCategoryItem);
        }
        if (player.hasPermission(PermissionUtils.hadesGuiPermission)){
            mainInventory.addItem(hadesCategoryItem);
        }
        if (player.hasPermission(PermissionUtils.zeusGuiPermission)){
            mainInventory.addItem(zeusCategoryItem);
        }
        if (player.hasPermission(PermissionUtils.poseidonGuiPermission)){
            mainInventory.addItem(poseidonCategoryItem);
        }
        player.openInventory(mainInventory);
    }
    private ItemStack createGuiItem(Material material, String name, Color color) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + name);
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
        int[] recipeSlots = {3, 4, 5, 12, 13, 14, 21, 22, 23};

        // Place the recipe items in the designated slots
        for (int i = 0; i < recipe.size() && i < recipeSlots.length; i++) {
            recipeInventory.setItem(recipeSlots[i], recipe.get(i));
        }

        // Place the output item in slot 15 (assuming the clicked item is the output)
        recipeInventory.setItem(16, clickedItem);
        recipeInventory.setItem(10, craftingTableItem);

        recipeInventory.setItem(8, backButton);
        player.openInventory(recipeInventory);
    }

    private void fillWithBlockers(Inventory inventory) {
        ItemStack blocker = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        for (Integer blockedSpot : blockedSpots){
            inventory.setItem(blockedSpot, blocker);
        }
    }
}
