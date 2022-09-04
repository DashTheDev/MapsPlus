package me.dash.mapsplus.utility;

import org.bukkit.DyeColor;
import org.bukkit.map.MapCursor;

public class MapCursorUtility {

    public static MapCursor.Type getMapCursorType(DyeColor dyeColour) {
        return switch (dyeColour) {
            case RED -> MapCursor.Type.BANNER_RED;
            case BLUE -> MapCursor.Type.BANNER_BLUE;
            case CYAN -> MapCursor.Type.BANNER_CYAN;
            case GRAY -> MapCursor.Type.BANNER_GRAY;
            case LIME -> MapCursor.Type.BANNER_LIME;
            case PINK -> MapCursor.Type.BANNER_PINK;
            case BLACK -> MapCursor.Type.BANNER_BLACK;
            case BROWN -> MapCursor.Type.BANNER_BROWN;
            case GREEN -> MapCursor.Type.BANNER_GREEN;
            case WHITE -> MapCursor.Type.BANNER_WHITE;
            case ORANGE -> MapCursor.Type.BANNER_ORANGE;
            case PURPLE -> MapCursor.Type.BANNER_PURPLE;
            case YELLOW -> MapCursor.Type.BANNER_YELLOW;
            case MAGENTA -> MapCursor.Type.BANNER_MAGENTA;
            case LIGHT_BLUE -> MapCursor.Type.BANNER_LIGHT_BLUE;
            case LIGHT_GRAY -> MapCursor.Type.BANNER_LIGHT_GRAY;
        };
    }
}
