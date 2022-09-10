package me.dash.mapsplus.core.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class MapWaypointRemoveEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final UUID waypointUUID;

    public MapWaypointRemoveEvent(Player player, UUID waypointUUID) {
        this.player = player;
        this.waypointUUID = waypointUUID;
    }

    public Player getPlayer() {
        return player;
    }

    public UUID getWaypointUUID() {
        return waypointUUID;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
