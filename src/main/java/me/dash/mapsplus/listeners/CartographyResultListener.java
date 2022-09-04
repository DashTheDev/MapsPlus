package me.dash.mapsplus.listeners;

import me.dash.mapsplus.MapsPlus;
import me.dash.mapsplus.events.CartographyResultEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;

public class CartographyResultListener implements Listener {

    private final MapsPlus plugin;

    public CartographyResultListener(MapsPlus plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCartographyResult(CartographyResultEvent event) {
        MapView mapView = ((MapMeta)event.getItem().getItemMeta()).getMapView();
        MapInitializeEvent mapInitializeEvent = new MapInitializeEvent(mapView);
        plugin.getServer().getPluginManager().callEvent(mapInitializeEvent);
    }
}
