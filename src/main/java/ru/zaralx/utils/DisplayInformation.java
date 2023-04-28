package ru.zaralx.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ru.zaralx.InfinityButtonSimulator;
import ru.zaralx.utils.zModules.coloredText;
import ru.zaralx.utils.zModules.configs.config;
import ru.zaralx.utils.zModules.database.TempData;
import ru.zaralx.utils.zModules.intFormatter;

import java.util.List;
import java.util.Objects;

public class DisplayInformation {
    public void loop() {
        new BukkitRunnable() {
            String gameWorld = config.get().get("gameWorld").toString();
            @Override
            public void run() {
                List<Player> players = Bukkit.getWorld(gameWorld).getPlayers();
                for (Player player : players) {
                    actionbar(player);
                }
            }
        }.runTaskTimer(InfinityButtonSimulator.getInstance(), 0, 2);
    }

    public void actionbar(Player player) {
        StringBuilder message = new StringBuilder();
        TempData player_data = InfinityButtonSimulator.getInstance().getPlayerData(player);

        message.append(coloredText.colorize("%>#FF3333#<%"+new intFormatter(player_data.getBalance()).string+" Рубинов §f| %>#44FF44#<%Множитель x"+ new intFormatter(player_data.getDouble("Gaining")).string));

        player.sendActionBar(message.toString());
    }
}
