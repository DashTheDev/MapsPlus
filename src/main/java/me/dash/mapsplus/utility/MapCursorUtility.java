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

    public static MapCursor.Type getMapCursorType(int value) {
        return switch (value) {
            case 0 -> MapCursor.Type.BANNER_RED;
            case 1 -> MapCursor.Type.BANNER_BLUE;
            case 2 -> MapCursor.Type.BANNER_CYAN;
            case 3 -> MapCursor.Type.BANNER_GRAY;
            case 4 -> MapCursor.Type.BANNER_LIME;
            case 5 -> MapCursor.Type.BANNER_PINK;
            case 6 -> MapCursor.Type.BANNER_BLACK;
            case 7 -> MapCursor.Type.BANNER_BROWN;
            case 8 -> MapCursor.Type.BANNER_GREEN;
            case 9 -> MapCursor.Type.BANNER_WHITE;
            case 10 -> MapCursor.Type.BANNER_ORANGE;
            case 11 -> MapCursor.Type.BANNER_PURPLE;
            case 12 -> MapCursor.Type.BANNER_YELLOW;
            case 13 -> MapCursor.Type.BANNER_MAGENTA;
            case 14 -> MapCursor.Type.BANNER_LIGHT_BLUE;
            case 15 -> MapCursor.Type.BANNER_LIGHT_GRAY;
            default -> throw new IllegalStateException("Unexpected value: " + value);
        };
    }

    public static int getMapCursorTypeInt(MapCursor.Type mapCursorType) {
        return switch (mapCursorType) {
            case BANNER_RED -> 0;
            case BANNER_BLUE -> 1;
            case BANNER_CYAN -> 2;
            case BANNER_GRAY -> 3;
            case BANNER_LIME -> 4;
            case BANNER_PINK -> 5;
            case BANNER_BLACK -> 6;
            case BANNER_BROWN -> 7;
            case BANNER_GREEN -> 8;
            case BANNER_WHITE -> 9;
            case BANNER_ORANGE -> 10;
            case BANNER_PURPLE -> 11;
            case BANNER_YELLOW -> 12;
            case BANNER_MAGENTA -> 13;
            case BANNER_LIGHT_BLUE -> 14;
            case BANNER_LIGHT_GRAY -> 15;
            default -> throw new IllegalStateException("Unexpected value: " + mapCursorType);
        };
    }
}
