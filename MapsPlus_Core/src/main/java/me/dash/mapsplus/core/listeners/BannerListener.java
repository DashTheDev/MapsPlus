package me.dash.mapsplus.core.listeners;

import me.dash.mapsplus.core.data.persistent.CustomPersistentDataType;
import me.dash.mapsplus.core.events.MapWaypointRemoveEvent;
import me.dash.mapsplus.core.MapsPlusPlugin;
import me.dash.mapsplus.core.events.MapWaypointAddEvent;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Banner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class BannerListener implements Listener {

    private final MapsPlusPlugin plugin;

    public BannerListener(MapsPlusPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBannerPlace(BlockPlaceEvent event) {
        if (event.getBlock().getState() instanceof Banner banner) {
            ItemStack itemStack = event.getItemInHand();
            if (itemStack.getItemMeta().getDisplayName().isEmpty()) return;

            NamespacedKey key = new NamespacedKey(plugin, "banner-name");
            banner.getPersistentDataContainer().set(key, PersistentDataType.STRING, itemStack.getItemMeta().getDisplayName());
            banner.update();
        }
    }

    @EventHandler
    public void onBannerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (event.getClickedBlock().getState() instanceof Banner banner) {
            ItemStack itemStack = event.getItem();
            if (itemStack != null && itemStack.getType() != Material.FILLED_MAP) return;

            NamespacedKey key = new NamespacedKey(plugin, "banner-name");
            String bannerName = banner.getPersistentDataContainer().get(key, PersistentDataType.STRING);

            MapWaypointAddEvent mapWaypointAddEvent = new MapWaypointAddEvent(event.getPlayer(), banner, bannerName);
            plugin.getServer().getPluginManager().callEvent(mapWaypointAddEvent);
        }
    }

    @EventHandler
    public void onBannerBreak(BlockBreakEvent event) {
        if (event.getBlock().getState() instanceof Banner banner) {
            NamespacedKey key = new NamespacedKey(plugin, "waypoint-uuid");
            UUID waypointUUID = banner.getPersistentDataContainer().get(key, CustomPersistentDataType.UUID);
            if (waypointUUID == null) return;

            MapWaypointRemoveEvent mapWaypointRemoveEvent = new MapWaypointRemoveEvent(event.getPlayer(), waypointUUID);
            plugin.getServer().getPluginManager().callEvent(mapWaypointRemoveEvent);
        }
    }
}
