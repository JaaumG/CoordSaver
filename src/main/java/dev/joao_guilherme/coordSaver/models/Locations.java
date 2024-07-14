package dev.joao_guilherme.coordSaver.models;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Locations implements Serializable {

    private final String name;
    private final String x;
    private final String y;
    private final String z;


    public Locations(String name, double x, double y, double z) {
        this.name = name;
        this.x = BigDecimal.valueOf(x).setScale(2, RoundingMode.DOWN).toPlainString();
        this.y = BigDecimal.valueOf(y).setScale(2, RoundingMode.DOWN).toPlainString();
        this.z = BigDecimal.valueOf(z).setScale(2, RoundingMode.DOWN).toPlainString();
    }

    public String getName() {
        return name;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public String getZ() {
        return z;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Locations locations = (Locations) object;
        return Objects.equals(name, locations.name) && Objects.equals(x, locations.x) && Objects.equals(y, locations.y) && Objects.equals(z, locations.z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, x, y, z);
    }

    public byte[] serialize() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try (ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(this);
            out.flush();
            return bos.toByteArray();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Locations deserialize(byte[] bytes) {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);

        try (ObjectInput in = new ObjectInputStream(bis)) {
            return (Locations) in.readObject();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String toString() {
        return name + "\n   X: " + x + "\n   Y: " + y + "\n   Z: " + z + "\n";
    }

    public Component toComponent() {
        return Component.text(name + "\n").color(NamedTextColor.BLACK).append(
            Component.text("   X: " + x + "\n").color(NamedTextColor.DARK_GREEN).append(
                Component.text("   Y: " + y + "\n").color(NamedTextColor.DARK_GREEN).append(
                    Component.text("   Z: " + z + "\n").color(NamedTextColor.DARK_GREEN)
                )
            )
        );
    }
}
