package me.dash.mapsplus.listeners;

import me.dash.mapsplus.MapsPlus;
import me.dash.mapsplus.data.persistent.CustomPersistentDataType;
import me.dash.mapsplus.events.MapWaypointAddEvent;
import me.dash.mapsplus.events.MapWaypointRemoveEvent;
import me.dash.mapsplus.records.MapWaypoint;
import me.dash.mapsplus.utility.MapCursorUtility;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

public class MapWaypointListener implements Listener {

    private final MapsPlus plugin;

    public MapWaypointListener(MapsPlus plugin) {
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
