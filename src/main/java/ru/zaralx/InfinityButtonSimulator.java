package ru.zaralx;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import ru.zaralx.commands.InfinityButtonSimulatorCommand;
import ru.zaralx.events.onPlayerJoin;
import ru.zaralx.events.onPlayerLeave;
import ru.zaralx.events.onPlayerMove;
import ru.zaralx.utils.ButtonInfortmation;
import ru.zaralx.utils.DisplayInformation;
import ru.zaralx.utils.MoneyMaking;
import ru.zaralx.utils.zModules.EntityHider;
import ru.zaralx.utils.zModules.configs.buttonsConfig;
import ru.zaralx.utils.zModules.configs.config;
import ru.zaralx.utils.zModules.configs.defaultConfig;
import ru.zaralx.utils.zModules.configs.defaultPlayerStats;
import ru.zaralx.utils.zModules.database.Save;
import ru.zaralx.utils.zModules.database.TempData;
import ru.zaralx.utils.zModules.database.database;
import ru.zaralx.utils.zModules.intFormatter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class InfinityButtonSimulator extends JavaPlugin {

    private static InfinityButtonSimulator instance;
    private static database db;
    public static List<TempData> data = new ArrayList<>();
    private static EntityHider entityHider;

    @Override
    public void onEnable() {
        instance = this;
        entityHider = new EntityHider(this, EntityHider.Policy.BLACKLIST);

        // Load and update configs
        new defaultConfig().run();
        defaultPlayerStats.setup();
        buttonsConfig.setup();

        // DataBase
        InitializeDataBase();
        db.init();

        // Events
        new onPlayerJoin(this);
        new onPlayerLeave(this);
        new onPlayerMove(this);

        // Commands
        getCommand("InfinityButtonSimulator").setExecutor(new InfinityButtonSimulatorCommand());

        // Loops
        new Save().autoSave();
        new DisplayInformation().loop();

        // Load Buttons Information
        ButtonInfortmation.init();
        MoneyMaking.loop();
    }

    @Override
    public void onDisable() {
        ButtonInfortmation.removeall();
        Save.saveOnlinePlayers();
        db.disconnect();
    }

    private void InitializeDataBase() {
        db = new database();
        try {
            db.connect();
            this.getLogger().info("Database is connected");
        } catch (ClassNotFoundException | SQLException e) {
            this.getLogger().warning("Database not connected");
            this.getServer().getPluginManager().disablePlugin(this);
        }
    }


    public static InfinityButtonSimulator getInstance() {
        return instance;
    }

    public database getDataBase() {
        return db;
    }

    public TempData getPlayerData(String uuid) {
        TempData plr_cfg = null;
        for (int i = 0; i < InfinityButtonSimulator.data.size(); i++) {
            if (Objects.equals(uuid, InfinityButtonSimulator.data.get(i).getUuid())) {
                plr_cfg = InfinityButtonSimulator.data.get(i);
            }
        }
        return plr_cfg;
    }

    public TempData getPlayerData(Player player) {
        return getPlayerData(player.getUniqueId().toString());
    }

    public void removeLaggedStands() {
        List<Entity> entities = Bukkit.getWorld((String) config.get().get("gameWorld")).getEntities();
        for (Entity entity : entities) {
            if (entity.getScoreboardTags().contains("Removable")) {
                entity.remove();
            }
        }
    }
}
