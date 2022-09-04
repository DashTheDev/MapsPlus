package me.dash.mapsplus.listeners;

import me.dash.mapsplus.MapsPlus;
import me.dash.mapsplus.data.UUIDDataType;
import me.dash.mapsplus.events.MapWaypointAddEvent;
import me.dash.mapsplus.records.MapWaypoint;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.UUID;

public class MapWaypointListener implements Listener {

    private MapsPlus plugin;

    public MapWaypointListener(MapsPlus plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMapWaypointAdd(MapWaypointAddEvent event) {
        if (!event.getPlayer().hasPermission("mp.waypoints.add")) return;

        MapWaypoint mapWaypoint = new MapWaypoint(UUID.randomUUID(),
                event.getBanner().getLocation(),
                event.hasBannerName() ? event.getBannerName() : "Waypoint",
                event.getBanner().getBaseColor());

        PersistentDataContainer persistentDataContainer = event.getBanner().getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(plugin, "waypoint-uuid");
        persistentDataContainer.set(key, new UUIDDataType(), mapWaypoint.waypointUid());
        event.getBanner().update();

        plugin.getWaypointMapCursorManager().addWaypointMapCursor(mapWaypoint);
    }
}
