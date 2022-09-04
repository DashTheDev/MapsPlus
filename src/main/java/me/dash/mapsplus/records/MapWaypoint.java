package me.dash.mapsplus.records;

import org.bukkit.DyeColor;
import org.bukkit.Location;

import java.util.UUID;

public record MapWaypoint(UUID waypointUid, Location location, String waypointName, DyeColor waypointColour) {

}