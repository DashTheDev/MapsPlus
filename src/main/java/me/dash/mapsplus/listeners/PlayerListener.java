package me.dash.mapsplus.listeners;

import me.dash.mapsplus.MapsPlus;
import me.dash.mapsplus.data.PlayerMapCursorManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public class PlayerListener implements Listener {

    private final PlayerMapCursorManager playerMapCursorManager;

    public PlayerListener(MapsPlus plugin) {
        this.playerMapCursorManager = plugin.getPlayerMapCursorManager();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        playerMapCursorManager.addPlayerMapCursor(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        playerMapCursorManager.removePlayerMapCursor(event.getPlayer());
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        playerMapCursorManager.updatePlayerMapCursor(event.getPlayer());
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        playerMapCursorManager.updatePlayerMapCursor(event.getPlayer(), event.getTo());
    }
}
