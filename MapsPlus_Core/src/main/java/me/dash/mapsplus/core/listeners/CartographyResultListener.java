package me.dash.mapsplus.core.listeners;

import me.dash.mapsplus.core.events.CartographyResultEvent;
import me.dash.mapsplus.core.MapsPlusPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;

public class CartographyResultListener implements Listener {

    private final MapsPlusPlugin plugin;

    public CartographyResultListener(MapsPlusPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCartographyResult(CartographyResultEvent event) {
        MapView mapView = ((MapMeta)event.getItem().getItemMeta()).getMapView();
        MapInitializeEvent mapInitializeEvent = new MapInitializeEvent(mapView);
        plugin.getServer().getPluginManager().callEvent(mapInitializeEvent);
    }
}
