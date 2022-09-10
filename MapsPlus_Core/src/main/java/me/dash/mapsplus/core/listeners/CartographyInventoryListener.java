package me.dash.mapsplus.core.listeners;

import me.dash.mapsplus.core.events.CartographyResultEvent;
import me.dash.mapsplus.core.MapsPlusPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.CartographyInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class CartographyInventoryListener implements Listener {

    private final MapsPlusPlugin plugin;

    public CartographyInventoryListener(MapsPlusPlugin plugin) {
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
