package ru.zaralx.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import ru.zaralx.InfinityButtonSimulator;
import ru.zaralx.utils.BuyButton;
import ru.zaralx.utils.zModules.configs.buttonsConfig;

public class onPlayerMove implements Listener {
    public onPlayerMove(InfinityButtonSimulator plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onPlayerMoving(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        Location location = e.getTo();

        if (e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockZ() != e.getTo().getBlockZ() || e.getFrom().getBlockY() != e.getTo().getBlockY()) {
            for (String button : buttonsConfig.get().getKeys(false)) {
                if ( location.getBlockX() == (int) buttonsConfig.get().get(button+".X") &&
                        location.getBlockY() == (int) buttonsConfig.get().get(button+".Y") &&
                        location.getBlockZ() == (int) buttonsConfig.get().get(button+".Z") )
                {
                    BuyButton.buy(player, button);
                }
            }
        }
    }
}
