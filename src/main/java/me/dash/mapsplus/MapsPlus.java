package me.dash.mapsplus;

import me.dash.mapsplus.data.PlayerMapCursorManager;
import me.dash.mapsplus.data.WaypointMapCursorManager;
import me.dash.mapsplus.listeners.*;
import me.dash.mapsplus.renderers.PlayerMapRenderer;
import me.dash.mapsplus.renderers.WaypointMapRenderer;
import org.bukkit.plugin.java.JavaPlugin;

public final class MapsPlus extends JavaPlugin {

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
