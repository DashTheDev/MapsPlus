package me.dash.mapsplus.core.utility;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.MapMeta;

public class PlayerHandUtility {

    public static boolean checkHandsForMap(Player player, int mapID) {
        PlayerInventory inventory = player.getInventory();
        ItemStack mainHandItem = inventory.getItemInMainHand();
        ItemStack offHandItem = inventory.getItemInOffHand();
        return isSameMap(mainHandItem, mapID) || isSameMap(offHandItem, mapID);
    }

    private static boolean isFilledMap(ItemStack item) {
        return item != null && item.getType() == Material.FILLED_MAP;
    }

    private static boolean isSameMap(ItemStack item, int mapID) {
        if (isFilledMap(item) && item.getItemMeta() instanceof MapMeta mapMeta) {
            return mapMeta.getMapView().getId() == mapID;
        }

        return false;
    }
}
