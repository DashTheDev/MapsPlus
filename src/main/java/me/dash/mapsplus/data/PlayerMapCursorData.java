package me.dash.mapsplus.data;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCursor;

public class PlayerMapCursorData {

    private MapCursor mapCursor;
    private int posX;
    private int posZ;

    public PlayerMapCursorData(Player player) {
        this.mapCursor = new MapCursor((byte)0, (byte)0, (byte)0, MapCursor.Type.GREEN_POINTER, true, player.getDisplayName());
        this.posX = player.getLocation().getBlockX();
        this.posZ = player.getLocation().getBlockZ();
    }

    public MapCursor getMapCursor() {
        return mapCursor;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosZ() {
        return posZ;
    }

    public void setPosZ(int posZ) {
        this.posZ = posZ;
    }
}
