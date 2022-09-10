package me.dash.mapsplus.core.renderers;

import net.minecraft.world.level.saveddata.maps.WorldMap;
import org.bukkit.entity.Player;
import org.bukkit.map.*;

public class TerrainMapRenderer extends MapRenderer {

    private final WorldMap worldMap;

    public TerrainMapRenderer(WorldMap worldMap) {
        super(false);
        this.worldMap = worldMap;
    }

    @Override
    public void render(MapView map, MapCanvas canvas, Player player) {
        for (int x = 0; x < 128; ++x) {
            for (int y = 0; y < 128; ++y) {
                canvas.setPixel(x, y, worldMap.g[y * 128 + x]);
            }
        }
    }
}