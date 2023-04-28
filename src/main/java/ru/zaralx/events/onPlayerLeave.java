package ru.zaralx.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.zaralx.InfinityButtonSimulator;
import ru.zaralx.utils.zModules.coloredText;
import ru.zaralx.utils.zModules.database.Save;

public class onPlayerLeave implements Listener {
    public onPlayerLeave(InfinityButtonSimulator plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        e.setQuitMessage(coloredText.colorize("%>#d9745e#<%"+e.getPlayer().getName()+" %>#d9915e#<%left.."));

        // Save player data in database and remove from TempData
        for (int i = 0; i < InfinityButtonSimulator.data.size(); i++) {
            if (InfinityButtonSimulator.data.get(i).getUuid().equals(e.getPlayer().getUniqueId().toString())) {
                new Save();
                Save.save(InfinityButtonSimulator.data.get(i));
                InfinityButtonSimulator.data.remove(InfinityButtonSimulator.data.get(i));
                break;
            }
        }
    }
}
