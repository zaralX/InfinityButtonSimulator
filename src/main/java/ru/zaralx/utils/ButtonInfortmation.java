package ru.zaralx.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import ru.zaralx.InfinityButtonSimulator;
import ru.zaralx.utils.zModules.coloredText;
import ru.zaralx.utils.zModules.configs.buttonsConfig;
import ru.zaralx.utils.zModules.configs.config;
import ru.zaralx.utils.zModules.intFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;
import static org.bukkit.Bukkit.getWorld;

public class ButtonInfortmation {
    private static List<ArmorStand> stands = new ArrayList<>();

    public static void summon(String button) {
        Location location = new Location(
                getServer().getWorld(
                        (String) config.get().get("gameWorld")
                ),
                (int) buttonsConfig.get().get(button+".X")+0.5,
                (int) buttonsConfig.get().get(button+".Y")+0.7,
                (int) buttonsConfig.get().get(button+".Z")+0.5
        );
        ArmorStand title = getWorld((String) config.get().get("gameWorld")).spawn(location, ArmorStand.class);
        title.setCanMove(false);
        title.setCustomName(coloredText.colorize("%>#ff2222#<%§lx"+new intFormatter((Double) buttonsConfig.get().get(button+".Multiply")).string));
        title.setCustomNameVisible(true);
        title.setInvisible(true);
        title.setMarker(true);
        title.addScoreboardTag("Removable");
        stands.add(title);

        location.setY(location.getY()-0.3);

        ArmorStand info = getWorld((String) config.get().get("gameWorld")).spawn(location, ArmorStand.class);
        info.setCanMove(false);
        info.setCustomName(coloredText.colorize("%>#22cc22#<%"+new intFormatter((Double) buttonsConfig.get().get(button+".Price")).string+"$"));
        info.setCustomNameVisible(true);
        info.setInvisible(true);
        info.setMarker(true);
        info.addScoreboardTag("Removable");
        stands.add(info);
    }

    public static void removeall() {
        for (int i = 0; i < 10; i++) {
            for (ArmorStand armorStand : stands) {
                getServer().getWorld((String) config.get().get("gameWorld")).getEntity(armorStand.getUniqueId()).remove();
            }
        }
    }

    public static void init() {
        InfinityButtonSimulator.getInstance().removeLaggedStands();
        for (String key : buttonsConfig.get().getKeys(false)) {
            summon(key);
        }
    }
}
