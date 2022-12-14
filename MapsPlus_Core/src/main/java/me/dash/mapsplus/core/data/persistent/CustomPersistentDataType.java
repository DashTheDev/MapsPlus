package me.dash.mapsplus.core.data.persistent;

import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public interface CustomPersistentDataType {
    PersistentDataType<byte[], UUID> UUID = new UUIDDataType();
}
