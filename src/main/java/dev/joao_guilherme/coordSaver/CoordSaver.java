package dev.joao_guilherme.coordSaver;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CoordSaver extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(Component.text("[CoordSaver] Plugin initialized!").color(NamedTextColor.GREEN));
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Component.text("[CoordSaver] Plugin disabled!").color(NamedTextColor.RED));
    }
}
