package me.dash.mapsplus.renderers;

import me.dash.mapsplus.records.MapPosition;
import org.bukkit.map.*;

public interface CustomMapRenderer {

    int MAP_DIAMETER = 128;
    int MAP_RADIUS = 64;

    default int getScaleSize(MapView.Scale scale) {
        return switch (scale) {
            case CLOSEST -> 1;
            case CLOSE -> 2;
            case NORMAL -> 4;
            case FAR -> 8;
            case FARTHEST -> 16;
        };
    }

    default MapPosition findRelPosition(int mapX, int mapZ, int locX, int locZ, int scale) {
        int adjustedX = (int)(MAP_DIAMETER * ((locX - mapX) / ((double) scale * MAP_RADIUS)));
        int adjustedZ = (int)(MAP_DIAMETER * ((locZ - mapZ) / ((double) scale * MAP_RADIUS)));
        boolean isInBounds = true;

        if (adjustedX > MAP_DIAMETER) {
            isInBounds = false;
            adjustedX = MAP_DIAMETER;
        } else if (adjustedX < -MAP_DIAMETER) {
            isInBounds = false;
            adjustedX = -MAP_DIAMETER;
        }

        if (adjustedZ > MAP_DIAMETER) {
            isInBounds = false;
            adjustedZ = MAP_DIAMETER;
        } else if (adjustedZ < -MAP_DIAMETER) {
            isInBounds = false;
            adjustedZ = -MAP_DIAMETER;
        }

        return new MapPosition(adjustedX, adjustedZ, isInBounds);
    }
}