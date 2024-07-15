package dev.joao_guilherme.coordSaver.commands;

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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class SaveLocationCommand implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            if (strings.length == 1) {
                String locName = strings[0];
                if (LocationsUtils.hasNameUsed(player, locName)) {
                    player.sendMessage(Component.text("O nome da localização já está em uso!").color(NamedTextColor.RED));
                    return true;
                }
                LocationsUtils.saveCoordinate(player, locName);
                player.sendMessage(Component.text("Localização salva com o nome: " + locName).color(NamedTextColor.GREEN));
            } else if (strings.length == 4) {
                String locName = strings[0];
                if (LocationsUtils.hasNameUsed(player, locName)) {
                    player.sendMessage(Component.text("O nome da localização já está em uso!").color(NamedTextColor.RED));
                    return true;
                }
                String x = new BigDecimal(strings[1].equals("~") ? String.valueOf(player.getLocation().getX()):strings[1]).setScale(2, RoundingMode.DOWN).toPlainString();
                String y = new BigDecimal(strings[2].equals("~") ? String.valueOf(player.getLocation().getY()):strings[3]).setScale(2, RoundingMode.DOWN).toPlainString();
                String z = new BigDecimal(strings[3].equals("~") ? String.valueOf(player.getLocation().getZ()):strings[2]).setScale(2, RoundingMode.DOWN).toPlainString();
                LocationsUtils.saveCoordinate(player, locName, x, y, z);
                player.sendMessage(Component.text("Localização salva com o nome: " + locName + " na posição " + x + ", " + y + ", " + z).color(NamedTextColor.GREEN));
            } else {
                player.sendMessage(Component.text("Você precisa informar o nome da localização e/ou as coordenadas X, Y e Z!").color(NamedTextColor.RED));
            }
        } else {
            commandSender.sendMessage(Component.text("Você precisa ser um jogador para usar este comando!").color(NamedTextColor.RED));
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            if (args.length == 1) {
                return List.of();
            }
            if (args.length == 2 || args.length == 3 || args.length == 4) {
                return List.of("~");
            }
        }
        return List.of();
    }
}
