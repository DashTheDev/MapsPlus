package me.dash.mapsplus.data;

import me.dash.mapsplus.records.MapWaypoint;
import me.dash.mapsplus.utility.MapCursorUtility;
import org.bukkit.map.MapCursor;

public class WaypointMapCursorData {

    private MapCursor mapCursor;
    private int posX;
    private int posZ;

    public WaypointMapCursorData(MapWaypoint mapWaypoint) {
        this.mapCursor = new MapCursor((byte)0, (byte)0, (byte)8, MapCursorUtility.getMapCursorType(mapWaypoint.waypointColour()), true, mapWaypoint.waypointName());
        this.posX = mapWaypoint.location().getBlockX();
        this.posZ = mapWaypoint.location().getBlockZ();
    }

    public MapCursor getMapCursor() {
        return mapCursor;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosZ() {
        return posZ;
    }

    public void setPosZ(int posZ) {
        this.posZ = posZ;
    }
}
