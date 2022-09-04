package me.dash.mapsplus.data;

import me.dash.mapsplus.MapsPlus;
import me.dash.mapsplus.records.MapWaypoint;
import me.dash.mapsplus.utility.MapCursorUtility;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class WaypointMapCursorManager {

    private final File waypointDataFile;
    private final FileConfiguration waypointDataYml;
    private final HashMap<UUID, WaypointMapCursorData> waypointMapCursors;

    public WaypointMapCursorManager(MapsPlus plugin) {
        this.waypointDataFile = new File(plugin.getDataFolder().getAbsolutePath() + "/waypointdata.yml");
        this.waypointDataYml = YamlConfiguration.loadConfiguration(waypointDataFile);
        this.waypointMapCursors = new HashMap<>();
        loadAllWaypointDataFromYml();
    }

    public Collection<WaypointMapCursorData> getWaypointMapCursorValues() {
        return waypointMapCursors.values();
    }

    public void addWaypointMapCursor(MapWaypoint mapWaypoint) {
        if (!waypointMapCursors.containsKey(mapWaypoint.waypointUid())) {
            WaypointMapCursorData waypointMapCursorData = new WaypointMapCursorData(mapWaypoint);
            waypointMapCursors.put(mapWaypoint.waypointUid(), waypointMapCursorData);
        }
    }

    public void removeWaypointMapCursor(UUID waypointUUID) {
        waypointMapCursors.remove(waypointUUID);
    }

    public void updateWaypointMapCursor(MapWaypoint mapWaypoint) {
        WaypointMapCursorData waypointMapCursorData = waypointMapCursors.get(mapWaypoint.waypointUid());

        if (waypointMapCursorData == null) {
            waypointMapCursorData = new WaypointMapCursorData(mapWaypoint);
        }

        waypointMapCursorData.setLocation(mapWaypoint.location());
    }

    private void loadAllWaypointDataFromYml() {
        for (String key : waypointDataYml.getKeys(false)) {
            UUID waypointUUID = UUID.fromString(key);
            WaypointMapCursorData waypointMapCursorData = new WaypointMapCursorData(getWaypointDataFromYml(waypointUUID));
            waypointMapCursors.put(waypointUUID, waypointMapCursorData);
        }
    }

    private MapWaypoint getWaypointDataFromYml(UUID waypointUUID) {
        return new MapWaypoint(waypointUUID,
                waypointDataYml.getLocation(waypointUUID + ".Location"),
                waypointDataYml.getString(waypointUUID + ".Name"),
                MapCursorUtility.getMapCursorType(waypointDataYml.getInt(waypointUUID + ".Colour"))
        );
    }

    public void saveAllWaypointDataToYml() {
        for (Map.Entry<UUID, WaypointMapCursorData> entry : waypointMapCursors.entrySet()) {
            saveWaypointDataToYml(entry.getKey(), entry.getValue());
        }

        HashSet<String> waypointsToDelete = new HashSet<>();

        for (String key : waypointDataYml.getKeys(false)) {
            UUID waypointUUID = UUID.fromString(key);
            if (!waypointMapCursors.containsKey(waypointUUID)) {
                waypointsToDelete.add(waypointUUID.toString());
            }
        }

        for (String waypointUUID : waypointsToDelete) {
            waypointDataYml.set(waypointUUID, null);
        }

        try {
            waypointDataYml.save(waypointDataFile);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void saveWaypointDataToYml(UUID waypointUUID, WaypointMapCursorData waypointMapCursorData) {
        waypointDataYml.set(waypointUUID + ".Location", waypointMapCursorData.getLocation());
        waypointDataYml.set(waypointUUID + ".Name", waypointMapCursorData.getMapCursor().getCaption());
        waypointDataYml.set(waypointUUID + ".Colour", MapCursorUtility.getMapCursorTypeInt(waypointMapCursorData.getMapCursor().getType()));
    }
}
