package me.dash.mapsplus.listeners;

import me.dash.mapsplus.MapsPlus;
import me.dash.mapsplus.events.MapWaypointAddEvent;
import org.bukkit.Material;
import org.bukkit.block.Banner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BannerInteractListener implements Listener {

    private MapsPlus plugin;

    public BannerInteractListener(MapsPlus plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBannerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (event.getClickedBlock().getState() instanceof Banner banner) {
            ItemStack itemStack = event.getItem();
            if (itemStack != null && itemStack.getType() != Material.FILLED_MAP) return;

            MapWaypointAddEvent mapWaypointAddEvent = new MapWaypointAddEvent(event.getPlayer(), banner, itemStack.getItemMeta().getDisplayName());
            plugin.getServer().getPluginManager().callEvent(mapWaypointAddEvent);
        }
    }
}
