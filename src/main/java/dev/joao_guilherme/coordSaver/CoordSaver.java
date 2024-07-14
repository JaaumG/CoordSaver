package dev.joao_guilherme.coordSaver;

import dev.joao_guilherme.coordSaver.commands.GetLocationCommand;
import dev.joao_guilherme.coordSaver.commands.ListLocationCommand;
import dev.joao_guilherme.coordSaver.commands.RemoveLocationCommand;
import dev.joao_guilherme.coordSaver.commands.SaveLocationCommand;
import dev.joao_guilherme.coordSaver.utils.LocationsUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CoordSaver extends JavaPlugin {

    @Override
    public void onEnable() {
        LocationsUtils.setPlugin(this);
        getCommand("coords").setExecutor(new ListLocationCommand());
        getCommand("savecoord").setExecutor(new SaveLocationCommand());
        getCommand("removecoord").setExecutor(new RemoveLocationCommand());
        getCommand("getcoord").setExecutor(new GetLocationCommand());
        Bukkit.getConsoleSender().sendMessage(Component.text("[CoordSaver] Plugin initialized!").color(NamedTextColor.GREEN));
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Component.text("[CoordSaver] Plugin disabled!").color(NamedTextColor.RED));
    }
}
