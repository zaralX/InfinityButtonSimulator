package ru.zaralx.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ru.zaralx.InfinityButtonSimulator;
import ru.zaralx.utils.zModules.configs.config;
import ru.zaralx.utils.zModules.database.TempData;

import java.util.List;

public class MoneyMaking {
    public static void loop() {
        new BukkitRunnable() {
            final String gameWorld = (String) config.get().get("gameWorld");
            @Override
            public void run() {
                List<Player> players = Bukkit.getWorld(gameWorld).getPlayers();

                for (Player player : players) {
                    TempData player_data = InfinityButtonSimulator.getInstance().getPlayerData(player);
                    Double give = player_data.getDouble("Gaining")/5;
                    player_data.set("Balance", player_data.getBalance()+give);
                }
            }
        }.runTaskTimer(InfinityButtonSimulator.getInstance(), 0, 4);
    }
}
