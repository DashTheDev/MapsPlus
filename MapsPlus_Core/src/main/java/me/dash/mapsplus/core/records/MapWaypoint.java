package me.dash.mapsplus.core.records;

import org.bukkit.Location;
import org.bukkit.map.MapCursor;

import java.util.UUID;

public record MapWaypoint(UUID waypointUid, Location location, String waypointName, MapCursor.Type waypointMapCursorType) {

}