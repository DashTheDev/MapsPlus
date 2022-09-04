package me.dash.mapsplus.data;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerMapCursorManager {

    private final double INTERCARDINAL_INCREMENT = 11.25;
    private HashMap<UUID, PlayerMapCursorData> playerMapCursors;

    public PlayerMapCursorManager() {
        this.playerMapCursors = new HashMap<>();
    }

    public Set<Map.Entry<UUID, PlayerMapCursorData>> getPlayerMapCursorEntrySet() {
        return playerMapCursors.entrySet();
    }

    public void addPlayerMapCursor(Player player) {
        if (!playerMapCursors.containsKey(player.getUniqueId())) {
            PlayerMapCursorData playerMapCursorData = new PlayerMapCursorData(player);
            playerMapCursorData.getMapCursor().setDirection(getCardinalDirection(player));
            playerMapCursors.put(player.getUniqueId(), playerMapCursorData);
        }
    }

    public void removePlayerMapCursor(Player player) {
        playerMapCursors.remove(player.getUniqueId());
    }

    public void updatePlayerMapCursor(Player player) {
        updatePlayerMapCursor(player, player.getLocation());
    }

    public void updatePlayerMapCursor(Player player, Location location) {
        PlayerMapCursorData playerMapCursorData = playerMapCursors.get(player.getUniqueId());

        if (playerMapCursorData == null) {
            playerMapCursorData = new PlayerMapCursorData(player);
        }

        playerMapCursorData.setPosX(location.getBlockX());
        playerMapCursorData.setPosZ(location.getBlockZ());
        playerMapCursorData.getMapCursor().setDirection(getCardinalDirection(player));
    }

    private byte getCardinalDirection(Player player) {
        double rotation = player.getLocation().getYaw() % 360 + 180;
        if (rotation < 0) {
            rotation += 360.0;
        }

        if (rotation <= INTERCARDINAL_INCREMENT) {
            // N
            return (byte)8;
        } else if (rotation <= INTERCARDINAL_INCREMENT * 3) {
            // NNE
            return (byte)9;
        } else if (rotation <= INTERCARDINAL_INCREMENT * 5) {
            // NE
            return (byte)10;
        } else if (rotation <= INTERCARDINAL_INCREMENT * 7) {
            // ENE
            return (byte)11;
        } else if (rotation <= INTERCARDINAL_INCREMENT * 9) {
            // E
            return (byte)12;
        } else if (rotation <= INTERCARDINAL_INCREMENT * 11) {
            // ESE
            return (byte)13;
        } else if (rotation <= INTERCARDINAL_INCREMENT * 13) {
            // SE
            return (byte)14;
        } else if (rotation <= INTERCARDINAL_INCREMENT * 15) {
            // SSE
            return (byte)15;
        } else if (rotation <= INTERCARDINAL_INCREMENT * 17) {
            // S
            return (byte)0;
        } else if (rotation <= INTERCARDINAL_INCREMENT * 19) {
            // SSW
            return (byte)1;
        } else if (rotation <= INTERCARDINAL_INCREMENT * 21) {
            // SW
            return (byte)2;
        } else if (rotation <= INTERCARDINAL_INCREMENT * 23) {
            // WSW
            return (byte)3;
        } else if (rotation <= INTERCARDINAL_INCREMENT * 25) {
            // W
            return (byte)4;
        } else if (rotation <= INTERCARDINAL_INCREMENT * 27) {
            // WNW
            return (byte)5;
        } else if (rotation <= INTERCARDINAL_INCREMENT * 29) {
            // NW
            return (byte)6;
        } else if (rotation <= INTERCARDINAL_INCREMENT * 31) {
            // NNW
            return (byte)7;
        } else {
            // N
            return (byte)8;
        }
    }
}
