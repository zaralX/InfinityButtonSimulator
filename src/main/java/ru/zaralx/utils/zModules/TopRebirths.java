package ru.zaralx.utils.zModules;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import ru.zaralx.InfinityButtonSimulator;
import ru.zaralx.utils.zModules.configs.config;
import ru.zaralx.utils.zModules.database.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;
import static org.bukkit.Bukkit.getWorld;

public class TopRebirths {

    private static List<ArmorStand> flytop = new ArrayList<>();
    public static void setup() {
        Location tmploc = new Location(
                getServer().getWorld(
                        (String) config.get().get("gameWorld")
                ),
                (Double) config.get().get("Top.Rebirths.X"),
                (Double) config.get().get("Top.Rebirths.Y")+3.6,
                (Double) config.get().get("Top.Rebirths.Z")
        );

        ArmorStand title = getWorld((String) config.get().get("gameWorld")).spawn(tmploc, ArmorStand.class);
        title.setCanMove(false);
        title.setCustomName(coloredText.colorize("§7• §fTOP %>#4444FF#<%Rebirths §7•"));
        title.setCustomNameVisible(true);
        title.setInvisible(true);
        title.setMarker(true);
        title.addScoreboardTag("Removable");
        flytop.add(title);

        tmploc.setY(tmploc.getY()-0.3);

        ArmorStand info = getWorld((String) config.get().get("gameWorld")).spawn(tmploc, ArmorStand.class);
        info.setCanMove(false);
        info.setCustomName("§6PLAYER   §7|   §9Rebirths");
        info.setCustomNameVisible(true);
        info.setInvisible(true);
        info.setMarker(true);
        info.addScoreboardTag("Removable");
        flytop.add(info);

        tmploc.setY(tmploc.getY()-0.4);

        for (int i = 0; i < 10; i++) {
            ArmorStand top = getWorld((String) config.get().get("gameWorld")).spawn(tmploc, ArmorStand.class);
            top.setCanMove(false);
            top.setCustomName("§e"+(i+1)+". §8<- ¤ ¤ ¤ -> §7► §80");
            top.setCustomNameVisible(true);
            top.setInvisible(true);
            top.setMarker(true);
            top.addScoreboardTag("Removable");
            flytop.add(top);
            tmploc.setY(tmploc.getY()-0.3);
        }
    }

    public static void removeall() {
        for (ArmorStand armorStand : flytop) {
            armorStand.remove();
        }
    }

    public static void update() {
        database db = InfinityButtonSimulator.getInstance().getDataBase();
        try {
            ResultSet top = db.execute("SELECT * FROM player_stats ORDER BY Rebirths DESC LIMIT 10");
            if (top == null) {
                return;
            }
            int toping = 2;
            for (int i = 0; i < 10; i++) {
                try {
                    final String add_spaces1 = "                ".substring(0, 16-Bukkit.getOfflinePlayer(UUID.fromString(top.getString("player_uuid"))).getName().length());
                    flytop.get(toping).setCustomName("§a"+(toping-1)+".   §6"+Bukkit.getOfflinePlayer(UUID.fromString(top.getString("player_uuid"))).getName()+add_spaces1+" §7►  §9"+new intFormatter(top.getDouble("Rebirths")).string+" ");
                } catch (RuntimeException ignore) {}
                toping++;

                if (!top.next()) { break; }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
