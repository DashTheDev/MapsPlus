package me.dash.mapsplus.listeners;

import me.dash.mapsplus.MapsPlus;
import me.dash.mapsplus.renderers.TerrainMapRenderer;
import net.minecraft.world.level.saveddata.maps.WorldMap;
import org.bukkit.craftbukkit.v1_19_R1.map.CraftMapView;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.map.MapRenderer;

import java.lang.reflect.Field;

public class MapInitialiseListener implements Listener {

    private MapsPlus plugin;

    public MapInitialiseListener(MapsPlus plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMapInitialise(MapInitializeEvent event) {
        try {
            Field field  = CraftMapView.class.getDeclaredField("worldMap");
            field.setAccessible(true);
            WorldMap worldMap = (WorldMap)field.get(event.getMap());

            for (MapRenderer renderer : event.getMap().getRenderers()) {
                event.getMap().removeRenderer(renderer);
            }

            event.getMap().addRenderer(new TerrainMapRenderer(worldMap));
            event.getMap().addRenderer(plugin.getPlayerMapRenderer());
            event.getMap().addRenderer(plugin.getWaypointMapRenderer());
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
