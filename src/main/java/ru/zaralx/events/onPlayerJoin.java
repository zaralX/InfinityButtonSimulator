package ru.zaralx.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.zaralx.InfinityButtonSimulator;
import ru.zaralx.utils.zModules.coloredText;
import ru.zaralx.utils.zModules.comboList;
import ru.zaralx.utils.zModules.configs.defaultPlayerStats;
import ru.zaralx.utils.zModules.database.TempData;
import ru.zaralx.utils.zModules.database.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class onPlayerJoin implements Listener {
    public onPlayerJoin(InfinityButtonSimulator plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(coloredText.colorize("%>#ff6738#<%"+e.getPlayer().getName()+" %>#ff8b38#<%joined!"));
        playerJoined(e.getPlayer());
    }

    public static void playerJoined(Player player) {
        player.spigot().setCollidesWithEntities(false);
        database db = InfinityButtonSimulator.getInstance().getDataBase();
        String uid = String.valueOf(player.getUniqueId());
        ArrayList<comboList.arrayClass> defaults = defaultPlayerStats.defaults;

        // Set default player stats if player not exists
        try {
            StringBuilder query = new StringBuilder("SELECT * FROM player_stats WHERE player_uuid = '" + uid + "'");
            if (db.execute(query.toString()) == null) {
                query = new StringBuilder("REPLACE INTO player_stats VALUES ('" + uid + "'");
                for (comboList.arrayClass aDefault : defaults) {
                    query.append(", ").append(aDefault.getSecond());
                }
                query.append(")");
                db.executeUpdate(query.toString());
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        // Spawn information
        // ***ADD***

        // Load player data in TempData
        try {
            ResultSet plr_cfg = db.execute("SELECT * FROM `player_stats` WHERE player_uuid = '" + player.getUniqueId() + "'");

            // Creating information
            ArrayList<comboList.arrayClass> add = new ArrayList<>();
            for (int i = 0; i < defaults.size(); i++) {
                add.add(new comboList.arrayClass(defaults.get(i).getFirst(), plr_cfg.getObject(i+2)));
            }

            // Create TempData for this player
            InfinityButtonSimulator.data.add(new TempData(player.getUniqueId().toString(), add));
        } catch (SQLException | ClassNotFoundException ex) {
            player.kickPlayer("Failed load!");
            throw new RuntimeException(ex);
        }
    }
}
