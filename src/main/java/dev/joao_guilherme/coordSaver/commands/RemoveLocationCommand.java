package dev.joao_guilherme.coordSaver.commands;

import dev.joao_guilherme.coordSaver.utils.LocationsUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RemoveLocationCommand implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 1) {
                String locName = args[0];
                if (LocationsUtils.hasNameUsed(player, locName)) {
                    LocationsUtils.removeCoordinate(player, locName);
                    player.sendMessage("Localização removida com sucesso!");
                } else {
                    player.sendMessage("O nome da localização não foi encontrado!");
                }
            } else {
                player.sendMessage("Você precisa informar o nome da localização!");
            }
        } else {
            sender.sendMessage("Você precisa ser um jogador para usar este comando!");
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
