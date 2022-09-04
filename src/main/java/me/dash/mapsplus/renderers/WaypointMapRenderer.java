package me.dash.mapsplus.renderers;

import me.dash.mapsplus.MapsPlus;
import me.dash.mapsplus.data.*;
import me.dash.mapsplus.records.MapPosition;
import org.bukkit.entity.Player;
import org.bukkit.map.*;

public class WaypointMapRenderer extends MapRenderer implements CustomMapRenderer {

    private final WaypointMapCursorManager waypointMapCursorManager;

    public WaypointMapRenderer(MapsPlus plugin) {
        super(false);
        this.waypointMapCursorManager = plugin.getWaypointMapCursorManager();
    }

    @Override
    public void render(MapView map, MapCanvas canvas, Player player) {
        int mapScale = getScaleSize(map.getScale());
        MapCursorCollection mapCursorCollection = new MapCursorCollection();

        for (WaypointMapCursorData waypointMapCursorData : waypointMapCursorManager.getWaypointMapCursorValues()) {
            MapPosition mapPosition = findRelPosition(map.getCenterX(), map.getCenterZ(), waypointMapCursorData.getPosX(), waypointMapCursorData.getPosZ(), mapScale);

            MapCursor mapCursor = waypointMapCursorData.getMapCursor();
            mapCursor.setX((byte)mapPosition.mapPosX());
            mapCursor.setY((byte)mapPosition.mapPosY());
            mapCursor.setVisible(mapPosition.isInBounds());
            mapCursorCollection.addCursor(mapCursor);
        }

        canvas.setCursors(mapCursorCollection);
    }
}