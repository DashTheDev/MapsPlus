package me.dash.mapsplus.events;

import org.bukkit.block.Banner;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MapWaypointAddEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private Banner banner;
    private String bannerName;

    public MapWaypointAddEvent(Player player, Banner banner, String bannerName) {
        this.player = player;
        this.banner = banner;
        this.bannerName = bannerName;
    }

    public Player getPlayer() {
        return player;
    }

    public Banner getBanner() {
        return banner;
    }

    public String getBannerName() {
        return bannerName;
    }

    public boolean hasBannerName() {
        return bannerName != null;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
