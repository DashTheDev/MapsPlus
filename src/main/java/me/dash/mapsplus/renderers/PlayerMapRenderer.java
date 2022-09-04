package me.dash.mapsplus.renderers;

import me.dash.mapsplus.MapsPlus;
import me.dash.mapsplus.utility.PlayerHandUtility;
import me.dash.mapsplus.data.PlayerMapCursorData;
import me.dash.mapsplus.data.PlayerMapCursorManager;
import me.dash.mapsplus.records.MapPosition;
import org.bukkit.entity.Player;
import org.bukkit.map.*;

import java.util.Map;
import java.util.UUID;

public class PlayerMapRenderer extends MapRenderer implements CustomMapRenderer {

    private final PlayerMapCursorManager playerMapCursorManager;

    public PlayerMapRenderer(MapsPlus plugin) {
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

            if (player.getUniqueId().equals(entry.getKey())) {
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