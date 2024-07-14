package dev.joao_guilherme.coordSaver.commands;

import dev.joao_guilherme.coordSaver.models.Locations;
import dev.joao_guilherme.coordSaver.utils.LocationsUtils;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ListLocationCommand implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            if (strings.length == 0) {
                List<Component> locations = LocationsUtils.listCoordinates(player).stream().map(Locations::toComponent).toList();
                if (locations.isEmpty()) {
                    player.sendMessage(Component.text("Você não possui nenhuma localização salva!").color(NamedTextColor.RED));
                } else {
                    TextComponent text = Component.text("Suas coordenadas: \n").color(NamedTextColor.GOLD);
                    for (Component location : locations) {
                        text = text.append(location);
                    }

                    Book book = Book.builder()
                            .title(Component.text("Coordenadas").color(NamedTextColor.GOLD))
                            .author(Component.text("CoordSaver"))
                            .addPage(text).build();

                    player.openBook(book);
                }
            }
        } else {
            commandSender.sendMessage("Você precisa ser um jogador para usar este comando!");
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return List.of();
    }
}
