package me.dash.mapsplus.core.renderers;

import me.dash.mapsplus.core.MapsPlusPlugin;
import me.dash.mapsplus.core.utility.PlayerHandUtility;
import me.dash.mapsplus.core.data.PlayerMapCursorData;
import me.dash.mapsplus.core.data.PlayerMapCursorManager;
import me.dash.mapsplus.core.records.MapPosition;
import org.bukkit.entity.Player;
import org.bukkit.map.*;

import java.util.Map;
import java.util.UUID;

public class PlayerMapRenderer extends MapRenderer implements CustomMapRenderer {

    private final PlayerMapCursorManager playerMapCursorManager;

    public PlayerMapRenderer(MapsPlusPlugin plugin) {
        super(false);
        this.playerMapCursorManager = plugin.getPlayerMapCursorManager();
    }

    @Override
    public void render(MapView map, MapCanvas canvas, Player player) {
        int mapScale = getScaleSize(map.getScale());
        MapCursorCollection mapCursorCollection = new MapCursorCollection();

        for (Map.Entry<UUID, PlayerMapCursorData> entry : playerMapCursorManager.getPlayerMapCursorEntrySet()) {
            PlayerMapCursorData playerMapCursor = entry.getValue();
            MapPosition mapPosition = findRelPosition(map.getCenterX(), map.getCenterZ(), playerMapCursor.getPosX(), playerMapCursor.getPosZ(), mapScale);

            MapCursor mapCursor = playerMapCursor.getMapCursor();
            mapCursor.setX((byte)mapPosition.mapPosX());
            mapCursor.setY((byte)mapPosition.mapPosY());
            mapCursor.setVisible(mapPosition.isInBounds());

            if (player.getUniqueId().equals(entry.getKey()) && PlayerHandUtility.checkHandsForMap(player, map.getId())) {
                mapCursor = new MapCursor(mapCursor.getX(), mapCursor.getY(), mapCursor.getDirection(), MapCursor.Type.WHITE_POINTER, mapCursor.isVisible());

                if (!mapPosition.isInBounds() && PlayerHandUtility.checkHandsForMap(player, map.getId())) {
                    mapCursor.setType(MapCursor.Type.WHITE_CIRCLE);
                    mapCursor.setVisible(true);
                    mapCursor.setDirection((byte)0);
                }
            }

            mapCursorCollection.addCursor(mapCursor);
        }

        canvas.setCursors(mapCursorCollection);
    }
}