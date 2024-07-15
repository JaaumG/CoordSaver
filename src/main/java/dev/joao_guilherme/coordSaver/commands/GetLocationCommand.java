package dev.joao_guilherme.coordSaver.commands;

import dev.joao_guilherme.coordSaver.models.Locations;
import dev.joao_guilherme.coordSaver.utils.LocationsUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GetLocationCommand implements CommandExecutor, TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length != 1) {
                sender.sendMessage(Component.text("Você precisa especificar o nome da localização").color(NamedTextColor.RED));
            }

            String name = args[0];
            Locations locations = LocationsUtils.getCoordinate(player, name);

            if (locations != null) {
                sender.sendMessage(Component.text("Nome: " + locations.getName()).color(NamedTextColor.GREEN));
                sender.sendMessage(Component.text("X: " + locations.getX()).color(NamedTextColor.GREEN));
                sender.sendMessage(Component.text("Y: " + locations.getY()).color(NamedTextColor.GREEN));
                sender.sendMessage(Component.text("Z: " + locations.getZ()).color(NamedTextColor.GREEN));
            } else {
                sender.sendMessage(Component.text("Localização não encontrada").color(NamedTextColor.RED));
            }
        } else {
            sender.sendMessage(Component.text("Você precisa ser um jogador para usar este comando!").color(NamedTextColor.RED));
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 1) {
                return LocationsUtils.getNames(player);
            }
        }
        return List.of();
    }
}
