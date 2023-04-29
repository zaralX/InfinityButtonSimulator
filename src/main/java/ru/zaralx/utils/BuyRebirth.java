package ru.zaralx.utils;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import ru.zaralx.InfinityButtonSimulator;
import ru.zaralx.utils.zModules.coloredText;
import ru.zaralx.utils.zModules.configs.rebirthsConfig;
import ru.zaralx.utils.zModules.database.TempData;
import ru.zaralx.utils.zModules.intFormatter;

public class BuyRebirth {
    public static void buy(Player player, String button) {
        TempData player_data = InfinityButtonSimulator.getInstance().getPlayerData(player.getUniqueId().toString());

        if (player_data.getDouble("Gaining") < (Double) rebirthsConfig.get().get(button+".Price")) {
            player.sendTitle("§cНехватает множителя!", coloredText.colorize("%>#0000FF#<%"+new intFormatter((Double) rebirthsConfig.get().get(button+".Price")-player_data.getDouble("Gaining")).string), 0, 10, 0);
            player.playSound(player.getLocation(), Sound.BLOCK_CHAIN_STEP, 100, 2);
            player.spawnParticle(Particle.FLAME, player.getLocation(), 20, 0.1, 0.2, 0.1, 0.08);
            return;
        }

        player.sendTitle("", coloredText.colorize("%>#00FF00#<%+"+new intFormatter((Double) rebirthsConfig.get().get(button+".Multiply")).string+"♣"), 0, 10, 0);
        player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 100, 2);

        player_data.set("Balance", 0.0);
        player_data.set("Gaining", 1.0);
        player_data.set("Rebirths", player_data.getDouble("Rebirths")+(Double) rebirthsConfig.get().get(button+".Multiply"));
    }
}
