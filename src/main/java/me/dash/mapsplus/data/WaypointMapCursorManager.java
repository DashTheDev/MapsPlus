package me.dash.mapsplus.data;

import me.dash.mapsplus.records.MapWaypoint;

import java.util.*;

public class WaypointMapCursorManager {

    private HashMap<UUID, WaypointMapCursorData> waypointMapCursors;

    public WaypointMapCursorManager() {
        this.waypointMapCursors = new HashMap<>();
    }

    public Collection<WaypointMapCursorData> getWaypointMapCursorValues() {
        return waypointMapCursors.values();
    }

    public void addWaypointMapCursor(MapWaypoint mapWaypoint) {
        if (!waypointMapCursors.containsKey(mapWaypoint.waypointUid())) {
            WaypointMapCursorData waypointMapCursorData = new WaypointMapCursorData(mapWaypoint);
            waypointMapCursors.put(mapWaypoint.waypointUid(), waypointMapCursorData);
        }
    }

    public void removeWaypointMapCursor(UUID waypointUUID) {
        waypointMapCursors.remove(waypointUUID);
    }

    public void updateWaypointMapCursor(MapWaypoint mapWaypoint) {
        WaypointMapCursorData waypointMapCursorData = waypointMapCursors.get(mapWaypoint.waypointUid());

        if (waypointMapCursorData == null) {
            waypointMapCursorData = new WaypointMapCursorData(mapWaypoint);
        }

        waypointMapCursorData.setPosX(mapWaypoint.location().getBlockX());
        waypointMapCursorData.setPosZ(mapWaypoint.location().getBlockZ());
    }
}
