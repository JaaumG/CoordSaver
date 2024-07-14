package dev.joao_guilherme.coordSaver.utils;

import dev.joao_guilherme.coordSaver.models.Locations;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class LocationsUtils {

    private static Plugin plugin;

    public static void setPlugin(@NotNull Plugin plugin) {
        LocationsUtils.plugin = plugin;
    }

    public static void saveCoordinate(@NotNull Player player, @NotNull String name) {
        saveCoordinate(player, name, player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
    }

    public static void saveCoordinate(@NotNull Player player, @NotNull String name, @NotNull String x, @NotNull String y, @NotNull String z) {
        saveCoordinate(player, name, Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z));
    }

    private static void saveCoordinate(@NotNull Player player, @NotNull String name, double x, double y, double z) {
        PersistentDataContainer data = player.getPersistentDataContainer();
        Locations locations = new Locations(name, x, y, z);
        data.set(new NamespacedKey(plugin, name), PersistentDataType.BYTE_ARRAY, locations.serialize());
    }

    public static @NotNull List<Locations> listCoordinates(@NotNull Player player) {
        List<Locations> locations = new ArrayList<>();
        PersistentDataContainer data = player.getPersistentDataContainer();
        for (NamespacedKey key : data.getKeys()) {
            locations.add(Locations.deserialize(data.get(key, PersistentDataType.BYTE_ARRAY)));
        }
        return locations;
    }

    public static void removeCoordinate(@NotNull Player player, @NotNull String name) {
        PersistentDataContainer data = player.getPersistentDataContainer();
        data.remove(new NamespacedKey(plugin, name));
    }

    public static boolean hasNameUsed(@NotNull Player player, @NotNull String name) {
        PersistentDataContainer data = player.getPersistentDataContainer();
        return data.has(new NamespacedKey(plugin, name), PersistentDataType.BYTE_ARRAY);
    }

    public static Locations getCoordinate(@NotNull Player player, String name) {
        PersistentDataContainer data = player.getPersistentDataContainer();
        return Locations.deserialize(data.get(new NamespacedKey(plugin, name), PersistentDataType.BYTE_ARRAY));
    }

    public static @NotNull List<String> getNames(@NotNull Player player) {
        List<String> names = new ArrayList<>();
        PersistentDataContainer data = player.getPersistentDataContainer();
        for (NamespacedKey key : data.getKeys()) {
            names.add(key.getKey());
        }
        return names;
    }
}
