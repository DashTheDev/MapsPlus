package me.dash.mapsplus.core;

import me.dash.mapsplus.core.data.PlayerMapCursorManager;
import me.dash.mapsplus.core.listeners.*;
import me.dash.mapsplus.core.renderers.PlayerMapRenderer;
import me.dash.mapsplus.core.renderers.WaypointMapRenderer;
import me.dash.mapsplus.core.data.WaypointMapCursorManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MapsPlusPlugin extends JavaPlugin {

    private PlayerMapCursorManager playerMapCursorManager;
    private WaypointMapCursorManager waypointMapCursorManager;
    private PlayerMapRenderer playerMapRenderer;
    private WaypointMapRenderer waypointMapRenderer;

    public PlayerMapCursorManager getPlayerMapCursorManager() {
        return playerMapCursorManager;
    }

    public WaypointMapCursorManager getWaypointMapCursorManager() {
        return waypointMapCursorManager;
    }

    public PlayerMapRenderer getPlayerMapRenderer() {
        return playerMapRenderer;
    }

    public WaypointMapRenderer getWaypointMapRenderer() {
        return waypointMapRenderer;
    }

    @Override
    public void onEnable() {
        playerMapCursorManager = new PlayerMapCursorManager();
        waypointMapCursorManager = new WaypointMapCursorManager(this);
        playerMapRenderer = new PlayerMapRenderer(this);
        waypointMapRenderer = new WaypointMapRenderer(this);

        getServer().getPluginManager().registerEvents(new MapInitialiseListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new CartographyInventoryListener(this), this);
        getServer().getPluginManager().registerEvents(new CartographyResultListener(this), this);
        getServer().getPluginManager().registerEvents(new BannerListener(this), this);
        getServer().getPluginManager().registerEvents(new MapWaypointListener(this), this);
    }

    @Override
    public void onDisable() {
        waypointMapCursorManager.saveAllWaypointDataToYml();
    }
}
