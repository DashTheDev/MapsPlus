package me.dash.mapsplus.core.listeners;

import me.dash.mapsplus.core.data.persistent.CustomPersistentDataType;
import me.dash.mapsplus.core.events.MapWaypointRemoveEvent;
import me.dash.mapsplus.core.utility.MapCursorUtility;
import me.dash.mapsplus.core.MapsPlusPlugin;
import me.dash.mapsplus.core.events.MapWaypointAddEvent;
import me.dash.mapsplus.core.records.MapWaypoint;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

public class MapWaypointListener implements Listener {

    private final MapsPlusPlugin plugin;

    public MapWaypointListener(MapsPlusPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMapWaypointAdd(MapWaypointAddEvent event) {
        if (!event.getPlayer().hasPermission("mp.waypoints.add")) return;

        MapWaypoint mapWaypoint = new MapWaypoint(UUID.randomUUID(),
                event.getBanner().getLocation(),
                event.hasBannerName() ? event.getBannerName() : "Waypoint",
                MapCursorUtility.getMapCursorType(event.getBanner().getBaseColor()));

        NamespacedKey key = new NamespacedKey(plugin, "waypoint-uuid");
        event.getBanner().getPersistentDataContainer().set(key, CustomPersistentDataType.UUID, mapWaypoint.waypointUid());
        event.getBanner().update();

        plugin.getWaypointMapCursorManager().addWaypointMapCursor(mapWaypoint);
    }

    @EventHandler
    public void onMapWaypointRemove(MapWaypointRemoveEvent event) {
        if (!event.getPlayer().hasPermission("mp.waypoints.remove")) return;

        plugin.getWaypointMapCursorManager().removeWaypointMapCursor(event.getWaypointUUID());
    }
}
