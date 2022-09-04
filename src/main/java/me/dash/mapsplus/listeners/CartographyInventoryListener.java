package me.dash.mapsplus.listeners;

import me.dash.mapsplus.MapsPlus;
import me.dash.mapsplus.events.CartographyResultEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.CartographyInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class CartographyInventoryListener implements Listener {

    private final MapsPlus plugin;

    public CartographyInventoryListener(MapsPlus plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCartographyInventoryClick(InventoryClickEvent event) {
        if (!event.isLeftClick()) return;
        if (!(event.getInventory() instanceof CartographyInventory)) return;
        if (event.getSlot() != 2) return;

        ItemStack resultItem = event.getInventory().getItem(2);

        if (resultItem != null && resultItem.getItemMeta() instanceof MapMeta) {
            CartographyResultEvent cartographyResultEvent = new CartographyResultEvent((Player)event.getWhoClicked(), resultItem);

            new BukkitRunnable() {
                @Override
                public void run() {
                    plugin.getServer().getPluginManager().callEvent(cartographyResultEvent);
                }
            }.runTaskLater(plugin, 1);
        }
    }
}
