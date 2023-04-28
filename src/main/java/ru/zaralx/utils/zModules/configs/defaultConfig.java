package ru.zaralx.utils.zModules.configs;

public class defaultConfig {
    public void run() {

        config.setup();

        config.get().addDefault("gameWorld", "world");
        config.get().addDefault("autoSave.time", 6000);


        config.get().options().copyDefaults(true);
        config.save();
    }
}
