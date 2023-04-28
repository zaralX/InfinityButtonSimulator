package ru.zaralx.utils.zModules.database;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import ru.zaralx.InfinityButtonSimulator;
import ru.zaralx.utils.zModules.configs.config;
import ru.zaralx.utils.zModules.configs.defaultPlayerStats;

import java.sql.SQLException;
import java.util.UUID;

public class Save {
    public void autoSave() {
        new BukkitRunnable() {
            @Override
            public void run() {
                saveOnlinePlayers();
            }
        }.runTaskTimer(InfinityButtonSimulator.getInstance(), 0L, (int) config.get().get("autoSave.time"));
    }

    public static void saveOnlinePlayers() {
        for (int i = 0; i < InfinityButtonSimulator.data.size(); i++) {
            TempData plr_data = InfinityButtonSimulator.data.get(i);
            Bukkit.getPlayer(UUID.fromString(plr_data.uuid)).sendMessage("§aSaving..");
            save(plr_data);
        }
    }

    public static void save(TempData playerData) {
        InfinityButtonSimulator.getInstance().getLogger().warning("Starting save..");
        database db = InfinityButtonSimulator.getInstance().getDataBase();
        try {
            for (int i = 0; i < defaultPlayerStats.defaults.size(); i++) {
                db.executeUpdate("UPDATE player_stats SET "+defaultPlayerStats.defaults.get(i).getFirst()+" = " + playerData.data.get(i).getSecond() + " WHERE player_uuid = '" + playerData.uuid + "'");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        InfinityButtonSimulator.getInstance().getLogger().info("Save completed!");
    }
}
