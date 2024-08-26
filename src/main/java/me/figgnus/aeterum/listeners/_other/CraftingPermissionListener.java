package me.figgnus.aeterum.listeners._other;

import me.figgnus.aeterum.Plugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class CraftingPermissionListener implements Listener {
    private final Plugin plugin;

    private String message = "§cNemáš oprávnění vytvořit tento předmět!";

    public CraftingPermissionListener(Plugin plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this , plugin);
    }

    @EventHandler
    public void onPrepareCraft(PrepareItemCraftEvent event) {
        CraftingInventory inventory = event.getInventory();
        ItemStack result = inventory.getResult();
        if (result == null || !result.hasItemMeta() || !result.getItemMeta().hasCustomModelData() ) {
            return;
        }
        int itemId = result.getItemMeta().getCustomModelData();
        Player player = (Player) event.getView().getPlayer();
        // Check all god's permissions for the crafted item
        for (String item : plugin.getConfig().getConfigurationSection("permissions").getKeys(false)) {
            int customId = plugin.getConfig().getInt("permissions." + item + ".custom_id");
            if (customId == itemId) {
                String permission = plugin.getConfig().getString("permissions." + item + ".permission");
                if (permission != null && !player.hasPermission(permission)) {
                    denyCrafting(inventory, player);
                    return;
                }
            }
        }
    }
//    @EventHandler
//    public void onPrepareCraft(PrepareItemCraftEvent event) {
//        CraftingInventory inventory = event.getInventory();
//        ItemStack result = inventory.getResult();
//
//        if (result != null) {
//            Player player = (Player) event.getView().getPlayer();
//            checkDemeterPermissions(player, result, inventory);
//            checkDionysusPermissions(player, result, inventory);
//            checkHadesPermissions(player, result, inventory);
//            checkPoseidonPermissions(player, result, inventory);
//            checkHermesPermissions(player, result, inventory);
//            checkZeusPermissions(player, result, inventory);
//        }
//    }
//
//    private void checkDemeterPermissions(Player player, ItemStack result, CraftingInventory inventory) {
//        if (result.isSimilar(CustomItems.BETTER_BONEMEAL)) {
//            if (!player.hasPermission(PermissionUtils.demeterBetterBonemeal)) {
//                denyCrafting(inventory, player);
//            }
//        } else if (result.isSimilar(CustomItems.FLOWER_HORSE_TAME)) {
//            if (!player.hasPermission(PermissionUtils.demeterHorseTame)) {
//                denyCrafting(inventory, player);
//            }
//        } else if (result.isSimilar(CustomItems.GROWTH_POTION)) {
//            if (!player.hasPermission(PermissionUtils.demeterGrowthPotion)) {
//                denyCrafting(inventory, player);
//            }
//        } else if (result.isSimilar(CustomItems.HOE_OF_HARVEST)) {
//            if (!player.hasPermission(PermissionUtils.demeterHoe)) {
//                denyCrafting(inventory, player);
//            }
//        }
//    }
//    private void checkDionysusPermissions(Player player, ItemStack result, CraftingInventory inventory) {
//        if (result.isSimilar(CustomItems.DRUNK_HORSE_TAME)) {
//            if (!player.hasPermission(PermissionUtils.dionysusHorseTame)) {
//                denyCrafting(inventory, player);
//            }
//        } else if (result.isSimilar(CustomItems.PARTY_ATMOSPHERE)) {
//            if (!player.hasPermission(PermissionUtils.dionysusPartyAtmosphere)) {
//                denyCrafting(inventory, player);
//            }
//        } else if (result.isSimilar(CustomItems.PARTY_BALL)) {
//            if (!player.hasPermission(PermissionUtils.dionysusPartyBall)) {
//                denyCrafting(inventory, player);
//            }
//        } else if (result.isSimilar(CustomItems.RANDOM_EFFECT_POTION)) {
//            if (!player.hasPermission(PermissionUtils.dionysusRandomEffectPotion)) {
//                denyCrafting(inventory, player);
//            }
//        }
//    }
//    private void checkHadesPermissions(Player player, ItemStack result, CraftingInventory inventory) {
//        if (result.isSimilar(CustomItems.ZOMBIE_HORSE_TAME)) {
//            if (!player.hasPermission(PermissionUtils.hadesHorseTame)) {
//                denyCrafting(inventory, player);
//            }
//        } else if (result.isSimilar(CustomItems.DARK_PEARL)) {
//            if (!player.hasPermission(PermissionUtils.hadesDarkPearl)) {
//                denyCrafting(inventory, player);
//            }
//        } else if (result.isSimilar(CustomItems.DARK_PORTAL)) {
//            if (!player.hasPermission(PermissionUtils.hadesPortal)) {
//                denyCrafting(inventory, player);
//            }
//        } else if (result.isSimilar(CustomItems.DARKNESS_POTION)) {
//            if (!player.hasPermission(PermissionUtils.hadesDarknessPotion)) {
//                denyCrafting(inventory, player);
//            }
//        }
//    }
//    private void checkPoseidonPermissions(Player player, ItemStack result, CraftingInventory inventory) {
//        if (result.isSimilar(CustomItems.SEA_HORSE_TAME)) {
//            if (!player.hasPermission(PermissionUtils.poseidonHorseTame)) {
//                denyCrafting(inventory, player);
//            }
//        } else if (result.isSimilar(CustomItems.WATER_BREATHING_CROWN)) {
//            if (!player.hasPermission(PermissionUtils.poseidonCrown)) {
//                denyCrafting(inventory, player);
//            }
//        } else if (result.isSimilar(CustomItems.BETTER_TRIDENT)) {
//            if (!player.hasPermission(PermissionUtils.poseidonTrident)) {
//                denyCrafting(inventory, player);
//            }
//        }
//    }
//    private void checkHermesPermissions(Player player, ItemStack result, CraftingInventory inventory) {
//        if (result.isSimilar(CustomItems.SPEED_HORSE_TAME)) {
//            if (!player.hasPermission(PermissionUtils.hermesHorseTame)) {
//                denyCrafting(inventory, player);
//            }
//        } else if (result.isSimilar(CustomItems.SPEED_HORSE_ABILITY)) {
//            if (!player.hasPermission(PermissionUtils.hermesHorseAbility)) {
//                denyCrafting(inventory, player);
//            }
//        } else if (result.isSimilar(CustomItems.SPEED_BOOTS)) {
//            if (!player.hasPermission(PermissionUtils.hermesSpeedBoots)) {
//                denyCrafting(inventory, player);
//            }
//        } else if (result.isSimilar(CustomItems.FLYING_ITEM)) {
//            if (!player.hasPermission(PermissionUtils.hermesFlyingItem)) {
//                denyCrafting(inventory, player);
//            }
//        } else if (result.isSimilar(CustomItems.MESSENGER_PACK)) {
//            if (!player.hasPermission(PermissionUtils.hermesMessengerPack)) {
//                denyCrafting(inventory, player);
//            }
//        }
//    }
//    private void checkZeusPermissions(Player player, ItemStack result, CraftingInventory inventory) {
//        if (result.isSimilar(CustomItems.PEGASUS_TAME)) {
//            if (!player.hasPermission(PermissionUtils.zeusHorseTame)) {
//                denyCrafting(inventory, player);
//            }
//        } else if (result.isSimilar(CustomItems.PEGASUS_ABILITY)) {
//            if (!player.hasPermission(PermissionUtils.zeusHorseAbility)) {
//                denyCrafting(inventory, player);
//            }
//        } else if (result.isSimilar(CustomItems.BREEDING_ITEM)) {
//            if (!player.hasPermission(PermissionUtils.zeusBreedingItem)) {
//                denyCrafting(inventory, player);
//            }
//        } else if (result.isSimilar(CustomItems.LIGHTNING_SPEAR)) {
//            if (!player.hasPermission(PermissionUtils.zeusLightningSpear)) {
//                denyCrafting(inventory, player);
//            }
//        } else if (result.isSimilar(CustomItems.WEATHER_CHANGER)) {
//            if (!player.hasPermission(PermissionUtils.zeusWeatherChanger)) {
//                denyCrafting(inventory, player);
//            }
//        }
//    }

    private void denyCrafting(CraftingInventory inventory, Player player) {
        inventory.setResult(new ItemStack(Material.AIR)); // Clear the result to prevent crafting
        player.sendMessage(message);
    }
}
