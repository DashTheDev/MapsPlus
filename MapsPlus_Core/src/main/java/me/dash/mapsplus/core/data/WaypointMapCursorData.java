package me.dash.mapsplus.core.data;

import me.dash.mapsplus.core.records.MapWaypoint;
import org.bukkit.Location;
import org.bukkit.map.MapCursor;

public class WaypointMapCursorData {

    private final MapCursor mapCursor;
    private Location location;

    public WaypointMapCursorData(MapWaypoint mapWaypoint) {
        this.mapCursor = new MapCursor((byte)0, (byte)0, (byte)8, mapWaypoint.waypointMapCursorType(), true, mapWaypoint.waypointName());
        this.location = mapWaypoint.location();
    }

    public MapCursor getMapCursor() {
        return mapCursor;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
